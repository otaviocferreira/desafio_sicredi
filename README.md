# Desafio SICREDI - by Otavio Ferreira

## Overview
Eu tentei usar o que existe de comum no mercado e o que eu domino mais.

Spring boot era a maneira mais facil de fazer, devido ao meu uspo diário, incluindo todo o ecossistema dele.

Fiquei devendo o Kafka por nao ter tempo de entender o porque a imagem não subia na minha máquina, mas a ideia é normal pra mim.

Utilizei o springDoc para criar a documentação da API, pois segue o openAPI v3 e tem suporte ao swagger, que eu particularmente gosto muito.

Usei o Jacoco para o coverage, e nao construi muitos testes para aumetar a cobertura, pois não achei necessário me aprofundar tanto, tendo em vista que criei testes "inbtegrados" usando os controllers, o que ja mostra mais ou menos o que acontece com a entrada de dados da API.

Usei o H2 como implementação de Banco para que fosse facil a manipulacao e o start da aplicacao para voces poderem analisar de imediato.

Acho que é só isso...

Ahhh, desculpa pelo historico de merges e commits nao estar bem claro, embora no final eu tenha me atentado a falta de clareza nos merges para a develop. Eu quis tentar usar o plugin do gitflow para ver como ele se comportaria, mas embora eu aprecie o fluxo em si, nao gostei de utilizar o fluxo do programa, não vi muita clareza nos merges e isso atrapalha pra rastrear as coisas.

###
### How to use
Para iniciar a aplicação, primeiro clone o repositorio.

````
git clone https://github.com/otaviocferreira/desafio_sicredi.git
````

*** Aplicação construida em Java 17

Após clonar, dentro na pasta raiz do projeto, execute o comando abaixo para startar a aplicação na porta 8080.

````
mvn spring-boot:run
````

Após a aplicação subir, dê uma olhada no swagger

````
http://localhost:8080/swagger-ui/index.html
````

Se quiser dar uma olhada no relatório do Jacoco. Após executar a fase verify do maven, abra o arquivo abaixo.

````
\target\site\jacoco\index.html
````


>*** Obrigado pela oportunidade !!!