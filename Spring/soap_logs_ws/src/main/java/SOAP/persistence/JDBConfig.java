package SOAP.persistence;


import java.sql.*;

public class JDBConfig {
    private Connection con = null;
    private PreparedStatement ps = null;
    private String dbURL = "jdbc:mysql://localhost:3306/albums_db?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "root";
    private String password = "1234";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection Driver error");
        }

    }

    public Connection getConnection(){
        try {
            con = DriverManager.getConnection(dbURL, username, password);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return con;
    }

    public PreparedStatement prepareStatement(String sql){
        Connection con = getConnection();
        try {
            ps = con.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement prepareStatementWithKeys(String sql){
        Connection con = getConnection();
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    public void close(){
        if(ps!=null){
            try{
                ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        close();
    }
}
