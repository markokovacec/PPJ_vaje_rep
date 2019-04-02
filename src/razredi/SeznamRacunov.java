package razredi;

import java.util.List;
import java.util.ArrayList;

public class SeznamRacunov {
    private List<Racun> seznam;

    public SeznamRacunov() {
        this.seznam= new ArrayList<>();
    }

    public void dodajRacun(Racun a){
        seznam.add(a);
    }

    public void brisiRacun(int id){
        seznam.remove(seznam.get(id-1));
    }

    public List<Racun> getSeznam(){
        return seznam;
    }

    public void izpisiRacune(){
        System.out.println("***********SPISEK RACUNOV********************");
        for(int i=0;i<seznam.size();i++){
            seznam.get(i).racunString();
        }
        System.out.println("****************KONEC************************");

    }

}
