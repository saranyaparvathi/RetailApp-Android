package data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Singleton;

@Singleton
@Database(entities = {ProductCart.class}, version = 1, exportSchema = false)
public abstract class ProductCartRoomDatabase extends RoomDatabase {

    public abstract ProductCartDao productCartDao();

    private static volatile ProductCartRoomDatabase INSTANCE;

    static ProductCartRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductCartRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductCartRoomDatabase.class, "product_cart")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
