create domain check_preco as decimal(10,2) check (value > 0);

--Tipos
create type venda as(
	cod_venda int,
	data date,
	produtos_vendidos produto[]	--lista e associação
);

create type produto as (
	cod_prod int,
	nome varchar(30),
	valor check_preco, --Dominio
	descricao varchar(200)
);

--Tabelas
create table vendas of venda(
	cod_venda primary key
)

create table produtos of produto(
	cod_prod primary key
);

create table bebidas(
	cod_bebida int primary key,
	ml float,
	marca varchar(30)
) inherits(produtos);

create table salgados(
	cod_salg int primary key,
	kg float
) inherits(produtos);

----------inserção---------------------------------

select * from produtos
select * from salgados

insert into bebidas values (1, 'Refrigerante', 5, 'Coca-Cola', 1, 2);
insert into bebidas values (2, 'Cachaça', 5, 'Pitu', 2, 1);

-- teste
insert into bebidas values (3, 'Agua', 5, 'Mineral', 2, 1);
insert into salgados values (1, 'Coxinha', 3, 'Cantina', 2, 5)
insert into salgados values (2, 'Empada', 3, 'Cantina', 3, 1)

-- inserindo em tabela que usa array
insert into vendas values (101, '01-05-2005', ARRAY[(1, 'Refrigerante', 5, 'Coca-Cola')]::produto[])
--teste
insert into vendas values (102, '01-05-2006', ARRAY[(2, 'Cachaca', 5, 'Pitu')]::produto[])


-------------- selecao---------------------
select * from bebidas where cod_prod = 1 
select * from vendas

select vendas.produtos_vendidos[1].nome from vendas where cod_venda = 101

select vendas.produtos_vendidos[1].valor from vendas where cod_venda = 101



--------------update------------------
update vendas set produtos[1].nome = '' where cod = 2 

--delecao
select * from vendas
select * from produtos;

create type pessoa as (
	id int,
	nome varchar(30),
	produtos_comprados 
)

drop table produtos;
drop table vendas;
drop table produtos;
drop table bebidas;
drop type vendas;
drop type produto;




----------------------------------------------------antigo---------------------------------------------------
create domain check_preco as decimal(10,2) check (value > 0);

create type armazem as(
	cod int,
	produtos produto[100]	--lista e associação
);

create type produto as (
	cod_prod int,
	nome varchar(30),
	valor check_preco, --Dominio
	descricao varchar(200)
);


create table estoque of armazem(
	cod primary key
)

create table produtos of produto(
	cod_prod primary key
);


-- inserção
insert into produtos values (1, 'mouse', 19.99, 'Mouse otico');
insert into produtos values (3, 'Video Games', 19.99, 'Xbox')
insert into produtos values (2, 'Filme', 19.99, 'Xmen')

insert into estoque values (1, ARRAY[(1, 'mouse', 19.99, 'Mouse otico')]::produto[]);
insert into estoque values (3, ARRAY[(2, 'Filme', 19.99, 'Xmen')]::produto[]);

-- selecao
select estoque.produtos[1].nome from estoque where cod = 1 
select estoque.produtos[1].valor from estoque where estoque.produtos[1].nome = 'Vidro' 

--update
update estoque set produtos[1].nome = 'Vidro' where cod = 2 
update estoque set produtos[1].valor = 30.12 where cod = 1

--delecao
delete from estoque where estoque.produtos[1].cod_prod = 1

select * from estoque
select * from produtos;


--
create type pessoa as (
	id int,
	nome varchar(30),
	produtos_comprados 
)
