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

import BLL.*;
import Account.*;
import Users.*;
import userService.Cart;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class customerAccountGUI extends JFrame {

	List<Account> accountList = new ArrayList<Account>();
	accountBLL accBLL = new accountBLL();
//	userBLL userBLL = new userBLL();
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfAccountCode;
	DefaultTableModel model = new DefaultTableModel();
	private JTextField tfAccountName;
	private JTextField tfAddress;
	private JTextField tfNumberPhone;
	private JTextField tfPassword;
	private JTextField tfBirthday;
	public customerAccountGUI() {
		initComponent();
		display();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Tài khoản của tôi");
		setSize(700, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(60, 179, 113));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(0, 111, 708, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*JLabel lAccountCode = new JLabel("ID");
		lAccountCode.setFont(new Font("SansSerif", Font.BOLD, 14));
		lAccountCode.setForeground(Color.WHITE);
		lAccountCode.setBounds(198, 42, 115, 28);
		panel.add(lAccountCode);
		
		tfAccountCode = new JTextField();
		tfAccountCode.setBounds(481, 39, 279, 40);
		panel.add(tfAccountCode);
		tfAccountCode.setColumns(10);*/
		JLabel lAccountName = new JLabel("Tên người dùng");
		lAccountName.setForeground(new Color(0, 0, 0));
		lAccountName.setFont(new Font("SansSerif", Font.BOLD, 14));
		lAccountName.setBounds(22, 55, 192, 28);
		panel.add(lAccountName);
		

		JLabel lAddress = new JLabel("Địa chỉ");
		lAddress.setForeground(new Color(0, 0, 0));
		lAddress.setFont(new Font("SansSerif", Font.BOLD, 14));
		lAddress.setBounds(22, 203, 209, 28);
		panel.add(lAddress);

		
		JButton btnUpdateInfo = new JButton("Cập nhật thông tin");
		btnUpdateInfo.setIcon(new ImageIcon("C:\\Users\\huynh\\OneDrive\\Máy tính\\LTHDT\\Image\\refresh-54-32.png"));
		btnUpdateInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdateInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if(tfAccountName.getText().equals("") || tfPassword.getText().equals("") || tfAddress.getText().equals("") || tfNumberPhone.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của tài khoản");
					}
					else {
						Account p = new Account();					
						p.setPassword(tfPassword.getText());
						p.setUserName(tfAccountName.getText().toString());
						p.setAddress(tfAddress.getText().toString());
						p.setNumberPhone(tfNumberPhone.getText().toString());
						p.setBirthday(tfBirthday.getText());
						
						JOptionPane.showMessageDialog(null, accBLL.updateAccount(p, Integer.parseInt(loginGUI.code)));
					}
			}
		});
		btnUpdateInfo.setBounds(224, 478, 279, 50);
		panel.add(btnUpdateInfo);
		
		JLabel lNumberPhone = new JLabel("Số điện thoại");
		lNumberPhone.setForeground(new Color(0, 0, 0));
		lNumberPhone.setFont(new Font("SansSerif", Font.BOLD, 14));
		lNumberPhone.setBounds(22, 278, 209, 28);
		panel.add(lNumberPhone);
		
		tfAccountName = new JTextField();
		tfAccountName.setColumns(10);
		tfAccountName.setBounds(224, 55, 279, 40);
		panel.add(tfAccountName);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(224, 203, 279, 40);
		panel.add(tfAddress);
		
		tfNumberPhone = new JTextField();
		tfNumberPhone.setColumns(10);
		tfNumberPhone.setBounds(224, 278, 279, 40);
		panel.add(tfNumberPhone);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(224, 127, 279, 40);
		panel.add(tfPassword);
		
		JLabel lPassword = new JLabel("Mật khẩu");
		lPassword.setForeground(new Color(0, 0, 0));
		lPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		lPassword.setBounds(22, 127, 209, 28);
		panel.add(lPassword);
		
		JLabel lBirthday = new JLabel("Ngày sinh");
		lBirthday.setForeground(Color.BLACK);
		lBirthday.setFont(new Font("SansSerif", Font.BOLD, 14));
		lBirthday.setBounds(22, 354, 209, 28);
		panel.add(lBirthday);
		
		tfBirthday = new JTextField();
		tfBirthday.setColumns(10);
		tfBirthday.setBounds(224, 354, 279, 40);
		panel.add(tfBirthday);
		
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 708, 111);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Tài khoản của tôi");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(200, 0, 280, 48);
		panel1.add(lbTitle);
		JButton btnTrLi = new JButton("");
		btnTrLi.setBounds(0, 0, 50, 50);
		panel1.add(btnTrLi);
		btnTrLi.setBackground(new Color(50, 205, 50));
		btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));
		
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				indexUserGUI p = new indexUserGUI(loginGUI.code);
				p.f.setVisible(true);
			}
		});
		f.setVisible(true);
		f.add(contentPane);
		f.setSize(700, 740);
		f.setResizable(false);
	}
	public void display() {
		accountList = accBLL.getAllAccount("Khách hàng");
		int i = 0;
		while(i < accountList.size()) {
			Account p = accountList.get(i);
			if(p.getAccountName().equals(loginGUI.accountName)) {
				tfAccountName.setText(p.getUserName());
				tfPassword.setText(p.getPassword());
				tfAddress.setText(p.getAddress());
				tfBirthday.setText(p.getBirthday());
				tfNumberPhone.setText(p.getNumberPhone());
			}
			i++;
		}
	}
}