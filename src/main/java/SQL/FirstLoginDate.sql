
--Write an SQL query that reports the first login date for each player.
--
--The query result format is in the following example:
--
--Activity table:
--+-----------+-----------+------------+--------------+
--| player_id | device_id | event_date | games_played |
--+-----------+-----------+------------+--------------+
--| 1         | 2         | 2016-03-01 | 5            |
--| 1         | 2         | 2016-05-02 | 6            |
--| 2         | 3         | 2017-06-25 | 1            |
--| 3         | 1         | 2016-03-02 | 0            |
--| 3         | 4         | 2018-07-03 | 5            |
--+-----------+-----------+------------+--------------+
--
--Result table:
--+-----------+-------------+
--| player_id | first_login |
--+-----------+-------------+
--| 1         | 2016-03-01  |
--| 2         | 2017-06-25  |
--| 3         | 2016-03-02  |
--+-----------+-------------+


select player_id, min(event_date) as first_login from Activity group by player_id


--the following query is to try and get first loggedin deviceId

SELECT player_id, device_id FROM Activity
WHERE (player_id, event_date) IN
(SELECT player_id, MIN(event_date) first_date FROM Activity GROUP BY player_id)