Feature: Delete Users

  Scenario: Validar exclusão de um usuário
    When o cliente faz uma requisição DELETE para "/users/1"
    Then o status da resposta ao deletar usuario deve ser 200


  Scenario: Erro 404 ao fazer exclusão de um usuário
    When o cliente faz uma requisição DELETE para "/users/10"
    Then o status da resposta ao deletar usuario deve ser 404