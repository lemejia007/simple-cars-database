
DROP TABLE CarTable;

CREATE TABLE CarTable (
    CarVIN     VARCHAR (17) NOT NULL,
    CarMake    VARCHAR (10) NOT NULL,
    CarModel   VARCHAR (15) NOT NULL,
    CarColor   VARCHAR (23) NOT NULL,
    CarYear    DECIMAL (4)  NOT NULL,
    CarMileage DECIMAL (6)  NOT NULL, 
    PRIMARY KEY (CarVIN)
);

INSERT INTO CarTable (CarVIN, CarMake, CarModel, CarColor, CarYear, CarMileage) 
VALUES ('123ABC456DEF789GH', 'Honda', 'Civic', 'Black', 1998, 135647),
       ('456123HIJKLMNOPQ1', 'Honda', 'Accord', 'Green', 2009, 46877),
       ('987654321LOLHAHA2', 'Nissan', 'Pickup', 'Grey', 1997, 235444);
       