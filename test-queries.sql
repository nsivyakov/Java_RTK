1. Список всех заказов за последние 7 дней с именем покупателя и описанием товара
SELECT o.id AS order_id, c.first_name, c.last_name, p.description, o.order_date, o.quantity, os.status_name
FROM "order" o
JOIN customer c ON o.customer_id = c.id
JOIN product p ON o.product_id = p.id
JOIN order_status os ON o.status_id = os.id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '7 days'
ORDER BY o.order_date DESC;

2. Топ-3 самых популярных товара (по количеству заказов)

SELECT p.id, p.description, COUNT(o.id) AS total_orders
FROM product p
JOIN "order" o ON o.product_id = p.id
GROUP BY p.id, p.description
ORDER BY total_orders DESC
LIMIT 3;

3. Общая сумма выручки по всем заказам

SELECT SUM(p.price * o.quantity) AS total_revenue
FROM "order" o
JOIN product p ON o.product_id = p.id;

4. Количество заказов по статусам
 
SELECT os.status_name, COUNT(o.id) AS order_count
FROM order_status os
LEFT JOIN "order" o ON o.status_id = os.id
GROUP BY os.id, os.status_name;

5. Список клиентов, сделавших более 5 заказов

SELECT c.id, c.first_name, c.last_name, COUNT(o.id) AS order_count
FROM customer c
JOIN "order" o ON o.customer_id = c.id
GROUP BY c.id, c.first_name, c.last_name
HAVING COUNT(o.id) > 5;

3 запроса на изменение

1. Обновить статус заказа на “Исполнен”

UPDATE "order"
SET status_id = 3
WHERE id = 6;

2. Уменьшить количество товара при покупке

 
UPDATE product
SET quantity = quantity - 1
WHERE id = 2;

Функция, которая принимает заказ и проверяет доступное количество товара. Если всё ок - меняет статус на Исполнено и уменьшает количество товара, если нет, то статус Отказ. Возвращает номер заказа (order.id):

declare 
	cur_product int;
	new_order_id int;
begin
	--пишем первый статус Принят
	insert into public.order(product_id,customer_id,order_date,quantity,status_id) values(c_product,c_customer,now(),c_quantity,1);
	  -- получаем id только что вставленной записи
    new_order_id := currval('order_id_seq');	
	update public.order set status_id=2
	where "id"=currval('order_id_seq');
	--если товара достаточно, то обрабатываем с уменьшением в product  и установкой статуса Исполнено
	select quantity into cur_product from public.product where "id"=c_product for update;
	if cur_product>=c_quantity then
		update public.order set status_id=3
		where "id"=currval('order_id_seq');

		update public.product set quantity=quantity-c_quantity
		where "id"=c_product;
	--если товара недостаточно, то устанавливаем статус Отказ
	else
		update public.order set status_id=4
		where "id"=currval('order_id_seq');
	end if;
	 -- возвращаем id созданного заказа
    RETURN new_order_id;
end;

                
3. Обновить цену товара
 
UPDATE product
SET price = 500
WHERE id = 3;

                
2 запроса на удаление

1. Удалить клиентов без заказов

DELETE FROM customer c
WHERE NOT EXISTS (
    SELECT 1 FROM "order" o WHERE o.customer_id = c.id);

2. Удалить заказы по определенному статусу (например, отмененные - статус “Отменен” с id=4)
 
DELETE FROM "order"
WHERE status_id = 4;
