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

--inserção
insert into produtos values (1, 'mouse', 19.99, 'Mouse otico');
insert into produtos values (3, 'Video Games', 19.99, 'Xbox')
insert into produtos values (2, 'Filme', 19.99, 'Xmen')

insert into estoque values (1, ARRAY[(1, 'mouse', 19.99, 'Mouse otico')]::produto[]);
insert into estoque values (3, ARRAY[(2, 'Filme', 19.99, 'Xmen')]::produto[]);

-- selecao
select * from bebidas where cod_prod = 1 

--delecao
select * from vendas_produtos
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

