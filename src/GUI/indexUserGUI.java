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

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;
import GUI.*;

public class indexUserGUI extends JFrame {
	private JPanel contentPane;
	public JFrame f = new JFrame();
	accountBLL accBLL = new accountBLL();
	
	static int userCode = -1; 
	public indexUserGUI(String code) {
		userCode = Integer.parseInt(code);
		initComponent();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Trang user");
		setSize(1300, 662);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 139, 277, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		f.setResizable(false);
		JButton btnOrder = new JButton("Đơn hàng của tôi");
		btnOrder.setIcon(new ImageIcon("Image\\bill-12-32.png"));
		btnOrder.setForeground(Color.BLACK);
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrder.setBackground(new Color(230, 230, 250));
		btnOrder.setBounds(0, 120, 277, 64);
		panel.add(btnOrder);
		
		btnOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				orderGUI u = new orderGUI();
				u.f.setVisible(true);
			}
		} );
		
		JButton btnProductList = new JButton("Danh sách sản phẩm");
		btnProductList.setIcon(new ImageIcon("Image\\paper-6-32.png"));
		btnProductList.setForeground(Color.BLACK);
		btnProductList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProductList.setBackground(new Color(230, 230, 250));
		btnProductList.setBounds(0, 0, 277, 64);
		panel.add(btnProductList);
		
		btnProductList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				customerProductGUI u = new customerProductGUI();
				u.f.setVisible(true);
			}
		} );
		
		JButton btnCart = new JButton("Giỏ hàng của tôi");
		btnCart.setIcon(new ImageIcon("Image\\cart-2-1-32.png"));
		btnCart.setForeground(Color.BLACK);
		btnCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCart.setBackground(new Color(230, 230, 250));
		btnCart.setBounds(0, 60, 277, 64);
		panel.add(btnCart);
		
		btnCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				cartGUI u = new cartGUI();
				u.f.setVisible(true);
			}
		} );
		
		JButton btnMyAccount = new JButton("Quản lí tài khoản");
		btnMyAccount.setIcon(new ImageIcon("Image\\account-25-32.png"));
		btnMyAccount.setForeground(Color.BLACK);
		btnMyAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMyAccount.setBackground(new Color(230, 230, 250));
		btnMyAccount.setBounds(0, 180, 277, 64);
		panel.add(btnMyAccount);
		
		btnMyAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				customerAccountGUI u = new customerAccountGUI();
				u.f.setVisible(true);
			}
		} );
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1286, 140);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblCaHngin = new JLabel("Cửa hàng điện thoại di động BCĐ");
		lblCaHngin.setBounds(342, 26, 807, 91);
		panel1.add(lblCaHngin);
		lblCaHngin.setForeground(new Color(240, 248, 255));
		lblCaHngin.setFont(new Font("SansSerif", Font.BOLD, 50));
		lblCaHngin.setBackground(new Color(175, 238, 238));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Image\\shop-27-128.png"));
		lblNewLabel.setBounds(112, 6, 128, 128);
		panel1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chào Bạn");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblNewLabel_1.setBounds(646, 139, 226, 111);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Đăng xuất");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnNewButton.setIcon(new ImageIcon("Image\\logout-7-32.png"));
		btnNewButton.setBounds(1107, 148, 173, 40);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
				loginGUI u = new loginGUI();
				u.f.setVisible(true);
			}
		});
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("Image\\king.png"));
		lblNewLabel_2.setBounds(616, 262, 300, 300);
		contentPane.add(lblNewLabel_2);
		f.add(contentPane);
		f.setSize(1300, 662);
		f.setVisible(true);
		f.setResizable(false);
	}
}