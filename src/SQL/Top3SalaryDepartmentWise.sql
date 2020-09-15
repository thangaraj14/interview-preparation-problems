--
--Now, for each row of the outer query:
--OuterDepartmentId, OuterEmployeeSalary is available to the inner query.
--The inner query will fetch all the salaries that are greater then OuterEmployeeSalary for department matching OuterDepartmentId
--and return a count of such distinct salaries
--
--This count can be 0,1 or 2
--
--if 0 -> that means there are no salaries greater then the OuterDepartmentSalary in that department. Hence, it is the greatest salary for that department. And outer query will include that OuterDepartmentId, OuterEmployeeSalary in the output.
--
--if 1 -> there is one salary bigger then OuterEmployeeSalary (it is the second largest salary)
--
--similarly for count 2, there are two larger salaries.

select d.Name Department, e1.Name Employee, e1.Salary
from Employee e1
join Department d
on e1.DepartmentId = d.Id
where 3>(select count(distinct(e2.Salary))
                  from Employee e2
                  where e2.Salary > e1.Salary
                  and e1.DepartmentId = e2.DepartmentId) -- the inner query will act as inner for loop which get e.salary from outside O(n^2)