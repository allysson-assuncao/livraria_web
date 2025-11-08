Projeto: Gestão de Livraria (Spring Boot)

Este é um projeto para a disciplina de Programação Web, com o objetivo de implementar um sistema CRUD (Create, Read, Update, Delete) completo.

O tema escolhido foi "Gestão de Livraria", que envolve o cadastro de duas entidades com relacionamento 1-N (Um-para-Muitos): Autores e Livros.

1. Arquitetura e Tecnologias

Para simplificar a configuração e focar na lógica da aplicação, o projeto utiliza o ecossistema Spring Boot.

    Backend: Spring Boot 3

    Linguagem: Java (JDK 17+)

    Framework Core: Spring Framework (Injeção de Dependência, Spring MVC)

    Servidor Web: Apache Tomcat (embutido, via spring-boot-starter-web)

    Visão (Frontend): Thymeleaf (via spring-boot-starter-thymeleaf)

    Persistência (Banco): Spring Data JPA / Hibernate (via spring-boot-starter-data-jpa)

    Banco de Dados: PostgreSQL

    Utilitários: Lombok (para reduzir boilerplate de getters, setters, etc.)

    Gerenciador: Maven

2. Configuração para Execução

Siga estes três passos para executar o projeto:

2.1. Banco de Dados

    Garanta que o servidor PostgreSQL esteja acessível (nos IPs 10.90.24.54 ou 200.18.128.54).

    Execute o projeto (Run em qualquer IDE ou Linha de comando "mvn spring-boot:run") para criar a estrutura do banco 
    (use "spring.jpa.hibernate.ddl-auto=drop" para resetar o banco se necessário).

2.2. Configuração do Projeto

    Abra o arquivo src/main/resources/application.properties.

    Verifique se a URL do banco em spring.datasource.url está correta, apontando para o IP do servidor que você está usando (laboratório ou casa).

    IMPORTANTE: Altere a propriedade spring.jpa.properties.hibernate.default_schema para o nome exato do schema previamente criado no banco (ex: spring.jpa.properties.hibernate.default_schema=fulano.silva).

2.3. Execução

    Abra o projeto em uma IDE (como IntelliJ IDEA).

    Localize a classe principal LivrariaWebApplication.java.

    Execute o método main(). O Spring Boot iniciará o servidor Tomcat embutido.

    Acesse a aplicação no seu navegador pela URL (definida no application.properties):

        http://localhost:8081/livraria_web/

3. Fluxo de Execução (Visão Geral)

O projeto segue o padrão Model-View-Controller (MVC).

Fluxo de Carregamento (GET)

    O usuário acessa a URL /livraria_web/.

    O LivrariaController intercepta a requisição no método @GetMapping("/").

    O Controller utiliza os AutorRepository e LivroRepository (interfaces do Spring Data JPA) para buscar todos os dados no banco (findAll()).

    O Controller adiciona essas listas (e objetos de formulário vazios) a um objeto Model.

    O Spring Boot renderiza o template src/main/resources/templates/index.html (Thymeleaf).

    O Thymeleaf usa as listas no Model para popular as tabelas (th:each) e os formulários (th:object).

    O HTML final é enviado ao navegador.

Fluxo de Cadastro/Edição (POST)

    O usuário preenche um formulário (ex: "Salvar Novo Autor") e clica em "Salvar".

    O navegador envia uma requisição POST para a URL definida no formulário (ex: /autores/salvar).

    O LivrariaController intercepta a requisição no método @PostMapping("/autores/salvar").

    O Spring automaticamente "amarra" (bind) os dados do formulário a um objeto Autor (@ModelAttribute).

    O Controller usa autorRepository.save(autor) para salvar (ou atualizar, se o ID existir) a entidade no banco.

    O Controller retorna um redirect:/, forçando o navegador a recarregar a página principal, reiniciando o fluxo GET e exibindo os dados atualizados.

Fluxo de Exclusão/Edição (GET com PathVariable)

    O usuário clica em "Excluir" ou "Editar" em um item da tabela.

    O navegador faz uma requisição GET para uma URL específica (ex: /autores/excluir/5).

    O LivrariaController intercepta a requisição (ex: @GetMapping("/autores/excluir/{id}")).

    O ID é capturado pela anotação @PathVariable.

    O Controller executa a ação (ex: autorRepository.deleteById(id)) e redireciona para a home, ou (no caso da edição) recarrega a página com o formulário preenchido.
