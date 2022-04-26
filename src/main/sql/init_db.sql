drop table if exists Product;
drop table if exists ProductCategory;
drop table if exists Supplier;

create table Product(id integer primary key not null,
                     name text not null,
                     description text not null,
                     supplier_id integer not null,
                     category_id integer not null);

create table ProductCategory(id integer primary key not null,
                             name text not null);

create table Supplier(id integer not null,
                      name text not null);