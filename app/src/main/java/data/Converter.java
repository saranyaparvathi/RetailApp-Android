package data;

import android.arch.persistence.room.TypeConverter;

import com.fordlabs.innovation.retailapp.ProductItemViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static ProductItemViewModel stringToList(String data) {
        Type listType = new TypeToken<ProductItemViewModel>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(ProductItemViewModel productItemViewModel) {
        return gson.toJson(productItemViewModel);
    }
}
