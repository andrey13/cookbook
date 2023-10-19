INSERT INTO d_t (id_tag, id_dish) SELECT tag.id AS id_tag1, dish.id AS id_dish1 FROM tag, dish WHERE (tag.selected=1 AND dish.selected = 1 AND (0 = (SELECT COUNT(*) FROM d_t WHERE d_t.id_tag = id_tag1 AND d_t.id_dish = id_dish1)))


INSERT INTO d_t (id_tag, id_dish) 
SELECT tag.id AS id_tag1, dish.id AS id_dish1 
FROM tag, dish 
WHERE (
    tag.selected=1 AND 
    dish.selected = 1 AND 
    (0 = (
        SELECT COUNT(*) FROM d_t WHERE d_t.id_tag = id_tag1 AND d_t.id_dish = id_dish1)
    )
 )