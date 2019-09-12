-- 管理员
create table admin(
id int(10) primary key auto_increment,
num varchar(10),
uname varchar(10),
salt varchar(10),
pwd varchar(80),
rid int
);
insert into admin (id,num,uname,salt,pwd,rid) values(default,"001","001","001","1fccb567a44880e8665b7cb9d0f97271",1);

-- 学生
create table student(
id int(10) primary key auto_increment,
num varchar(10),
uname varchar(10),
salt varchar(10),
pwd varchar(80),
institute varchar(20),
rid int
);

-- 老师
create table teacher(
id int(10) primary key auto_increment,
num varchar(10),
uname varchar(10),
salt varchar(10),
pwd varchar(80),
institute varchar(20),
rid int
);

-- 角色表
create table roles(
id int(2) primary key auto_increment,
rname varchar(10),
pid int(3)
);
insert into roles values(default,"管理员",1);
insert into roles values(default,"老师",2);
insert into roles values(default,"学生",3);

-- 权限表
create table permission(
id int primary key auto_increment,
pname varchar(20)
);
insert into permission values(default,"user:*");
insert into permission values(default,"user:see");
insert into permission values(default,"user:edit");
insert into permission values(default,"user:delete");

-- 角色&权限表
create table roles_permission(
id int(2) primary key auto_increment,
roles_id int,
permission_id int
);
insert into roles_permission values(default,1,1);
insert into roles_permission values(default,2,2);

select p.pname from permission p join roles_permission rp on rp.permission_id = p.id and rp.roles_id = 2;