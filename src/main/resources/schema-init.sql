CREATE TABLE IF NOT EXISTS scale (
    id SERIAL PRIMARY KEY,
    tienda INTEGER NOT NULL,
    formato VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    departamento INTEGER NOT NULL,
    ip_balanza VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT FALSE,
    last_update VARCHAR(100) NOT NULL,
    carga_layout BOOLEAN DEFAULT FALSE,
    es_dual BOOLEAN DEFAULT FALSE,
    carga_maestra BOOLEAN DEFAULT TRUE,
    es_autoservicio BOOLEAN DEFAULT FALSE
);