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
public class RecIngredientsModle {
    
    private String recID;  

    public RecIngredientsModle(String ingreID, String ingreName, String ingreCategoty, String ingreQty, String ingreUnit, String ingrePrice) {
        this.ingreID = ingreID;
        this.ingreName = ingreName;
        this.ingreCategoty = ingreCategoty;
        this.ingreQty = ingreQty;
        this.ingreUnit = ingreUnit;
        this.ingrePrice = ingrePrice;
    }
    private String ingreID;
    private String ingreName;
    private String ingreCategoty;
    private String ingreQty;
    private String ingreUnit;
    private String ingrePrice;

    public String getIngreName() {
        return ingreName;
    }

    public void setIngreName(String ingreName) {
        this.ingreName = ingreName;
    }

    public String getIngreCategoty() {
        return ingreCategoty;
    }

    public void setIngreCategoty(String ingreCategoty) {
        this.ingreCategoty = ingreCategoty;
    }
    

    public RecIngredientsModle() {
    }
    
    public RecIngredientsModle(String recID, String ingriID,String ingreName, String ingreCategory, String ingriQty, String ingriUnit, String ingriPrice){
        this.recID = recID;
        this.ingreID = ingriID;
        this.ingreName = ingreName;
        this.ingreCategoty = ingreCategory;
        this.ingreQty = ingriQty;
        this.ingreUnit = ingriUnit;
        this.ingrePrice = ingriPrice;
    }
    
    public void setRecID(String id){
        this.recID = id;
    }
    public String getRecID(){
        return recID;
    }
    
    public void setIngriID(String id){
        this.ingreID = id;
    }
    public String getIngriID(){
        return this.ingreID;
    }
    
    public void setIngriQty(String qty){
        this.ingreQty = qty;
    }
    public String getIngriQty(){
        return this.ingreQty;
    }
    
    public void setIngriUnit(String unit){
        this.ingreUnit = unit;
    }
    public String getIngriUnit(){
        return this.ingreUnit;
    }
    
    public void setIngriPrice(String price){
        this.ingrePrice = price;
    }
    public String getIngriPrice(){
        return this.ingrePrice;
    }
}
