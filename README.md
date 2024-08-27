Executar projeto

docker-compose up --build

Endpoints do projeto
- /livros
- /usuarios
- /emprestimos
- /indicacoes

Exemplos JSON

- Cadastro Livros
{
	"titulo":"livro1",
	"author":"zé",
	"isbn":"9783-16-148410-0",
	"categoria":"NACIONAL"
}   --

-Cadastro usuário

{
	"nome":"fulano",
	"email":"teste2@teste.com",
	"telefone":{
		"numero":"12345"
	}
}   --


-- Empréstimo

{
	"id_usuario": 1,
	"id_livro": 4
} --


--Indicações

{
	"id_usuario":1
}
