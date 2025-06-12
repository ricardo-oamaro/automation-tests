Feature: Create Posts

  Scenario: Validar criação de post
    Given um payload de post com título "foo", corpo "bar" e userId 1
    When o cliente faz uma requisição POST para "/posts"
    Then o status code deve ser 201
    And o título da resposta deve ser "foo"
    And o userId da resposta deve ser 1

  Scenario: Retornar 404 ao acessar endpoint inexistente
    Given um payload de post com título "foo", corpo "bar" e userId 1
    When o cliente faz uma requisição POST para "/postagens"
    Then o status code deve ser 404

  Scenario: Retornar 500 ao enviar payload malformado
    Given um payload malformado
    When o cliente faz uma requisição POST para "/posts"
    Then o status code deve ser 500