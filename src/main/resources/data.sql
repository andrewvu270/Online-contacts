INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('ahmad.soleimani@sheridancollege.ca', '$2a$10$c8tvAjKGERDkvYOiQSHx.u5zKjwpM6jxH51K0Ph2U1mzGheJ0ucWe', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('guest.guest@sheridancollege.ca', '$2a$10$c8tvAjKGERDkvYOiQSHx.u5zKjwpM6jxH51K0Ph2U1mzGheJ0ucWe', 1);

insert into contact(name, phone, address, email, role)
values ('Andrew', '0123123', 'somewhere1', 'a@b.c', 'ROLE_ADMIN'),
       ('Matthew', '03756353', 'somewhere2', 'a@b.cd', 'ROLE_ADMIN'),
       ('Jennie', '988560', 'somewhere3', 'a@b.cde', 'ROLE_GUEST'),
       ('Someone', '38670', 'somewhere4', 'a@b.cdef', 'ROLE_ADMIN'),
       ('Romeo', '35656335', 'somewhere5', 'a@b.cdefg', 'ROLE_ADMIN'),
       ('Juliet', '35563', 'somewhere6', 'a@a.a', 'ROLE_GUEST'),
       ('My Name', '0123123', 'somewhere7', 'a@b.b', 'ROLE_MEMBER'),
       ('Man', '0356323', 'somewhere8', 'a@b.bb', 'ROLE_MEMBER'),
       ('Spiderman', '2536523', 'somewhere9', 'a@b.bbbbbb', 'ROLE_GUEST'),
       ('The Rock', '35623', 'somewhere10', 'a@c.c', 'ROLE_GUEST'),
       ('MrCat', '23123', 'somewhere11', 'abc@b.cdefg', 'ROLE_MEMBER'),
       ('Hoang', '0353123', 'somewhere12', 'abc@b.b', 'ROLE_MEMBER');



INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_MEMBER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (1, 2);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 3);

