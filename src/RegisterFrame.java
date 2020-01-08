import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame {
	Connect con;
	public RegisterFrame() {
		con = new Connect();
		JFrame f = new JFrame();
		JPanel centerPanel = new JPanel(new GridLayout(3,2));
		JPanel l1 = new JPanel(new BorderLayout());
		JPanel r1 = new JPanel(new BorderLayout());
		JPanel l2 = new JPanel(new BorderLayout());
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(JLabel.CENTER);
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setHorizontalAlignment(JLabel.CENTER);
		JTextField usernameTxt = new JTextField();
		JPasswordField passwordTxt = new JPasswordField();
		JLabel confLbl = new JLabel("Confirm Password");
		confLbl.setHorizontalAlignment(JLabel.CENTER);
		JPasswordField confTxt = new JPasswordField();
		
		l1.add(usernameLbl,BorderLayout.CENTER);
		r1.add(passwordLbl, BorderLayout.CENTER);
		l2.add(usernameTxt, BorderLayout.CENTER);
		
		
		centerPanel.add(l1);
		centerPanel.add(l2);
		centerPanel.add(r1);
		centerPanel.add(passwordTxt);
		centerPanel.add(confLbl, BorderLayout.CENTER);
		centerPanel.add(confTxt);
		
		JPanel bottomPanel = new JPanel();
		JButton rgstrBtn= new JButton("Register");
		rgstrBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(usernameTxt.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Username must be filled!");
				}else if(passwordTxt.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Password must be filled!");
				}else if(!confTxt.getText().equals(passwordTxt.getText())){
					JOptionPane.showMessageDialog(null, "Password not same");
				}else{
					con.executeInsertToUser(usernameTxt.getText(), passwordTxt.getText(), "Member");
					JOptionPane.showMessageDialog(null, "Register Success!");
					new LoginFrame();
					f.dispose();	
				}
			}
		});
		
		JPanel topPanel = new JPanel();
		JLabel title = new JLabel("Register");
		title.setFont(new Font("Calibri", Font.BOLD, 20));
		topPanel.add(title);
		
		bottomPanel.add(rgstrBtn);
		
		f.add(bottomPanel, BorderLayout.SOUTH);
		f.add(centerPanel, BorderLayout.CENTER);
		f.add(topPanel, BorderLayout.NORTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setSize(300,200);
		f.setVisible(true);
	}

}
