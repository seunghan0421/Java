## Java Annotations

@Somename - Interpreted by the Java compiler as an Annotation

## Annotation Meaning and Functionality

- Annotation itself doesn’t have any functionality or efect on the program
- It is a way to privide additional information (metadata) about the target it annotates or the program as a whole - 추가적인 데이터를 제공하거나 전체 프로그램 관련 정보를 제공하는 Java의 한 방법
- We can think of an annotations as a “comment” but a lot more powerful
    - We can integrate annotations with the compiler and compile time tools to detect errors, bugs and provide warnings
    - Carry into the JVM and access at Runtime using Reflection

## Annotations and Reflection - Motivation

- Annotations can instruct and direct Reflection code on - 어떤 타깃에 가서 무슨 작업을 해야하는지 알려줄 수 있다
    - What targets to process
    - What to do with those targets
- We can decouple our application code from the Reflection code
- We don’t restrict our methods/classes/fields to particular - 특정한 이름, 구조를 띄지 않아도 된다. Reflection으로 할거야
    - Name
    - Structure

## Annotation Element Types

- All Primitive types
- Strings
- Enums
- Classes(Class<?>)
- Other annotations
- An array of one of the above types

## Annotations Visibility

- Not all annotations are visible at Runtime
- By default annotations are retained by the compiler at compile time but are ignored by the JVM and are not visible at Runtime - 일반적으로 JVM은 어노테이션을 무시한다.

애너테이션의 가시성과 다른 많은 특성을 제어하기 위해 특별한 애너테이션인 메타 애너테이션이 있다.

## Meta-Annotations

- Meta-annotations - Annotations that apply to other annotation - 다른 어노테이션에 적용되는 어노테이션

## Retention Meta- Annotation - 중요

- One of the most important predefined meta-annotation for us is the @Retention meta-annotation
- The @Retention specifies
    - How a marked annotation is stored
    - For how long it is going to be retained
- @Retention meta-annotation takes a enum value of type RetentionPolicy that has 3 options
    - RetentionPolicy.SOURCE - Annotations are discarded by the compiler
        - IDE와 컴파일러가 오류나 경고를 보내는 것을 도움, 코드가 컴파일 된 이후 목적 X
        - Examples. @Override, @SuppressWarnings
    - RetentionPolicy.CLASS - Annotations are recorede in the class file by the compiler but ignored by the JVM at runtime - DEFAULT
        - 클래스 파일에 표시된 어노테이션을 저장하도록 컴파일러에 지시
        - Examples. @AutoValue
    - RetentionPolicy.RUNTIME - Annotations are recorded in the class filed by the compiler and are retained by the JVM at runtime
        - 컴파일러에게 클래스 팡리의 표시된 애너테이션을 기록하게 하고 런타임 동안 JVM이 애너테이션을 이용할 수 있게 한다.
        - 커스텀 애너테이션을 사용하고 싶을 때 사용할 수 있는 보존 정책

## Target Meta-Annotation

- Target meta-annotation limits the targets on which an annotation can be applied - 애너테이션이 적용될 수 있는 Target 제한
    - ElementTpye.ANNOTATION_TYPE
    - ElementType.CONSTRUCTOR
    - ElementType.FIELD
    - …

## Find Target Annotation

boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)를 사용

- The isAnnotationPresent() method exists in the
    - Class
    - Field
    - Method
    - Constructor
    - Parameter

Targets

- The method takes an annotation type as an argument and returns
    - True - If the target is annotated with the given annotation
    - False - Otherwise
- Perfect for simple Marking ANnotations discovery - 단순 마킹 어노테이션을 찾기에는 최고

## Application Initialization

- We need to perform many initialization steps at application startup
- Those steps can involve:
    - Connecting to different sources of data( databases, caches)
    - Loading configuration files
    - Registering and initializing other libraries and frameworks
    - Communicating with other services(service registries, load balancers)
- The logic we want to perform at startup can be organized in many classes all around our application’s codebase - 가동 시 필요한 로직들이 코드 전반에 뿌려져 있다.
- Annotating and performing those methods dynamically using reflection is a very common and convinient solution - 어노테이션을 사용하는 방법이 보편적이다.

## Class Discovery - 매우매우 중요!

- Given a package_name
    - Default package : “”
    - Top level package examples : “model”, “java”
    - Nested package examples : “app.configs”, “java.lang”
- We want to obtain all the Class<?> objects within the package **
- In Java, classes are not available to the JVM until they are used in the code and loaded by the Class Loader - 하지만 자바에서는 실제로 코드가 사용될 떄까지  JVM에서 클래스를 사용할 수 없고, 클래스 로더를 이용해  JVM으로 자동으로 로드되기 때문에 클래스가 사용되거나 로드되기 전에  자동으로 찾으려면 지정된 패키지에 해당하는  경로에서 .class 가 붙은 파일을 찾아야한다
- To automatically discover all the classes in a package we need to look for the files with the “.class” extension in the package path
- The “.class” files contain the compiled Java class
- Their filename corresponds to the Java class name

클래스 파일은 JVM으로 로드될 수 있는 컴파일된 Java 클래스를 포함하고 클래스명과 같은 파일명을 갖는다.

## Java Class Path

java -cp “/home/additional_classes” Main

classpath, Location of additional classes, Class containing main(…) method → 다시보기

## Strategy to find all Classes in a Package

- Step 1 : Convert package name “app.data” → “app/data”
- Step 2 : Use getResource(..) method of a Class<?> or ClassLoader to get the URI(Uniform Resource Identifier) of “app/data”
- Step 3 : Get the Path of the package directory “app/data” → “/home/additional_classes/app/data/”
- Look for all the “.class” files in the “/home/additional_classes/app/data/”
- Find “/home/additional_classes/app/data/Product.class

## Annotation Object

<T extends Annotation> T getAnnotation(Class<T> annotationClass)

- Available on Class<?>, Method, Constructor, Field, Parameter targets
- Returns
    - An annotation object of type T
    - null if the target is not annotated with annotation of type T

애너테이션 객체가 무엇을 의미할까?

Java 애너테이션을 선언할 때 인터페이스 키워드를 사용하는 것은 우연이 아니다.

또한 애너테이션 요소 선언은 인터페이스 메서드 선언과 매우 유사하다

애너테이션 객체를 실제 요청하면 JVM이 런타임동안 타깃에 존재하느 애너테이션을 분석하고, 애너테이션을 선언할 때 묘사한 인터페이스를 구현하는   동적 프록시 객체를 구성한다.

## Parameter Annotations on Constructors and Methods

### 매개변수에 애너테이션을 정의하는 이유

ans. 매개변수 애너테이션으로 같은 타입의 여러 매개변수가 있는  생성자나 매서드 매개변수를 확실하게 구분할 수 있다.

```java
class HttpClient {
	private int port;
	private int retries;

	// 컴파일이 끝난 후 매개변수의 이름은 알 수 없다.
	// 하지만 새로운 각 매개변수에 대한 마킹 애너테이션을 만드는 대신 애너테이션을 만들고
	// 다른 요소 값과 사용해서 매개변수끼리 구분할 수 있다.
	HttpClient(@Port int arg0, @Retries int arg1){
		this.port = arg0;
		this.retries = arg1;
	}
}
```

```java
class HttpClient {
	private int port;
	private int retries;

	public void sendRequest( @Param("port") int port,
														@Param("retries") int retries){
		...
	}
}
```
