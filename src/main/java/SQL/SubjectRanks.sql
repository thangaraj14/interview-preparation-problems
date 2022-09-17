
--+----+-------+
--| Id | Score |
--+----+-------+
--| 1  | 3.50  |
--| 2  | 3.65  |
--| 3  | 4.00  |
--| 4  | 3.85  |
--| 5  | 4.00  |
--| 6  | 3.65  |
--+----+-------+

--+-------+---------+
--| score | Rank    |
--+-------+---------+
--| 4.00  | 1       |
--| 4.00  | 1       |
--| 3.85  | 2       |
--| 3.65  | 3       |
--| 3.65  | 3       |
--| 3.50  | 4       |
--+-------+---------+


SELECT
  Score,
  (SELECT count(distinct Score) FROM Scores WHERE Score >= s.Score) "Rank" -- inner for-loop
FROM Scores s
ORDER BY Score desc


SELECT
  Score,
  (SELECT count(distinct Score) FROM Scores WHERE Score >= s.Score) "Rank"
FROM Scores s
ORDER BY Score desc