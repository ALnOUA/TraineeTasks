/*create table if not exists users (
  user_id int primary key auto_increment,
  username varchar(32) not null unique
);

create table if not exists orders (
  order_id INTEGER primary key auto_increment,
  user_id int not null,
  order_date timestamp not null,
  order_result boolean default false not null,
  foreign key (user_id) references users(user_id)
);*/


create table if not exists products (
  product_id int primary key auto_increment,
  name varchar(36) not null,
  price long,
  use_before_date timestamp,
  expiration_days integer
);




create table users
(
  user_id int primary key auto_increment,
  username varchar(36) null
);
create table orders
(
  order_id int primary key auto_increment,
  user_id int not null,
  product_id int not null,
  order_date TIMESTAMP null,
  order_result boolean,
  constraint products_orders
    foreign key(product_id) references products(product_id),
  constraint users_orders
    foreign key (user_id) references users (user_id)
);

create table orders_info
(
  info_id int primary key auto_increment,
  order_id int not null,
  order_date TIMESTAMP null,
  order_result boolean,
  constraint info_orders
    foreign key (order_id) references orders (order_id)
);


DELIMITER //
CREATE TRIGGER order_info
  AFTER INSERT
  ON orders FOR EACH ROW

BEGIN
  INSERT INTO orders_info
  ( order_id,
    order_date,
    order_result)
  VALUES
  ( NEW.order_id,
    SYSDATE(),
    NEW.order_result );
END; //
DELIMITER ;


DROP PROCEDURE IF EXISTS SHOW_HISTORY;

DELIMITER //

CREATE PROCEDURE SHOW_HISTORY(IN id INT)

BEGIN
  DECLARE sum INT;

   SELECT u.user_id,
          o.product_id,
          p.price
   FROM orders as o
      inner join users u on o.user_id = u.user_id
      inner join products p on o.product_id = p.product_id
      where u.user_id=id;
      call GET_SUM(id);

END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GET_SUM(IN id INT)

BEGIN
  SELECT sum(p.price)
  FROM orders as o
         inner join users u on o.user_id = u.user_id
         inner join products p on o.product_id = p.product_id
  where u.user_id=id;
END//

DELIMITER ;





