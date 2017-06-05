/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.services.impl;

import com.ongoingsolution.openfood.dao.Openfooddao;
import com.ongoingsolution.openfood.model.OpenFoodProduct;
import com.ongoingsolution.openfood.services.Openfoodservices;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xxxxxxx
 */
@Service

public class Openfoodservicesimpl implements Openfoodservices {

    @Autowired
    private Openfooddao openfooddao;

    @Transactional
    @Override
    public Response getAllProducts() {
        try {
            boolean val = openfooddao.checkDcumentEmpty();
            if (val == false) {
                openfooddao.firstTimeSaveData();
            }
            JSONArray allproduct = openfooddao.getAllProducts();
            return Response.ok(allproduct).build();
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response getAllProductsOrderByQuentity() {
        try {
            boolean val = openfooddao.checkDcumentEmpty();
            if (val == false) {
                openfooddao.firstTimeSaveData();
            }
            JSONArray allproduct = openfooddao.getAllProductsOrderByQuentity();
            return Response.ok(allproduct).build();
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response getProductsByName(String productName) {
        try {
            boolean val = openfooddao.checkDcumentEmpty();
            if (val == false) {
                openfooddao.firstTimeSaveData();
            }
            JSONArray allproduct = openfooddao.getProductsByName(productName);
            return Response.ok(allproduct).build();
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response getProductsByNameAndQuantity(String productName, int quantity) {
        try {
            boolean val = openfooddao.checkDcumentEmpty();
            if (val == false) {
                openfooddao.firstTimeSaveData();
            }
            JSONArray allproduct = openfooddao.getProductsByNameAndQuantity(productName, quantity);
            return Response.ok(allproduct).build();
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response createProduct(OpenFoodProduct creayeproduct) {
        try {
            boolean val = openfooddao.checkDcumentEmpty();
            if (val == false) {
                openfooddao.firstTimeSaveData();
            }
            Boolean val_create = openfooddao.createProduct(creayeproduct);
            if (val_create == true) {
                return Response.ok(Response.Status.CREATED).build();
            } else {
                return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response updateProduct(OpenFoodProduct updateproduct) {
        try {
            Boolean val = openfooddao.updateProduct(updateproduct);
            if (val == true) {
                return Response.ok(Response.Status.CREATED).build();
            } else {
                return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Transactional
    @Override
    public Response deleteProduct(String Id) {
        try {
            Boolean val = openfooddao.deleteProduct(Id);
            if (val == true) {
                return Response.ok(Response.Status.CREATED).build();
            } else {
                return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.ok(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
