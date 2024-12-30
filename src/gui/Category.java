package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.SQLIntegrityConstraintViolationException;

public class Category extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t;
	private JTable table;
	
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname= "spendingdb";
	String dbPwd = "spendingdb";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
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
	public Category() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBounds(0, 10, 434, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 153));
		panel_1.setBounds(10, 5, 414, 47);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Category");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 394, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 63, 72, 14);
		panel.add(lblNewLabel_1);
		
		t = new JTextField();
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String category = t.getText();
					if(!category.equals("")) {
					db.DbConnect.st.executeUpdate("insert into category_info values('"+category+"')");
					JOptionPane.showMessageDialog(null, "Category added successfully!");
					getEntries();
				}else {
					JOptionPane.showMessageDialog(null, "Please enter any value!");
				}}
				catch(java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Category already Existed");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
//			btnNewButton.addActionListener(null);
			}
		});
		t.setBounds(79, 63, 240, 20);
		panel.add(t);
		t.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String category = t.getText();
					if(!category.equals("")) {
					db.DbConnect.st.executeUpdate("insert into category_info values('"+category+"')");
					JOptionPane.showMessageDialog(null, "Category added successfully!");
					getEntries();
				}else {
					JOptionPane.showMessageDialog(null, "Please enter any value!");
				}}
				catch(java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Category already Existed");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(324, 63, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane table_1 = new JScrollPane();
		table_1.setBounds(0, 110, 434, 131);
		contentPane.add(table_1);
		
		table = new JTable();
		table_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Category"
			}
		));
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ri = table.getSelectedRow();
				if(ri!=-1) {
				int r = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Deletion Confirmation", JOptionPane.YES_NO_OPTION);
				if(r==JOptionPane.YES_OPTION) {
				String category = (String)table.getValueAt(ri, 1);
				try {
				db.DbConnect.st.executeUpdate("delete from category_info where category='"+category+"'");
				JOptionPane.showMessageDialog(null, "Category deleted successfully!!!");
				getEntries();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
				}
				}
			}
		});
		btnNewButton_1.setBounds(0, 246, 434, 23);
		contentPane.add(btnNewButton_1);
		
		getEntries();
	}
	void getEntries() {
		try {
			javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
			int rc = dtm.getRowCount();
			while(rc--!=0) {
				dtm.removeRow(0);
			}
			
			ResultSet rs = db.DbConnect.st.executeQuery("select * from category_info");
			int sno = 0;
			while(rs.next()) {
				String category = rs.getString("category");
			
//				Object o[] = {++sno,category};
//				dtm.addRow(o);
				java.util.Vector row = new java.util.Vector();
				row.add(++sno);
				row.add(category);
				dtm.addRow(row);
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
