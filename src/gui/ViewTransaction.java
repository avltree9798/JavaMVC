package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import model.Console;
import model.DetailTransaction;
import model.HeaderTransaction;


public class ViewTransaction extends JInternalFrame{
	private MainForm mf;
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
	private JPanel pane = new JPanel(new GridLayout(2,1));
	private ArrayList<HeaderTransaction> htall;
	public ViewTransaction(MainForm mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
		mf.jdp.add(this);
		addComp();
		fetchData();
		addEvt();
		setSize(500,500);
		setClosable(true);
		setTitle("View Transaction");
		setVisible(true);
	}
	private void addEvt() {
		// TODO Auto-generated method stub
		utama.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				HeaderTransaction ht = htall.get(utama.getSelectedRow());
				ArrayList<DetailTransaction> dt = ht.ambilDetail();
				System.out.println("lala "+dt.size());
				for(int i=0;i<dt.size();i++){
					dtm2.setRowCount(0);
					System.out.println("haha");
					for(DetailTransaction d : dt){
						System.out.println("test");
						dtm2.addRow(new Object[]{d.getHeader().getTransactionID(),d.getConsole().getConsoleID(),d.getQty()});
					}
				}
			}
		});
	}
	private void fetchData(){
		dtm.setRowCount(0);
		htall = HeaderTransaction.fetchAll();
		for(HeaderTransaction ht : htall){
			dtm.addRow(new Object[]{ht.getTransactionID(),ht.getUser().getUserID(),ht.getDate()});
		}
	}
	private void addComp(){
		tableUtamaScroll.setPreferredSize(new Dimension(400,100));
		tableUtamaPane.add(tableUtamaScroll);
		tableKeduaScroll.setPreferredSize(new Dimension(400,100));
		tableKeduaPane.add(tableKeduaScroll);
		dtm.addColumn("Transaction ID");
		dtm.addColumn("UserID");
		dtm.addColumn("Date");
		dtm2.addColumn("Transaction ID");
		dtm2.addColumn("ConsoleID");
		dtm2.addColumn("Quantity");
		pane.add(tableUtamaPane);
		pane.add(tableKeduaPane);
		add(pane,"North");
	}
}
