package P5;

import P2.Reiziger;
import P2.ReizigerDAO;
import P2.ReizigerDAOPsql;
import P4.OVChipkaart;
import P4.OVChipkaartDAO;
import P4.OVChipkaartDAOsql;
import Verbinding.DBVerbinding;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    OVChipkaart ovChipkaart;
    Product product;

    Connection conn = DBVerbinding.getConnection();

    OVChipkaartDAOsql ovChipkaartDAOsql = new OVChipkaartDAOsql(conn);
    ProductDAOPsql productDAOPsql = new ProductDAOPsql(conn);
    ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(conn);

    private static void testProductDAO(ProductDAO productDAO, OVChipkaartDAO ovChipkaartDAO, ReizigerDAO reizigerDAO) throws SQLException {
        System.out.println("\n---------- Test dao.ProductDAO -------------");

        Product randomProduct = new Product(103, "Studentenreisproduct", "Gratis of met korting reizen als je studeert", 0);

        //findall
        List<Product> productList3 = productDAO.findAll();
        System.out.println("[Test]Uitvoer na het gebruik van de methode ProductDAO.findAll() \n");
        for (Product product : productList3) {
            System.out.println(product);
        }

        //save
        System.out.println("\n \n[Test]Uitvoer na het gebruik van de methode ProductDAO.save() en ProductDAO.findAll() :\n");
        productDAO.save(randomProduct);
        List<Product> productList2 = productDAO.findAll();
        for (Product p : productList2) {
            System.out.println(p);
        }

        //update
        System.out.println("\n\n[Test]Voor ProductDAO.update():" + randomProduct.toString());
        randomProduct.setNaam("hallo");
        productDAO.update(randomProduct);
        System.out.println("[Test]Na ProductDAO.update():" + randomProduct.toString() +"\n \n");

        String gbdatum = "2002-03-19";
        Reiziger randomReiziger = new Reiziger(203, "J", "de", "Buiting", java.sql.Date.valueOf(gbdatum));
        reizigerDAO.save(randomReiziger);

        String geldig_tot = "3000-12-12";
        OVChipkaart randomChip = new OVChipkaart(53, java.sql.Date.valueOf(geldig_tot), 1, 12, randomReiziger);
        randomChip.addProduct(randomProduct);
        randomProduct.addOVChipkaarten(randomChip);
        ovChipkaartDAO.save(randomChip);


        //findbyOVChipkaart
        System.out.println("[Test] Uitvoer na het gebruik van ProductDAO.findByOVChipkaart()");
        List<Product> products = productDAO.findByOVChipkaart(randomChip);
        for (Product product : products) {
            System.out.println(product);
        }

        //delete
        List<Product> productList = productDAO.findAll();
        System.out.print("\n \n[Test] producten voor delete: " + productList.size() + "\n");
        productDAO.delete(randomProduct);
        List<Product> productList1 = productDAO.findAll();
        System.out.println("[Test] producten na delete: " + productList1.size());

    }

    public Main() throws SQLException{
        testProductDAO(productDAOPsql, ovChipkaartDAOsql, reizigerDAOPsql);
    }

    public static void main(String[] args)throws SQLException {
        new Main();
    }
}
