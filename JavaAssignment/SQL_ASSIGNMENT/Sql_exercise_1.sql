/*
1: Display the number of records in the [SalesPerson] table. (Schema(s) involved: Sales
*/
select count(*) as "Numer of Records" from  Sales.SalesPerson;



/*
2: Select both the FirstName and LastName of records from the Person table where the FirstName begins with the letter ‘B’.
(Schema(s) involved: Person)
*/
select FirstName, LastName from Person.Person where FirstName like 'B%';




/* 
3: Select a list of FirstName and LastName for employees where Title is one of Design Engineer, Tool Designer or Marketing
Assistant. (Schema(s) involved: HumanResources, Person)
*/
SELECT p.FirstName, p.LastName
FROM HumanResources.Employee e
JOIN Person.Person p ON e.BusinessEntityID = p.BusinessEntityID
WHERE e.JobTitle IN ('Design Engineer', 'Tool Designer', 'Marketing Assistant')
/*
using cross join :
select * from HumanResources.Employee hr , Person.Person pe where hr.BusinessEntityID = pe.BusinessEntityID 
and JobTitle in ('Design Engineer', 'Tool Designer', 'Marketing Assistant');
*/




/*
4: Display the Name and Color of the Product with the maximum weight. (Schema(s) involved: Production)
*/
-- Using Sub query :
select P.Name,P.Color,P.Weight from Production.Product as P where P.Weight = (select max(Weight) from Production.Product );
-- Using Top Clause
select top 1 P.Name,P.Color,P.Weight from Production.Product as P order by p.Weight desc;




/*
5: Display Description and MaxQty fields from the SpecialOffer table. Some of the MaxQty values are NULL, 
in this case display the value 0.00 instead. (Schema(s) involved: Sales)
*/

-- Using Case Expression
SELECT ssp.Description ,
Case 
	WHEN ssp.MaxQty IS Null THEN 0.00
	ELSE ssp.MaxQty
	End as MaxQty
from Sales.SpecialOffer ssp;
-- Using COALESCE Function 
SELECT ssp.Description, COALESCE(ssp.MaxQty,0.00) as MaxQty from Sales.SpecialOffer ssp;




/*
6: Display the overall Average of the [CurrencyRate].[AverageRate] values for the exchange rate ‘USD’ to ‘GBP’
for the year 2005 i.e. FromCurrencyCode = ‘USD’ and ToCurrencyCode = ‘GBP’. 
Note: The field [CurrencyRate].[AverageRate] is defined as 'Average exchange rate for the day.' (Schema(s) involved: Sales)
*/
SELECT AVG(scr.AverageRate) as OverallAverage from Sales.CurrencyRate scr WHERE scr.FromCurrencyCode='USD' and scr.ToCurrencyCode='GBP'
and YEAR(scr.CurrencyRateDate)=2005;




/*
7: Display the FirstName and LastName of records from the Person table where FirstName contains the letters ‘ss’. 
Display an additional column with sequential numbers for each row returned beginning at integer 1. 
(Schema(s) involved: Person)
*/
SELECT ROW_NUMBER() over (order by firstname ) as SequentialNumbers, FirstName, LastName from Person.Person where
FirstName like '%ss%';




/*
8: Sales people receive various commission rates that belong to 1 of 4 bands. (Schema(s) involved: Sales)
Display the [SalesPersonID] with an additional column entitled ‘Commission Band’ indicating the appropriate band as above.
*/
SELECT sp.BusinessEntityID as SalesPersonID,sp.CommissionPct,
CASE 
	WHEN sp.CommissionPct = 0 THEN 'Band 0'
	WHEN sp.CommissionPct <=0.01 THEN 'Band 1'
	WHEN sp.CommissionPct <=0.015 THEN 'Band 2'
	WHEN sp.CommissionPct > 0.015 THEN 'Band 3'
	END as 'Commission Band'
from Sales.SalesPerson as sp;




/*
9: Display the managerial hierarchy from Ruth Ellerbrock (person type – EM) up to CEO Ken Sanchez.
Hint: use[uspGetEmployeeManagers] (Schema(s) involved: [Person], [HumanResources])
*/
DECLARE @BusinessEntityID INT;
-- Here saved the BusinessEntityID of Ruth Ellerbrock in variable .
SELECT @BusinessEntityID = BusinessEntityID FROM Person.Person 
WHERE LastName = 'Ellerbrock' AND FirstName = 'Ruth' and PersonType='EM';

EXEC dbo.uspGetEmployeeManagers @BusinessEntityID;




/*
10: Display the ProductId of the product with the largest stock level. Hint: Use the Scalar-valued function [dbo]. 
[UfnGetStock].(Schema(s) involved: Production)
*/
SELECT TOP 1 ProductId
FROM Production.Product
ORDER BY dbo.ufnGetStock(ProductId) DESC;
