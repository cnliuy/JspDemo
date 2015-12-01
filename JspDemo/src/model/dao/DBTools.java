package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTools {
	
	private String user = Messages.getString("DBTools.user");
	private String pwd = Messages.getString("DBTools.pwd");
	private String url = Messages.getString("DBTools.url");
	private String classname = Messages.getString("DBTools.classname");  
	
	

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * 
	 * @return��ݿ�l�Ӷ���
	 */
	public Connection getConn() {
		Connection connection = null;
		try {
			//Returns the Class object associated with the class or interface with
			//the given string name.
			Class.forName(classname);
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * sql update
	 */
	public int executeUpdate(String sql) {
		int i = 0;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			closeStmt();
			closeConn();
		}
		return i;
	}

	/**
	 * sql query
	 */
	public ResultSet executeQuery(String sql) {		
		
		try {
			connection = getConn();
			//Statement statement = null;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	/**
	 *  close Connection 
	 */
	public void closeConn() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 
	 * 
	 */
	public void closeStmt() {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * close ResultSet
	 * 
	 * 
	 */
	public void closeRs() {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeRs(DBTools dbtools) {
		try {
			dbtools.resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn(DBTools dbtools) {
		try {
			dbtools.connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}


	public void closeStmt(DBTools dbtools) {
		try {
			dbtools.statement.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}


}
