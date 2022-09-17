--to get to 3 salaries from a table
-- select * from Employee where salary >= {value} this value we need to calculate dynamically (some min value in top 3 salaries)
-- so select min(Salary) from (select distinct(salary) from Employee order by Salary DESC limit 3) this will fetch min of top 3 salaries
-- add this to original query
--+----+-------------+-------------+
--| id | Name        | Salary      |
--+----+-------------+-------------+
--|  1 |        A    |        7000 |
--|  2 |        B    |        6000 |
--|  3 |        C    |        6000 |
--|  4 |        D    |        6000 |
--|  5 |        E    |        4000 |
--|  6 |        F    |        3000 |
--|  7 |        G    |        3000 |
--|  8 |        H    |        2000 |
--|  9 |        I    |        1000 |
--+----+-------------+-------------+

select * from Employee where salary >= (select min(Salary) from (select distinct(salary) from Employee order by Salary DESC limit 3))