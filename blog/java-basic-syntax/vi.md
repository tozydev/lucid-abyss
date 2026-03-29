---
title: Java Basic Syntax
author: tozydev
publishedAt: 2024-05-13T00:00:00Z
topic: java
tags:
  - java
  - syntax
description: Trình bày cú pháp cơ bản của Java, cách định nghĩa package, entry point, và các thao tác in ra màn hình.
---

Đây là tập hợp của những cú pháp cơ bản cùng với ví dụ trong Java. Mỗi phần sẽ có các liên kết đến chủ đề liên quan.

Ý tưởng thực hiện dựa trên https://kotlinlang.org/docs/basic-syntax.html

## Package definition and imports

Package dùng để tổ chức các source code Java. Lệnh `package` sẽ được đặt ở dòng đầu tiên của file source Java.

```java
package vn.id.tozydev.javaseries.basicsyntax;

import java.util.Collection;
import java.nio.*;

public class YourClassDefinition {
  // ...
}
```

Thông thường, mỗi phần phân cách bởi dấu chấm sẽ là một folder trên máy tính (không bắt buộc).
`vn.id.tozydev.javaseries.basicsyntax` →`vn/id/tozydev/javaseries/basicsyntax/YourClassDefinition.java`

## Program entry point

Entry point (điểm vào) của chương trình Java là `main` method:

```java
public class Program {
  static void main(String[] args) {
    System.out.println("Hello world!");
  }
}

// Hello world!
```

## Print to the standard output

`System.out.print` sẽ in ra console tham số của nó:

```java
System.out.print("Hello ");
System.out.print("Hello world!");

// Hello world!
```

`System.out.println` sẽ in ra tham số và thêm ngắt dòng, vì thế những lệnh in sau sẽ hiển thị ở dòng tiếp theo:

```java
System.out.println("Hello world!");
System.out.println("👌");

// Hello world!
// 👌
```

## Read from the standard input

Sử dụng class `Scanner` để có thể nhận dữ liệu từ console. Method `nextLine` sẽ đọc toàn bộ chuỗi ở dòng mà bạn nhập.
Method `nextInt` sẽ đọc số nguyên mà bạn nhập vào:

```java
import java.util.Scanner;

// ...

var out = System.out;
var sc = new Scanner(System.in);
out.print("Enter your name: ");
var name = sc.nextLine();

out.print("Enter your age: ");
var age = sc.nextInt();

out.println("You entered: ");
out.println("\tName: " + name + " - Age: " + age);
```

## Creating classes and instances

Các method, field trong Java phải thuộc một class nào đó. Sử dụng `class` keyword để tạo class:

```java
public class People {
  private String name; // field

  public String getName() { // method - getter
    return name;
  }

  public void setName(String name) { // method - setter
    this.name = name;
  }

  // other fields, methods, classes,...
}
```

Sử dụng constructor để khởi tạo giá trị ban đầu cho các thành phần của class:

```java
public class Program {
    static void main(String[] args) {
        var dev = new Developer("Java Dev"); // Sử dụng new keyword để tạo instance của class
        System.out.println(dev.getTitle());

        dev.setTitle("Web Dev");
        System.out.println(dev.getTitle());
    }
}

public class Developer {
    private String title;

    public Developer(String title) { // constructor
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

// Java Dev
// Web Dev
```

## Methods

Method `sum` với 2 tham số `int` và trả về kiểu `int`:

```java
public int sum(int a, int b) {
  return a + b;
}
```

Dùng void để bỏ qua kiểu trả về:

```java
public void hello(String name) {
  System.out.println("Hello " + name);
}
```

Để sử dụng method, method cần nằm trong một class và ta cần tạo instance của class đó:

```java
// ...
var utils = new Utils();
System.out.println(sum(2, 3));
System.out.println(hello("tozydev"));
// ...

public class Utils {
  public int sum(int a, int b) {
    return a + b;
  }

  public void hello(String name) {
    System.out.println("Hello " + name);
  }
}

// 5
// Hello tozydev
```

Sử dụng keyword `static` để truy cập method mà không cần tạo instance:

```java
// ...
System.out.println(Utils.sum(3, 3));
System.out.println(Utils.hello("tozydev"));
// ...

public class Utils {
  public static int sum(int a, int b) {
    return a + b;
  }

  public static void hello(String name) {
    System.out.println("Hello " + name);
  }
}

// 6
// Hello tozydev
```

## Variables

Để khai báo biến, ta sẽ sử dụng tên kiểu dữ liệu + tên biến + thêm giá khởi tạo (nếu có). Sử dụng `var` keyword để suy
kiểu (tự động nhận dạng kiểu dữ liệu của giá trị). Sử dụng `final` để đánh dấu biến không thể được gán lại (thay đổi giá
trị).

```java
int year; // Khởi tạo biến year với giá trị mặc định 0
year = 2025 // Gán giá trị cho biến
String month; // Khởi tạo biến month chưa có giá trị (null)

int age = 20; // Biến age với giá trị khởi tạo 20
var name = "tozy"; // Biến name với kiểu String (suy ra từ giá trị - chuỗi "tozy")

final double PI = 3.14;
final var E = 2.7;

// Lỗi nếu thay đổi giá trị
// PI = 3.14159;
// E = 2.71828;
```

## Comments

Trong Java hỗ trợ chú thích một dòng (single-line) và nhiều dòng (multi-line):

```java
// Chú thích đến hết dòng

/* Đây là
chú thích
nhiều dòng */

/**
 * Đây là Javadoc, chú thích sử dụng cho việc tạo tài liệu code (code documentation)
 */
```

## Conditional operator (Ternary operator)

Có thể xem là biểu thức boolean, nếu điều kiện đúng thì trả về biểu thức 1 và ngược lại, điều kiện sai trả về biểu thức

2.

```java
var a = 5;
var b = 7;

System.out.println(a >= b ? "a >= b" : "a < b");

// a < b
```

## if statement

```java
public static void max(int a, int b) {
  if (a > b) {
    return a;
  } else {
    return b;
  }
}
```

## for loop

Vòng lặp for với chỉ số:

```java
for (int i = 0; i < 5; i++) {
  System.out.println(i);
}

// 0
// 1
// 2
// 3
// 4
```

Vòng lặp for each để duyệt qua toàn bộ phần tử của mảng, collection:

```java
int[] years = {2021,2022,2023,2024,2025};
for (var year : years) {
  System.out.println(year);
}

// 2021
// 2022
// 2023
// 2024
// 2025
```

## while loop

```java
String[] roles = {"Guest", "User", "Manager", "Admin"};
var index = 0;
while (index < roles.length) {
  System.out.println(roles[index]);
  index++;
}

// Guest
// User
// Manager
// Admin
```

## switch expression

```java
public static String getSeasonName(int seasonCode) {
  return switch (seasonCode)
   {
      case 0 -> "Spring";
      case 1 -> "Summer";
      case 2 -> "Fall";
      case 3 -> "Winter";
      default -> "???";
   };
}

// ...
System.out.println(getSeasonName(0));

// Spring
```

## Collections

Tạo một danh sách trong Java:

```java
import java.util.ArrayList;
import java.util.List;

// ...
var mutableList = new ArrayList<String>(); // Tạo một danh sách chứa kiểu String
// List<String> = new ArrayList(); // Cũng có thể sử dụng cách này

var immutableList = List.of("Can", "not", "change", "it", "element"); // Tạo danh sách bất biến (không thể thay đổi các phần tử của nó)
```

Duyệt qua từng phần tử của danh sách:

```java
import java.util.List;

// ...

var roles = List.of("guest", "user", "manager", "admin");
for (var role : roles) {
  System.out.println(role);
}

// hoặc

roles.forEach(role -> System.out.println(role));

// guest
// user
// manager
// admin
```

Kể từ Java 8, ta có thể sử dụng Stream API để xử lý danh sách dễ dàng hơn:

```java
var roles = List.of("guest", "user", "manager", "admin");
roles.stream()
  .filter(role -> role.startsWith("a"))
  .map(role -> role.toUpperCase()))
  .forEach(role -> System.out.println(role));

// ADMIN
```
