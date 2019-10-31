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
select * from bebidas

insert into bebidas values (1, 'Refrigerante', 5, 'Refrigerante de cola', 1, 200, 'Coca-Cola');
insert into bebidas values (2, 'Cachaça', 3, 'Cachaça de bebo', 2, 1000, 'Pitu');

-- teste
insert into bebidas values (3, 'Agua', 2, 'Agua mineral', 3, 500, 'Cristalina');
insert into salgados values (4, 'Empada', 3.5, 'Salgado de frango', 1, 0.2);
insert into salgados values (5, 'Coxinha', 2.5, 'Salgado de frango', 2, 0.3);

-- inserindo em tabela que usa array
insert into vendas values (1, '01-05-2005', ARRAY[(2, 'Cachaca', 3, 'Cachaca de bebo')::produto, 
													(1, 'Refrigerante', 5, 'Refrigerante de cola' )::produto, 
													(4, 'Empada', 3.5, 'Salgado de frango')::produto]::produto[]);
--teste
insert into vendas values (2, '01-05-2006', ARRAY[(2, 'Cachaca', 5, 'Pitu')]::produto[])

-------------- selecao---------------------
select * from bebidas where cod_prod = 1 
select * from vendas

select vendas.produtos_vendidos[1].nome from vendas where cod_venda = 101

select vendas.produtos_vendidos[1].valor from vendas where cod_venda = 101

--------------update------------------
update vendas set produtos_vendidos[2].nome = '' where produtos_vendidos[2].cod_prod = 1

-------------delecao------------------
delete from vendas where cod_venda = 1

select * from vendas
select * from produtos;

drop table produtos;
drop table vendas;
drop table produtos;
drop table bebidas;
drop type vendas;
drop type produto;