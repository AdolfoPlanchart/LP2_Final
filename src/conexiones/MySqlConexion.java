package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySqlConexion {
	public static Connection getConexion(){
		Connection cn=null;
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://localhost/instituto_2021_t4cm?serverTimezone=UTC";
				cn=DriverManager.getConnection(url,"root","mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;	
	}
}
