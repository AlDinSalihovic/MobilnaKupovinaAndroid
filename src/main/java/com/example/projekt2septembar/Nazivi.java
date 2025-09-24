package com.example.projekt2septembar;

public class Nazivi {

    String naziv;
    String cijena;
    int slika;

    public Nazivi(String naziv, String cijena, int slika) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public int getSlika() {
        return slika;
    }

    public void setSlika(int slika) {
        this.slika = slika;
    }
}
