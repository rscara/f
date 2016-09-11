create table orders (
    id BIGSERIAL primary key,
    clientId integer,
    productId integer,
    quantity integer,
    orderId integer,
    itemId integer
);