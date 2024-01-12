[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

[![CI](https://github.com/RaspberryCode/hms/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/RaspberryCode/hms/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=RaspberryCode_hms&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=RaspberryCode_hms)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=RaspberryCode_hms&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=RaspberryCode_hms)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=RaspberryCode_hms&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=RaspberryCode_hms)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=RaspberryCode_hms&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=RaspberryCode_hms)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=RaspberryCode_hms&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=RaspberryCode_hms)


<!-- PROJECT LOGO -->

<br />
<div align="center">
  <a href="https://github.com/RaspberryCode/hms">
    <img src="images/logo.png" alt="Logo" width="750" height="56.5">
  </a>

<h3 align="center">Happenings Management System</h3>

  <p align="center">
    Platform dedicated to streamlining the publishing and sharing of event information in specified areas. With HMS, you can not only keep locals and visitors informed about upcoming events, but also share and access details about interested participants.
    <br />
    <a href="https://github.com/RaspberryCode/hms"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/RaspberryCode/hms">View Demo</a>
    ·
    <a href="https://github.com/RaspberryCode/hms/issues">Report Bug</a>
    ·
    <a href="https://github.com/RaspberryCode/hms/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#key-integrations">Key Integrations</a></li>
      </ul>
      <ul>
        <li><a href="#note">Note</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

HMS is a developer-centric project, aiming to provide a practical demonstration of several contemporary technologies and
practices in software development. While the core functionality revolves around event management, the real value lies in
the integrated tech stack.

### Key Integrations:

* **Spring Security**: We've used Spring Security to handle our platform's security, focusing on ensuring data
  protection and
  secure user interactions.

* **Testcontainers & Localstack**: For testing, HMS uses Testcontainers in tandem with Localstack. This allows us to
  mock AWS
  services, offering a cost-effective and accurate testing environment.

* **Terraform**: To manage and provision our infrastructure, we've integrated Terraform. This gives us a clear,
  code-driven
  approach to setting up and maintaining our deployment environments.

* **AWS (Amazon Web Services)**: We've built HMS on AWS to utilize its robust cloud services, focusing on scalability
  and
  efficient performance.

* **Github Actions for CI/CD**: To streamline our development process, we've incorporated Github Actions for continuous
  integration and deployment, keeping the codebase agile and maintainable.

### Note

While HMS serves as a demonstrative platform for these technologies, it's primarily built for developmental
exploration and should not be deployed directly in production environments. We encourage fellow developers to dive in,
explore, and possibly contribute to enhancing its capabilities.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

[//]: # (### Built With)

[//]: # ()
[//]: # ([![Kotlin][Kotlin]][Kotlin-url])

[//]: # ([![Spring Boot][React.js]][SpringBoot-url])

[//]: # ([![Gradle][Vue.js]][Gradle-url])

[//]: # ([![Test Containers][Angular.io]][TestContainers-url])

[//]: # ([![Docker][Laravel.com]][Docker-url])

[//]: # ([![JJWT][Bootstrap.com]][JJWT-url])

[//]: # ([![Swagger][JQuery.com]][Swagger-url])

[//]: # ([![LocalStack][JQuery.com]][Localstack-url])

[//]: # ()
[//]: # (<p align="right">&#40;<a href="#readme-top">back to top</a>&#41;</p>)



<!-- GETTING STARTED -->

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* [Install Kotlin](https://kotlinlang.org/docs/getting-started.html#install-kotlin) - bundled in IntelliJ
* [Install Gradle](https://gradle.org/install/)
* [Install Docker](https://docs.docker.com/get-docker/)

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/RaspberryCode/hms.git
   ```
2. Run with gradle - make sure docker is running
   ```sh
   gradlew bootRun
   ```
3. Access the swagger documentation at http://localhost:8080

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

[//]: # ()

[//]: # (## Usage)

[//]: # ()

[//]: # (Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos)

[//]: # (work well in this space. You may also link to more resources.)

[//]: # ()

[//]: # (_For more examples, please refer to the [Documentation]&#40;https://example.com&#41;_)

[//]: # ()

[//]: # (<p align="right">&#40;<a href="#readme-top">back to top</a>&#41;</p>)

[//]: # ()

[//]: # ()

<!-- ROADMAP -->

## Roadmap

- [x] Create security skeleton
- [x] Admin panel
    - [x] Admin can add happening
    - [ ] Admin can modify happening - triggers Users notification
- [ ] Users functionalities
    - [x] Users can register new account
        - [ ] Forgot password functionality
    - [ ] Users can modify their account preferences and details
    - [x] Read REST API for happenings
        - [ ] Add pagination
        - [ ] Add filtering
    - [ ] Users are notified about upcoming happenings
        - [ ] Add email notifications
        - [ ] Add push notifications
        - [ ] Add SMS notifications
    - [x] Users can mark that they are interested in happening
- [ ] Prepare for cloud deployment
    - [x] Add docker-compose
    - [x] Add container building manifest - not needed using buildpack
    - [x] Add CI/CD manifest
    - [ ] Add Code Coverage tool
    - [ ] Add terraform

See the [open issues](https://github.com/RaspberryCode/hms/issues) for a full list of proposed features (and
known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->

## Contact

[//]: # ([@twitter_handle]&#40;https://twitter.com/twitter_handle&#41;)
Tomasz Malinowski - tmalinowski@softwarekitchen.com

Project Link: [https://github.com/RaspberryCode/hms](https://github.com/RaspberryCode/hms)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

* [README you are reading was created based on this repository](https://github.com/othneildrew/Best-README-Template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/RaspberryCode/hms.svg?style=for-the-badge

[contributors-url]: https://github.com/RaspberryCode/hms/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/RaspberryCode/hms.svg?style=for-the-badge

[forks-url]: https://github.com/RaspberryCode/hms/network/members

[stars-shield]: https://img.shields.io/github/stars/RaspberryCode/hms.svg?style=for-the-badge

[stars-url]: https://github.com/RaspberryCode/hms/stargazers

[issues-shield]: https://img.shields.io/github/issues/RaspberryCode/hms.svg?style=for-the-badge

[issues-url]: https://github.com/RaspberryCode/hms/issues

[license-shield]: https://img.shields.io/github/license/RaspberryCode/hms.svg?style=for-the-badge

[license-url]: https://github.com/RaspberryCode/hms/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/tomalinowski

[product-screenshot]: images/screenshot.png

[Kotlin]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white

[Kotlin-url]: https://kotlinlang.org/

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[SpringBoot-url]: https://spring.io/projects/spring-boot

[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D

[Gradle-url]: https://gradle.org/

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

[TestContainers-url]: https://www.testcontainers.org/

[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00

[Svelte-url]: https://svelte.dev/

[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white

[Docker-url]: https://www.docker.com/

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

[JJWT-url]: https://github.com/jwtk/jjwt

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white

[Swagger-url]: https://swagger.io/

[Localstack-url]: https://localstack.cloud/