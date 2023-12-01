CREATE TABLE Usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    apellido_paterno VARCHAR(255),
    apellido_materno VARCHAR(255),
    fecha_nacimiento DATE,
    sexo VARCHAR(10),
    correo VARCHAR(255),
    telefono VARCHAR(20)
);
