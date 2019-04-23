package razredi;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

//TODO checkDigit metoda

public class Artikli {
    private List<Artikel> seznam;
    List<Interni_Izdelek> list;

    public Artikli() {
        this.seznam = new ArrayList<>();
        this.list= new ArrayList<>();
    }


    public List<Artikel> getSeznamArtikel() {
        return seznam;
    }

    public List<Interni_Izdelek> getSeznamInterniArtikel(){return  list;}

    public void getArtikel(int i){ System.out.print(seznam.get(i).artikelString());}

    public void dodaj_artikel(Artikel a) {
        seznam.add(a);
    }

    public void dodajInterniArtikel(Interni_Izdelek a) {
        list.add(a);
    }

    public void brisiArtikel(int i){
        seznam.remove(seznam.get(i-1));
    }

    public void brisiInterniArtikel(int i){
        list.remove(list.get(i-1));
    }

    public void seznam_artikel_String() {
        //int i = seznam.size();
        for (int i=0; i< seznam.size();i++) {
            System.out.print(seznam.get(i).artikelString());
        }
    }

    public void list_interni_String(){
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).interniString());
        }
    }

    void artikliString(){
        seznam_artikel_String();
        list_interni_String();
    }

    public String toJson() {
        Gson gson = new Gson();
        List<Artikel> seznam = getSeznamArtikel();
        String json = gson.toJson(seznam);
        return json;
    }


    public String fromJson(String json) {
        Gson gson = new Gson();
        List<Artikel> seznam2 = gson.fromJson(json, (Type) seznam);
        return null;
    }
}
