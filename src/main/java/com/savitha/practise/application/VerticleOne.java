package com.savitha.practise.application;

import com.savitha.practise.model.Address;
import com.savitha.practise.model.Buyer;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.util.Iterator;


/**
 * Created by savithakumarasamy on 6/6/17.
 */
public class VerticleOne extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
      //  router.route().handler(BodyHandler.create());

        // event bus bridge
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        router.route("/eventbus/*").handler(sockJSHandler);

        // static content
        router.route("/*").handler(StaticHandler.create());

        router.exceptionHandler(throwable -> {
            System.err.println("router failure");
            throwable.printStackTrace();
        });

        router.route("/").handler(r->{
            r.response().end("Hello there!");
        });

        router  .get("/enroll/buyers")
                .produces("application/json")
                .handler(this::getBuyers);

        router  .post("/enroll/buyer")
                .produces("application/json")
                .handler(this::addBuyer);

        router  .post("/enroll/supplier")
                .produces("application/json")
                .handler(this::addSupplier);

         vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });

        vertx.eventBus().consumer("v1",message -> System.out.println("v1 received message : "+message.body()));
    }

    private void addBuyer(RoutingContext routingContext)  {
        System.out.println("In addBuyer call");
        HttpServerRequest  request  = routingContext.request();
        HttpServerResponse response = request.response();

        request.bodyHandler((Buffer body) ->{
            Iterator<String> iterator = new QueryStringDecoder(body.toString(), false).parameters().keySet().iterator();

            while (iterator.hasNext()) {
                    vertx.eventBus().send("addBuyer", iterator.next(), result ->{
                        if (result.succeeded())
                            response.end("Buyer added successfully");
                        else
                            response.end("There was trouble adding the buyer. Please try later.");
                    });
                }
            });
    }

    private void addSupplier(RoutingContext routingContext) {
        System.out.println("In addSupplier call");
        routingContext.response().end();
        //vertx.eventBus().send("addSupplier",routingContext);
    }

    private void getBuyers(RoutingContext routingContext)
    {
        /*Use "with" instead of "set" to make construction fluent
        and not break the java bean standard */
        HttpServerResponse response = routingContext.response();

        response .end(Json.encodePrettily(new Buyer()
                                                .withID(123)
                                                .withName("test buyer")
                                                .withAddress(new Address()
                                                            .withAddressLine1("123 test street")
                                                            .withCity("Phoenix")
                                                            .withState("AZ")
                                                            .withZipCode(85050))
        ));
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        Thread.sleep(200);
        System.out.println("Stopping Verticle One");
    }

}
