# Дипломный проект профессии «Тестировщик»
Ссылка на Дипломное задание: 

```
https://github.com/netology-code/qa-diploma
```

***Задача***: автоматизировать позитивные и негативные сценарии покупки тура.

## Тестовые артефакты
- [План тестирования](https://github.com/Lesha55-90/Graduate_work/blob/main/documents/Plan.md)
- [Отчет по итогам тестирования](https://github.com/Lesha55-90/Graduate_work/blob/main/documents/Report.md)
- [Gradle-отчет](https://github.com/Lesha55-90/Graduate_work/blob/main/documents/images/Gradle_Report.png)
- [Allure-отчет](https://github.com/Lesha55-90/Graduate_work/blob/main/documents/images/Allure_Report.png)
- [Отчет по автоматизации и подведение итогов](https://github.com/Lesha55-90/Graduate_work/blob/main/documents/Summary_and_Auto.md)


## Запуск приложения

***Подготовительный этап***:
- Установить IntelliJ IDEA;
- Установить Node.js;
- Установать и запустить Docker Desktop; 
- Скопировать репозиторий с Github по ссылке: 
```
https://github.com/Lesha55-90/Graduate_work
```
- Открыть проект в IntelliJ IDEA;


***Развернуть контейнеры MySQL, PostgreSQL и платформу Node.js***:
- Запустить файл *docker-compose.yml*:
```
docker compose up -build
```

***Запустить тестируемый сервис***:
- В новой вкладке терминала в IDEA ввести команду:
  
Для MySQL:
```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```

Для PostgreSQL:
```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```
   
   
 ***Убедиться в готовности сервиса***:
 - приложение должно быть доступно по адресу:

```
http://localhost:8080/
```

## Запустить тесты

- В новой вкладке терминала в IDEA ввести команду:
- 
Для MySQL:

```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```

Для PostgreSQL:

```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```

## Формирование отчёта о тестировании
- Для формирования отчёта через Allure, в новой вкладке терминала в IDEA вводим команду:
```
./gradlew allureServe
```

## Остановка сервиса и контейнеров
- В терминале, где запущен сервис, ввести команду:
***`Ctrl+C`***

- В терминале, где развёрнуты контейнеры (файл docker-compose.yml), ввести команды:
 
***`Ctrl+C`*** 

а затем команду:

```
docker compose down
```
