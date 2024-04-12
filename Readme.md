# 1. Используя команду cat в терминале операционной системы Linux, создать два файлаДомашниеживотные(заполнивфайлсобаками,кошками, хомяками) и ВьючныеживотнымизаполнивфайлЛошадьми,верблюдамии ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав емуновоеимя(Друзьячеловека).

![Задание 1](https://github.com/VVV-Devastator/Attestation/blob/main/im.jpg)

![Задание 1](https://github.com/VVV-Devastator/Attestation/blob/main/im1.jpg)

# 2. Создать директорию, переместить файл туда.


![Задание 2](https://github.com/VVV-Devastator/Attestation/blob/main/im2.jpg)


# 3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
# 4. Установить и удалить deb-пакет с помощью dpkg.
# 5. Выложитьисториюкомандвтерминалеubuntu

![Задание 3](https://github.com/VVV-Devastator/Attestation/blob/main/im3.jpg)



# 6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).


![Задание 6](https://github.com/VVV-Devastator/Attestation/blob/main/im4.jpg)

# 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”

CREATE DATABASE Human_friends;


# 8. Создать таблицы с иерархией из диаграммы в БД

USE Human_friends;
CREATE TABLE animals
(
	id INT AUTO_INCREMENT PRIMARY KEY, 
	class_name VARCHAR(20)
);

INSERT INTO animals (class_name)
VALUES ('вьючные'),
('домашние');  

CREATE TABLE home_animals
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    genus_name VARCHAR (20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES animal_classes (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO home_animals (genus_name, class_id)
VALUES ('Кошки', 1),
('Собаки', 1),  
('Хомяки', 1); 



CREATE TABLE packed_animals
(   id INT AUTO_INCREMENT PRIMARY KEY,
    genus_name VARCHAR (20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES animal_classes (iId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO packed_animals genus_name , class_id )
VALUES ('Лошади', 2),
('Ослы', 2),  
('Верблюды', 2); 

CREATE TABLE dogs 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE cats 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);
    



CREATE TABLE hamsters 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE horses 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE donkeys 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    genus_id int,
    Foreign KEY (genus_id) REFERENCES home_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

# 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения






INSERT INTO cats (name, birthday, commands, genus_id)
VALUES ('Пушок', '2011-01-01', 'мяу', 1),
('тыг', '2020-01-01', "фу", 1),  
('дык', '2021-01-01', "пс", 1); 


INSERT INTO dogs (name, birthday, commands, genus_id)
VALUES ('кыш', '2020-01-01', 'ко мне', 2),
('лорд', '2029-01-21', "сидеть", 2),  
('ральф', '2019-07-02', "лежать", 2); 



INSERT INTO hamsters (name, birthday, commands, genus_id)
VALUES ('пух', '2019-02-02', NULL,  3),
('бик', '2019-03-06',NULL,  3),  
('бык', '2019-04-07', NULL, 3); 



INSERT INTO horses (name, birthday, commands, genus_id)
VALUES ('иго', '2020-01-12', 'галоп', 1),
('го', '2020-03-12', 'идти', 1),  
('тикитак', '2012-11-10', 'прячься', 1);


INSERT INTO donkeys (name, birthday, commands, genus_id)
VALUES ('иа', '2012-11-10', NULL, 2),
('аи', '2012-12-10', NULL, 2),  
('оо', '2012-01-10', 'стащи печеньку', 2);


INSERT INTO camels (name, birthday, commands, genus_id)
VALUES ('кэмэл', '2002-01-10', 'вернись', 3),
('рис', '2003-02-10', 'остановись', 3),  
('коля', '2004-03-10', 'улыбнись', 3);


# 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

DELETE FROM camels;

SELECT name, birthday, commands FROM horses
UNION SELECT  name, birthday, commands FROM donkeys;

# 11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

CREATE TEMPORARY TABLE animals AS 
SELECT *, 'Лошади' as genus FROM horses
UNION SELECT *, 'Ослы' AS genus FROM donkeys
UNION SELECT *, 'Собаки' AS genus FROM dogs
UNION SELECT *, 'Кошки' AS genus FROM cats
UNION SELECT *, 'Хомяки' AS genus FROM hamsters;

CREATE TABLE young animals AS
SELECT Name, Birthday, Commands, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM young animals;

# 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам

SELECT h.name, h.birthday, h.commands, pa.genus_name, ya.Age_in_month 
FROM horses h
LEFT JOIN yang_animal ya ON ya.nam = h.name
LEFT JOIN packed_animals pa ON pa.id = h.genus_id
UNION 
SELECT d.name, d.birthday, d.commands, pa.genus_name, ya.Age_in_month 
FROM donkeys d 
LEFT JOIN yang_animal ya ON ya.name = d.name
LEFT JOIN packed_animals pa ON pa.id = d.genus_id
UNION
SELECT c.name, c.birthday, c.commands, ha.genus_name, ya.Age_in_month 
FROM cats c
LEFT JOIN yang_animal ya ON ya.name = c.name
LEFT JOIN home_animals ha ON ha.id = c.genus_id
UNION
SELECT d.name, d.birthday, d.commands, ha.genus_name, ya.Age_in_month 
FROM dogs d
LEFT JOIN yang_animal ya ON ya.name = d.name
LEFT JOIN home_animals ha ON ha.id = d.genus_id
UNION
SELECT hm.name, hm.birthday, hm.commands, ha.genus_name, ya.Age_in_month 
FROM hamsters hm
LEFT JOIN yang_animal ya ON ya.name = hm.name
LEFT JOIN home_animals ha ON ha.id = hm.genus_id;

# 13.Создать класс с Инкапсуляцией методов и наследованием по диаграмме.

![Задание 13](https://github.com/VVV-Devastator/Attestation/blob/main/im5.jpg)
