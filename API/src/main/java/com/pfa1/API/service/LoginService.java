package com.pfa1.API.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pfa1.API.model.Tokenlog;

public class LoginService {
	private static String url = "jdbc:mysql://localhost:3306/mcd1";
	private static String username = "root";
	private static String password = "root";
	//Test du validité d'une session a partir du token
	public static boolean checkSession(String session) throws ClassNotFoundException, SQLException {
		String sql = "select * from Users where session=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
	//Recuperation de token à partir du username et password
	public static Tokenlog getSession(String Login,String Pass) throws ClassNotFoundException, SQLException {
		String sql = "select * from Users where Login=? and PWD=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, Login);
		st.setString(2, Pass);
		ResultSet rs = st.executeQuery();
		Tokenlog token = new Tokenlog();
		if (rs.next()) {
			token.setToken(rs.getString("session"));
			token.setType(rs.getString("type"));
			return token;
		}
		return null;
	}
}
