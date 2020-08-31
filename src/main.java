import java.sql.*;

public class main {
    public static void main(String[] args) {

        try {
            String dbUrl = "jdbc:postgresql://localhost/ovchip";
            String user = "postgres";
            String pass = "welkom123";

            Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
            Statement st = myConn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reiziger");
            System.out.println("Alle reizigers:");
            while (rs.next())
            {
                System.out.println("#" + rs.getString("reiziger_id") + ": " + rs.getString("voorletters") + ". " + rs.getString("tussenvoegsel") + " " + rs.getString("achternaam") + "(" + rs.getString("geboortedatum") + ")" );
            }
            rs.close();
            st.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
