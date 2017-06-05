/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.dao.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.ongoingsolution.openfood.config.MongoConnection;
import com.ongoingsolution.openfood.dao.Openfooddao;
import com.ongoingsolution.openfood.model.OpenFoodProduct;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xxxxxxx
 */
public class Openfooddaoimpl implements Openfooddao {

    DBCollection collection = new MongoConnection().MongoConn();

    @Override
    public JSONArray getAllProducts() {
        try {
            DBCursor cursor = collection.find();
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray;
        } catch (Exception e) {
            System.out.println("Exception Error getAllProducts");
            return null;
        }
    }

    @Override
    public void firstTimeSaveData() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\Praneeth Madusanka\\Desktop\\OpenFood\\src\\main\\resources\\webservices.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray slideContent = (JSONArray) jsonObject.get("hits");
            Iterator i = slideContent.iterator();
            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                Gson gson = new Gson();
                DBObject dbObject = (DBObject) JSON.parse(gson.toJson(slide));
                WriteResult result = collection.insert(WriteConcern.SAFE, dbObject);
            }
        } catch (IOException | ParseException er) {
            System.out.println("Exception Error add data into db first time" + er);
        }
    }

    @Override
    public boolean checkDcumentEmpty() {
        try {
            DBCursor cursor = collection.find();
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray.isEmpty() == false;
        } catch (Exception e) {
            System.out.println("Exception Error: document error function");
            return true;
        }
    }

    @Override
    public JSONArray getAllProductsOrderByQuentity() {
        try {
            BasicDBObject sortQuery = new BasicDBObject();
            sortQuery.put("_source.quantity", 1);
            DBCursor cursor = collection.find().sort(sortQuery);
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray;
        } catch (Exception e) {
            System.out.println("Exception Error getAllProductsOrderByQuentity");
            return null;
        }
    }

    @Override
    public JSONArray getProductsByName(String productName) {
        try {
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_source.display_name_translations", new BasicDBObject("fr", productName));
            DBCursor cursor = collection.find(whereQuery);
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray;
        } catch (Exception e) {
            System.out.println("Exception Error getProductsByName");
            return null;
        }
    }

    @Override
    public JSONArray getProductsByNameAndQuantity(String productName, int quantity) {
        try {
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_source.display_name_translations", new BasicDBObject("fr", productName));
            whereQuery.put("_source.quantity", quantity);
            DBCursor cursor = collection.find(whereQuery);
            String dataUser = JSON.serialize(cursor);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(dataUser);
            JSONArray jsonarray = (JSONArray) obj;
            return jsonarray;
        } catch (Exception e) {
            System.out.println("Exception Error getProductsByNameAndQuantity");
            return null;
        }
    }

    @Override
    public boolean createProduct(OpenFoodProduct createdproduct) {
        try {
            JSONObject json = new JSONObject();
            JSONObject source = new JSONObject();
            json.put("ingredients_translations", createdproduct.getIngredients_translations());
            json.put("unit", createdproduct.getUnit());
            json.put("portion_unit", createdproduct.getPortion_unit());
            json.put("quantity", createdproduct.getQuantity());
            json.put("display_name_translations", createdproduct.getDisplay_name_translations());
            json.put("portion_quantity", createdproduct.getPortion_quantity());
            json.put("nutrients", createdproduct.getNutrients());
            source.put("_source", json);

            Gson gson = new Gson();
            DBObject dbObject = (DBObject) JSON.parse(gson.toJson(source));
            WriteResult result = collection.insert(WriteConcern.SAFE, dbObject);
            return true;
        } catch (Exception e) {
            System.out.println("Exception Error createProduct");
            return false;
        }
    }

    @Override
    public boolean updateProduct(OpenFoodProduct updatedproduct) {
        try {
            JSONObject json = new JSONObject();
            JSONObject source = new JSONObject();
            json.put("ingredients_translations", updatedproduct.getIngredients_translations());
            json.put("unit", updatedproduct.getUnit());
            json.put("portion_unit", updatedproduct.getPortion_unit());
            json.put("quantity", updatedproduct.getQuantity());
            json.put("display_name_translations", updatedproduct.getDisplay_name_translations());
            json.put("portion_quantity", updatedproduct.getPortion_quantity());
            json.put("nutrients", updatedproduct.getNutrients());
            source.put("_source", json);

            BasicDBObject newDocumentOne = new BasicDBObject();
            newDocumentOne.append("$set", source);
            BasicDBObject searchQuery = new BasicDBObject().append("_source.display_name_translations", updatedproduct.getDisplay_name_translations());
            collection.update(searchQuery, newDocumentOne);
            return true;
        } catch (Exception e) {
            System.out.println("Exception Error updateProduct");
            return false;
        }
    }

    @Override
    public boolean deleteProduct(String Id) {
        try {
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_id", new ObjectId(Id));
            System.out.println(whereQuery);
            WriteResult result = collection.remove(whereQuery, WriteConcern.SAFE);
            return result.getN() == 1;
        } catch (Exception e) {
            System.out.println("Exception Error deleteProduct");
            return false;
        }
    }

}
