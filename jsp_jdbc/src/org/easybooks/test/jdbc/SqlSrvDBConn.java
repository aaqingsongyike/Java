package org.easybooks.test.jdbc;
import java.sql.*;
public class SqlSrvDBConn {
	private Statement stmt;	//Statement对象（语句）
	private Connection conn;	//Connection对象（连接）
	ResultSet rs;	//Result对象（结果集）
	//在构造方法中创建数据库连接
	public SqlSrvDBConn(){
		stmt = null;
		try{
			/**加载并注册SQLServer2008/2012的JDBC驱动*/
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			/**编写连接字符串，获取创建连接*/
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+ "databaseName=TEST","sa","123456");
		}catch(Exception e){
			e.printStackTrace();
		}
		rs = null;
	}
	//执行查询类的SQL语句，有返回集
	public ResultSet executeQuery(String sql){
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);	//执行查询语句
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Data.executeQuery:" + e.getMessage());
		}
		return rs;	//返回结果集
	}
	//关闭对象
	public void closeStmt(){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Data.executeQuery:" + e.getMessage());
		}
	}
	public void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Data.executeQuery:" + e.getMessage());
		}
	}
}
