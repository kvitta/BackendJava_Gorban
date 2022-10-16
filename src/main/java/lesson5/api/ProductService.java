package lesson5.api;

import lesson5.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);
    //<ResponseBody> по факту void- когда нам не важно что приходит в ответе или запрос ничего не возвращает

    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);
    // входным параметром запроса будет json-объект modifyProductRequest ( в нашем случае сериализованный объект продакт)
    //возвращаемое значение -product

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @GET("products")
    Call<ResponseBody> getProducts();

}
