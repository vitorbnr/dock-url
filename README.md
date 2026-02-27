# 🔗 Encurtador de URLs

Este é um projeto de uma API RESTful para encurtamento de URLs, desenvolvido como solução para um desafio de Backend. O sistema recebe uma URL longa, gera um código curto único usando codificação Base62 e redireciona os usuários automaticamente, coletando métricas de acesso de forma assíncrona.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3** (Web, Data JPA)
* **PostgreSQL** (Banco de dados relacional)
* **Flyway** (Migrations para versionamento do banco)
* **Docker & Docker Compose** (Containerização da aplicação e infraestrutura)
* **Swagger / OpenAPI** (Documentação da API)

## ⚙️ Arquitetura e Decisões Técnicas

* **Algoritmo Base62:** Utilizado para gerar códigos curtos (ex: `aB3x9Z`), permitindo bilhões de combinações possíveis com apenas 6 caracteres.
* **Processamento Assíncrono (`@Async`):** O registro de métricas (IP e User-Agent) ocorre em uma thread separada no *background*, garantindo que o redirecionamento (HTTP 302) seja instantâneo para o usuário.
* **Multi-stage Build (Docker):** O `Dockerfile` foi otimizado utilizando múltiplos estágios, separando o ambiente de build (Maven) do ambiente de execução (JRE), gerando uma imagem final muito mais leve e segura.

## 🛠️ Como rodar o projeto localmente

Pré-requisitos: Ter o **Docker** e o **Docker Compose** instalados na sua máquina.

1. Clone o repositório:
```bash
git clone https://github.com/vitorbnr/dock-url.git
cd dock-url
```

2. Suba a infraestrutura usando o Docker Compose:
```bash
docker-compose up -d --build
```

3. A aplicação estará disponível na porta 8081. Você pode acessar a documentação interativa do Swagger em:
   http://localhost:8081/swagger-ui.html

4. Endpoints da API

POST	/api/shorten	Recebe um JSON com a url longa e devolve a URL encurtada.

GET	/api/links	Lista todas as URLs encurtadas e o total de cliques de cada uma.

DELETE	/api/links/{shortCode}	Deleta um link encurtado e todas as suas métricas.

GET	/{shortCode}	Rota de redirecionamento. Redireciona para a URL original (HTTP 302).

Exemplo de Uso (Criar Link)
Requisição: POST http://localhost:8081/api/shorten

```bash
{
  "url": "https://github.com/vitorbnr/dock-url"
}
```

Resposta (201 Created):

```bash
{
  "short_url": "http://localhost:8081/x2LCjx",
  "original_url": "https://github.com/vitorbnr/dock-url"
}
```

(Para testar o redirecionamento, basta colar a short_url no navegador).