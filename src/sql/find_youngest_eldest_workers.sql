select 'YOUNGEST' as "type", w."name", w.birthday
from workers w
where birthday  = (select MAX(birthday ) from workers w2)
UNION all
select 'OLDEST' as "type", w."name", w.birthday
from workers w
where birthday  = (select MIN(birthday ) from workers w3);