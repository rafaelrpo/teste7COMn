BANCO DE DADOS:

PARTE E - SQL e Banco de Dados (PostgreSQL)(30 pts)

Exemplo 1 – Propósito e correção

SELECT u.username, COUNT(a.id) AS total_logins
FROM usuarios u
LEFT JOIN auditoria_login a ON a.usuario_id = u.id AND a.sucesso = true
WHERE u.perfil = 'ADMIN'
GROUP BY u.username
HAVING COUNT(a.id) > 5
ORDER BY total_logins DESC;

Perguntas:
1. Qual o propósito dessa consulta?
Resposta: A consulta tem como objetivo identificar usuários com perfil ADMIN que realizaram mais de 5 logins bem-sucedidos, retornando o nome do usuário (username) e o total de logins com sucesso, ordenados do maior para o menor número de acessos.

2. Há erros lógicos ou sintáticos?
   Resposta: Não há erros lógicos ou sintáticos, mas podendo ser melhorada utilizando INNER JOIN

3. Que tipo de cenário de teste você derivaria a partir dela?
   Resposta: Cenário 1 – Admin com mais de 5 logins bem-sucedidos
   Dado um usuário com perfil ADMIN
   E com 6 ou mais registros de login com sucesso = true
   Quando a consulta for executada
   Então o usuário deve aparecer no resultado com o total correto de logins

   Cenário 2 – Logins sem sucesso
   Dado um usuário ADMIN com vários logins
   Mas todos com sucesso = false
   Então o usuário não deve ser retornado

   Cenário 3 – Duplicidade de username
   Dado dois usuários distintos com o mesmo username
   Quando a consulta for executada
   Então o total de logins pode ser consolidado incorretamente, indicando risco no GROUP BY

---

Exemplo 2 – Identificação de erro lógico

SELECT * FROM usuarios WHERE bloqueado = 'false';

Perguntas: 
1. O que há de errado nesta consulta no PostgreSQL?
Reposta: No PostgreSQL, campos do tipo BOOLEAN devem ser comparados com:
false (sem aspas)
true (sem aspas)
'false' entre aspas é uma string, não um boolean.

2. Como corrigir? Resposta: SELECT * FROM usuarios  
   WHERE bloqueado = false;

3. Como um teste automatizado poderia detectar essa falha?
   Resposta: Validando o comportamento da consulta em tempo de execução, com isso garantindo o tipo de dado BOOLEAN está sendo tratado de forma correta

---

Exemplo 3 – Verificação de bloqueio

SELECT u.username, u.tentativas,
CASE WHEN u.tentativas >= 3 THEN 'BLOQUEADO' ELSE 'ATIVO' END AS status
FROM usuarios u;

Perguntas: 

1. Descreva um cenário que resulte em “BLOQUEADO”;
Resposta: Dado um usuário com username = 'admin01'
E com tentativas = 3 (ou mais)
Quando a consulta for executada
Então o campo status retornado será “BLOQUEADO”

2. Como validar a consistência após o teste;
   Resposta: Verificar se o campo tentativo reflete corretamente o comportamento esperado.
   Exemplo:
   Usuário com 3 tentativas inválidas
   Campo tentativas deve permanecer ≥ 3
   Nenhuma alteração indevida no valor após a consulta

3. Como limpar a base após o teste?
   Resposta: BEGIN;
   -- ações do teste
   INSERT INTO usuarios (...);
   UPDATE usuarios SET tentativas = 3 WHERE id = 1;
   -- validações
   SELECT ...
   ROLLBACK; 

---

Exemplo 4 – Erro conceitual em junção

SELECT u.username, a.data_evento
FROM usuarios u, auditoria_login a
WHERE u.id = a.usuario_id(+);

Perguntas: 

1. Qual o erro na sintaxe?
Resposta: O erro está no uso do operador 

2. Como reescrever corretamente?
Resposta: O correto seria:
   SELECT u.username, a.data_evento
   FROM usuarios u
   LEFT JOIN auditoria_login a
   ON u.id = a.usuario_id; 

3. Que erro o PostgreSQL retornaria?
   Resposta: ERROR:  syntax error at or near "+"
   LINE X: WHERE u.id = a.usuario_id(+);

---

Exemplo 5 – Integridade e dados órfãos
SELECT a.id, a.usuario_id
FROM auditoria_login a
WHERE a.usuario_id NOT IN (SELECT id FROM usuarios);

Perguntas: 

1. Qual o propósito da consulta?
Resposta: Consulta tem como objetivo identificar registros órfãos na tabela auditoria_login, ou seja, eventos de login associados a usuários que não existem mais na tabela usuários.

2. Como ela contribui para testes de integração?
   Resposta: Essa consulta contribui diretamente para testes de integração porque valida a consistência entre tabelas relacionadas, garantindo que o sistema esteja respeitando a integridade dos dados ao longo dos fluxos funcionais.

3. Que cenário de teste validaria isso?
   Resposta: Dado que existe um usuário válido no sistema
   E que esse usuário realiza um login (registro criado em auditoria_login)
   Quando o usuário é removido da tabela usuários
   E não existe regra de exclusão em cascata
   Então a consulta de auditoria deve retornar o registro órfão
   E o teste deve falhar, indicando inconsistência de dados

4. Como evitar o problema no banco?
   Resposta: ALTER TABLE auditoria_login
   ADD CONSTRAINT fk_auditoria_usuario
   FOREIGN KEY (usuario_id)
   REFERENCES usuarios(id); 
