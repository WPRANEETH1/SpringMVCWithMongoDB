/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ongoingsolution.openfood.model;

import org.json.simple.JSONObject;

/**
 *
 * @author xxxxxxx
 */
public class OpenFoodProduct {

    private JSONObject ingredients_translations;
    private String unit;
    private String portion_unit;
    private String quantity;
    private JSONObject display_name_translations;
    private String portion_quantity;
    private JSONObject nutrients;

    /**
     * @return the ingredients_translations
     */
    public JSONObject getIngredients_translations() {
        return ingredients_translations;
    }

    /**
     * @param ingredients_translations the ingredients_translations to set
     */
    public void setIngredients_translations(JSONObject ingredients_translations) {
        this.ingredients_translations = ingredients_translations;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the portion_unit
     */
    public String getPortion_unit() {
        return portion_unit;
    }

    /**
     * @param portion_unit the portion_unit to set
     */
    public void setPortion_unit(String portion_unit) {
        this.portion_unit = portion_unit;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the display_name_translations
     */
    public JSONObject getDisplay_name_translations() {
        return display_name_translations;
    }

    /**
     * @param display_name_translations the display_name_translations to set
     */
    public void setDisplay_name_translations(JSONObject display_name_translations) {
        this.display_name_translations = display_name_translations;
    }

    /**
     * @return the portion_quantity
     */
    public String getPortion_quantity() {
        return portion_quantity;
    }

    /**
     * @param portion_quantity the portion_quantity to set
     */
    public void setPortion_quantity(String portion_quantity) {
        this.portion_quantity = portion_quantity;
    }

    /**
     * @return the nutrients
     */
    public JSONObject getNutrients() {
        return nutrients;
    }

    /**
     * @param nutrients the nutrients to set
     */
    public void setNutrients(JSONObject nutrients) {
        this.nutrients = nutrients;
    }
}
