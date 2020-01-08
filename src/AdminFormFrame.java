import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFormFrame extends JFrame{

	public AdminFormFrame() {
		JPanel centerPanel = new JPanel(new GridLayout(3,1));
		JButton prodBtn = new JButton("Manage Product");
		JButton categoryBtn = new JButton("Manage Category");
		JButton transBtn = new JButton("Manage Transaction");
		prodBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageProductForm();
			}
		});
		categoryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageCategoryForm();
			}
		});
		transBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageTransactionForm();
			}
		});
		centerPanel.add(prodBtn);
		centerPanel.add(categoryBtn);
		centerPanel.add(transBtn);
		
		
		this.add(centerPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
