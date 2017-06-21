package com.savitha.practise.service.impl;

import com.savitha.practise.model.Buyer;
import com.savitha.practise.service.BuyerCRUDService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * Created by savithakumarasamy on 6/19/17.
 */
public class BuyerCRUDServiceImpl implements BuyerCRUDService {

    private static final String COLLECTION = "Buyer";

    private final MongoClient client;

    public BuyerCRUDServiceImpl(Vertx vertx, JsonObject config) {
        this.client = MongoClient.createShared(vertx, config);
    }


    @Override
    public void upsertBuyer(Buyer buyer, Handler<AsyncResult<Void>> resultHandler) {

       client.insert(COLLECTION, new JsonObject().put("name", buyer.getName())
                                     .put("address",(new JsonObject().put("addressLine1",buyer.getAddress().getAddressLine1())
                                                .put("addressLine2",buyer.getAddress().getAddressLine2())
                                                .put("city",buyer.getAddress().getCity())
                                                .put("state",buyer.getAddress().getState())
                                                .put("zipCode",buyer.getAddress().getZipCode())
        )), handler -> {
           if(handler.succeeded()) {
               System.out.println("Save successful");
               resultHandler.handle(Future.succeededFuture());
           }else
           resultHandler.handle(Future.failedFuture("Error saving buyer"));
        });
    }

    @Override
    public void findBuyer(int buyerID, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void deleteBuyer(int buyerID, Handler<AsyncResult<Void>> resultHandler) {

    }
}
