insert into Client(client_id, name, surname, jmbg, address, phone, email) values (1, "Nina", "Simic", "3", "a3", "3", "email3@email.com");
insert into Client(client_id, name, surname, jmbg, address, phone, email) values (2, "Strahinja", "Stanivuk", "4", "a4", "4", "email4@email.com");

insert into Merchant(mer_id, name, merchant_id, merchant_password, address, phone, email) values (1,"Casopis 3", "3", "$argon2i$v=19$m=128000,t=15,p=4$4x1wT2RxHep8aWYmk7xLrA$klkTHXjTsqqifWcsDZpemb0sHF/yyG39XH1h7ygkVLY", "a4", "4", "email5@email.com");

insert into Account(account_id, number, active, client_id, mer_id, balance) values (1, "170-3216598446421-86", true, 1, null, 40000);
insert into Account(account_id, number, active, client_id, mer_id, balance) values (2, "170-0000654891266-32", true, 2, null, 40000);
insert into Account(account_id, number, active, client_id, mer_id, balance) values (3, "170-6548246166001-48", true, null, 1, 70000);

insert into Card(id, pan, active, holder, security_code, validity_date, account_id, card_limit, card_type) values (1, "5319330154542225", true, "Nina Simic", "$argon2i$v=19$m=128000,t=15,p=4$wEUq4fysCdLXYaD1WS7Ypw$d+G20G8vNmoAmYMiSTlO7D1bPMLemLHYcejjLx3nPPI", "2020-11-11 10:10:10", 1, 0, "DEBIT");
insert into Card(id, pan, active, holder, security_code, validity_date, account_id, card_limit, card_type) values (2, "5319341154542225", true, "Strahinja Stanivuk", "$argon2i$v=19$m=128000,t=15,p=4$vE37pAJB8ELuKempZ3Tm7g$v631ZpaA5IwXUh6Gh1wF+O48lpn2KzPlq6xbpiXTOAI", "2020-11-11 10:10:10", 2, 2000, "CREDIT");

insert into IIN(id, bank_swift, bin) values (1, "DBDBRSBG", "406225");
insert into IIN(id, bank_swift, bin) values (2, "DBDBRSBG", "484187");
insert into IIN(id, bank_swift, bin) values (3, "BACXRSBG", "531933");
insert into IIN(id, bank_swift, bin) values (4, "BACXRSBG", "531934");