---
title: Java packages & imports
author: tozydev
publishedAt: 2025-07-22T00:00:00Z
topic: java
tags:
  - java
  - packages
description: Giải thích về package, cách tổ chức mã nguồn và quy tắc đặt tên package trong Java.
---

Trong Java, ta sử dụng package để tổ chức các source code Java. Có thể xem package như thư mục và source code Java như
các file được tổ chức trong hệ thống tệp tin trên hệ điều hành. Trong package, bạn có thể gom nhóm các class, interfaces
và các sub-package khác.

## Tạo packages

Để tạo package, ta sử dụng lệnh `package` ở đầu file. Không nhất thiết lệnh phải ở dòng đầu tiên của source (vì ta có
thể thêm comment ở trước lệnh `package`), nhưng phải đảm bảo rằng không có lệnh nào được thực hiện trước `package`.

```java
/* We can use comment here */
package vn.id.tozydev.packages;

public class Application {
    // ..
}
```

Trong ví dụ ở trên, class `Application` được đặt trong package `vn.id.tozydev.packages`. Tên đầy đủ của class
`Application` là `vn.id.tozydev.packages.Application`.

Nếu các source Java không chỉ định các package, các source này sẽ thuộc về default package hoặc unnamed package. Điều
này nên tránh trong khi phát triển ứng dụng thực tế.

Thông thường, mỗi package sẽ tương ứng với từng thư mục riêng. Ví dụ: `vn.id.tozydev.packages` sẽ tương ứng
`vn/id/tozydev/packages`. Và class `Application` sẽ có vị trí `vn/id/tozydev/packages/Application.java` trên hệ thống
file.

## Naming conventions

Khi đặt tên package, chúng ta nên tuân thủ theo quy chuẩn sau:

- Tên package được đặt bằng chữ thường (lower case)
- Package được phân cách bằng dấu chấm `.`
- Tên cũng được xác định cùng với tên người ta tạo ra chương trình (cá nhân, tổ chức,…), thông thường sẽ đảo ngược tên
  miền để sử dụng (ví dụ: [tozydev.id.vn](http://tozydev.id.vn) → `vn.id.tozydev`).

Để dễ hình dung bạn có thể xét các ví dụ sau:

```java
package vn.id.tozydev.todolist; // Tên miền cá nhân + tên dự án
---
        package vn.id.tozydev.todolist.sub1.sub2; // Sử dụng cho sub-package
---
        package io.github.tozydev.app; // Nếu chưa có tên miền, có thể sử dụng tên miền GitHub
---
        package com.example;
```

## Imports

Giả sử ta có 2 class như sau:

```java
package vn.id.tozydev.todolist.entity;

public class Task {
    // ...
}

---
        package vn.id.tozydev.todolist.entity;

public class User {
    // ...
}

```

Để sử dụng các source Java, ta phải import vào source hiện tại.

Ta có thể import một class đơn:

```java
import vn.id.tozydev.todolist.entity.Task;

// ...

var task = new Task();
```

hoặc import tất cả các class, interface có trong package:

```java
import vn.id.tozydev.todolist.entity.*;

// ...

var task = new Task();
var user = new User();
```

Nếu không import, ta phải sử dụng tên đầy đủ khi muốn sử dụng các class đó:

```java
var task = new vn.id.tozydev.todolist.entity.Task();
var user = new vn.id.tozydev.todolist.entity.User();
```

<aside>
💡

Mặc định, Java đã import sẵn package `java.lang` (default import), vì thế khi code không cần phải import package này.
Một số class thuộc package này:

- `java.lang.System`
- `java.lang.String`
- `java.lang.Thread`

</aside>

## Static imports

Ta sử dụng `import static` để import các thành phần static có trong class:

```java
import vn.id.tozydev.todolist.util;

public class NameUtils {
    public static final int NAME_MIN_LENGTH = 6;

    public static boolean isValid(String name) {
        return !name.isBlank() && name.length() >= 6;
    }
}

// ...
import static vn.id.tozydev.todolist.util.NameUtils.NAME_MIN_LENGTH;
import static vn.id.tozydev.todolist.util.NameUtils.isValid;
// import static vn.id.tozydev.todolist.util.NameUtils.*;

// ...

var name = "tozy";
if(!

isValid(name)){
        System.out.

println("Name length must greater or equals than "+NAME_MIN_LENGTH);
}
```

## Naming conflicts

Nếu trong cùng một source Java có 2 class trùng tên, ta sẽ sử dụng full name của class đó:

```java
import java.util.Date;

// ...
var date = new Date();
        var sqlDate = new java.sql.Date(System.currentTimeMillis());
```

## References

https://kotlinlang.org/docs/packages.html

https://www.baeldung.com/java-packages

https://docs.oracle.com/en/java/javase/21/docs/api/index.html
