# Java Reflection Field class

## Obtaining Field objects of a class

- Class.getDeclaredFields() : get all declared fields of a class
    - 모든 필드 regardless of their access modifiers
    - Excludes inherited fields
- Class.getFields() - get all the public fields of a class
    - Includes inherited fields

### if we know the fields’s name

- Class.getDeclaredField(FieldName)
- Class.getField(fieldsName)

if the field with the given fieldName does not exists a NoSuchFieldException is trown

해당 필드가 존재하지 않는다면 → NoSuchFieldException

## Synthetic Field? 이게 뭐지 헷갈림 주의

Java Compiler generates artificial fields for internal usage

클래스에 명시하여 선언한 필드 외에도 Java 컴파일러가 내부 사용을 위해 인위적으로 필드를 생성한다.

실행할 때 Reflection을 사용해 찾지 않는 한 보이지 않는 필드. → 만드는 이유 나중에 조사해 보면 좋을듯

이러한 필드는 컴파일러에 특정한 이름을 갖고 주로 변경하지 않는다. → 그러면 어디에 쓰이는 건데?

- To find out if a Field is synthetic we check the Field.isSynthetic()
- about syntetic

  ## Inner class

    ```java
    public static class Movie extends Product {
    		public static final double MINIMUM_PRICE = 10.99;
    
    		private boolean isReleased;
    		private Category category;
    		private double actualPrice;
    
    		public Movie(final String name, final int year,
    			final boolean isReleased, final Category category,
    			final double actualPrice) {
    			super(name, year);
    			this.isReleased = isReleased;
    			this.category = category;
    			this.actualPrice = actualPrice;
    		}
    
    		public class MovieStats {
    			private double timeWatched;
    
    			public MovieStats(final double timeWatched) {
    				this.timeWatched = timeWatched;
    			}
    
    			public double getRevenue() {
    				return timeWatched * actualPrice;
    			}
    		}
    	}
    ```

  Result

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ccc70f73-51a1-48f5-9139-8745b67029d5/Untitled.png)

  필드는 timeWatched 하나 선언했는데 필드가 2개다. 외부 클래스인 Movie도 필드가 있고 synthetic field라고 한다.

  → 컴파일러가 생성한 부모 클래스 Movie의 인스턴스와 내부 클래스가 연결된다.

    <aside>
    💡 Synthetic 필드는 건드리지 않는게 좋다. → 예상하지 않은 오류가 발생할 수 있다.

    </aside>

  ## Enum

    ```java
    public enum Category {
    		ADVENTURE,
    		ACTION,
    		COMEDY
    	}
    ```

  Result

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fe33f042-e9bc-4500-9a1c-588bb66fb0ec/Untitled.png)

  유추해보자면 이는 Category 타입 배열이고 각 enum은 Value 메서드를 가지니

  컴파일러가 생성한 필드이고, enum이 가지는 모든 필드를 나타낸 배열을 포함한다.

  → Enum의 특징과 연결시켜 잘 이해해 보자

## Fields use cases

- Using the Field class we were able to get a class’s field
    - Type
    - Name
- Read the field value of an object
- This ability really shines when we are working on Objects that represent data
- The types of those objects and fields may not be known to us at compile time or when we write our program
- The object types are typically not even available to our library
- The types may be defined in the application that uses our library

## Json Serialize Example

- JSON - popular format for
    - Storing objects in a database
    - Sending data between services over the network
- It supports a small set of types
- It is human readable

런타임으로 모든 유형의 배열 검사 방법과 모든 차원의 배열 요소를 개별로 읽는 법을 학습하겠다.?

## Array inspection using Java Reflection API

### Identifying Arrays and Element Types

- Arrays are special classes → 배열도 클래스다
- We can get basic information about arrays using the Class<?> API - 모든 차원의 배열에 관한 기본 지식을 얻을 수 있다.
- We confirm that the object is an array using Class.isArray() property - Array 클래스인지 확인 가능
- We can use the Class.getComponentType() to get the type of the array elements - 배열 요소 유형 확인
- Class.getComponentType() returns null on non array Class objects - 배열이 아니면 null 반환

## Reading values from arrays using Java Reflection - Runtime

### java.lang.reflect.Array Class

- Given an array object, we can use the Reflection to access the array object’s runtime properties and data -
  Reflection을 사용해 런타임 값과 데이터에 접근 가능
- Array class contains static methods that help us get that data from an array object - 메서드 제공?
- Example
    - Array.getLength(Object arrayObject) - get array’s length
    - Array.get(Object arrayObject, int index) - get element by index

---

## 필드 값을 세팅하고 역동적으로 설정하는 방법

## Setting Field Values

```java
public<T> void setFieldValue(T instance){
	Class<T> clazz=instance.getClass();
	Field field=clazz.getDeclaredField("fieldName");
	field.setAccessible(true);
	field.set(instance,value); // value must be right type
	}
```

## Use cases for setting field values with Reflection

used with unknown ahead of time(instance)

유형도 필드 이름도 알 수 없을 경우 사용하는 것이 대부분이기 때문에 매개변수에 fieldName도 넣는다.

## Use case - Object Deserializers

- Take data in a predefined protocol and translate into a Java Object represetnation (Plain Old Java Object) - 미리 정의된
  프로토콜에서 데이터를 가져와 Java 언어로 변경해준다.
- Examples
    - Network Protocol Deserializers (Json Converter)
    - Object Relational Mapping Software
        - 복잡한 SQL 쿼리를 쓰지 않고 관계형 데이터베이스에서 데이터를 읽게 한다.
        - 데이터베이스의 열 형태로 오는 데이터를 Java 객체로 변환하는 기능을 제공
    - Application Configuration File Parsers
        - 텍스트 포맷의 사람이 읽을 수 있는 설정을 파싱하여 추가 코드 수정 없이 어플리케이션 동작이나 작업 모드를 변경할 수 있는 객체로 전환할 수 있다.

## Setting Final Field Values - 언젠가 이해 필요!!!

Reflection을 사용한 객체의 final 필드 설정은 권장하지 않고 예상치 못한 결과가 나올 수 있다.

<aside>
💡 아 그래서 Response 객체는 final Request 객체는 final을 붙이지 않았던 걸까?

</aside>

→ 최종 필드 건드는 것은 좀 많이 복잡함, 강의 다시 들을 것

## Why? 클래스에 생성자를 갖게 하고 값을 생성자를 입력하는 대신에  객체 필드에 직접 값을 할당하는 침습적 방법을 사용하는 이유가 뭘까?

- 생성자 인수 이름이 Compile 뒤에 지워지기 떄문 → 어떤 인수가 어떤 필드를 초기화했는지 알 수 없다.
- 같은 유형의 파라미터가 여럿 있다면 매개변수 유형에 의지하는 것은 불가능하다.

→ 따라서 런타임에 이름을 유지하는 필드를 사용하는 게 작업에 있어서 더 좋은 옵션이 된다.

```java
public class GameConfig {
	private int releaseYear;
	private String gameName;
	private String company;

	// (int releaseYear, String gameName, String company)
	public GameConfig(int arg0, String arg1, String arg2) {
		this.releaseYear = arg0;
		this.gameName = arg1;
		this.company = arg3;
	}
}
```

---

## Array Creations using Reflection

We learnd about the java.lang.reflect.Array helper Class

we can read values of Array by using this helper class abd also create arrays of any type and demension.

### One Dimensional Array Creation

- Array.newInstance(Class<?> componentType, int length)
    - create a 1D array with elements of type componentType and length *length*

## Setting Array values using Reflection

### Setting Array Values

- Array.set(Object arrayObject, int index, Object value)
- The passed value must be of the correct type, otherwise an exception will be thrown
- For compile time safety:
    - Array.setBoolean(Object arrayObject, int index, boolean value)
    - Array.setInt(Object arrayObject, int index, int value)
    - Array.setDouble(Object arrayObject, int index, double value)
    - Array.setLong(Object arrayObject, int index, long value)

Do It → Parsing arrays from Configuration file
