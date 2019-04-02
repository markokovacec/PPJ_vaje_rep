package razredi;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Invoices implements JsonSupport {
    private List<SeznamRacunov> seznam;

    public Invoices (){
        this.seznam =  new ArrayList<>();
    }

    void addInvoiceList(SeznamRacunov a){seznam.add(a);}

    public List<SeznamRacunov> getSeznam() {
        return seznam;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        List<SeznamRacunov> seznam = getSeznam();
        String json = gson.toJson(seznam);
        return json;
    }

    @Override
    public String fromJson(String json) {
        Gson gson = new Gson();
        List<SeznamRacunov> seznam2 = gson.fromJson(json, (Type) seznam);
        return null;
    }
}
