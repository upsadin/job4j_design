insert into roles(name) values('admin');
insert into roles(name) values('user');

insert into users(name, role_id) values('Pavel', 4);
insert into users(name, role_id) values('Petr', 5);

insert into rules(name) values('all');
insert into rules(name) values('user');

insert into roles_rules(role_id, rule_id) values(4, 1);
insert into roles_rules(role_id, rule_id) values(5, 2);

insert into categories(name) values('standard');
insert into categories(name) values('important');

insert into states(name) values('process');
insert into states(name) values('ready');

insert into items(content, user_id, categorie_id, state_id) 
values('does not work', 1, 2, 1);
insert into items(content, user_id, categorie_id, state_id) 
values('change info', 2, 1, 2);

insert into comments(content, item_id) values('ok', 1);
insert into attachs(name, item_id) values('screen', 1);