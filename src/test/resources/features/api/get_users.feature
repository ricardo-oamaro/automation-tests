Feature: Get Users

  Scenario: Validar recuperação de usuários
    When o cliente faz uma requisição GET para "/users"
    Then o status da resposta ao buscar usuarios deve ser 200
    And o corpo da resposta deve conter mais de 0 usuários


  Scenario: Erro 404 ao buscar usuários com rota inválida
    When o cliente faz uma requisição GET para "/userz"
    Then o status da resposta ao buscar usuarios deve ser 404


  Scenario: Nenhum usuário encontrado
    When o cliente faz uma requisição GET para "/user-empty"
    Then o status da resposta ao buscar usuarios deve ser 200
    And o corpo da resposta deve conter 0 usuários