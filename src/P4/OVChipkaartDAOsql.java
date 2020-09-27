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
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO reiziger values (?,?,?,?,?) ");
        preparedStatement.setInt(1, ovChipkaart.getKaartnummer());
        preparedStatement.setDate(2, ovChipkaart.getGeldig_tot());
        preparedStatement.setInt(3, ovChipkaart.getKlasse());
        preparedStatement.setInt(4, ovChipkaart.getSaldo());
        preparedStatement.setInt(5, ovChipkaart.getReiziger_id());

        return preparedStatement.execute();
    }

    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        int nummer = ovChipkaart.getKaartnummer();
        Date geldig_tot = ovChipkaart.getGeldig_tot();
        int klasse = ovChipkaart.getKlasse();
        int saldo = ovChipkaart.getSaldo();
        int id = ovChipkaart.getReiziger_id();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE ov_chipkaart SET kaart_nummer = " + nummer + ", geldig_tot = '" + geldig_tot + "', klasse = '" + klasse + "', saldo = '" + saldo + "', reiziger_id = '" + id);
        return true;
    }

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer = ?");
        preparedStatement.setInt(1, ovChipkaart.getKaartnummer());

        return preparedStatement.execute();
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

            OVChipkaart ovChipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, reiziger.getReiziger_id());
            ovChipkaartList.add(ovChipkaart);

        }
        return ovChipkaartList;
    }

    public List<OVChipkaart> findAll() throws SQLException {
        String query = "SELECT * FROM ov_chipkaart";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<OVChipkaart> ovChipkaartList = new ArrayList<>();

        while (resultSet.next()) {
            int nummer = resultSet.getInt(1);
            Date geldig_tot = resultSet.getDate(2);
            int klasse = resultSet.getInt(3);
            int saldo = resultSet.getInt(4);
            int reiziger_id = resultSet.getInt(5);

            ReizigerDAOPsql rdao = new ReizigerDAOPsql(conn);
            List<Reiziger> lijst = rdao.findAll();
            for (var r : lijst) {
                if (r.getReiziger_id() == reiziger_id) {
                    OVChipkaart ov = new OVChipkaart(nummer, geldig_tot, klasse, saldo, r.getReiziger_id());

                    ovChipkaartList.add(ov);
                }
            }

        }return ovChipkaartList;
    }
}
