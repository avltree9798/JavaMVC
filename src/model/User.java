package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import classes.Connect;

public class User extends BaseModel{
	private int UserID;
	private String Username;
	private String Password;
	private String Gender;
	private String Email;
	private String Hobby;
	private String Name;
	private String Role;
	public int getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = Integer.parseInt(userID);
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getHobby() {
		return Hobby;
	}

	public void setHobby(String hobby) {
		Hobby = hobby;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("INSERT INTO MsUser(UserID,Username,Password,Gender,Email,Hobby,Name,Role) VALUES("+getUserID()+",'"+getUsername()+"','"+getPassword()+"','"+getGender()+"','"+getEmail()+"','"+getHobby()+"','"+getName()+"','"+getRole()+"')");
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
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE MsUser SET Username = ? , Password = ? , Gender = ? , Email = ? , Hobby = ? , Name = ? , Role = ? WHERE UserID = ?");
			ps.setString(1, getUsername());
			ps.setString(2, getPassword());
			ps.setString(3, getGender());
			ps.setString(4, getEmail());
			ps.setString(5, getHobby());
			ps.setString(6, getName());
			ps.setString(7, getRole());
			ps.setInt(8, getUserID());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		Connect con = new Connect();
		con.executeUpdate("DELETE FROM MsUser WHERE UserID = "+getUserID());
		con.close();
	}
	public static User getLast(){
		try{
			Connect con = new Connect();
			ResultSet rs = con.executeQuery("SELECT * FROM MsUser ORDER BY UserID DESC");
			if(rs.next()){
				User u = new User();
				u.setUserID(rs.getString("UserID"));
				u.setName(rs.getString("Name"));
				u.setEmail(rs.getString("Email"));
				u.setGender(rs.getString("Gender"));
				u.setHobby(rs.getString("Hobby"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("Role"));
				u.setUsername(rs.getString("Username"));
				return u;
			}
		}catch(Exception e){}
		return null;
	}
	public static User getUser(String username, String password){
		Connect con = new Connect();
		try{
			ResultSet rs = con.executeQuery("SELECT * FROM MsUser WHERE Username = '"+username+"' AND Password = '"+password+"' ");
			if(rs.next()){
				User u = new User();
				u.setUserID(rs.getString("UserID"));
				u.setName(rs.getString("Name"));
				u.setEmail(rs.getString("Email"));
				u.setGender(rs.getString("Gender"));
				u.setHobby(rs.getString("Hobby"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("Role"));
				u.setUsername(rs.getString("Username"));
				return u;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	public static User getUserByID(int UserID){
		Connect con = new Connect();
		try{
			ResultSet rs = con.executeQuery("SELECT * FROM MsUser WHERE UserID = "+UserID);
			if(rs.next()){
				User u = new User();
				u.setUserID(rs.getString("UserID"));
				u.setName(rs.getString("Name"));
				u.setEmail(rs.getString("Email"));
				u.setGender(rs.getString("Gender"));
				u.setHobby(rs.getString("Hobby"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("Role"));
				u.setUsername(rs.getString("Username"));
				return u;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	public static ArrayList<User> fetchArray(){
		ArrayList<User> data = new ArrayList<User>();
		Connect con = new Connect();
		try{
			ResultSet rs = con.executeQuery("SELECT * FROM MsUser");
			while(rs.next()){
				User u = new User();
				u.setUserID(rs.getString("UserID"));
				u.setName(rs.getString("Name"));
				u.setEmail(rs.getString("Email"));
				u.setGender(rs.getString("Gender"));
				u.setHobby(rs.getString("Hobby"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("Role"));
				u.setUsername(rs.getString("Username"));
				data.add(u);
			}
			con.close();
		}catch(Exception e){}
		return data;
	}
	public static User getUser(String username){
		Connect con = new Connect();
		try{
			ResultSet rs = con.executeQuery("SELECT * FROM MsUser WHERE Username = '"+username+"'");
			if(rs.next()){
				User u = new User();
				u.setUserID(rs.getString("UserID"));
				u.setName(rs.getString("Name"));
				u.setEmail(rs.getString("Email"));
				u.setGender(rs.getString("Gender"));
				u.setHobby(rs.getString("Hobby"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("Role"));
				u.setUsername(rs.getString("Username"));
				return u;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
}
