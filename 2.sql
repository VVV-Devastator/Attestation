CREATE FUNCTION GetEvenNumbers()
RETURNS TABLE
AS
RETURN
(
  SELECT number
  FROM (VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10)) AS numbers(number)
  WHERE number % 2 = 0
);