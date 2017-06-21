package com.savitha.practise.application;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;

/**
 * Created by savithakumarasamy on 6/13/17.
 */
public class VertxApplication {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx =  Vertx.vertx() ;
        vertx.deployVerticle(new VerticleOne(), (AsyncResult<String> stringAsyncResult) -> {
            System.out.println("Verticle One Started");
        });

       vertx.deployVerticle(new BuyerVerticle(), (AsyncResult<String> stringAsyncResult) -> {
            System.out.println("Buyer Verticle Started");
        });

        vertx.deployVerticle(new VerticleTwo(), (AsyncResult<String> stringAsyncResult) -> {
            System.out.println("Verticle Two Started");
        });
        System.out.println("Application terminating");
    }

}
