package razredi;

import com.google.gson.*;

public interface JsonSupport {
    String toJson();
    String fromJson(String json);

}
