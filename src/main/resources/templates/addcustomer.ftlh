<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RoboFinanceTest</title>
</head>
<body>
<div>RoboFinance Тестовое задание</div>
<div>Добавить нового пользователя</div>
<form method="post">
    <label for="firstname">Имя</label><br/>
    <input type="text" name="first_name" placeholder="Иванов" id="firstname"/><br/>
    <label for="lastname">Фамииля</label><br/>
    <input type="text" name="last_name" placeholder="example@example.ru" id="lastname"/><br/>
    <label for="middlename">Отчество</label><br/>
    <input type="text" name="middle_name" placeholder="88005553535" id="middlename"/><br/>
    <label for="sex">Пол</label><br/>
    <select name="sex" id="sex">
        <option value="male">Мужской</option>
        <option value="female">Женский</option>
    </select>
    <label for="country">Страна</label><br/>
    <input type="text" name="country" placeholder="Кибердянск" id="country"/><br/><br/>
    <label for="region">Регион</label><br/>
    <input type="text" name="region" placeholder="Кибердянск" id="region"/><br/><br/>
    <label for="city">Город</label><br/>
    <input type="text" name="city" placeholder="Кибердянск" id="city"/><br/><br/>
    <label for="street">Улица</label><br/>
    <input type="text" name="street" placeholder="Кибердянск" id="street"/><br/><br/>
    <label for="house">Дом</label><br/>
    <input type="text" name="house" placeholder="Кибердянск" id="house"/><br/><br/>
    <label for="flat">Квартира</label><br/>
    <input type="text" name="flat" placeholder="Кибердянск" id="flat"/><br/><br/>
    <button type="submit">Добавить</button>
</form>

</body>
</html>