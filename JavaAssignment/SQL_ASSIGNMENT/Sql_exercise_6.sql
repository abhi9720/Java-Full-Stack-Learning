/*
Write a trigger for the Product table to ensure the list price can never be raised more than 15 Percent in a single change. Modify the
above trigger to execute its check code only if the ListPrice column is updated (Use AdventureWorks Database).
*/
CREATE TRIGGER tr_ProductListPrice
ON Production.Product
AFTER UPDATE
AS
BEGIN
	-- Trigger that executes its check code only if the ListPrice column is updated:
    IF NOT UPDATE(ListPrice)
        RETURN;

    DECLARE @MaxPercentIncrease DECIMAL(5,2) = 0.15;
    DECLARE @OldListPrice MONEY, @NewListPrice MONEY, @ProductId INT;
    
    SELECT @OldListPrice = d.ListPrice , @NewListPrice = i.ListPrice, @ProductId = i.ProductID
    FROM inserted i
    INNER JOIN deleted d ON i.ProductID = d.ProductID;
	
    IF @NewListPrice > @OldListPrice * (1 + @MaxPercentIncrease)
    BEGIN
		PRINT 'New list price: ' + CONVERT(VARCHAR, @NewListPrice);
		PRINT 'Old list price: ' + CONVERT(VARCHAR, @OldListPrice);
		PRINT 'Max percent increase: ' + CONVERT(VARCHAR, @MaxPercentIncrease*100);
		PRINT 'Allowed increase: ' + CONVERT(VARCHAR, @OldListPrice * (1 + @MaxPercentIncrease));
        RAISERROR('List price can not be raised more than 15 percent in a single change.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END

-- Initial Price 
select ProductID, ListPrice from Production.Product WHERE ProductID = 710 ;


-- increases the list price within the limit
UPDATE Production.Product
SET ListPrice = ListPrice * 1.1
WHERE ProductID = 710 ;
select ProductID, ListPrice from Production.Product WHERE ProductID = 710 ;

-- increases the list price beyond the limit
UPDATE Production.Product
SET ListPrice = ListPrice * 2
WHERE ProductID = 710;
select ProductID, ListPrice from Production.Product WHERE ProductID = 710 ;
