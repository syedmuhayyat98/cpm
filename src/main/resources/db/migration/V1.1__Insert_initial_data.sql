INSERT INTO public.customers
(id, email_office, email_personal, first_name, last_name)
VALUES
(1, 'adminOffice@localhost.com', 'adminPersonal@localhost.com', 'Admin', 'One'),
(2, 'userOffice@localhost.com', 'userPersonal@localhost.com', 'User', 'Two');

INSERT INTO public.family_members
(id, first_name, last_name, customer_id)
VALUES
(1, 'Mrs', 'Admin', 1),
(2, 'Mom', 'Admin', 1);

INSERT INTO public.products
(id, item_type, "name", price, quantity, owner_id)
VALUES
(1, 'Canned Food', 'Baked Beans', 8, 10, 1),
(2, 'Instant Food', 'Ramen', 5, 2, 2);


