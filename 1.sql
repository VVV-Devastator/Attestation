CREATE FUNCTION FormatSeconds
(
    @seconds INT
)
RETURNS NVARCHAR(100)
AS
BEGIN
    DECLARE @formatted_time NVARCHAR(100)
    DECLARE @days INT, @hours INT, @minutes INT

    SET @days = @seconds / (24 * 3600)
    SET @seconds = @seconds % (24 * 3600)

    SET @hours = @seconds / 3600
    SET @seconds = @seconds % 3600

    SET @minutes = @seconds / 60
    SET @seconds = @seconds % 60

    SET @formatted_time = CONCAT(
        CAST(@days AS NVARCHAR(10)), ' days ',
        CAST(@hours AS NVARCHAR(10)), ' hours ',
        CAST(@minutes AS NVARCHAR(10)), ' minutes ',
        CAST(@seconds AS NVARCHAR(10)), ' seconds'
    )

    RETURN @formatted_time
END