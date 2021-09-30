package kr.co.tjoeun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyOracle {
	private static Connection conn;
	private static String driver="oracle.jdbc.OracleDriver";
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";	//1521

	
}
