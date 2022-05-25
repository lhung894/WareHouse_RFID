use master
go

create database PRODUCT
go

use PRODUCT
go

create table Product (
	product_id nvarchar(30) primary key,
	product_name nvarchar(100),
	product_quantity int,
	product_detail nvarchar(255)
);

create table Tag (
	tag_id nvarchar(30) primary key,
	product_id nvarchar(30) not null,
	tag_gate_in nvarchar(30),
	tag_date_in datetime,
	tag_gate_out nvarchar(30),
	tag_date_out datetime,
	order_id nvarchar(30)
);

create table Order_Product (
	order_id nvarchar(30) primary key,
	order_date datetime,
	status int
);

create table Order_Detail (
	order_detail_id nvarchar(30) primary key,
	order_id nvarchar(30) not null,
	product_id nvarchar(30) not null,
	order_quantity int
);

create table Storage (
	storage_id nvarchar(30) primary key,
);

create table Slot (
	slot_id nvarchar(30) primary key,
	storage_id nvarchar(30) not null,
	product_id nvarchar(30) not null, 
	status int
);

create table LastId (
	product_id nvarchar(30),
	order_id nvarchar(30),
	order_detail_id nvarchar(30),
	storage_id nvarchar(30),
	slot_id nvarchar(30)
)


alter table Tag add constraint FK_Tag_Product
foreign key (product_id) references Product(product_id);

alter table Order_Detail add constraint FK_OrderDetail_Order
foreign key (order_id) references Order_Product(order_id);
alter table Order_Detail add constraint FK_OrderDetail_Product
foreign key (product_id) references Product(product_id);

alter table Slot add constraint FK_Slot_Storage
foreign key (storage_id) references Storage(storage_id);
alter table Slot add constraint FK_Slot_Product
foreign key (product_id) references Product(product_id);

insert into Product
values ('PRO_1','product 1',0,'detail product 1'),
('PRO_2','product 2',0,'detail product 2'),
('PRO_3','product 3',0,'detail product 3'),
('PRO_4','product 4',0,'detail product 4'),
('PRO_5','product 5',0,'detail product 5'),
('PRO_6','product 6',0,'detail product 6'),
('PRO_7','product 7',0,'detail product 7');

insert into LastId
values ('7','0','0','0','0');


insert into Tag
values ('TAG_1','PRO_2','',null,'',null);

drop table Slot
go
drop table Tag
go
drop table Order_Detail
go
drop table Order_Product
go
drop table Storage
go
drop table Product
go