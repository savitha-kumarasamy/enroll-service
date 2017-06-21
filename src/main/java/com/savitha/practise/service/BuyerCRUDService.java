package com.savitha.practise.service;

import com.savitha.practise.model.Buyer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by savithakumarasamy on 6/18/17.
 */
public interface BuyerCRUDService {

     void upsertBuyer(Buyer buyer, Handler<AsyncResult<Void>> resultHandler);
     void findBuyer(int buyerID, Handler<AsyncResult<Void>> resultHandler);
     void deleteBuyer(int buyerID, Handler<AsyncResult<Void>> resultHandler);
}
