DROP TABLE Purchase;
DROP TABLE Session;
DROP TABLE Room;
DROP TABLE Movie;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Movie (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tittle VARCHAR(200) NOT NULL,
    summary VARCHAR(200) NOT NULL,
    duration VARCHAR(20) NOT NULL,
    CONSTRAINT MoviePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Room (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR (20) NOT NULL,
    capacity SMALLINT NOT NULL,
    CONSTRAINT RoomPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Session (
    id BIGINT NOT NULL AUTO_INCREMENT,
    price DECIMAL(6, 2) NOT NULL,
    date DATE NOT NULL,
    hour TIME NOT NULL,
    avaiableSeats SMALLINT NOT NULL,
    movieId BIGINT NOT NULL,
    roomId BIGINT NOT NULL,
    CONSTRAINT SessionPK PRIMARY KEY (id),
    CONSTRAINT SessionMovieFK FOREIGN KEY (movieId)
                     REFERENCES Movie (id),
    CONSTRAINT SessionRoomFK FOREIGN KEY (roomId)
                     REFERENCES Room (id)
) ENGINE = InnoDB;

CREATE TABLE Purchase (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cardNumber VARCHAR(16) NOT NULL,
    amount SMALLINT NOT NULL,
    userId BIGINT NOT NULL,
    sessionId BIGINT NOT NULL,
    CONSTRAINT PurchasePK PRIMARY KEY (id),
    CONSTRAINT PurchaseUserIdFK FOREIGN KEY (userId)
                      REFERENCES User (id),
    CONSTRAINT PurchaseSessionIdFK FOREIGN KEY (sessionId)
                      REFERENCES Session (id)
) ENGINE = InnoDB;
