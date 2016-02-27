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
    public static ArrayList<RecIngredientsModle> getRecipeIngredients(String recID) {
        try {
            String quary = "SELECT R.ingreID,  I.ingreName, I.ingreCategory, R.qty, R.unit, R.price FROM recipeIngredients R INNER JOIN ingredients I ON R.ingreID = I.ingreID WHERE R.recID = '" + recID + "'";
            ResultSet result = DBHandler.getData(conn, quary);
            ArrayList<RecIngredientsModle> list = null;
            if (!result.isBeforeFirst()) {
                System.out.println("Empty Ingredients for Recipe.");
                return null;
            } else {
                list = new ArrayList<RecIngredientsModle>();
                while (result.next()) {
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

    public static ArrayList<IngredientModle> getNotIncludedIngredientsInRec(String recID) {
        try {
            System.out.println("recID :" + recID);
            String selectedIngredientsViewQuary = "CREATE VIEW temRecIngre AS SELECT R.No, R.recID, R.ingreID FROM recipeIngredients R WHERE R.recID = '" + recID + "'";
            int quaryValue = DBHandler.setData(conn, selectedIngredientsViewQuary);
            System.out.println("quary value :"+quaryValue);
            if (quaryValue == 0) {
                System.out.println("Successfully create tempary not included ingredients table.");
                String getNotIncludedQuary = "SELECT I.ingreID, I.ingreName, I.ingreCategory From ingredients I LEFT JOIN temRecIngre T ON I.ingreID = T.ingreID WHERE T.ingreID IS null";
                ResultSet result = DBHandler.getData(conn, getNotIncludedQuary);
                ArrayList<IngredientModle> ingreArrayList = null;
                if (!result.isBeforeFirst()) {
                    System.out.println("All Ingredients Added to Recipe.");
                    return ingreArrayList;
                } else {
                    ingreArrayList = new ArrayList<>();
                    int i = 0;
                    while (result.next()) {
                        System.out.println("i = " + i++);
                        IngredientModle igreModle = new IngredientModle(result.getString(1), result.getString(2), result.getString(3));
                        ingreArrayList.add(igreModle);
                    }
                    System.out.println("get not added ingredients...");
                    String deleteTemQuery = "DROP VIEW temRecIngre";
                    quaryValue = DBHandler.setData(conn, deleteTemQuery);
                    
                    if (quaryValue == 0) {
                        System.out.println("Successfully deleted temRecIngre table.");
                    }
                    return ingreArrayList;
                }
            } else {
                System.out.println("TemRecIngre table create failed.");
                return null;
            }

        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateRecDetails(RecipeModule rm) {
        try {
            String quary = "UPDATE recipes SET recName = '" + rm.getRecName() + "', recCategory = '" + rm.getRecCategory() + "', recQty = '" + rm.getRecQty() + "', recUnit = '" + rm.getRecUnit() + "' WHERE recID = '" + rm.getRecID() + "'";
            int resultValue = DBHandler.setData(conn, quary);
            if (resultValue != 0) {
                System.out.println("Succsfully update Recipe Details...");
                return true;
            } else {
                System.out.println("Fails to update Recipe Details...");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateRecIngreDetails(ArrayList<RecIngredientsModle> list, String recID) {
        try {
            String getQuery = "SELECT recID FROM recipeIngredients WHERE recID = '" + recID + "' ORDER BY recID LIMIT 1";
            ResultSet ingreResultSet = DBHandler.getData(conn, getQuery);
            // Check ngredient include in recipe.
            if (!ingreResultSet.isBeforeFirst()) {
                addAllSelectedIngredients(list);
            } else {
                String delQuery = "DELETE FROM recipeIngredients WHERE recID = '" + recID + "'";
                int delResultValue = DBHandler.setData(conn, delQuery);
                if (delResultValue != 0) {
                    System.out.println("Succsfully deleted include ingredients in recipe.");
                    addAllSelectedIngredients(list);
                    return true;
                } else {
                    System.out.println("Failed to delete include ingredients in recipe.");
                    return false;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean addAllSelectedIngredients(ArrayList<RecIngredientsModle> list) {

        for (int i = 0; i < list.size(); i++) {
            try {
                RecIngredientsModle mod = list.get(i);
                String addQuery = "INSERT INTO recipeIngredients (recID, ingreID, qty, unit, price) VALUES ('" + mod.getRecID() + "', '" + mod.getIngriID() + "', '" + mod.getIngriQty() + "', '" + mod.getIngriUnit() + "', '" + mod.getIngriPrice() + "')";
                int insertResultValue = DBHandler.setData(conn, addQuery);
                if (insertResultValue != 0) {
                    System.out.println("Successfully insert ingredients to recipe.");
                } else {
                    System.out.println("Failed to insert ingredients to recipe.");
                }
            } catch (Exception ex) {
                Logger.getLogger(RecIngredientsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
