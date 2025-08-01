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
    address_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_auth FOREIGN KEY (auth_id) REFERENCES auths(id) ON DELETE CASCADE,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE SET NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL,
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE SET NULL
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

-- Tabela Category
CREATE TABLE categories (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    icon_url TEXT,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela Badge
CREATE TABLE badges (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url TEXT,
    category_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_badge_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

-- Tabela Game
CREATE TABLE games (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    mode VARCHAR(50),
    status VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela GamePlayer
CREATE TABLE game_players (
    id UUID PRIMARY KEY NOT NULL,
    game_id UUID NOT NULL,
    profile_id UUID NOT NULL,
    score INTEGER,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    CONSTRAINT fk_gameplayer_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE CASCADE
);

-- Tabela Leaderboard
CREATE TABLE leaderboards (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0
);

-- Tabela LeaderboardEntry
CREATE TABLE leaderboard_entries (
    id UUID PRIMARY KEY NOT NULL,
    leaderboard_id UUID NOT NULL,
    profile_id UUID NOT NULL,
    score INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_leaderboard FOREIGN KEY (leaderboard_id) REFERENCES leaderboards(id) ON DELETE CASCADE,
    CONSTRAINT fk_leaderboard_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE CASCADE
);

-- Tabela Question
CREATE TABLE questions (
    id UUID PRIMARY KEY NOT NULL,
    category_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    media_url TEXT,
    media_type VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_question_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Tabela Options
CREATE TABLE options (
    id UUID PRIMARY KEY NOT NULL,
    question_id UUID NOT NULL,
    text TEXT NOT NULL,
    image_url TEXT,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Tabela Round
CREATE TABLE rounds (
    id UUID PRIMARY KEY NOT NULL,
    game_id UUID NOT NULL,
    player_id UUID NOT NULL,
    question_id UUID NOT NULL,
    start_time TIME,
    end_time TIME,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_round_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    CONSTRAINT fk_round_player FOREIGN KEY (player_id) REFERENCES game_players(id) ON DELETE CASCADE,
    CONSTRAINT fk_round_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Tabela UserBadge
CREATE TABLE user_badges (
    id UUID PRIMARY KEY NOT NULL,
    profile_id UUID NOT NULL,
    badge_id UUID NOT NULL,
    earned_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_userbadge_profile FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE CASCADE,
    CONSTRAINT fk_userbadge_badge FOREIGN KEY (badge_id) REFERENCES badges(id) ON DELETE CASCADE
);

-- =======================AUDS========================

-- Criação da sequência revinfo_seq
CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 50;

-- Tabela de auditoria do Envers
CREATE TABLE revinfo (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('revinfo_seq'),
    timestamp BIGINT,
    username VARCHAR(255)
);

-- Tabelas de auditoria para entidades auditadas
CREATE TABLE addresses_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    country VARCHAR(20),
    city VARCHAR(100),
    state VARCHAR(100),
    PRIMARY KEY (id, rev)
);

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
    address_id UUID,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);
CREATE TABLE categories_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    description TEXT,
    icon_url TEXT,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE badges_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    description TEXT,
    image_url TEXT,
    category_id UUID,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE games_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    mode VARCHAR(50),
    status VARCHAR(50),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE game_players_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    game_id UUID,
    profile_id UUID,
    score INTEGER,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE leaderboards_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    name VARCHAR(255),
    type VARCHAR(50),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE leaderboard_entries_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    leaderboard_id UUID,
    profile_id UUID,
    score INTEGER,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE questions_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    category_id UUID,
    title VARCHAR(255),
    description TEXT,
    media_url TEXT,
    media_type VARCHAR(50),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE options_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    question_id UUID,
    text TEXT,
    image_url TEXT,
    is_correct BOOLEAN,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE rounds_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    game_id UUID,
    player_id UUID,
    question_id UUID,
    start_time TIME,
    end_time TIME,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);

CREATE TABLE user_badges_AUD (
    id UUID NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    profile_id UUID,
    badge_id UUID,
    earned_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,
    version INTEGER,
    PRIMARY KEY (id, rev)
);