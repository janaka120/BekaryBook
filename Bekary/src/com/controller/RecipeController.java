/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;
import com.modle.RecipeModule;
import com.importstuff.ImportantStuff;
import com.modle.DBConnection;
import com.modle.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author janaka
 */
public class RecipeController {
    private static Connection con = DBConnection.getConnection();
    
    public static boolean addRecipe(RecipeModule rm){
        try {
            String no = ImportantStuff.integrateID("recipes", "No");
            String recID = "REC".concat(no);
            String quary = "INSERT INTO recipes VALUES("+Integer.parseInt(no)+",'"+recID+"','"+rm.getRecName()+"', '"+rm.getRecCategory()+"', '"+rm.getRecQty()+"', '"+rm.getRecUnit()+"')";
            int result = DBHandler.setData(con, quary);
            if(result != 0){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    public static ArrayList<RecipeModule> searchRecipes(String repName, String repCategory){
        try {
            String quary = "SELECT * FROM recipes WHERE recName LIKE '%"+repName+"%' AND recCategory LIKE '%"+repCategory+"%'";
            ResultSet result = DBHandler.getData(con, quary);
            ArrayList<RecipeModule> al = null;
            if(!result.isBeforeFirst()){
                System.out.println("Empty Recipes Dataset.");                
                return al;
            }else{
                al = new ArrayList<RecipeModule>();
                while(result.next()){
                    RecipeModule rm = new RecipeModule(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                    al.add(rm);
                }
                return al;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}