<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RoboFinanceTest</title>
</head>
<body>
<div>RoboFinance Тестовое задание</div>
<a href="/addcustomer">Добавить пользователя</a><br/>
<a href="/changeaddress">Изменить адрес пользователя</a><br/>
<a href="/search">Поиск пользователя</a><br/>

Таблица клиентов
<table border="1">
    <thead>
    <tr>
        <th>Код клиента</th>
        <th>Имя клиента</th>
    </tr>
    </thead>
    <body>
    <#list customers as customer>
        <tr>
            <td>${customer.id}</td>
            <td>${customer.first_name!}</td>
        </tr>
    </#list>
    </body>
</table>
</body>
</html>