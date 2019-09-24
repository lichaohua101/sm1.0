package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class DBUtil {
	/**
	 * ����
	 */
	private static String driver;
	/**
	 * �����ַ���
	 */
	private static String url;
	/**
	 * �ʺ�
	 */
	private static String username;
	/**
	 * ����
	 */
	private static String password;
	/**
	 * ��̬�����������ʼ�����ݣ�ֻ��ִ��һ��
	 */
	static {
		Properties p =new Properties();
		//д����
		//p.load(new FileInputStream("C:\\Users\\Administrator\\Workspaces\\MyEclipse 10\\jdbc_0722\\src\\com\\entor\\util\\db.properties"));
		try {
			//��ȡsrc����·���µĴ���properties �ļ���/ ָsrc
			p.load(DBUtil.class.getResourceAsStream("/db.properties"));
			 driver= p.getProperty("driver");
			 url= p.getProperty("url");
			 username= p.getProperty("username");
			 password= p.getProperty("password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �����ݿ�
	 * @return
	 */
	public static Connection getConnection() {
		// 1���������� (oracle.jdbc.driver.OracleDriver)
		try {
			Class.forName(driver);
			// 2.��ȡ���ݿ�����Ӷ���
			return DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/**
 * �ر����ݿ�
 * @param con	Connection
 * @param pst	PreparedStatement
 * @param rs	ResultSet
 */
	public static void close(Connection con, PreparedStatement pst, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		if (pst != null) {
			try {
				pst.close();
				pst = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
