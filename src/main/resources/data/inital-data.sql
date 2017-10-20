/* Populate UserRole Table */
/*INSERT INTO UserRole(name, description)
VALUES ('ADMIN',"An admin who can create all");

INSERT INTO UserRole(name, description)
VALUES ('PROJECT_MANGER',"projec manager");

INSERT INTO UserRole(name, description)
VALUES ('DEVELOPER',"developer");

INSERT INTO UserRole(name, description)
VALUES ('CLIENT',"client");*/

INSERT INTO UserRole(name, description)
SELECT * FROM (SELECT 'ADMIN', 'An admin who can create all') AS tmp
WHERE NOT exists(
  SELECT name FROM UserRole WHERE name = 'ADMIN'
)LIMIT 1;

INSERT INTO UserRole(name, description)
SELECT * FROM (SELECT 'PROJECT_MANAGER','project manager') AS tmp
WHERE NOT exists(
  SELECT name FROM UserRole WHERE name = 'PROJECT_MANAGER'
)LIMIT 1;

INSERT INTO UserRole(name, description)
SELECT * FROM (SELECT 'DEVELOPER','developer user') AS tmp
WHERE NOT exists(
  SELECT name FROM UserRole WHERE name = 'DEVELOPER'
)LIMIT 1;


INSERT INTO UserRole(name, description)
SELECT * FROM (SELECT 'CLIENT','client user') as temp
WHERE NOT exists(
  SELECT name FROM UserRole WHERE name = 'CLIENT'
) LIMIT 1;

/* Populate one Admin User which will further create other users for the application using GUI */
-- username minazg
-- password abc125
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('minazg','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'minazg','minazg');

/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='minazg' and role.name='ADMIN';