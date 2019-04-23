package razredi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;

public class Racun implements Searchable {
    private UUID id = UUID.randomUUID();
    private String izdajatelj;
    private Date datum = new Date();
    private Artikli artikli;
    private double skupaj;
    private int kupec_DDV;
    private Podjetje podjetje;
    Boolean kupec_podjetje;

    public Racun(Artikli artikli, String izdajatelj, Boolean kupec_podjetje, Podjetje podjetje) {
        this.artikli = artikli;
        this.podjetje = podjetje;
        this.izdajatelj = izdajatelj;
        this.kupec_podjetje = kupec_podjetje;
        if (this.kupec_podjetje) {
            this.kupec_DDV = this.dodajDavcnoKupca();
        }

    }

    private int dodajDavcnoKupca() {
        return this.podjetje.getDavcna();
    }

    public void racunString() {
        if (this.podjetje.getDavcni_zavezanec()) {
            System.out.println("--------------------------------------");
            System.out.print(System.lineSeparator());
            System.out.print("ID računa:" + this.id);
            System.out.print(System.lineSeparator());
            System.out.println("Izdajatelj:" + this.izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.print("Kupec:" + this.podjetje.getImePodjetja() + " DDV: SI" + this.kupec_DDV);
            System.out.print(System.lineSeparator());
            System.out.print("Datum: " + this.datum + System.lineSeparator() + "Nakup:");
            System.out.print(System.lineSeparator());
            this.artikli.artikliString();
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.println("Skupni znesek: " + this.getZnesekRacuna() + "€");
            System.out.println("--------------------------------------");
        } else {
            System.out.println("--------------------------------------");
            System.out.print(System.lineSeparator());
            System.out.println("Izdajatelj:" + this.izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.println("ID DDV kupca: ID" + this.izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.print("ID računa: " + this.id + System.lineSeparator() + "Datum: " + this.datum + System.lineSeparator() + "Nakup:");
            System.out.print(System.lineSeparator());
            this.artikli.artikliString();
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.println("Skupni znesek: " + this.getZnesekRacuna() + "€");
            System.out.println("--------------------------------------");
        }

    }

    public void deleteArikel(int i) {
        this.artikli.brisiArtikel(i);
    }

    public BigDecimal getZnesekRacuna() {
        this.skupaj = 0.0D;

        for(int i = 0; i < this.artikli.getSeznamArtikel().size(); ++i) {
            this.skupaj += this.artikli.getSeznamArtikel().get(i).getCena();
        }

        for(int i = 0; i < this.artikli.getSeznamInterniArtikel().size(); ++i) {
            this.skupaj += this.artikli.getSeznamInterniArtikel().get(i).getCena();
        }

        MathContext mc = new MathContext(5, RoundingMode.HALF_EVEN);
        BigDecimal Znesek = (new BigDecimal(this.skupaj)).round(mc);
        return Znesek;
    }

    public Boolean Search(String niz) {
        Boolean rezultat = false;
        if (niz.equals(this.datum.toString())) {
            rezultat = true;
        }

        if (niz.equals(this.izdajatelj)) {
            rezultat = true;
        }

        if (niz.equals(this.id.toString())) {
            rezultat = true;
        }

        if (niz.equals(this.artikli.getSeznamArtikel().toString())) {
            rezultat = true;
        }

        if (niz.equals(this.podjetje.ime)) {
            rezultat = true;
        }

        if (niz.equals(String.valueOf(this.skupaj))) {
            rezultat = true;
        }

        if (niz.equals(String.valueOf(this.kupec_DDV))) {
            rezultat = true;
        }

        return rezultat;
    }
}