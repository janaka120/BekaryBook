/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.importstuff;

import com.controller.IngredientController;
import com.modle.DBConnection;
import com.modle.DBHandler;
import com.mysql.jdbc.jdbc2.optional.ConnectionWrapper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janaka
 */
public class ImportantStuff {
    private static Connection con = DBConnection.getConnection();
    
    public static String integrateID(String tableName, String tableColum){
        try {
            String getCurrentIDquary = "SELECT No FROM "+tableName+" ORDER BY No DESC LIMIT 1";
            ResultSet res = DBHandler.getData(con, getCurrentIDquary);
            String id = "";
            if(!res.isBeforeFirst()){
                    return "01";
            }else{
                while(res.next()){
                    System.out.println("colum v: "+res.getInt(1));
                    if(res.getInt(1) < 10){
                        id = "0"+(res.getInt(1)+1);
                        System.out.println("less 10 : "+ id);
                    }else{
                        id = id+(res.getInt(1)+1);
                    }
                  }
                System.out.println("No. :" + id);
                return id;
            }
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
