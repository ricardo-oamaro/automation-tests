Feature: Login no OrangeHRM

  @Skip
  Scenario: Usuário acessa o sistema com credenciais válidas
    Given o usuário está na página de login do OrangeHRM
    When ele informa usuário "Admin" e senha "admin123"
    And clica no botão de login
    Then ele deve ver o painel de controle

  @Skip
  Scenario: Usuário acessa o sistema com credenciais inválidas
    Given o usuário está na página de login do OrangeHRM
    When ele informa usuário "usuarioInvalido" e senha "senhaIncorreta"
    And clica no botão de login
    Then ele deve ver uma mensagem de erro

  @Skip
  Scenario: Usuário faz logout após login
    Given o usuário está na página de login do OrangeHRM
    When ele informa usuário "Admin" e senha "admin123"
    And clica no botão de login
    And ele clica no ícone do usuário e seleciona logout
    Then ele deve retornar para a página de login

  Scenario Outline: Executa ações diferentes após login com sucesso
    Given o usuário está logado no sistema com usuário "<usuario>" e senha "<senha>"
    When ele acessa o menu "<menu>"
    Then o sistema deve exibir a seção "<seçãoEsperada>"

    Examples:
      | usuario | senha    | menu      | seçãoEsperada     |
      | Admin   | admin123 | Admin     | System Users       |
      | Admin   | admin123 | PIM       | Employee Information |
      | Admin   | admin123 | Leave     | Leave List         |