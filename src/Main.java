import javax.swing.SwingUtilities;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ManageProductForm();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
