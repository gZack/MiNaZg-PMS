create table Address
(
	id bigint auto_increment
		primary key,
	city varchar(255) null,
	country varchar(255) null,
	email varchar(255) null,
	street varchar(255) null,
	zipCode varchar(255) null,
	user_id bigint null
)
;

create index FK_33hg5keygw64femiy5lgd4y9t
	on Address (user_id)
;

create table Comment
(
	id bigint auto_increment
		primary key,
	componentId bigint not null,
	componentType varchar(255) not null,
	dateCommented tinyblob not null,
	statement varchar(255) not null,
	proposer_user_id bigint null
)
;

create index FK_7jcnjcp300y30wbifn7xl44t2
	on Comment (proposer_user_id)
;

create table Project
(
	id bigint auto_increment
		primary key,
	dateEnd tinyblob not null,
	dateStart tinyblob not null,
	description varchar(255) not null,
	name varchar(255) not null,
	status varchar(255) not null,
	client_user_id bigint null,
	pm_user_id bigint null
)
;

create index FK_fhtmc9kxdc6ynqxfxt42e04lk
	on Project (client_user_id)
;

create index FK_inhtfelekqpnw6xfpec0l0gr
	on Project (pm_user_id)
;

create table Report
(
	id int auto_increment
		primary key,
	hoursSpent double not null,
	progressPercentage double not null,
	timeLog tinyblob not null,
	comment_id bigint null,
	workOrder_id bigint not null,
	constraint FK_619hbq1axno55kwvmf4rvw4rr
		foreign key (comment_id) references Comment (id)
)
;

create index FK_5rhrnc50s9flenc673b5werqp
	on Report (workOrder_id)
;

create index FK_619hbq1axno55kwvmf4rvw4rr
	on Report (comment_id)
;

create table Sprint
(
	id bigint auto_increment
		primary key,
	description varchar(255) not null,
	endDate tinyblob not null,
	startDate tinyblob not null,
	status varchar(255) not null,
	title varchar(255) not null,
	release_id bigint not null
)
;

create table User
(
	id bigint auto_increment
		primary key,
	firstName varchar(255) not null,
	lastName varchar(255) not null,
	password varchar(255) not null,
	ssoId varchar(255) not null,
	constraint UK_r8h9hto41glsa6kc33d9nt8da
		unique (ssoId)
)
;

alter table Address
	add constraint FK_33hg5keygw64femiy5lgd4y9t
		foreign key (user_id) references User (id)
;

alter table Comment
	add constraint FK_7jcnjcp300y30wbifn7xl44t2
		foreign key (proposer_user_id) references User (id)
;

alter table Project
	add constraint FK_fhtmc9kxdc6ynqxfxt42e04lk
		foreign key (client_user_id) references User (id)
;

alter table Project
	add constraint FK_inhtfelekqpnw6xfpec0l0gr
		foreign key (pm_user_id) references User (id)
;

create table UserRole
(
	name varchar(255) not null
		primary key,
	description varchar(255) null
)
;

create table User_UserRole
(
	user_id bigint not null,
	userRole_name varchar(255) not null,
	primary key (user_id, userRole_name),
	constraint FK_qox7y9tcryivfpla995qbdp5p
		foreign key (user_id) references User (id),
	constraint FK_nkd7q7a1lt6170109r1p51cws
		foreign key (userRole_name) references UserRole (name)
)
;

create index FK_nkd7q7a1lt6170109r1p51cws
	on User_UserRole (userRole_name)
;

create table WorkOrder
(
	id bigint auto_increment
		primary key,
	closedDate tinyblob null,
	deadLine tinyblob not null,
	description varchar(255) not null,
	endDate tinyblob not null,
	resolvedDate tinyblob null,
	startDate tinyblob not null,
	status varchar(255) not null,
	title varchar(255) not null,
	totalDuration double null,
	totalProgress double null,
	dev_user_id bigint null,
	sprint_id bigint not null,
	constraint FK_j2eabhp2644xutn4hyr311u8t
		foreign key (dev_user_id) references User (id),
	constraint FK_6f41w2tddm6jl9x35v09gnkwb
		foreign key (sprint_id) references Sprint (id)
)
;

create index FK_6f41w2tddm6jl9x35v09gnkwb
	on WorkOrder (sprint_id)
;

create index FK_j2eabhp2644xutn4hyr311u8t
	on WorkOrder (dev_user_id)
;

alter table Report
	add constraint FK_5rhrnc50s9flenc673b5werqp
		foreign key (workOrder_id) references WorkOrder (id)
;

