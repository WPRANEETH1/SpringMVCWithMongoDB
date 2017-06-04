/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import com.ongoingsolution.openfood.config.MongoConnection;
import com.ongoingsolution.openfood.dao.Openfooddao;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author xxxxxxx
 */
public class Openfooddaoimpl implements Openfooddao {

    DBCollection collection = new MongoConnection().MongoConn();

    @Override
    public JSONArray getAllProducts() {
        try {
            BasicDBObject whereQuery = new BasicDBObject();
            BasicDBObject field = new BasicDBObject();
            BasicDBObject sortQuery = new BasicDBObject();

//            whereQuery.put("createdprojectData", "createdprojectData.length>60");
            field.put("createdprojectName", 1);
            field.put("createdprojectData", 1);
//            field.put("createdprojectCategory", 1);
            sortQuery.put("createdprojectDateTime", -1);

            //DBCursor cursor = collection.find(whereQuery, field).sort(sortQuery);

            DBCursor cursor = collection.find();
            
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray;
        } catch (Exception e) {
            System.out.println("Exception Error mainManagerLoadDashboard");
            return null;
        }
    }

}
