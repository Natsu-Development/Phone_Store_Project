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
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import BLL.enterCouponDetailsBLL;
import BLL.productBLL;
import BLL.supplierBLL;
import DAL.productDAL;
import Dungchung.Tester;
import EnterCoupon.*;
import database.databaseHelp;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class enterCouponDetailsGUI extends JFrame {

	List<EnterCouponDetails> ECDetailsList = new ArrayList<EnterCouponDetails>();
	enterCouponDetailsBLL ECDetailsBLL = new enterCouponDetailsBLL();
	productDAL pDAL = new productDAL();
	supplierBLL sBLL = new supplierBLL();
	databaseHelp dh = new databaseHelp();
	
	int flag = 0;
	public JFrame f=new JFrame();
	private JPanel contentPane;
	private JTextField tfDate;
	private JTextField tfProductCode;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JTextField tfPrice;
	private JTextField tfImportPrice;
	private JTextField tfAmount;
	private JTextField tfTotal;
	private JTextField tfCouponCodeDetails;
	JComboBox cbEmployee, cbSuplier;
	String[] employeeList = {"Admin"};
	List<String> supplierList = sBLL.getSupplierList();
	
	public enterCouponDetailsGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lý chi tiết phiếu nhập");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		f.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 108, 334, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lDate = new JLabel("Ngày nhập");
		lDate.setFont(new Font("SansSerif", Font.BOLD, 12));
		lDate.setForeground(new Color(0, 0, 0));
		lDate.setBounds(13, 9, 62, 28);
		panel.add(lDate);
		
		tfDate = new JTextField();
		tfDate.setBounds(138, 11, 186, 28);
		panel.add(tfDate);
		tfDate.setColumns(10);
		tfDate.setText(enterCouponGUI.code);
		tfDate.setEditable(false);
		
		JLabel lProductCode = new JLabel("Mã sản phẩm");
		lProductCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductCode.setForeground(new Color(0, 0, 0));
		lProductCode.setBounds(13, 109, 81, 28);
		panel.add(lProductCode);
		
		tfProductCode = new JTextField();
		tfProductCode.setBounds(138, 111, 186, 28);
		panel.add(tfProductCode);
		tfProductCode.setColumns(10);
		
		JLabel lSupplierCode = new JLabel("Tên nhà cung cấp");
		lSupplierCode.setForeground(new Color(0, 0, 0));
		lSupplierCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSupplierCode.setBounds(13, 162, 115, 28);
		panel.add(lSupplierCode);
		
		cbSuplier = new JComboBox(supplierList.toArray());
		cbSuplier.setBounds(138, 164, 186, 28);
		panel.add(cbSuplier);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(169, 507, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(255, 507, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(6, 507, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(85, 507, 75, 28);
		panel.add(btnReset);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(138, 282, 186, 28);
		panel.add(tfPrice);
		
		JLabel lPrice = new JLabel("Giá bán");
		lPrice.setForeground(new Color(0, 0, 0));
		lPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPrice.setBackground(Color.BLACK);
		lPrice.setBounds(13, 280, 63, 28);
		panel.add(lPrice);
		
		tfImportPrice = new JTextField();
		tfImportPrice.setColumns(10);
		tfImportPrice.setBounds(138, 332, 186, 28);
		panel.add(tfImportPrice);
		
		tfAmount = new JTextField();
		tfAmount.setColumns(10);
		tfAmount.setBounds(138, 383, 186, 28);
		panel.add(tfAmount);
		
		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(138, 427, 186, 28);
		tfTotal.setEditable(false);
		panel.add(tfTotal);
		
		JLabel lImportPrice = new JLabel("Giá nhập");
		lImportPrice.setForeground(new Color(0, 0, 0));
		lImportPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lImportPrice.setBounds(13, 330, 55, 28);
		panel.add(lImportPrice);
		
		JLabel lAmount = new JLabel("Số lượng");
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setBounds(13, 381, 62, 28);
		panel.add(lAmount);
		
		JLabel lTotal = new JLabel("Thành tiền");
		lTotal.setForeground(new Color(0, 0, 0));
		lTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lTotal.setBounds(13, 425, 69, 28);
		panel.add(lTotal);
		
		JLabel lCouponCodeDetails = new JLabel("Mã phiếu nhập");
		lCouponCodeDetails.setForeground(new Color(0, 0, 0));
		lCouponCodeDetails.setFont(new Font("SansSerif", Font.BOLD, 12));
		lCouponCodeDetails.setBounds(13, 61, 123, 28);
		panel.add(lCouponCodeDetails);
		
		tfCouponCodeDetails = new JTextField();
		tfCouponCodeDetails.setColumns(10);
		tfCouponCodeDetails.setBounds(138, 63, 186, 28);
		panel.add(tfCouponCodeDetails);
		
		JLabel lEmployeeName = new JLabel("Tên nhân viên");
		lEmployeeName.setForeground(Color.BLACK);
		lEmployeeName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lEmployeeName.setBounds(13, 223, 115, 28);
		panel.add(lEmployeeName);
		
		cbEmployee = new JComboBox(employeeList);
		cbEmployee.setBounds(138, 225, 186, 28);
		panel.add(cbEmployee);
		cbEmployee.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 108, 1032, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Ngày nhập");
		model.addColumn("Mã phiếu nhập");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Mã nhà sản xuất");
		model.addColumn("Mã nhân viên");
		model.addColumn("Giá bán");
		model.addColumn("Giá nhập");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1364, 111);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Danh Sách Chi Tiết Phiếu Nhập");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(332, 6, 531, 36);
		panel1.add(lbTitle);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 36);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(1010, 12, 243, 36);
		panel1.add(tfFind);
		tfFind.setColumns(10);
		JButton btnTrLi = new JButton("");
		btnTrLi.setBounds(0, 0, 50, 50);
		panel1.add(btnTrLi);
		btnTrLi.setBackground(new Color(50, 205, 50));
		btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));
		
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				enterCouponGUI p = new enterCouponGUI();
				p.f.setVisible(true);
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerformed(e);
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnResetActionPerformed(e);
			}
		});
		cbEmployee.setEnabled(false);;
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(false);
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo mã nhân viên)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
	}
	private void btnResetActionPerformed(ActionEvent e) {
		tfDate.setText(enterCouponGUI.code);
		tfCouponCodeDetails.setText("");
		tfProductCode.setText("");
		tfImportPrice.setText("");
		tfPrice.setText("");
		tfTotal.setText("");
		tfAmount.setText("");
		cbEmployee.setSelectedIndex(0);
		cbSuplier.setSelectedIndex(0);
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
            tfDate.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
            tfCouponCodeDetails.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfProductCode.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		
    		int supplierCode = Integer.parseInt(model.getValueAt(selectedIndex, 4).toString());
    		String supplierName = dh.changeCodeToName("supplier", "SUPPLIER_NAME", supplierCode, "SUPPLIER_CODE");
    		cbSuplier.setSelectedItem(supplierName);
    		
//    		int employeeCode = 0;
//    		if(flag!=1) {
//	    		employeeCode = Integer.parseInt(model.getValueAt(selectedIndex, 5).toString());
//	    		String employeeName = dh.changeCodeToName("employee", "EMPLOYEE_NAME", employeeCode, "EMPLOYEE_CODE");
//	    		cbEmployee.setSelectedItem(employeeName);
//        	}	
//        	else {
//        		cbEmployee.setSelectedItem("Admin");
//        	}	
    		
    		cbEmployee.setSelectedItem("Admin");
    		
    		tfPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
    		tfImportPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 8)));
    		tfTotal.setText(String.valueOf(model.getValueAt(selectedIndex, 9)));
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfDate.getText().trim().equals("")&& !tfCouponCodeDetails.getText().trim().equals("") && !tfProductCode.getText().trim().equals("") && !tfPrice.getText().trim().equals("") && !tfImportPrice.getText().trim().equals("") && !tfAmount.getText().trim().equals("")) {
			try {
				String date = tfDate.getText();
				String codeDetails = tfCouponCodeDetails.getText();
				String productCode = tfProductCode.getText();
				
				String supplierName = cbSuplier.getSelectedItem().toString();
				int supplierCode = dh.changeNameToCode("supplier", "SUPPLIER_CODE", supplierName, "SUPPLIER_NAME");	
				
				int employeeCode = 0;
				
				int price = Integer.parseInt(tfPrice.getText());
				int importPrice  = Integer.parseInt(tfImportPrice.getText());
				int amount  = Integer.parseInt(tfAmount.getText());
				int total  = 0;
				// chheck giá nhập và giá bán
				if(importPrice>=price) {
					JOptionPane.showMessageDialog(null, "Giá nhập phải nhỏ hơn giá bán");
					return;
				}
				// check ngày
				if(!Tester.day(date)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của ngày (Ví Dụ: 10/05/2021) ");
					return;
				}
				// check số lượng
				if(amount <= 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng phù hơp của sản phẩm");
					return;
				}
				EnterCouponDetails p = new EnterCouponDetails(date, codeDetails, productCode, supplierCode, employeeCode, price, importPrice, amount, total);
				
				JOptionPane.showMessageDialog(null, ECDetailsBLL.addEnterCouponDetails(p));
				
				displayList();
				btnResetActionPerformed(e);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của phiếu nhập");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String employeeCode = tfFind.getText();
        if(employeeCode != null && employeeCode.length() > 0) {
            ECDetailsList = ECDetailsBLL.searchEnterCouponDetailsByEmployeeCode(employeeCode);
            
            if(ECDetailsList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có phiếu nhập bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < ECDetailsList.size()) {
	    			EnterCouponDetails p = ECDetailsList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getCouponDate(), p.getEnterCouponCodeDetails(), p.getProductCode(), p.getSupplierCode(), p.getEmployeeCode(), Tester.format(p.getPrice()), Tester.format(p.getImportPrice()), p.getAmount(), Tester.format(p.getTotal())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm cần tìm kiếm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(!tfDate.getText().trim().equals("") && !tfCouponCodeDetails.getText().trim().equals("") && !tfProductCode.getText().trim().equals("") && !tfPrice.getText().trim().equals("") && !tfImportPrice.getText().trim().equals("") && !tfAmount.getText().trim().equals("")) {
				EnterCouponDetails p = new EnterCouponDetails();
				
				p.setCouponDate(tfDate.getText());
				p.setEnterCouponCodeDetails(tfCouponCodeDetails.getText());
				p.setProductCode(tfProductCode.getText());
				
				String supplierName = String.valueOf(cbSuplier.getSelectedItem().toString());
				int supplierCode = dh.changeNameToCode("supplier", "SUPPLIER_CODE", supplierName, "SUPPLIER_NAME");
				p.setSupplierCode(supplierCode);
				
				String employeeName = String.valueOf(cbEmployee.getSelectedItem().toString());
				int employeeCode = dh.changeNameToCode("account", "ID", employeeName, "USERNAME");
				p.setEmployeeCode(employeeCode);
				
				p.setPrice(Tester.formatStringToNumber(tfPrice.getText()));
				p.setImportPrice(Tester.formatStringToNumber(tfImportPrice.getText()));
				p.setAmount(Integer.parseInt(tfAmount.getText()));
				p.setTotal(p.getAmount() * p.getImportPrice());
				
				// chheck giá nhập và giá bán
				if(p.getImportPrice()>=p.getPrice()) {
					JOptionPane.showMessageDialog(null, "Giá nhập phải nhỏ hơn giá bán");
					return;
				}
				// check ngày
				if(!Tester.day(p.getCouponDate())) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của ngày (Ví Dụ: 10/05/2021) ");
					return;
				}
				
				if(p.getAmount() <= 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng phù hơp của sản phẩm");
					return;
				}
				JOptionPane.showMessageDialog(null, ECDetailsBLL.editEnterCouponDetails(p));
				displayList();
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của phiếu nhập");
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập phải là số");
		} 
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfCouponCodeDetails.getText().equals("") && !tfDate.getText().equals("")) {
        	String date = tfDate.getText();
        	String code = tfCouponCodeDetails.getText();
//            bill p = billList.get(selectedIndex);
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu nhập này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa phiếu nhập này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, ECDetailsBLL.deleteEnterCouponDetails(code, date));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần xóa");
        }
	}
	
	public void displayList() {
		model.setRowCount(0);
		ECDetailsList = ECDetailsBLL.getAllEnterCouponDetails(enterCouponGUI.code);
    	int i = 0;
    	while(i < ECDetailsList.size()) {
			EnterCouponDetails p = ECDetailsList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getCouponDate(), p.getEnterCouponCodeDetails(), p.getProductCode(), p.getSupplierCode(), p.getEmployeeCode(), Tester.format(p.getPrice()), Tester.format(p.getImportPrice()), p.getAmount(), Tester.format(p.getTotal())
			});
			i++;
		}
	}

}