--N is passed as parameter

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE M INT; #note variable declaration
SET M=N-1;
  RETURN (
      select distinct(salary) from Employee order by Salary desc limit M,1
  );
END