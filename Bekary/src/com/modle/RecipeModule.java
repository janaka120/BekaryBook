/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modle;

/**
 *
 * @author janaka
 */
public class RecipeModule {
    private String recID;
    private String recName;
    private String recCategory;
    private String recQty;
    private String recUnit;
    
    public RecipeModule(){    
    }
    
    public RecipeModule(String recName, String recCategory, String recQty, String recUnit) {
        this.recName = recName;
        this.recCategory = recCategory;
        this.recQty = recQty;
        this.recUnit = recUnit;
    }

    public RecipeModule(String recID, String recName, String recCategory, String recQty, String recUnit) {
        this.recID = recID;
        this.recName = recName;
        this.recCategory = recCategory;
        this.recQty = recQty;
        this.recUnit = recUnit;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }
    
    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecCategory() {
        return recCategory;
    }

    public void setRecCategory(String recCategory) {
        this.recCategory = recCategory;
    }

    public String getRecQty() {
        return recQty;
    }

    public void setRecQty(String recQty) {
        this.recQty = recQty;
    }

    public String getRecUnit() {
        return recUnit;
    }

    public void setRecUnit(String recUnit) {
        this.recUnit = recUnit;
    }
    
}
