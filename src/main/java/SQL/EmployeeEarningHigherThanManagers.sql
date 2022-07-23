

select tb1.Name as Employee from Employee tb1 inner join Employee tb2 on tb1.ManagerId=tb2.Id where tb1.Salary>tb2.Salary
