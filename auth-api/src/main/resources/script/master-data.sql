USE sch_auth;

-- The encrypted client_secret it `secret`
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types,authorities, access_token_validity) VALUES ('client', '{bcrypt}$2a$10$HCAzE/AaRy8PvB/yML8eneoeXlt72sCRo5UsutuFum68dJLG.dYLi','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300),('hospital-api','{bcrypt}$2a$10$FYTANNqzZqI7ZzS5eiV/Fu3JbZkrjEMr9/IXewyY7cus1MpajHHvq','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300),('doctor-api','{bcrypt}$2a$10$qhsUMd3OfEn1xRk3osB1Iu8jwqnU9xKLfdS8uJA15mEJxHJxoLRvO','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300),('patient-api','{bcrypt}$2a$10$XNjMMXPDiMk2KadFwZbl2e9LBcv8PDt5qsHsL6mYh6lr2qWMboKhK','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300),('appointment-api','{bcrypt}$2a$10$8APcZGMyrr9w7CxZjjQtme/MpzQu0KtNU0mHbQ1cI2TSOxn3kFlwG','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300),('payment-api','{bcrypt}$2a$10$aZOYlVmToSFlbjrOGSsY6uq/.MwfRG7PcpKvSvX.wv0Eh/SoUiNYe','read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

-- The encrypted password is `pass`
INSERT INTO users (username, password, enabled) VALUES ('api', '{bcrypt}$2a$10$kwa1yk4s9c8LZK8P2r9eEuhLUVpCksc/w74WhqiEJbXiX9A10mnVS', 1),('hospital-api', '{bcrypt}$2a$10$/yO2Klrp0SFmEmyhFNLqWOJs/xsD383mOX4qWuIt1h/I7Wx0fsiUO', 1),('doctor-api', '{bcrypt}$2a$10$T7FtNhiCDgJBI/aoXpxImO8Xo/CqUUKswTf0i0SjiTdOAGLjuy64.', 1),('patient-api', '{bcrypt}$2a$10$Rnr.bedCwurfeGM1MfK0ZevTpcHCf6m/ohulr.tJj1aY/T3uFeCzC', 1),('appointment-api', '{bcrypt}$2a$10$8qo4jVZXWXYal/khWVkycu6hMSN5FNuCrUfZoMm8KCl4munlJ.Ml.',1),('payment-api', '{bcrypt}$2a$10$9WchzVftSRp6vlGDmgVMlOirRHLlHdZgRgdNrMGfIfa5ljDK.GWTO', 1);

INSERT INTO `authorities` (username, authority)VALUES ('api', 'ROLE_HOSP'),('api', 'ROLE_DOC'),('api', 'ROLE_PT'),('api', 'ROLE_APPT'),('api', 'ROLE_PAY'),('hospital-api', 'ROLE_HOSP'),('doctor-api', 'ROLE_DOC'),('patient-api', 'ROLE_PT'),('appointment-api', 'ROLE_APPT'),('payment-api', 'ROLE_PAY');

INSERT INTO user_type VALUES (1, 'System-Admin'), (2, 'Hospital-Admin'), (3, 'Doctor'), (4, 'Patient');

-- update existing users as System Admins
UPDATE users SET type = 1 WHERE 1 = 1;;

-- Authorities for System-Admin
INSERT INTO user_type_authority(type, authority) VALUES(1, 'ROLE_ADD_HOSP'),(1, 'ROLE_GET_ALL_HOSP'),(1, 'ROLE_GET_HOSP'),(1, 'ROLE_UPDATE_HOSP'),(1, 'ROLE_GET_ROOM'),(1, 'ROLE_GET_ALL_DOC'),(1, 'ROLE_GET_DOC'),(1, 'ROLE_REG_PT'),(1, 'ROLE_GET_ALL_PT'),(1, 'ROLE_GET_PT'),(1, 'ROLE_UPDATE_PT'),(1, 'ROLE_DELETE_PT'),(1, 'ROLE_GET_DOC_APPT'),(1, 'ROLE_GET_PT_APPT'),(1, 'ROLE_GET_HOSP_APPT'),(1, 'ROLE_GET_APPT'),(1, 'ROLE_UPDATE_APPT'),(1, 'ROLE_CANCEL_APPT'),(1, 'ROLE_GET_ALL_PAY'),(1, 'ROLE_GET_APPT_PAY'),(1, 'ROLE_REFUND_PAY'),(1, 'ROLE_GET_PAY');

-- Authorities for Hospital-Admin
INSERT INTO user_type_authority(type, authority) VALUES(2, 'ROLE_GET_HOSP'),(2, 'ROLE_UPDATE_HOSP'),(2, 'ROLE_ADD_ROOM'),(2, 'ROLE_GET_ROOM'),(2, 'ROLE_UPDATE_ROOM'),(2, 'ROLE_DELETE_ROOM'),(2, 'ROLE_REG_DOC'),(2, 'ROLE_GET_ALL_DOC'),(2, 'ROLE_GET_DOC'),(2, 'ROLE_UPDATE_DOC'),(2, 'ROLE_DELETE_DOC'),(2, 'ROLE_REG_PT'),(2, 'ROLE_GET_ALL_PT'),(2, 'ROLE_GET_PT'),(2, 'ROLE_UPDATE_PT'),(2, 'ROLE_DELETE_PT'),(2, 'ROLE_GET_DOC_APPT'),(2, 'ROLE_GET_PT_APPT'),(2, 'ROLE_GET_HOSP_APPT'),(2, 'ROLE_GET_APPT'),(2, 'ROLE_UPDATE_APPT'),(2, 'ROLE_CANCEL_APPT'),(2, 'ROLE_GET_ALL_PAY'),(2, 'ROLE_GET_APPT_PAY'),(2, 'ROLE_REFUND_PAY'),(2, 'ROLE_GET_PAY');

-- Authorities for Doctor
INSERT INTO user_type_authority(type, authority) VALUES(3,'ROLE_REG_DOC'),(3,'ROLE_GET_DOC'),(3,'ROLE_GET_ALL_HOSP'),(3,'ROLE_GET_HOSP'),(3,'ROLE_GET_ROOM'),(3,'ROLE_UPDATE_DOC'),(3,'ROLE_REG_PT'),(3,'ROLE_GET_ALL_PT'),(3,'ROLE_GET_PT'),(3,'ROLE_UPDATE_PT'),(3,'ROLE_GET_DOC_APPT'),(3,'ROLE_GET_PT_APPT'),(3,'ROLE_GET_APPT'),(3,'ROLE_GET_APPT_PAY');

-- Authorities for Patient
INSERT INTO user_type_authority(type, authority) VALUES(4, 'ROLE_GET_ALL_HOSP'),(4, 'ROLE_GET_HOSP'),(4, 'ROLE_GET_ROOM'),(4, 'ROLE_GET_ALL_DOC'),(4, 'ROLE_GET_DOC'),(4, 'ROLE_REG_PT'),(4, 'ROLE_GET_PT'),(4, 'ROLE_MAKE_APPT'),(4, 'ROLE_GET_PT_APPT'),(4, 'ROLE_MAKE_PAY'),(4, 'ROLE_GET_APPT_PAY');

-- DELETE FROM `authorities` WHERE username IN ('api', 'appointment-api', 'doctor-api', 'hospital-api', 'patient-api', 'payment-api');

INSERT INTO `authorities` SELECT DISTINCT 'api', authority FROM user_type_authority;
INSERT INTO `authorities` SELECT DISTINCT 'appointment-api', authority FROM user_type_authority;
INSERT INTO `authorities` SELECT DISTINCT 'doctor-api', authority FROM user_type_authority;
INSERT INTO `authorities` SELECT DISTINCT 'hospital-api', authority FROM user_type_authority;
INSERT INTO `authorities` SELECT DISTINCT 'patient-api', authority FROM user_type_authority;
INSERT INTO `authorities` SELECT DISTINCT 'payment-api', authority FROM user_type_authority;