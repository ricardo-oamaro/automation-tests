Feature: Adicionar e remover itens do carrinho no SauceDemo

  @ecommerce
  Scenario Outline: Adicionar e remover um produto do carrinho
    Given que o usuário acessa o site SauceDemo
    When ele faz login com usuário "standard_user" e senha "secret_sauce"
    And adiciona o produto "<produto>" ao carrinho
    Then o carrinho deve conter o produto "<produto>"
    When ele remove o produto "<produto>" do carrinho
    Then o carrinho não deve conter o produto "<produto>"

    Examples:
      | produto               |
      | Sauce Labs Backpack   |

  @checkout
  Scenario: Finalizar uma compra com sucesso
    Given que o usuário acessa o site SauceDemo
    When ele faz login com usuário "standard_user" e senha "secret_sauce"
    And adiciona o produto "Sauce Labs Backpack" ao carrinho
    And vai para o carrinho
    And inicia o checkout
    And preenche os dados de pagamento com nome "Maria", sobrenome "Silva" e CEP "12345-678"
    And finaliza a compra
    Then a compra deve ser concluída com sucesso