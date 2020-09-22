package P2;

import P3.Adres;
import P3.AdresDAO;
import P3.AdresDAOPsql;
import Verbinding.DBVerbinding;

import java.sql.*;
import java.util.List;

public class Main {
    ReizigerDAO reizigerDAO;
    AdresDAO adresDAO;


    Connection conn = DBVerbinding.getConnection();

    ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(conn);

    AdresDAOPsql adresDAOPsql = new AdresDAOPsql(conn);

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        //findall
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test]Uitvoer na het gebruik van de methode ReizigerDAO.findAll()\n");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

        //save
        String gbdatum = "2002-03-19";
        Reiziger Joost = new Reiziger(6, "J", "", "Buiting", Date.valueOf(gbdatum));
        System.out.println("\n \n[Test]Uitvoer na het gebruik van de methode ReizigerDAO.save() en Reiziger.findAll() :\n");
        rdao.save(Joost);
        List<Reiziger> reizigers1 = rdao.findAll();
        for (Reiziger r : reizigers1) {
            System.out.println(r);
        }

        //update
        Joost.setTussenvoegsel("van");
        System.out.println("\n \n[Test]Voor ReizigerDAO.update() :  " + rdao.findById(6));
        rdao.update(Joost);
        System.out.println("Na ReizigerDAO.update()   :  " + rdao.findById(6));

        //delete
        System.out.print("\n[Test] Reizigers voor delete: " + reizigers.size() + "  , reizigers na delete: ");
        rdao.delete(Joost);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + "\n");

        //findbyid
        Reiziger reiziger = rdao.findById(1);
        System.out.println("[Test] findById(1) geeft de reiziger:");
        System.out.println(reiziger);

        //findbygeboortedatum
        List<Reiziger> reizigers2 = rdao.findByGbdatum("2002-10-22");
        System.out.println("\n[Test] findByGbdatum geeft de reizigers:");
        for (Reiziger r2 : reizigers2) {
            System.out.println(r2);
        }
        System.out.println();
    }



    public Main() throws SQLException{
        testReizigerDAO(reizigerDAOPsql);

    }

    public static void main(String[] args) throws SQLException{
        new Main();

    }
    //
}
