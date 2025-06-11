Feature: Create Posts

  Scenario: Validar criação de post
    Given um payload de post com título "foo", corpo "bar" e userId 1
    When o cliente faz uma requisição POST para "/posts"
    Then o status da resposta deve ser 201
    And o título da resposta deve ser "foo"
    And o userId da resposta deve ser 1