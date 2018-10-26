package data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

public class ProductCartRepository {

    private ProductCartDao productCartDao;
    private LiveData<List<ProductCart>> productCartList;

    @Inject
    public ProductCartRepository(Application application) {
        ProductCartRoomDatabase db = ProductCartRoomDatabase.getDatabase(application);
        productCartDao = db.productCartDao();
        productCartList = productCartDao.getAllProducts();
    }

    public LiveData<List<ProductCart>> getAllWords() {
        return productCartList;
    }

    public void insert(ProductCart productCart) {
        new insertAsyncTask(productCartDao).execute(productCart);
    }

    private static class insertAsyncTask extends AsyncTask<ProductCart, Void, Void> {

        private ProductCartDao mAsyncTaskDao;

        insertAsyncTask(ProductCartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ProductCart... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
