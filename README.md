# Auth Api Java

Desenvolvido como uma API RESTful com Spring Framework, este projeto oferece um sistema CRUD básico, fortalecido com autenticação JWT e segurança reforçada pelo Spring Security. Com exceções personalizadas para uma experiência de desenvolvimento e explorar conceitos avançados de desenvolvimento com segurança e eficiência.

## Índice

1. [Tecnologias Utilizadas](#tecnologias-utilizadas)
2. [Instalação](#instalação)
3. [Como Usar](#como-usar)
4. [Contribuição](#contribuição)
5. [Licença](#licença)


<a name="tecnologias-utilizadas"></a>

## Tecnologias Utilizadas

As principais tecnologias, frameworks e ferramentas utilizadas no projeto:

- Java

- Spring

- JWT

- Spring Security

  

## Instalação

Para instalação do projetos, execute os seguintes comdandos:

```
git clone https://github.com/pedrohma07/auth-api-java.git
cd auth-api-java
```

Como maven, docker e o java devidamente instalados execute os seguintes comando:

```
docker compose up -d
```

```
mvn clean install
```


<a name="como-usar"></a>

## Como Usar

Para executar execute os seguintes comandos:

```
mvn spring-boot:run
```

E basta acessar http://localhost:8080

OBS: Podem verificar as rotas nas controllers

## Contribuição

Se você deseja contribuir para o projeto, siga estas etapas: 

1. Faça um fork do projeto. 
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`). 
3. Faça commit de suas alterações (`git commit -am 'Adicionando uma nova feature'`).
4. Faça push para a branch (`git push origin feature/MinhaFeature`). 
5. Abra um Pull Request. Após o envio do Pull Request, ele será revisado pelo mantenedor do projeto. Contribuições são bem-vindas! Qualquer ajuda para melhorar este projeto é muito apreciada.

## Licença

Este projeto está licenciado sob os termos da Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.