package P4;

import P2.Reiziger;
import P2.ReizigerDAO;
import P2.ReizigerDAOPsql;
import P3.Adres;
import P3.AdresDAO;
import Verbinding.DBVerbinding;
import jdk.jfr.DataAmount;

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


    private static void testOVChipkaartDAO(OVChipkaartDAO ovDAO, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        String gbdatum = "2002-03-19";
        Reiziger randomReiziger = new Reiziger(9, "J", "van", "Buiting", java.sql.Date.valueOf(gbdatum));
        rdao.save(randomReiziger);

        //findall
        List<OVChipkaart> ovChipkaartList = ovDAO.findAll();
        System.out.println("[Test]Uitvoer na het gebruik van de methode OVChipkaartDAO.findAll() \n");
        for (OVChipkaart ov : ovChipkaartList) {
            System.out.println(ov);
        }

        String geldig_tot1 = "2030-02-02";
        OVChipkaart randomOV = new OVChipkaart(4, java.sql.Date.valueOf(geldig_tot1), 2, 100, randomReiziger);

        //save
        System.out.println("\n \n[Test]Uitvoer na het gebruik van de methode OVChipkaartDAO.save() en OVChipkaartDAO.findAll() :\n");
        ovDAO.save(randomOV);
        List<OVChipkaart> ovList1 = ovDAO.findAll();
        for (OVChipkaart ov : ovList1) {
            System.out.println(ov);
        }

        //update
        randomOV.setSaldo(200);
        System.out.println("\n\n[Test]Voor OVChipkaartDAO.update():" + ovDAO.findByReiziger(randomReiziger));
        ovDAO.update(randomOV);
        System.out.println("[Test]Na OVChipkaartDAO.update():" + ovDAO.findByReiziger(randomReiziger) +"\n \n");

        //findbyreiziger
        List<OVChipkaart> ovchips = ovDAO.findByReiziger(randomReiziger);
        System.out.println("[Test] Uitvoer na het gebruik van OVChipkaartDAO.findByReiziger()");
        for (OVChipkaart ov : ovchips) {
            System.out.println(ov);
        }

        //delete
        List<OVChipkaart> ovList2 = ovDAO.findAll();
        System.out.print("\n \n[Test] Adressen voor delete: " + ovList2.size() + "\n");
        ovDAO.delete(randomOV);
        List<OVChipkaart> ovList3 = ovDAO.findAll();
        System.out.println("[Test] Adressen na delete: " + ovList3.size());
    }
    public Main() throws SQLException{
        testOVChipkaartDAO(ovChipkaartDAOsql, reizigerDAOPsql);

    }

    public static void main(String[] args) throws SQLException{
        new Main();

    }
}
