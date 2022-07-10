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
import java.io.File;
import java.sql.ResultSet;
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
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import BLL.productBLL;
import Dungchung.Tester;
import Product.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class productGUI extends JFrame {

	List<Product> productList = new ArrayList<Product>();
	productBLL proBBL = new productBLL();
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfProductName;
	private JTextField tfImportPrice;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JTextField tfFind;
	JButton btnChooseImage;
	JLabel lImage , lbImage;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	JComboBox cbProducer;
	List<String> producerList = proBBL.getSupplierList();
	JComboBox  cbSupplier;
	List<String> supplierList = proBBL.getSupplierList();
	private JTextField tfImage;
	public productGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lí sản phẩm");
		setSize(1366, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel.setBounds(-11, 59, 336, 652);
		contentPane.add(panel);
		panel.setLayout(null);
		f.setResizable(false);
		JLabel lCode = new JLabel("Mã sản phẩm");
		lCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lCode.setForeground(new Color(0, 0, 0));
		lCode.setBounds(25, 10, 91, 28);
		panel.add(lCode);
		
		tfCode = new JTextField();
		tfCode.setBounds(126, 12, 186, 28);
		panel.add(tfCode);
		tfCode.setColumns(10);
		
		JLabel lProductName = new JLabel("Tên sản phẩm");
		lProductName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductName.setForeground(new Color(0, 0, 0));
		lProductName.setBounds(25, 48, 106, 28);
		panel.add(lProductName);
		
		JLabel lProducer = new JLabel("Hãng");
		lProducer.setForeground(new Color(0, 0, 0));
		lProducer.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProducer.setBounds(25, 88, 69, 28);
		panel.add(lProducer);
		
		tfProductName = new JTextField();
		tfProductName.setBounds(126, 50, 186, 28);
		panel.add(tfProductName);
		tfProductName.setColumns(10);
		
		cbProducer = new JComboBox(producerList.toArray());
		cbProducer.setBounds(126, 89, 186, 28);
		panel.add(cbProducer);
		
		JLabel lbGiaNhap = new JLabel("Giá nhập");
		lbGiaNhap.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbGiaNhap.setForeground(new Color(0, 0, 0));
		lbGiaNhap.setBounds(25, 127, 55, 28);
		panel.add(lbGiaNhap);
		
		tfImportPrice = new JTextField();
		tfImportPrice.setBounds(126, 127, 186, 28);
		panel.add(tfImportPrice);
		tfImportPrice.setColumns(10);
		
		JLabel lPrice = new JLabel("Giá bán");
		lPrice.setForeground(new Color(0, 0, 0));
		lPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPrice.setBounds(25, 175, 55, 28);
		panel.add(lPrice);
		
		JLabel lSupplier = new JLabel("Nhà cung cấp");
		lSupplier.setFont(new Font("SansSerif", Font.BOLD, 12));
		lSupplier.setForeground(new Color(0, 0, 0));
		lSupplier.setBackground(Color.BLACK);
		lSupplier.setBounds(27, 224, 101, 28);
		panel.add(lSupplier);
		
		JLabel lAmount = new JLabel("Số lượng");
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setBackground(Color.BLACK);
		lAmount.setBounds(25, 269, 55, 28);
		panel.add(lAmount);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(126, 177, 186, 28);
		panel.add(tfPrice);
		tfPrice.setColumns(10);
		
		cbSupplier = new JComboBox(supplierList.toArray());
		cbSupplier.setBounds(126, 225, 186, 28);
		panel.add(cbSupplier);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(126, 271, 186, 28);
		panel.add(tfAmount);
		tfAmount.setColumns(10);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(187, 594, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(266, 594, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(10, 594, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(90, 594, 75, 28);
		panel.add(btnReset);
		
		lImage = new JLabel("");
		lImage.setBounds(64, 358, 248, 226);
		panel.add(lImage);
		
		tfImage = new JTextField("Image\\\\Product\\\\");
		tfImage.setColumns(10);
		tfImage.setBounds(126, 320, 186, 28);
		panel.add(tfImage);
		tfImage.setVisible(false);

		
		lbImage = new JLabel("File ảnh");
		lbImage.setForeground(Color.BLACK);
		lbImage.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbImage.setBackground(Color.BLACK);
		lbImage.setBounds(25, 318, 55, 28);
		panel.add(lbImage);
		
		btnChooseImage = new JButton("Chọn file");
		btnChooseImage.setBounds(126, 320, 100, 28);
		panel.add(btnChooseImage);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 59, 1036, 652);
		contentPane.add(scrollPane);
	    btnChooseImage.addActionListener(new ActionListener(){
	        @Override
	     public void actionPerformed(ActionEvent e){
	         JFileChooser fileChooser = new JFileChooser();
	         fileChooser.setCurrentDirectory(new File("Image/Product"));
	         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
	         fileChooser.addChoosableFileFilter(filter);
	         int result = fileChooser.showSaveDialog(null);
	         if(result == JFileChooser.APPROVE_OPTION){
	        	btnChooseImage.setVisible(false);
	        	tfImage.setVisible(true);
	        	tfImage.setText(fileChooser.getSelectedFile().toString());
	        	lImage.setIcon(new ImageIcon(fileChooser.getSelectedFile().toString()));
	         }
	     }
	    });
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Hãng");
		model.addColumn("Nhà cung cấp");
		model.addColumn("Số Lượng");
		model.addColumn("Giá nhập");
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
		panel1.setBounds(0, 0, 1364, 62);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbTitle = new JLabel("Danh Sách Sản Phẩm");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 387, 36);
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
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
		if(employeeInterfaceGUI.employeeCode!=-1) {
			btnAdd.setEnabled(false);
			btnEdit.setEnabled(false);
			btnRemove.setEnabled(false);
			btnChooseImage.setEnabled(false);
		}
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo tên sản phẩm)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(750, 20, 309, 29);
		panel1.add(lblNewLabel);
	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfCode.setText("");
		tfProductName.setText("");
		cbProducer.setSelectedIndex(0);
		tfImportPrice.setText("");
		tfPrice.setText("");
		tfAmount.setText("");
		cbSupplier.setSelectedIndex(0);
		lImage.setIcon(null);
		tfImage.setVisible(false);
		btnChooseImage.setVisible(true);
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
            tfCode.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfProductName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		cbProducer.setSelectedItem(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		cbSupplier.setSelectedItem(String.valueOf(model.getValueAt(selectedIndex, 4)));
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 5)));
    		tfImportPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
    		tfPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
            productList=proBBL.getAllProduct();
            Product p = productList.get(selectedIndex);
    		tfImage.setText(p.getImage());
        }
	        List<Product> pro = new ArrayList<Product>();
	        pro = proBBL.getAllProduct();
	        for(int i=0;i<pro.size();i++) {
	        	Product p1=pro.get(i);
	        	if(p1.getProductCode().equals(tfCode.getText())) {
	        		lImage.setIcon(new ImageIcon(p1.getImage()));
	        	}
	        }			
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfCode.getText().trim().equals("") && !tfProductName.getText().trim().equals("") && !tfAmount.getText().trim().equals("") && !tfImportPrice.getText().trim().equals("") && !tfPrice.getText().trim().equals("") && !tfImage.getText().trim().equals("")) {
			try {
				String code = tfCode.getText();
				String name = tfProductName.getText();
				String producer = cbProducer.getSelectedItem().toString();
				String supplier = cbSupplier.getSelectedItem().toString();
				int amount = Integer.parseInt(tfAmount.getText());
				int importPrice = Integer.parseInt(tfImportPrice.getText());
				int price = Integer.parseInt(tfPrice.getText());
				String image = tfImage.getText();
				
				Product p = new Product(code, name, producer, supplier, amount, importPrice, price,image);
				
				JOptionPane.showMessageDialog(null, proBBL.addProduct(p));
				
				displayList();
				btnResetActionPerformed(e);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của sản phẩm");
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
	    					model.getRowCount()+1, p.getProductCode(), p.getProductName(), p.getProducerName(), p.getSupplierName(), p.getAmount(), Tester.format(p.getImportPrice()), Tester.format(p.getPrice())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của sản phẩm để tìm kiếm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(index>=0 && !tfCode.getText().equals("") && !tfProductName.getText().equals("") && !tfImportPrice.getText().equals("") && !tfPrice.getText().equals("") && !tfAmount.getText().equals("")&& !tfImage.getText().trim().equals("")) {
				Product p = new Product();
				
				p.setProductCode(tfCode.getText());
				p.setProductName(tfProductName.getText());
				p.setProducerName(cbProducer.getSelectedItem().toString());
				p.setSupplierName(cbSupplier.getSelectedItem().toString());
				p.setAmount(Integer.parseInt(tfAmount.getText()));
				p.setImportPrice(Tester.formatStringToNumber(tfImportPrice.getText()));
				p.setPrice(Tester.formatStringToNumber(tfPrice.getText()));
				p.setImage(tfImage.getText());
				
				JOptionPane.showMessageDialog(null, proBBL.editProduct(p));
				displayList();
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của sản phẩm");
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Giá bán hoặc giá nhập hoặc số lượng phải là số");
		} 
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfCode.getText().equals("")) {
        	String code = tfCode.getText();
//            Product p = productList.get(selectedIndex);
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, proBBL.deleteProduct(code));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa");
        }
	}
	
	private void displayList() {
		model.setRowCount(0);
		productList = proBBL.getAllProduct();
    	int i = 0;
		while(i < productList.size()) {
			Product p = productList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getProductCode(), p.getProductName(), p.getProducerName(), p.getSupplierName(), p.getAmount(), Tester.format(p.getImportPrice()), Tester.format(p.getPrice())
			});
			i++;
		}
		
	}

}