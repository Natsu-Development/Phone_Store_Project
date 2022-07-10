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

public class employeeInterfaceGUI extends JFrame {
	private JPanel contentPane;
	public JFrame f = new JFrame();
	accountBLL accBLL = new accountBLL();
	
	public static int employeeCode = -1;
	public employeeInterfaceGUI(String code) {
		employeeCode = Integer.parseInt(code);
		initComponent();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Trang admin");
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
		panel.setBounds(0, 139, 306, 248);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProduct = new JButton("Xem danh sách sản phẩm");
		btnProduct.setIcon(new ImageIcon("Image\\phone-123-32.png"));
		btnProduct.setForeground(Color.BLACK);
		btnProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProduct.setBackground(new Color(230, 230, 250));
		btnProduct.setBounds(0, 63, 306, 64);
		panel.add(btnProduct);
		
		btnProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				productGUI u = new productGUI();
				u.f.setVisible(true);
			}
		} );
		
		JButton btnAccount = new JButton("Danh sách tài khoản");
		btnAccount.setIcon(new ImageIcon("Image\\account-25-32.png"));
		btnAccount.setForeground(Color.BLACK);
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAccount.setBackground(new Color(230, 230, 250));
		btnAccount.setBounds(0, 126, 306, 64);
		panel.add(btnAccount);
		
		btnAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				accountGUI u = new accountGUI();
				u.f.setVisible(true);
			}
		} );
		
		JButton btnBill = new JButton("Tạo hóa đơn bán hàng");
		btnBill.setIcon(new ImageIcon("Image\\paper-6-32.png"));
		btnBill.setForeground(Color.BLACK);
		btnBill.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBill.setBackground(new Color(230, 230, 250));
		btnBill.setBounds(0, 0, 306, 64);
		panel.add(btnBill);
		
		JButton btnXcNhnn = new JButton("Xác nhận đơn hàng");
		btnXcNhnn.setIcon(new ImageIcon("Image\\bill-12-32.png"));
		btnXcNhnn.setBounds(0, 184, 306, 64);
		panel.add(btnXcNhnn);
		btnXcNhnn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				confirmOrder u = new confirmOrder();
				u.f.setVisible(true);
			}
		});
		btnXcNhnn.setForeground(Color.BLACK);
		btnXcNhnn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXcNhnn.setBackground(new Color(230, 230, 250));
		
		btnBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				billDetailsGUI u = new billDetailsGUI();
//				u.f.setVisible(true);
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
		
		JLabel lblNewLabel_1 = new JLabel("Chào NHÂN VIÊN");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblNewLabel_1.setBounds(616, 141, 300, 111);
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
		lblNewLabel_2.setIcon(new ImageIcon("Image\\worker-3-256.png"));
		lblNewLabel_2.setBounds(616, 262, 300, 300);
		contentPane.add(lblNewLabel_2);
	
		f.add(contentPane);
		f.setSize(1300, 662);
		f.setVisible(true);
		f.setResizable(false);
	}
}