package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import classes.Session;

public class MainForm extends JFrame{
	public JDesktopPane jdp;
	private JMenuBar jmb;
	private JMenu file;
	private JMenu master;
	private JMenu transaction;
	private JMenuItem logout;
	private JMenuItem exit;
	private JMenuItem masterconsole;
	private JMenuItem masteruser;
	private JMenuItem dotransaction;
	private JMenuItem viewtransaction;
	private MainForm mf;
	private MasterConsole mc;
	private MasterUser mu;
	private DoTransaction dt;
	private ViewTransaction vt;
	public void initMenu(){
		if(Session.user.getRole().equals("User")){
			master.setVisible(false);
			viewtransaction.setVisible(false);//tanya dulu ini nanti
		}
	}
	public MainForm() {
		// TODO Auto-generated constructor stub
		initComp();
		addComp();
		addEvt();
		initMenu();
		setSize(1024,720);
		setTitle("GameStops");
		mf = this;
		jdp.setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private void initComp() {
		// TODO Auto-generated method stub
		jdp = new JDesktopPane();
		jmb = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		master = new JMenu("Master");
		master.setMnemonic(KeyEvent.VK_M);
		transaction = new JMenu("Transaction");
		transaction.setMnemonic(KeyEvent.VK_T);
		logout = new JMenuItem("Logout");
		logout.setMnemonic(KeyEvent.VK_O);
		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E);
		masterconsole = new JMenuItem("Master Console");
		masterconsole.setMnemonic(KeyEvent.VK_C);
		masteruser = new JMenuItem("Master User");
		masteruser.setMnemonic(KeyEvent.VK_U);
		dotransaction = new JMenuItem("Do Transaction");
		dotransaction.setMnemonic(KeyEvent.VK_D);
		viewtransaction = new JMenuItem("View Transaction");
		viewtransaction.setMnemonic(KeyEvent.VK_V);
		
	}
	private void addComp(){
		jmb.add(file);
		jmb.add(master);
		jmb.add(transaction);
		file.add(logout);
		file.add(exit);
		master.add(masterconsole);
		master.add(masteruser);
		transaction.add(dotransaction);
		transaction.add(viewtransaction);
		setJMenuBar(jmb);
		add(jdp, BorderLayout.CENTER);
	}
	private void addEvt(){
		viewtransaction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vt==null||vt.isClosed()){
					vt = new ViewTransaction(mf);
				}
			}
		});
		dotransaction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(dt==null || dt.isClosed()){
					dt = new DoTransaction(mf);
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		masteruser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mu==null || mu.isClosed())
					mu = new MasterUser(mf);
			}
		});
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new LoginForm();
				Session.user = null;
				dispose();
			}
		});
		masterconsole.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mc==null || mc.isClosed())
					mc = new MasterConsole(mf);
			}
		});
	}
}
