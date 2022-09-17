
--when you give query like this
--SELECT id,
--CASE WHEN month = "Jan" THEN revenue END as "Jan_Revenue",
--CASE WHEN month = "Feb" THEN revenue END AS "Feb_Revenue"
--FROM Department;
--the output will be
--+----+-------------+-------------+
--| id | Jan_Revenue | Feb_Revenue |
--+----+-------------+-------------+
--|  1 |        NULL |        7000 |
--|  1 |        8000 |        NULL |
--|  1 |        NULL |        NULL |
--|  2 |        9000 |        NULL |
--|  3 |        NULL |       10000 |
--+----+-------------+-------------+
--To get one row for each id we need to aggregate by id using GROUP BY.
--But since we have multiple rows with the same id but different values (e.g. for id=1 we have Jan_Revenues:
-- NULL, 8000 and NULL. When we merge these 3 together what value should be chosen?
--This is why we need either SUM (NULL+8000+NULL) or MAX, in both cases 8000 will be used

SELECT id,
MAX(CASE WHEN month='Jan' then revenue else null end) Jan_Revenue,
MAX(CASE WHEN month='Feb' then revenue else null end) Feb_Revenue,
MAX(CASE WHEN month='Mar' then revenue else null end) Mar_Revenue,
MAX(CASE WHEN month='Apr' then revenue else null end) Apr_Revenue,
MAX(CASE WHEN month='May' then revenue else null end) May_Revenue,
MAX(CASE WHEN month='Jun' then revenue else null end) Jun_Revenue,
MAX(CASE WHEN month='Jul' then revenue else null end) Jul_Revenue,
MAX(CASE WHEN month='Aug' then revenue else null end) Aug_Revenue,
MAX(CASE WHEN month='Sep' then revenue else null end) Sep_Revenue,
MAX(CASE WHEN month='Oct' then revenue else null end) Oct_Revenue,
MAX(CASE WHEN month='Nov' then revenue else null end) Nov_Revenue,
MAX(CASE WHEN month='Dec' then revenue else null end) Dec_Revenue
FROM Department GROUP BY id