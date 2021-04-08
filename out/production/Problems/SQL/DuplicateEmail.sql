--Write a SQL query to find all duplicate emails in a table named Person.
--+----+---------+
--| Id | Email   |
--+----+---------+
--| 1  | a@b.com |
--| 2  | c@d.com |
--| 3  | a@b.com |
--+----+---------+

select distinct(t1.Email) as Email
from Person t1
where 1<(
        select count(t2.Email)
        from Person t2 where t1.Email=t2.Email)

 -- Inner Join concept
select distinct p1.Email
from Person p1
inner join Person p2 on p1.Email=P2.Email
where p1.Id <> p2.Id