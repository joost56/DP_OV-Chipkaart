package P3;

import P2.Reiziger;
import P2.ReizigerDAO;
import P2.ReizigerDAOPsql;
import Verbinding.DBVerbinding;

import java.sql.*;
import java.util.List;

public class Main {
    ReizigerDAO reizigerDAO;
    AdresDAO adresDAO;

    Connection conn = DBVerbinding.getConnection();

    AdresDAOPsql adresDAOPsql = new AdresDAOPsql(conn);
    ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(conn);

    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        String gbdatum = "2002-03-19";
        Reiziger RandomReiziger = new Reiziger(6, "J", "van", "Buiting", java.sql.Date.valueOf(gbdatum));
        rdao.save(RandomReiziger);

        //findall
        List<Adres> adresList = adao.findAll();
        System.out.println("[Test]Uitvoer na het gebruik van de methode AdresDAO.findAll()\n");
        for (Adres a : adresList) {
            System.out.println(a);
        }

        Adres adres = new Adres(6, "3766cl", "9", "van der weydenstraat", "soest", RandomReiziger.getReiziger_id());

        //save
        System.out.println("\n \n[Test]Uitvoer na het gebruik van de methode AdresDAO.save() en Adres.findAll() :\n");
        adao.save(adres);
        List<Adres> adresList1 = adao.findAll();
        for (Adres a : adresList1) {
            System.out.println(a);
        }

        //update
        adres.setPostcode("1234op");
        System.out.println("\n \n[Test]Voor AdresDAO.update() :  " + adao.findByReiziger(RandomReiziger));
        adao.update(adres);
        System.out.println("Na AdresDAO.update()   :  " + adao.findByReiziger(RandomReiziger) +"\n");

        //findbyreiziger
        Adres adres1 = adao.findByReiziger(RandomReiziger);
        System.out.println("[Test] findByReiziger() geeft het adres:");
        System.out.println(adres1);

        //delete
        System.out.print("\n[Test] Adressen voor delete: " + adresList.size() + "  , adressen na delete: ");
        adao.delete(adres);
        adresList1 = adao.findAll();
        System.out.println(adresList.size() + "\n");
    }


        public Main() throws SQLException{
            testAdresDAO(adresDAOPsql, reizigerDAOPsql);

        }

        public static void main(String[] args) throws SQLException{
            new Main();

        }
}
