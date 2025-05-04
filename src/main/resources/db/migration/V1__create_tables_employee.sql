-- Criação da tabela funcionario
CREATE TABLE tb_employee (
    id UUID PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) NOT NULL UNIQUE,
    document_id VARCHAR(255) NOT NULL,
    employee_company_id int NOT NULL,
    address_id UUID NOT NULL,
    employee_position VARCHAR(50),
    employee_status VARCHAR(50),
    hiring_date DATE,
    status varchar(255) NOT NULL,
    create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp DEFAULT CURRENT_DATE
);

-- Criação da tb_employee_role
CREATE TABLE tb_employee_role (
    id UUID PRIMARY KEY,
    employee_id UUID NOT NULL,
    role VARCHAR(50) NOT NULL,
    status varchar(255) NOT NULL,
    create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp DEFAULT CURRENT_DATE,
    FOREIGN KEY (employee_id) REFERENCES tb_employee(id) ON DELETE CASCADE
);
