package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.User;

public class RegisterForm extends JFrame{
	private LoginForm lf=null;
	private JPanel panelRegistration;
	private JPanel panelGender;
	private JPanel panelButton;
	private JLabel lregister;
	private JLabel lname;
	private JLabel lusername;
	private JLabel lpassword;
	private JLabel lgender;
	private JLabel lemail;
	private JLabel lhobby;
	private JLabel lrole;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;
	private JRadioButton male;
	private JRadioButton female;
	private ButtonGroup bg;
	private JTextField email;
	private JComboBox hobby;
	private JComboBox role;
	private JButton register;
	private JButton login;
	private JButton reset;
	private void initComp(){
		panelRegistration = new JPanel(new GridLayout(7,2));
		panelGender = new JPanel(new FlowLayout());
		panelButton = new JPanel(new FlowLayout());
		lregister = new JLabel("Register Form",JLabel.CENTER);
		lregister.setFont(new Font("Sherif",Font.BOLD,32));
		lname = new JLabel("Name");
		lusername = new JLabel("Username");
		lpassword = new JLabel("Password");
		lgender = new JLabel("Gender");
		lemail = new JLabel("Email");
		lhobby = new JLabel("Hobby");
		lrole = new JLabel("Role");
		name = new JTextField();
		username = new JTextField();
		password = new JPasswordField();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		bg = new ButtonGroup();
		email = new JTextField();
		hobby = new JComboBox();
		role = new JComboBox();
		register = new JButton("Register");
		login = new JButton("Login");
		reset = new JButton("Reset");
	}
	void addComp(){
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
		bg.add(male);
		bg.add(female);
		panelGender.add(male);
		panelGender.add(female);
		panelButton.add(register);
		panelButton.add(login);
		panelButton.add(reset);
		panelRegistration.add(lname);
		panelRegistration.add(name);
		panelRegistration.add(lusername);
		panelRegistration.add(username);
		panelRegistration.add(lpassword);
		panelRegistration.add(password);
		panelRegistration.add(lgender);
		panelRegistration.add(panelGender);
		panelRegistration.add(lemail);
		panelRegistration.add(email);
		panelRegistration.add(lhobby);
		panelRegistration.add(hobby);
		panelRegistration.add(lrole);
		panelRegistration.add(role);
		add(lregister,"North");
		add(panelRegistration,"Center");
		add(panelButton,"South");
	}
	private void initState(){
		hobby.setSelectedIndex(0);
		role.setSelectedIndex(0);
		name.setText("");
		username.setText("");
		password.setText("");
		bg.clearSelection();
		email.setText("");
		name.requestFocus();
	}
	private void addEvt(){
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				initState();
			}
		});
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
					User u = new User();
					u.setUsername(us);
					u.setEmail(em);
					u.setGender(gd);
					u.setRole(rl);
					u.setHobby(hb);
					u.setPassword(pw);
					u.setName(nm);
					u.save();
					JOptionPane.showMessageDialog(null, "Success Registered","Success",JOptionPane.INFORMATION_MESSAGE);
					initState();
				}
			}
		});
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lf.setVisible(true);
				dispose();
			}
		});
	}
	public RegisterForm(LoginForm lf) {
		// TODO Auto-generated constructor stub
		this.lf = lf;
		initComp();
		addComp();
		addEvt();
		pack();
		setTitle("Registration Form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		System.out.println("Haha");
	}
}
