package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import classes.Connect;

public class Console extends BaseModel{
	private int ConsoleID;
	private String Name;
	private int Price;
	private int Stock;
	private String Images;
	public int getConsoleID() {
		return ConsoleID;
	}

	public void setConsoleID(int consoleID) {
		ConsoleID = consoleID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public String getImages() {
		return Images;
	}

	public void setImages(String images) {
		Images = images;
	}
	public static Console getLast(){
		try{
			Connect con = new Connect();
			ResultSet rs = con.executeQuery("SELECT * FROM MsConsole ORDER BY ConsoleID DESC");
			if(rs.next()){
				Console temp = new Console();
				temp.setConsoleID(Integer.parseInt(rs.getString("ConsoleID")));
				temp.setName(rs.getString("Name"));
				temp.setImages(rs.getString("Images"));
				temp.setPrice(Integer.parseInt(rs.getString("Price")));
				temp.setStock(Integer.parseInt(rs.getString("Stock")));
				return temp;
			}
		}catch(Exception e){}
		return null;
	}
	public static Console findByID(String ID){
		try{
			Connect con = new Connect();
			ResultSet rs = con.executeQuery("SELECT * FROM MsConsole WHERE ConsoleID = "+ID);
			if(rs.next()){
				Console temp = new Console();
				temp.setConsoleID(Integer.parseInt(rs.getString("ConsoleID")));
				temp.setName(rs.getString("Name"));
				temp.setImages(rs.getString("Images"));
				temp.setPrice(Integer.parseInt(rs.getString("Price")));
				temp.setStock(Integer.parseInt(rs.getString("Stock")));
				con.close();
				return temp;
			}
		}catch(Exception e){}
		return null;
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("INSERT INTO MsConsole(ConsoleID,Name,Price,Stock,Images) VALUES ("+getConsoleID()+",'"+getName()+"',"+getPrice()+","+getStock()+",'"+getImages()+"')");
		con.close();
	}
	public static ArrayList<Console> fetchArray(){
		ArrayList<Console> data = new ArrayList<Console>();
		Connect con = new Connect();
		try{
			ResultSet rs = con.executeQuery("SELECT * FROM MsConsole");
			while(rs.next()){
				Console temp = new Console();
				temp.setConsoleID(Integer.parseInt(rs.getString("ConsoleID")));
				temp.setName(rs.getString("Name"));
				temp.setImages(rs.getString("Images"));
				temp.setPrice(Integer.parseInt(rs.getString("Price")));
				temp.setStock(Integer.parseInt(rs.getString("Stock")));
				data.add(temp);
			}
			con.close();
		}catch(Exception e){}
		return data;
	}
 	@Override
	public boolean findById() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("UPDATE MsConsole SET Name = '"+getName()+"', Price = "+getPrice()+", Stock = "+getStock()+", Images = '"+getImages()+"' WHERE ConsoleID = "+getConsoleID());
		con.close();
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("DELETE FROM MsConsole WHERE ConsoleID = "+getConsoleID());
		con.close();
	}

}
