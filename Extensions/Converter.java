package Extensions;

import Adapters.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public final class Converter {
    public static <T> T Deserialize(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        return (T)gson.fromJson(json, tClass);
    }
}
