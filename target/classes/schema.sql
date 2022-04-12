CREATE TABLE sec_user (
  userId            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled           BIT NOT NULL 
);

create table contact(
    id int primary key auto_increment,
    name varchar(255),
    phone varchar(10),
    address varchar(255),
    email varchar(75),
    role varchar(30)
);

CREATE TABLE sec_role(
  roleId   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
  id      INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId  INT NOT NULL,
  roleId  INT NOT NULL
);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
  REFERENCES sec_user (userId);
 
ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
  REFERENCES sec_role (roleId);
