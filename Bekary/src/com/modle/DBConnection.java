/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modle;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janaka
 */
public class DBConnection {
    private String url = "jdbc:mysql://localhost/bekarydb";
    private String user = "root";
    private String password = "";
    
    private static Connection con= null;
    private static DBConnection dbc = null;
    
    private DBConnection (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static DBConnection createConnection(){
        if(con == null){
            dbc = new DBConnection();
        }
        return dbc;
    }
    
    public static Connection getConnection(){
        return createConnection().con;
    }
     
}
