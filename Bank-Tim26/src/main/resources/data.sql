insert into Client(client_id, name, surname, jmbg, address, phone, email) values (1, "Tanja", "Provci", "1", "a1", "1", "email@email.com");
insert into Client(client_id, name, surname, jmbg, address, phone, email) values (2, "Aleksandar", "Arambasic", "2", "a2", "2", "email2@email.com");

insert into Merchant(mer_id, name, merchant_id, merchant_password, address, phone, email) values (1, "Casopis 1", "1", "argon2i$v=19$m=128000,t=15,p=4$otf9e3+/KhcRpzPm7vSX3w$386QXkLYGgO5Hv1KALvwyED9cqOTKbCpwPEsvm9Ob+Y", "a3", "3", "email3@email.com");
insert into Merchant(mer_id, name, merchant_id, merchant_password, address, phone, email) values (2, "Casopis 2", "2", "argon2i$v=19$m=128000,t=15,p=4$CTsJ4bF5TBPNgn7Rbhpw2g$8ZfzYi6XAeh742fxpQh/7atvmLSyJKWVLSnpcCPIzAo", "a4", "4", "email4@email.com");

insert into Account(account_id, number, active, client_id, mer_id, balance) values (1, "160-0000005588281-86", true, 1, null, 20000);
insert into Account(account_id, number, active, client_id, mer_id, balance) values (2, "160-5800101265455-32", true, 2, null, 10000);
insert into Account(account_id, number, active, client_id, mer_id, balance) values (3, "160-1896004672232-48", true, null, 1, 50000);
insert into Account(account_id, number, active, client_id, mer_id, balance) values (4, "160-3340791565173-12", true, null, 2, 60000);

insert into Card(id, pan, active, holder, security_code, validity_date, account_id, card_limit, card_type) values (1, "4062259300840237", true, "Tanja Provci", "$argon2i$v=19$m=128000,t=15,p=4$U+0X8q0U+e7rx4eL9eFISg$rfhFOUzjiaOg6COBKEWFOie3pBX0lUIC/sYc9FMqSnE", "2020-11-11 10:10:10", 1, 0, "DEBIT");
insert into Card(id, pan, active, holder, security_code, validity_date, account_id, card_limit, card_type) values (2, "4062255488711215", true, "Djordje Provci", "$argon2i$v=19$m=128000,t=15,p=4$eFAqg/tHsGa5x1v2ylD1GA$r48sP2oEay7gtXmO63kJyhC8L+dqVuHwmWPVm4+gz40", "2020-11-11 10:10:10", 1, 2000, "CREDIT");
insert into Card(id, pan, active, holder, security_code, validity_date, account_id, card_limit, card_type) values (3, "4841878716158815", true, "Aleksandar Arambasic", "$argon2i$v=19$m=128000,t=15,p=4$jXfFlk+bGZwGH13w2D05IQ$hqdtITc1PkAbff3XnUvPadAvZ0wqYLqVAtBtWLKHkhs", "2020-11-11 10:10:10", 2, 0, "DEBIT");

insert into IIN(id, bank_swift, bin) values (1, "DBDBRSBG", "406225");
insert into IIN(id, bank_swift, bin) values (2, "DBDBRSBG", "484187");
insert into IIN(id, bank_swift, bin) values (3, "BACXRSBG", "531933");
insert into IIN(id, bank_swift, bin) values (4, "BACXRSBG", "531934");