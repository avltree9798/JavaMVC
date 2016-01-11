package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.Session;

import model.User;

public class LoginForm extends JFrame{
	private JLabel GameStopsLabel;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JPanel panelLogin;
	private JPanel panelButton;
	private JButton login;
	private JButton reset;
	private JButton register;
	private JLabel uname;
	private JLabel passw;
	private JTextField username;
	private JPasswordField password;
	private JDesktopPane jdp;
	private RegisterForm rf;
	public LoginForm lf;
	private MainForm mf;
	private void initComp() {
		// TODO Auto-generated method stub
		GameStopsLabel = new JLabel("GameStops",JLabel.CENTER);
		GameStopsLabel.setFont(new Font("Serif",Font.BOLD,32));
		panelUsername = new JPanel(new BorderLayout());
		panelPassword = new JPanel(new BorderLayout());
		panelLogin = new JPanel(new FlowLayout());
		uname = new JLabel("Username");
		passw = new JLabel("Password");
		username = new JTextField();
		password = new JPasswordField();
		login = new JButton("Login");
		reset = new JButton("Reset");
		register = new JButton("Register");
		panelButton = new JPanel(new FlowLayout());
		this.setLayout(new GridLayout(3, 1));
	}
	private void addComp() {
		// TODO Auto-generated method stub
		panelUsername.add(uname,"North");
		panelUsername.add(username,"South");
		panelPassword.add(passw,"North");
		panelPassword.add(password,"South");
		panelLogin.add(panelUsername);
		panelLogin.add(panelPassword);
		panelButton.add(login);
		panelButton.add(reset);
		panelButton.add(register);
		add(GameStopsLabel);
		add(panelLogin);
		add(panelButton);
	}
	private void addEvt(){
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(rf==null){
					rf = new RegisterForm(lf);
					dispose();
				}else{
					rf.setVisible(true);
					dispose();
				}
			}
		});
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				username.setText("");
				password.setText("");
				username.requestFocus();
			}
		});
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(username.getText() == null || username.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Username must be filled!","Error",JOptionPane.ERROR_MESSAGE);
					username.requestFocus();
				}else if(password.getText() == null || password.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Password must be filled!","Error",JOptionPane.ERROR_MESSAGE);
					password.requestFocus();
				}else{
					if(User.getUser(username.getText(), password.getText())!=null){
						JOptionPane.showMessageDialog(null, "Login Success","Success",JOptionPane.INFORMATION_MESSAGE);
						Session.user = User.getUser(username.getText(), password.getText());
						new MainForm();
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Invalid username or password","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	public LoginForm() {
		// TODO Auto-generated constructor stub
		lf = this;
		initComp();
		addComp();
		addEvt();
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Login Form");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
