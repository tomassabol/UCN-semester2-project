drop table if exists OrderDetails;
drop table if exists Orders;
drop table if exists Employees;
drop table if exists EmployeeTypes;
drop table if exists Customers;
drop table if exists CustomerTypes;
drop table if exists ShelfDetails
drop table if exists Shelves;
drop table if exists Departments;
drop table if exists StorageLineDetails;
drop table if exists StorageLines;
drop table if exists OrderLineDetails;
drop table if exists OrderLines;
drop table if exists Items;
drop table if exists SupplyOrders;
drop table if exists Suppliers;
drop table if exists Products;
drop table if exists ProductTypes;
drop table if exists City;

create table City(
    ZIP varchar(10) primary key,
    Name varchar(100)
);

create table Suppliers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Email varchar(100),
    Phone varchar(20),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table ProductTypes(
    Id int identity(1,1) primary key,
    Name varchar(20)
);

create table Products(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Description varchar (500),
    ProductTypeId int,
    foreign key (ProductTypeId) references ProductTypes(Id),
    Price smallmoney,
    Discount int,
    Active bit
);

create table SupplyOrders(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
    OrderDate date,
    SupplierId int,
    foreign key (SupplierId) references Suppliers(Id)
);

create table Items(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id)
);

create table OrderLines(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
);

create table OrderLineDetails(
    OrderLineId int,
    foreign key (OrderLineId) references OrderLines(Id),
    ItemId int,
    foreign key (ItemId) references Items(Id)
);

create table Departments(
    Id int identity(1,1) primary key,
    Name varchar(100),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table CustomerTypes(
    Id int identity(1,1) primary key,
    Name varchar(50)
);

create table Customers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Email varchar(100),
    Phone varchar(20),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100),
    CustomerTypeId int,
    foreign key (CustomerTypeId) references CustomerTypes(Id)
);

create table EmployeeTypes(
    Id int identity(1,1) primary key,
    Name varchar(50)
);

create table Employees(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Email varchar(100),
    Phone varchar(20),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100),
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id),
    EmployeeTypeId int,
    foreign key (EmployeeTypeId) references EmployeeTypes(Id)
);

create table Orders(
    Id int identity(1,1) primary key,
    CustomerId int,
    foreign key (CustomerId) references Customers(Id),
    EmployeeId int,
    foreign key (EmployeeId) references Employees(Id),
);

create table OrderDetails(
    OrderId int,
    foreign key (OrderId) references Orders(Id),
    OrderLineId int,
    foreign key (OrderLineId) references OrderLines(Id)
);

create table StorageLines(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
);

create table StorageLineDetails(
    StorageLineId int,
    foreign key (StorageLineId) references StorageLines(Id),
    ItemId int,
    foreign key (ItemId) references Items(Id)
);

create table Shelves(
    Id int identity(1,1) primary key,
    Name varchar(10),
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id),
);

create table ShelfDetails(
    ShelfId int,
    foreign key (ShelfId) references Shelves(Id),
    StorageLineId int,
    foreign key (StorageLineId) references StorageLines(Id)
);