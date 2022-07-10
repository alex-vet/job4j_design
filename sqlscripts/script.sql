create database books;
create table book (
    id serial primary key,
    name text,
	page_count integer,
	read boolean
);
insert into book (name,page_count,read) values ('Alice''s Adventures in Wonderland',96,false);
select * from book;
update book set read=true;
delete from book where id=1;