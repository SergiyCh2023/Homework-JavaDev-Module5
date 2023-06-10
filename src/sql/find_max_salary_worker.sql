SELECT "name", salary from workers
where (salary=(SELECT MAX(salary) FROM workers));