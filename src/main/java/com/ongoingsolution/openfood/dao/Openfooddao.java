/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.dao;

import com.ongoingsolution.openfood.model.OpenFoodProduct;
import org.json.simple.JSONArray;

/**
 *
 * @author xxxxxxx
 */
public interface Openfooddao {

    JSONArray getAllProducts();

    JSONArray getAllProductsOrderByQuentity();

    JSONArray getProductsByName(String productName);

    JSONArray getProductsByNameAndQuantity(String productName, int quantity);

    boolean createProduct(OpenFoodProduct createdproduct);

    boolean updateProduct(OpenFoodProduct updateproduct);

    boolean deleteProduct(String Id);

    void firstTimeSaveData();

    boolean checkDcumentEmpty();
}
