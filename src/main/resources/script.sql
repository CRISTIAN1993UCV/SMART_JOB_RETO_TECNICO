CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created DATETIME,
    modified DATETIME,
    last_login DATETIME,
    token VARCHAR(255),
    is_active BOOLEAN
);

CREATE TABLE phone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255),
    city_code VARCHAR(255),
    country_code VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES usuario (id) ON DELETE CASCADE
);
