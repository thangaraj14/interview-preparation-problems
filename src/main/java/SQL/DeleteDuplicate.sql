

-- the below command will do cross product and generate all combinations of rows
-- we are selecting a row with same Email and Greater Id
DELETE p1 from Person p1, Person p2 where p1.Email=p2.Email and p1.Id>p2.Id