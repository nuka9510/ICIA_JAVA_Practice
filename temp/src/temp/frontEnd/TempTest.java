package temp.frontEnd;

import java.sql.Connection;
import java.sql.DriverManager;

public class TempTest {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			System.out.println("立加己傍");
		} catch (Exception e) {
			System.out.println("立加角菩");
			e.printStackTrace();
		}

	}

}
