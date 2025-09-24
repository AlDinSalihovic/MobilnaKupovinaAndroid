package com.example.projekt2septembar;

public class Kreiranje {

    String ID;
    String Korisnicko_Ime;
    String Email;
    String Password;

    public Kreiranje(String ID, String korisnicko_Ime, String email, String password) {

        this.ID = ID;
        this.Korisnicko_Ime = korisnicko_Ime;
        this.Email = email;
        this.Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKorisnicko_Ime() {
        return Korisnicko_Ime;
    }

    public void setKorisnicko_Ime(String korisnicko_Ime) {
        Korisnicko_Ime = korisnicko_Ime;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
