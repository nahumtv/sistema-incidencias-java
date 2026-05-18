CREATE TYPE rol_usuario AS ENUM (
    'ATC',
    'DESARROLLADOR',
    'SUPERVISOR',
    'ADMIN'
);

CREATE TYPE turno_atc AS ENUM (
    'MANANA',
    'TARDE',
    'NOCHE'
);

CREATE TYPE estado_ticket AS ENUM (
    'ABIERTO',
    'EN_PROCESO',
    'PENDIENTE',
    'RESUELTO',
    'CERRADO'
);

CREATE TYPE nivel_severidad AS ENUM (
    'CRITICO',
    'ALTO',
    'MEDIO',
    'BAJO'
);

CREATE TYPE formato_reporte AS ENUM (
    'PDF',
    'CSV',
    'JSON'
);

CREATE TYPE tipo_reporte AS ENUM (
    'TICKETS_POR_PERIODO',
    'TICKETS_POR_DESARROLLADOR',
    'TICKETS_POR_INCIDENCIA',
    'RENDIMIENTO_GENERAL'
);


CREATE TABLE usuarios (
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    apellido    VARCHAR(100) NOT NULL,
    correo      VARCHAR(150) UNIQUE NOT NULL,
    contrasena  VARCHAR(255) NOT NULL,
    rol         rol_usuario  NOT NULL,
    activo      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLA USUARIOS ATC
-- (especialización de usuarios)
-- =========================
CREATE TABLE usuarios_atc (
    id                       INT         PRIMARY KEY,
    turno                    turno_atc   NOT NULL,
    total_tickets_registrados INT        NOT NULL DEFAULT 0,
    created_at               TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_atc
        FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- =========================
-- TABLA DESARROLLADORES
-- (especialización de usuarios)
-- =========================
CREATE TABLE desarrolladores (
    id           INT          PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    carga_actual INT          NOT NULL DEFAULT 0,  -- calculado, se sincroniza con trigger
    disponible   BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_desarrollador
        FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- =========================
-- TABLA INCIDENCIAS (catálogo)
-- =========================
CREATE TABLE incidencias (
    id               INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    categoria        VARCHAR(100) NOT NULL,
    descripcion_tipo TEXT         NOT NULL,
    frecuencia       INT          NOT NULL DEFAULT 0, -- calculado via trigger
    activo           BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLA TICKETS (central)
-- =========================
CREATE TABLE tickets (
    id                  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion         TEXT              NOT NULL,
    estado              estado_ticket     NOT NULL DEFAULT 'ABIERTO',
    severidad           nivel_severidad   NOT NULL,
    plataforma          VARCHAR(100)      NOT NULL,
    paciente_afectado   VARCHAR(150)      NOT NULL,
    fecha_resolucion    TIMESTAMP         NULL,
    creado_por          INT               NOT NULL,  -- FK al ATC que lo registró
    desarrollador_id    INT               NULL,
    incidencia_id       INT               NOT NULL,
    created_at          TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ticket_creado_por
        FOREIGN KEY (creado_por)       REFERENCES usuarios_atc(id) ON DELETE RESTRICT,
    CONSTRAINT fk_ticket_desarrollador
        FOREIGN KEY (desarrollador_id) REFERENCES desarrolladores(id) ON DELETE SET NULL,
    CONSTRAINT fk_ticket_incidencia
        FOREIGN KEY (incidencia_id)    REFERENCES incidencias(id)    ON DELETE RESTRICT,
    CONSTRAINT chk_fecha_resolucion
    CHECK (
        (
            estado IN ('RESUELTO', 'CERRADO')
            AND fecha_resolucion IS NOT NULL
        )
        OR
        (
            estado NOT IN ('RESUELTO', 'CERRADO')
        )
    )
);

-- =========================
-- TABLA HISTORIAL DE CAMBIOS
-- =========================
CREATE TABLE historial_cambios (
    id              INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ticket_id       INT           NOT NULL,
    autor_id        INT           NOT NULL,   -- FK a usuarios (quién hizo el cambio)
    estado_anterior estado_ticket NOT NULL,
    estado_nuevo    estado_ticket NOT NULL,
    comentario      TEXT          NOT NULL,
    fecha_cambio    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_ticket
        FOREIGN KEY (ticket_id) REFERENCES tickets(id)  ON DELETE CASCADE,
    CONSTRAINT fk_historial_autor
        FOREIGN KEY (autor_id)  REFERENCES usuarios(id) ON DELETE RESTRICT
);

-- =========================
-- TABLA REPORTES
-- =========================
CREATE TABLE reportes (
    id                INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo              tipo_reporte    NOT NULL,
    fecha_desde       DATE            NOT NULL,
    fecha_hasta       DATE            NOT NULL,
    generado_por      INT             NOT NULL,  -- FK a usuarios
    formato           formato_reporte NOT NULL DEFAULT 'PDF',
    ruta_archivo      VARCHAR(500)    NULL,       -- path del archivo generado
    fecha_generacion  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reporte_usuario
        FOREIGN KEY (generado_por) REFERENCES usuarios(id) ON DELETE RESTRICT,
    CONSTRAINT chk_fechas_reporte
        CHECK (fecha_hasta >= fecha_desde)
);

