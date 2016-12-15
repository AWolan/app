INSERT INTO t_person (id, company_name, first_name, last_name, is_company)
  VALUES (1, NULL , 'Test', 'User', 0);

INSERT INTO t_account_number (id, iban, bank_number, account_number, control_sum)
  VALUES (1, 'PL', 1002, 1, 12);

INSERT INTO t_account (id, number_id, owner_id, currency)
  VALUES (1, 1, 1, 'PLN');
INSERT INTO t_account (id, number_id, owner_id, currency)
  VALUES (2, NULL , 1, 'PLN');

INSERT INTO t_category (id, name)
  VALUES (1, 'C1');

INSERT INTO t_payment (id, group_id, source_id, target_id, value, currency, name, details, execution_date, plan_date)
  VALUES (1, NULL, 1, 2, 100, 'PLN', 'P1', '', '2016-11-29', NULL);

INSERT INTO t_payment_category (payment_id, category_id)
  VALUES (1, 1);

INSERT INTO t_payment_filter (id, owner_id, name, direction, status)
  VALUES (1, 1, 'F1', 'BOTH', 'BOTH');

INSERT INTO t_payment_filter_category (payment_filter_id, category_id)
  VALUES (1, 1);

