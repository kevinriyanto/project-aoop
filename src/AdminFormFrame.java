import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminFormFrame extends JFrame{

	public AdminFormFrame() {
		JPanel centerPanel = new JPanel();
		
		
		this.add(centerPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		this.setVisible(true);
	}

}
