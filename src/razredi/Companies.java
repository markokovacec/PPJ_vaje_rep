package razredi;

import java.util.ArrayList;
import java.util.List;

public class Companies {

    private List<Podjetje> Podjetja;
     public Companies(){
         this.Podjetja = new ArrayList<>();
     }

     public void addPodjetje(Podjetje a){Podjetja.add(a);}

    public List<Podjetje> getPodjetja() {
        return Podjetja;
    }
}
