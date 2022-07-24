# Тестовый проект по автоматизации тестирования API https://restful-booker.herokuapp.com/. 

## :memo: Содержание:

- [Реализованные проверки](#boom-Реализованные-проверки)
- [Технологии](#classical_building-Технологии)
- [Сборка в Jenkins](#man_cook-Jenkins-job)
- [Запуск из терминала](#electron-Запуск-тестов-из-терминала)
- [Allure отчет](#bar_chart-Allure-отчет)
- [Allure TestOps](#bar_chart-alluretestops)
- [Telegram Notifications](#robot-telegram)


## :boom: Реализованные проверки

- ✓ Проверка логина
- ✓ Проверка добавления нового бронирования
- ✓ Проверка апдейта существующего бронирования
- ✓ Попытка апдейта несуществующего бронирования
- ✓ Проверка удаления бронирования

## :classical_building: Технологии

<p align="center">
<img width="6%" title="Idea" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Rest Assured" src="images/logo/RestAssured.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_TestOps.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/Junit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Jenkins" src="images/logo/Telegram.svg">
</p>

## :man_cook: Jenkins job
<img src="images/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a>  <a target="_blank" href="https://jenkins.autotests.cloud/job/REST-API-Example-ED/">Jenkins job</a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/REST-API-Example-ED/"><img src="images/screenshots/jenkins0.png" alt="Jenkins"/></a>
</p>

## :electron: Запуск тестов из терминала

```
gradle clean test
```

## :bar_chart: Allure-отчет
<img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/C01-elena_alexandrovna31-MobileUI/4/allure/">Allure report</a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/REST-API-Example-ED/7/allure/"><img src="images/screenshots/allure-main.png" alt="Allure"/></a>
</p>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/REST-API-Example-ED/7/allure/"><img src="images/screenshots/allure-stata.png" alt="Allure"/></a>
</p>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/REST-API-Example-ED/7/allure/"><img src="images/screenshots/allure-tests.png" alt="Allure"/></a>
</p>

## :bar_chart: AllureTestOps
<img src="images/logo/Allure_TestOps.svg" width="25" height="25"  alt="Allure"/></a> Отчет в <a target="_blank" href="https://allure.autotests.cloud/project/1469/dashboards">Allure TestOps</a>
<p align="center">
<img src="images/screenshots/allure-testops-testcases.png" alt="AllureTestOps"/>
</p>
<p align="center">
<img src="images/screenshots/allure-testops-stata.png" alt="JAllureTestOps"/>
</p>

## :robot: Telegram
<img src="images/logo/Telegram.svg" width="25" height="25"  alt="Allure"/></a> Уведомления в Telegram
<p align="center">
<img src="images/screenshots/telegram-report.png" alt="Jenkins"/>
</p>
