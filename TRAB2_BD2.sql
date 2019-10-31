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
