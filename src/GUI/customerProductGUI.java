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
import Dungchung.Tester;
import Product.*;
import userService.Cart;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class customerProductGUI extends JFrame {

	public String userCode = "1";
	List<Product> productList = new ArrayList<Product>();
	productBLL proBBL = new productBLL();
	CustomerBLL cusBLL = new CustomerBLL();
	
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfProductName;
	private JTextField tfSupplier;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JTextField tfFind;
	private JLabel lImage;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JTextField tfProductCode;
	public customerProductGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Danh sách sản phẩm");
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
		panel.setBounds(0, 70, 385, 641);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lProductName = new JLabel("Tên sản phẩm");
		lProductName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductName.setForeground(new Color(0, 0, 0));
		lProductName.setBounds(24, 102, 115, 28);
		panel.add(lProductName);
		
		tfProductName = new JTextField();
		tfProductName.setBounds(150, 104, 209, 28);
		panel.add(tfProductName);
		tfProductName.setColumns(10);
		
		JLabel lSuppiler = new JLabel("Hãng:");
		lSuppiler.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSuppiler.setForeground(new Color(0, 0, 0));
		lSuppiler.setBounds(24, 148, 43, 28);
		panel.add(lSuppiler);
		
		tfSupplier = new JTextField();
		tfSupplier.setBounds(150, 150, 209, 28);
		panel.add(tfSupplier);
		tfSupplier.setColumns(10);
		f.setResizable(false);
		JLabel lAmount = new JLabel("Số lượng mua");
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setBounds(24, 245, 101, 28);
		panel.add(lAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(150, 247, 209, 28);
		panel.add(tfAmount);
		tfAmount.setColumns(10);
		
		lImage = new JLabel("");
		lImage.setBounds(80, 294, 258, 226);
		panel.add(lImage);
		JLabel lPrice = new JLabel("Giá bán");
		lPrice.setForeground(new Color(0, 0, 0));
		lPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPrice.setBounds(25, 202, 209, 28);
		panel.add(lPrice);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(150, 204, 209, 28);
		panel.add(tfPrice);
		tfPrice.setColumns(10);

		
		JButton btnBuy = new JButton("Thêm vào giỏ hàng");
		btnBuy.setIcon(new ImageIcon("Image\\cart-2-1-32.png"));
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnBuyActionPerformed(e);			}
		});
		
		btnBuy.setBounds(88, 531, 200, 65);
		panel.add(btnBuy);
		
		JLabel lProductCode = new JLabel("Mã sản phẩm");
		lProductCode.setForeground(new Color(0, 0, 0));
		lProductCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductCode.setBounds(23, 45, 115, 28);
		panel.add(lProductCode);
		
		tfProductCode = new JTextField();
		tfProductCode.setColumns(10);
		tfProductCode.setBounds(148, 47, 209, 28);
		panel.add(tfProductCode);



		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 70, 978, 641);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Hãng");
		model.addColumn("Số Lượng");
		model.addColumn("Giá bán");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		
		Panel panel1 = new Panel();
		panel1.setBackground(new Color(50, 205, 50));
		panel1.setBounds(0, 0, 1364, 71);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Danh Sách Sản Phẩm");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 443, 36);
		panel1.add(lbTitle);
		
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
				indexUserGUI p = new indexUserGUI(loginGUI.code);
				p.f.setVisible(true);
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo tên sản phẩm)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(750, 20, 309, 29);
		panel1.add(lblNewLabel);
		
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
	}
	

	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
            tfProductCode.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfProductName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfSupplier.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfAmount.setText("1");
    		tfPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 5)));
    		tfProductCode.setEditable(false);
    		tfProductName.setEditable(false);
    		tfSupplier.setEditable(false);
    		tfPrice.setEditable(false);
        }
		productList = proBBL.getAllProduct();
		Product p = productList.get(selectedIndex);
		lImage.setIcon(new ImageIcon(p.getImage()));
	}
	
	private void btnBuyActionPerformed(ActionEvent e) {
		if(!tfAmount.getText().trim().equals("") && Integer.parseInt(tfAmount.getText())>=0 && tfAmount.getText().toString().length()<10) {
			try {
				int cartCode = 0;
				String productCode = tfProductCode.getText();
				String productName = tfProductName.getText();
				int amount = Integer.parseInt(tfAmount.getText());
				int price = Tester.formatStringToNumber(tfPrice.getText());
				int total = 0;
				Cart p = new Cart(cartCode, productCode, productName, amount, price, total);
				JOptionPane.showMessageDialog(null, cusBLL.addCart(p, indexUserGUI.userCode));
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Số lượng của sản phẩm muốn mua phải là số");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn số lượng phù hợp của sản phẩm để thêm vào giỏ hàng.");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String productName = tfFind.getText();
        if(productName != null && productName.length() > 0) {
            productList = proBBL.searchProductByName(productName);
            
            if(productList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có sản phẩm bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < productList.size()) {
	    			Product p = productList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getProductCode(), p.getProductName(), p.getProducerName(),p.getAmount(), Tester.format(p.getPrice())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của sản phẩm để tìm kiếm");
        }
	}
	
	
	private void displayList() {
		model.setRowCount(0);
		productList = proBBL.getAllProduct();
    	int i = 0;
		while(i < productList.size()) {
			Product p = productList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getProductCode(), p.getProductName(), p.getProducerName(), p.getAmount(), Tester.format(p.getPrice())
			});
			i++;
		}
		
	}
}