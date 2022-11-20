CREATE TABLE drone
(
    serial_number    TEXT UNIQUE ,
    model            TEXT NOT NULL ,
    weight_limit     FLOAT NOT NULL ,
    battery_capacity INT NOT NULL ,
    state            TEXT NOT NULL ,

    CONSTRAINT primary_key PRIMARY KEY (serial_number)
);

CREATE TABLE medication
(
    id        BIGSERIAL UNIQUE ,
    name      TEXT NOT NULL ,
    weight    FLOAT NOT NULL ,
    code      TEXT NOT NULL ,
    image_link TEXT
);

CREATE TABLE drone_medication
(
    drone_id TEXT NOT NULL ,
    medication_id BIGINT NOT NULL,

    CONSTRAINT fk_drone FOREIGN KEY(drone_id)
        REFERENCES drone(serial_number),
    CONSTRAINT fk_medication FOREIGN KEY(medication_id)
        REFERENCES medication(id)
)