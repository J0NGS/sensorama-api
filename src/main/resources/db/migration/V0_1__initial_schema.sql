-- V1__initial_schema.sql

-- Tabela Address
CREATE TABLE addresses (
    id UUID PRIMARY KEY NOT NULL,
    country VARCHAR(20),
    city VARCHAR(100),
    state VARCHAR(100)
);

-- Tabela Auth
CREATE TABLE auths (
    id UUID PRIMARY KEY NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela Privilege
CREATE TABLE privileges (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    is_signature_revoked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela Role
CREATE TABLE roles (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela Profile
CREATE TABLE profiles (
    id UUID PRIMARY KEY NOT NULL,
    photo TEXT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    birth_date DATE,
    address_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE SET NULL
);

-- Tabela User
CREATE TABLE users (
    id UUID PRIMARY KEY NOT NULL,
    status VARCHAR(50),
    auth_id UUID,
    profile_id UUID,
    role_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_auth FOREIGN KEY (auth_id) REFERENCES auths(id) ON DELETE CASCADE,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE SET NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL
);

-- Tabela de relacionamento entre Role e Privilege
CREATE TABLE privileges_on_roles (
    role_id UUID NOT NULL,
    privilege_id UUID NOT NULL,
    PRIMARY KEY (role_id, privilege_id),
    CONSTRAINT fk_role_privilege FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_privilege_role FOREIGN KEY (privilege_id) REFERENCES privileges(id) ON DELETE CASCADE
);

-- Tabela de relacionamento entre User e Privilege
CREATE TABLE privileges_on_users (
    user_id UUID NOT NULL,
    privilege_id UUID NOT NULL,
    PRIMARY KEY (user_id, privilege_id),
    CONSTRAINT fk_user_privilege FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_privilege_user FOREIGN KEY (privilege_id) REFERENCES privileges(id) ON DELETE CASCADE
);

-- Criação da sequência revinfo_seq
CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 50;

-- Tabela de auditoria do Envers
CREATE TABLE revinfo (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('revinfo_seq'),
    timestamp BIGINT,
    username VARCHAR(255)
);

-- Tabelas de auditoria para entidades auditadas
CREATE TABLE auths_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    username VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE privileges_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    is_signature_revoked BOOLEAN,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE roles_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE profiles_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    photo TEXT,
    name VARCHAR(255),
    phone VARCHAR(50),
    birth_date DATE,
    address_id UUID,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE users_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    status VARCHAR(50),
    auth_id UUID,
    profile_id UUID,
    role_id UUID,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);
