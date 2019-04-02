package razredi;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.UUID;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * Datum in ID se lahko nastavitva tukaj, popravi datum, naj ne bo String.
 */



public class Racun implements Searchable {
    private UUID id;
    private String izdajatelj;
    //private int id;
    //za id je boljse uporabit UUID uudi= UUID.randomUUID(); da ne pride do podvojitve ID
    private Date datum;
    private Artikli artikli;
    private double skupaj;
    private int kupec_DDV;
    private Podjetje podjetje;
    Boolean kupec_podjetje;
   // private String[] davek={"22%","9.5%"};

    public Racun (Artikli artikli, String izdajatelj, Boolean kupec_podjetje, Podjetje podjetje){
        //this.id=id;
        //this.datum=datum;
        this.id=UUID.randomUUID();
        this.datum= new Date();
        this.artikli=artikli;
        this.podjetje=podjetje;
        this.izdajatelj=izdajatelj;
        this.kupec_podjetje=kupec_podjetje;
        if(this.kupec_podjetje==true){
            kupec_DDV=dodajDavcnoKupca();
        }
    }


    private int dodajDavcnoKupca (){
        return podjetje.getDavcna();
    }



    public void racunString(){
        if(podjetje.getDavcni_zavezanec()==true) {
            System.out.println("--------------------------------------");
            System.out.print(System.lineSeparator());
            System.out.print("ID računa:"+id);
            System.out.print(System.lineSeparator());
            System.out.println("Izdajatelj:" + izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.print("Kupec:"
                    +podjetje.getImePodjetja() +" "
                    +"DDV: SI"+kupec_DDV
                    );
            System.out.print(System.lineSeparator());
            System.out.print("Datum: " + datum + System.lineSeparator()
                    + "Nakup:"
            );
            System.out.print(System.lineSeparator());
            artikli.seznamString();
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.println("Skupni znesek: " + getZnesekRacuna() + "€");
            System.out.println("--------------------------------------");
        }
        else {

            System.out.println("--------------------------------------");
            System.out.print(System.lineSeparator());
            System.out.println("Izdajatelj:" + izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.println("ID DDV kupca: ID" + izdajatelj);
            System.out.print(System.lineSeparator());
            System.out.print("ID računa: " + id + System.lineSeparator()
                    + "Datum: " + datum + System.lineSeparator()
                    + "Nakup:"
            );
            System.out.print(System.lineSeparator());
            artikli.seznamString();
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            System.out.println("Skupni znesek: " + getZnesekRacuna() + "€");
            System.out.println("--------------------------------------");
        }
    }

    public void deleteArikel(int i){
        artikli.brisiArtikel(i);
    }

    public BigDecimal getZnesekRacuna (){

        skupaj=0;
        for(int i=0;i<artikli.getSeznam().size();i++){
            skupaj=skupaj + artikli.getSeznam().get(i).getCena();
        }
        //System.out.println("Cena brez dveh decimalk: "+skupaj);

        MathContext mc = new MathContext(5, HALF_EVEN);
        BigDecimal Znesek = new BigDecimal(skupaj).round(mc);



        return Znesek;

    }

    @Override
    public Boolean Search(String niz) {
        Boolean rezultat=false;
        if(niz.equals(this.datum.toString())) rezultat=true;
        if(niz.equals(izdajatelj))rezultat=true;
        if(niz.equals(id.toString())) rezultat=true;
        if(niz.equals(artikli.getSeznam().toString()))rezultat=true;
        if(niz.equals(podjetje.ime.toString()))rezultat=true;
        if(niz.equals(String.valueOf(skupaj)))rezultat=true;
        if (niz.equals(String.valueOf(kupec_DDV))) rezultat=true;
        return rezultat;
    }
}
