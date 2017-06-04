/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.config;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.DB;

/**
 *
 * @author xxxxxxx
 */
public class MongoConnection {

    private DBCollection collection;

    public DBCollection MongoConn() {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("webservices");
            collection = db.getCollection("openfood");
        } catch (Exception ex) {
            System.out.println("Exception Error mongo connection" + ex);
        }
        return collection;
    }
}
