Feature: Delete Users

  Scenario: Validar exclusão de um usuário
    When o cliente faz uma requisição DELETE para "/users/1"
    Then o status da resposta ao deletar usuario deve ser 200