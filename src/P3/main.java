package P3;

import P2.ReizigerDAOPsql;

import java.sql.*;

public class main {
    private Connection conn;

    public Connection getConn() {
        AdresDAOPsql adresDAOPsql = null;
        Adres adres = null;

        String dbUrl = "jdbc:postgresql://localhost/ovchip";
        String user = "postgres";
        String pass = "welkom123";

        try{
            conn = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection succesfull");
            adresDAOPsql.save(adres);
            conn.close();
            System.out.println("Connection closed");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException{
        new main();

    }
}
