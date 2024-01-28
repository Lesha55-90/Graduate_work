# Дипломный проект профессии «Тестировщик»
[Ссылка на Дипломное задание](https://github.com/netology-code/qa-diploma)

***Задача***: автоматизировать позитивные и негативные сценарии покупки тура.

## Тестовая документация
- [План тестирования](https://.....Plan.md);
- [Отчет по итогам автоматизации](https://...Summary.md)
- [Отчёт по итогам тестирования](https://...Report.md);


## Запуск приложения

***Подготовительный этап***
- Установить IntelliJ IDEA;
- Установать и запустить Docker Desktop;
- Установить Node.js; 
- Скопировать репозиторий с Github [по ссылке](https://github.com/Lesha55-90/Graduate_work);
- Открыть проект в IntelliJ IDEA;


***Развернуть контейнеры MySQL, PostgreSQL и платформу Node.js***
- Запустить файл *docker-compose.yml*:
   ```
   docker compose up -build
   ```

***Запуск тестируемого сервиса***
- В новой вкладке терминала в IDEA запустить тестируемый сервис:
  
  Для MySQL:
   ```
   java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
   ```

  Для PostgreSQL:
   ```
   java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
   ```
   
   
 ***Убедиться в готовности сервиса***
 - приложение должно быть доступно по адресу:

   ```
   http://localhost:8080/
   ```

## Запуск тестов

В новой вкладке терминала в IDEA запустить тесты:

   ```
   ./gradlew clean test
   ```


## Формирование отчёта о тестировании
Для формирования отчёта через Allure, в новой вкладке терминала в IDEA вводим команду:
```
./gradlew allureServe
```

## Остановка сервиса контейнеров
- В терминале, где запущен сервис, ввести команду:
***`Ctrl+C`***

- В терминале, где развёрнуты контейнеры (файл docker-compose.yml), ввести команды:
 
***`Ctrl+C`*** 

а затем команду:

```
docker compose down
```
