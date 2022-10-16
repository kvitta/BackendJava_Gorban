package lesson5;

import lesson5.api.CategoryService;
import lesson5.dto.GetCategoryResponse;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {

    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }
    // получаем экземпляр реализации categoryService- обращаемся к классу RetrofitUtils, вызываем его метод RetrofitUtils
    // и с учетом его настроек получаем реализацию CategoryService,чем получаем доступ к его сервисам

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();
// обращаемся к классу  categoryServic и вызываем у него публичный апи ( методы в классе) и делаем execute
        //в итоге получаем объект responce внутри которого body - GetCategoryResponse
        // итак мы имеем доступ и к ответу и к json и пишем проверки
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));


    }

}
