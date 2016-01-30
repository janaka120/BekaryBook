/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author janaka
 */
public class DBHandler {
    public static int setData(Connection con,String query ) throws Exception{
			Statement stm=con.createStatement();
			int res=stm.executeUpdate(query);
			return res;
	}

	public static ResultSet getData(Connection con,String query ) throws Exception{
			Statement stm=con.createStatement();
			ResultSet res=stm.executeQuery(query);
			return res;
	}
}
