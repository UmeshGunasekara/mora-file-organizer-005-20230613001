----------------------------------------------------------------------------------
-- DATABSE
----------------------------------------------------------------------------------

DROP DATABASE IF EXISTS MORA_FILE_ORGANIZER_DB_V003;
CREATE DATABASE MORA_FILE_ORGANIZER_DB_V003;
USE MORA_FILE_ORGANIZER_DB_V003;

----------------------------------------------------------------------------------
-- TABLES
----------------------------------------------------------------------------------

-- MFO_DIRECTORY

DROP TABLE IF EXISTS MFO_SYSTEM_PROPERTYT;
CREATE TABLE MFO_SYSTEM_PROPERTY
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    system_prop_code Varchar(30) NOT NULL,
    system_prop_value Varchar(150) NOT NULL,
    system_prop_modify_ind Int NOT NULL,
    system_prop_value_separator Char(1),
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    PRIMARY KEY (id)
);

ALTER TABLE MFO_SYSTEM_PROPERTY ADD CONSTRAINT UNK_SYSTEM_PROPERTY_A_SYSTEM_PROPERTY_CODE
    UNIQUE (system_prop_code);

DROP TABLE IF EXISTS MFO_DIRECTORY;
CREATE TABLE MFO_DIRECTORY
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    directory_name Varchar(150),
    directory_full_path  Varchar(255),
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    directory_parent_id BINARY(16),
    PRIMARY KEY (id)
);

CREATE INDEX IX_DIRECTORY_A_DIRECTORY ON MFO_DIRECTORY (directory_parent_id);

ALTER TABLE MFO_DIRECTORY ADD CONSTRAINT UNK_DIRECTORY_A_DIRECTORY_FULL_PATH
    UNIQUE (directory_full_path);

ALTER TABLE MFO_DIRECTORY ADD CONSTRAINT FRK_DIRECTORY_A_DIRECTORY
    FOREIGN KEY (directory_parent_id) REFERENCES MFO_DIRECTORY (id)
        ON DELETE SET NULL ON UPDATE CASCADE;

DROP TABLE IF EXISTS MFO_FILE_CATEGORY;
CREATE TABLE MFO_FILE_CATEGORY
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    file_category_name Varchar(150) NOT NULL,
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    PRIMARY KEY (id)
);

ALTER TABLE MFO_FILE_CATEGORY ADD CONSTRAINT UNK_FILE_CATEGORY_A_FILE_CATEGORY_NAME
    UNIQUE (file_category_name);

INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 1, 'Video', 'Video Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 2, 'Image', 'Image Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 3, 'Audio', 'Audio Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 4, 'Document', 'Document Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 5, 'Setup', 'Setup Files can execute', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 6, 'Programming', 'Programming Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO mora_file_organizer_db_v003.mfo_file_category
(id, code, file_category_name, note, raw_create_user_account_id, raw_last_update_user_account_id, raw_create_date_time, raw_last_update_date_time, raw_last_update_log_id, raw_show_status, raw_update_status, raw_delete_status, raw_active_status, extra_01, extra_02, extra_03) VALUES
    (FN_UUID_TO_BIN(uuid()), 6, 'Other', 'Other Files', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, 0, 0, NULL, NULL, NULL);


DROP TABLE IF EXISTS MFO_FILE_FORMAT;
CREATE TABLE MFO_FILE_FORMAT
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    file_format Varchar(150) NOT NULL,
    file_format_description Varchar(255) NOT NULL,
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    PRIMARY KEY (id)
);

ALTER TABLE MFO_FILE_FORMAT ADD CONSTRAINT UNK_FILE_FORMAT_A_FILE_CATEGORY_NAME
    UNIQUE (file_format);


DROP TABLE IF EXISTS MFO_FILE_PROPERTY;
CREATE TABLE MFO_FILE_PROPERTY
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    file_property Varchar(150) NOT NULL,
    file_property_description Varchar(255) NOT NULL,
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    PRIMARY KEY (id)
);

ALTER TABLE MFO_FILE_PROPERTY ADD CONSTRAINT UNK_FILE_PROPERTY_A_FILE_CATEGORY_NAME
    UNIQUE (file_property);

DROP TABLE IF EXISTS MFO_FILE_FORMAT_PROPERTY;
CREATE TABLE MFO_FILE_FORMAT_PROPERTY
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    file_property_id BINARY(16),
    file_format_id BINARY(16),
    PRIMARY KEY (id)
);

ALTER TABLE MFO_FILE_PROPERTY ADD CONSTRAINT UNK_FILE_PROPERTY_A_FILE_CATEGORY_NAME
    UNIQUE (file_property);

DROP TABLE IF EXISTS MFO_FILE;
CREATE TABLE MFO_FILE
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    file_name Varchar(150) NOT NULL,
    file_full_path  Varchar(255) NOT NULL,
    file_extension Char(5),
    file_size Decimal(15,2),
    file_created_date_time Datetime,
    note Text,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    directory_id BINARY(16),
    file_category_id BINARY(16),
    PRIMARY KEY (id)
);

CREATE INDEX IX_FILE_A_DIRECTORY ON MFO_FILE (directory_id);

CREATE INDEX IX_FILE_A_FILE_CATEGORY ON MFO_FILE (file_category_id);

ALTER TABLE MFO_FILE ADD CONSTRAINT UNK_FILE_A_FILE_FULL_PATH
    UNIQUE (file_full_path);

ALTER TABLE MFO_FILE ADD CONSTRAINT FRK_FILE_A_DIRECTORY
    FOREIGN KEY (directory_id) REFERENCES MFO_DIRECTORY (id)
        ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE MFO_FILE ADD CONSTRAINT FRK_FILE_A_FILE_CATEGORY
    FOREIGN KEY (file_category_id) REFERENCES MFO_FILE_CATEGORY (id)
        ON DELETE SET NULL ON UPDATE CASCADE;

DROP TABLE IF EXISTS MFO_VIDEO_FILE_DATA;
CREATE TABLE MFO_VIDEO_FILE_DATA
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    note Text,
    video_frame_rate_per_second Decimal(5,2),
    audio_bit_rate Int,
    video_duration_seconds Int,
    audio_channels Int,
    audio_sampling_rate_hz Int,
    video_resolution_height Int,
    video_resolution_width Int,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    file_id BINARY(16),
    PRIMARY KEY (id)
);

CREATE INDEX IX_VIDEO_FILE_DATA_A_FILE ON MFO_FILE (file_id);

ALTER TABLE MFO_VIDEO_FILE_DATA ADD CONSTRAINT FRK_VIDEO_FILE_DATA_A_FILE
    FOREIGN KEY (file_id) REFERENCES MFO_FILE (id)
        ON DELETE SET NULL ON UPDATE CASCADE;

DROP TABLE IF EXISTS MFO_AUDIO_FILE_DATA;
CREATE TABLE MFO_AUDIO_FILE_DATA
(
    id BINARY(16) NOT NULL,
    code Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    note Text,
    video_frame_rate_per_second Decimal(5,2),
    audio_bit_rate Int,
    video_duration_seconds Int,
    audio_channels Int,
    audio_sampling_rate_hz Int,
    video_resolution_height Int,
    video_resolution_width Int,
    raw_create_user_account_id Int NOT NULL DEFAULT '0',
    raw_last_update_user_account_id Int NOT NULL DEFAULT '0',
    raw_create_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_date_time Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    raw_last_update_log_id Int NOT NULL DEFAULT '0',
    raw_show_status Int NOT NULL DEFAULT '0',
    raw_update_status Int NOT NULL DEFAULT '0',
    raw_delete_status Int NOT NULL DEFAULT '0',
    raw_active_status Int NOT NULL DEFAULT '0',
    extra_01 Text,
    extra_02 Text,
    extra_03 Text,
    file_id BINARY(16),
    PRIMARY KEY (id)
);

CREATE INDEX IX_VIDEO_FILE_DATA_A_FILE ON MFO_FILE (file_id);

ALTER TABLE MFO_VIDEO_FILE_DATA ADD CONSTRAINT FRK_VIDEO_FILE_DATA_A_FILE
    FOREIGN KEY (file_id) REFERENCES MFO_FILE (id)
        ON DELETE SET NULL ON UPDATE CASCADE;

----------------------------------------------------------------------------------
-- STORED PROCEDURE
----------------------------------------------------------------------------------

-- SP_ADD_USER_CATEGORY --

CREATE DEFINER='root'@'localhost' PROCEDURE 'SP_ADD_USER_CATEGORY'(category_name varchar(255), is_default Int)
BEGIN

DECLARE v1_id int DEFAULT 0;

  /*CUSTOM SQLEXCEPTION*/
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;  /* rollback any changes made in the transaction*/
RESIGNAL;  /* raise again the sql exception to the caller*/
END;

START TRANSACTION;

INSERT INTO SB_USER_CATEGORY (
    user_category_name,
    user_category_code,
    user_category_is_default,
    raw_last_update_date_time,
    raw_last_update_log_id,
    update_user_account_id,
    raw_show_status,
    raw_update_status,
    raw_delete_status,
    raw_active_status)
VALUES (category_name, 1, is_default, now(), 1, 1, 1, 1, 1, 1);

SET v1_id = LAST_INSERT_ID();

	IF (v1_id = 0 OR v1_id IS NULL) THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "SB_USER_CATEGORY details are wrong.!", MYSQL_ERRNO = 300;
END IF;

--   ROLLBACK;
COMMIT;
SELECT v1_id;
-- SELECT 'ok';
/*  COMMIT;*/
END

call SP_ADD_USER_CATEGORY("Test Call", 1)

----------------------------------------------------------------------------------
-- FUNCTIONS
----------------------------------------------------------------------------------

SET GLOBAL log_bin_trust_function_creators = 1;

-- FN_GET_ORDERED_UUID --

CREATE DEFINER= `root`@`localhost` FUNCTION `FN_GET_ORDERED_UUID`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
RETURN UNHEX(CONCAT(SUBSTR(uuid, 15, 4),SUBSTR(uuid, 10, 4),SUBSTR(uuid, 1, 8),SUBSTR(uuid, 20, 4),SUBSTR(uuid, 25)));
END

select FN_GET_ORDERED_UUID(uuid())

-- FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS`() RETURNS bigint
BEGIN
  DECLARE milliseconds BIGINT DEFAULT 0;

SELECT CONV(
               CONCAT(
                       SUBSTRING(uid,16,3),
                       SUBSTRING(uid,10,4),
                       SUBSTRING(uid,1,8)),
               16,10)
           DIV 10000
	  - (141427 * 24 * 60 * 60 * 1000) INTO milliseconds
FROM (SELECT UUID() uid) AS alias;

RETURN milliseconds;
END

select FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS()

-- FN_UUID_TO_BIN --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_UUID_TO_BIN`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
RETURN
    UNHEX(
            CONCAT(
                    SUBSTR(uuid, 15, 4),
                    SUBSTR(uuid, 10, 4),
                    SUBSTR(uuid, 1, 8),
                    SUBSTR(uuid, 20, 4),
                    SUBSTR(uuid, 25)));
END

-- FN_UUID_FROM_BIN --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_UUID_FROM_BIN`(`bin` binary(16)) RETURNS binary(36)
BEGIN
RETURN
    CONCAT_WS('-',
              HEX(SUBSTR(bin, 5, 4)),
              HEX(SUBSTR(bin, 3, 2)),
              HEX(SUBSTR(bin, 1, 2)),
              HEX(SUBSTR(bin, 9, 2)),
              HEX(SUBSTR(bin, 11)));
END


---------------------
-- Special
---------------------

SHOW VARIABLES LIKE '%timeout%';

SHOW VARIABLES LIKE '%connect%';