package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import BLL.CustomerBLL;
import BLL.productBLL;
import Bill.BillDetails;
import Dungchung.Tester;
import Product.*;
import userService.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class orderGUI extends JFrame {

	List<BillDetails> billDetailsList = new ArrayList<BillDetails>();
	CustomerBLL cusBLL = new CustomerBLL();
	
	List<Product> productList = new ArrayList<Product>();
	productBLL proBLL = new productBLL();
	
	JLabel lblNewLabel;
	int totalPrice = 0;
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	public orderGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Đơn hàng của tôi");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		f.setResizable(false);


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 108, 1360, 553);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Số Lượng");
		model.addColumn("Giá");
		model.addColumn("Thành tiền");
		model.addColumn("Tình trạng");

		scrollPane.setViewportView(table);
		
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1364, 111);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Đơn Hàng Của Tôi");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 280, 36);
		panel1.add(lbTitle);
//		
//		JButton btnSearch = new JButton("Tìm kiếm");
//		btnSearch.setBounds(1252, 12, 90, 28);
//		panel1.add(btnSearch);
//		
//		tfFind = new JTextField();
//		tfFind.setBounds(1010, 12, 243, 28);
//		panel1.add(tfFind);
//		tfFind.setColumns(10);
		
		JButton btnTrLi = new JButton("");
		btnTrLi.setBounds(0, 0, 50, 50);
		panel1.add(btnTrLi);
		btnTrLi.setBackground(new Color(50, 205, 50));
		btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 661, 977, 42);
		contentPane.add(lblNewLabel);
		
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				indexUserGUI p = new indexUserGUI(loginGUI.code);
				p.f.setVisible(true);
			}
		});
		
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnSearchActionPerformed(e);
//			}
//		});
		
//		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo tên sản phẩm)");
//		lblNewLabel.setForeground(Color.WHITE);
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblNewLabel.setBounds(1013, 60, 309, 29);
//		panel1.add(lblNewLabel);
		
		f.add(contentPane);
		f.setSize(1366, 740);
	}
	
//	private void btnSearchActionPerformed(ActionEvent e) {
//		String productName = tfFind.getText();
//        if(productName != null && productName.length() > 0) {
//        	billDetailsList = cusBLL.searchBillByProductName(productName, indexUserGUI.userCode);
//            
//            if(billDetailsList.size()==0) {
//            	JOptionPane.showMessageDialog(null, "Không có sản phẩm bạn cần tìm");
//            	displayList();
//            }
//            
//            else {
//	            model.setRowCount(0);
//	            int i = 0;
//	    		while(i < billDetailsList.size()) {
//	    			String status = "";
//	    			BillDetails p = billDetailsList.get(i);
//	    			if(p.getConfirm() == 0) {
//	    				status = "Đang chờ xác nhận";
//	    			}
//	    			else {
//	    				status = "Đã xác nhận";
//	    			}
//	    			model.addRow(new Object [] {
//	    					model.getRowCount()+1, p.getProductCode(), proBLL.getProduct(p.getProductCode()), p.getAmount(), Tester.format(p.getPrice()), Tester.format(p.getTotal()), status
//	    			});
//	    			i++;
//	    		}
//            }
//            
//        } else {
//        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của sản phẩm để tìm kiếm");
//        }
//	}
	
	private void displayList() {
		model.setRowCount(0);
		billDetailsList = cusBLL.getAllBill(indexUserGUI.userCode);
    	int i = 0;
		while(i < billDetailsList.size()) {
			String status = "";
			BillDetails p = billDetailsList.get(i);
			if(p.getConfirm() == 0) {
				status = "Đang chờ xác nhận";
			}
			else {
				status = "Đã xác nhận";
			}
			// select tên sản phẩm theo mã sản phẩm
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getProductCode(), proBLL.getProduct(p.getProductCode()), p.getAmount(), Tester.format(p.getPrice()), Tester.format(p.getTotal()), status
			});
			totalPrice = totalPrice + p.getTotal();
			i++;
		}
		lblNewLabel.setText("Tổng tiền: " + Tester.format(totalPrice));
	}
}