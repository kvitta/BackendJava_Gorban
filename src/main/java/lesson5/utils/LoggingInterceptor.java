package lesson5.utils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {// класс имплементирует Interceptor

    @Override public Response intercept(Chain chain) throws IOException {// метод intercept выполняет функцию перехвата
        // на входе получаем объект типа сhain
        Request request = chain.request();//получили объект запроса

        long t1 = System.nanoTime();

        System.out.println(String.format("Sending request %s on %s%n%s",// сохранили информацию о времени создания
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);// выполнили запрос и получили responce

        long t2 = System.nanoTime();

        System.out.println(String.format("Received response for %s in %.1fms%n%s",// дополнительно залогировали этот responce
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;// и вернули его
    }
}
