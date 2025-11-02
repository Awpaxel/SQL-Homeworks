package SQLHomeworks8;

public class SQLHomeworks8

-- 1. Employee tablosunu oluşturma
CREATE TABLE employee (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    birthday DATE,
    email VARCHAR(100)
);

-- 2. Mockaroo ile 50 adet veri ekleme
INSERT INTO employee (id, name, birthday, email) VALUES
(1, 'Alyse Joddins', '1990-05-15', 'ajoddins0@example.com'),
(2, 'Barnaby Dalgety', '1985-08-22', 'bdalgety1@example.com'),
(3, 'Cordie Blaw', '1992-03-10', 'cblaw2@example.com'),
(4, 'Danya Howieson', '1988-11-30', 'dhowieson3@example.com'),
(5, 'Ernesta Boeter', '1995-07-18', 'eboeter4@example.com'),
(6, 'Ferd Kleinmann', '1987-02-25', 'fkleinmann5@example.com'),
(7, 'Guthrie Lambeth', '1993-09-14', 'glambeth6@example.com'),
(8, 'Hedwig Scrimgeour', '1991-06-08', 'hscrimgeour7@example.com'),
(9, 'Iorgo Dunleavy', '1989-12-19', 'idunleavy8@example.com'),
(10, 'Jeanie Batchelor', '1994-04-27', 'jbatchelor9@example.com'),
(11, 'Kathy Mumbray', '1986-10-03', 'kmumbray10@example.com'),
(12, 'Lemmy Brickwood', '1992-07-22', 'lbrickwood11@example.com'),
(13, 'Maryrose Dunning', '1990-01-16', 'mdunning12@example.com'),
(14, 'Noel Christoffersen', '1988-05-29', 'nchristoffersen13@example.com'),
(15, 'Orel Timmis', '1995-11-11', 'otimmis14@example.com'),
(16, 'Perla Gorick', '1987-08-05', 'pgorick15@example.com'),
(17, 'Quillan Shavel', '1993-03-24', 'qshavel16@example.com'),
(18, 'Roxane Brecknall', '1991-09-17', 'rbrecknall17@example.com'),
(19, 'Sal Heintzsch', '1989-02-08', 'sheintzsch18@example.com'),
(20, 'Tabby Worsnop', '1994-12-13', 'tworsnop19@example.com'),
(21, 'Urbano Crosland', '1986-06-20', 'ucrosland20@example.com'),
(22, 'Vanda Gascoine', '1992-10-31', 'vgascoine21@example.com'),
(23, 'Wallas Bolver', '1990-07-04', 'wbolver22@example.com'),
(24, 'Xena Dufton', '1988-01-26', 'xdufton23@example.com'),
(25, 'Yul Beamish', '1995-05-09', 'ybeamish24@example.com'),
(26, 'Zak Kleinplatz', '1987-11-15', 'zkleinplatz25@example.com'),
(27, 'Alyce Pashbee', '1993-04-28', 'apashbee26@example.com'),
(28, 'Brewer Cranefield', '1991-08-12', 'bcranefield27@example.com'),
(29, 'Cassie Drinkall', '1989-12-06', 'cdrinkall28@example.com'),
(30, 'Derick Emerton', '1994-03-19', 'demerton29@example.com'),
(31, 'Elset Gieraths', '1986-09-23', 'egieraths30@example.com'),
(32, 'Fanchette Croxall', '1992-01-07', 'fcroxall31@example.com'),
(33, 'Gwyn Treadger', '1990-11-30', 'gtreadger32@example.com'),
(34, 'Hermy Balducci', '1988-07-14', 'hbalducci33@example.com'),
(35, 'Irina Dunsmuir', '1995-02-21', 'idunsmuir34@example.com'),
(36, 'Joby Eadington', '1987-06-05', 'jeadington35@example.com'),
(37, 'Karlotta Klimaszewski', '1993-10-18', 'kklimaszewski36@example.com'),
(38, 'Lemuel Hablet', '1991-04-02', 'lhablet37@example.com'),
(39, 'Mathilde Knighton', '1989-08-25', 'mknighton38@example.com'),
(40, 'Nev Kubecka', '1994-12-08', 'nkubecka39@example.com'),
(41, 'Orazio Philpott', '1986-05-11', 'ophilpott40@example.com'),
(42, 'Portia Heckney', '1992-09-24', 'pheckney41@example.com'),
(43, 'Quintus Matley', '1990-03-17', 'qmatley42@example.com'),
(44, 'Reube Brockley', '1988-11-01', 'rbrockley43@example.com'),
(45, 'Shaylah Leyland', '1995-07-26', 'sleyland44@example.com'),
(46, 'Terri Berntssen', '1987-01-09', 'tberntssen45@example.com'),
(47, 'Uriah Hanbidge', '1993-05-22', 'uhanbidge46@example.com'),
(48, 'Valida Maffia', '1991-12-15', 'vmaffia47@example.com'),
(49, 'Wiatt Retchless', '1989-04-28', 'wretchless48@example.com'),
(50, 'Xylia Tocher', '1994-08-11', 'xtocher49@example.com');

-- 3. UPDATE İşlemleri (5 adet)

-- 3.1. ID'ye göre name güncelleme
UPDATE employee
SET name = 'John Doe Updated'
WHERE id = 1;

-- 3.2. Name'e göre email güncelleme
UPDATE employee
SET email = 'newemail@example.com'
WHERE name = 'Barnaby Dalgety';

-- 3.3. Birthday'e göre name güncelleme
UPDATE employee
SET name = 'Birthday Match Updated'
WHERE birthday = '1992-03-10';

-- 3.4. Email'e göre birthday güncelleme
UPDATE employee
SET birthday = '2000-01-01'
WHERE email = 'dhowieson3@example.com';

-- 3.5. ID aralığına göre çoklu sütun güncelleme
UPDATE employee
SET name = 'Bulk Updated',
    email = 'bulkupdate@example.com'
WHERE id BETWEEN 45 AND 50;

-- 4. DELETE İşlemleri (5 adet)

-- 4.1. ID'ye göre silme
DELETE FROM employee
WHERE id = 10;

-- 4.2. Name'e göre silme
DELETE FROM employee
WHERE name = 'Cordie Blaw';

-- 4.3. Birthday'e göre silme
DELETE FROM employee
WHERE birthday = '1995-07-18';

-- 4.4. Email'e göre silme
DELETE FROM employee
WHERE email = 'bulkupdate@example.com';

-- 4.5. ID aralığına göre toplu silme
DELETE FROM employee
WHERE id BETWEEN 46 AND 50;

