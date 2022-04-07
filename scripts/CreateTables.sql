drop table if exists City;
drop table if exists Suppliers;
drop table if exists Products;
drop table if exists SupplyOrders;
drop table if exists Items;
drop table if exists OrderLines;
drop table if exists Customers;
drop table if exists Employees;
drop table if exists Departments;
drop table if exists Orders;
drop table if exists StorageLines;
drop table if exists Shelves;

create table City(
    ZIP varchar(10) primary key,
    Name varchar(100)
);

create table Suppliers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table Products(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Description varchar (500),
    ProductType varchar(50),
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
    Id uniqueidentifier default NEWID() unique,
    ProductId int,
    foreign key (ProductId) references Products(Id)
);

create table OrderLines(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
    Items uniqueidentifier,
    foreign key (Items) references Items(Id)
);

create table Departments(
    Id int identity(1,1) primary key,
    Name varchar(100),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table Customers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    /* should work like enum in mysql/java */
    /*CustomerType varchar(10) NOT NULL CHECK (name IN('PRIVATE', 'BUSINESS', 'STUDENT')),*/
    Email varchar(100),
    Phone varchar(20),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table Employees(
    Id int identity(1,1) primary key,
    Name varchar(100),
    /* should work like enum in mysql/java */
    /*EmployeeType varchar(10) NOT NULL CHECK (name IN('CEO', 'REGULAR')),*/
    Email varchar(100),
    Phone varchar(20),
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100),
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id)
);

create table Orders(
    Id int identity(1,1) primary key,
    CustomerId int,
    foreign key (CustomerId) references Customers(Id),
    EmployeeId int,
    foreign key (EmployeeId) references Employees(Id),
    OrderLineId int,
    foreign key (OrderLineId) references OrderLines(Id)
);

create table StorageLines(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
    Items uniqueidentifier,
    foreign key (Items) references Items(Id)
);

create table Shelves(
    Id int identity(1,1) primary key,
    Name varchar(10),
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id),
    StorageLineId int,
    foreign key (StorageLineId) references StorageLines(Id)
);