
INSERT INTO City VALUES('DK-9000', 'Aalborg', 1)
INSERT INTO City VALUES('SK-4212', 'Bratislava', 1)
INSERT INTO City VALUES('CZ-60200', 'Brno', 0)

INSERT INTO Suppliers VALUES('Supplier1','Supplier1@emal.com','111111','DK-9000','Street 1', 1)
INSERT INTO Suppliers VALUES('Supplier2','Supplier2@emal.com','222222','SK-4212','Street 2', 1)
INSERT INTO Suppliers VALUES('Supplier3','Supplier3@emal.com','333333','CZ-60200','Street 3', 0)

INSERT INTO ProductTypes VALUES('BIKE')
INSERT INTO ProductTypes VALUES('EBIKE')
INSERT INTO ProductTypes VALUES('ACCESSORIES')
INSERT INTO ProductTypes VALUES('ELECTRONICS')
INSERT INTO ProductTypes VALUES('PARTS')
INSERT INTO ProductTypes VALUES('TOOLS')
INSERT INTO ProductTypes VALUES('CLOTHING')
INSERT INTO ProductTypes VALUES('SHOES')
INSERT INTO ProductTypes VALUES('NUTRITION')

INSERT INTO Products VALUES('Product1','Description one',1,'1000',0,1)
INSERT INTO Products VALUES('Product2','Description two',2,'2000',10,1)
INSERT INTO Products VALUES('Product3','Description three',3,'3000',5,0)

INSERT INTO SupplyOrders VALUES(1,10,'2020-01-01',1, 1, 1)
INSERT INTO SupplyOrders VALUES(2,20,'2021-02-02',2, 1, 1)
INSERT INTO SupplyOrders VALUES(3,30,'2022-03-03',3, 1, 0)

INSERT INTO Departments VALUES('Department1','DK-9000','Street11', 1)
INSERT INTO Departments VALUES('Department2','SK-4212','Street22', 1)
INSERT INTO Departments VALUES('Department3','CZ-60200','Street33', 0)

INSERT INTO Items VALUES(1, 1, 0) 
INSERT INTO Items VALUES(2, 1, 0)
INSERT INTO Items VALUES(2, 2, 0)
INSERT INTO Items VALUES(3, 1, 0)

INSERT INTO OrderLines VALUES(1,1,1)
INSERT INTO OrderLines VALUES(2,2,1)
INSERT INTO OrderLines VALUES(3,1,0)

INSERT INTO OrderLineDetails VALUES(1,1)
INSERT INTO OrderLineDetails VALUES(2,2)
INSERT INTO OrderLineDetails VALUES(2,3)
INSERT INTO OrderLineDetails VALUES(3,4)

INSERT INTO CustomerTypes VALUES('PRIVATE')
INSERT INTO CustomerTypes VALUES('BUSINESS')
INSERT INTO CustomerTypes VALUES('STUDENT')

INSERT INTO Customers VALUES('Customer1','custome1r@email.com','111','DK-9000','Street 111',1, 1)
INSERT INTO Customers VALUES('Customer2','customer2@email.com','222','SK-4212','Street 222',2, 1)
INSERT INTO Customers VALUES('Customer3','customer3@email.com','333','CZ-60200','Street 333',3, 0)

INSERT INTO EmployeeTypes VALUES('ADMIN')
INSERT INTO EmployeeTypes VALUES('CEO')
INSERT INTO EmployeeTypes VALUES('EMPLOYEE')

INSERT INTO Employees VALUES('Employee1','employee1@email.com','111111','DK-9000','Street 1111',1, '$2a$12$7OqbaBnW26W/IkqDN1RiVucu8gG64O7kgOyWHcA8qa9zAnIuqCDUK', 0101010101, 1, 1)
INSERT INTO Employees VALUES('Employee2','employee2@email.com','222222','SK-4212','Street 2222',2,'$2a$12$7OqbaBnW26W/IkqDN1RiVucu8gG64O7kgOyWHcA8qa9zAnIuqCDUK', 0202020202, 2, 1)
INSERT INTO Employees VALUES('Employee3','employee3@email.com','333333','CZ-60200','Street 3333',3,'$2a$12$7OqbaBnW26W/IkqDN1RiVucu8gG64O7kgOyWHcA8qa9zAnIuqCDUK', 0303030303, 3, 0)
insert into Employees values('admin', 'admin', 1234, 'CZ-60200', 'test', 3, '$2a$12$7OqbaBnW26W/IkqDN1RiVucu8gG64O7kgOyWHcA8qa9zAnIuqCDUK', 909090090, 1, 1)

INSERT INTO Orders VALUES(1,1, '2022-05-01', 1)
INSERT INTO Orders VALUES(2,2, '2022-05-01', 1)
INSERT INTO Orders VALUES(3,2, '2022-05-01', 0)

INSERT INTO OrderDetails VALUES(1,1)
INSERT INTO OrderDetails VALUES(2,2)
INSERT INTO OrderDetails VALUES(3,3)

INSERT INTO Shelves VALUES('A1',1, 1, 1, 1)
INSERT INTO Shelves VALUES('B2',2, 2, 2, 1)
INSERT INTO Shelves VALUES('C4',3, 3, 3, 0)

INSERT INTO ShelfDetails VALUES(1,1,0)
INSERT INTO ShelfDetails VALUES(2,2,0)
INSERT INTO ShelfDetails VALUES(3,3,0)