package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	public ResultSet rs;
	public Statement st;
	Connection con;
	PreparedStatement pStat;
	ResultSetMetaData rsm;
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager
					.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}; DBQ=Database1.mdb");
			st = con.createStatement(1004, 1008);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection");
		}
		
	}

	public ResultSet executeQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection RS");
		}
		return rs;
	}

	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PreparedStatement prepareStatement(String arg) throws Exception{
		return con.prepareStatement(arg);
	}
}
