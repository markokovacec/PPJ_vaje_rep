package razredi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Invoices implements JsonSupport {
    private List<SeznamRacunov> seznam;

    public Invoices (){
        this.seznam =  new ArrayList<>();
    }

    public void addInvoiceList(SeznamRacunov a){seznam.add(a);}

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
        Type fooType = new TypeToken<List<SeznamRacunov>>(){}.getType();
        List<SeznamRacunov> seznam2 = gson.fromJson(json,fooType);
        return seznam2.toString();
    }

    public void writeJson() throws IOException {
        File file=new File ("invoices.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        System.out.println("Writing JSON object to file");
        System.out.println("-----------------------");
        String json = toJson();
        System.out.println(json);
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
    }

    public void readJson() throws IOException {
        //FileReader reader = new FileReader("invoice.json");
        String json = new String(Files.readAllBytes(Paths.get("invoices.json")));
        String sez = fromJson(json);

        System.out.println("Reading JSON object from file");
        System.out.println("-----------------------");
        System.out.println(json);
    }
}
