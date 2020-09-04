package P2;

import java.sql.*;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private Reiziger reiziger;
    private ReizigerDAO reizigerDAO;
    private List<Reiziger> reizigers;


    public ReizigerDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
       PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO reiziger values (?,?,?,?,?) ");
       preparedStatement.setInt(1, reiziger.getId());
       preparedStatement.setString(2, reiziger.getVoorletters());
       preparedStatement.setString(3, reiziger.getTussenvoegsel());
       preparedStatement.setString(4, reiziger.getAchternaam());
       preparedStatement.setDate(5, java.sql.Date.valueOf(reiziger.getGeboortedatum()));

        return preparedStatement.execute();
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE reiziger SET reiziger_id = ?, voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ?) ");
        preparedStatement.setInt(1, reiziger.getId());
        preparedStatement.setString(2, reiziger.getVoorletters());
        preparedStatement.setString(3, reiziger.getTussenvoegsel());
        preparedStatement.setString(4, reiziger.getAchternaam());
        preparedStatement.setDate(5, java.sql.Date.valueOf(reiziger.getGeboortedatum()));

        return preparedStatement.execute();
    }

    @Override
    public boolean delete(Reiziger reiziger ) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id = ?, voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? ");
        preparedStatement.setInt(1, reiziger.getId());
        preparedStatement.setString(2, reiziger.getVoorletters());
        preparedStatement.setString(3, reiziger.getTussenvoegsel());
        preparedStatement.setString(4, reiziger.getAchternaam());
        preparedStatement.setDate(5, java.sql.Date.valueOf(reiziger.getGeboortedatum()));

        return preparedStatement.execute();
    }
    public Reiziger findById(int id) {
        if (id == reiziger.getId()) {
            return reiziger;
        }
        return null;
    }
    public List<Reiziger> findByGbdatum(String datum){
        if (datum == reiziger.getGeboortedatum()){
            reizigers.add(reiziger);
            return reizigers;
        }
    return null;
    }
    public List<Reiziger> findAll() throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM reiziger");
        while (rs.next())
        {
            reizigers.add(reiziger);
        }
        rs.close();
        st.close();
        return reizigers;
    }

}
