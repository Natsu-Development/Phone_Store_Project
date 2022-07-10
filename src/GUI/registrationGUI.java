package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Account.Account;
import BLL.accountBLL;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import GUI.loginGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Dungchung.*;

public class registrationGUI extends JFrame {
	accountBLL accBLL = new accountBLL();
	public JFrame f=new JFrame();
	private JPanel contentPane;
	private JTextField tfAccountName;
	private JPasswordField tfPass;
	private JPasswordField tfPass1;
	private JTextField tfUsername;
	private JTextField tfAddress;
	private JTextField tfBirthday;
	private JTextField tfNumberPhone;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public registrationGUI() {
		setTitle("\u0110\u0103ng k\u00FD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370, 150, 727, 898);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(371, 21, 97, 25);
		contentPane.add(lblNewLabel);
		
		tfAccountName = new JTextField();
		tfAccountName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}); 
		tfAccountName.setBounds(371, 46, 330, 31);
		contentPane.add(tfAccountName);
		tfAccountName.setColumns(10);
		
		tfPass = new JPasswordField();
		tfPass.setColumns(10);
		tfPass.setBounds(371, 113, 330, 31);
		contentPane.add(tfPass);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u:");
		lblMtKhu.setForeground(Color.WHITE);
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMtKhu.setBounds(371, 88, 67, 25);
		contentPane.add(lblMtKhu);
		
		tfPass1 = new JPasswordField();
		tfPass1.setColumns(10);
		tfPass1.setBounds(371, 188, 330, 31);
		contentPane.add(tfPass1);
		
		JLabel lblNhpLiMt = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u:");
		lblNhpLiMt.setForeground(Color.WHITE);
		lblNhpLiMt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhpLiMt.setBounds(371, 163, 119, 25);
		contentPane.add(lblNhpLiMt);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(371, 254, 330, 31);
		contentPane.add(tfUsername);
		
		JLabel lblSinThoi = new JLabel("Tên người dùng");
		lblSinThoi.setForeground(Color.WHITE);
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSinThoi.setBounds(371, 229, 129, 25);
		contentPane.add(lblSinThoi);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng k\u00FD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfAccountName.getText().equals("") || tfPass.getPassword().equals("") ||  tfPass1.getPassword().equals("")|| tfUsername.getText().equals("")|| tfAddress.getText().equals("") || tfNumberPhone.getText().equals("") || tfBirthday.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin đăng kí");
				}
				
				else if(checkSpace(tfAccountName.getText())) {
					JOptionPane.showMessageDialog(null, "Tài khoản không được chứa khoảng trắng");
				}
				
				else if(!tfPass.getText().equals(tfPass1.getText())) {
					JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không trùng với mật khẩu đã đặt");
				}
				
				else if(tfPass.getPassword().length<6) {
					JOptionPane.showMessageDialog(null, "Mật khẩu phải ít nhất 6 kí tự");
				}
				
				else if(!Tester.day(tfBirthday.getText())) {
					JOptionPane.showMessageDialog(null, "Ngày sinh không đúng định dạng. Vui lòng nhập đúng định dạng của ngày");
				}
				
				else if(!Tester.numberPhone(tfNumberPhone.getText())) {
					JOptionPane.showMessageDialog(null, "Số điện thoại phải bao gồm 10 hoặc 11 chữ số");
				}
				else {
					int id = accBLL.getAccountCode();
					String accountName = tfAccountName.getText();
					String password = tfPass.getText();
					String permission = "Khách hàng";
					String userName = tfUsername.getText();
					String address = tfAddress.getText();
					String numberPhone = tfNumberPhone.getText();
					String birthday = tfBirthday.getText();
					
					Account p = new Account(id, accountName,password, permission, userName, address, numberPhone, birthday);
					
					accBLL.addAccount(p);
					
					JOptionPane.showMessageDialog(null, "Đăng kí thành công");
					f.setVisible(false);
					loginGUI l = new loginGUI();
				}	
				
			}
		});
		
		
		
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(371, 541, 330, 39);
		contentPane.add(btnNewButton);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(371, 321, 330, 31);
		contentPane.add(tfAddress);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblaCh.setForeground(Color.WHITE);
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblaCh.setBounds(371, 296, 46, 25);
		contentPane.add(lblaCh);
		
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginGUI login=new loginGUI();
				f.setVisible(false);
			}
		});
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDangNhap.setBackground(new Color(30, 144, 255));
		btnDangNhap.setBounds(371, 591, 330, 39);
		contentPane.add(btnDangNhap);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 361, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("Image\\office-background-design_1300-381.jpg"));
		lblNewLabel_1.setBounds(10, 0, 336, 655);
		panel.add(lblNewLabel_1);
		
		JLabel lblTnTiKhon = new JLabel("Ngày sinh");
		lblTnTiKhon.setForeground(Color.WHITE);
		lblTnTiKhon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnTiKhon.setBounds(371, 372, 119, 25);
		contentPane.add(lblTnTiKhon);
		
		tfBirthday = new JTextField();
		tfBirthday.setColumns(10);
		tfBirthday.setBounds(371, 405, 330, 31);
		contentPane.add(tfBirthday);
		
		JLabel lblSinThoi_1 = new JLabel("Số điện thoại");
		lblSinThoi_1.setForeground(Color.WHITE);
		lblSinThoi_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSinThoi_1.setBounds(371, 449, 119, 25);
		contentPane.add(lblSinThoi_1);
		
		tfNumberPhone = new JTextField();
		tfNumberPhone.setColumns(10);
		tfNumberPhone.setBounds(371, 482, 330, 31);
		contentPane.add(tfNumberPhone);
		f.add(contentPane);
		f.setBounds(370, 150, 730, 690);
		f.setVisible(true);
		f.setResizable(true);
	}
	private boolean checkSpace(String user) {
		char check;
		for(int i=0; i<user.length(); i++) {
			check = user.charAt(i);
			if(Character.isSpaceChar(check)) {
				return true;
			}
		}
		return false;
	}
}
