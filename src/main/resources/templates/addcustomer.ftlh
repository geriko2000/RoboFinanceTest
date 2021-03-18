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
    <input type="text" name="first_name" placeholder="Имя" id="firstname"/><br/>
    <label for="lastname">Фамииля</label><br/>
    <input type="text" name="last_name" placeholder="Фамилия" id="lastname"/><br/>
    <label for="middlename">Отчество</label><br/>
    <input type="text" name="middle_name" placeholder="Отчество" id="middlename"/><br/>
    <label for="sex">Пол</label><br/>
    <select name="sex" id="sex">
        <option value="male">Мужской</option>
        <option value="female">Женский</option>
    </select><br/>
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
</body>
</html>