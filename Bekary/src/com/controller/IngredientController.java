/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;

/**
 *
 * @author janaka
 */

import com.modle.DBConnection;
import com.modle.DBHandler;
import com.modle.IngredientModle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.importstuff.ImportantStuff;

public class IngredientController {
    private static Connection con = DBConnection.getConnection();
   
    public static boolean addIngredient(IngredientModle im){
        try {
            String i = ImportantStuff.integrateID("ingredients", "No");
            
//            System.out.println("id i : "+i);
            String a = "INGRE".concat(i);
//            System.out.println("a add: "+a);
//            System.out.println(" name: "+ im.getIngreName());
//            System.out.println("id categ: "+im.getIngreCategory());
//            System.out.println(Integer.parseInt(i));
            
            String query = "INSERT INTO ingredients VALUES ("+Integer.parseInt(i)+",'"+a+"','"+im.getIngreName()+"', '"+im.getIngreCategory()+"')";
//            System.out.println("cccc");
            int success = DBHandler.setData(con, query);
//            System.out.println("cccc 002");
            if(success != 0){
                System.out.println("Ingregients add successfull");
                return true;
            }else{
                System.out.println("Ingregients add Fail.");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ArrayList<IngredientModle> getAllIngredients(){
        try {
            String quary = "SELECT * FROM ingredients";
            ResultSet result =  DBHandler.getData(con, quary);
            return addResultsToArrayList(result);
//            ArrayList<IngredientModle> ingreList = new ArrayList<IngredientModle>();
//            if(!result.isBeforeFirst()){
//                System.out.println("Empty Ingredients.");
//                return null;
//            }else{
//                while(result.next()){
//                    IngredientModle im = new IngredientModle(result.getString(2), result.getString(3), result.getString(4));
//                    ingreList.add(im); 
//                }
//                return ingreList;
//            }            
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean updateIngredient(IngredientModle im){
        try {
            String quary = "UPDATE ingredients SET ingreName = '"+im.getIngreName()+"', ingreCategory = '"+im.getIngreCategory()+"' WHERE ingreID = '"+im.getIngreID()+"'";
            int result = DBHandler.setData(con, quary);
            if(result != 0){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ArrayList<IngredientModle> searchIngredients(String name, String category){
        if(name != null && category != null){
            try {
                String quary = "SELECT * FROM ingredients WHERE ingreName LIKE '%"+name+"%' AND ingreCategory LIKE '%"+category+"%'";
                ResultSet result = DBHandler.getData(con, quary);
                return addResultsToArrayList(result);                
            } catch (Exception ex) {
                Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static boolean removeIngredient(String id){
        try {
            String quary = "DELETE FROM ingredients WHERE ingreID = '"+id+"'";
            int result = DBHandler.setData(con, quary);
            if(result != 0){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ArrayList<IngredientModle> addResultsToArrayList(ResultSet result){
        ArrayList<IngredientModle> al = null;
        try {                      
            if(!result.isBeforeFirst()){
               System.out.println("No data feach from ingredient table."); 
            }else{
                al = new ArrayList<>();
                while(result.next()){
                    IngredientModle im = new IngredientModle(result.getString(2), result.getString(3), result.getString(4));
                    al.add(im);
                }
                return al;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
//    public static String integrateID(String tableName, String tableColum){
//        try {
//            String getCurrentIDquary = "SELECT No FROM "+tableName+" ORDER BY No DESC LIMIT 1";
//            ResultSet res = DBHandler.getData(con, getCurrentIDquary);
//            String id = "";
//            if(!res.isBeforeFirst()){
//                    return "01";
//            }else{
//                while(res.next()){
//                    System.out.println("colum v: "+res.getInt(1));
//                    if(res.getInt(1) < 10){
//                        id = "0"+(res.getInt(1)+1);
//                        System.out.println("less 10 : "+ id);
//                    }else{
//                        id = id+(res.getInt(1)+1);
//                    }
//                  }
//                System.out.println("No. :" + id);
//                return id;
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
