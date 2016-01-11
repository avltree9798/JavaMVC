package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import classes.Connect;

import model.Console;

public class MasterConsole extends JInternalFrame{
	private String defImage = System.getProperty("user.dir")+"\\images\\no_image_available.png";
	private Console console;
	private MainForm mf;
	private JPanel panelAtas;
	private JPanel panelTengah;
	private JPanel panelBawah;
	private JPanel panelForm;
	private JLabel lcid;
	private JLabel lname;
	private JLabel lprice;
	private JLabel lstock;
	private JLabel limage;
	private JTextField cid;
	private JTextField name;
	private JTextField price;
	private JTextField image;
	private JPanel southSide;
	private JSpinner stock;
	private DefaultTableModel dtm;
	private JTable table;
	private JPanel tablePane;
	private JLabel mclabel;
	private JScrollPane jsp;
	private JLabel labelImage;
	private ImageIcon img;
	private JButton insert;
	private JButton update;
	private JButton delete;
	private JButton save;
	private JButton cancel;
	private JButton browse;
	private String fileName;
	private String location;
	private int flag = -1;
	void initComp(){
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		save.setEnabled(false);
		cancel.setEnabled(false);
		dtm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		dtm.addColumn("Console ID");
		dtm.addColumn("Name");
		dtm.addColumn("Price");
		dtm.addColumn("Stock");
		dtm.addColumn("Images");
		table = new JTable(dtm);
		tablePane = new JPanel();
		panelAtas = new JPanel(new GridLayout(2,1));
		panelTengah = new JPanel(new GridLayout(1,2));
		panelBawah = new JPanel(new FlowLayout());
		panelForm = new JPanel(new GridLayout(6,2));
		southSide = new JPanel(new GridLayout(2,1));
		labelImage = new JLabel();
		img = new ImageIcon(defImage);
		Image images = img.getImage();
		Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		labelImage.setIcon(new ImageIcon(newimg));
		labelImage.setSize(50, 50);
		jsp = new JScrollPane(table);
		lcid = new JLabel("Console ID");
		lname = new JLabel("Name");
		lprice = new JLabel("Price");
		lstock = new JLabel("Stock");
		limage = new JLabel("Image");
		stock = new JSpinner();
		cid = new JTextField();
		name = new JTextField();
		price = new JTextField();
		image = new JTextField();
		browse = new JButton("Browse");
		mclabel = new JLabel("Master Console",JLabel.CENTER);
		mclabel.setFont(new Font("Serif",Font.BOLD,32));
		jsp.setPreferredSize(new Dimension(500,100));
	}
	void refreshTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
		ArrayList<Console> ar = Console.fetchArray();
		System.out.println(ar.size());
		for(int i=0;i<ar.size();i++){
			dtm.addRow(new Object[]{ar.get(i).getConsoleID(),ar.get(i).getName(),ar.get(i).getPrice(),ar.get(i).getStock(),ar.get(i).getImages()});
		}
	}
	private void initialState() {
		// TODO Auto-generated method stub
		location = null;
		fileName = null;
		refreshTable();
		cid.setText("");
		name.setText("");
		price.setText("");
		stock.setValue(0);
		image.setText("");
		cid.setEnabled(false);
		name.setEnabled(false);
		price.setEnabled(false);
		stock.setEnabled(false);
		image.setEnabled(false);
		img = new ImageIcon(defImage);
		Image images = img.getImage();
		Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		labelImage.setIcon(new ImageIcon(newimg));
		labelImage.setSize(120, 120);
		toogle(false);
	}
	private void toogle(boolean status) {
		// TODO Auto-generated method stub
		insert.setEnabled(!status);
		update.setEnabled(!status);
		delete.setEnabled(!status);
		browse.setEnabled(status);
		save.setEnabled(status);
		cancel.setEnabled(status);
		cid.setEnabled(false);
		name.setEnabled(status);
		price.setEnabled(status);
		stock.setEnabled(status);
		image.setEnabled(false);
	}
	private void copyFile(File source, File dest){
		// TODO Auto-generated method stub
		InputStream inStream = null;
		OutputStream outStream = null;
		try{
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = inStream.read(buffer)) > 0){
	    		outStream.write(buffer, 0, length);
    	    }
	    	inStream.close();
	    	outStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	private void addEvt() {
		// TODO Auto-generated method stub
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(console==null){
					JOptionPane.showMessageDialog(null, "You must select the data first","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					int res = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete Confirmation",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION){
						console.delete();
						initialState();
						console=null;
					}
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				initialState();
				console = null;
			}
		});
		browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
				int res = fc.showOpenDialog(null);
				if(res==JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					String ext = file.getName().substring(file.getName().length()-4);
					System.out.println(ext);
					if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".png")){
						location = file.getAbsolutePath();
						fileName = file.getName();
						image.setText(fileName);
						img = new ImageIcon(location);
						Image images = img.getImage();
						Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
						labelImage.setIcon(new ImageIcon(newimg));
						labelImage.setSize(120, 120);
					}else{
						JOptionPane.showMessageDialog(null, "File Extension must be JPG or PNG","Error",JOptionPane.ERROR_MESSAGE);
						location = null;
						fileName = null;
					}
				}
			}
		});
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				flag = 1;
				Integer id;
				try{
					id  = Console.getLast().getConsoleID();
				}catch(Exception e){
					id = 0;
				}
				id++;
				cid.setText(id.toString());
				toogle(true);
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(cid.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Console ID cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(name.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Name cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(name.getText().length()<=5){
					JOptionPane.showMessageDialog(null, "Name length must be more than 5 characters","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(image.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Console Image cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(price.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Price cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(((Integer)stock.getValue())<=0){
					JOptionPane.showMessageDialog(null, "Price cannot be lower or equals 0","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(flag==1){
					File source = new File(location);
					File dest = new File(System.getProperty("user.dir")+"\\images\\"+fileName);
					console = new Console();
					console.setConsoleID(Integer.parseInt(cid.getText()));
					console.setName(name.getText());
					console.setImages(image.getText());
					console.setPrice(Integer.parseInt(price.getText()));
					console.setStock((Integer)stock.getValue());
					console.save();
					copyFile(source, dest);
					refreshTable();
					console = null;
				}else{
					String ID = cid.getText();
					String nama = name.getText();
					Integer harga;
					Integer stok;
					try{
						harga = Integer.parseInt(price.getText());
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Price must be a numeric","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						stok = Integer.parseInt(stock.getValue().toString());
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Stock must be a numeric","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					String gambar = image.getText();
					console.setName(nama);
					console.setPrice(harga);
					console.setStock(stok);
					console.setImages(gambar);
					console.update();
					console = null;
				}
				flag = -1;
				Integer id;
				try{
					id  = Console.getLast().getConsoleID();
				}catch(Exception e){
					id = 0;
				}
				id++;
				initialState();
				cid.setText(id.toString());
			}
		});
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(console==null){
					JOptionPane.showMessageDialog(null, "You must select the data first","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					toogle(true);
					flag = 0;
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				String ID = table.getValueAt(table.getSelectedRow(), 0).toString();
				console = Console.findByID(ID);
				cid.setText(String.valueOf(console.getConsoleID()));
				name.setText(console.getName());
				price.setText(String.valueOf(console.getPrice()));
				stock.setValue(console.getStock());
				image.setText(console.getImages());
				img = new ImageIcon(System.getProperty("user.dir")+"\\images\\"+console.getImages());
				Image images = img.getImage();
				Image newimg = images.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				labelImage.setIcon(new ImageIcon(newimg));
				labelImage.setSize(120, 120);
			}
		});
	}
	private void addComp() {
		// TODO Auto-generated method stub
		// (Vertical atas)
		panelAtas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		southSide.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));
		panelForm.setBorder(BorderFactory.createEmptyBorder(0,170,0,0));
		tablePane.add(jsp);
		panelAtas.add(mclabel);
		panelAtas.add(tablePane);
		panelForm.add(lcid);
		panelForm.add(cid);
		panelForm.add(lname);
		panelForm.add(name);
		panelForm.add(lprice);
		panelForm.add(price);
		panelForm.add(lstock);
		panelForm.add(stock);
		panelForm.add(limage);
		panelForm.add(image);
		panelForm.add(new JPanel());
		panelForm.add(browse);
		panelTengah.add(panelForm);
		panelTengah.add(labelImage);
		panelBawah.add(insert);
		panelBawah.add(update);
		panelBawah.add(delete);
		panelBawah.add(save);
		panelBawah.add(cancel);
		southSide.add(panelTengah);
		southSide.add(panelBawah);
		add(panelAtas,"North");
		add(southSide,"South");
	}
	public MasterConsole(MainForm mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
		initComp();
		addComp();
		addEvt();
		initialState();
		setSize(700,600);
		mf.jdp.add(this);
		setClosable(true);
		setTitle("Master Console");
		setVisible(true);
	}
}
