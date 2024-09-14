CREATE DATABASE PcParts;
USE PcParts;

-- PC case table
CREATE TABLE pc_case (
    id                  integer AUTO_INCREMENT primary key,
    name                varchar(255) not null,
    brand               varchar(255) not null,
    part_number 		VARCHAR(255) unique NOT NULL,
    case_type      		varchar(255) not null,
    color				varchar(255) not null,
    price 				DECIMAL(10, 2)
);

INSERT INTO pc_case(name, brand, part_number, case_type, color, price) VALUES
("Cooler Masterbox", "Cooler Master", "MCB-Q300L_KANN-S00", "MicroATX Mini Tower", "Black", 44.99),
("In Win D-Frame 2.0", "In Win", "D-Frame 2.0 BK/GO", "ATX FULL TOWER", "Black/Gold", 1199.00),
("Corsair iCUE 4000X RGB", "Corsair", "CC-9011205-WW", "ATX Mid Tower", "White", 130.99 );

-- PC storage device table
CREATE TABLE storage_drive (
	id integer AUTO_INCREMENT primary key,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    storage_type VARCHAR(255) NOT NULL,
    capacity VARCHAR(255) NOT NULL  
);

INSERT INTO storage_drive(name, brand, part_number, price, storage_type, capacity) VALUES
("Samsung 970 Evo 1 TB", "Samsung", "MZ-V7E1T0BW", 168.28, "SSD", "1 TB"),
("Seagate BarraCuda", "Seagate", "ST1000DM010", 49.99, "HDD", "1 TB"),
("Western Digital Blue 2 TB", "Western Digital", "BTO-003X", 49.99, "HDD", "2 TB");

-- PC RAM table
CREATE TABLE memory_ram(
	id integer AUTO_INCREMENT primary key,
    name varchar(255) NOT Null,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    form_factor VARCHAR(255) NOT NULL,
    modules VARCHAR(255) NOT NULL
);

INSERT INTO memory_ram(name, brand, part_number, price, form_factor, modules) VALUES
("Corsair Vengeance RGB", "Corsair", "CMW32GX4M2C3200C16", 131.99, "288-pin DIMM", "2 x 16GB"),
("Crucial Ballistix 16 GB", "Crucial", "BL2K8G32C16U4B", 59.99, "288-pin DIMM", "2 x 8GB"),
("Samsung 8 GB", "Samsung", "MV-3V4G3D/US", 306.36, "240-pin DIMM", "2 x 4GB");

-- Monitor table
CREATE TABLE monitor(
	id integer AUTO_INCREMENT primary key,
    name varchar(255) NOT Null,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    screen_size VARCHAR(255) NOT NULL,
    resolution VARCHAR(255) NOT NULL
);

INSERT INTO monitor(name, brand, part_number, price, screen_size, resolution) VALUES
("Samsung U28E590D", "Samsung", "U28E590D", 357.68, "28", "3840 x 2160"),
("Razer RAPTOR 27", "Razer", "RZ39-02760100-R3U1", 699.99, "27", "2560 x 1440"),
("Samsung C49RG9", "Samsung", "LC49RG90SSNXZA", 1199.99, "49", "5120 x 1440");

-- PC Motherboard table
CREATE TABLE motherboard(
	id integer AUTO_INCREMENT primary key,
    name varchar(255) NOT Null,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    `socket` VARCHAR(255) NOT NULL,
    form_factor VARCHAR(255) NOT NULL
);

INSERT INTO motherboard(name, brand, part_number, price, `socket`, form_factor) VALUES
("Asus TUF GAMING X570-PLUS", "Asus", "90MB1170-MVAAY0", 188.99, "AM4", "ATX"),
("Gigabyte Z390 AORUS PRO", "Gigabyte", "GA-Z390-AORUS-PRO-WIFI", 189.99, "LGA1151", "ATX"),
("MSI B450M PRO-M2 MAX", "MSI", "7B84-017R", 78.98, "AM4", "Micro ATX");

-- PC CPU table
CREATE TABLE processor(
	id integer AUTO_INCREMENT primary key,
    name varchar(255) NOT Null,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    core_clock VARCHAR(255) NOT NULL,
    core_count int
);

INSERT INTO processor(name, brand, part_number, price, core_clock, core_count) VALUES
("Intel Core i9-9900k", "Intel", "BX80684I99900K", 399.99, "3.6 GHz", 8),
("AMD Ryzen 9 3950X", "AMD", "100-100000051WOF", 719.99, "3.5 GHz", 16),
("Intel Core i7-5960X", "Intel", "BX80648I75960X", 799.99, "3 GHz", 8);

-- GPU table
CREATE TABLE gpu(
	id integer AUTO_INCREMENT primary key,
    name varchar(255) NOT Null,
    brand VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) unique NOT NULL,
    price DECIMAL(10, 2),
    `memory` VARCHAR(255) NOT NULL,
    core_clock VARCHAR(255) NOT NULL
);

INSERT INTO gpu(name, brand, part_number, price, `memory`, core_clock) VALUES
("GeForce RTX 3090", "NVIDIA", "900-1G136-2510-000", 1499.99, "24 GB", "1395 MHz"),
("Titan V", "NVIDIA", "900-1G500-2500-000", 2756.99, "12 GB", "1200 MHz"),
("Radeon RX 5700 XT", "Radeon", "RX 5700 XT 8G", 449.99, "8 GB", "1605 MHz");

-- User's pc parts
CREATE TABLE user_pick (
    case_id int,
    motherboard_id int,
    processor_id int,
    memory_id int,
    storage_id int,
    monitor_id int,
    gpu_id int,
    FOREIGN KEY (case_id) REFERENCES pc_case(id),
    FOREIGN KEY (motherboard_id) REFERENCES motherboard(id),
    FOREIGN KEY (processor_id) REFERENCES processor(id),
    FOREIGN KEY (memory_id) REFERENCES memory_ram(id),
    FOREIGN KEY (storage_id) REFERENCES storage_drive(id),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id),
    FOREIGN KEY (gpu_id) REFERENCES gpu(id)
);
