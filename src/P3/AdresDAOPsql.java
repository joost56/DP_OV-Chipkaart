package P3;

import P2.Reiziger;
import P2.ReizigerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save (Adres adres) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO adres VALUES (adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id)");
        preparedStatement.setInt(1, adres.getAdres_id());
        preparedStatement.setString(2, adres.getPostcode());
        preparedStatement.setString(3, adres.getHuisnummer());
        preparedStatement.setString(4, adres.getStraat());
        preparedStatement.setString(5, adres.getWoonplaats());
        preparedStatement.setInt(6, adres.getReiziger_id());
        preparedStatement.execute();
        return true;

    }

    @Override
    public boolean update (Adres adres) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE adres VALUES (adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id)");
        preparedStatement.setInt(1, adres.getAdres_id());
        preparedStatement.setString(2, adres.getPostcode());
        preparedStatement.setString(3, adres.getHuisnummer());
        preparedStatement.setString(4, adres.getStraat());
        preparedStatement.setString(5, adres.getWoonplaats());
        preparedStatement.setInt(6, adres.getReiziger_id());
        preparedStatement.execute();
        return true;

    }

    @Override
    public boolean delete (Adres adres) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM adres VALUES (adres_id = ?)");
        preparedStatement.setInt(1, adres.getAdres_id());
        preparedStatement.execute();
        return true;

    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT adres_id, reiziger_id FROM adres");
        List<Adres> adresList = findAll();

        for (Adres adres : adresList){
            if (reiziger.getId() == adres.getAdres_id()){
                return adres;
            }
        }
        return null;
    }

    @Override
    public List<Adres> findAll(){

        return null;
    }
}
