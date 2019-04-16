package razredi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.UP;


/**
 * Preimenuj metode v pravilne
 * Davek naj bo globalna spremenljivka ki se dodaja
 */

enum Oddelek{
    sadje(211), zelenjava (222), meso(210), mlecni_izdelki(232)
        ;
    private final int koda;
    Oddelek(int koda){
        this.koda=koda;
    }
    public  int getCode(){
        return this.koda;
    }
}

enum Izdelek{
    Banana(6789), Korenje(5566), Mleko(2564), Krompir(9876), Kruh(6464)
    ;
    private  final int koda;
    Izdelek(int koda){
        this.koda=koda;
    }

    public int getCode(){
        return this.koda;
    }
}


public class Artikel implements Searchable {
    private String ime;
    private double cena;
    private String ean;
    private int kolicina;
    private int davek;
    private String drzava;
    private List<String> lastnost;

    public Artikel(String ime, double cena, String ean, int kolicina, int davek){
        this.ime= ime;
        this.cena=cena;
        this.ean=ean;
        this.kolicina=kolicina;
        this.davek=davek;
        this.drzava=eanCountry(ean);
    }

    public void changeIme(String n){
        ime=n;
    }

    public String getIme(){return ime;}
    public void changeCena(double n){
        cena=n;
    }

    public void changeEan(String n){
        ean=n;

    }

    public void changeKolicina(int n){
        kolicina=n;

    }

    public void changeDavek(int n){
        if(n==0||n==1){
            davek=n;
        }
    }

    public void DodajLastnost(String n){
        lastnost.add(n);

    }

    public void BrisiLastnost(int i){
        lastnost.remove(lastnost.get(i-1));
    }

    public double getCena(){
        double temp=0;
        if(davek < 1) {
            temp= getCenaKoncna(cena, 9.5);
        }

        if(davek == 1){
            temp= getCenaKoncna(cena, 22);
        }
            return temp * kolicina;

    }

    public double getCenaKoncna(double cena, double stop){
        double koncna=0;
        double temp=0;
        temp=cena/100;
        temp=temp*stop;
        koncna=cena+temp;
        return koncna;
    } //izracuna ceno z davkom

    public double stopnjaDDV(int davek){
        double l=22;
        if(davek < 1){
            l=9.5;
        }
        return l;
    } //vrne katera stopnja davka je 22% ali 9.5%

    public String artikelString(){
        return  System.lineSeparator()
                + ime
                +"  "+ cena*kolicina + "€ | "+stopnjaDDV(davek) +"% DDV | "
                + " Količina: " + kolicina
                + " | EAN: "+ ean + " | Drzava: "+ drzava
                + " | CheckDigit: "+checkDigit(ean).toString()+System.lineSeparator()
                ;
    }

    static Boolean checkDigit(String ean){
        Boolean validation=false;
        char[] c= ean.toCharArray();
        int[] test=new int[ean.length()+1];
        for (int i = 0; i < ean.length(); i++) {
            test[i] = Character.digit(c[i], 10);
        }
        int[] num = new int[ean.length()+1];

        for (int i = 0; i < ean.length(); i++) {
            num[i] = Character.digit(c[i], 10);
        }
        Boolean t=true;
        int sum=0, s=0;
        int checkdigi=0;
        int zaokrozi=0;
        int x=c.length;
        //mnozi z 3
        for(int i=c.length-2; i>=0;i--){
            if(t==true) {
                num[i] = num[i]*3;
                t=false;
            } else if(t==false){
                t=true;
            }

        }

        //sum
        for(int i=0;i<ean.length()-1;i++){
            sum=sum+num[i];
        }

        zaokrozi=sum/10;//odstranimo enico
        zaokrozi=(zaokrozi*10)+10;//zakrozimo navzgor
        checkdigi=zaokrozi-sum;

        if(checkdigi==test[x-1]){
            validation=true;
        }

        return validation;
    }

    @Override
    public Boolean Search(String niz) {
        Boolean rezultat=false;
        if(niz.equals(ime)) rezultat=true;
        if(niz.equals(ean)) rezultat=true;
        for (int i=0;i<lastnost.size();i++){
            if (niz.equals(lastnost.get(i)))
                rezultat=true;
        }
        if(niz.equals(String.valueOf(cena))) rezultat=true;
        if(niz.equals(String.valueOf(davek))) rezultat=true;
        if (niz.equals(String.valueOf(kolicina)))rezultat=true;
        return rezultat;
    }

    public String eanCountry (String ean){
        String drzava="Unknown";
        int n=0;
        char[] c= ean.toCharArray();
        String vmes =""+c[0]+""+c[1]+""+c[2];
        n=Integer.valueOf(vmes);
        if(000<=n && n <= 19) {
            drzava="ZDA";
        }

        if(020<=n && n <= 29) {
            drzava="ZDA";
        }

        if(060<=n && n <= 139) {
            drzava="ZDA";
        }

        if(300<=n && n <= 379) {
            drzava="Francija";
        }

        if(380==n) {
            drzava="Bulgarija";
        }

        if(383==n) {
            drzava="Slovenija";
        }

        if(385==n) {
            drzava="Hrvaška";
        }

        if(387==n) {
            drzava="BIH";
        }

        if(389==n) {
            drzava="Črna Gora";
        }

        if(400<=n && n <= 440) {
            drzava="Nemčija";
        }

        if(460<=n && n <= 469) {
            drzava="Rusija";
        }

        if(470==n) {
            drzava="Kirgistan";
        }

        if(470==n) {
            drzava="Kirgistan";
        }

        if (n==471)drzava="Taivan";
        if (n==474)drzava="Estonija";
        if (n==475)drzava="Latvija";
        if (n==476)drzava="Azerbejdžan";
        if (n==477)drzava="Litva";
        if (n==478)drzava="Uzbekistan";
        if (n==479)drzava="Šri Lanka";
        if (n==480)drzava="Filipini";
        if (n==481)drzava="Belorusija";
        if (n==482)drzava="Ukrajina";
        if (n==484)drzava="Moldavija";
        if (n==485)drzava="Armenija";
        if (n==486)drzava="Gruzija";
        if (n==487)drzava="Kazahstan";
        if (n==489)drzava="Hong Kong";

        if(450<=n && n <= 459){
            drzava="Japonska";
        }

        if(490<=n && n <= 499){
            drzava="Japonska";
        }

        if(500<=n && n <= 509){
            drzava="VB";
        }

        if (n==520)drzava="Grčija";
        if (n==528)drzava="Libanon";
        if (n==529)drzava="Ciper";
        if (n==530)drzava="Albanija";
        if (n==531)drzava="Makedonija";
        if (n==535)drzava="Malta";
        if (n==539)drzava="Irska";
        if(540<=n && n <= 549){drzava="Belgija & Luksemburg";}
        if (n==560)drzava="Portugalska";
        if (n==569)drzava="Islandija";
        if(570<=n && n <= 579){drzava="Danska";}
        if (n==590)drzava="Polska";
        if (n==594)drzava="Romunija";
        if (n==599)drzava="Madžarska";
        if (n==600 || n==601)drzava="Islandija";
        if (n==608)drzava="Bahrain";
        if (n==609)drzava="Mauricius";
        if (n==611)drzava="Maroko";
        if (n==613)drzava="Alžirija";
        if (n==616)drzava="Kenija";
        if (n==619)drzava="Tunizija";
        if (n==621)drzava="Sirija";
        if (n==622)drzava="Egipt";
        if (n==624)drzava="Libija";
        if (n==625)drzava="Jordanija";
        if (n==626)drzava="Iran";
        if (n==627)drzava="Kuvait";
        if (n==628)drzava="Saudova Arabija";
        if (n==629)drzava="Emirati";
        if(640<=n && n <= 649){drzava="Finska";}
        if(690<=n && n <= 695){drzava="Kitajska";}
        if(700<=n && n <= 709){drzava="Norveška";}
        if (n==729)drzava="Izrael";
        if(730<=n && n <= 739){drzava="Švedska";}
        if (n==740)drzava="Gvatemala";
        if (n==741)drzava="El Salvador";
        if (n==742)drzava="Honduras";
        if (n==743)drzava="Nikaragva";
        if (n==744)drzava="Kostarika";
        if (n==745)drzava="Panama";
        if (n==746)drzava="Dom. republika";
        if (n==750)drzava="Mehika";
        if(754==n || n == 755){drzava="Kanada";}
        if (n==759)drzava="Venezuela";
        if(760<=n && n <= 769){drzava="Švica";}
        if (n==770)drzava="Kolumbija";
        if (n==773)drzava="Kolumbija";
        if (n==775)drzava="Peru";
        if (n==775)drzava="Bolivija";
        if (n==779)drzava="Argentina";
        if (n==780)drzava="Čile";
        if (n==784)drzava="Paragvaj";
        if (n==786)drzava="Ekvador";
        if(789 == n || n == 790){drzava="Brazilija";}
        if(800<=n && n <= 839){drzava="Švica";}
        if(840<=n && n <= 849){drzava="Španija";}
        if (n==850)drzava="Kuba";
        if (n==858)drzava="Slovaška";
        if (n==859)drzava="Češka";
        if (n==860)drzava="Srbija";
        if (n==865)drzava="Mongolija";
        if (n==867)drzava="Severna Koreja";
        if (n==869)drzava="Turčija";
        if(870<=n && n <= 879){drzava="Nizozemska";}
        if (n==880)drzava="Južna Koreja";
        if (n==884)drzava="Kambodja";
        if (n==885)drzava="Tajska";
        if (n==888)drzava="Singapur";
        if (n==890)drzava="Indija";
        if (n==893)drzava="Vietnam";
        if (n==899)drzava="Indonezija";
        if(900<=n && n <= 919){drzava="Avstrija";}
        if(930<=n && n <= 939){drzava="Avstralija";}
        if(940<=n && n <= 949){drzava="Nova Zelandija";}
        if (n==955)drzava="Malezija";
        if (n==958)drzava="Makao";

        return drzava;
    }

}
