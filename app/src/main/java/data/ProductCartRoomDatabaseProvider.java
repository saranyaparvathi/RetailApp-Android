package data;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Inject;

public class ProductCartRoomDatabaseProvider {

    private static final String DATABASE = "product_cart";

    private final Context context;
    private static ProductCartRoomDatabase productCartRoomDatabase = null;

    public ProductCartRoomDatabaseProvider(Context context) {
        this.context = context;
    }

    public ProductCartRoomDatabase get() {
        if (productCartRoomDatabase == null) {
            productCartRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ProductCartRoomDatabase.class, DATABASE)
                    .build();
        }
        return productCartRoomDatabase;
    }
}
