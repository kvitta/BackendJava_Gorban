package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;


public class HomeWorkCreateProductTest extends HomeWorkAbstractTest{

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id;



    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp()throws IOException {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));

    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assert response.body() != null;
        setSavedId(response.body().getId().toString());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }

    @Test
    void getProductsTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assert response.body() != null;
    }

    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService.getProductById(getSavedId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        Assertions.assertEquals(getSavedId(), response.body().getId());
        db.model.Products selected = productsMapper.selectByPrimaryKey(Long.valueOf(getSavedId()));
        Assertions.assertEquals(selected.getId(), Long.valueOf(getSavedId()));
        Assertions.assertEquals(selected.getCategory_id(), 1);
    }

    @Test
    void modifyProductTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        db.model.Products selected = productsMapper.selectByPrimaryKey(Long.valueOf(getSavedId()));
        Assertions.assertEquals(selected.getTitle(), getSavedTitle());
        Assertions.assertEquals(selected.getCategory_id(), 1);


    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        db.model.Products selected = productsMapper.selectByPrimaryKey(Long.valueOf(getSavedId()));
        assertThat(selected, null);
    }



}
