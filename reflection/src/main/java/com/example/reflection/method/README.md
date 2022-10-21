# Method Class

## Discovering all methods of a class

### java.lang.reflect.Method

- All class methods are represented as object of type Method
- There are a few ways to obtain all the methods of a class
    - Class.getDeclaredMethods() - All methods declared in a class
    - Class.getMethods() - All public methods including methods inherited from superclasses and implemented interfaces

### Method’s Properties

- Method.getName() - Returns a String representing a method’s name
- Method.getReturnType() - Returns a Class<?> representing the return type
- Method.getParameterTypes()?
- Method.getParameterCount - Returns the number of method parameters
- Method.getExceptionTypes() - Returns an array of exception types declared with the method

Getter Testing using Reflection - Junit?

Reflection을 사용한 테스팅 프레임워크

Getter를 작성하는 과정 자체가 반복되기 때문에 작은 실수(오타, 빠트림)을 조심해야 한다.

기존 데이터 필드를 가진 클래스에 올바른 getter 메서드를 사용하여 다른 프레임워크나 라이브러리 사용자의 데이터 필드에 접근

<aside>
💡 필드에 바로 접근할  수 있는데 Getter를 작성하고 필요한 이유는 무엇일까???

</aside>

### java.lang.reflect.Method

- Two options to obtain a method object based on its name and parameter types
    - Class.getMethod(String name, Class<?> … paramTypes) - loons for a public method in current or superclasses
    - Class.getDeclaredMethod(String name, Class<?> … paramTypes) - looks for a method only in the current class
- if a method with the name and parameter types(in that order) is not found a NoSuchMethodException is thrown


## Method Parameter Names

- Just like Constructor parameter names, Method parameter names are erased after compliation - 컴파일을 진행하고 나면 생성자와 메서드의 매개변수 이름은 지워진다!! 주의
- Method.getParameters() - Provides additional information about a method’s parameters
- The parameter names are replaced with “arg0”, “arg1”…

## Calling a Method using Reflection

- We learned how to obtain a Method object of a Class
- We can call a method of a Java Object using the Method.invoke(Object instance, Object …args) operation
    - instance - The object we are interested in invoking the method
    - if the method is static we can pass nul as the instance - 메서드가 static이면 instance null 가능
    - args - arguments to the method. Must be of the correct type and order as the method’s signature - 매개변수는 정확한 유형과 순서여야 한다.

Object Method.invoke(Object instance, Object …args)

- if the return type of the method is void, invoke(..) will return null
- if the return type is a primitive type(int, boolean, float, long, double, etc…). The return value is wrapped in an object.
    - int value is returned as an Integer object.. etc

Method.invoke(..) can throw:

- NullPointerException
- ExceptionInInitializerError
- IllegalAccesException
- IllegalArgumentException
- InvocationTargetException - 호출 타깃 예외 처리
    - Wraps an exception that the target method has thrown
    - 이거 다시 봐야겠따.

    <aside>
    💡 Method.getReturnType()에서는 int.class가 반환될 수 있지만
    Method.invoke(instance, arguments)를 할 경우 Wrapper class가 반환된다.

    </aside>


## Method Invocation with Reflection - Use cases

Reflection을 활용한 주요 메서드 호출 사용 사례

- When the Method’s
    - Name
    - Signature
    - Return Type
    - Containing class

are decoupled from the logic that controls the execution of those methods

메서드 이름, 시그니처, 반환 유형, 심지어 메서드를 포함한 클래스가 메서드를 실행하는 로직에서 분리될 때?

- Typically the order of execution is either not important or unknown at compile time - 실행 시간은 중요하지 않거나 컴파일 타임으로 알 수 없다>?
- Method invocation using Reflection is one of the most widely used features of Java Reflection
- Can be found in many stack traces of libraries and frameworks, used on a daily basis

두 클래스가 완전히 분리된 라이브러리에 속하여 공유 인터페이스나 클래스에서 구현 또는 상속을 하지 못하면 어떻게 될까? 게다가 메서드 이름마저 심지어 똑같지 않다면?

Can we still use Polymorphism and group HttpClient and DatabaseClient in a similar way?

- Without Reflection : NO!

Thanks to the fact that

- All Classes in Java inherit from the Object class
- Java Reflection features

We can achieve similar functionality using Java Reflection without

- Implementing additional interfaces
- Extending additional classes
