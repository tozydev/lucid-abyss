---
title: Java Types
author: tozydev
publishedAt: 2024-09-30T00:00:00Z
topic: java
tags:
  - java
  - types
description: Tổng hợp các kiểu dữ liệu trong Java, bao gồm primitive types và reference types, cùng bảng so sánh chi tiết.
---

Type hay data type (kiểu dữ liệu) là dạng dữ liệu sẽ được lưu trữ và trao đổi trong chương trình. Trong Java, type có ở
variable (biến), field (trường), method return (trả về của hàm), parameter (tham số method, constructure),…

Java phân type thành 2 nhóm: primitive type (kiểu nguyên thủy) và reference type (kiểu tham chiếu)

## Primitive types

Có 8 loại primitive type: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`. Những loại này không
phải là đối tượng, chúng là dữ liệu thuần túy (raw value).

| Type      | Size (bits) | Minimum                    | Maximum                    | Default value |
|-----------|-------------|----------------------------|----------------------------|---------------|
| `byte`    | 8           | $-2^7$ (-128)              | $2^7 -1$ (127)             | `0`           |
| `short`   | 16          | $-2^{15}$ (-32 768)        | $2^{15}-1$(32 767)         | `0`           |
| `int`     | 32          | $-2^{31}$ (-2 147 483 648) | $2^{31}-1$ (2 147 483 647) | `0`           |
| `long`    | 64          | $-2^{63}$                  | $2^{63-1}$                 | `0L`          |
| `float`   | 32          | $-2^{-149}$                | $(2-2^{-23})\cdot2^{127}$  | `0.0f`        |
| `double`  | 64          | $-2^{-1074}$               | $(2-2^{-52})\cdot2^{1023}$ | `0.0d`        |
| `char`    | 16          | 0                          | $2^{16}-1$ (65355)         | `‘\u0000’`    |
| `boolean` | 1           | $NaN$                      | $NaN$                      | `false`       |

## Reference types

Type thứ 2 trong Java là reference type. Bản chất của nó không chứa giá trị nào cả, nó chứa tham chiếu đến một đối tượng
trong bộ nhớ heap của JVM. Nó tương tự như pointer của C++.

## References

https://www.baeldung.com/java-primitives
