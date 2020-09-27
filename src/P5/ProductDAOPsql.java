package P5;

import P4.OVChipkaart;
import P4.OVChipkaartDAOsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;

    public ProductDAOPsql (Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Product product)throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO product VALUES(?,?,?,?)");
        preparedStatement.setInt(1, product.getProduct_nummer());
        preparedStatement.setString(2, product.getNaam());
        preparedStatement.setString(3, product.getBeschrijving());
        preparedStatement.setString(4, product.getPrijs());

        OVChipkaartDAOsql ovChipkaartDAOsql = new OVChipkaartDAOsql(conn);


        return preparedStatement.execute();
    }

    @Override
    public boolean update(Product product) throws SQLException{
        int nummer = product.getProduct_nummer();
        String naam = product.getNaam();
        String beschrijving = product.getBeschrijving();
        String prijs = product.getPrijs();

        Statement statement = conn.createStatement();

        statement.executeUpdate("UPDATE product SET product_nummer = " + nummer + "naam = " + naam + "beschrijving = " + beschrijving + "prijs = "  + prijs);
        return true;
    }

    @Override
    public boolean delete(Product product) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM product WHERE product_nummer = ?");
        preparedStatement.setInt(1, product.getProduct_nummer());

        return preparedStatement.execute();
    }
}
