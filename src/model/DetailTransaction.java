package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Connect;

public class DetailTransaction extends BaseModel{
	private HeaderTransaction header;
	private Console console;
	private int qty;
	public DetailTransaction(){
		
		
	}
	public DetailTransaction(HeaderTransaction header, Console console, int qty){
		this.header = header;
		this.console = console;
		this.qty = qty;
	}
	public static ArrayList<DetailTransaction> fetchDetail(HeaderTransaction header){
		ArrayList<DetailTransaction> retval = new ArrayList<DetailTransaction>();
		Connect con = new Connect();
		ResultSet rs= con.executeQuery("SELECT * FROM DetailTransaction WHERE TransactionID = "+header.getTransactionID());
		try {
			while(rs.next()){
				DetailTransaction dt = new DetailTransaction();
				dt.setHeader(header);
				dt.setConsole(Console.findByID(rs.getString("ConsoleID")));
				dt.setQty(rs.getInt("Quantity"));
				retval.add(dt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}
	public HeaderTransaction getHeader() {
		return header;
	}

	public void setHeader(HeaderTransaction header) {
		this.header = header;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("INSERT INTO DetailTransaction VALUES("+header.getTransactionID()+","+console.getConsoleID()+","+getQty()+")");
		System.out.println("INSERT INTO DetailTransaction VALUES("+header.getTransactionID()+","+console.getConsoleID()+","+getQty()+")");
		console.setStock(console.getStock()-getQty());
		console.update();
	}

	@Override
	public boolean findById() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
