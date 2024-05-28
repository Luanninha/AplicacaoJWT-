# Descrição

Construa uma aplicação que exponha uma api web que recebe por parametros um JWT (string) e verifica se é valida conforme regras abaixo:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

#  Definição
Input: Uma token JWT (string).  
Output: Um boolean indicando se a valido ou não conforme regras descritas acima.

#  Continue a partir desse ponto...
Foi disponibilizada uma aplicação inicial para apoiar na conclusão dessa tarefa. Você deve evoluir o código a partir da classe **TokenController**

![token](/img/token.png)

Para executar a aplicação, você pode utilizar qualquer IDE e ela está disponível executando um POST http://localhost:8080/validate

Também foi disponibilizado uma coleção no postman com exemplos de chamadas dos casos de teste exemplificados na massa de teste descrita abaixo.

![postman](/img/postman.png)



# Massa de teste 

### Caso 1:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
```
Saida:
```
verdadeiro
```
Justificativa:
Abrindo o JWT, as informações contidas atendem a descrição:
```json
{
  "Role": "Admin",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
```

### Caso 2:
Entrada:
```
eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
```
Saida:
```
falso
```
Justificativa:
JWT invalido

### Caso 3:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs
```
Saida:
```
falso
```
Justificativa:
Abrindo o JWT, a Claim Name possui caracter de números
```json
{
  "Role": "External",
  "Seed": "72341",
  "Name": "M4ria Olivia"
}
```

### Caso 4:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY
```
Saida:
```
falso
```
Justificativa:
Abrindo o JWT, foi encontrado mais de 3 claims.
```json
{
  "Role": "Member",
  "Org": "BR",
  "Seed": "14627",
  "Name": "Valdir Aranha"
}
```
## Pontos que daremos maior atenção

- Testes de unidade / integração
- Abstração, acoplamento, extensibilidade e coesão
- Design de API
- SOLID
- Documentação da solução no *README* 
- Commits realizados durante a construção

## Pontos que não iremos avaliar

- docker file
- scripts ci/cd
- coleções do postman ou ferramentas para execução
- expor a api em algum provedor de cloud (aws, azure...)

### Sobre a documentação

Nesta etapa do processo seletivo queremos entender as decisões por trás do código, portanto é fundamental que o *README* tenha algumas informações referentes a sua solução.

Algumas dicas do que esperamos ver são:

- Instruções básicas de como executar o projeto;
- Detalhes da descrição do metodo
- Caso algo não esteja claro e você precisou assumir alguma premissa, quais foram e o que te motivou a tomar essas decisões.

## Como esperamos receber sua solução

Esta etapa é eliminatória, e por isso esperamos que o código reflita essa importância.

Se tiver algum imprevisto, dúvida ou problema, por favor entre em contato com a gente, estamos aqui para ajudar.

Nos envie o *link de um repo público* com a sua solução

Validação de Tokens JWT

Descrição
Este projeto consiste em uma aplicação que valida tokens JWT (JSON Web Tokens) de acordo com um conjunto de regras. 
É uma API REST que recebe um token JWT como entrada e retorna um booleano indicando se o token é válido ou não, seguindo as seguintes regras:

O token deve ser válido.
O token deve conter apenas três reivindicações (Name, Role e Seed).
A reivindicação Name não pode conter caracteres numéricos.
A reivindicação Role deve ser um dos seguintes valores: Admin, Member ou External.
A reivindicação Seed deve ser um número primo.
O comprimento máximo da reivindicação Name é de 256 caracteres.

Tecnologias Utilizadas

Java 17
Spring Boot
JJWT (Java JWT)

Instruções para Execução

Clone o repositório:https://github.com/Luanninha/AplicacaoJWT-DesafioItau

git clone 

Configuração do projeto:


Inicie a classe Application, que dara inicio ao Spring boot 

Testes:

Utilize a coleção do Postman fornecida para realizar as chamadas de teste:

URL para validação: http://localhost:8080/validate

Métodos Implementados

Validação do Token

Os métodos de validação do token realizam as seguintes verificações:

Verifica se o token é nulo ou vazio.
Cria uma chave secreta para verificar o token.
Analisa e valida o token JWT, obtendo as três claims ("Name", "Role" e "Seed").
Valida se as claims são nulas, têm mais de 256 caracteres ou contêm números.
Valida a claim "Role" - "Admin", "Member" ou "External".
Valida se a claim "Seed" é um número primo.




