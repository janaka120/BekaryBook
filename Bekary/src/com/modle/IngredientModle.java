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
public class IngredientModle {
    private String ingreId, ingreName, ingreCategory;
    
    public IngredientModle(){
        
    }
    
    public IngredientModle(String ingreName, String ingreCategory){
        this.ingreName = ingreName;
        this.ingreCategory = ingreCategory;
    }
    
    public IngredientModle(String ingreId, String ingreName, String ingreCategory) {
        this.ingreId = ingreId;
        this.ingreName = ingreName;
        this.ingreCategory = ingreCategory;
    }
    
    public void setIngreName(String name){
        this.ingreName = name;
    }
    
    public String getIngreName(){
        return this.ingreName;
    }
    
    public void setIngreCategoty(String categoty){
        this.ingreCategory = categoty;
    }
    
    public String getIngreCategory(){
        return this.ingreCategory;
    }
    
    public void setIngreID(String id){
        this.ingreId = id;
    }
    
    public String getIngreID(){
        return this.ingreId;
    }
            
}
