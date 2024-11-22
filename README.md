#Blue Velvet Music Store Application

## Descrição

Este repositório contém uma aplicação completa separada em **Frontend** e **Backend**. A aplicação é uma loja de música chamada **Blue Velvet Music Store**. 

- **Backend**: Desenvolvido com Spring Boot, gerencia toda a lógica de negócios e acesso a dados.
- **Frontend**: Contém páginas HTML que fornecem a interface visual para os usuários.

---

## Pré-requisitos

Certifique-se de ter os seguintes requisitos instalados em sua máquina:

### Backend
- **Java 17+**
- **Maven** (para gerenciamento de dependências)

### Frontend
- Qualquer navegador moderno para abrir os arquivos HTML.

---

## Passo a passo para rodar a aplicação

### 1. Backend (Spring Boot)
1. Navegue até a pasta `backend` no terminal.
   ```bash
   cd backend
   ```

2. Compile e baixe as dependências do projeto usando o Maven:
   ```bash
   mvn clean install
   ```

3. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```
   - A aplicação será iniciada no endereço padrão: [http://localhost:8080](http://localhost:8080).

   A aplicação principal é a **BlueVelvetMusicStoreApplication**.

---

### 2. Frontend
1. Navegue até a pasta `frontend`.
   ```bash
   cd frontend/html
   ```

2. Abra o arquivo `AdminControlPanel.html` em seu navegador para começar.
   - Este é o ponto inicial para navegar entre as páginas do frontend.

---

## Notas adicionais
- Certifique-se de que o backend está rodando antes de acessar as páginas HTML do frontend, pois as páginas podem depender das APIs do backend para funcionar corretamente.
- Verifique as configurações de CORS no backend caso acesse o frontend a partir de um servidor diferente.

--- 

## Tecnologias utilizadas

- **Backend**: Spring Boot, Maven
- **Frontend**: HTML, CSS, JavaScript

--- 

**Blue Velvet Music Store** 🚀 - _Organize, gerencie e venda músicas com facilidade!_
