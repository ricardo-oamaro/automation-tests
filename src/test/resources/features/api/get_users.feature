Feature: Get Users

  Scenario: Validar recuperação de usuários
    When o cliente faz uma requisição GET para "/users"
    Then o status da resposta ao buscar usuarios deve ser 200
    And o corpo da resposta deve conter mais de 0 usuários