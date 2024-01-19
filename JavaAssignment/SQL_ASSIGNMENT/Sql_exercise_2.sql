 /*
 Write separate queries using a join, a subquery, a CTE, and then an EXISTS to list all 
 AdventureWorks customers who have not placed an order.
 */

 -- USING JOIN 
 SELECT *
FROM Sales.Customer AS c
LEFT JOIN Sales.SalesOrderHeader AS soh
ON c.CustomerID = soh.CustomerID
WHERE soh.CustomerID IS NULL order by c.CustomerID;

-- USING SUB_QUERY
SELECT *
FROM Sales.Customer AS c
WHERE c.CustomerID NOT IN (
  SELECT CustomerID
  FROM Sales.SalesOrderHeader
) order by CustomerID;

-- USING CTE
-- CTE returns rows from the first SELECT statement that are not returned by the second SELECT statement. 
WITH CustomersWithoutOrders AS ( 
  SELECT CustomerID
  FROM AdventureWorks.Sales.Customer
  EXCEPT
  SELECT CustomerID
  FROM AdventureWorks.Sales.SalesOrderHeader
)
select * from 
CustomersWithoutOrders cwo inner join Sales.Customer sc 
on cwo.CustomerID = sc.CustomerID order by sc.CustomerID;


-- USING EXISTS query:
SELECT *
FROM Sales.Customer c
where NOT EXISTS(
	SELECT 1
	FROM Sales.SalesOrderHeader s
	WHERE  c.customerID = s.customerID
) order by CustomerID

