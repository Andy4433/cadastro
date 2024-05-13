Projeto de Cadastro com JavaServer Faces (JSF) e PostgreSQL
Descrição
Este projeto consiste em um sistema de cadastro desenvolvido utilizando JavaServer Faces (JSF) como framework web e PostgreSQL como banco de dados relacional. O objetivo é permitir o cadastro de usuários e seus endereços, demonstrando a integração entre a camada de visão, controle e modelo.

Decisões Técnicas e Arquiteturais
JavaServer Faces (JSF): Foi escolhido devido à sua facilidade de uso para o desenvolvimento de interfaces web em Java, além de oferecer componentes ricos e um modelo de programação baseado em eventos.

PostgreSQL: Utilizado como banco de dados devido à sua robustez, suporte a transações e compatibilidade com o ambiente Java.

MVC (Model-View-Controller): A arquitetura do projeto segue o padrão MVC, separando as responsabilidades de forma clara entre a camada de modelo (entidades e acesso ao banco de dados), a camada de visão (páginas XHTML) e a camada de controle (managed beans).

Frameworks e Bibliotecas
PrimeFaces: Utilizado para aprimorar a interface do usuário com componentes mais elaborados e responsivos.

JDBC: Para a conexão e manipulação do banco de dados PostgreSQL.

Compilação e Execução do Projeto
Clone o repositório para sua máquina local.
Importe o projeto em sua IDE preferida (Eclipse, IntelliJ, etc.).
Certifique-se de ter o servidor Apache Tomcat configurado na IDE.
Configure o banco de dados PostgreSQL e ajuste as configurações de conexão no arquivo ConexaoPostgreSQL.java.
Execute o projeto no servidor Tomcat.
Acesse a aplicação através da URL fornecida pelo servidor Tomcat.
Execução dos Testes
Os testes automatizados estão localizados no diretório src/test/java. Para executá-los:

Abra a IDE e navegue até o diretório dos testes.
Execute os testes unitários para as classes que deseja testar.
Notas Adicionais
Certifique-se de ter o JDK (Java Development Kit) instalado em sua máquina para compilar o projeto.
É recomendável utilizar uma IDE compatível com Java EE para facilitar o desenvolvimento e execução do projeto.
Caso encontre algum problema durante a execução do projeto, consulte a documentação do framework JSF e do banco de dados PostgreSQL para obter ajuda.
