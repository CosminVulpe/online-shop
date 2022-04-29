drop table if exists public.Product;
drop table if exists public.ProductCategory;
drop table if exists public.Supplier;
drop table if exists public.Users;
drop table if exists public.User_info;

create table public.Product(id integer primary key not null,
                            name text not null,
                            description text not null,
                            price decimal not null,
                            currency text not null,
                            supplier_id integer not null,
                            category_id integer not null);

create table public.ProductCategory(id integer primary key not null,
                                    name text not null,
                                    departament text not null,
                                    description text not null);

create table public.Supplier(id integer not null,
                             name text not null,
                             description text not null);

create table public.Users(id integer primary key not null,
                             username text not null,
                             email text not null,
                             password text not null);

create table public.User_info(id integer primary key not null,
                              first_name text not null,
                              last_name text not null,
                              email text not null,
                              phone_number text not null,
                              country text not null,
                              city text not null,
                              address text not null,
                              zipcode integer not null,
                              country_ship text not null,
                              city_ship text not null,
                              address_ship text not null,
                              zipcode_ship integer not null,
                              user_id integer not null);

insert into public.productcategory(id, name, departament, description)
values(1, 'Tablet', 'Hardware','A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
(2, 'SmartPhones', 'Hardware','A smartphone is a handheld electronic device that provides a connection to a cellular network and the internet.'),
(3, 'Accessories', 'Others','All products needs extra accessories for a better aesthetic aspect'),
(4, 'Watches', 'Others', 'Handy watches for handy people');

insert into supplier(id, name, description)
values(1,'Amazon','Digital content and services'),
(2,'Lenovo', 'Computers'),
(3,'Motorola','Old but Gold phone Company'),
(4, 'Apple', 'EveryThingInHere');

insert into product(id, name, description, price, currency,supplier_id,category_id)
values(1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',
       49.9, 'USD', 1, 1),
       (2,'Lenovo IdeaPad Miix 700','Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',
        479, 'USD', 2,1),
       (3,'Amazon Fire HD 8','Amazon''s latest Fire HD 8 tablet is a great value for media consumption.',
        89,'USD', 1,1),
       (4,'Iphone 12', 'Tim Cook latest iphone.', 100, 'USD', 4,2);
