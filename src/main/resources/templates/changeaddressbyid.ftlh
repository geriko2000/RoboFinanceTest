<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RoboFinanceTest</title>
</head>
<body>
<div>RoboFinance Тестовое задание</div>
<div>Изменить адрес пользователя ${customerFirstName!} ${customerMiddleName!} ${customerLastName!}</div>
<form method="post">
    <input type="hidden" name="_method" value="put" />
    <label for="country">Страна</label><br/>
    <input type="text" name="country" placeholder="Страна" id="country"/><br/><br/>
    <label for="region">Регион</label><br/>
    <input type="text" name="region" placeholder="Регион" id="region"/><br/><br/>
    <label for="city">Город</label><br/>
    <input type="text" name="city" placeholder="Город" id="city"/><br/><br/>
    <label for="street">Улица</label><br/>
    <input type="text" name="street" placeholder="Улица" id="street"/><br/><br/>
    <label for="house">Дом</label><br/>
    <input type="text" name="house" placeholder="Дом" id="house"/><br/><br/>
    <label for="flat">Квартира</label><br/>
    <input type="text" name="flat" placeholder="Квартира" id="flat"/><br/><br/>
    <button type="submit">Добавить</button>
</form>
<b>${message!}</b><br/>
<a href="/">Главная страница</a><br/>
</body>
</html>