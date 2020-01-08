import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame {
	Connect con;
	public LoginFrame() {
		con = new Connect();
		JFrame f = new JFrame();
		JPanel centerPanel = new JPanel(new GridLayout(2,2));
		JPanel l1 = new JPanel(new BorderLayout());
		JPanel r1 = new JPanel(new BorderLayout());
		JPanel l2 = new JPanel(new BorderLayout());
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(JLabel.CENTER);
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setHorizontalAlignment(JLabel.CENTER);
		JTextField usernameTxt = new JTextField();
		JPasswordField passwordTxt = new JPasswordField();
		
		l1.add(usernameLbl,BorderLayout.CENTER);
		r1.add(passwordLbl, BorderLayout.CENTER);
		l2.add(usernameTxt, BorderLayout.CENTER);
		
		
		centerPanel.add(l1);
		centerPanel.add(l2);
		centerPanel.add(r1);
		centerPanel.add(passwordTxt);
		
		JPanel bottomPanel = new JPanel();
		JButton loginBtn = new JButton("Login");
		JButton rgstrBtn= new JButton("Register");
		
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(usernameTxt.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Username must be filled!");
				}else if(passwordTxt.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Password must be filled!");
				}else{
					String query = "select * from users where username = '" + usernameTxt.getText() + "' and password = '" +passwordTxt.getText()+ "'"; 
					con.rs = con.executeQuery(query);
					try {
						if (con.rs.next()) {
							JOptionPane.showMessageDialog(null, "Welcome, "
									+ usernameTxt.getText());
//							main.setLoggedState(false, con.rs.getString("Role"));
							System.out.println(con.rs.getString("role"));
							if(con.rs.getString("role").equals("Admin")){
								new AdminFormFrame();
							}else{
								
							}
							f.dispose();
						} else
							JOptionPane.showMessageDialog(null,
									"Invalid username/password");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		rgstrBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				f.dispose();
			}
		});
		
		
		
		JPanel topPanel = new JPanel();
		JLabel title = new JLabel("Login");
		title.setFont(new Font("Calibri", Font.BOLD, 20));
		topPanel.add(title);
		
		bottomPanel.add(loginBtn);
		bottomPanel.add(rgstrBtn);
		
		f.add(bottomPanel, BorderLayout.SOUTH);
		f.add(centerPanel, BorderLayout.CENTER);
		f.add(topPanel, BorderLayout.NORTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setSize(300,150);
		f.setVisible(true);
	}

}
