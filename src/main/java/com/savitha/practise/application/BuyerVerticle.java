package com.savitha.practise.application;

import com.savitha.practise.model.Buyer;
import com.savitha.practise.service.BuyerCRUDService;
import com.savitha.practise.service.impl.BuyerCRUDServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;

/**
 * Created by savithakumarasamy on 6/19/17.
 */
public class BuyerVerticle extends AbstractVerticle {

    BuyerCRUDService service;

    @Override
    public void start(Future<Void> startFuture) throws InterruptedException {
        service = new BuyerCRUDServiceImpl(vertx,config());

        System.out.println("In Buyer Verticle start method");

        vertx.eventBus().consumer("addBuyer", message -> {
            System.out.println("add buyer request received");
            Buyer buyer = Json.decodeValue( message.body().toString(),Buyer.class);
            service.upsertBuyer(buyer, h-> {
                        if (h.succeeded()) {
                            message.reply("Buyer data added successfully");
                            System.out.println("Add buyer completed");
                        } else if (h.failed()){
                            message.fail(420,"Error encountered when adding data");
                            System.out.println("Add buyer encountered an error");
                        }
                    }
            );

        });
    }

    @Override
    public void stop()  throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Stopping BuyerVerticle");

    }
}
