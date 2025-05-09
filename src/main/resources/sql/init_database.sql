
CREATE TABLE Admin (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(100),
	lastName VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    hashPassword VARCHAR(100) NOT NULL,
	salt VARCHAR(100) NOT NULL
);

CREATE TABLE Airline (
    airlineid SERIAL PRIMARY KEY,
    airlinename VARCHAR(100) NOT NULL,
    country VARCHAR(100),
    email VARCHAR(100),
    hashpassword VARCHAR(100),
	salt VARCHAR(100),
    phoneNumber VARCHAR(20)
);

CREATE TABLE AirlineStaff (
    staffId SERIAL PRIMARY KEY,
    airlineId INT REFERENCES Airline(airlineid),
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    phoneNumber VARCHAR(20),
    role VARCHAR(50)
);

CREATE TABLE AirportStaff (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20),
    role VARCHAR(50),
    addressId INT REFERENCES Address(addressId),
    startedAt DATE,
    shift VARCHAR(50)
);

CREATE TABLE CheckIn (
    id SERIAL PRIMARY KEY,
    ticketid INT REFERENCES Tickets(ticketid),
    checkinTime TIMESTAMP,
    seatNumber VARCHAR(10),
    method VARCHAR(20)
);

CREATE TABLE Costumer (
    costumerId SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    telephoneNumber VARCHAR(20),
    gender VARCHAR(10),
    birthDate DATE,
    hashpassword VARCHAR(100),
	salt VARCHAR(100),
    addres_id INT REFERENCES Address(addressId)
);

ALTER TABLE Costumer DROP COLUMN gender;

CREATE TABLE Delays (
    delayId SERIAL PRIMARY KEY,
    flightNumber INT REFERENCES Flights(flightNumber),
    delayDuration INT,
    date DATE,
    status VARCHAR(50)
);

CREATE TABLE Feedback (
    fb_id SERIAL PRIMARY KEY,
    costumerId INT REFERENCES Costumer(costumerId),
    flightNumber INT REFERENCES Flights(flightNumber),
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comments VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE Flights (
    flightNumber SERIAL PRIMARY KEY,
    airlineid INT REFERENCES Airline(airlineid),
    planeid INT,
    departureAirport VARCHAR(100),
    arrivalAirport VARCHAR(100),
    departureTime TIMESTAMP,
    arrivalTime TIMESTAMP,
    duration INTERVAL,
    status VARCHAR(50)
);

CREATE TABLE Gate(
    gateId SERIAL PRIMARY KEY,
    flightNumber INT REFERENCES Flights(flightNumber),
    gateNumber VARCHAR(10),
    status VARCHAR(50)
);

CREATE TABLE Refunds (
    refundId SERIAL PRIMARY KEY,
    paymentid INT,
    bookingId INT REFERENCES Booking(bookingId),
    flightId INT REFERENCES Flights(flightNumber),
    refundAmount DECIMAL(10,2),
    refundStatus VARCHAR(50),
    refundReason VARCHAR(100),
    transactionDate DATE
);

CREATE TABLE Seat (
    seatNumber VARCHAR(10),
    classType VARCHAR(50),
    isAvailable BOOLEAN,
    flightNumber INT REFERENCES Flights(flightNumber),
	ticketId INT REFERENCES Tickets(ticketid),
    PRIMARY KEY (seatNumber, flightNumber)
);

CREATE TABLE Address (
    addressId SERIAL PRIMARY KEY,
    county VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(100),
    zipCode INT
);

CREATE TABLE Tickets (
    ticketid SERIAL PRIMARY KEY,
    flightNumber INT REFERENCES Flights(flightNumber),
    customerid INT REFERENCES Costumer(costumerId),
    bookingdate DATE,
    ticketprice DECIMAL(10,2),
    paymentmethod VARCHAR(50)
);

CREATE TABLE TravelDocuments (
    documentId SERIAL PRIMARY KEY,
    costumerId INT REFERENCES Costumer(costumerId),
    bookingId INT REFERENCES Booking(bookingId),
    issueDate DATE,
    expiryDate DATE,
    isValid VARCHAR(10),
    fileAttachment TEXT
);

CREATE TABLE Booking (
    bookingId SERIAL PRIMARY KEY,
    costumerId INTEGER NOT NULL REFERENCES Costumer(costumerId),
    flightNumber INTEGER NOT NULL REFERENCES Flights(flightNumber),
    departureDate DATE NOT NULL,
    destination VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    seatNumber VARCHAR(10),

    FOREIGN KEY (seatNumber, flightNumber) REFERENCES Seat(seatNumber, flightNumber)
);

ALTER TABLE Costumer
ALTER COLUMN hashpassword TYPE VARCHAR(256);

ALTER TABLE Costumer
ALTER COLUMN salt TYPE VARCHAR(128);

ALTER TABLE Costumer
DROP COLUMN addres_id;

ALTER TABLE Costumer
ADD COLUMN address VARCHAR(255);

ALTER TABLE AirportStaff
DROP COLUMN addressId;

ALTER TABLE AirportStaff
ADD COLUMN address VARCHAR(255);

DROP TABLE Address;