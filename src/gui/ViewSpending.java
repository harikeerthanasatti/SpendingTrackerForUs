package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ViewSpending extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table1;
	private JLabel totalAmount1;
	private JLabel totalAmount2;
	private JDateChooser d1;
	private JDateChooser d2;
	private JTable table2;
	private JComboBox category;
	private JDateChooser dd1;
	private JDateChooser dd2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSpending frame = new ViewSpending();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewSpending() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 355);
		try {
		contentPane = new JPanel();
		contentPane.setVisible(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(0, 11, 222, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 202, 38);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View Spending Date Wise");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 182, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 43, 36, 20);
		panel.add(lblNewLabel_1);
		
		
		d1 = new JDateChooser();
		d2 = new JDateChooser();
		d1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				d2.setSelectableDateRange(d1.getDate(),new java.util.Date());
				d2.setDate(d1.getDate());
			}
		});
		d1.setBounds(49, 43, 163, 20);
		panel.add(d1);
		
		JLabel lblNewLabel_1_1 = new JLabel("To :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 68, 36, 20);
		panel.add(lblNewLabel_1_1);
		
		
		d2.setBounds(49, 68, 163, 20);
		panel.add(d2);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table1.getModel();
					int rc = dtm.getRowCount();
					while(rc--!=0) {
						dtm.removeRow(0);
					}
				java.sql.Date dt1 = new java.sql.Date(d1.getDate().getTime());
				java.sql.Date dt2 = new java.sql.Date(d2.getDate().getTime());
				ResultSet rs = db.DbConnect.st.executeQuery("select * from spendings where sdate>=DATE '"+dt1+"' and sdate<=DATE '"+dt2+"' order by sdate asc");
				int total=0;
				while(rs.next()) {
					int t= rs.getInt("amount");
					total+=t;
					Object o[] = {rs.getDate("sdate"),rs.getString("category"),rs.getInt("amount")};
					dtm.addRow(o);
				}
				totalAmount1.setText(total+"");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}

			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(10, 96, 69, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 202, 172);
		panel.add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Category", "Amount"
			}
		));
		
		JLabel lblNewLabel_2 = new JLabel("Total Amount:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(80, 97, 76, 20);
		panel.add(lblNewLabel_2);
		
		totalAmount1 = new JLabel("0");
		totalAmount1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		totalAmount1.setBounds(159, 99, 53, 20);
		panel.add(totalAmount1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(204, 204, 255));
		panel_2.setBounds(228, 11, 222, 305);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 0, 202, 38);
		panel_2.add(panel_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("View Spending Date Wise");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 11, 182, 16);
		panel_1_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("From :");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(10, 80, 36, 20);
		panel_2.add(lblNewLabel_1_2);
		
		dd1 = new JDateChooser();
		dd2 = new JDateChooser();

		dd1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dd2.setSelectableDateRange(dd1.getDate(),new java.util.Date());
				dd2.setDate(dd1.getDate());
			}
		});
		dd1.setBounds(49, 80, 163, 20);
		panel_2.add(dd1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("To :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 110, 36, 20);
		panel_2.add(lblNewLabel_1_1_1);
		
		dd2.setBounds(49, 110, 163, 20);
		panel_2.add(dd2);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table2.getModel();
					int rc = dtm.getRowCount();
					while(rc--!=0) {
						dtm.removeRow(0);
					}
					String c = (String)category.getSelectedItem();
				java.sql.Date dt1 = new java.sql.Date(dd1.getDate().getTime());
				java.sql.Date dt2 = new java.sql.Date(dd2.getDate().getTime());
				ResultSet rs = db.DbConnect.st.executeQuery("select * from spendings where sdate>=DATE '"+dt1+"' and sdate<=DATE '"+dt2+"' and category='"+c+"' order by sdate asc");
				int total=0;
				while(rs.next()) {
					int t= rs.getInt("amount");
					total+=t;
					Object o[] = {rs.getDate("sdate"),rs.getString("category"),rs.getInt("amount")};
					dtm.addRow(o);
				}
				totalAmount2.setText(total+"");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 141, 69, 23);
		panel_2.add(btnNewButton_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 175, 202, 119);
		panel_2.add(scrollPane_2);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Amount", "Category", "Date"
			}
		));
		scrollPane_2.setViewportView(table2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Total Amount:");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2_2.setBounds(78, 142, 76, 20);
		panel_2.add(lblNewLabel_2_2);
		
		totalAmount2 = new JLabel("0");
		totalAmount2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		totalAmount2.setBounds(157, 142, 44, 20);
		panel_2.add(totalAmount2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Category :");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(10, 49, 55, 20);
		panel_2.add(lblNewLabel_1_2_1);
		
		category = new JComboBox();
		category.setBounds(69, 49, 143, 22);
		panel_2.add(category);
		
		d1.setDate(new java.util.Date());
		d2.setDate(new java.util.Date());
		displayCategory();
		dd1.setDate(new java.util.Date());
		dd2.setDate(new java.util.Date());
	}
	private void displayCategory() {
		try {
		ResultSet rs = db.DbConnect.st.executeQuery("select * from category_info");
		while(rs.next()) {
			category.addItem(rs.getString("category"));
		}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex);
		}
		
	}
}