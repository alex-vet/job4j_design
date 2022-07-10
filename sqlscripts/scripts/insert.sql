INSERT INTO role(name)
	VALUES ('Administrator');
INSERT INTO role(name)
	VALUES ('User');

INSERT INTO users(name, role_id)
	VALUES ('admin', 1);
INSERT INTO users(name, role_id)
	VALUES ('user', 2);

INSERT INTO rules(name)
	VALUES ('admin access');
INSERT INTO rules(name)
	VALUES ('user access');

INSERT INTO role_rules(role_id, rules_id)
	VALUES (1, 1);
INSERT INTO role_rules(role_id, rules_id)
	VALUES (2, 2);

INSERT INTO category(name)
	VALUES ('important');
INSERT INTO category(name)
	VALUES ('normal');

INSERT INTO state(name)
	VALUES ('open');
INSERT INTO state(name)
	VALUES ('closed');

INSERT INTO item(name, users_id, category_id, state_id)
	VALUES ('Ремонт принтера',2,2,1);
INSERT INTO item(name, users_id, category_id, state_id)
	VALUES ('Ремонт монитора',2,1,2);

INSERT INTO comments(name)
	VALUES ('Не работает');
INSERT INTO comments(name)
	VALUES ('Погас и не включается');

INSERT INTO attachs(name)
	VALUES ('1.jpg');
INSERT INTO attachs(name)
	VALUES ('dsfgsd.jpg');