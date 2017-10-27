-- --------------------------------------
-- --------------------------------------
-- -------- Create database and user-----
-- --------------------------------------
-- --------------------------------------
CREATE SCHEMA minazg_db;

CREATE USER 'minazg'@'localhost' IDENTIFIED BY 'minazg';
GRANT ALL PRIVILEGES ON *.* TO 'minazg'@'localhost';
FLUSH PRIVILEGES;




-- --------------------------------------
-- --------------------------------------
-- -------- Create Address -------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.Address
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  city varchar(255),
  country varchar(255),
  email varchar(255),
  street varchar(255),
  zipCode varchar(255)
);

-- --------------------------------------
-- --------------------------------------
-- -------- Create User -----------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.User
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  firstName varchar(255) NOT NULL,
  lastName varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  ssoId varchar(255) NOT NULL,
  address_id bigint(20),
  CONSTRAINT FK_25yqck53dyy0k1q261ncjxmw3 FOREIGN KEY (address_id) REFERENCES Address (id)
);

-- --------------------------------------
-- --------------------------------------
-- -------- Create UserRole -------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.UserRole
(
  name varchar(255) PRIMARY KEY NOT NULL,
  description varchar(255)
);
INSERT INTO minazg_db.UserRole (name, description) VALUES ('ADMIN', 'An admin who can create all');
INSERT INTO minazg_db.UserRole (name, description) VALUES ('CLIENT', 'client user');
INSERT INTO minazg_db.UserRole (name, description) VALUES ('DEVELOPER', 'developer user');
INSERT INTO minazg_db.UserRole (name, description) VALUES ('PROJECT_MANAGER', 'project manager');

-- --------------------------------------
-- --------------------------------------
-- -------- Create User_UserRole --------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.User_UserRole
(
  user_id bigint(20) NOT NULL,
  userRole_name varchar(255) NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, userRole_name),
  CONSTRAINT FK_qox7y9tcryivfpla995qbdp5p FOREIGN KEY (user_id) REFERENCES User (id),
  CONSTRAINT FK_nkd7q7a1lt6170109r1p51cws FOREIGN KEY (userRole_name) REFERENCES UserRole (name)
);


-- username admin
-- password password

INSERT INTO User(ssoId, password, firstName, lastName)
VALUES ('admin','$2a$10$PIUihg.4YYgdn5Y8s1rGP.kCZnI.xTlmbEaI43bfjgnvLlY7VUpgK', 'Admin','Admin');
/* Populate JOIN Table */
INSERT INTO User_UserRole (user_id, userRole_name)
  SELECT user.id, role.name FROM User user, UserRole role
  where user.ssoId='admin' and role.name='ADMIN';


-- --------------------------------------
-- --------------------------------------
-- -------- Create Project --------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.Project
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  dateEnd datetime NOT NULL,
  dateStart datetime NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  status varchar(255) NOT NULL,
  client_user_id bigint(20),
  pm_user_id bigint(20),
  CONSTRAINT FK_fhtmc9kxdc6ynqxfxt42e04lk FOREIGN KEY (client_user_id) REFERENCES User (id),
  CONSTRAINT FK_inhtfelekqpnw6xfpec0l0gr FOREIGN KEY (pm_user_id) REFERENCES User (id)
);

-- --------------------------------------
-- --------------------------------------
-- -------- Create Milestone ------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.Milestone
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  releaseDate datetime NOT NULL,
  remark varchar(255) NOT NULL,
  status varchar(255),
  versionNumber varchar(255) NOT NULL,
  project_id bigint(20) NOT NULL,
  CONSTRAINT FK_njlote8mf3hun5mhbvgurxgka FOREIGN KEY (project_id) REFERENCES Project (id)
);


-- --------------------------------------
-- --------------------------------------
-- -------- Create Sprint ---------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.Sprint
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  description varchar(255) NOT NULL,
  endDate datetime NOT NULL,
  startDate datetime NOT NULL,
  status varchar(255) NOT NULL,
  title varchar(255) NOT NULL,
  release_id bigint(20) NOT NULL,
  CONSTRAINT FK_aspl6rrrk8gylc0yxwuhd25ek FOREIGN KEY (release_id) REFERENCES Milestone (id)
);

-- --------------------------------------
-- --------------------------------------
-- -------- Create WorkOrder ------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.WorkOrder
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  closedDate datetime,
  deadLine datetime NOT NULL,
  description varchar(255) NOT NULL,
  resolvedDate datetime,
  startDate datetime NOT NULL,
  status varchar(255) NOT NULL,
  title varchar(255) NOT NULL,
  totalDuration double,
  totalProgress double,
  type varchar(255) NOT NULL,
  dev_user_id bigint(20),
  sprint_id bigint(20),
  CONSTRAINT FK_j2eabhp2644xutn4hyr311u8t FOREIGN KEY (dev_user_id) REFERENCES User (id),
  CONSTRAINT FK_6f41w2tddm6jl9x35v09gnkwb FOREIGN KEY (sprint_id) REFERENCES Sprint (id)
);


-- --------------------------------------
-- --------------------------------------
-- -------- Create Comment --------------
-- --------------------------------------
-- --------------------------------------
CREATE TABLE minazg_db.Comment
(
  id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  componentId bigint(20) NOT NULL,
  componentType varchar(255) NOT NULL,
  dateCommented datetime NOT NULL,
  statement varchar(255) NOT NULL,
  proposer_user_id bigint(20),
  CONSTRAINT FK_7jcnjcp300y30wbifn7xl44t2 FOREIGN KEY (proposer_user_id) REFERENCES User (id)
);

-- --------------------------------------
-- --------------------------------------
-- -------- Create Report ---------------
-- --------------------------------------
-- --------------------------------------

CREATE TABLE minazg_db.Report
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hoursSpent double NOT NULL,
  progressPercentage double NOT NULL,
  timeLog datetime NOT NULL,
  comment_id bigint(20),
  workOrder_id bigint(20) NOT NULL,
  CONSTRAINT FK_619hbq1axno55kwvmf4rvw4rr FOREIGN KEY (comment_id) REFERENCES Comment (id),
  CONSTRAINT FK_5rhrnc50s9flenc673b5werqp FOREIGN KEY (workOrder_id) REFERENCES WorkOrder (id)
);