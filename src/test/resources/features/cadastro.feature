Feature: Login e cadastro de novo empregado no OrangeHRM

  @cadastro
  Scenario: Login bem-sucedido e preenchimento do formulário de novo empregado
    Given que o usuário acessa a página de login do OrangeHRM
    When realiza o login com usuário "Admin" e senha "admin123"
    And acessa o menu PIM e clica em "Add Employee"
    And preenche o formulário com nome "Joao", nome do meio "Silva" e sobrenome "Santos"
    Then o sistema deve redirecionar para a página de detalhes do empregado