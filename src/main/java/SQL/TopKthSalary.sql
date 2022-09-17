SELECT IFNULL((SELECT DISTINCT Salary
               FROM Employee
               ORDER BY Salary DESC
                  LIMIT 1,N-1),NULL) AS NthHighestSalary

