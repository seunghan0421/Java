# Reflection Use Cases

- Junit - Framework for Unit Testing of Java Applications
- Dependency Injection
    - Spring / Spring Boot
    - Google Guice
- Json Serialization / Deserialization - 프로토콜 간 변환
    - Jackson
    - Gson
- Other popular use cases include:
    - Logging Frameworks
    - ORM (Object-Relational Mapping) tools
    - Web Frameworks
    - Developer tools
    - Many more…

매우 많은 것을 할 수 있지만 잘못 사용하면 프로그램이 위험해진다.

<aside>
💡 “With Great Power, Comes Great Responsibility”

</aside>

# Introduction to Reflection’s Entry Point(Class)

Class<?> is an entry point for us to reflect on our application’s code

An object of Class<?> contains all the information on

- A given object’s runtime type
- A class in our application

That information includes

- What methods and fields it contains
- What classes it extends
- what interfaces it implements

## Ways to obtain an Object of type Class<?>

### Object.getClass()

- String stringObject = “some=string”;
    - Class<String> stringClass = stringObject.getClass();
- Car car = new Car();
    - Class<Car> carClass = car.getClass();
- Map<String,Integer> map = new HashMap<>();
    - Class<?> mapClass = map.getClass(); // returns HashMap class 객체는 변수의 런타임 타입

if No Object.getClass(); for primitive types (primitive type is not Object)

- boolean condition = true;
    - Class booleanClass = condition.getClass(); // compilation error
- int value = 55;
    - Class intClass = value.getClass(); // compilation error
- double price = 1.5;
    - Class doublePrice = price.getClass(); // compilation error

### “.class” suffix to a type name

- Class<String> stringClass = String.class;
- CLass<Car> carClass = Car.class;
- Class<?> mapClass = HashMap.class;

if No Object.class; for primitive type

- Class booleanType = boolean.class;
- Class intType = int.class;

### Class.forName(…) - 클래스 경로에서 동적으로 찾는다. 패키지 명을 포함한 이름을 사용

Class<?> stringType = Class.forName(”java.lang.String”);

Class<?> carType = Class.forName(”vehicles.Car”);

Class?> engineType = Class.forName(vehicles.Car$Engine”) // we use “$” for inner class

Much more likely to mistype the class name and get the ClassNotFoundException

못찾을 시 → ClassNotFoundException

## Class Documentation

[Class (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)
