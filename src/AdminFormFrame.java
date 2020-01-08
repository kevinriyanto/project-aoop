import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFormFrame extends JFrame{

	public AdminFormFrame() {
		JPanel centerPanel = new JPanel(new GridLayout(3,1));
		JButton prodBtn = new JButton("Manage Product");
		JButton categoryBtn = new JButton("Manage Category");
		JButton transBtn = new JButton("Manage Transaction");
		
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
