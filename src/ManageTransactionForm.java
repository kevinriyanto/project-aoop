import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageTransactionForm extends JFrame{
	Connect con;
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tHeader, tRow;
	int id = -1;
	int[] categories = new int[999];
	String[] categories_name = new String[999];
	int[] users = new int[999];
	String[] user_name = new String[999];
	JComboBox<String> cb,cbUser;
	private void fillTable(){
		dtm = new DefaultTableModel(tHeader, 0);
		con.rs = con
				.executeQuery("SELECT username,products.id, product_name, category_name, product_price,qty, created_at FROM products join categories on products.id = categories.id join transactions on transactions.product_id = products.id join users on users.id = transactions.user_id");
		try {
			int index = 0;
			while (con.rs.next()) {
				tRow = new Vector<Object>();
				System.out.println(con.rsm.getColumnCount());
				tRow.add((index+1) + "");
				for (int i = 1; i <= con.rsm.getColumnCount(); i++){
					
					tRow.add(con.rs.getObject(i) + "");
				}
				dtm.addRow(tRow);
				index++;
			}
			table.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fillComboBox(){
		con.rs = con
				.executeQuery("SELECT id,product_name FROM products");
		try {
			int index = 0;
			
			while (con.rs.next()) {
				categories[index] = Integer.parseInt(con.rs.getObject(1)+ "") ;
				categories_name[index] = con.rs.getObject(2)+ "" ;
				cb.addItem(con.rs.getObject(2) + "");
				index++;
			}
			idCate = categories[0];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fillCbUser(){
		con.rs = con
				.executeQuery("SELECT id,username FROM users");
		try {
			int index = 0;
			
			while (con.rs.next()) {
				users[index] = Integer.parseInt(con.rs.getObject(1)+ "") ;
				user_name[index] = con.rs.getObject(2)+ "" ;
				cbUser.addItem(con.rs.getObject(2) + "");
				index++;
			}
			idUser = users[0];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int findIndex(String text){
		for(int i=0;i<categories_name.length;i++){
			
			if(text.equals(categories_name[i])){
				return i;
			}
		}
		return -1;
	}
	private int findIndexUser(String text){
		for(int i=0;i<user_name.length;i++){
			
			if(text.equals(user_name[i])){
				return i;
			}
		}
		return -1;
	}
	int idCate = -1,idUser = -1;
	public ManageTransactionForm() {
		tHeader = new Vector<Object>();
		cb = new JComboBox<>();
		cbUser = new JComboBox<>();

		JTextField qty = new JTextField();
		tHeader.add("No");
		tHeader.add("User Name");
		tHeader.add("Product Id");
		tHeader.add("Product Name");
		tHeader.add("Product Category");
		tHeader.add("Product Price");
		tHeader.add("Qty");
		tHeader.add("Transaction Date");
		con = new Connect();
		JPanel topPanel = new JPanel();
		table = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		fillTable();
		fillComboBox();
		fillCbUser();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cbUser.setSelectedIndex(findIndexUser(table.getValueAt(table.getSelectedRow(), 1)+""));
				id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2) + "");
				cb.setSelectedIndex(findIndex(table.getValueAt(table.getSelectedRow(), 3)+""));
				qty.setText(table.getValueAt(table.getSelectedRow(), 6) + "");
			}
		});
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(400,200));
		
		
		
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		GridLayout gridLayout = new GridLayout(4,2);
		gridLayout.setHgap(20);
		JPanel formPanel = new JPanel(gridLayout);
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				idCate = categories[cb.getSelectedIndex()];
			}
		});
		
		
		
		formPanel.add(new JLabel("User Name"));
		formPanel.add(cbUser);
		formPanel.add(new JLabel("Product Name"));
		formPanel.add(cb);
		formPanel.add(new JLabel("Qty"));
		formPanel.add(qty);
		
		
		JPanel bottomPanel = new JPanel();
		JButton insertBtn = new JButton("Insert");
		JButton updateBtn = new JButton("Update");
		JButton deleteBtn = new JButton("Delete");
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminFormFrame();
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(qty.getText().length() == 0 || Integer.parseInt(qty.getText()) <= 0){
					JOptionPane.showMessageDialog(null, "Input valid qty!");
				}else{
					con.executeInsertToTransaction(idUser, idCate,Integer.parseInt(qty.getText()));
					JOptionPane.showMessageDialog(null, "success insert data");
					fillTable();
				}
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(qty.getText().length() == 0 || idCate < 1 || idUser < 1){
					JOptionPane.showMessageDialog(null, "select transaction want to be delete");
				}else{
					String query = "delete from transactions where id = " + id;
					con.executeUpdate(query);
					cb.setSelectedIndex(0);
					cbUser.setSelectedIndex(0);
					qty.setText("");
					id = -1;
					JOptionPane.showMessageDialog(null, "delete success");
				}
				fillTable();
			}
		});
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		bottomPanel.add(insertBtn);
//		bottomPanel.add(updateBtn);
		bottomPanel.add(deleteBtn);
		bottomPanel.add(backBtn);
		centerPanel.add(formPanel);
		topPanel.add(pane);
		
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		add(topPanel, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
