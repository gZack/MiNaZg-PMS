-- username minazg
-- password abc125

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('miki','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Miki','Afe');
/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='miki' and role.name='PROJECT_MANAGER';

-- ----

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('apposit','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Apposit','LLC');
/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='apposit' and role.name='CLIENT';

-- -----


INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('zack','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Zack','Mersha');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='zack' and role.name='PROJECT_MANAGER';

-- ------

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('yyt','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'YYT','Software');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='yyt' and role.name='CLIENT';

-- ------

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('nati','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Nati','Getch');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='nati' and role.name='DEVELOPER';

-- ----

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('beki','$2a$10$pHJRgyB/nIqivZ34DvrnNOk6UGHSI08hI0MddkMJaS218gUwML08a', 'Beki','Haile');

INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='beki' and role.name='DEVELOPER';

