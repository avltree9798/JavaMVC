package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import classes.Session;

import model.Console;
import model.DetailTransaction;
import model.HeaderTransaction;

public class DoTransaction extends JInternalFrame {
	private Console console;
	private HeaderTransaction ht;
	private DetailTransaction selectedDetail=null;
	private String defImage = System.getProperty("user.dir")+"\\images\\no_image_available.png";
	private MainForm mf;
	private JLabel labelutama = new JLabel("Do Transaction",JLabel.CENTER);
	final private JLabel ltid = new JLabel("Transaction ID = ");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date d = new Date();
	final private JLabel ltdt = new JLabel("Transaction Date = "+ sdf.format(d));
	final private JLabel luserid = new JLabel("User ID = "+Session.user.getUserID());
	final private JLabel lusername = new JLabel("Username = "+Session.user.getUsername());
	final private JLabel lname = new JLabel("Name = "+Session.user.getName());
	final private JLabel lconsoleid = new JLabel("Console ID");
	final private JLabel lconsolename = new JLabel("Console Name");
	final private JLabel lquantity = new JLabel("Quantity");
	final private JLabel lsubtotal = new JLabel("Sub Total");
	final private JLabel ltotal = new JLabel("Total ");
	private JButton badd = new JButton("Add");
	private JButton bsubmit = new JButton("Submit");
	private JButton bremove = new JButton("Remove");
	private JTextField id = new JTextField();
	private JTextField name = new JTextField();
	private JTextField subtotal = new JTextField();
	private ImageIcon img;
	private JLabel labelImage;
	private JSpinner quantity = new JSpinner();
	DefaultTableModel dtm = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	DefaultTableModel dtm2 = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}
	};
	private JTable utama = new JTable(dtm);
	private JTable kedua = new JTable(dtm2);
	private JPanel tableUtamaPane = new JPanel();
	private JPanel tableKeduaPane = new JPanel();
	private JScrollPane tableUtamaScroll = new JScrollPane(utama);
	private JScrollPane tableKeduaScroll = new JScrollPane(kedua);
	private JPanel panelkepala = new JPanel(new GridLayout(3, 1));
	private JPanel paneltiga = new JPanel(new GridLayout(1,3));
	private JPanel panelKosong = new JPanel();
	private JPanel panelkedua = new JPanel(new GridLayout(2,1));
	private JPanel panelketiga = new JPanel(new GridLayout(3,1));
	private JPanel panelTengah = new JPanel(new GridLayout(2,2));
	private JPanel form = new JPanel(new GridLayout(1,2));
	private JPanel gambar = new JPanel();
	private JPanel formIsi = new JPanel(new GridBagLayout());
	private JPanel panelBawah = new JPanel(new GridLayout(2,1));
	private JPanel panelButton = new JPanel();
	private JPanel panelTable = new JPanel(new BorderLayout());
	private JLabel label = new JLabel("Total : 0");
	public DoTransaction(MainForm mf){
		this.mf = mf;
		ht = new HeaderTransaction();
		ltid.setText("Transaction ID = "+ht.getTransactionID());
		initComp();
		addComp();
		addEvt();
		fillTable();
		initialState();
		setSize(700,700);
		mf.jdp.add(this);
		setClosable(true);
		subtotal.setEditable(false);
		setTitle("Do Transaction");
		initial();
		setVisible(true);
	}
	private void refreshTable() {
		// TODO Auto-generated method stub
		dtm2.setRowCount(0);
		ArrayList<DetailTransaction> dt = ht.fetchDetail();
		for(int i=0;i<dt.size();i++){
			DetailTransaction d = dt.get(i);
			Console c = d.getConsole();
			dtm2.addRow(new Object[]{c.getConsoleID(),c.getName(),d.getQty(),(d.getQty()*c.getPrice())});
		}
	}
	private void initialState() {
		// TODO Auto-generated method stub
		
	}
	String ids = null;
	private void addEvt() {
		// TODO Auto-generated method stub
		kedua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(kedua.getSelectedRow()>=0){
					selectedDetail = ht.fetchDetail().get(kedua.getSelectedRow());
				}
			}
		});
		bremove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selectedDetail==null){
					JOptionPane.showMessageDialog(null, "Select the console first","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					ht.removeDetail(selectedDetail);
					selectedDetail=null;
					refreshTable();
				}
			}
		});
		utama.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ids = utama.getValueAt(utama.getSelectedRow(), 0).toString();
				console = Console.findByID(ids);
				id.setText(String.valueOf(console.getConsoleID()));
				name.setText(console.getName());
				img = new ImageIcon(System.getProperty("user.dir")+"\\images\\"+console.getImages());
				Image images = img.getImage();
				Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				labelImage.setIcon(new ImageIcon(newimg));
				labelImage.setSize(120, 120);
				SpinnerModel sm = new SpinnerNumberModel(1,1,console.getStock(),1);
				quantity.setModel(sm);
				subtotal.setText(Integer.parseInt(quantity.getValue().toString())*console.getPrice()+"");
			}
		});
		badd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(console!=null){
					boolean ketemu = false;
					for(int i=0;i<ht.fetchDetail().size();i++){
						if(ht.fetchDetail().get(i).getConsole().getConsoleID()==console.getConsoleID()){
							ketemu = true;
							DetailTransaction dt = ht.fetchDetail().get(i);
							if(dt.getQty()+Integer.parseInt(quantity.getValue().toString())>console.getStock()){
								JOptionPane.showMessageDialog(null, "The stock is not enough","Error",JOptionPane.ERROR_MESSAGE);
							}else{
								dt.setQty(ht.fetchDetail().get(i).getQty()+Integer.parseInt(quantity.getValue().toString()));
							}
							break;
						}
					}
					if(!ketemu){
						DetailTransaction dt = new DetailTransaction(ht,console,Integer.parseInt(quantity.getValue().toString())); 
						ht.fetchDetail().add(dt);
					}
					System.out.println(ht.fetchDetail().size());
					refreshTable();
					console=null;
					label.setText("Total : Rp."+ht.getDetailTotalPrice());
					initial();
				}else{
					JOptionPane.showMessageDialog(null, "Select the console first","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		quantity.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				try{subtotal.setText(Integer.parseInt(quantity.getValue().toString())*console.getPrice()+"");}catch(Exception ee){quantity.setValue(0);}
			}
		});
		bsubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ht.fetchDetail().size()==0){
					JOptionPane.showMessageDialog(null, "Select at least 1 console to create new transaction","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					ht.save();
					ht=null;
					initial();
					ht = new HeaderTransaction();
					ltid.setText("Transaction ID = "+ht.getTransactionID());
					fillTable();
					refreshTable();
				}
			}
		});
	}
	private void fillTable() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) utama.getModel();
	    model.setRowCount(0);
		ArrayList<Console> ar = Console.fetchArray();
		System.out.println(ar.size());
		for(int i=0;i<ar.size();i++){
			dtm.addRow(new Object[]{ar.get(i).getConsoleID(),ar.get(i).getName(),ar.get(i).getPrice(),ar.get(i).getStock(),ar.get(i).getImages()});
		}
	}
	private void addComp() {
		// TODO Auto-generated method stub
		panelkepala.add(labelutama);
		panelkepala.add(paneltiga);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		dtm.addColumn("Console ID");
		dtm.addColumn("Name");
		dtm.addColumn("Price");
		dtm.addColumn("Stock");
		dtm.addColumn("Images");
		dtm2.addColumn("Console ID");
		dtm2.addColumn("Console Name");
		dtm2.addColumn("Total");
		dtm2.addColumn("Subtotal");
		paneltiga.add(panelKosong);
		labelImage = new JLabel();
		paneltiga.add(panelkedua);
		paneltiga.add(panelketiga);
		panelKosong.add(new JPanel());
		panelkedua.add(ltid);
		panelkedua.add(ltdt);
		panelketiga.add(luserid);
		panelketiga.add(lusername);
		panelketiga.add(lname);
		tableUtamaScroll.setPreferredSize(new Dimension(600,100));
		tableUtamaPane.add(tableUtamaScroll);
		tableKeduaScroll.setPreferredSize(new Dimension(600,100));
		tableKeduaPane.add(tableKeduaScroll);
		panelTengah.add(tableUtamaPane);
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx =0;
		gb.gridy =0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		formIsi.add(lconsoleid,gb);
		gb.gridx =1;
		gb.gridy =0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx=1.;
		formIsi.add(id,gb);
		gb.gridx =0;
		gb.gridy =1;
		gb.fill = GridBagConstraints.HORIZONTAL;
		formIsi.add(lconsolename,gb);
		gb.gridx =1;
		gb.gridy =1;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx=1.;
		formIsi.add(name,gb);
		gb.gridx =0;
		gb.gridy =2;
		gb.fill = GridBagConstraints.HORIZONTAL;
		formIsi.add(lquantity,gb);
		gb.gridx =1;
		gb.gridy =2;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx=1.;
		formIsi.add(quantity,gb);
		gb.gridx =0;
		gb.gridy =3;
		gb.fill = GridBagConstraints.HORIZONTAL;
		formIsi.add(lsubtotal,gb);
		gb.gridx =1;
		gb.gridy =3;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx=1.;
		formIsi.add(subtotal,gb);
		gb.gridx =1;
		gb.gridy =4;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx=1.;
		formIsi.add(badd,gb);
		img = new ImageIcon(defImage);
		Image images = img.getImage();
		Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		labelImage.setIcon(new ImageIcon(newimg));
		labelImage.setSize(50, 50);
		form.add(formIsi);
		form.add(labelImage);
		panelTengah.add(form);
		panelTable.add(tableKeduaPane,"North");
		panelTable.add(new JPanel().add(label),"South");
		panelBawah.add(panelTable);
		panelButton.add(bsubmit);
		panelButton.add(bremove);
		panelBawah.add(panelButton);
		add(panelkepala,BorderLayout.NORTH);
		add(panelTengah,BorderLayout.CENTER);
		add(panelBawah,BorderLayout.SOUTH);
	}
	private void initial(){
		img = new ImageIcon(defImage);
		Image images = img.getImage();
		Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		labelImage.setIcon(new ImageIcon(newimg));
		name.setText("");
		subtotal.setText("0");
		id.setText("");
		name.setEditable(false);
		id.setEditable(false);
		subtotal.setEditable(false);
		quantity.setValue(0);
	}
	private void initComp() {
		// TODO Auto-generated method stub
		labelutama.setFont(new Font("Serif",Font.BOLD,32));
	}
	
}
