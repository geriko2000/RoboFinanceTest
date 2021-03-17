<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RoboFinanceTest</title>
</head>
<body>
<div>RoboFinance Тестовое задание</div>
<div>Поиск в базе по имени фамилии</div>
<form method="get">

    <input type="text" name="customer">
    <button type="submit">Поиск</button>
    <br/>
</form>
<b>${message!}</b><br/>
Результаты поиска:
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
    </tr>
    </thead>
    <body>
    <#list customers as customer>
        <tr>
            <td>${customer.first_name!}</td>
            <td>${customer.last_name!}</td>
            <td>${customer.middle_name!}</td>
            <td>${customer.sex}</td>
            <td>${customer.actual_address_id.country!}</td>
            <td>${customer.actual_address_id.region!}</td>
            <td>${customer.actual_address_id.city!}</td>
            <td>${customer.actual_address_id.street!}</td>
            <td>${customer.actual_address_id.house!}</td>
            <td>${customer.actual_address_id.flat!}</td>
            <td>${customer.actual_address_id.created!}</td>
            <td>${customer.actual_address_id.modified!}</td>
        </tr>
        <#else>
        </br>Пользователи отсутствуют
    </#list>
    </body>
</table>

</body>
</html>