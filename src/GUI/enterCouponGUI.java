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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import BLL.enterCouponBLL;
import BLL.enterCouponDetailsBLL;
import Dungchung.Tester;
import EnterCoupon.EnterCoupon;
import GUI.billGUI.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import java.awt.Panel;
import database.databaseHelp;

public class enterCouponGUI extends JFrame {
	JFrame f = new JFrame("Quản lý phiếu nhập");
	enterCouponDetailsGUI e = new enterCouponDetailsGUI();
	List<EnterCoupon> EnterCouponList = new ArrayList<EnterCoupon>();
	enterCouponBLL EnterCouponBLL = new enterCouponBLL();
	databaseHelp dh = new databaseHelp();
	
	Date now = new Date();
	String ft = new SimpleDateFormat("dd/MM/yyyy").format(now);
	String billDate = ft.formatted(now);
	int flag = 0;
	private JPanel contentPane;
	private JTextField tfAmount;
	private JTextField tfEnterCouponDate;
	private JTextField tfTotal;
	private JTextField tfFind;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	
	
	public enterCouponGUI() {
		initComponent();
		displayList();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponent() {
		setTitle("Quản lý phiếu nhập");
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
		panel.setBounds(0, 108, 324, 603);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lEnterCouponDate = new JLabel("Ngày nhập");
		lEnterCouponDate.setFont(new Font("SansSerif", Font.BOLD, 12));
		lEnterCouponDate.setForeground(new Color(0, 0, 0));
		lEnterCouponDate.setBounds(25, 42, 62, 28);
		panel.add(lEnterCouponDate);
		
		tfEnterCouponDate = new JTextField();
		tfEnterCouponDate.setBounds(108, 42, 186, 28);
		panel.add(tfEnterCouponDate);
		tfEnterCouponDate.setColumns(10);
		
		f.setResizable(false);
		JLabel lAmount = new JLabel("Số lượng");
		lAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lAmount.setForeground(new Color(0, 0, 0));
		lAmount.setBounds(25, 91, 84, 28);
		panel.add(lAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(109, 91, 186, 28);
		panel.add(tfAmount);
		tfAmount.setColumns(10);
		tfAmount.setEditable(false);
		
		JLabel lTotal = new JLabel("Tổng cộng");
		lTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lTotal.setForeground(new Color(0, 0, 0));
		lTotal.setBackground(Color.BLACK);
		lTotal.setBounds(26, 143, 73, 28);
		panel.add(lTotal);
		
//		JLabel lbGhiChu = new JLabel("Ghi chú");
//		lbGhiChu.setFont(new Font("SansSerif", Font.BOLD, 12));
//		lbGhiChu.setForeground(new Color(0, 0, 0));
//		lbGhiChu.setBounds(25, 238, 55, 28);
//		panel.add(lbGhiChu);
		
		tfTotal = new JTextField();
		tfTotal.setBounds(109, 144, 185, 28);
		panel.add(tfTotal);
		tfTotal.setColumns(10);
		tfTotal.setEditable(false);
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(173, 363, 69, 28);
		panel.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBounds(247, 363, 69, 28);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveActionPerformed(e);
			}
		});
		btnRemove.setBounds(10, 363, 69, 28);
		panel.add(btnRemove);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(89, 363, 75, 28);
		panel.add(btnReset);
		
//		TextArea txtGhiChu = new TextArea();
//		txtGhiChu.setBounds(108, 238, 186, 126);
//		panel.add(txtGhiChu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 108, 1037, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		model.addColumn("STT");
		model.addColumn("Ngày nhập");
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
		
		JLabel lbTitle = new JLabel("Danh Sách Phiếu Nhập");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		lbTitle.setBounds(315, 6, 421, 36);
		panel1.add(lbTitle);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(1252, 12, 90, 30);
		panel1.add(btnSearch);
		
		tfFind = new JTextField();
		tfFind.setBounds(1010, 12, 243, 30);
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
		lblNewLabel.setBounds(1052, 52, 201, 27);
		panel1.add(lblNewLabel);
		
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				adminGUI p = new adminGUI();
				p.f.setVisible(true);
			}
		});

		f.add(contentPane);
		f.setSize(1366, 740);
		f.setVisible(true);
		f.setResizable(false);
		JButton btnDetails = new JButton("Xem chi tiết phiếu nhập");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailsActionPerformed(e);
			}
		});
		btnDetails.setBounds(43, 317, 220, 28);
		panel.add(btnDetails);
		
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
		tfEnterCouponDate.setText(billDate);
	}
	
	private void btnResetActionPerformed(ActionEvent e) {
		tfAmount.setText("");
		tfEnterCouponDate.setText("");
		tfTotal.setText("");
		tfTotal.setEditable(false);
		tfAmount.setEditable(false);
	}
	
	private void jTableMouseClicked(MouseEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0) {            
    		tfEnterCouponDate.setText(String.valueOf(model.getValueAt(selectedIndex, 1)));
    		tfAmount.setText(String.valueOf(model.getValueAt(selectedIndex, 2)));
    		tfTotal.setText(String.valueOf(model.getValueAt(selectedIndex, 3)));
    		tfEnterCouponDate.setEditable(false);
        }
	}
	
	private void btnAddActionPerformed(ActionEvent e) {
		if(!tfEnterCouponDate.getText().trim().equals("")) {
			try {
				int amount = 0;
				String date = tfEnterCouponDate.getText();
				int total = 0;
				
				EnterCoupon p = new EnterCoupon(date, amount, total);
				
				if(!Tester.day(date)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng của ngày (Ví Dụ: 10/05/2021) ");
					return;
				}
				JOptionPane.showMessageDialog(null, EnterCouponBLL.addEnterCoupon(p));
				
				displayList();
				btnResetActionPerformed(e);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Giá bán hoặc Giá nhập phải là số");
			} 
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phiếu nhập");
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String EnterCouponDate = tfFind.getText();
        if(EnterCouponDate != null && EnterCouponDate.length() > 0) {
            EnterCouponList = EnterCouponBLL.searchEnterCouponByDate(EnterCouponDate);
            
            if(EnterCouponList.size()==0) {
            	JOptionPane.showMessageDialog(null, "Không có phiếu nhập cần tìm");
            	displayList();
            }
            
            else {
	            model.setRowCount(0);
	            int i = 0;
	    		while(i < EnterCouponList.size()) {
	    			EnterCoupon p = EnterCouponList.get(i);
	    			model.addRow(new Object [] {
	    					model.getRowCount()+1, p.getCouponDate(), p.getAmount(), Tester.format(p.getTotal())
	    			});
	    			i++;
	    		}
            }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày của phiếu nhập cần tìm");
        }
	}
	
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			tfEnterCouponDate.setEditable(false);
			int index = table.getSelectedRow();
			if(index>=0 && !tfEnterCouponDate.getText().equals("")) {
				EnterCoupon p = new EnterCoupon();
				
				// chỉ cho sửa mã nhân viên 
				JOptionPane.showMessageDialog(null, EnterCouponBLL.editEnterCoupon(p));
				displayList();
				btnResetActionPerformed(e);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần sửa");
			}
			
		}catch(InputMismatchException ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phiếu nhập");
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Giá bán hoặc Giá nhập phải là số");
		} 
	}
	
	private void btnRemoveActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
        if(selectedIndex >= 0 && !tfAmount.getText().equals("")) {
        	String code = tfEnterCouponDate.getText();
            
            int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa phiếu nhập này không?", "Question", JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
            	int sure = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập này không?", "Question", JOptionPane.YES_NO_OPTION);
            	if(sure == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, EnterCouponBLL.deleteEnterCoupon(code));
	                displayList();
	                btnResetActionPerformed(e);
            	}
            }
        }
        else {
        	JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần xóa");
        }
	}
	public static String code = null;
	public void btnDetailsActionPerformed(ActionEvent e) {
		int selectedIndex = table.getSelectedRow();
		if(selectedIndex>=0 && !tfAmount.getText().trim().equals("")) {
			code = tfEnterCouponDate.getText();
			f.setVisible(false);
			enterCouponDetailsGUI bd = new enterCouponDetailsGUI();
			bd.f.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập để xem chi tiết phiếu nhập");
		}
	}
	private void displayList() {
		
		model.setRowCount(0);
		EnterCouponList = EnterCouponBLL.getAllEnterCoupon();
    	int i = 0;
    	while(i < EnterCouponList.size()) {
			EnterCoupon p = EnterCouponList.get(i);
			model.addRow(new Object [] {
					model.getRowCount()+1, p.getCouponDate(), p.getAmount(), Tester.format(p.getTotal())
			});
			i++;
		}
		
	}
	
	
}