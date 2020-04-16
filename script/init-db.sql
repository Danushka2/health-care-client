#--------------------------------------- For Auth --------------------------------------------------

DROP SCHEMA IF EXISTS sch_auth;
CREATE SCHEMA sch_auth;

drop user 'usr_auth'@'localhost';
CREATE USER 'usr_auth'@'localhost' IDENTIFIED BY 'Auth@HealthCare';
GRANT ALL PRIVILEGES ON sch_auth.* TO 'usr_auth'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

#-------------------------------------- For Hospital -----------------------------------------------

DROP SCHEMA IF EXISTS sch_hospital;
CREATE SCHEMA sch_hospital;

drop user 'usr_hosp'@'localhost';
CREATE USER 'usr_hosp'@'localhost' IDENTIFIED BY 'Hops@HealthCare';
GRANT ALL PRIVILEGES ON sch_hospital.* TO 'usr_hosp'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

#--------------------------------------- For Doctor ------------------------------------------------

DROP SCHEMA IF EXISTS sch_doctor;
CREATE SCHEMA sch_doctor;

drop user 'usr_doc'@'localhost';
CREATE USER 'usr_doc'@'localhost' IDENTIFIED BY 'Doc@HealthCare';
GRANT ALL PRIVILEGES ON sch_doctor.* TO 'usr_doc'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

#-------------------------------------- For Patient ------------------------------------------------

DROP SCHEMA IF EXISTS sch_patient;
CREATE SCHEMA sch_patient;

drop user 'usr_pt'@'localhost';
CREATE USER 'usr_pt'@'localhost' IDENTIFIED BY 'Pt@HealthCare';
GRANT ALL PRIVILEGES ON sch_patient.* TO 'usr_pt'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

#------------------------------------- For Appointment ---------------------------------------------

DROP SCHEMA IF EXISTS sch_appointment;
CREATE SCHEMA sch_appointment;

drop user 'usr_appt'@'localhost';
CREATE USER 'usr_appt'@'localhost' IDENTIFIED BY 'Appt@HealthCare';
GRANT ALL PRIVILEGES ON sch_appointment.* TO 'usr_appt'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

#-------------------------------------- For Payment ------------------------------------------------

DROP SCHEMA IF EXISTS sch_payment;
CREATE SCHEMA sch_payment;

drop user 'usr_pay'@'localhost';
CREATE USER 'usr_pay'@'localhost' IDENTIFIED BY 'Pay@HealthCare';
GRANT ALL PRIVILEGES ON sch_payment.* TO 'usr_pay'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

USE sch_auth;
CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256) NOT NULL,
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4000),
    autoapprove             VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token             BLOB,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token             BLOB,
    authentication_id VARCHAR(256),
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    BLOB,
    refresh_token     VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          BLOB,
    authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_code
(
    code           VARCHAR(256),
    authentication BLOB
);

CREATE TABLE IF NOT EXISTS users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    enabled  TINYINT(1),
    UNIQUE KEY unique_username (username)
);

CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(256) NOT NULL,
    authority VARCHAR(256) NOT NULL,
    PRIMARY KEY (username, authority)
);