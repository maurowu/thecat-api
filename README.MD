# The Cat API

Micro serviço que coleta as informações da API de gatos (https://thecatapi.com/), realiza a carga de dados (informações sobre a raça e imagens) e disponibiliza APIS de busca para consumo externo.

## Índice

<!--ts-->
   * [Documentação do Projeto] (#Documentação do Projeto)
   * [Dicionário de dados] (#Dicionário de dados)
   * [Documentação das APIS] (#Documentação das APIS)
   * [Documentação da Arquitetura] (#Documentação da Arquitetura)
   * [HOWTO - subida do serviço em ambiente local] (#HOWTO - subida do serviço em ambiente local)
   * [Manual - Elasticsearch ] (#Manual - Elasticsearch )
   * [Manual - Prometheus] (#Manual - Prometheus)
   * [Manual = Grafana Dashboard] (#Manual - Grafana Dashboard)

## Documentação do Projeto

## Dicionário de dados
catId       -> Id da raça do gato

name        -> Nome da raça do gato

origin      -> Origem do gato

temperament -> Temperamentos dos gato

description -> Descrição da raça do gato

## Documentação das APIS

Para a documentação das APIs, utilizamos o Swagger conforme os seguintes links:

* JSON com a estrutura dos serviços - http://localhost:8080/thecat/api/swagger/swagger.json
* Interface para execução dos serviços - http://localhost:8080/thecat/webjars/swagger-ui/index.html?url=/thecat/api/swagger&validatorUrl=

### Serviços expostos:

#### http://localhost:8080/thecat/api/v1/breed (Método GET)
Retorna uma lista com todas as raças de gatos carregados no serviço.

**Response:**
```
[
    {
        "catId": "abys",
        "name": "Abyssinian",
        "origin": "Egypt",
        "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
        "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals."
    }
]
```

#### http://localhost:8080/thecat/api/v1/breed-info-by-id (Método GET)
Retorna a raça de um gato a partir de um parâmetro de Id.
Query param: catId
Exemplo: http://localhost:8080/thecat/api/v1/breed-info-by-id?catID=abys

**Response:**
```
{
    "catId": "abys",
    "name": "Abyssinian",
    "origin": "Egypt",
    "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
    "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals."
}
```

#### http://localhost:8080/thecat/api/v1/breed-by-origin (Método GET)
Retorna a lista de raças de gatos a partir de um parâmetro de origem.
Query param: origin
Exemplo: http://localhost:8080/thecat/api/v1/breed-by-origin?origin=Egypt

**Response:**
```
[
    {
        "catId": "abys",
        "name": "Abyssinian",
        "origin": "Egypt",
        "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
        "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals."
    }
]
```

#### http://localhost:8080/thecat/api/v1/breed-by-temperament(Método GET)
Retorna a lista de raças de gatos a partir de um parâmetro de temperamento.
Query param: origin
Exemplo: http://localhost:8080/thecat/api/v1/breed-by-temperament?temperament=Active

**Response:**
```
[
    {
        "catId": "abys",
        "name": "Abyssinian",
        "origin": "Egypt",
        "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
        "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals."
    }
]
```

## Documentação da Arquitetura

## HOWTO - subida do serviço em ambiente local

Caso utilize git, realizar o clone do projeto a partir do comando "git clone https://github.com/maurowu/thecat-api.git". Será baixado a branch default automaticamente (feature/0.0.1-SNAPSHOT) o qual será utilizada para a subida do serviço.
Caso negativo, realize o download a partir do link "https://github.com/maurowu/thecat-api/tree/feature/0.0.1-SNAPSHOT".

Os procedimentos a seguir devem ser realizados no diretório do projeto e possuir tanto o maven quanto o Docker instalados na máquina. O Docker precisa do serviço estar inicializado também.

1. Execute o comando "mvn package" para a criação do executável ".JAR".

2. Execute o comando "docker image build -t thecat-api ." para gerar a imagem do serviço.

3. Execute o comando "docker run -p 8080:8080 -t docker.io/library/thecat-api" para inicializar o serviço com a configuração local.

Temos a possibilidade de executar com o comando "docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" --env-file ./environment/dev/dev.env -t docker.io/library/thecat-api". Desta forma, podemos escolher o ambiente que será executado (dev, hml, pro), sendo necessário trocar a palavra no comando conforme necessário. Exemplo:

* dev (desenvolvimento) -> "docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" --env-file ./environment/dev/dev.env -t docker.io/library/thecat-api"
* hml (homologação) -> "docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=hml" --env-file ./environment/hml/hml.env -t docker.io/library/thecat-api"
* pro (produção) -> "docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=pro" --env-file ./environment/pro/pro.env -t docker.io/library/thecat-api"
	
O serviço estará disponível no caminho "http://localhost:8080/thecat/".

## Manual - Elasticsearch 

## Manual - Prometheus

## Manual - Grafana Dashboard

