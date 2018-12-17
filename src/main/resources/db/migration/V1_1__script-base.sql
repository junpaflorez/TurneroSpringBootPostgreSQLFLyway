CREATE TABLE Asesor (
    identificacion VARCHAR(5) NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Turno (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    secuencia BIGINT NOT NULL,
    categoria VARCHAR(2) NOT NULL,
    hora TIME NOT NULL DEFAULT CURRENT_TIME,
    fecha DATE NOT NULL DEFAULT CURRENT_DATE,
    llamado BOOLEAN NOT NULL,
    atendido BOOLEAN NOT NULL
);

CREATE TABLE Atendido (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    fkTurno BIGINT NOT NULL UNIQUE,
    fkAsesor VARCHAR(5) NOT NULL,
    horaInicio TIME NOT NULL,
    horaFinal TIME NOT NULL,
    promedioEspera TIME,
    promedioAtendido TIME
);

CREATE TABLE Cola (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    turno INT NOT NULL UNIQUE,
    asesor VARCHAR(5) NULL
);

alter table Atendido
  add constraint asesorAtendido
  foreign key (fkAsesor)
  references Asesor (identificacion);

alter table Atendido
  add constraint turnoAtendido
  foreign key (fkTurno)
  references Turno (id);