package razredi;

import com.google.gson.Gson;

import java.lang.reflect.Type;
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

    public String toJson() {
        Gson gson = new Gson();
        List<Podjetje> Podjetja = getPodjetja();
        String json = gson.toJson(Podjetja);
        return json;
    }


    public String fromJson(String json) {
        Gson gson = new Gson();
        List<Podjetje> Podjetja2 = gson.fromJson(json, (Type) Podjetja);
        return null;
    }
}
