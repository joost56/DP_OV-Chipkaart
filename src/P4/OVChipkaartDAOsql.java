package P4;

import P2.Reiziger;
import P2.ReizigerDAO;

import java.sql.*;
import java.util.List;

public class OVChipkaartDAOsql {
    private Connection conn;
    private OVChipkaart ovChipkaart;
    private OVChipkaartDAO ovChipkaartDAO;
    private List<OVChipkaart> ovChipkaarten;


    public OVChipkaartDAOsql(Connection conn){
        this.conn = conn;
    }

    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO reiziger values (?,?,?,?,?) ");
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
        preparedStatement.setDate(2, (Date) ovChipkaart.getGeldig_tot());
        preparedStatement.setInt(3, ovChipkaart.getKlasse());
        preparedStatement.setInt(4, ovChipkaart.getSaldo());
        preparedStatement.setInt(5, ovChipkaart.getReiziger_id());

        return preparedStatement.execute();
    }

    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ?) ");
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
        preparedStatement.setDate(2, (Date) ovChipkaart.getGeldig_tot());
        preparedStatement.setInt(3, ovChipkaart.getKlasse());
        preparedStatement.setInt(4, ovChipkaart.getSaldo());
        preparedStatement.setInt(5, ovChipkaart.getReiziger_id());

        return preparedStatement.execute();
    }

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer = ?");
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());

        return preparedStatement.execute();
    }
    public OVChipkaart findByKaartnummer(int id) {
        if (id == ovChipkaart.getKaart_nummer()) {
            return ovChipkaart;
        }
        return null;
    }
    public List<OVChipkaart> findAll() throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM ov_chipkaart");
        rs.close();
        st.close();
        return ovChipkaarten;
    }
}
