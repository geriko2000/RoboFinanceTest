<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RoboFinanceTest</title>
</head>
<body>
<div>RoboFinance Тестовое задание</div>
<div>Изменение адреса пользователя</div>
<table border="1">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Пол</th>
        <th>Страна</th>
        <th>Регион</th>
        <th>Город</th>
        <th>Улица</th>
        <th>Дом</th>
        <th>Квартира</th>
        <th>Создан</th>
        <th>Модифицирован</th>
        <th>Редактировать адрес</th>
    </tr>
    </thead>
    <body>
    <#list customers as customer>
        <tr>
            <td>${customer.first_name!}</td>
            <td>${customer.last_name!}</td>
            <td>${customer.middle_name!}</td>
            <td>${customer.sex}</td>
            <td>${customer.actualAddressId.country!}</td>
            <td>${customer.actualAddressId.region!}</td>
            <td>${customer.actualAddressId.city!}</td>
            <td>${customer.actualAddressId.street!}</td>
            <td>${customer.actualAddressId.house!}</td>
            <td>${customer.actualAddressId.flat!}</td>
            <td>${customer.actualAddressId.created!}</td>
            <td>${customer.actualAddressId.modified!}</td>
            <td><a href="/changeaddress/${customer.id}">✎</a></td>
        </tr>
    <#else>
        </br>Пользователи отсутствуют
    </#list>
    </body>
</table>
<a href="/">Главная страница</a><br/>
</body>
</html>