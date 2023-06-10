select c."name", count(*) as projects_count from clients c
inner join projects p on c.id = p.client_id
group by c.id
having count(*) = (select count(*) from projects
group by client_id
order by count(*) desc limit 1);