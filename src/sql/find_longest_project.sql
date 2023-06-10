SELECT pw.project_id, (SUM(w.salary) * DATEDIFF('MONTH', p.start_date, finish_date)) as price
FROM project_worker pw
JOIN workers w ON pw.worker_id = w.id
JOIN projects p ON pw.project_id = p.id
GROUP BY pw.project_id, p.finish_date, p.start_date
ORDER BY price DESC;