package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.*;

import BLL.billBLL;
import Bill.*;
import Dungchung.Tester;
import database.databaseHelp;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import java.awt.Panel;

public class billGUI extends JFrame {
	
	// tạo con trỏ tới hóa đơn chi tiết
	
	List<Bill> billList = new ArrayList<Bill>();
	billBLL billBLL = new billBLL();
	databaseHelp dh = new databaseHelp();
	
	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfbillDate;
	private JTextField tfTotal;
	private JTextField tfAmount;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	public JFrame f = new JFrame("Quản lí phiếu xuất");
	
	public billGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lí phiếu xuất");
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
		panel.setBounds(0, 108, 314, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
		
//		JLabel lCode = new JLabel("Mã phiếu");
//		lCode.setFont(new Font("SansSerif", Font.BOLD, 12));
//		lCode.setForeground(Color.WHITE);
//		lCode.setBounds(25, 42, 62, 28);
//		panel.add(lCode);
//		
//		tfCode = new JTextField();
//		tfCode.setBounds(108, 42, 186, 28);
//		panel.add(tfCode);
//		tfCode.setColumns(10);
		
		JLabel lbillName = new JLabel("Ngày");
		lbillName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbillName.setForeground(new Color(0, 0, 0));
		lbillName.setBounds(18, 29, 43, 28);
		panel.add(lbillName);
		
		tfbillDate = new JTextField();
		tfbillDate.setBounds(102, 29, 186, 28);
		panel.add(tfbillDate);
		tfbillDate.setColumns(10);
		
		JLabel lPrice = new JLabel("Số lượng");
		lPrice.setForeground(new Color(0, 0, 0));
		lPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
		lPrice.setBounds(14, 80, 55, 28);
		panel.add(lPrice);
		
		JLabel lAmount = new JLabel("Tổng cộng");
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setBackground(Color.BLACK);
		lAmount.setBounds(14, 139, 73, 28);
		panel.add(lAmount);
		
		JLabel lbGhiChu = new JLabel("Ghi chú:");
		lbGhiChu.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbGhiChu.setForeground(new Color(0, 0, 0));
		lbGhiChu.setBounds(14, 195, 55, 28);
		panel.add(lbGhiChu);
		
		tfAmount = new JTextField();
		tfAmount.setEnabled(false);
		tfAmount.setBounds(97, 82, 186, 28);
		panel.add(tfAmount);
		tfAmount.setColumns(10);
		
		tfTotal = new JTextField();
		tfTotal.setEnabled(false);
		tfTotal.setBounds(97, 141, 191, 28);
		panel.add(tfTotal);
		tfTotal.setColumns(10);
		f.setResizable(false);
		JButton btnDetails = new JButton("Xem chi tiết hóa đơn");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailsActionPerformed(e);
			}
		});
		btnDetails.setBounds(32, 394, 220, 28);
		panel.add(btnDetails);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setBounds(164, 444, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(237, 444, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(10, 444, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(79, 444, 75, 28);
		panel.add(btnReset);
		
//		JLabel lbTrangThai = new JLabel("Tr\u1EA1ng th\u00E1i:");
//		lbTrangThai.setFont(new Font("SansSerif", Font.BOLD, 12));
//		lbTrangThai.setForeground(Color.WHITE);
//		lbTrangThai.setBounds(14, 503, 75, 16);
//		panel.add(lbTrangThai);
//		
//		JRadioButton rdHien = new JRadioButton("Hi\u1EC7n");
//		rdHien.setBackground(new Color(51, 153, 102));
//		rdHien.setFont(new Font("SansSerif", Font.BOLD, 12));
//		rdHien.setForeground(Color.WHITE);
//		rdHien.setBounds(114, 502, 60, 18);
//		panel.add(rdHien);
//		
//		JRadioButton rdAn = new JRadioButton("\u1EA8n");
//		rdAn.setBackground(new Color(51, 153, 102));
//		rdAn.setFont(new Font("SansSerif", Font.BOLD, 12));
//		rdAn.setForeground(Color.WHITE);
//		rdAn.setBounds(214, 502, 44, 18);
//		panel.add(rdAn);
		
		TextArea txtGhiChu = new TextArea();
		txtGhiChu.setBounds(98, 192, 186, 126);
		panel.add(txtGhiChu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 108, 1047, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Ngày xuất");
		model.addColumn("Số lượng");
		model.addColumn("Tổng cộng");
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
		
		JLabel lbTitle = new JLabel("Danh Sách Phiếu Xuất");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 442, 63);
		panel1.add(lbTitle);
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 38);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(934, 12, 319, 38);
		panel1.add(tfFind);
		tfFind.setColumns(10);
		JButton btnTrLi = new JButton("");
		btnTrLi.setBounds(0, 0, 50, 50);
		panel1.add(btnTrLi);
		btnTrLi.setBackground(new Color(50, 205, 50));
		btnTrLi.setIcon(new ImageIcon("Image\\return-24-48.png"));
		
		JLabel lblNewLabel = new JLabel("(Tìm kiếm theo ngày)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1013, 60, 309, 29);
		panel1.add(lblNewLabel);
		
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
		btnEdit.setEnabled(false);
	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfbillDate.setText("");
		tfAmount.setText("");
		tfTotal.setText("");
		tfTotal.setEditable(true);
		tfAmount.setEditable(true);
		tfbillDate.setEditable(true);
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
    		tfbillDate.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfTotal.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfTotal.setEditable(false);
    		tfAmount.setEditable(false);
    		tfbillDate.setEditable(false);
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfbillDate.getText().trim().equals("")) {
			try {
				String date = tfbillDate.getText();
				int amount = 0;
				int total = 0;
				
				Bill p = new Bill(date, amount, total);
				if(!Tester.day(date)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của ngày");
					return;
				}
				
				JOptionPane.showMessageDialog(null, billBLL.addBill(p));
				
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
		String billDate = tfFind.getText();
        if(billDate != null && billDate.length() > 0) {
            billList = billBLL.searchBillByDate(billDate);
            
            if(billList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có phiếu xuất bạn cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < billList.size()) {
	    			Bill p = billList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getBillDate(), p.getAmount(), Tester.format(p.getTotal())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của phiếu xuất để tìm kiếm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			int index = table.getSelectedRow();
			if(index>=0 && !tfbillDate.getText().equals("")) {
				Bill p = new Bill();
				
				p.setBillDate(tfbillDate.getText());
				// chỉ cho sửa mã nhân viên
				
				JOptionPane.showMessageDialog(null, billBLL.editBill(p));
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
        if(selectedIndex >= 0 && !tfbillDate.getText().equals("")) {
        	String code = tfbillDate.getText();
//            bill p = billList.get(selectedIndex);
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu xuất này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu xuất này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, billBLL.deleteBill(code));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu xuất để xóa");
        }
	}
	public static String date = null;
	public void btnDetailsActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
		if(selectedIndex>=0 && !tfbillDate.getText().trim().equals("")) {
			date = tfbillDate.getText();
			f.setVisible(false);
			billDetailsGUI bd = new billDetailsGUI();
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu xuất để xem chi tiết phiếu xuất!!!!!");
		}
	}
	
	private void displayList() {
		table.setFillsViewportHeight(true); 
		
		model.setRowCount(0);
		billList = billBLL.getAllBill();
    	int i = 0;
		while(i < billList.size()) {
			Bill p = billList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getBillDate(), p.getAmount(), Tester.format(p.getTotal())
			});
			i++;
		}
		
	}
}