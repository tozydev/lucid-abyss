---
title: Chương trình Java cơ bản
author: tozydev
publishedAt: 2025-10-11T00:00:00Z
topic: java
tags:
  - java
  - programming
description: Giới thiệu về chương trình Java cơ bản, giải thích cấu trúc class, method và cách hoạt động của chương trình Java đầu tiên.
---

```java
public class Program {
    static void main(String[] args) {
        var os = System.getProperty("os.name");
        System.out.println("Your operating system is " + os);
    }
}
```

Chương trình trên sẽ cho ra kết quả như thế nào? Hãy chạy chương trình trên để trả lời nhé.

## Class

Java là một ngôn ngữ lập trình hướng đối tượng dựa trên lớp (class-based OOP). Vì thế, các hàm (function), thuộc tính (
property) phải thuộc về một lớp nào đó.

Từ khóa `public` đánh dấu class có thể được truy cập ở bất kì đâu (class khác, chương trình khác,…) Thông thường sẽ dùng
`public`, những trường hợp khác sẽ được tìm hiểu cụ thể sau.

Từ khóa `class` dùng để khai báo class (lớp). Tiếp theo sau từ khóa là tên lớp, chương trình mẫu phía trên có tên là
`Program`. Tên không được bắt đầu bằng số, các ký tự hợp lệ trong đặt tên lớp bao gồm: `A-Z`, `a-z`, `0-9`, `_` và `$`.

Lớp được bắt đầu bằng `{` và kết thúc bằng `}`, lưu ý **không có** `;`. Các thành phần nằm trong `{` `}` bao gồm các
class khác, method, field.

<aside>
💡

Best practices: Tên lớp phải là một danh từ, đặt tên theo cú pháp PascalCase (viết hoa ký tự đầu tiên mỗi từ), ví dụ:
MyProgram, BlogPost, ClassWithLongName,…

</aside>

## Method

Trong Java, các function (hàm) được hiểu là method (phương thức). Có 2 loại method: static method (phương thức thuộc
lớp) và instance method (phương thức thuộc đối tượng/object).

Static method được định nghĩa bởi keyword `static`, khi gọi (invoke/call) phương thức này, bạn sẽ không cần tạo một đối
tượng mới (gọi trực tiếp). Instance method là method phải được gọi thông qua một object.

Để hiểu rõ hơn, ta sẽ xét các trường hợp trên chương trình mẫu.

`System` là một class, `getProperty(”os.name”)` là một static method. Khi gọi method, ta trực tiếp sử dụng tên class và
gọi method ngay sau đó `System.getProperty(”os.name”);`

Đối với phương thức `println("Your operating system is " + os)`, nó là một method của lớp `PrintStream`, là một instance
method. Muốn gọi method này, ta phải gọi thông qua đối tượng đã được khởi tạo là `System.out`, instance/object của lớp
`PrintStream`.

Ta sẽ tìm hiểu sâu hơn về vấn đề này ở các bài sau.

Tương tự như tên class, tên method không được bắt đầu bằng số, ký tự phải thuộc các nhóm sau: `A-Z`, `a-z`, `0-9`, `_`
và `$`.

Hàm được bắt đầu bằng `{` và kết thúc bằng `}`, lưu ý **không có** `;`. Các thành phần nằm trong `{` `}` bao gồm các câu
lệnh (statement), biểu thức (expression).

`System.getProperty(String)`: method lấy giá trị thuộc tính của hệ thống.

`System.out.println(String)`: method xuất chuỗi ra console (màn hình).

<aside>
💡

Best practices: Tên lớp phải là một động từ, đặt tên theo cú pháp camelCase (từ đầu tiên viết thường, các từ còn lại
viết hoa chữ đầu tiên), ví dụ: getUser, setValue, migrateDatabase,…

</aside>

## `main(String[])` method

Để vào nhà, ta cần phải có cửa nhà. Tương tự, để chương trình chạy, ta cần phải có điểm vào (entry point). Trong chương
trình Java, static method `main(String[])` sẽ là điểm vào của chương trình. Nếu một chương trình không có method này,
chương trình đó sẽ không thể thực thi đúng cách. Hàm này sẽ được gọi đầu tiên, từ đây bạn có thể gọi các hàm chức năng
khác. Hãy nhớ cú pháp của hàm này

```java
public class App {
    static void main(String[] args) {
        // your program here
    }
}
```

và dĩ nhiên, nó phải nằm trong một lớp nào đó, `Program` hay `App` chẳng hạn.

## `var`

Từ khóa `var` dùng để khai báo một biến (variable). Trước Java 11, muốn khai báo một biến, ta phải sử dụng tên kiểu dữ
liệu của biến đó, ví dụ `String os = "...";`. Ta sử dụng `var`, để code ngắn gọn hơn, tránh lặp lại kiểu dữ liệu của
biến nhiều lần. Tuy nhiên, trong một số trường hợp, sử dụng kiểu dữ liệu để khai báo biến sẽ ngắn gọn hơn nhiều.

## `String`

`String` trong Java là class, đồng nghĩa với mỗi string là một object. Để tạo một string, ta sẽ dụng `“` `”` và nội dung
của chuỗi sẽ nằm ở giữa, ví dụ: `“os.name”`

Ta có thể kết hợp nhiều chuỗi thành một chuỗi bằng phép `+` , `“Your operating system is " + os`, `os` là một biến
string có dữ liệu trả về từ method `System.getProperty(”os.name”);`

## Kết luận

Việc nắm cú pháp của chương trình Java cơ bản sẽ giúp bạn học được ngôn ngữ này nhanh hơn. Ví các cú pháp nâng cao hơn
đều dựa trên những nguyên tắc cơ bản này.

Các nội dung cụ thể của từng phần sẽ được mổ sẽ chi tiết sau, phần này chỉ hướng bạn hình dung một chương trình Java nó
như thế nào.
