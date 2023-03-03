# PicPay - Desafio Android

<img src="./desafio-picpay.gif" width="300"/>

Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:
- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
- Discutir as possibilidades de solução durante a entrevista, fazendo um pair programming (bate-papo) interativo com os nossos devs.

Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:

- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
- Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
- Gostaríamos de cachear os dados retornados pelo servidor.
- Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
- Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais.
  - Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)

Ps.: Fique à vontade para editar o projeto inteiro, organização de pastas e módulos, bem como as dependências utilizadas

## Minha entrevista

Antes da entrevista técnica com perguntas e apresentação do desafio, listei alguns tópicos que considero importantes para a carreira de um engenheiro de software e que poderia também servir de script para mim... Quase todos eles foram abordados durante o papo:

```
1. Project structure
  
  - package by feature

  - package by layer is bad

2. Dependency injection with Dagger
  
  - decoupling: casting context to ApplicationComponent
  
3. CoreModule: providing common dependencies

  - creational design pattern: AppDatabase as a Singleton

    * only one connection to database opened during the app lifecycle
  
  - structural design pattern: InternetConnectivity as a Proxy
  
  - Dagger Multibindings: providing view models to ViewModelFactory

4. Software architecture

  - Clean Architecture (guided by Uncle Bob)

    * productivity x time relation comparison when adopting or not an architecture

    * focusing on domain

  - software design principles with SOLID

    * Liskov Substitution Principle: square vs rectangle is a good example

    * Dependency Inversion Principle: making the build system more efficient

5. Data package

  - convention: Data Source
  
  - convention: Repository

  - concurrency with suspend functions
    
    * continuation callback
    
    * state machine (theory of computation)
    
    * thread mapping models
    
    * coroutines and dispatchers (Kotlin allocates 64 threads by default for IO dispatcher)
    
    * efficiency in IO-bound tasks, similar to the Node.js (event loop)

  - injecting dependencies on activities

  - OkHttp as the HTTP client

  - Retrofit as an abstraction to map the web service endpoints

6. Domain package

  - User entity and UserUseCase

  - ready to receive business rules

7. Presentation package

  - ViewModel: surviving to process killing/configuration changes

    * retaining UI state on the users LiveData

    * behavioral design pattern: LiveData is an Observer implementation

    * single shot LiveData: event doesn't re-emit its value

  - ViewModel::onLoadUsers called on first activity instance

  - Achieving the unidirectional data flow

  - Comparison with MVP (bidirectional)

8. Testing

  - unit tests

  - instrumented tests

  - article: "Mocks aren't stubs" by Martin Fowler

    * classicist vs mockist

9. Other important topics

  - Algorithms and data structures

  - Complexity of algorithms and asymptotic analysis

  - Parametric polymorphism and Kotlin's in/out/where

  - Benefits of the functional paradigm

    * focusing on state management

  - Network protocols, OSI model, tcp vs udp

  - Database indexing and searching in O(log n) with the B-tree data structure
```
