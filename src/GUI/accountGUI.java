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

import BLL.accountBLL;
import Account.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class accountGUI extends JFrame {

	List<Account> accountList = new ArrayList<Account>();
	accountBLL accBBL = new accountBLL();
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfAccountName;
	private JTextField tfPassword;
	JComboBox cbPermission, cbPermission_1;
	String [] permissionList = {"Admin",  "Nhân viên", "Khách hàng"};
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JTextField tfUsername;
	private JTextField tfAddress;
	private JTextField tfBirthday;
	private JTextField tfNumberPhone;
	private JTextField tfID;
	
	
	public accountGUI() {
		initComponent();
		displayList("Khách hàng");
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lí tài khoản");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 108, 399, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lCode = new JLabel("Tên tài khoản");
		lCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lCode.setForeground(new Color(0, 0, 0));
		lCode.setBounds(25, 90, 150, 28);
		panel.add(lCode);
		
		tfAccountName = new JTextField();
		tfAccountName.setBounds(185, 93, 182, 28);
		panel.add(tfAccountName);
		tfAccountName.setColumns(10);
		
		JLabel lName = new JLabel("Password");
		lName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lName.setForeground(new Color(0, 0, 0));
		lName.setBounds(25, 150, 150, 28);
		panel.add(lName);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(185, 152, 182, 28);
		panel.add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel lPermission = new JLabel("Phân quyền tài khoản:");
		lPermission.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPermission.setForeground(new Color(0, 0, 0));
		lPermission.setBounds(25, 436, 150, 28);
		panel.add(lPermission);
		
		cbPermission = new JComboBox(permissionList);
		cbPermission.setBounds(185, 437, 182, 28);
		panel.add(cbPermission);
		
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(218, 494, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(320, 494, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(10, 494, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(113, 494, 75, 28);
		panel.add(btnReset);
		
		JLabel lblTnNgiDng = new JLabel("Tên người dùng");
		lblTnNgiDng.setForeground(Color.BLACK);
		lblTnNgiDng.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTnNgiDng.setBounds(25, 202, 150, 28);
		panel.add(lblTnNgiDng);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(185, 205, 182, 28);
		panel.add(tfUsername);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(Color.BLACK);
		lblaCh.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblaCh.setBounds(25, 257, 150, 28);
		panel.add(lblaCh);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(185, 259, 182, 28);
		panel.add(tfAddress);
		
		tfBirthday = new JTextField();
		tfBirthday.setColumns(10);
		tfBirthday.setBounds(185, 317, 182, 28);
		panel.add(tfBirthday);
		
		JLabel lName_1_1 = new JLabel("Ngày sinh");
		lName_1_1.setForeground(Color.BLACK);
		lName_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lName_1_1.setBounds(25, 315, 150, 28);
		panel.add(lName_1_1);
		
		JLabel lName_1_1_1 = new JLabel("Số điện thoại");
		lName_1_1_1.setForeground(Color.BLACK);
		lName_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lName_1_1_1.setBounds(25, 374, 150, 28);
		panel.add(lName_1_1_1);
		
		tfNumberPhone = new JTextField();
		tfNumberPhone.setColumns(10);
		tfNumberPhone.setBounds(185, 376, 182, 28);
		panel.add(tfNumberPhone);
		
		JLabel lblMTiKhon = new JLabel("Mã tài khoản");
		lblMTiKhon.setForeground(Color.BLACK);
		lblMTiKhon.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMTiKhon.setBounds(25, 40, 150, 28);
		panel.add(lblMTiKhon);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setText(String.valueOf(accBBL.getAccountCode()));;
		tfID.setBounds(185, 43, 182, 28);
		panel.add(tfID);
		tfID.setEditable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(399, 108, 961, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã tài khoản");
		model.addColumn("Tên tài khoản");
		model.addColumn("Mật khẩu");
		model.addColumn("Tên người dùng");
		model.addColumn("Địa chỉ");
		model.addColumn("Ngày sinh");
		model.addColumn("Số điện thoại");
		model.addColumn("Quyền của tài khoản");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				jTableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1364, 111);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Danh Sách Tài Khoản");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 372, 57);
		panel1.add(lbTitle);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(1252, 12, 90, 38);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(934, 12, 319, 38);
		panel1.add(tfFind);
		tfFind.setColumns(10);
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTableMouseClicked(e);
			}
		});
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setResizable(false);
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo tên người dùng)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
		
		JLabel lblHinThTi = new JLabel("Hiển thị tài khoản theo");
		lblHinThTi.setForeground(Color.WHITE);
		lblHinThTi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHinThTi.setBounds(43, 60, 195, 28);
		panel1.add(lblHinThTi);
		
		cbPermission_1 = new JComboBox(permissionList);
		cbPermission_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbPermission_1.setBounds(215, 60, 182, 28);
		panel1.add(cbPermission_1);
		cbPermission_1.setSelectedItem("Khách hàng");
		cbPermission_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String permission = cbPermission_1.getSelectedItem().toString();
				displayList(permission);
			}
		});
		
		// permission by role 
		if(employeeInterfaceGUI.employeeCode!=-1) {
			// bỏ tài khoản nhân viên và tài khoản admin ra khỏi mảng hiển thị chỉ hiện tài khoản khách hàng
			btnRemove.setEnabled(false);
			cbPermission.setSelectedItem("Khách hàng");
			cbPermission.setEnabled(false);
			cbPermission_1.setEnabled(false);
		}
 	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfID.setText(String.valueOf(accBBL.getAccountCode()));
		tfAccountName.setText("");
		tfPassword.setText("");
		tfUsername.setText("");
		tfAddress.setText("");
		tfBirthday.setText("");;
		tfNumberPhone.setText("");
		tfID.setText(String.valueOf(accBBL.getAccountCode()));
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {    
        	tfID.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfAccountName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfPassword.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfUsername.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
    		tfAddress.setText(String.valueOf(model.getValueAt(selectedIndex, 5)));
    		tfBirthday.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
    		tfNumberPhone.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
    		cbPermission.setSelectedItem(String.valueOf(model.getValueAt(selectedIndex, 8)));
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfAccountName.getText().trim().equals("") && !tfPassword.getText().trim().equals("")) {
			try {
				// id và phân quyền chưa thêm
				int id = Integer.parseInt(tfID.getText().toString());
				String accountName = tfAccountName.getText();
				String password = tfPassword.getText();
				String permission = cbPermission.getSelectedItem().toString();
				String userName = tfUsername.getText();
				String address = tfAddress.getText();
				String numberPhone = tfNumberPhone.getText();
				String birthday = tfBirthday.getText();
				
				Account p = new Account(id, accountName,password, permission, userName, address, numberPhone, birthday);
				
				JOptionPane.showMessageDialog(null, accBBL.addAccount(p));
				
				displayList(cbPermission_1.getSelectedItem().toString());
				btnResetActionPerformed(e);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của tài khoản");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String userName = String.valueOf(tfFind.getText());
        if(!userName.equals("")) {
            accountList = accBBL.searchAccountByName(userName);
            
            if(accountList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có tài khoản bạn cần tìm");
            	displayList(cbPermission_1.getSelectedItem().toString());
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	            while(i < accountList.size()) {
	    			Account p = accountList.get(i);
	    			if(employeeInterfaceGUI.employeeCode!=-1) {
	    				if(p.getPermission().equals("Nhân viên") || p.getPermission().equals("admin")) {
	    					i++;
	    					continue;
	    				}
	    			}
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getId(), p.getAccountName(), p.getPassword(), p.getUserName(), p.getAddress(), p.getBirthday(), p.getNumberPhone(), p.getPermission()
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên người dùng của tài khoản để tìm kiếm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(index>=0 && !tfAccountName.getText().equals("") && !tfPassword.getText().equals("") && !tfID.getText().trim().equals("")) {
				Account p = new Account();
				
				p.setId(Integer.parseInt(tfID.getText()));
				p.setAccountName(tfAccountName.getText());
				p.setPassword(tfPassword.getText());
				p.setPermission(cbPermission.getSelectedItem().toString());
				p.setUserName(tfUsername.getText());
				p.setAddress(tfAddress.getText());
				p.setNumberPhone(tfNumberPhone.getText());
				p.setBirthday(tfBirthday.getText());
				
				JOptionPane.showMessageDialog(null, accBBL.editAccount(p));
				displayList(cbPermission_1.getSelectedItem().toString());
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của tài khoản");
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
		} 
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfAccountName.getText().equals("")) {
        	int code = Integer.parseInt(tfID.getText());
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
            		JOptionPane.showMessageDialog(null, accBBL.deleteAccount(code)); 
	                displayList(cbPermission_1.getSelectedItem().toString());
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để xóa");
        }
	}
	
	private void displayList(String permission) {
		model.setRowCount(0);
		accountList = accBBL.getAllAccount(cbPermission_1.getSelectedItem().toString());
    	int i = 0;
		while(i < accountList.size()) {
			Account p = accountList.get(i);
			if(employeeInterfaceGUI.employeeCode!=-1) {
				if(p.getPermission().equals("Nhân viên") || p.getPermission().equals("admin")) {
					i++;
					continue;
				}
			}
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getId(), p.getAccountName(), p.getPassword(), p.getUserName(), p.getAddress(), p.getBirthday(), p.getNumberPhone(), p.getPermission()
			});
			i++;
		}
		
	}
}