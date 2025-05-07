Análise de resultados testes JMeter
https://docs.google.com/document/d/1BC12iv7MQgJyYF0FL05TrD5e7o98xIpVWW1Vj5GoXEc/edit?tab=t.0#heading=h.4wls4fjldw9z

Análise de resultados testes RestAssured
https://docs.google.com/document/d/1Td7wpgzvUklnr6CM8tmOYZc2MOxys8NzSOS7CDPGOqI/edit?tab=t.0#heading=h.d0m2gawoq5p8

Análise de resultados testes e2e
https://docs.google.com/document/d/1AZi_fOV6M9ck-cur4xfM3nmTvWXpWOuXql4K-kkqyKI/edit?tab=t.0


# Projeto de Testes Automatizados - API e E2E

Este repositório contém testes automatizados de **API** e **E2E (Selenium)**, além de um script de carga utilizando **JMeter**.

---

## Estrutura do Projeto

```
test/
├── java/
│   └── br/com/test/
│       ├── api/                  # Testes de API (JUnit/TestNG)
│       ├── automation/           # Classe principal para execução
│       ├── base/                 # Base de testes (configurações e setup)
│       ├── jmeter/               # Script JMeter (.jmx)
│       ├── runners/              # Test runner (ex: Cucumber/TestNG)
│       ├── steps/                # Definição dos passos para testes E2E (BDD)
│       └── utils/                # Utilitários para os testes
├── resources/
│   ├── features/                 # Cenários em Gherkin (.feature)
│   └── extent.properties         # Configurações de relatórios
```

---

## Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Node.js (v18+)](https://nodejs.org/)
- [Google Chrome](https://www.google.com/chrome/)
- (Opcional) [Apache JMeter](https://jmeter.apache.org/)

---

## Executando os Testes Localmente

### 1. Clone o repositório

```bash
git clone https://github.com/ricardo-oamaro/automation-tests.git
### caso nao consiga clonar, faça o download do zip ou solicite acesso ao repositorio
```

### 2. Instale as dependências Node.js

```bash
npm install
```

### 3. Inicie o servidor fake JSON

```bash
npx json-server --watch db.json --port 3000
```

---

## Rodar Testes de API

```bash
mvn test -Dtest=br.com.test.api.*Test
```

---

## Rodar Testes E2E (Selenium)

```bash
mvn verify -P e2e-tests
```

---

## Rodar Testes de Carga (JMeter)

O projeto inclui um script JMeter em `test/java/br/com/test/jmeter/test.jmx`.

1. Abra o **Apache JMeter**.
2. Vá em `File > Open`.
3. Importe o arquivo `test.jmx`.
4. Execute o teste diretamente pela interface.

---

## Pipeline CI/CD

A pipeline roda automaticamente:

- Testes de API ao fazer push ou pull request.
- Testes E2E com Selenium, dependendo dos testes de API.
- Configuração do ambiente via GitHub Actions com `Java 17`, `Node.js 18` e `Chrome`.

---

## Observações

- Os testes usam variáveis de ambiente, como `BASE_URL`, já configuradas na pipeline.
- Você pode configurar no seu ambiente local com:

```bash
export BASE_URL=http://localhost:3000
```

---