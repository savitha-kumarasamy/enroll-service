package com.savitha.practise.application;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by savithakumarasamy on 6/19/17.
 */
public class SupplierVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws InterruptedException {
        vertx.eventBus().consumer("addSupplier", r -> {
            System.out.println("add supplier request received");
        });
    }

    @Override
    public void stop()  throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Stopping SupplierVerticle");

    }
}
