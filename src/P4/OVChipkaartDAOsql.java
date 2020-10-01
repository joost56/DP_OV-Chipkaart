package P4;

import P2.Reiziger;
import P2.ReizigerDAO;
import P2.ReizigerDAOPsql;
import P3.Adres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOsql implements OVChipkaartDAO {
    private Connection conn;

    public OVChipkaartDAOsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        int nummer = ovChipkaart.getKaartnummer();
        Date geldig_tot = ovChipkaart.getGeldig_tot();
        int klasse = ovChipkaart.getKlasse();
        int saldo = ovChipkaart.getSaldo();
        int id = ovChipkaart.getReiziger().getReiziger_id();

        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (" + nummer + ", '" + geldig_tot + "', " + klasse + ", " + saldo + ", " + id + ")");
        return true;
    }

    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        int nummer = ovChipkaart.getKaartnummer();
        Date geldig_tot = ovChipkaart.getGeldig_tot();
        int klasse = ovChipkaart.getKlasse();
        int saldo = ovChipkaart.getSaldo();
        int id = ovChipkaart.getReiziger().getReiziger_id();

        Statement statement = conn.createStatement();
        statement.executeUpdate("UPDATE ov_chipkaart SET geldig_tot = '" + geldig_tot + "', klasse = '" + klasse + "', saldo = '" + saldo + "', reiziger_id = '" + id + "'where kaart_nummer = " + nummer);

        return true;
    }

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        int nummer = ovChipkaart.getKaartnummer();
        Statement statement = conn.createStatement();

        statement.executeUpdate("DELETE FROM ov_chipkaart where kaart_nummer = " + nummer);

        return true;
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = " + reiziger.getReiziger_id();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<OVChipkaart> ovChipkaartList = new ArrayList<>();

        while (rs.next()) {
            int kaartnummer = rs.getInt(1);
            Date geldig_tot = rs.getDate(2);
            int klasse = rs.getInt(3);
            int saldo = rs.getInt(4);

            OVChipkaart ovChipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger);
            ovChipkaartList.add(ovChipkaart);

        }
        return ovChipkaartList;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        String query = "SELECT * FROM ov_chipkaart";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<OVChipkaart> chipList = new ArrayList<>();

        while (rs.next()) {
            int nummer = rs.getInt(1);
            Date geldig_tot = rs.getDate(2);
            int klasse = rs.getInt(3);
            int saldo = rs.getInt(4);
            int reiziger_id = rs.getInt(5);

            ReizigerDAOPsql rdao = new ReizigerDAOPsql(conn);
            List<Reiziger> findAllList = rdao.findAll();
            for (var reiziger : findAllList) {
                if (reiziger.getReiziger_id() == reiziger_id) {
                    OVChipkaart ov = new OVChipkaart(nummer, geldig_tot, klasse, saldo, reiziger);//maakt een ov aan alleen als de reiziger bestaat
                    chipList.add(ov);
                }
            }

        }return chipList;
    }
}
