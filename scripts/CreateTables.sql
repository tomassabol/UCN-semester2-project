drop table if exists OrderDetails;
drop table if exists Orders;
drop table if exists Employees;
drop table if exists EmployeeTypes;
drop table if exists Customers;
drop table if exists CustomerTypes;
drop table if exists ShelfDetails
drop table if exists Shelves;
drop table if exists StorageLineDetails;
drop table if exists StorageLines;
drop table if exists OrderLineDetails;
drop table if exists OrderLines;
drop table if exists SupplyOrders;
drop table if exists Suppliers;
drop table if exists Items;
drop table if exists Products;
drop table if exists ProductTypes;
drop table if exists Departments;
drop table if exists City;

create table City(
    Id int identity(1,1) unique not null,
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
    foreign key (SupplierId) references Suppliers(Id),
    Delivered bit
);

create table Departments(
    Id int identity(1,1) primary key,
    Name varchar(100) unique,
    ZIP varchar(10),
    foreign key (ZIP) references City(ZIP),
    Address varchar(100)
);

create table Items(
    Id int identity(1,1) primary key,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id),
    Sold bit
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
    EmployeeTypeId int,
    foreign key (EmployeeTypeId) references EmployeeTypes(Id),
    Password varchar(20),
    CPR varchar(10) unique,
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id)
);

create table Orders(
    Id int identity(1,1) primary key,
    CustomerId int,
    foreign key (CustomerId) references Customers(Id),
    EmployeeId int,
    foreign key (EmployeeId) references Employees(Id),
    [Date] date
);

create table OrderDetails(
    OrderId int,
    foreign key (OrderId) references Orders(Id),
    OrderLineId int,
    foreign key (OrderLineId) references OrderLines(Id)
);

create table Shelves(
    Id int identity(1,1) primary key,
    Name varchar(10) unique,
    ProductId int,
    foreign key (ProductId) references Products(Id),
    ItemQuantity int,
    DepartmentId int,
    foreign key (DepartmentId) references Departments(Id),
);

create table ShelfDetails(
    ShelfId int,
    foreign key (ShelfId) references Shelves(Id),
    ItemId int,
    foreign key (ItemId) references Items(Id),
    Disabled bit
);