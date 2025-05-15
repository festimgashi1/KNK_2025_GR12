
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

CREATE TABLE pending_airlines (
    id SERIAL PRIMARY KEY,
    airline_name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    password_hash TEXT NOT NULL,
    salt TEXT NOT NULL,
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(10) DEFAULT 'PENDING'
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

CREATE TABLE Address (
    addressId SERIAL PRIMARY KEY,
    county VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(100),
    zipCode INT
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

CREATE TABLE Tickets (
    ticketid SERIAL PRIMARY KEY,
    flightNumber INT REFERENCES Flights(flightNumber),
    customerid INT REFERENCES Costumer(costumerId),
    bookingdate DATE,
    ticketprice DECIMAL(10,2),
    paymentmethod VARCHAR(50),
    passengers INT
);

CREATE TABLE CheckIn (
    id SERIAL PRIMARY KEY,
    ticketid INT REFERENCES Tickets(ticketid),
    checkinTime TIMESTAMP,
    seatNumber VARCHAR(10),
    method VARCHAR(20)
);

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

CREATE TABLE Gate(
    gateId SERIAL PRIMARY KEY,
    flightNumber INT REFERENCES Flights(flightNumber),
    gateNumber VARCHAR(10),
    status VARCHAR(50)
);

CREATE TABLE Seat (
    seatNumber VARCHAR(10),
    classType VARCHAR(50),
    isAvailable BOOLEAN,
    flightNumber INT REFERENCES Flights(flightNumber),
	ticketId INT REFERENCES Tickets(ticketid),
    PRIMARY KEY (seatNumber, flightNumber)
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

CREATE TABLE TravelDocuments (
    documentId SERIAL PRIMARY KEY,
    costumerId INT REFERENCES Costumer(costumerId),
    bookingId INT REFERENCES Booking(bookingId),
    issueDate DATE,
    expiryDate DATE,
    isValid VARCHAR(10),
    fileAttachment TEXT
);

CREATE TABLE BoardingPass (
    reservationId SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    flight_date DATE NOT NULL,
    flight_code VARCHAR(20) NOT NULL,
    gate VARCHAR(10) NOT NULL,
    boarding_time VARCHAR(10) NOT NULL,
    seat VARCHAR(10) NOT NULL
);
SELECT t.* FROM tickets t
JOIN flights f ON t.flightNumber = f.flightNumber
WHERE f.departure = ? AND f.destination = ? AND f.departure_date = ?




ALTER TABLE Costumer DROP COLUMN gender;

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

ALTER TABLE Admin
ALTER COLUMN salt TYPE VARCHAR(300);

ALTER TABLE Admin
ALTER COLUMN hashpassword TYPE VARCHAR(300);

ALTER TABLE Airline
ALTER COLUMN hashpassword TYPE VARCHAR(300);

ALTER TABLE Airline
ALTER COLUMN salt TYPE VARCHAR(300);



insert into Admin(id, firstName,lastName, email, salt , hashpassword)
values(1, 'Festim', 'Gashi', 'admin@gmail.com', '+lxY0OuuRfNBzP0qvrd1Jq7Z4lspDmH57EZrt0OhteE=' , '2b6c7859304f757552664e427a503071767264314a71375a346c7370446d483537455a7274304f687465453d9600cc448737ec409df22929e8410c31db52b046d525d93dd72367a416a5e3a8');

<<<<<<< HEAD
ALTER TABLE Tickets
ADD COLUMN passengers INT;
=======
ALTER TABLE flights
ALTER COLUMN duration TYPE VARCHAR(50)
USING duration::VARCHAR;
>>>>>>> 10739e242b198c92643e99d7d044553670818756
