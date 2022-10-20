package lesson6;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



public class ExampleMain {

    public static void main( String[] args ) throws IOException {
        SqlSession session = null;
        try {//создаем sql сессию для домтупа к бд
            String resource = "mybatis-config.xml";//путь к файлу
            InputStream inputStream = Resources.getResourceAsStream(resource);//читаем его
            SqlSessionFactory sqlSessionFactory = new
                    SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();//открываем сессию

            // далее обращемся к объекту session и передаем ему соответствующий класс
            db.dao.CategoriesMapper categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
            //создаем условие where объекта categories
            db.model.CategoriesExample example = new db.model.CategoriesExample();
            //добавляем к объекту example условие критерия(сответствиет 1)
            example.createCriteria().andIdEqualTo(1);
            // вызываем selectByExample и передаем ему условие - в итоге полуаем объект list
            List<db.model.Categories> list = categoriesMapper.selectByExample(example);
            //посчитаем количесвто этих объектов и выведем на эеран
            System.out.println(categoriesMapper.countByExample(example));

            db.model.Categories categories = new db.model.Categories();
            categories.setTitle("test");
            categoriesMapper.insert(categories);
            session.commit();

            db.model.CategoriesExample example2 = new db.model.CategoriesExample();
            example2.createCriteria().andTitleLike("%test%");
            List<db.model.Categories> list2 = categoriesMapper.selectByExample(example2);
            db.model.Categories categories2 = list2.get(0);
            categories2.setTitle("test100");
            categoriesMapper.updateByPrimaryKey(categories2);
            session.commit();

            categoriesMapper.deleteByPrimaryKey(categories2.getId());
            session.commit();

        } finally {
            session.close();
        }


    }
}
