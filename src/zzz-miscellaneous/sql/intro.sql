--https://www.edureka.co/blog/interview-questions/sql-query-interview-questions
--https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_groupby
--https://www.edureka.co/blog/sql-joins-types

--first 2max prices
Select * FROM Products
order by price desc limit 0,2

--If only 4record are available-gets 4
select * from products
where SupplierID=2
order by price desc
limit 0,5

--first 2 max prices-without using limit
select * from products p1
where 2>(
select count(distinct(price))from products p2 where p2.price>p1.price
)
order by price desc

-- >2 same columns with same group of 2 columns
select p1.* from products p1 join
(
SELECT * FROM Products
group by SupplierID, CategoryID
having count(*)>1
) p2
on p1.SupplierID=p2.SupplierID and p1.CategoryID=p2.CategoryID;
