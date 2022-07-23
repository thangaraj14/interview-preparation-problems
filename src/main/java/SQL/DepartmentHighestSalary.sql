
--+----+-------+--------+--------------+
--| Id | Name  | Salary | DepartmentId |
--+----+-------+--------+--------------+
--| 1  | Joe   | 70000  | 1            |
--| 2  | Jim   | 90000  | 1            |
--| 3  | Henry | 80000  | 2            |
--| 4  | Sam   | 60000  | 2            |
--| 5  | Max   | 90000  | 1            |
--+----+-------+--------+--------------+

--+----+----------+
--| Id | Name     |
--+----+----------+
--| 1  | IT       |
--| 2  | Sales    |
--+----+----------+

--OUTPUT
--+------------+----------+--------+
--| Department | Employee | Salary |
--+------------+----------+--------+
--| IT         | Max      | 90000  |
--| IT         | Jim      | 90000  |
--| Sales      | Henry    | 80000  |
--+------------+----------+--------+

--this inner query will give single top salaries for each dept, however if there are multiple people who gets the same salary? we do one more join
-- SELECT d.Id
--        , MAX(d.Name) AS Name
--        , MAX(e.Salary) AS Salary
--    FROM Department d
--    JOIN Employee e
--    ON e.DepartmentId = d.Id
--    GROUP BY d.Id

SELECT d1.Name as Department
    , e1.Name as Employee
    , e1.Salary
FROM (
    SELECT d.Id
        , MAX(d.Name) AS Name
        , MAX(e.Salary) AS Salary
    FROM Department d
    JOIN Employee e
    ON e.DepartmentId = d.Id
    GROUP BY d.Id
) d1
JOIN Employee e1
ON d1.Id = e1.DepartmentId AND e1.Salary = d1.Salary