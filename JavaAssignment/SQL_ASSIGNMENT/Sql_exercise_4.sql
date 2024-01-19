/*
Create a function that takes as inputs a SalesOrderID, a Currency Code, and a date, and 
returns a table of all the SalesOrderDetail rows for that Sales Order including Quantity, ProductID, 
UnitPrice, and the unit price converted to the target currency based on the end of
day rate for the date provided. Exchange rates can be found in the Sales.CurrencyRate table. ( Use AdventureWorks)
*/
-- Here used End of day rate to convert uniPrice


--drop function GetSalesOrderDetails;
--Approach1 : USING TABLE JOIN 
CREATE FUNCTION GetSalesOrderDetails(
    @SalesOrderID INT,
    @CurrencyCode NVARCHAR(3),
    @Date DATE
)
RETURNS TABLE
AS
RETURN
(
    SELECT sod.OrderQty, sod.ProductID, sod.UnitPrice,
        sod.UnitPrice * ISNULL(cr.EndOfDayRate, 1) AS ConvertedUnitPrice
    FROM Sales.SalesOrderDetail sod
    INNER JOIN Sales.SalesOrderHeader soh ON sod.SalesOrderID = soh.SalesOrderID
    LEFT JOIN Sales.CurrencyRate cr
        ON cr.FromCurrencyCode = 'USD'
        AND cr.ToCurrencyCode = @CurrencyCode
        AND cr.CurrencyRateDate = @Date
    WHERE sod.SalesOrderID = @SalesOrderID
);



-- APPROACH 2: SAVING RATE in variable
CREATE FUNCTION getSalesOrderDetail (
  @SalesOrderID INT,
  @CurrencyCode NVARCHAR(3),
  @Date DATE
)
RETURNS @SalesOrderDetails TABLE (
  Quantity INT,
  ProductID INT,
  UnitPrice MONEY,
  ConvertedUnitPrice MONEY
)
AS
BEGIN
  Declare @rate MONEY;
  SELECT @rate=EndOfDayRate from Sales.CurrencyRate scr WHERE ToCurrencyCode=@CurrencyCode and CurrencyRateDate=  @Date
  INSERT INTO @SalesOrderDetails (Quantity, ProductID, UnitPrice, ConvertedUnitPrice)
  SELECT OrderQty, ProductID,UnitPrice, UnitPrice*@rate as ConvertedUnitPrice FROM sales.SalesOrderDetail sod    WHERE sod.SalesOrderID =  @SalesOrderID
  RETURN;
END;
GO

SELECT * FROM GetSalesOrderDetails(43668, 'MXN', '2005-07-01');
SELECT * FROM getSalesOrderDetail(43668, 'MXN', '2005-07-01');
