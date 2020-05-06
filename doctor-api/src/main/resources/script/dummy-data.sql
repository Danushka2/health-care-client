INSERT INTO sch_doctor.doctor (id, email, name, specialization, status, tel, user_id) VALUES (1, 'liyanarachchi@gmail.com', 'Dr. A.S.L Liyanarachchi 1', 'Cardiologists', 'Active', '0715123698', 9);

INSERT INTO sch_doctor.doctor_hospital (id, doctor_free, hospital_id, doctor_id) VALUES (1, 1500.00, 1, 1);

INSERT INTO sch_doctor.doctor_session (id, session_from, room_id, status, session_to, doctor_id) VALUES (1, '20:00:00', 1, 1, '21:00:00', 1);