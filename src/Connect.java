import java.sql.*;
public final class Connect {
	public ResultSet rs;
	Statement st;
	Connection con;
	PreparedStatement pStat;
	ResultSetMetaData rsm;

	public Connect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");

            // Nanti prk nya diubah sesuai dengan nama db yang mau di connect
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop","root","");  
            st = con.createStatement();  
            
            System.out.println("Connected to the database..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection");
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection RS");
		}
		return rs;
	}

	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executePStatement(String query) {
		try {
			pStat = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection PSTATEMENT");
		}
	}

	public void executeInsertToUser(String username, String password, String role) {
		try {
			pStat = con
					.prepareStatement("INSERT INTO Users(username,password,role) VALUES(?, ?, ?)");
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.setString(3, role);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void executeInsertToProduct(String productName,int productCategory,int productPrice, int productStock) {
		try {
			pStat = con
					.prepareStatement("INSERT INTO Products(product_name,product_category_id,product_price,product_stock) VALUES(?,?, ?, ?)");
			pStat.setString(1, productName);
			pStat.setInt(2, productCategory);
			pStat.setInt(3, productPrice);
			pStat.setInt(4, productStock);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeInsertToCategory(String category_name) {
		try {
			pStat = con
					.prepareStatement("INSERT INTO categories(category_name) VALUES(?)");
			pStat.setString(1, category_name);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
