import razredi.*;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

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

        Interni_Izdelek i1 = new Interni_Izdelek(Izdelek.Banana,Oddelek.sadje, "0023", 0.22);
        Interni_Izdelek i2 = new Interni_Izdelek(Izdelek.Korenje,Oddelek.zelenjava, "0010", 0.12);
        Interni_Izdelek i3 = new Interni_Izdelek(Izdelek.Kruh,Oddelek.pekovski_izdelki, "0130", 0.08);

        a1.eanCountry(e);
        a1.eanCountry(ee);
        a1.eanCountry(eee);


        Artikli nevem= new Artikli();
        Podjetje p1 = new Podjetje("Blabla d.o.o.", 1196332000, 56498789,true);
        Podjetje p2 = new Podjetje("Goran S.P.",1468952000, 12544487,false);
        Podjetje p3 = new Podjetje("ŽUPNIJA STARA CERKEV",1753037000,91803721,false);

        nevem.dodaj_artikel(a1);System.out.print(System.lineSeparator());
        nevem.dodaj_artikel(a2);System.out.print(System.lineSeparator());
        nevem.dodaj_artikel(a3);System.out.print(System.lineSeparator());
        nevem.dodajInterniArtikel(i1);
        nevem.dodajInterniArtikel(i2);
        nevem.dodajInterniArtikel(i3);

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

        Invoices in = new Invoices();
        in.addInvoiceList(sez);
        in.writeJson();
        in.readJson();

        sez.izpisiRacune();

    }

}
