package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import model.Console;
import model.User;

public class MasterUser extends JInternalFrame{
	private JPanel panelButton;
	private MainForm mf;
	private JButton insert;
	private JButton update;
	private JButton delete;
	private JButton save;
	private JButton cancel;
	private JButton browse;
	private JLabel lid;
	private JLabel lname;
	private JLabel lusername;
	private JLabel lpassword;
	private JLabel lgender;
	private JLabel lemail;
	private JLabel lhobby;
	private JLabel lrole;
	private JTextField id;
	private JTextField name;
	private JTextField username;
	private JTextField email;
	private JPasswordField password;
	private ButtonGroup bg;
	private JRadioButton male;
	private JRadioButton female;
	private JComboBox hobby;
	private JComboBox role;
	private JPanel panelForm;
	private JPanel panelGender;
	private DefaultTableModel dtm;
	private JTable table;
	private JPanel tablePane;
	private JPanel panelTengah;
	private JLabel mclabel;
	private JPanel panelAtas;
	private JScrollPane jsp;
	private User u=null;
	private int flag=-1;
	public MasterUser(MainForm mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
		initComp();
		addComp();
		addEvt();
		initialState();
		setSize(700,600);
		mf.jdp.add(this);
		setClosable(true);
		refreshTable();
		setTitle("Master User");
		setVisible(true);
	}
	private void addEvt() {
		// TODO Auto-generated method stub
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				flag = 1;
				Integer sid;
				try{
					sid  = User.getLast().getUserID();
				}catch(Exception e){
					sid = 0;
				}
				sid++;
				id.setText(sid.toString());
				toogle(true);
			}
		});
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				flag = 2;
				toogle(true);
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				u = null;
				initialState();
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String us = username.getText();
				String nm = name.getText();
				String pw = password.getText();
				String gd;
				if(bg.getSelection()==null){
					gd="NULL";
				}else{
					gd = (female.isSelected())?"Female":"Male";
				}
				String em = email.getText();
				String hb  = hobby.getSelectedItem().toString();
				String rl = role.getSelectedItem().toString();
				boolean usernameIsAlphabetic=true;
				boolean nameIsAlphabetic=true;
				for(int i=0;i<nm.length();i++){
					if(Character.isDigit(nm.charAt(i))){
						nameIsAlphabetic = false;
						break;
					}
				}
				for(int i=0;i<us.length();i++){
					if(Character.isDigit(us.charAt(i))){
						usernameIsAlphabetic = false;
						break;
					}
				}
				if(nm.equals("")){
					JOptionPane.showMessageDialog(null, "Name must be filled","Error",JOptionPane.ERROR_MESSAGE);
				}else if(nm.length()<=5){
					JOptionPane.showMessageDialog(null, "Name length must be more than 5 characters","Error",JOptionPane.ERROR_MESSAGE);
				}else if(!nameIsAlphabetic){
					JOptionPane.showMessageDialog(null, "Name must be alphabetic","Error",JOptionPane.ERROR_MESSAGE);
				}else if(us.equals("")){
					JOptionPane.showMessageDialog(null, "Username must be filled","Error",JOptionPane.ERROR_MESSAGE);
				}else if(us.length()<=5){
					JOptionPane.showMessageDialog(null, "Username length must be more than 5 characters","Error",JOptionPane.ERROR_MESSAGE);
				}else if(!usernameIsAlphabetic){
					JOptionPane.showMessageDialog(null, "Username must be alphabetic","Error",JOptionPane.ERROR_MESSAGE);
				}else if(User.getUser(us)!=null){
					JOptionPane.showMessageDialog(null, "Username already registered","Error",JOptionPane.ERROR_MESSAGE);
				}else if(pw.equals("")){
					JOptionPane.showMessageDialog(null, "Password must be filled","Error",JOptionPane.ERROR_MESSAGE);
				}else if(pw.length()<=5){
					JOptionPane.showMessageDialog(null, "Password length must be more than 5 characters","Error",JOptionPane.ERROR_MESSAGE);
				}else if(em.equals("")){
					JOptionPane.showMessageDialog(null, "Email must be filled","Error",JOptionPane.ERROR_MESSAGE);
				}else if(!em.contains("@")){
					JOptionPane.showMessageDialog(null, "Email must contains @ symbol","Error",JOptionPane.ERROR_MESSAGE);
				}else if(!em.contains(".")){
					JOptionPane.showMessageDialog(null, "Email must contains . symbol","Error",JOptionPane.ERROR_MESSAGE);
				}else if(gd.equals("NULL")){
					JOptionPane.showMessageDialog(null, "Gender must be choosen","Error",JOptionPane.ERROR_MESSAGE);
				}else if(hb.equals("--Select--")){
					JOptionPane.showMessageDialog(null, "Hobby must be choosen","Error",JOptionPane.ERROR_MESSAGE);
				}else if(rl.equals("--Select--")){
					JOptionPane.showMessageDialog(null, "Role must be choosen","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					if(flag==1){
						User uss = new User();
						uss.setUsername(us);
						uss.setEmail(em);
						uss.setGender(gd);
						uss.setRole(rl);
						uss.setHobby(hb);
						uss.setPassword(pw);
						uss.setName(nm);
						uss.save();
						JOptionPane.showMessageDialog(null, "Success Added","Success",JOptionPane.INFORMATION_MESSAGE);
					}else{
						u.setUsername(us);
						u.setEmail(em);
						u.setGender(gd);
						u.setRole(rl);
						u.setHobby(hb);
						u.setPassword(pw);
						u.setName(nm);
						u.update();
						JOptionPane.showMessageDialog(null, "Success Edited","Success",JOptionPane.INFORMATION_MESSAGE);
					}
					flag=-1;
					Integer sid;
					try{
						sid  = User.getLast().getUserID();
					}catch(Exception e){
						sid = 0;
					}
					sid++;
					id.setText(sid.toString());
					toogle(false);
					initialState();
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(u==null){
					JOptionPane.showMessageDialog(null, "You must select the data first","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					int res = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete Confirmation",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION){
						u.delete();
						initialState();
						u= null;
					}
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				u = User.getUser(table.getValueAt(table.getSelectedRow(),2).toString());
				id.setText(String.valueOf(u.getUserID()));
				name.setText(u.getName());
				username.setText(u.getUsername());
				email.setText(u.getEmail());
				password.setText(u.getPassword());
				male.setSelected(u.getGender().equalsIgnoreCase("male"));
				female.setSelected(u.getGender().equalsIgnoreCase("female"));
				ComboBoxModel cbm = hobby.getModel();
				int index = -1;
				for(int i=0;i<cbm.getSize();i++){
					if(cbm.getElementAt(i).equals(u.getHobby())){
						index = i;
						break;
					}
				}
				hobby.setSelectedIndex(index);
				index=-1;
				cbm = role.getModel();
				for(int i=0;i<cbm.getSize();i++){
					if(cbm.getElementAt(i).equals(u.getRole())){
						index = i;
						break;
					}
				}
				role.setSelectedIndex(index);
			}
		});
	}
	private void toogle(boolean status){
		id.setEnabled(false);
		name.setEnabled(status);
		username.setEnabled(status);
		email.setEnabled(status);
		password.setEnabled(status);
		male.setEnabled(status);
		female.setEnabled(status);
		hobby.setEnabled(status);
		role.setEnabled(status);
		save.setEnabled(status);
		cancel.setEnabled(status);
		insert.setEnabled(!status);
		update.setEnabled(!status);
		delete.setEnabled(!status);
	}
	private void initialState() {
		// TODO Auto-generated method stub
		refreshTable();
		u = User.getUser("");
		id.setText("");
		name.setText("");
		username.setText("");
		email.setText("");
		password.setText("");
		male.setSelected(false);
		female.setSelected(false);
		hobby.setSelectedIndex(0);
		role.setSelectedIndex(0);
		toogle(false);
	}
	public void refreshTable(){
		ArrayList<User> au = User.fetchArray();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	    for(int i=0;i<au.size();i++){
	    	dtm.addRow(new Object[]{au.get(i).getUserID(),
	    	au.get(i).getName(),
			au.get(i).getUsername(),
			au.get(i).getPassword(),
			au.get(i).getEmail(),
			au.get(i).getGender(),
			au.get(i).getHobby(),
			au.get(i).getRole()});
	    }
	}
	private void addComp() {
		// TODO Auto-generated method stub
		bg.add(male);
		bg.add(female);
		panelButton.add(insert);
		panelButton.add(update);
		panelButton.add(delete);
		panelButton.add(save);
		panelButton.add(cancel);
		panelForm.add(lid);
		panelForm.add(id);
		panelForm.add(lname);
		panelForm.add(name);
		panelForm.add(lusername);
		panelForm.add(username);
		panelForm.add(lpassword);
		panelForm.add(password);
		panelForm.add(lgender);
		panelGender.add(male);
		panelGender.add(female);
		panelForm.add(panelGender);
		panelForm.add(lemail);
		panelForm.add(email);
		panelForm.add(lhobby);
		panelForm.add(hobby);
		panelForm.add(lrole);
		panelForm.add(role);
		panelTengah.add(panelForm);
		panelTengah.add(panelButton);
		tablePane.add(jsp);
		panelAtas.add(mclabel);
		panelAtas.add(tablePane);
		panelAtas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panelTengah.setBorder(BorderFactory.createEmptyBorder(70,0,-200,0));
		add(panelAtas,"North");
		add(panelTengah,"South");
		hobby.addItem("--Select--");
		hobby.addItem("Coding");
		hobby.addItem("Swimming");
		hobby.addItem("Racing");
		hobby.addItem("Playing Football");
		hobby.addItem("Playing Basketball");
		hobby.addItem("Painting");
		hobby.addItem("Drawing");
		role.addItem("--Select--");
		role.addItem("Admin");
		role.addItem("User");
	}
	private void initComp() {
		// TODO Auto-generated method stub
		panelButton = new JPanel();
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		save.setEnabled(false);
		cancel.setEnabled(false);
		lid = new JLabel("User ID");
		lname = new JLabel("Name");
		lusername = new JLabel("Username");
		lpassword = new JLabel("Password");
		lgender = new JLabel("Gender");
		lemail = new JLabel("Email");
		lhobby = new JLabel("Hobby");
		lrole = new JLabel("Role");
		id = new JTextField();
		name = new JTextField();
		username = new JTextField();
		email = new JTextField();
		password = new JPasswordField();
		bg = new ButtonGroup();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		hobby = new JComboBox();
		role = new JComboBox();
		panelForm = new JPanel(new GridLayout(8,2));
		panelGender = new JPanel();
		dtm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dtm.addColumn("User ID");
		dtm.addColumn("Name");
		dtm.addColumn("Username");
		dtm.addColumn("Password");
		dtm.addColumn("Email");
		dtm.addColumn("Gender");
		dtm.addColumn("Hobby");
		dtm.addColumn("Role");
		table = new JTable(dtm);
		tablePane = new JPanel();
		panelTengah = new JPanel(new GridLayout(2,1));
		mclabel = new JLabel("Master User",JLabel.CENTER);
		mclabel.setFont(new Font("Serif",Font.BOLD,32));
		panelAtas = new JPanel(new GridLayout(2,1));
		jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(650,100));
	}
}
