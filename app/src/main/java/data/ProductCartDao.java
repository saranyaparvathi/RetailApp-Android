package data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.fordlabs.innovation.retailapp.ProductItemViewModel;

import java.util.List;

@Dao
public interface ProductCartDao {

    @Insert
    void insert(ProductCart productCart);

    @Query("DELETE FROM product_cart")
    void deleteAll();

    @Query("SELECT * from product_cart")
    LiveData<List<ProductCart>> getAllProducts();

    @Delete
    void delete(ProductCart productCart);
}
