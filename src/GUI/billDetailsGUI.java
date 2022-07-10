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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import BLL.CustomerBLL;
import BLL.billDetailsBLL;
import BLL.accountBLL;
import BLL.productBLL;
import Bill.BillDetails;
import Dungchung.Tester;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;
import GUI.*;
import database.databaseHelp;

public class billDetailsGUI extends JFrame {

	List<BillDetails> billDetailsList = new ArrayList<BillDetails>();
	billDetailsBLL billDetailsBBL = new billDetailsBLL();
	accountBLL accBLL = new accountBLL();
	productBLL pBLL = new productBLL();
	databaseHelp dh = new databaseHelp();
	
	static Date now = new Date();
	static String ft = new SimpleDateFormat("dd/MM/yyyy").format(now);
	static String billDate = ft.formatted(now);
	private JPanel contentPane;
	private JTextField tfBillDate, tfBillDetailsCode;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JTextField tfAmount;
	private JTextField tfPrice;
	private JTextField tfTotal;
	public JFrame f = new JFrame("Danh sách chi tiết phiếu xuất");
	JComboBox cbEmployee, cbCustomer, cbProduct;
	List<String> employeeList = accBLL.getList("Nhân viên");
	List<String> customerList = accBLL.getList("Khách hàng");
	List<String> productList = pBLL.getProductList();

	public billDetailsGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Chi tiết phiếu xuất");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 108, 319, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		f.add(contentPane);
		f.setVisible(true);
		f.setSize(1366, 740);
		
		tfBillDate = new JTextField();
		tfBillDate.setBounds(132, 44, 177, 28);
		panel.add(tfBillDate);
		tfBillDate.setColumns(10);
		tfBillDate.setText(billGUI.date);
		tfBillDate.setEditable(false);
		
		// check
		JLabel lbillDetailsCode = new JLabel("Mã phiếu chi tiết");
		
		tfBillDetailsCode = new JTextField();
		tfBillDetailsCode.setBounds(132, 89, 177, 28);
		panel.add(tfBillDetailsCode);
		tfBillDetailsCode.setColumns(10);
		// check 
		
		JLabel CustomerCode = new JLabel("Tên khách hàng");
		CustomerCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		CustomerCode.setForeground(new Color(0, 0, 0));
		CustomerCode.setBounds(24, 141, 142, 28);
		panel.add(CustomerCode);
		
		cbCustomer = new JComboBox(customerList.toArray());
		cbCustomer.setBounds(132, 143, 177, 28);
		panel.add(cbCustomer);
		
		
		JLabel total = new JLabel("Thành tiền");
		total.setForeground(new Color(0, 0, 0));
		total.setFont(new Font("SansSerif", Font.BOLD, 12));
		total.setBounds(25, 410, 90, 28);
		panel.add(total);
		
		JLabel ProductCode = new JLabel("Tên sản phẩm");
		ProductCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		ProductCode.setForeground(new Color(0, 0, 0));
		ProductCode.setBackground(Color.BLACK);
		ProductCode.setBounds(25, 194, 90, 28);
		panel.add(ProductCode);
		
		JLabel lemployeeName = new JLabel("Tên nhân viên");
		lemployeeName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lemployeeName.setForeground(new Color(0, 0, 0));
		lemployeeName.setBackground(Color.BLACK);
		lemployeeName.setBounds(25, 247, 90, 28);
		panel.add(lemployeeName);
		
		tfTotal = new JTextField();
		tfTotal.setBounds(132, 412, 177, 28);
		panel.add(tfTotal);
		tfTotal.setColumns(10);
		tfTotal.setEditable(false);
		
		cbEmployee = new JComboBox(employeeList.toArray());
		cbEmployee.setBounds(132, 249, 177, 28);
		panel.add(cbEmployee);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setBounds(169, 544, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(243, 544, 69, 28);
		panel.add(btnAdd);
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(6, 544, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(85, 544, 75, 28);
		panel.add(btnReset);
		
		cbProduct = new JComboBox(productList.toArray());
		cbProduct.setBounds(132, 196, 177, 28);
		panel.add(cbProduct);
		
		JLabel Amount = new JLabel("Số lượng");
		Amount.setForeground(new Color(0, 0, 0));
		Amount.setFont(new Font("SansSerif", Font.BOLD, 12));
		Amount.setBackground(Color.BLACK);
		Amount.setBounds(25, 303, 90, 28);
		panel.add(Amount);
		
		JLabel Price = new JLabel("Giá bán");
		Price.setForeground(new Color(0, 0, 0));
		Price.setFont(new Font("SansSerif", Font.BOLD, 12));
		Price.setBackground(Color.BLACK);
		Price.setBounds(25, 358, 90, 28);
		panel.add(Price);
		
		tfAmount = new JTextField();
		tfAmount.setColumns(10);
		tfAmount.setBounds(132, 305, 177, 28);
		panel.add(tfAmount);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(132, 360, 177, 28);
		panel.add(tfPrice);
		
		JLabel billCode = new JLabel("Ngày xuất");
		billCode.setForeground(new Color(0, 0, 0));
		billCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		billCode.setBounds(25, 44, 118, 28);
		panel.add(billCode);
		
		JLabel billBillDetailsCode = new JLabel("Mã phiếu chi tiết");
		billBillDetailsCode.setForeground(Color.BLACK);
		billBillDetailsCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		billBillDetailsCode.setBounds(24, 89, 118, 28);
		panel.add(billBillDetailsCode);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 108, 1047, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Ngày xuất");
		model.addColumn("Mã phiếu chi tiết");
		model.addColumn("Mã khách hàng");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Mã nhân viên");
		model.addColumn("Số lượng");
		model.addColumn("Giá bán");
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
		
		JLabel lbTitle = new JLabel("Danh Sách Chi Tiết Phiếu Xuất");
		lbTitle.setBackground(new Color(50, 205, 50));
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 468, 36);
		panel1.add(lbTitle);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 36);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(963, 12, 290, 36);
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
		f.setResizable(false);
		if(employeeInterfaceGUI.employeeCode!=-1) {
			String employeeName = dh.changeCodeToName("account", "USERNAME", employeeInterfaceGUI.employeeCode, "ID");
			cbEmployee.setSelectedItem(employeeName);
			cbEmployee.setEnabled(false);
			btnEdit.setEnabled(false);
			btnRemove.setEnabled(false);
			// Set ngày cho bill đó của nhân viên
	    	tfBillDate.setText(billDate);
		}
		if(loginGUI.accountName.equals("adminlachalame")) {
			cbEmployee.addItem("Admin");
			cbEmployee.setSelectedItem("Admin");
			cbEmployee.setEnabled(false);
		}
		tfPrice.setEditable(false);
		cbProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String productName = cbProduct.getSelectedItem().toString();
				int price = dh.changeNameToCode("product", "PRICE", productName, "PRODUCT_NAME");
				tfPrice.setText(Tester.format(Long.parseLong(String.valueOf(price))));
			}
		});
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo mã sản phẩm)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfBillDetailsCode.setText("");
		cbCustomer.setSelectedIndex(0);
		cbEmployee.setSelectedIndex(0);
		cbProduct.setSelectedIndex(0);
		tfAmount.setText("");
		tfPrice.setText("");
		tfTotal.setText("");
		
		if(employeeInterfaceGUI.employeeCode!=-1) {
			tfBillDate.setText(billDate);
			int employeeCode = employeeInterfaceGUI.employeeCode;
			String employeeName = dh.changeCodeToName("account", "USERNAME", employeeInterfaceGUI.employeeCode, "ID");
			cbEmployee.setSelectedItem(employeeName);
		}
		else {
			tfBillDate.setText(billGUI.date);
		}
		tfBillDate.setEditable(false);
		tfBillDetailsCode.setEditable(true);
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
        	tfBillDate.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
            tfBillDetailsCode.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
            
            int customerCode = Integer.parseInt(model.getValueAt(selectedIndex, 3).toString());
    		String customerName = dh.changeCodeToName("account", "USERNAME", customerCode, "ID");
    		cbCustomer.setSelectedItem(String.valueOf(customerName));

    		int productCode = Integer.parseInt(model.getValueAt(selectedIndex, 4).toString());
    		String productName = dh.changeCodeToName("product", "PRODUCT_NAME", productCode, "PRODUCT_CODE");
    		cbProduct.setSelectedItem(productName);
    		
            int employeeCode = Integer.parseInt(model.getValueAt(selectedIndex, 5).toString());
            String employeeName = dh.changeCodeToName("account", "USERNAME", employeeCode, "ID");
    		cbEmployee.setSelectedItem(String.valueOf(employeeName));
            
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
    		tfPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
    		tfTotal.setText(String.valueOf(model.getValueAt(selectedIndex, 8)));
    		tfBillDate.setEditable(false);
    		tfBillDetailsCode.setEditable(false);
    		tfTotal.setEditable(false);
    		
    		// if user is employee
    		if(employeeInterfaceGUI.employeeCode!=-1) {
    			String employeeName1 = dh.changeCodeToName("account", "USERNAME", employeeInterfaceGUI.employeeCode, "ID");
    			cbEmployee.setSelectedItem(employeeName1);
    		}
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfBillDate.getText().trim().equals("") && !tfBillDetailsCode.getText().trim().equals("") && !tfAmount.getText().trim().equals("") && !tfPrice.getText().trim().equals("")) {
			try {
				String billDate = tfBillDate.getText();
				int billDetailsCode = Integer.parseInt(tfBillDetailsCode.getText());
				
				String customerName = cbCustomer.getSelectedItem().toString();
				int customerCode = dh.changeNameToCode("account", "ID", customerName, "USERNAME");
				
				String productName = cbProduct.getSelectedItem().toString();
				String productCode = String.valueOf(dh.changeNameToCode("product", "PRODUCT_CODE", productName, "PRODUCT_NAME"));
				
				String employeeName = cbEmployee.getSelectedItem().toString();
				int employeeCode = dh.changeNameToCode("account", "ID", employeeName, "USERNAME");
				
				int amount = Integer.parseInt(tfAmount.getText());
				int price = Tester.formatStringToNumber(tfPrice.getText());
				int total = amount * price;
				
				if(amount <=0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn số lượng phù hợp của sản phẩm");
					return;
				}
				BillDetails p = new BillDetails(billDate, billDetailsCode, customerCode, productCode, employeeCode, amount, price, total, 1);
				JOptionPane.showMessageDialog(null, billDetailsBBL.addBillDetails(p, billDetailsList));
				
				displayList();
				btnResetActionPerformed(e);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của phiếu xuất");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String dataToFind = null;
		if(employeeInterfaceGUI.employeeCode!=-1) {
			// tìm kiếm theo mã khách hàng
			dataToFind = tfFind.getText();
		}
		else {
			// tìm kiếm theo mã sản phẩm
			dataToFind = tfFind.getText();
		}
        if(dataToFind != null && dataToFind.length() > 0) {
        	if(employeeInterfaceGUI.employeeCode!=-1) {
        		billDetailsList = billDetailsBBL.searchBillDetailsByCustomerCode(dataToFind, "CUSTOMER_CODE");
        	}
        	else {
        		billDetailsList = billDetailsBBL.searchBillDetailsByCustomerCode(dataToFind, "PRODUCT_CODE");
        	}
            
            if(billDetailsList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có phiếu xuất bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < billDetailsList.size()) {
	    			BillDetails p = billDetailsList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getBillDate(), p.getBillDetailsCode(), p.getCustomerCode(), p.getProductCode(), p.getEmployeeCode(), p.getAmount(), Tester.format(p.getPrice()), Tester.format(p.getTotal())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	if(employeeInterfaceGUI.employeeCode!=-1) {
        		JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng để tìm kiếm");
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm để tìm kiếm");
        	}
        }
	}
	

	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(index>=0 && !tfBillDate.getText().equals("") && !tfBillDetailsCode.getText().trim().equals("")  && !tfAmount.getText().equals("") && !tfPrice.getText().equals("")) {
				BillDetails p = new BillDetails();
				
				p.setBillDate(tfBillDate.getText());
				p.setBillDetailsCode(Integer.parseInt(tfBillDetailsCode.getText()));
				
				String customerName = String.valueOf(cbCustomer.getSelectedItem().toString());
				int customerCode = dh.changeNameToCode("account", "ID", customerName, "USERNAME");
				p.setCustomerCode(customerCode);
				
				String employeeName = String.valueOf(cbEmployee.getSelectedItem().toString());
				int employeeCode = dh.changeNameToCode("account", "ID", employeeName, "USERNAME");
				p.setEmployeeCode(employeeCode);
				
				String productName = String.valueOf(cbProduct.getSelectedItem().toString());
				String productCode = String.valueOf(dh.changeNameToCode("product", "PRODUCT_CODE", productName, "PRODUCT_NAME"));
				p.setProductCode(productCode);
				
				p.setAmount(Integer.parseInt(tfAmount.getText()));
				p.setPrice(Integer.parseInt(tfPrice.getText()));
				p.setTotal(p.getPrice() * p.getAmount());
				
				JOptionPane.showMessageDialog(null, billDetailsBBL.editBillDetails(p));
				displayList();
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu xuất để sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của phiếu xuất");
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
		} 
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfBillDate.getText().equals("")) {
        	String code = tfBillDetailsCode.getText();
        	String date = tfBillDate.getText();
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu xuất này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu xuất này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
            		JOptionPane.showMessageDialog(null, billDetailsBBL.deleteBillDetails(code, date));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu xuất để xóa");
        }
	}
	
	public void displayList() {
		model.setRowCount(0);
		if(employeeInterfaceGUI.employeeCode!=-1) {
			billDetailsList = billDetailsBBL.getAllBillDetails(billDate);
		}
		else {
			billDetailsList = billDetailsBBL.getAllBillDetails(billGUI.date);
		}
    	int i = 0;
		while(i < billDetailsList.size()) {
			BillDetails p = billDetailsList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getBillDate(), p.getBillDetailsCode(), p.getCustomerCode(), p.getProductCode(), p.getEmployeeCode(), p.getAmount(), Tester.format(p.getPrice()), Tester.format(p.getTotal())
			});
			i++;
		}
	}
}