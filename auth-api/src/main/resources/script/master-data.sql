USE sch_auth;

-- The encrypted client_secret it `secret`
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types,
                                  authorities, access_token_validity)
VALUES ('client', '{bcrypt}$2a$10$HCAzE/AaRy8PvB/yML8eneoeXlt72sCRo5UsutuFum68dJLG.dYLi',
        'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

-- The encrypted password is `pass`
INSERT INTO users (username, password, enabled)
VALUES ('api', '{bcrypt}$2a$10$kwa1yk4s9c8LZK8P2r9eEuhLUVpCksc/w74WhqiEJbXiX9A10mnVS', 1),
       ('hospital-api', '{bcrypt}$2a$10$/yO2Klrp0SFmEmyhFNLqWOJs/xsD383mOX4qWuIt1h/I7Wx0fsiUO', 1),
       ('doctor-api', '{bcrypt}$2a$10$T7FtNhiCDgJBI/aoXpxImO8Xo/CqUUKswTf0i0SjiTdOAGLjuy64.', 1),
       ('patient-api', '{bcrypt}$2a$10$Rnr.bedCwurfeGM1MfK0ZevTpcHCf6m/ohulr.tJj1aY/T3uFeCzC', 1),
       ('appointment-api', '{bcrypt}$2a$10$8qo4jVZXWXYal/khWVkycu6hMSN5FNuCrUfZoMm8KCl4munlJ.Ml.',
        1),
       ('payment-api', '{bcrypt}$2a$10$9WchzVftSRp6vlGDmgVMlOirRHLlHdZgRgdNrMGfIfa5ljDK.GWTO', 1);

INSERT INTO `authorities` (username, authority)
VALUES ('api', 'ROLE_HOSP'),
       ('api', 'ROLE_DOC'),
       ('api', 'ROLE_PT'),
       ('api', 'ROLE_APPT'),
       ('api', 'ROLE_PAY'),
       ('hospital-api', 'ROLE_HOSP'),
       ('doctor-api', 'ROLE_DOC'),
       ('patient-api', 'ROLE_PT'),
       ('appointment-api', 'ROLE_APPT'),
       ('payment-api', 'ROLE_PAY');
