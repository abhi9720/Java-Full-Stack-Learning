/*
Show the most recent five orders that were purchased from account numbers that have spent more than $70,000 with
AdventureWorks
*/


SELECT RowNum,AccountNumber, SalesOrderID, OrderDate, TotalDue
FROM (
  SELECT 
    soh.AccountNumber,
    soh.SalesOrderID,
    soh.OrderDate,
    soh.TotalDue,
    ROW_NUMBER() OVER (PARTITION BY soh.AccountNumber ORDER BY soh.OrderDate DESC) AS RowNum
  FROM Sales.SalesOrderHeader soh
  WHERE soh.AccountNumber IN (
    SELECT soh.AccountNumber
    FROM Sales.SalesOrderHeader soh
    GROUP BY soh.AccountNumber
    HAVING SUM(soh.TotalDue) > 70000 /*TotalDue is amount payed by customer */
  )
) AS OrderDetails
WHERE RowNum <= 5 ;
-------------------------------------------------------------------------------
-- TEST
-- for this account number we have 10 order we selected top 5 order 
select * from Sales.SalesOrderHeader where AccountNumber='10-4020-000036';



