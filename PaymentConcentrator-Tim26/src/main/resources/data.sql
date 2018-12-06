insert into Merchant(id, name, merchant_id, merchant_password, bank_swift, bank_url, success_url, failed_url, error_url) values (1, "Casopis 1", "1", "$argon2i$v=19$m=128000,t=15,p=4$otf9e3+/KhcRpzPm7vSX3w$386QXkLYGgO5Hv1KALvwyED9cqOTKbCpwPEsvm9Ob+Y", "DBDBRSBG", "https://localhost:8300", "", "", "");
insert into Merchant(id, name, merchant_id, merchant_password, bank_swift, bank_url, success_url, failed_url, error_url) values (2, "Casopis 2", "2", "$argon2i$v=19$m=128000,t=15,p=4$CTsJ4bF5TBPNgn7Rbhpw2g$8ZfzYi6XAeh742fxpQh/7atvmLSyJKWVLSnpcCPIzAo", "DBDBRSBG", "https://localhost:8300", "", "", "");
insert into Merchant(id, name, merchant_id, merchant_password, bank_swift, bank_url, success_url, failed_url, error_url) values (3, "Casopis 3", "3", "$argon2i$v=19$m=128000,t=15,p=4$jHi2qhnEDIgf+ZUlmZUKIg$b89/EbxE5jPH+V7XFnm9O1t4tfJPahnuEqpL3h1bZBY", "BACXRSBG", "https://localhost:8301", "", "", "");

insert into Merchant_Allowed_Payment(id, merchant_id, payment_type) values (1, 1, "CARD");
insert into Merchant_Allowed_Payment(id, merchant_id, payment_type) values (2, 1, "PAYPAL");
insert into Merchant_Allowed_Payment(id, merchant_id, payment_type) values (3, 2, "CARD");
insert into Merchant_Allowed_Payment(id, merchant_id, payment_type) values (4, 3, "PAYPAL");