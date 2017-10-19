/* Populate UserRole Table */
INSERT INTO UserRole(name, description)
VALUES ('ADMIN',"An admin who can create all");


/* Populate one Admin User which will further create other users for the application using GUI */
-- username minazg
-- password abc125
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('minazg','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'minazg','minazg');


/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='minazg' and role.name='ADMIN';