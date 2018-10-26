package data;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fordlabs.innovation.retailapp.ProductItemViewModel;
import com.fordlabs.innovation.retailapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ProductCartDaoTest {

    private ProductCartRoomDatabase database;
    private ProductCartDao dao;

    @Mock
    private Observer<List<ProductCart>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, ProductCartRoomDatabase.class)
                .allowMainThreadQueries().build();
        dao = database.productCartDao();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void insert() throws Exception {
        // given
        ProductCart productCart = new ProductCart(1, new ProductItemViewModel(R.drawable.microwave, "Microwave", "Rs.1000"));
        // when
        dao.insert(productCart);
        // then
        dao.getAllProducts().observeForever(observer);
        verify(observer).onChanged(any(List.class));
    }

}