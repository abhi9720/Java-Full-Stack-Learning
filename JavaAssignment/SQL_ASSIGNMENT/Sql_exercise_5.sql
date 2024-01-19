/*
Write a Procedure supplying name information from the Person.Person table and accepting a filter for the first name. 
Alter the above Store Procedure to supply Default Values if user does not enter any value.( Use AdventureWorks)
*/
--drop PROCEDURE dbo.usp_GetPersonByName;
CREATE PROCEDURE dbo.usp_GetPersonByName
    @FirstName NVARCHAR(50) = NULL
AS
BEGIN
    
    IF @FirstName IS NULL
        SET @FirstName = '%'

    SELECT FirstName, MiddleName, LastName
    FROM Person.Person
    WHERE FirstName LIKE @FirstName
END
GO

EXEC dbo.usp_GetPersonByName @FirstName = 'John' /* return row where firstname match : @FirstName  */
EXEC dbo.usp_GetPersonByName -- return all rows 

