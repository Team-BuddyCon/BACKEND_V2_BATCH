SELECT * FROM users;
INSERT INTO users
VALUES (null, null, null, null, 1, "email", null, "user1"),
       (null, null, null, null, 2, "email", null, "user2"),
       (null, null, null, null, 3, "email", null, "user3"),
       (null, null, null, null, 4, "email", null, "user4"),
       (null, null, null, null, 5, "email", null, "user5"),
       (null, null, null, null, 6, "email", null, "user6"),
       (null, null, null, null, 7, "email", null, "user7")
;
SELECT * FROM notification_setting;
INSERT INTO notification_setting
VALUES (null, null, null, true, true, true, true, true, true, 1),
       (null, null, null, false, true, true, true, true, true, 2),
       (null, null, null, true, false, true, true, true, true, 3),
       (null, null, null, true, true, false, true, true, true, 4),
       (null, null, null, true, true, true, false, true, true, 5),
       (null, null, null, true, true, true, true, false, true, 6),
       (null, null, null, true, true, true, true, true, false, 7)
;

SELECT * FROM gifticon;
INSERT INTO gifticon
VALUES (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 1),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 1),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 1),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 1),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 1),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 2),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 2),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 2),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 2),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 2),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 3),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 3),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 3),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 3),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 3),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 4),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 4),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 4),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 4),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 4),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 5),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 5),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 5),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 5),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 5),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 6),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 6),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 6),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 6),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 6),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 0 DAY), "STARBUCKS", "CAFE", "image", null, "0일 후", false, 7),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 1 DAY), "STARBUCKS", "CAFE", "image", null, "1일 후", false, 7),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 3 DAY), "STARBUCKS", "CAFE", "image", null, "3일 후", false, 7),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 7 DAY), "STARBUCKS", "CAFE", "image", null, "7일 후", false, 7),
       (null, null, null, DATE_ADD(NOW(), INTERVAL + 14 DAY), "STARBUCKS", "CAFE", "image", null, "14일 후", false, 7)
;

SELECT g.gifticon_id,
       g.expire_date,
       g.name,
       g.used,
       g.user_id,
       ns.notification_setting_id,
       ns.activated,
       ns.fourteen_days_before,
       ns.seven_days_before,
       ns.three_days_before,
       ns.one_day_before,
       ns.the_day
FROM gifticon g
         INNER JOIN notification_setting ns
                    ON ns.user_id = g.user_id
WHERE g.used = false
    AND ns.activated = true
    AND (
        (ns.fourteen_days_before = true AND g.expire_date = DATE(DATE_ADD(NOW(), INTERVAL + 14 DAY)))
        OR (ns.seven_days_before = true AND g.expire_date = DATE(DATE_ADD(NOW(), INTERVAL + 7 DAY)))
        OR (ns.three_days_before = true AND g.expire_date = DATE(DATE_ADD(NOW(), INTERVAL + 3 DAY)))
        OR (ns.one_day_before = true AND g.expire_date = DATE(DATE_ADD(NOW(), INTERVAL + 1 DAY)))
        OR (ns.the_day = true AND g.expire_date = DATE(NOW()))
    )
;