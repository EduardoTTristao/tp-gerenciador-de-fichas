# tp-gerenciador-de-fichas

Integrantes: Eduardo Torres Tristão (4219), Luis Henrique Santos de Carvalho (4254)

Para o projeto da disciplina CCF 313 (Programação Orientada a Objetos), será desenvolvido em linguagem Java um programa que atue como gerenciador de fichas de personagens para sistemas de RPG. O programa tem suporte para os sistemas GURPS(Generical and Universal Role Playing System) e D&D(Dungeons & Dragons), além de diversas fichas de diferentes construções para cada usuário. Cada ficha armazenará informações sobre o personagem de cada usuário dentro do jogo como características pessoais (nome, idade, altura, peso etc.), atributos (força, pontos de vida, inteligência etc.) entre outras informações.

## Introdução ao jogo

RPG(Role-playing game) é um jogo de tabuleiro que consiste em um mestre(ou em alguns casos um conjunto de mestres), ou também conhecido como narrador, um conjunto de n jogadores, na maioria das vezes dados para tornar o jogo mais imprevisível e um sistema que irá obter as regras que irão regir o mundo do jogo. Esse conjunto de jogadores pode variar de 1 à qualquer quantidade que o mestre julgue viável. O jogo é constituido de uma história contada em conjunto que funciona da seguinte maneira: 

Os jogadores primeiramente fazem seus personagens, esses serão os protagonistas da história. Cada personagem possui um nome, idade, altura, peso, determinados valores que representam seus pontos fracos e fortes como Força, Destreza, Vitalidade, pontos de Vida(os atributos variam de acordo com o sistema), determinadas caracteristicas como por exemplo: cegueira, vício em bebidas, pensamento impussivo ou pensamento cauteloso, medo de aranhas, ódio contra goblins, vinculos, desejos, sonhos, dentre outras coisas.

O mestre por sua vez, irá descrever para os jogadores qual situação que os seus personagens estão e os jogadores irão dizer o que seus personagens farão. Portanto, o mestre controla todos os outros personagens do mundo, o cenário, os vilões e além disso é o juiz caso haja alguma dúvida sobre regras ou qualquer outra coisa.

## O Código

### Padrão de projeto

O nosso projeto usou o padrão MVC para dividir e organizar o código. Portanto, foi criado todo o código foi organizado entre Model, View e Controller. Model responsável pelas entidades e controle do banco de dados, View responsável pela aparência e interação com usuário e Controller que funciona como uma camada intermediaria entre o Model e o View.

### Model

No model temos as seguintes entidades:

- Atributo: Objeto abstrado responsável por representar os atributos armazenando seu nome e valor
- AtributoDND: Objeto que herda de atributo e possui adicionamente as especificações do sistema D&D para os atributos
- AtributoGURPS: Mesmo caso da AtributoDND mas para GURPS
- Caracteristica: Representa as desvantagens e vantagens do sistema gurps
- Personagem: Objeto abstrato que representa os personagens
- PersonagemDND: Herda de Persoangem e possui as especificidades do sistema D&D
- PersonagemGURPS: Mesmo caso da PersonagemDND mas para gurps
- Mesa: Representa aquela disposição de jogadores e mestres para um determinado jogo/história. Possui jogadores, mestres e personagens. Jogadores podem ser mestres em outras mesas e mestres podem ser jogadores em outras mesas.
- Usuario: Usuario representa a pessoa que irá usar do sistema de gereciamento de fichas.

#### Exceptions

Com o fim de tornar o sistema mais robusto, foi criado algumas exceções para que sejam tratadas com as seguintes condições:

- Acesso Negado: Quando aquele usuario não possui aquela permissão para fazer determinada ação ou acessar determinado dado
- Atr Inexistente: Quando aquele atributo buscado não existe
- Atributo Negativo: Quando é colocado um atributo negativo(o que não é permitido)
- Caracteristica Ja Adicionada: Quando é colocado uma caracteristica já adicionada no mesmo personagem
- Caracteristica Nao adicionada: Quando é buscado por uma caracteristica inexistente
- Categoria Pericia Invalida: Quando é adicionada uma pericia com valor inconsistente
- Custo Incompativel: Quando é colocado um custo incompativel com a categoria da caracteristica
- Mesa Cadastrada: Quando uma mesa já foi cadastrada
- Mesa Inexistente: Quando se tenta buscar por uma mesa que não existe
- Mod Pericia Invalida: Quando o modificador da pericia é invalida
- Personagem Inexistente: Quando se tenta buscar por um personagem que não existe
- Senha fraca: Quando o usuário digita uma senha fraca
- Senhas diferentes: Quando o usuário digita senhas diferentes no momento de repetir
- Sistema nao cadastrado: Quando é colocado um sistema nao cadastrado
- Usuario ja cadastrado: Quando é feito a tentativa de cadastrar um usuario ja cadastrado
- Usuario nao cadastrado: Quando é feito a tentativa de uma busca de um usuario nao cadastrado

#### Persistence

Os bancos de dados foram feitos com apenas 3 classes. MesasBD que é o banco de dados que armazena a mesa, PersonagemBD que armazena os personagens e o UserBD que armazena os usuarios. Atualmente internamente eles são armazenados como ArrayLists, mas no futuro pretendemos implementar como um banco de dados SQL.

### Controller

Para o controller só houve a necessidade de duas classes. MesasController e UserController. MesasController para gerenciar os acessos as mesas e UserController para gerenciar os acessos dos usuarios.
