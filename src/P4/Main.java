package P4;

import P2.Reiziger;
import P2.ReizigerDAO;
import P2.ReizigerDAOPsql;
import P3.Adres;
import P3.AdresDAO;
import Verbinding.DBVerbinding;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    OVChipkaart ovChipkaart;
    Reiziger reiziger;

    Connection conn = DBVerbinding.getConnection();

    OVChipkaartDAOsql ovChipkaartDAOsql = new OVChipkaartDAOsql(conn);
    ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(conn);


    private static void testOVChipkaartDAO(OVChipkaartDAO ovChipkaartDAO, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        String gbdatum = "2002-03-19";
        Reiziger RandomReiziger = new Reiziger(8, "P", "van", "Buiting", java.sql.Date.valueOf(gbdatum));
        rdao.save(RandomReiziger);

        //findall
        List<OVChipkaart> ovChipkaartList = ovChipkaartDAO.findAll();
        System.out.println("[Test]Uitvoer na het gebruik van de methode AdresDAO.findAll()\n");
        for (OVChipkaart ovChipkaart : ovChipkaartList) {
            System.out.println(ovChipkaart);
        }

        String geldig_tot = "2030- 02-02";
        OVChipkaart randomOV = new OVChipkaart(100, java.sql.Date.valueOf(geldig_tot), 2, 100, RandomReiziger.getReiziger_id());

        //save
        System.out.println("\n \n[Test]Uitvoer na het gebruik van de methode AdresDAO.save() en Adres.findAll() :\n");
        ovChipkaartDAO.save(randomOV);
        List<OVChipkaart> ovList1 = ovChipkaartDAO.findAll();
        for (OVChipkaart ov : ovList1) {
            System.out.println(ov);
        }

        //update
        randomOV.setSaldo(200);
        System.out.println("\n \n[Test]Voor AdresDAO.update() :  " + ovChipkaartDAO.findByReiziger(RandomReiziger));
        ovChipkaartDAO.update(randomOV);
        System.out.println("Na AdresDAO.update()   :  " + ovChipkaartDAO.findByReiziger(RandomReiziger) +"\n");

        //findbyreiziger
        List<OVChipkaart> ovchips = ovChipkaartDAO.findByReiziger(RandomReiziger);
        System.out.println("\n[Test] Uitvoer na het gebruik van OVChipkaartDAO.findByReiziger()");
        for (OVChipkaart ov : ovchips) {
            System.out.println(ov);
        }

        //delete
        System.out.print("\n[Test] Adressen voor delete: " + ovChipkaartList.size() + "  , adressen na delete: ");
        ovChipkaartDAO.delete(randomOV);
        ovChipkaartList = ovChipkaartDAO.findAll();
        System.out.println(ovChipkaartList.size() + "\n");
        
    }
    public Main() throws SQLException{
        testOVChipkaartDAO(ovChipkaartDAOsql, reizigerDAOPsql);

    }

    public static void main(String[] args) throws SQLException{
        new Main();

    }
}
