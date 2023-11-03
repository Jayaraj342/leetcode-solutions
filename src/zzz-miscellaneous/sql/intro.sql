--https://www.edureka.co/blog/interview-questions/sql-query-interview-questions
--https://www.w3schools.com/mysql/trymysql.asp?filename=trysql_select_all
--https://onecompiler.com/mysql/3ygckqt52
--https://www.edureka.co/blog/sql-joins-types
--https://www.studytonight.com/dbms/database-normalization.php

-- create only structure
SELECT *
INTO NewTable
FROM TableStructureIWishToClone LIMIT 0;

-- create from existing table with all data
CREATE TABLE SALARY AS
   SELECT ID, SALARY
   FROM CUSTOMERS;
SELECT *
INTO NewTable
FROM TableStructureIWishToClone;

--first 2max prices
--0 is offset, 2 number of rows
Select * FROM Products
order by price desc limit 0,2

--If only 4record are available-gets 4
select * from Products
where SupplierID = 2
order by price desc
limit 0, 5

--first 2 max prices-without using limit
select * from Products p1
where 2 > (
select count(distinct(price)) from Products p2 where p2.price > p1.price
)
order by price desc

-- >2 same columns with same group of 2 columns
select * from Products p1 join
(
SELECT * FROM Products
group by SupplierID, CategoryID
having count(*) > 1
) p2
on p1.SupplierID = p2.SupplierID and p1.CategoryID = p2.CategoryID;

-- reverse 2 columns at a time
update customers set city = 'Bengaluru'
update customers set city = 'Mysore' where customerid < 46
update customers set  city = (case when city = 'Mysore' then 'Bengaluru' when city = 'Bengaluru' then 'Mysore' end)

-- flipkart, amazon min value https://stackoverflow.com/questions/3024670/how-can-i-add-a-column-to-this-union-result
CREATE TABLE FLIPKART (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  price INTEGER
);
CREATE TABLE AMAZON (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  price INTEGER
);

INSERT INTO FLIPKART VALUES (0001, 'shoes', 90);
INSERT INTO FLIPKART VALUES (0002, 'phone', 70);
INSERT INTO FLIPKART VALUES (0003, 'dress', 60);

INSERT INTO AMAZON VALUES (0001, 'shoes', 70);
INSERT INTO AMAZON VALUES (0002, 'phone', 80);
INSERT INTO AMAZON VALUES (0003, 'dress', 70);

-- runs if strict mode is removed
SELECT temp.name, min(temp.price)  FROM (
  SELECT id, name, price, 'flipkart' as website FROM FLIPKART
  union
  SELECT  id, name, price, 'amazon' as website FROM AMAZON
) temp
group by temp.name;

SELECT name, price, website FROM (
  SELECT id, name, price, 'flipkart' as website FROM FLIPKART
  union
  SELECT  id, name, price, 'amazon' as website FROM AMAZON
) temp
where (name, price) IN (
 SELECT name, min(price) FROM (
    SELECT id, name, price, 'flipkart' as website FROM FLIPKART
    union
    SELECT  id, name, price, 'amazon' as website FROM AMAZON
 ) temp2 group by name
);

-- left join
SELECT Categories.CategoryName, sum(price)  FROM Products left join Categories on Categories.CategoryID = Products.CategoryID
group by Categories.CategoryName
order by sum(price) desc
limit 5;

-- date/time
SELECT CURTIME(); -- 08:03:09
SELECT SYSDATE(); -- 2022-09-18 08:02:47

-- odd rows from table without using ID
CREATE TABLE EMPLOYEE (
  empId INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  dept TEXT NOT NULL
);

-- insert
INSERT INTO EMPLOYEE VALUES (0001, 'Clark', 'Sales');
INSERT INTO EMPLOYEE VALUES (0002, 'Dave', 'Accounting');
INSERT INTO EMPLOYEE VALUES (0003, 'Ava', 'Sales');

-- fetch
SET @i = 0;
SELECT * from (SELECT *, @i := @i + 1 as serialNo
from EMPLOYEE) temp
where mod(temp.serialNo, 2) = 1;

-- not working - to get 50%  records
SELECT * FROM EMPLOYEE
limit ROUND(SELECT COUNT(*) / 2 FROM EMPLOYEE);

-- Join 3 tables https://learnsql.com/blog/how-to-join-3-tables-or-more-in-sql/

-- Top 3 costly products from each category (works only if prices are different)
select sq.ProductID, sq.ProductName, sq.CategoryID, sq.Price from
(select t1.*, (select count(*) from Products t2 where t1.Price<=t2.Price and t1.CategoryID = t2.CategoryID) as rownum from Products t1) sq
where rownum <= 3
order by CategoryID asc, Price desc;

-- product with max price in a given category
SELECT ProductID, ProductName, CategoryID, Price FROM Products where (CategoryID, Price) IN
(SELECT CategoryID, max(Price) FROM Products group by CategoryID)
order by CategoryID;