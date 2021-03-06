package kr.co.tjoeun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyOracle {

	private static Connection conn;
	private static String driver="oracle.jdbc.OracleDriver";
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";	//1521
	private static String user="scott";	//scott
	private static String password="tiger";	//tiger


	
	private MyOracle() {}
	
	public static Connection getConnection() throws SQLException {
		if(conn==null || conn.isClosed()) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn=DriverManager.getConnection(url, user, password);
		}
		return conn;
	}
}