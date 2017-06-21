package com.savitha.practise.application;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by savithakumarasamy on 6/13/17.
 */
public class VerticleTwo extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws InterruptedException {
        vertx.eventBus().send("addBuyer", "message 2");

        Thread.sleep(200);
       // vertx.eventBus().publish("addBuyer", "message 2");
        vertx.eventBus().send   ("v1", "message 1");
        Thread.sleep(200);
        System.out.println("Exit Verticle Two Start method");

    }

    @Override
    public void stop() throws Exception {
        Thread.sleep(200);
        System.out.println("Stopping Verticle Two");
        try {
            super.stop();
        } catch (Exception e) {
            throw e;
        }

    }


}
