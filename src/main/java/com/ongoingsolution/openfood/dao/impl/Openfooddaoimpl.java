/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.dao.impl;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.ongoingsolution.openfood.config.MongoConnection;
import com.ongoingsolution.openfood.dao.Openfooddao;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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
            System.out.println("Exception Error mainManagerLoadDashboard");
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

}
