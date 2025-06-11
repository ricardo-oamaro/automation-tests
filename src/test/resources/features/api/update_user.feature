Feature: Update Users

  Scenario: Validar atualização de um usuário
    Given um payload com nome "Alice Atualizada" e email "alice.new@example.com"
    When o cliente faz uma requisição PUT para "/users/1"
    Then o status da resposta deve ser 200
    And o nome da resposta deve ser "Alice Atualizada"
    And o email da resposta deve ser "alice.new@example.com"