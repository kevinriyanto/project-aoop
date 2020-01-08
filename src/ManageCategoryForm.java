import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageCategoryForm extends JFrame{
	Connect con;
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tHeader, tRow;
	int id = -1;
	private void fillTable(){
		dtm = new DefaultTableModel(tHeader, 0);
		con.rs = con
				.executeQuery("SELECT id,category_name FROM categories");
		try {
			int index = 0;
			while (con.rs.next()) {
				tRow = new Vector<Object>();
				System.out.println(con.rsm.getColumnCount());
				tRow.add((index+1) + "");
				for (int i = 1; i <= con.rsm.getColumnCount(); i++){
					
					tRow.add(con.rs.getObject(i) + "");
//					tRow.add(con.rs.getObject(i) + "");
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
	public ManageCategoryForm() {
		tHeader = new Vector<Object>();

		JTextField categoryTxt = new JTextField();
		tHeader.add("No");
		tHeader.add("ID Category");
		tHeader.add("Category Name");
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
				// TODO Auto-generated method stub
				id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1) + "");
				categoryTxt.setText(table.getValueAt(table.getSelectedRow(), 2) + "");
				
			}
		});
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(400,200));
		
		
		
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		GridLayout gridLayout = new GridLayout(1,2);
		gridLayout.setHgap(20);
		JPanel formPanel = new JPanel(gridLayout);
		JLabel lbl = new JLabel("Category Name");
		formPanel.add(lbl);
		formPanel.add(categoryTxt);
		
		
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
				if(categoryTxt.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "category must be filled");
				}else{
					con.executeInsertToCategory(categoryTxt.getText());
					JOptionPane.showMessageDialog(null, "success insert data");
					fillTable();
				}
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(categoryTxt.getText().length() == 0 || id == -1){
					JOptionPane.showMessageDialog(null, "select category want to be delete");
				}else{
					String query = "delete from categories where id = " + id;
					con.executeUpdate(query);
					categoryTxt.setText("");
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
				if(categoryTxt.getText().length() == 0 || id == -1){
					JOptionPane.showMessageDialog(null, "select category want to be update");
				}else{
					String query = "update categories set category_name = '"+categoryTxt.getText()+"' where id = " + id;
					con.executeUpdate(query);
					categoryTxt.setText("");
					id = -1;
					JOptionPane.showMessageDialog(null, "update success");
				}
				fillTable();
			}
		});
		
		
		bottomPanel.add(insertBtn);
		bottomPanel.add(updateBtn);
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
