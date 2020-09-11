package P2;

import java.sql.*;
import java.util.List;

public class main {
    private Connection conn;
    ReizigerDAO reizigerDAO;

    public Connection getConn() {
        String dbUrl = "jdbc:postgresql://localhost/ovchip";
        String user = "postgres";
        String pass = "welkom123";

        try{
            conn = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection succesfull");
            conn.close();
            System.out.println("Connection closed");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;

    }

    ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(getConn());



    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test P2.ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] P2.ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger();
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na P2.ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.


    }

    public main() throws SQLException{
        Reiziger reiziger = null;
        testReizigerDAO(reizigerDAOPsql);
    }

    public static void main(String[] args) throws SQLException{
        new main();

    }
    //
}
