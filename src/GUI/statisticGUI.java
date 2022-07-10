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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.Date;
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

import BLL.accountBLL;
import BLL.billDetailsBLL;
import BLL.enterCouponDetailsBLL;
import Bill.BillDetails;
import DAL.billDetailsDAL;
import Dungchung.Tester;
import Dungchung.statis;
import Account.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class statisticGUI extends JFrame {
	public static void main(String[] args) throws ParseException {
		new statisticGUI();
	}
	Date now = new Date();
	String formatSetMonth = new SimpleDateFormat("MM").format(now);
	String formatSetYear = new SimpleDateFormat("yyyy").format(now);
	String formatMonth = new SimpleDateFormat("MM/yyyy").format(now);
	String monthNow = formatMonth.formatted(now);
	String setMonthNow = formatSetMonth.formatted(now); 
	String setYearNow = formatSetYear.formatted(now);
	String monthSelect = setMonthNow;
	String yearSelect = setYearNow;

	List<statis> resultList = new ArrayList<statis>();
	List<statis> resultListByProduct = new ArrayList<statis>();
	billDetailsBLL bdBLL = new billDetailsBLL();
	enterCouponDetailsBLL ecdBLL = new enterCouponDetailsBLL();
	public JFrame f = new JFrame();
	private JPanel contentPane;
	String [] permissionList = {"Hãng",  "Sản phẩm"};
	String [] monthList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	String [] yearList = {"2019", "2020", "2021", "2022"};
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel modelProduct = new DefaultTableModel();
	private JTable table, tableProduct;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	
	
	public statisticGUI() throws ParseException {
		initComponent();
		displayList("Hãng", monthNow);
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Thống kê kinh doanh");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// panel hãng 
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 108, 399, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHngCDoanh = new JLabel("Hãng có doanh thu lớn nhất");
		lblHngCDoanh.setForeground(Color.BLACK);
		lblHngCDoanh.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHngCDoanh.setBounds(25, 287, 172, 28);
		panel.add(lblHngCDoanh);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(194, 289, 182, 28);
		panel.add(textField_1);
		
		JLabel lblHngCDoanh_1 = new JLabel("Hãng có doanh thu thấp nhất");
		lblHngCDoanh_1.setForeground(Color.BLACK);
		lblHngCDoanh_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHngCDoanh_1.setBounds(25, 338, 172, 28);
		panel.add(lblHngCDoanh_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(194, 340, 182, 28);
		panel.add(textField_2);
		
		JLabel lblTngTinBn = new JLabel("Tổng tiền bán hàng");
		lblTngTinBn.setForeground(Color.BLACK);
		lblTngTinBn.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTngTinBn.setBounds(25, 454, 150, 28);
		panel.add(lblTngTinBn);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(194, 456, 182, 28);
		panel.add(textField_9);
		
		JLabel lblTngTinNhp = new JLabel("Tổng tiền nhập hàng");
		lblTngTinNhp.setForeground(Color.BLACK);
		lblTngTinNhp.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTngTinNhp.setBounds(25, 396, 150, 28);
		panel.add(lblTngTinNhp);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(194, 398, 182, 28);
		panel.add(textField_10);
		
		JLabel lblHngCDoanh_2 = new JLabel("Tổng doanh thu");
		lblHngCDoanh_2.setForeground(Color.BLACK);
		lblHngCDoanh_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHngCDoanh_2.setBounds(25, 515, 172, 28);
		panel.add(lblHngCDoanh_2);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(194, 517, 182, 28);
		panel.add(textField_11);
		
		JLabel lblTopCcHng = new JLabel("Top các hãng bán chạy nhất");
		lblTopCcHng.setForeground(Color.BLACK);
		lblTopCcHng.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTopCcHng.setBounds(96, 22, 280, 28);
		panel.add(lblTopCcHng);
		
		JLabel lblTop_2 = new JLabel("Top 1");
		lblTop_2.setForeground(Color.BLACK);
		lblTop_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTop_2.setBounds(63, 60, 150, 28);
		panel.add(lblTop_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 60, 182, 28);
		panel.add(textField);
		
		JLabel lblMTiKhon_1_1_2 = new JLabel("Top 2");
		lblMTiKhon_1_1_2.setForeground(Color.BLACK);
		lblMTiKhon_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_1_2.setBounds(63, 108, 150, 28);
		panel.add(lblMTiKhon_1_1_2);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(140, 108, 182, 28);
		panel.add(textField_12);
		
		JLabel lblMTiKhon_1_2_2 = new JLabel("Top 3");
		lblMTiKhon_1_2_2.setForeground(Color.BLACK);
		lblMTiKhon_1_2_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_2_2.setBounds(63, 154, 150, 28);
		panel.add(lblMTiKhon_1_2_2);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(140, 154, 182, 28);
		panel.add(textField_13);
		
		JLabel lblSnPhmBn = new JLabel("Hãng bán chậm nhất");
		lblSnPhmBn.setForeground(Color.BLACK);
		lblSnPhmBn.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSnPhmBn.setBounds(25, 227, 172, 28);
		panel.add(lblSnPhmBn);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(194, 229, 182, 28);
		panel.add(textField_14);
		
		// sản phẩm
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 108, 399, 603);
		contentPane.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel_1.setBackground(new Color(204, 204, 204));
		
		JLabel lblTopCcSn = new JLabel("Top các sản phẩm bán chạy nhất");
		lblTopCcSn.setForeground(Color.BLACK);
		lblTopCcSn.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTopCcSn.setBounds(77, 40, 280, 28);
		panel_1.add(lblTopCcSn);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(147, 78, 182, 28);
		panel_1.add(textField_3);
		
		JLabel lblTop = new JLabel("Top 1");
		lblTop.setForeground(Color.BLACK);
		lblTop.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTop.setBounds(70, 78, 150, 28);
		panel_1.add(lblTop);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(147, 126, 182, 28);
		panel_1.add(textField_4);
		
		JLabel lblMTiKhon_1_1 = new JLabel("Top 2");
		lblMTiKhon_1_1.setForeground(Color.BLACK);
		lblMTiKhon_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_1.setBounds(70, 126, 150, 28);
		panel_1.add(lblMTiKhon_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(147, 172, 182, 28);
		panel_1.add(textField_5);
		
		JLabel lblMTiKhon_1_2 = new JLabel("Top 3");
		lblMTiKhon_1_2.setForeground(Color.BLACK);
		lblMTiKhon_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_2.setBounds(70, 172, 150, 28);
		panel_1.add(lblMTiKhon_1_2);
		
		JLabel lblTopCcSn_2 = new JLabel("Top các sản phẩm bán chậm nhất");
		lblTopCcSn_2.setForeground(Color.BLACK);
		lblTopCcSn_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTopCcSn_2.setBounds(77, 236, 280, 28);
		panel_1.add(lblTopCcSn_2);
		
		JLabel lblTop_1 = new JLabel("Top 1");
		lblTop_1.setForeground(Color.BLACK);
		lblTop_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTop_1.setBounds(70, 274, 150, 28);
		panel_1.add(lblTop_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(147, 274, 182, 28);
		panel_1.add(textField_6);
		
		JLabel lblMTiKhon_1_1_1 = new JLabel("Top 2");
		lblMTiKhon_1_1_1.setForeground(Color.BLACK);
		lblMTiKhon_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_1_1.setBounds(70, 322, 150, 28);
		panel_1.add(lblMTiKhon_1_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(147, 322, 182, 28);
		panel_1.add(textField_7);
		
		JLabel lblMTiKhon_1_2_1 = new JLabel("Top 3");
		lblMTiKhon_1_2_1.setForeground(Color.BLACK);
		lblMTiKhon_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon_1_2_1.setBounds(70, 368, 150, 28);
		panel_1.add(lblMTiKhon_1_2_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(147, 368, 182, 28);
		panel_1.add(textField_8);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 108, 963, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Tên hãng");
		model.addColumn("Số lượng bán");
		model.addColumn("Doanh thu");
		
		tableProduct = new JTable();
		tableProduct.setModel(modelProduct);
		modelProduct.addColumn("STT");
		modelProduct.addColumn("Tên sản phẩm");
		modelProduct.addColumn("Số lượng bán");
		
		scrollPane.setViewportView(table);
		
		//header
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1364, 111);
		contentPane.add(panel1);
		panel1.setLayout(null);
    	
		JLabel lbTitle = new JLabel("Thống Kê Kinh Doanh Tháng " + monthNow);
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(490, 10, 600, 57);
		panel1.add(lbTitle);
		JButton btnTrLi = new JButton("");
		btnTrLi.setBounds(10, 0, 50, 50);
		panel1.add(btnTrLi);
		btnTrLi.setBackground(new Color(50, 205, 50));
		btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));
		
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				if(Integer.parseInt(loginGUI.code)!=0) {
					employeeInterfaceGUI p = new employeeInterfaceGUI(loginGUI.code);
					p.f.setVisible(true);
				}
				else {
					adminGUI p = new adminGUI();
					p.f.setVisible(true);
				}
			}
		});
		
		JLabel lblHinThTi = new JLabel("Thống kê theo");
		lblHinThTi.setForeground(Color.WHITE);
		lblHinThTi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHinThTi.setBounds(43, 60, 195, 28);
		panel1.add(lblHinThTi);
		
		JComboBox cbPermission_1 = new JComboBox(permissionList);
		cbPermission_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbPermission_1.setBounds(170, 60, 153, 28);
		panel1.add(cbPermission_1);
		
		JComboBox cbMonth = new JComboBox(monthList);
		cbMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbMonth.setBounds(400, 60, 160, 28);
		cbMonth.setSelectedItem(setMonthNow);
		panel1.add(cbMonth);
		
		JComboBox cbYear = new JComboBox(yearList);
		cbYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbYear.setBounds(600, 60, 153, 28);
		cbYear.setSelectedItem(setYearNow);
		panel1.add(cbYear);
		
		cbPermission_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String type = cbPermission_1.getSelectedItem().toString();
				if(type.equals("Hãng")) {
					panel.setVisible(true);
					panel_1.setVisible(false);
					scrollPane.setViewportView(table);
				}
				else {
					panel_1.setVisible(true);
					panel.setVisible(false);
					scrollPane.setViewportView(tableProduct);
					try {
						displayList("", monthNow);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		cbMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				monthSelect = cbMonth.getSelectedItem().toString();
				lbTitle.setText("Thống Kê Kinh Doanh Tháng " + monthSelect + "/" + yearSelect);
				try {
					displayList(cbPermission_1.getSelectedItem().toString(), monthSelect + "/" + yearSelect);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		cbYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				yearSelect = cbYear.getSelectedItem().toString();
				lbTitle.setText("Thống Kê Kinh Doanh Tháng " + monthSelect + "/" + yearSelect);
				try {
					displayList(cbPermission_1.getSelectedItem().toString(), monthSelect + "/" + yearSelect);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setResizable(false);
		f.setVisible(true);
 	}	
	
	private void displayList(String action, String monthNow) throws ParseException {
		List<statis> listToTal = new ArrayList<statis>();
		List<statis> listToTalProduct = new ArrayList<statis>();
		
		if(action.equals("Hãng")) {
			model.setRowCount(0);
			resultList = bdBLL.statistic(monthNow);
	    	int k = 0; 
	    	long totalBillOfMonth  = 0, totalEnterOfMonth = 0;
	    	if(resultList.size() == 0) {
	    		JOptionPane.showMessageDialog(null, "Không có dữ liệu để thống kê trong khoảng thời gian này!!");
	    		return;
	    	}
			while(k < resultList.size()) {
				statis sta = new statis(resultList.get(k).getName(), resultList.get(k).getAmount(), resultList.get(k).getTotal());
				listToTal.add(sta);
				statis s = resultList.get(k);
				totalBillOfMonth += resultList.get(k).getTotal();
				model.addRow(new Object [] {
						model.getRowCount()+1, s.getName(), s.getAmount(), Tester.format(s.getTotal()) 
				});
				k++;
			}
			// sort amount
		    Collections.sort(listToTal, statis.amountComparator);
		    // top brand
	    	textField.setText(listToTal.get(0).getName());
	    	textField_12.setText(listToTal.get(1).getName());
	    	textField_13.setText(listToTal.get(2).getName());
	    	textField.setEditable(false);
	    	textField_12.setEditable(false);
	    	textField_13.setEditable(false);
	    	// worst product
	    	textField_14.setText(listToTal.get(listToTal.size() - 1).getName());
	     	textField_14.setEditable(false);
	     	// sort total
	     	Collections.sort(listToTal, statis.totalComparator);
	     	textField_1.setText(listToTal.get(0).getName());
	     	textField_2.setText(listToTal.get(listToTal.size() - 1).getName());
	     	textField_1.setEditable(false);
	     	textField_2.setEditable(false);
	     	// money of bill in a month
	     	textField_9.setText(Tester.format(totalBillOfMonth));
	     	textField_9.setEditable(false);
	     	// money of entercoupon in a month
	     	totalEnterOfMonth = ecdBLL.getTotal();
	     	textField_10.setText(Tester.format(totalEnterOfMonth));
	     	textField_10.setEditable(false);
	     	// doannh thu
	     	textField_11.setText(Tester.format(totalBillOfMonth - totalEnterOfMonth));
	     	textField_11.setEditable(false);
		}
		else {
			modelProduct.setRowCount(0);
			resultListByProduct = bdBLL.statisticByProduct(monthNow);
			int k = 0;
			if(resultListByProduct.size() == 0) {
	    		JOptionPane.showMessageDialog(null, "Không có dữ liệu để thống kê trong khoảng thời gian này!!");
	    		return;
	    	}
			while(k < resultListByProduct.size()) {
				statis sta = new statis(resultListByProduct.get(k).getName(), resultListByProduct.get(k).getAmount());
				listToTalProduct.add(sta);
				statis s = resultListByProduct.get(k);
				modelProduct.addRow(new Object [] {
						modelProduct.getRowCount()+1, s.getName(), s.getAmount() 
				});
				k++;
			}
			// sort amount
		    Collections.sort(listToTalProduct, statis.amountComparator);
		    // top product trend
		    textField_3.setText(listToTalProduct.get(0).getName());
		    textField_4.setText(listToTalProduct.get(1).getName());
		    textField_5.setText(listToTalProduct.get(2).getName());
		    
		    // top product not trend
		    textField_6.setText(listToTalProduct.get(listToTalProduct.size() - 3).getName());
		    textField_7.setText(listToTalProduct.get(listToTalProduct.size() - 2).getName());
		    textField_8.setText(listToTalProduct.get(listToTalProduct.size() - 1).getName());
		    
		    textField_8.setEditable(false);
		    textField_7.setEditable(false);
		    textField_6.setEditable(false);
		    textField_5.setEditable(false);
		    textField_4.setEditable(false);
		    textField_3.setEditable(false);
		}
	}
	
}