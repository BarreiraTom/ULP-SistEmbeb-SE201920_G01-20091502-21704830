# Nome da Aplicação: Four Wheeler
## Ponto de Situação

**1. Objetivos Alcançado**

- Layouts de Login e Painel de Controlo

- Ligação com Base de Dados para Execução do LogIn e Visualização de informação no Painel de Controlo

- Criado Ficheiro de preferencias onde são guardados(Atualmente) o Username, Password e ID do utilizador



**2. Plano de desenvolvimento da aplicação**

- Jorge Gonçalves desenvolveu o FrontEnd (Fragments da Aplicação e Layouts)

- João Tomás Barreira desenvolveu o BackEnd (Base de Dados, Ficheiro de Preferência) e associou o FrontEnd com o BackEnd



**3. Código desenvolvido até ao momento**

- Criado no branch: ENTREGA02




## Descrição da Aplicação - Tema 1
### Sistemas Embebidos

João Tomás Barreira, 21704830

Jorge Gonçalves, 20091502


- Requisitos para 11-12-2019

<p align="center">
  <img width="400" src="https://i.imgur.com/KKDO7v9.png">
</p>

- Perante os requesitos apresentados, abaixo podemos ver o desenvolvimento de cada um deles.


1. Planeamento do Layout dos Ecrãs da Aplicação


- A aplicação quando iniciada pela 1ª vez irá mostrar um ecrã de início de sessão, no qual o utilizador irá inserir as suas credenciais.

![Image](https://i.imgur.com/bLml03t.png)

- Se o login for bem-sucedido, irá mudar o layout do ecrã para mostrar um painel de controlo geral com algumas informações do utilizador, do veículo do mesmo, e notificações por ler relacionadas com o veículo.

![Image](https://i.imgur.com/2zJ4ACK.png)


- No mesmo layout irá existir um menu que poderá levar a um layout diferente no qual será apresentada uma lista com Manutenções, Seguros, Viagens Realizadas ou Abastecimentos dependendo da escolha do Utilizador.

![Image](https://i.imgur.com/9IANu94.png)


- Para se adicionarem itens às listas, o Layout anterior irá conter um botão de inserção das listas que levará para um layout onde os valores poderão ser preenchidos à escolha do utilizador.

![Image](https://i.imgur.com/0Q7yJ3Q.png)

- Relativamente à navegação, a aplicação irá conter um menu do tipo drawer, o qual disponibilizará as opções de navegação todas, assim como a possibilidade de efetuar Logout.

![Image](https://i.imgur.com/WfjRWwg.png)

- Para a visualização de todos os dados relativos aos veiculos da empresa, estes serão apresentados num layout que só um utilizador terá acesso. **(a confirmar com o professor)**


2. Dados Guardados

2.1. Base de dados

- A Base de dados irá conter informações da empresa, informações e credenciais dos utilizadores, informações dos veículos e informações de tudo relacionado aos veículos: Abastecimentos, Viagens Realizadas, Manutenção Programada e referente Histórico e por fim uma lista de todos os seguros do Veículo até ao atual.
- A baixo podemos ver a modelização planeada para a implementação deste projeto.

![Image](https://i.imgur.com/dxpe8AP.png)


2.2. Ficheiro de Preferências

- O Ficheiro XML (de preferências) irá guardar o _“username”_ do Utilizador assim como um _access token_ para manter a sessão iniciada no dispositivo.

- Poderá eventualmente também guardar preferências do utilizador que foram alteradas nas definições da aplicação.
