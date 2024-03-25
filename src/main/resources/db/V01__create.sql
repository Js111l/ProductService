-- Create a sequence for the category table
CREATE SEQUENCE category_id_seq START 1;

CREATE TABLE IF NOT EXISTS category (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('category_id_seq'),
    name VARCHAR(255),
    parent_category_id BIGINT
);

CREATE SEQUENCE product_id_seq START 1;

CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('product_id_seq'),
    name VARCHAR(255),
    description VARCHAR(255),
    created_at TIMESTAMP,
    start_date_of_sale DATE,
    end_date_of_sale DATE,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS category_product_map (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT category_id_fkey FOREIGN KEY (category_id) REFERENCES category (id)
);
