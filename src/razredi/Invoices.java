package razredi;

import java.util.ArrayList;
import java.util.List;

public class Invoices {
    private List<SeznamRacunov> seznam;

    public Invoices (){
        this.seznam =  new ArrayList<>();
    }

    void addInvoiceList(SeznamRacunov a){seznam.add(a);}

    public List<SeznamRacunov> getSeznam() {
        return seznam;
    }
}
