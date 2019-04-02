import razredi.SeznamRacunov;
import razredi.Artikel;
import razredi.Racun;
import razredi.Artikli;
import razredi.Podjetje;


public class Main {

    public static void main(String[] args){

        String s="Ovitek";
        String ss="Steklo";
        String sss="Torbica";

        int i=1;
        SeznamRacunov sez= new SeznamRacunov();


        double c=12.00;
        double cc=17.00;
        double ccc=21.00;

        String e="626345789688";
        String ee="5991345789628";
        String eee="3835659856593";


        //kreiranje artikolov

        Artikel a1= new Artikel(s,c,e, i+2,1);
        Artikel a2= new Artikel(ss,cc,ee,i,0);
        Artikel a3= new Artikel(sss,ccc,eee,i+3,1);

        a1.eanCountry(e);
        a1.eanCountry(ee);
        a1.eanCountry(eee);


        Artikli nevem= new Artikli();
        Podjetje p1 = new Podjetje("Fukich d.o.o.", 1196332000, 56498789,true);
        Podjetje p2 = new Podjetje("Goran Karan S.P.",1468952000, 12544487,false);
        Podjetje p3 = new Podjetje("ŽUPNIJA STARA CERKEV",1753037000,91803721,false);

        nevem.dodajSeznam(a1);System.out.print(System.lineSeparator());
        nevem.dodajSeznam(a2);System.out.print(System.lineSeparator());
        nevem.dodajSeznam(a3);System.out.print(System.lineSeparator());

        //izdelava racuna in seznama
       /* System.out.print(System.lineSeparator());
        System.out.print("Vpisite ime izdajatelja:");
        System.out.print(System.lineSeparator());*/
        String izdajatelj= "Marko Kovačec"; //vpis izdajatelja



        Racun rac1 = new Racun(nevem, izdajatelj,true,p1);
        Racun rac2 = new Racun(nevem, izdajatelj,false,p2);
        Racun rac3 = new Racun(nevem, izdajatelj,false,p3);
        sez.dodajRacun(rac1);
        sez.dodajRacun(rac2);
        sez.dodajRacun(rac3);

        sez.izpisiRacune();

    }

}
