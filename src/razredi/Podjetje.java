package razredi;

import java.util.Date;

public class Podjetje implements Searchable {
    String ime;
    int maticna=0;
    int davcna=0;
    Boolean davcni_zavezanec;
    Date datum_vpisa;

    public Podjetje(String ime, int maticna, int davcna, Boolean davcnzavezanec){
        this.ime=ime;
        this.maticna=maticna;
        this.davcna=davcna;
        this.davcni_zavezanec=davcnzavezanec;
        this.datum_vpisa= new Date();
    }

    public String getImePodjetja(){ return ime;}
    public void setImePodjetja(String ime){this.ime=ime;}

    public int getMaticna(){return maticna;}
    public void setMaticna(int maticna){this.maticna=maticna;}

    public int getDavcna(){return davcna;}
    public void setDavcna(int davcna) { this.davcna = davcna;}

    public Boolean getDavcni_zavezanec(){return davcni_zavezanec;}
    public void setDavcni_zavezanec(Boolean davcni_zavezanec) { this.davcni_zavezanec = davcni_zavezanec; }

    @Override
    public Boolean Search(String niz) {
        Boolean rezultat=false;

        if (niz.equals(ime)) rezultat=true;
        if (niz.equals(String.valueOf(maticna))) rezultat=true;
        if (niz.equals(String.valueOf(davcni_zavezanec)))rezultat=true;
        if (niz.equals(datum_vpisa.toString()))rezultat=true;
        return rezultat;
    }
}
