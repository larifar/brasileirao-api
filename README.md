<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->

<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">brasileirao-api</h3>

  <p align="center">
    Um projeto de webScrapping de partidas de futebol do brasileirão 2024
    <br />
    <a href="https://github.com/larifar/brasileirao-api"><strong>Explore the docs »</strong></a>
    <br />
    <br />
   <!-- <a href="https://github.com/github_username/repo_name">View Demo</a> -->
    .
    <a href="https://github.com/larifar/brasileirao-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/larifar/brasileirao-api/issues">Request Feature</a>
  </p>
</div>




<!-- ABOUT THE PROJECT -->
## Sobre o projeto

<!-- [![Product Name Screen Shot][product-screenshot]](https://example.com) -->

Desenvolvido em curso da [udemy](https://www.udemy.com/course/java-web-scraping).
Projeto de WebScrapping feito em Java 17 com Spring Boot, desenvolvimento de um bot que captura informações de partidas de futebol do Brasileirão em tempo real. Desenvolvimento da API com padrão de arquitetura REST para exposição de dados.
Dados fornecidos: 
- Equipe: nome da equipe, url da logo
- Partida: nomes das equipes, placares das equipes, placar extendido (pênaltis), tempo de jogo, status do jogo, gols das equipes (jogador e tempo).


### Tecnologias utilizadas

* Java 17
* Spring Boot
* Jsoup
* Spring Data JPA
* Swagger
* Lombok
* Scheduler
* Hikari
* H2 database



<!-- GETTING STARTED -->
## SetUp
Setup para poder ter uma cópia do repositório localmente:

### Pré Requisitos

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- IDE: eu utilizei a Spring Tool Suite, mas pode ser qualquer uma de sua preferência (Eclipse, Intellij, etc)
- Loombok
- Git
- Maven


### Instalação

1. Clone o repositório:
   ```sh
   git clone https://github.com/larifar/brasileirao-api.git
   ```
2. Para executar o projeto no terminal:
   ```sh
   * shell script
    mvn spring-boot:run
   ```
3. Depois abra a documentação em:
   ```js
   http://localhost:8080/swagger-ui.html
   ```



<!-- USAGE EXAMPLES -->
## Usos

- Página da documentação quando executa o projeto:
![image](https://github.com/larifar/brasileirao-api/assets/114440036/4d4622f6-040e-465b-8a83-13496d3287e6)
- Página listagem de equipes:
![image](https://github.com/larifar/brasileirao-api/assets/114440036/fd94f0c6-9c10-4fd3-8b1b-9daf92f5b02e)
Os dados são devolvidos em JSON.
A aplicação não tem um banco de dados físico, toda vez que é executada o banco de dados é criado e quando finalizada ele é apagado.

 <br />

 A aplicação tem um arquivo data.sql com o nome e logo de todos times que participarão do brasileirão 2024. Quando executada, o bot da aplicação roda todas quartas, quintas, sabádos e domingos.


<!-- CONTRIBUTING -->
## Contribuição

Todas contribuições são bem-vindas. Se tiver alguma sugestão de como melhorar o projeto, por favor de um fork e crie um pull request.




<!-- CONTACT -->
## Contato

Larissa Faria Silva -  lari.f4ria@gmail.com

Project Link: [https://github.com/larifar/brasileirao-api](https://github.com/larifar/brasileirao-api)




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
