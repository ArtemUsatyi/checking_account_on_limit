# Тестовое задание по разработке микросервиса.

Данный проект создан для интеграции в существующую банковскую систему.

Миросервис должен:
 - Получать информацию о проделанной транзакции рублях (RUB)
   и других валютах в реальном времени и сохранять ее в БД;
 - Хранить лимит по расходам в долларах США (USD) раздельно для двух
   категорий расходов: товаров и услуг. Если не установлен, принимать лимит равным
   1000 USD;
 - Запрашивать данные биржевых курсов валютных пар RUB/USD, EUR/USD по
   дневному интервалу (1day/daily) и хранить их в собственной базе данных;
 - Помечать транзакции, превысившие месячный лимит операций (технический флаг
   limit_exceeded);
 - Дать возможность клиенту установить новый лимит. При установлении нового
   лимита микросервисом автоматически выставляется текущая дата, не позволяя
   выставить ее в прошедшем или будущем времени. Обновлять существующие
   лимиты запрещается;
 - по запросу клиента возвращать список транзакций, превысивших лимит, с
   указанием лимита, который был превышен (дата установления, сумма лимита,
   валюта (USD)) не успел сделать.

### Использованные технологии в проекте
* Java Core
* Spring Boot
* JPA Hibernate
* Spring Data

