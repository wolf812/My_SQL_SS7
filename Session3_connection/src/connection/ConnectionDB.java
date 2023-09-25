package connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private  static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private  static final String URL = "jdbc:mysql://localhost:3306/Book_SQL";
    private  static final String USERNAME = "root";
    private  static final String PASSWORD = "Anhanh11";

    public static Connection openConnection(){
        Connection conn = null;

        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static  void main(String[] args) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            System.out.println("thành công");
        }else {
            System.out.println("thất bại");
        }

    }

    public static void closeConnection(Connection conn, CallableStatement callSt){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


