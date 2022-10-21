# Java Modifier

## Modifiers - 제어자, 적용되는 타겟에 대한 접근 제어

- Keywords, added to a class, constructor, method or field
- Add functionality or change the meaning of its target
- Modifiers are divided into 2 groups
    - Access Modifiers
    - Non-Access Modifiers
        - Abstract - Makes the class abstract
        - Final - Finalizes the implementation of the class
        - Static - Makes inner classes instantiable without the outer class
        - Interface - Marks the class as an interface
        - Synchronized - Method can be accessed by one thread at a time - 잘 모름
        - Native - Implemented in another programming language

      [Modifier (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/Modifier.html)


## Modifiers Discovery with getModifiers()

- Class.getModifiers() - Returns Class Modifiers
- Constructor.getModifiers() - Returns Constructor modifiers
- Method.getModifiers() - Returns Method modifiers
- Field.getModifiers() - Returns Field modifiers

Modifiers are packed in an integer value -  제어자는 정수 변수에 묶인다? - 중요

## ## Bitmap Encoding of Modifiers

- The modifiers are encoded as a bitmap
- Each modifier represents a single bit

제어자가 비트맵으로 인코딩되어 각 제어자는 하나의 단일 비트로 표현된다.

### Example

PUBLIC = 1. Binary representation : …. 0000 0001

STATIC = 8. Binary representation : …. 0000 1000

PUBLIC | STATIC = 9. Binary representation : …. 0000 1001

## Modifier Helper Class

- To help us work with those bitmaps, Reflection comes with the modifier class that contains bit masks for all the modifiers - 모든 제어자를 위한 비트마스크를 포함하고 있는 제어자 헬퍼 클래스가 딸려 있다.

### Example

```java
int modifiers = Product.class.getModifiers();

if((modifiers & Modifier.ABSTRACT) != 0){
	System.out.println("Product is an abstract class");
}
```

- Modifier class also provides us with static helper methods that perform those bitmask operations for us
- Methods like
    - boolean Modifier.isPublic(int modifiers)
    - boolean Modifier.isFinal(int modifiers)
    - boolean Modifier.isStatic(int modifiers)
    - …
