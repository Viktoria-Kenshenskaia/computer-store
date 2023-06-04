# computer-store

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Gradle](https://img.shields.io/badge/Gradle-blueviolet)](https://gradle.org/releases/)
[![H2 Database](https://img.shields.io/badge/H2%20Database-orange)](https://www.h2database.com/html/main.html)
[![JUnit](https://img.shields.io/badge/JUnit-green)](https://junit.org/)
[![Mockito](https://img.shields.io/badge/Mockito-green)](https://site.mockito.org/)

## Описание проекта

Проект "computer-store" представляет собой веб-приложение для управления магазином компьютеров и комплектующих. Приложение позволяет добавлять, редактировать и просматривать информацию о товарах в магазине.

## Требования

Перед запуском приложения убедитесь, что у вас установлены следующие компоненты:

- [Java Development Kit (JDK) версии 17 или выше](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Gradle версии 7.0.2 или выше](https://gradle.org/releases/)

## Установка

1. Клонируйте репозиторий на свой локальный компьютер: git clone <URL репозитория>
2. Перейдите в каталог проекта: cd computer-store

## Запуск

1. Соберите проект с помощью Gradle: gradle build
2. Запустите приложение: java -jar build/libs/computer-store.jar


3. Откройте веб-браузер и перейдите по адресу: `http://localhost:8080`

## Использование

После успешного запуска приложения вы можете использовать его следующим образом:

- Добавление товара:
    - Endpoint: `POST /products`
    - Запрос: JSON с информацией о товаре

  Пример JSON для добавления монитора:
  ```json
  Content-Type: application/json
  typeProduct: monitor
  {
    "serialNumber": "0012345",
    "manufacturer": "Some Manufacturer",
    "price": 129.9,
    "quantityInStock": 10,
    "diagonal": 42.0
  }

- Редактирование товара:
    - Endpoint: `PUT /products/{id}`
    - Запрос: JSON с обновленной информацией о товаре
  
      Пример JSON для редактирования монитора:

  ```json
  Content-Type: application/json
  typeProduct: monitor
  {
    "serialNumber": "0012345",
    "manufacturer": "Some Manufacturer",
    "price": 259.9,
    "quantityInStock": 25,
    "diagonal": 48.0
  }

- Просмотр всех товаров определенного типа:
    - Endpoint: `GET /products?type={type}`

- Просмотр товара по идентификатору:
    - Endpoint: `GET /products/{id}`

## База данных

В данном проекте используется in-memory база данных H2. При каждом запуске приложения база данных будет инициализирована заново и заполнена тестовыми данными.







