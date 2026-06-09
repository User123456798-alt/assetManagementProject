CREATE TABLE employees (
                           id BIGSERIAL PRIMARY KEY,
                           employee_id VARCHAR(50),
                           first_name VARCHAR(100),
                           last_name VARCHAR(100),
                           department VARCHAR(100),
                           email VARCHAR(150)
);

CREATE TABLE assets (
                        id BIGSERIAL PRIMARY KEY,
                        asset_name VARCHAR(100),
                        asset_type VARCHAR(100),
                        serial_number VARCHAR(100) UNIQUE,
                        status VARCHAR(20)
);

CREATE TABLE asset_assignments (
                                   id BIGSERIAL PRIMARY KEY,
                                   employee_id BIGINT REFERENCES employees(id),
                                   asset_id BIGINT REFERENCES assets(id),
                                   assigned_date DATE,
                                   returned_date DATE
);