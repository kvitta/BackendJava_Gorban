package lesson5;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.Properties;

public class HomeWorkAbstractTest {
    Properties properties = new Properties();
    private static InputStream parameters;

    static {
        try {
            parameters = new FileInputStream("src/main/resources/test.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    String resource = "mybatis-config.xml";
    InputStream inputStream;

    {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
    db.model.ProductsExample productsExample = new db.model.ProductsExample();

    @SneakyThrows
    public void setSavedId(String newId) {
        properties.load(parameters);
        properties.setProperty("NewID", newId);
        FileOutputStream flow = new FileOutputStream("src/main/resources/my.properties");
    }

    @SneakyThrows
    public Integer getSavedId() {
        properties.load(parameters);
        return Integer.parseInt(properties.getProperty("NewID"));

    }
}
