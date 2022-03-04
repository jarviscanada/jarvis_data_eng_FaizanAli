--tables
--DDL
CREATE TABLE PUBLIC.host_info
    (
        id                      SERIAL NOT NULL PRIMARY KEY,
        hostname                VARCHAR NOT NULL UNIQUE,
        cpu_number              INT NOT NULL,
        cpu_architecture        VARCHAR NOT NULL,
        cpu_model               VARCHAR NOT NULL,
        cpu_mhz                 FLOAT NOT NULL,
        l2_cache                INT NOT NULL,
        total_mem               INT NOT NULL,
        time_stamp              TIMESTAMP NOT NULL
    );

CREATE TABLE PUBLIC.host_usage
    (
        host_id                 INT NOT NULL,
        time_stamp              TIMESTAMP NOT NULL,
        memory_free             INT NOT NULL,
        cpu_idle                INT NOT NULL,
        cpu_kernel              INT NOT NULL,
        disk_io                 INT NOT NULL,
        disk_available          INT NOT NULL,
        FOREIGN KEY(host_id) REFERENCES host_info(id)
    );