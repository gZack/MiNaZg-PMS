-- username minazg
-- password abc125

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user1','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Miki','Afe');
/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user1' and role.name='PROJECT_MANAGER';

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user2','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Apposit','LLC');
/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user2' and role.name='CLIENT';

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user3' and role.name='PROJECT_MANAGER';
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user3','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Zack','Mersha');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user4' and role.name='CLIENT';
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user4','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'YYT','Software');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user5' and role.name='DEVELOPER';
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user5','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Nati','Getch');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='user6' and role.name='DEVELOPER';
INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('user6','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Beki','Haile');


