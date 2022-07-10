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

import BLL.supplierBLL;
import Supplier.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class supplierGUI extends JFrame {
	List<Supplier> supplierList = new ArrayList<Supplier>();
	supplierBLL supBLL = new supplierBLL();
	
	private JPanel contentPane;
	private JTextField tfSupplierCode;
	private JTextField tfSupplierName;
	private JTextField tfSupplierNumberPhone;
	private JTextField tfSupplierAddress;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	public JFrame f = new JFrame();
	
	public supplierGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lí nhà cung cấp");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(220, 220, 220));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 108, 329, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lSupplierCode = new JLabel("Mã nhà cung cấp");
		lSupplierCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSupplierCode.setForeground(new Color(0, 0, 0));
		lSupplierCode.setBounds(10, 54, 100, 28);
		panel.add(lSupplierCode);
		
		tfSupplierCode = new JTextField();
		tfSupplierCode.setBounds(120, 56, 183, 28);
		panel.add(tfSupplierCode);
		tfSupplierCode.setColumns(10);
		f.setResizable(false);
		JLabel lSupplierName = new JLabel("Tên nhà cung cấp");
		lSupplierName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSupplierName.setForeground(new Color(0, 0, 0));
		lSupplierName.setBounds(10, 102, 101, 28);
		panel.add(lSupplierName);
		
		JLabel lSupplierAddress = new JLabel("Địa chỉ");
		lSupplierAddress.setForeground(new Color(0, 0, 0));
		lSupplierAddress.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSupplierAddress.setBounds(10, 155, 55, 28);
		panel.add(lSupplierAddress);
		
		tfSupplierName = new JTextField();
		tfSupplierName.setBounds(120, 104, 183, 28);
		panel.add(tfSupplierName);
		tfSupplierName.setColumns(10);
		
		JLabel lbSupplierNumberPhone = new JLabel("Số điện thoại");
		lbSupplierNumberPhone.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbSupplierNumberPhone.setForeground(new Color(0, 0, 0));
		lbSupplierNumberPhone.setBounds(10, 193, 100, 28);
		panel.add(lbSupplierNumberPhone);
		
		tfSupplierNumberPhone = new JTextField();
		tfSupplierNumberPhone.setBounds(120, 195, 183, 28);
		panel.add(tfSupplierNumberPhone);
		tfSupplierNumberPhone.setColumns(10);
		
		tfSupplierAddress = new JTextField();
		tfSupplierAddress.setBounds(120, 151, 183, 28);
		panel.add(tfSupplierAddress);
		tfSupplierAddress.setColumns(10);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(170, 274, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(249, 274, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(10, 274, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(85, 274, 75, 28);
		panel.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(328, 108, 1032, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã nhà cung cấp");
		model.addColumn("Tên nhà cung cấp");
		model.addColumn("Địa chỉ:");
		model.addColumn("Số điện thoại");
		
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
		
		JLabel lDSNCC = new JLabel("Danh Sách Nhà Cung Cấp");
		lDSNCC.setForeground(Color.WHITE);
		lDSNCC.setFont(new Font("SansSerif", Font.BOLD, 30));
		lDSNCC.setBounds(315, 6, 508, 36);
		panel1.add(lDSNCC);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 38);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(1010, 12, 243, 38);
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
				adminGUI p = new adminGUI();
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
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
		JLabel lblNewLabel = new JLabel("(Tìm kiếm tên nhà cung cấp)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfSupplierCode.setText("");
		tfSupplierName.setText("");
		tfSupplierAddress.setText("");
		tfSupplierNumberPhone.setText("");
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
            tfSupplierCode.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfSupplierName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfSupplierAddress.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfSupplierNumberPhone.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfSupplierCode.getText().trim().equals("") && !tfSupplierName.getText().trim().equals("") && !tfSupplierNumberPhone.getText().trim().equals("") && !tfSupplierAddress.getText().trim().equals("")) {
			try {
				String code = tfSupplierCode.getText();
				String name = tfSupplierName.getText();
				String address = tfSupplierAddress.getText();
				String phone = tfSupplierNumberPhone.getText();
				Supplier s=new Supplier(code,name,address,phone);				
				JOptionPane.showMessageDialog(null,supBLL.addSupplier(s) );
				displayList();
				btnResetActionPerformed(e);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Nhập đúng kí tự");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của sản phẩm");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String supplierName = tfFind.getText();
        if(supplierName != null && supplierName.length() > 0) {
            supplierList = supBLL.searchSupplierByName(supplierName);
            
            if(supplierList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có nhà cung cấp bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < supplierList.size()) {
	    			Supplier p = supplierList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getSupplierCode(), p.getSupplierName(), p.getAddress(), p.getNumberPhone()
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của nhà cung cấp để tìm kiếm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(index>=0 && !tfSupplierCode.getText().equals("") && !tfSupplierName.getText().equals("") && !tfSupplierNumberPhone.getText().equals("") && !tfSupplierAddress.getText().equals("") ) {
				Supplier p = new Supplier();
				p.setSupplierCode(tfSupplierCode.getText());
				p.setSupplierName(tfSupplierName.getText());
				p.setAddress(tfSupplierAddress.getText());
				p.setNumberPhone(tfSupplierNumberPhone.getText());
				JOptionPane.showMessageDialog(null, supBLL.editSupplier(p));
				displayList();
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của nhà cung cấp");
		}
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfSupplierCode.getText().equals("")) {
        	String code = tfSupplierCode.getText();
//            Product p = productList.get(selectedIndex);
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhà cung cấp này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhà cung cấp này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, supBLL.deleteSupplier(code));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp để xóa");
        }
	}
	
	private void displayList() {
		model.setRowCount(0);
		supplierList = supBLL.getAllSupplier();
    	int i = 0;
		while(i < supplierList.size()) {
			Supplier p = supplierList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getSupplierCode(), p.getSupplierName(), p.getAddress(), p.getNumberPhone()
			});
			i++;
		}
		
	}
}