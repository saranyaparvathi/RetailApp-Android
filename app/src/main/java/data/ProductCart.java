package data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.fordlabs.innovation.retailapp.ProductItemViewModel;

import java.util.Objects;

@Entity(tableName = "product_cart")
public class ProductCart {

    @TypeConverters(Converter.class)
    @ColumnInfo(name = "product")
    private ProductItemViewModel productItemViewModel;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public ProductCart(int id, ProductItemViewModel productItemViewModel) {
        this.id = id;
        this.productItemViewModel = productItemViewModel;
    }

    public ProductItemViewModel getProductItemViewModel() {
        return productItemViewModel;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCart that = (ProductCart) o;
        return id == that.id &&
                Objects.equals(productItemViewModel, that.productItemViewModel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productItemViewModel, id);
    }
}
