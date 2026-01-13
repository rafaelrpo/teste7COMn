Feature: Login do sistema

  @user
  Scenario: Login válido com perfil USER
    Given que acesso a página de login
    When realizo login com usuário USER válido
    Then devo visualizar o dashboard do usuário

  @visitor
  Scenario: Login com perfil VISITOR sem acesso
    Given que acesso a página de login
    When realizo login com usuário VISITOR válido
    Then devo visualizar mensagem de acesso negado

  @blocked
  Scenario: Bloqueio após três tentativas inválidas
    Given que acesso a página de login
    When realizo 3 tentativas de login com senha inválida
    Then devo visualizar mensagem de usuário bloqueado


