CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    image_url TEXT,
    filename VARCHAR(255),
    filetype VARCHAR(100),
    filesize BIGINT,
    width INTEGER,
    height INTEGER
);
