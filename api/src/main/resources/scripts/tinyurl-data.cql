-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS tinyurl WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };


CREATE TABLE IF NOT EXISTS tinyurl.new_short_code
(
    short_code       text PRIMARY KEY,
    created_timestamp timestamp
);

CREATE TABLE IF NOT EXISTS tinyurl.used_short_code
(
    short_code       text PRIMARY KEY,
    created_timestamp timestamp
);

CREATE TABLE IF NOT EXISTS tinyurl.url
(
    short_code       text PRIMARY KEY,
    original_url     text,
    created_timestamp timestamp
);

insert into tinyurl.url (short_code, original_url, created_timestamp)
VALUES('123456','https://hemantkumar.dev', '2025-09-22');