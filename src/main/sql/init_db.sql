drop table if exists public.Product;
drop table if exists public.ProductCategory;
drop table if exists public.Supplier;

create table public.Product(id integer primary key not null,
                            name text not null,
                            description text not null,
                            supplier_id integer not null,
                            category_id integer not null);
create table public.ProductCategory(id integer primary key not null,
                                    name text not null);
create table public.Supplier(id integer not null,
                             name text not null);