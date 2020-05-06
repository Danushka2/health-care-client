INSERT INTO sch_payment.payment (id, amount, appointment_id, pay_on, reference, stripe_intent_id, status) VALUES (1, 2200.00, 1, '2020-05-05 16:28:02', '5A4E480BF53B4', 'pi_1GfOTLGpCPBiNXLXfjiJFmtM', 2);
INSERT INTO sch_payment.payment (id, amount, appointment_id, pay_on, reference, stripe_intent_id, status) VALUES (2, 2250.00, 2, '2020-05-05 16:29:13', '5A4E489AC8A76', 'pi_1GfOUVGpCPBiNXLXXqZGBIks', 1);
INSERT INTO sch_payment.payment (id, amount, appointment_id, pay_on, reference, stripe_intent_id, status) VALUES (3, 2350.00, 3, '2020-05-05 21:24:14', '5A4E8A67ABF07', 'pi_1GfT5yGpCPBiNXLXuNcSJ1eT', 2);

INSERT INTO sch_payment.refund_payment (id, reason, refund_on, refund_ref, payment_id) VALUES (1, 'Doctor cannot consult', '2020-05-05 16:28:32', 're_1GfOTrGpCPBiNXLXIn92UDNO', 1);
INSERT INTO sch_payment.refund_payment (id, reason, refund_on, refund_ref, payment_id) VALUES (2, 'Doctor cannot consult', '2020-05-05 21:24:33', 're_1GfT6KGpCPBiNXLX7EvECG66', 3);