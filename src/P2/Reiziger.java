package P2;

import P3.Adres;
import P4.OVChipkaart;

import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private String geboortedatum;
    private Adres adres;
    private List<OVChipkaart> ovChipkaarten;

    public Reiziger(){
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return reiziger_id;
    }

    public void setId(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getNaam() {
        return voorletters + tussenvoegsel + achternaam;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    @Override
    public String toString() {
        return "#" + reiziger_id + ": " + voorletters + ". " + tussenvoegsel + " " + achternaam + " (" + geboortedatum + ")" + adres;
    }
}
