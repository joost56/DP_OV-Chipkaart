package P5;

import P4.OVChipkaart;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private String prijs;
    List<OVChipkaart> ovkaarten = new ArrayList<>();


    public Product(int product_nummer, String naam, String beschrijving, String prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvkaarten() {
        return ovkaarten;
    }

    public void setOvkaarten(List<OVChipkaart> ovkaarten) {
        this.ovkaarten = ovkaarten;
    }
}
