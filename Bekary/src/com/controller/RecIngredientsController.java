/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;
import com.modle.DBConnection;
import com.modle.DBHandler;
import com.modle.IngredientModle;
import com.modle.RecIngredientsModle;
import com.modle.RecipeModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
/**
 *
 * @author janaka
 */
public class RecIngredientsController {
    private static Connection conn = DBConnection.getConnection();

//    public RecIngredientsController() {
//        conn = DBConnection.getConnection();
//    }
    
    public static ArrayList<RecIngredientsModle> getRecipeIngredients(String recID){
        try {
            String quary = "SELECT R.ingreID,  I.ingreName, I.ingreCategory, R.qty, R.unit, R.price FROM recipeIngredients R INNER JOIN ingredients I ON R.ingreID = I.ingreID WHERE R.recID = '"+recID+"'";
            ResultSet result = DBHandler.getData(conn, quary);
            ArrayList<RecIngredientsModle> list = null;
            if(!result.isBeforeFirst()){
                System.out.println("Empty Ingredients for Recipe.");
                return list;
            }else{
                list = new ArrayList<RecIngredientsModle>();
                while(result.next()){
                    RecIngredientsModle rm = new RecIngredientsModle(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                    list.add(rm);
                }
                return list;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<IngredientModle> getNotIncludedIngredientsInRec(String recID){
        try {
            String quary = "SELECT I.ingreID, I.ingreName, I.ingreCategory From ingredients I LEFT JOIN recipeIngredients R ON I.ingreID = R.ingreID WHERE R.recID != '"+recID+"'";
            ResultSet result = DBHandler.getData(conn, quary);
            ArrayList<IngredientModle> ingreArrayList = null;
            if(!result.isBeforeFirst()){
                System.out.println("All Ingredients Added to Recipe.");
                return ingreArrayList;
            }else{
                ingreArrayList = new ArrayList<>();
                while(result.next()){
                    IngredientModle igreModle = new IngredientModle(result.getString(1), result.getString(1), result.getString(2));
                    ingreArrayList.add(igreModle);
                }
                System.out.println("get not added ingredients...");
                return ingreArrayList;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean updateRecDetails(RecipeModule rm){
        try {
                String quary = "UPDATE recipes SET recName = '"+rm.getRecName()+"', recCategory = '"+rm.getRecCategory()+"', recQty = '"+rm.getRecQty()+"', recUnit = '"+rm.getRecUnit()+"' WHERE recID = '"+rm.getRecID()+"'";
            int resultValue = DBHandler.setData(conn, quary);
            if(resultValue != 0){
                System.out.println("Succsfully update Recipe Details...");
                return true;
            }else{
                System.out.println("Fails to update Recipe Details...");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
