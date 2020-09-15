--Mary is a teacher in a middle school and she has a table seat storing students' names and their corresponding seat ids.
--
--The column id is continuous increment.
--
--+---------+---------+
--|    id   | student |
--+---------+---------+
--|    1    | Abbot   |
--|    2    | Doris   |
--|    3    | Emerson |
--|    4    | Green   |
--|    5    | Jeames  |
--+---------+---------+

--For students with odd id, the new id is (id+1) after switch unless it is the last seat. And for students with even id, the new id is (id-1).
--In order to know how many seats in total, we can use a subquery:
--gotcha is when we do id+1 we shouldn't exceed the row number so when id==count we leave as is

select (case
            when MOD(id,2)!=0 && id!=counts then id+1
            when MOD(id,2)!=0 && id=counts then id
            else id-1
            end) as id, student
            from
                seat,
            (select count(*) as counts from seat) as seat_counts
            order by  id asc