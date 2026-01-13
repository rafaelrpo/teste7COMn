# 🚀 Projeto de Automação de Testes – Login (Selenium + Cucumber + Java)

## 📌 Visão Geral

Este projeto demonstra a automação de testes funcionais **End-to-End (E2E)** para um sistema de login,
utilizando **Selenium WebDriver**, **Cucumber (BDD)** e **Java**, seguindo boas práticas de mercado
como Page Object Model, BDD e configuração externa.

A aplicação testada é pública e estável:

🔗 https://the-internet.herokuapp.com/login

---

## 🎯 Objetivo do Projeto

- Automatizar cenários de login válido
- Automatizar cenários de login inválido
- Servir como base para projetos corporativos de automação
- Demonstrar organização e boas práticas para CI/CD

---

## 🧪 Cenários Automatizados

### ✔ Login válido
- Usuário informa credenciais corretas - USER
- Sistema redireciona para área segura
- Mensagem de sucesso exibida

### ❌ Login inválido
- Usuário informa senha incorreta - VISITOR
- Sistema exibe mensagem de erro

### 🔒 Logout
- Usuário autenticado realiza logout
- Sistema retorna para tela de login

---

## 🛠 Tecnologias Utilizadas

- Java 17
- Maven
- Selenium WebDriver 4
- Cucumber 7
- JUnit 5
- AssertJ
- ChromeDriver

---

## 📁 Estrutura do Projeto

login-automation-cucumber
├── pom.xml
├── README.md
└── src
└── test
├── java
│ └── br.com.empresa.login
│ ├── pages
│ │ ├── LoginPage.java
│ │ └── SecureAreaPage.java
│ ├── steps
│ │ └── LoginSteps.java
│ └── runner
│ └── CucumberTestRunner.java
└── resources
├── features
│ └── login.feature
└── config
└── test.properties


---

## ⚙️ Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.8+
- Google Chrome atualizado
- IDE (IntelliJ IDEA ou Eclipse)

---

## 🔧 Configuração

Arquivo:
src/test/resources/config/test.properties


---

## ▶️ Como Executar

### Executar todos os testes

### Executar pela IDE
- Abrir a feature login e executar scenario por scenario ou por tags @
- Executar como teste JUnit
---

## 🧠 Boas Práticas Aplicadas

- Page Object Model
- BDD com Gherkin
- Reutilização de código
- Configuração externa
- Estrutura pronta para CI/CD

---

## 🚀 Possíveis Evoluções

- Screenshot automático em falhas
- WebDriverManager
- Execução paralela
- Relatórios Allure
- Integração com Jenkins / GitHub Actions

---

## 👨‍💻 Autor

Rafael Rodrigo  
QA Automation Engineer
