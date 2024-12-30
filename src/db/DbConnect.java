package db;

import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	public static Connection c;
	public static Statement st;
	
	static {
		try {
			c =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "spendingdb", "spendingdb");
			st = c.createStatement();
	}catch(Exception ex) {
		JOptionPane.showMessageDialog(null,ex);
	}
	}
	
}
