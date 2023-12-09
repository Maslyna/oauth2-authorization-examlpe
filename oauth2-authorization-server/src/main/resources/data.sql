INSERT INTO t_users(
    user_id, account_non_expired, account_non_locked, credentials_non_expired, enabled, password, role, username)
VALUES
    ('581db8c6-968e-11ee-b9d1-0242ac120002',
     true, true, true, true,
     '$2a$12$CNwBKCpOaJIwRQBBf7yuFeyBrdIBH2b7/5Uq.lPg6CgD76/HdKOrK', --password: password
     'USER', 'jogndoe@mail.com');
