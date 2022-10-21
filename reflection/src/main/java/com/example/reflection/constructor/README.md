# Java.lang.reflect.Constructor<?>

- A Constructor of a Java class is represented by an instance of Constructor<?> class
- The Constructor object contains all the information about the class’s constructor including:
    - Number of parameters
    - Types of the parameters
- A class may have multiple constructors

클래스는 여러 생성자를 가질 수 있고, 이를 확인할 수 있다.

## Method to get Constructor<?> objects

- Class.getDeclaredConstructors()
    - public and non-public 포함 declared constructors 반환
- Class.getConstructors()
    - public constructor만 반환

- 특정한 constructor를 찾고 싶을 때 매개변수를 알고있다면 넘겨도 좋다.
    - Class.getConstructor(Class<?> … parameterTypes)
    - Class.getDeclaredConstructor(Class<?> … parameterTypes)
    - 단일 생성자를 반환하거나 맞는 생성자가 없다면 NoSuchMethodException 발생
    - 그럼 만약 두 메서드를 인수없이 호출한다면 Default 생성자를 가지게 되나?

## Object Creation

어던 클래스의 객체든 생성하는 단일 팩토리 메서드를 실행하고, 더 나아가 메서드에 전달한 인수에 따라 주어진 클래스에 알맞는 생성자를 찾아서 객체를 생성하는 동안 해당 생성자를 호출하는 것이 우리의 목적, reflection 없이는 불가.

## Object Creation with Java Reflection

- Constuctor.newInstance(Object … arguments)
    - 생성자에 선언된 수서대로 생성자 매개변수에 응답하는 가변인수를 받는 메서드

## Calling Private Constructors with Reflection

Constructor<?> constructor = getDeclaredConstructor();

constructor.setAccessible(true); // maek constructor accessible

constructor new Instance(arg1, arg2);
