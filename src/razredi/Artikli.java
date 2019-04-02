package razredi;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

//TODO checkDigit metoda

public class Artikli {
    private List<Artikel> seznam;

    public Artikli() {
        this.seznam = new ArrayList<>();
    }


    public List<Artikel> getSeznam() {
        return seznam;
    }

    public void getArtikel(int i){ System.out.print(seznam.get(i).artikelString());}

    public void dodajSeznam(Artikel a) {
        seznam.add(a);
    }

    public void brisiArtikel(int i){
        seznam.remove(seznam.get(i-1));
    }

    public void seznamString() {
        //int i = seznam.size();
        for (int i=0; i< seznam.size();i++) {
            System.out.print(seznam.get(i).artikelString());
        }
    }

    public String toJson() {
        Gson gson = new Gson();
        List<Artikel> seznam = getSeznam();
        String json = gson.toJson(seznam);
        return json;
    }


    public String fromJson(String json) {
        Gson gson = new Gson();
        List<Artikel> seznam2 = gson.fromJson(json, (Type) seznam);
        return null;
    }
}
