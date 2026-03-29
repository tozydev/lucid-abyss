---
title: Danh sách các bản phân phối JDK phổ biến hiện nay
author: tozydev
publishedAt: 2024-08-21T00:00:00Z
topic: java
tags:
  - java
  - jdk
  - openjdk
description: Danh sách các bản phân phối JDK phổ biến, so sánh đặc điểm, giấy phép và liên kết tải về.
---

## Giới thiệu

Để xây dựng và chạy các ứng dụng trên JVM (Java Virtual Machine), ta cần một trình biên dịch (compiler), các thư viện (
runtime libraries) và một máy ảo Java (JVM). Các thành phần trên tạo thành một nền tảng Java (Java Platform), nền tảng
này tuân theo tiêu chuẩn có tên
là [Java Platform, Standard Edition ("Java SE") Specification](https://docs.oracle.com/javase/specs/).

[OpenJDK](https://openjdk.org/) là dự án mã nguồn mở (sử dụng giấy
phép [GPL-2.0](https://openjdk.org/legal/gplv2+ce.html)) triển khai các đặc tả của Java SE. Các nhà phân phối sẽ dựa
trên mã nguồn đó và xây dựng lên các **bản phân phối** cho các nền tảng được hỗ trợ. Các bản phân phối này sẽ khác nhau
về giấy phép, hỗ trợ thương mại, nền tảng hỗ trợ và tần suất cập nhật, vá lỗi. Ngoài ra, các bản phân phối này cũng có
thể có thêm một số tính năng bổ sung được nhà phát hành thêm vào.

## Các bản phân phối phổ biến

| Tên                                                                | Nhà phân phối     | Links                                                                                                                                       |
|--------------------------------------------------------------------|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| [Bisheng](https://www.openeuler.org/en/other/projects/bishengjdk/) | Huawei            | [download](https://www.hikunpeng.com/en/developer/devkit/download/jdk)                                                                      |
| [Corretto](https://aws.amazon.com/corretto)                        | Amazon            | [download](https://aws.amazon.com/corretto) [docker](https://hub.docker.com/_/amazoncorretto)                                               |
| [Dragonwell](https://dragonwell-jdk.io/)                           | Alibaba           | [download](https://dragonwell-jdk.io/)                                                                                                      |
| GraalVM                                                            | Oracle            | [download](https://www.graalvm.org/downloads/) [docker](https://container-registry.oracle.com/ords/ocr/ba/graalvm/native-image)             |
| GraalVM                                                            | GraalVM Community | [download](https://github.com/graalvm/graalvm-ce-builds/releases/) [docker](https://github.com/graalvm/container/pkgs/container/graalvm-ce) |
| [Java SE Development Kit](https://www.oracle.com/java/)            | Oracle            | [download](https://www.oracle.com/java/technologies/downloads/)                                                                             |
| [Kona](https://tencent.github.io/konajdk/)                         | Tencent           | [download](https://tencent.github.io/konajdk/)                                                                                              |
| [Liberica](https://bell-sw.com/libericajdk/)                       | Bellsoft          | [download](https://bell-sw.com/pages/downloads/) [docker](https://hub.docker.com/r/bellsoft/liberica-runtime-container)                     |
