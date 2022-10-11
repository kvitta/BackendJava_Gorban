package lesson4.builder;

public class MyClassBuilder {// класс билдер всегда оканчивается на builder
    //прописываем переменные, можно указать значение,можно нет-тогда оно будет деволтным
    private String attr1;
    private Boolean attr2;
    private Long attr3 = 1l;

    //ко всем переменным делаем сеттеры, но внутри возвращаем эту же переменную
    public MyClassBuilder setAttr1(String attr1) {
        this.attr1 = attr1;
        return this;
    }

    public MyClassBuilder setAttr2(Boolean attr2) {
        this.attr2 = attr2;
        return this;
    }

    public MyClassBuilder setAttr3(Long attr3) {
        this.attr3 = attr3;
        return this;
    }
    // обязательно вызываем билдер creatНазвание класса
    public MyClass createMyClass() {
        return new MyClass(attr1, attr2, attr3);
    }// возвращает атрибуты
}