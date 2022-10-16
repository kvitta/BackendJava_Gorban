package lesson5.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

@UtilityClass//делает все методы в классе статическими
public class RetrofitUtils {

    Properties prop = new Properties();//создается объект property
    private static InputStream configFile;// он читается из файла

    static {
        try {
            configFile = new FileInputStream("src/main/resources/my.properties");// путь к файлу
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows// может генерировать exeption-ы  и скрывает их
    public String getBaseUrl() {
        prop.load(configFile);// метод load в классе property гененерит in out exeption
        return prop.getProperty("url");
    }

//определяем перехватики
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();//стандартный от библиотеки okhttp3
    LoggingInterceptor logging2 = new LoggingInterceptor();// самописный перехватчик
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();//доступ к самому клиенту  okhttp3 для настройки

// по факту в RetrofitUtils может быть нескольео retrofit клиентов, с разным сожердимым и билдерами

//   public Retrofit getRetrofit(){  //статический метод(т.к. есть Util), обращение к нему напрямую//он настраивает библеотеку retrofit
//        return new Retrofit.Builder()// настраивается через билдер
//                .baseUrl(getBaseUrl())// в нашем случае настраивает только url
//                .addConverterFactory(JacksonConverterFactory.create())// добавляем фабрику- по сути билдер-возвращает некую реализацию класса
//                //в данном случае мы добавляем конвектор и указываем что для сериализации инаоборот мы используем Jackson
//                .build();
//
//    }

   public Retrofit getRetrofit(){
        logging.setLevel(BODY);// определяем уровень логирования
       httpClient.addInterceptor(logging2);//создаем и настраиваем Http клиент на использование перхватчика- указывает тот что определили для данного объекта
       return new Retrofit.Builder()
               .baseUrl(getBaseUrl())// урл
               .addConverterFactory(JacksonConverterFactory.create())//
               .client(httpClient.build())
               .build();
   }

}
