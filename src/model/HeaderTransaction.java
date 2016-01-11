package model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.Connect;
import classes.Session;

public class HeaderTransaction extends BaseModel {
	private int TransactionID;
	private User user;
	private String date;

	private ArrayList<DetailTransaction> detail = null;

	public HeaderTransaction() {
		try {
			Connect con = new Connect();
			ResultSet rs = con.executeQuery("SELECT * FROM HeaderTransaction");
			if (rs.last()) {
				Integer id = rs.getInt("TransactionID");
				id++;
				setTransactionID(id);
			} else {
				setTransactionID(1);
			}
		} catch (Exception e) {
		}
		user = Session.user;
		fetchDetail();
	}

	public Integer getDetailTotalPrice() {
		Integer sum = 0;
		for (int i = 0; i < detail.size(); i++) {
			DetailTransaction det = detail.get(i);
			sum += det.getQty() * det.getConsole().getPrice();
		}
		return sum;
	}

	public boolean removeDetail(DetailTransaction det) {
		int sizeLama = detail.size();
		for (int i = 0; i < detail.size(); i++) {
			if (det.getHeader().equals(this)
					&& det.getConsole().getConsoleID() == detail.get(i)
							.getConsole().getConsoleID()) {
				detail.remove(i);
				break;
			}
		}
		return sizeLama != detail.size();
	}

	public ArrayList<DetailTransaction> fetchDetail() {
		if (detail == null)
			detail = DetailTransaction.fetchDetail(this);
		return detail;
	}
	public ArrayList<DetailTransaction> ambilDetail() {
		return DetailTransaction.fetchDetail(this);
	}
	public static ArrayList<HeaderTransaction> fetchAll(){
		ArrayList<HeaderTransaction> ar = new ArrayList<HeaderTransaction>();
		try{
			Connect con = new Connect();
			ResultSet rs= con.executeQuery("SELECT * FROM HeaderTransaction");
			while(rs.next()){
				HeaderTransaction ht = new HeaderTransaction();
				ht.setUser(User.getUserByID(rs.getInt("UserID")));
				ht.setDate(rs.getString("Date"));
				ht.setTransactionID(rs.getInt("TransactionID"));
				ht.fetchDetail();
				ar.add(ht);
			}
		}catch(Exception e){}
		return ar;
	}
	public int getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Masuk sini");
			Connect con = new Connect();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date d = new Date();
			con.executeUpdate("INSERT INTO HeaderTransaction VALUES("+getTransactionID()+","+getUser().getUserID()+",'"+sdf.format(new Date())+"')");
			for(int i=0;i<detail.size();i++){
				detail.get(i).save();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
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
