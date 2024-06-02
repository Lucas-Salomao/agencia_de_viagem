CREATE SCHEMA users_details;
CREATE TABLE users_details.users (
                                     id BIGSERIAL PRIMARY KEY,
                                     firstname VARCHAR(255) NOT NULL,
                                     lastname VARCHAR(255) NOT NULL,
                                     username VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) NOT NULL
);