package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.sql.ResultSet;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SpendingTracker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField a;
	private JTable table;
	private JComboBox category;
	private JLabel totalAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpendingTracker frame = new SpendingTracker();
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
	public SpendingTracker() {
		setTitle("SpendingTracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 605, 99);
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(0, 0, 605, 40);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton viewAllSpend = new JButton("View All Spending");
		viewAllSpend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewSpending().setVisible(true);
			}
		});
		viewAllSpend.setBounds(0, 11, 124, 23);
		panel_1.add( viewAllSpend);
		
		JButton addViewCatg = new JButton("Add/View Category");
		addViewCatg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Category().setVisible(true);
		}
		});
		addViewCatg.setBounds(134, 11, 136, 23);
		panel_1.add(addViewCatg);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(280, 11, 89, 23);
		panel_1.add(exit);
		
		JButton aboutApp = new JButton("About App");
		aboutApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Design & Develop by Hari Keerthana");
			}
		});
		aboutApp.setBounds(379, 11, 89, 23);
		panel_1.add(aboutApp);
		
		

		
		
		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 40, 46, 26);
		panel.add(lblNewLabel_1);
		
		JDateChooser d = new JDateChooser();
		d.setBounds(53, 46, 105, 20);
		panel.add(d);
		
		JLabel lblNewLabel_1_1 = new JLabel("Amount :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(184, 40, 65, 26);
		panel.add(lblNewLabel_1_1);
		
		a = new JTextField();
		a.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!Character.isDigit(ch)) {
					e.consume();
				}
			}
		});
		a.setBounds(245, 45, 65, 20);
		panel.add(a);
		a.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Category :");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(349, 40, 65, 26);
		panel.add(lblNewLabel_1_2);
		
		category = new JComboBox();
		category.setBounds(416, 43, 111, 22);
		panel.add(category);
		
		JButton btnNewButton = new JButton("Add New Category");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Category().setVisible(true);
			}
			
		});
		btnNewButton.setBounds(416, 68, 111, 20);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.util.Date dt = d.getDate();
					String s1 = a.getText();
					String c = (String)category.getSelectedItem();
					if(dt!=null && !s1.equals("") && !c.equals("")) {
						int amount = Integer.parseInt(s1);
						java.sql.Date date = new java.sql.Date(dt.getTime());
						db.DbConnect.st.executeUpdate("insert into spendings (category,sdate,amount) values('"+c+"',DATE '"+date+"',"+amount+")");
						JOptionPane.showMessageDialog(null,"Expense added Successfully!!!");
					}else {
						JOptionPane.showMessageDialog(null,"Please fill all the details");
						}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_1.setBackground(new Color(64, 224, 208));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(537, 43, 58, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCategory();
			}
		});
		btnNewButton_3.setBounds(323, 67, 89, 23);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("Last 20 days Spendings:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(0, 110, 143, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("REMOVE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ri = table.getSelectedRow();
				if(ri!=-1) {
					int r = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Deletion Confirmation", JOptionPane.YES_NO_OPTION);
					if(r==JOptionPane.YES_OPTION) {
					int id = (int)table.getValueAt(ri,0);
					try {
						db.DbConnect.st.executeUpdate("delete from spendings where sid = "+id);
						JOptionPane.showMessageDialog(null, "Successfully deleted");
						getEntries();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
					
				}
				}
			}
		});
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setBounds(516, 106, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 135, 605, 122);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Date", "Category", "Amount"
			}
		));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(0, 261, 605, 23);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Total Amount:");
		lblNewLabel_2_1.setBounds(10, 5, 471, 14);
		panel_2.add(lblNewLabel_2_1);
		
		totalAmount = new JLabel("0");
		totalAmount.setBounds(484, 5, 121, 14);
		panel_2.add(totalAmount);
		
		d.setSelectableDateRange(null, new java.util.Date());
		displayCategory();
		getEntries();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		d.setDate(new java.util.Date());
	}
	private void displayCategory() {
		try {
			category.removeAllItems();
			ResultSet rs = db.DbConnect.st.executeQuery("select * from category_info");
			while(rs.next()) {
				category.addItem(rs.getString("category"));
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);;
		}
	}
	
	private void getEntries() {
		try {
			javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)table.getModel();
			int rc = dtm.getRowCount();
			while(rc--!=0) {
				dtm.removeRow(0);
			}
			java.time.LocalDate cd = java.time.LocalDate.now();
			java.time.LocalDate bd = cd.minusDays(20);
			ResultSet rs = db.DbConnect.st.executeQuery("select * from spendings where sdate<=DATE '"+cd+"'and sdate>=DATE '"+bd+"'");
			int total =0;
			while(rs.next()) {
				int t = rs.getInt("amount");
				total+=t;
				Object o[] = {rs.getInt("sid"),rs.getString("category"),rs.getDate("sdate"),t};
				dtm.addRow(o);
			}
			totalAmount.setText(total+"");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);;
		}
	}
}
