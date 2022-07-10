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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import BLL.billDetailsBLL;
import BLL.productBLL;
import Bill.BillDetails;
import Dungchung.Tester;
import Product.*;
import userService.Cart;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Panel;

public class confirmOrder extends JFrame {
	List<BillDetails> orderList = new ArrayList<BillDetails>();
	
	billDetailsBLL billDetailsBLL = new billDetailsBLL();
	
	public JFrame f = new JFrame();
	private JPanel contentPane;
	private JTextField tfProductName;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JTextField tfFind;
//	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model = new DefaultTableModel() {
		   public Class<?> getColumnClass(int column) {
		    switch (column) {
		    case 0:
		     return Integer.class;
		    case 1: 
		     return String.class;
		    case 2:
		     return String.class;
		    case 3:
		     return String.class;
		    case 4:
		     return String.class;
		    case 5:
		     return String.class;
		    case 6:
		    	 return String.class;
		    case 7:
		    	return String.class;
		    case 8:
		    	return Boolean.class;
		    default:
		     return String.class;
		    }
		   }
	 };
	
	
	private JTable table;
	private JTextField tfTotal;
	private JCheckBox c = new JCheckBox();
	private float sum =0;
	private JTextField tfCusName;
	private JTextField tfDateOrder;
	public confirmOrder() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Giỏ hàng của tôi");
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
		panel.setBounds(0, 108, 385, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lProductName = new JLabel("Tên sản phẩm");
		lProductName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductName.setForeground(new Color(0, 0, 0));
		lProductName.setBounds(25, 42, 115, 28);
		panel.add(lProductName);
		
		tfProductName = new JTextField();
		tfProductName.setBounds(150, 44, 209, 28);
		panel.add(tfProductName);
		tfProductName.setColumns(10);
		
		JLabel lAmount = new JLabel("Số lượng");
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setBounds(25, 106, 69, 28);
		panel.add(lAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(150, 108, 209, 28);
		panel.add(tfAmount);
		tfAmount.setColumns(10);
		
		f.setResizable(false);
		JLabel lPrice = new JLabel("Giá bán");
		lPrice.setForeground(new Color(0, 0, 0));
		lPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPrice.setBounds(25, 163, 209, 28);
		panel.add(lPrice);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(150, 165, 209, 28);
		panel.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lTotal = new JLabel("Thành tiền");
		lTotal.setForeground(new Color(0, 0, 0));
		lTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lTotal.setBounds(25, 221, 209, 28);
		panel.add(lTotal);
		
		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(150, 221, 209, 28);
		panel.add(tfTotal);
		
		JButton btnConfirm = new JButton("Xác nhận đơn hàng");
		btnConfirm.setIcon(new ImageIcon("Image\\refresh-54-32.png"));
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirm.setBounds(82, 436, 227, 50);
		panel.add(btnConfirm);
		
		JLabel lCartCode = new JLabel("Tên khách hàng");
		lCartCode.setForeground(new Color(0, 0, 0));
		lCartCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lCartCode.setBounds(25, 277, 209, 28);
		panel.add(lCartCode);
		
		tfCusName = new JTextField();
		tfCusName.setColumns(10);
		tfCusName.setBounds(150, 277, 209, 28);
		panel.add(tfCusName);
		
		JLabel lProductCode = new JLabel("Ngày đặt hàng");
		lProductCode.setForeground(new Color(0, 0, 0));
		lProductCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		lProductCode.setBounds(25, 334, 209, 28);
		panel.add(lProductCode);
		
		tfDateOrder = new JTextField();
		tfDateOrder.setColumns(10);
		tfDateOrder.setBounds(150, 336, 209, 28);
		panel.add(tfDateOrder);
		
		JButton btnCancel = new JButton("Hủy đơn hàng");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(82, 520, 227, 50);
		btnCancel.setIcon(new ImageIcon("Image\\cancel-20-32.png"));
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDeleteActionPerformed(e);
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnConfirmActionPerformed(e);
			}
		});


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 108, 978, 569);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Mã đơn hàng");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Số Lượng");
		model.addColumn("Giá bán");
		model.addColumn("Thành tiền");
		model.addColumn("Mã khách hàng");
		model.addColumn("Ngày đặt hàng");
		model.addColumn("Chọn");
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
		
		JLabel lbTitle = new JLabel("Xác nhận đơn hàng");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 329, 36);
		panel1.add(lbTitle);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 28);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(1010, 12, 243, 28);
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
				employeeInterfaceGUI p = new employeeInterfaceGUI(loginGUI.code);
				p.f.setVisible(true);
			}
		});
		
		JButton btnChooseFull = new JButton("Chọn tất cả");
		btnChooseFull.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChooseFull.setBounds(1217, 676, 143, 27);
		contentPane.add(btnChooseFull);
		btnChooseFull.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectAll(e);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo mã khách hàng)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
		
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
	}
	

	private void selectAll(ActionEvent e) {
		for(int i=0;i<table.getRowCount();i++) {
			model.setValueAt(true, i, 8);
		}
	}
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {    
    		tfProductName.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfPrice.setText(String.valueOf(model.getValueAt(selectedIndex, 4)));
    		tfTotal.setText(String.valueOf(model.getValueAt(selectedIndex, 5)));
    		tfCusName.setText(String.valueOf(model.getValueAt(selectedIndex, 6)));
    		tfDateOrder.setText(String.valueOf(model.getValueAt(selectedIndex, 7)));
    		
    		tfProductName.setEditable(false); 
    		tfPrice.setEditable(false);
    		tfTotal.setEditable(false);
    		tfDateOrder.setEditable(false);
    		tfCusName.setEditable(false);
    	}
	}
	
	private void btnConfirmActionPerformed(ActionEvent e) {
		int flag = 0;
		String check = "";
		for(int i=0;i<table.getRowCount();i++)
        {
	      // lấy giá trị của hàng đó ra 
	      int billDetailCode = Integer.parseInt(table.getValueAt(i, 1).toString());
	      String billDate = table.getValueAt(i, 7).toString();
	      
		  // xem cái nào được chọn
	      Boolean checked=Boolean.valueOf(table.getValueAt(i, 8).toString());
	      //DISPLAY
	      if(checked)
	      {
	    	  BillDetails p = new BillDetails(billDetailCode, billDate, employeeInterfaceGUI.employeeCode);
	    	  check = billDetailsBLL.confirmOrder(p);
	    	  flag = 1;
	      }
        }
		if(flag==0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn hàng chưa xác nhận để xác nhận");
		}
		// 
		if(flag==1) {
			JOptionPane.showMessageDialog(null, check);
			displayList();
		}
	}
	
	private void btnDeleteActionPerformed(ActionEvent e) {
		int flag = 0;
		String check = "";
		for(int i=0;i<table.getRowCount();i++)
        {
	      // lấy giá trị của hàng đó ra 
	      int billDetailCode = Integer.parseInt(table.getValueAt(i, 1).toString());
	      String billDate = table.getValueAt(i, 7).toString();
	      
		  // xem cái nào được chọn
	      Boolean checked=Boolean.valueOf(table.getValueAt(i, 8).toString());
	      //DISPLAY
	      if(checked)
	      {
	    	  BillDetails p = new BillDetails(billDetailCode, billDate, employeeInterfaceGUI.employeeCode);
	    	  check = billDetailsBLL.deleteOrderNotConfirmed(p);
	    	  flag = 1;
	      }
        }
		if(flag==0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn hàng chưa xác nhận để hủy");
		}
		// 
		if(flag==1) {
			JOptionPane.showMessageDialog(null, check);
			displayList();
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String cusCode = tfFind.getText();
        if(cusCode != null && cusCode.length() > 0) {
            orderList = billDetailsBLL.searchOrderNotConfirmByCusCode(cusCode);
            
            if(orderList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có đơn hàng bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < orderList.size()) {
	    			BillDetails p = orderList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getBillDetailsCode(), p.getProductCode() ,p.getAmount(), Tester.format(p.getPrice()) , Tester.format(p.getTotal()), p.getCustomerCode(), p.getBillDate()
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
		orderList = billDetailsBLL.getOrderNotConfirmed();
    	int i = 0;
		while(i < orderList.size()) {
			BillDetails p = orderList.get(i);
			model.addRow(new Object [7] );
			model.setValueAt(model.getRowCount(), i, 0);
			model.setValueAt(p.getBillDetailsCode() , i, 1);
			model.setValueAt(p.getProductCode(), i, 2);
			model.setValueAt(p.getAmount(), i, 3);
			model.setValueAt(Tester.format(p.getPrice()), i, 4);
			model.setValueAt(Tester.format(p.getTotal()), i, 5);
			model.setValueAt(p.getCustomerCode(), i, 6);
			model.setValueAt(p.getBillDate(), i, 7);
			model.setValueAt(false, i, 8);
			i++;
		}
	}
	
}