<h1>Comunicação Entre Macacos Ververts</h1>

<h2>O contexto</h2>
<p>Assim como em outras espécies, Macacos Vervets possuem alguns predadores dos quais precisam se proteger diariamente, tais como a águia, o tigre e a cobra. Para garantir a proteção dos membros de seu grupo e de si mesmos, esses macacos usam alarmes que informam a presença de seus predadores no ambiente. Esse alarme, por sua vez, é adaptativo, de forma que ao possuir variáções para cada predador, os alarmes permitem que estratégias de fuga eficientes para cada tipo de predador sejam tomadas pelos macacos. </p>
<p>Todavia, esses macacos não nascem com essa linguagem compartilhada entre os membros, o que impede que a comunicação possa ser feita de forma eficiente e garantindo a sobrevivência dos membros do grupo. Assim, é necessário que aprendam juntos essas regras e que elas se tornem um comportamento comum entre eles. </p>

<h2>A representação do problema</h2>
<p>No contexto dos macacos Vervets, podemos entender que a linguagem nada mais é que um <b>conjunto de dez símbolos que possuem pesos de associação com cada macaco</b>. Isso quer dizer que, quando um macaco precisa alertar sobre um determinado predador, ele consulta sua matriz de pesos e escolhe o símbolo que possui a maior probabilidade <b>p(Símbolo, Predador)</b> associada ao predador em questão. Assim, podemos mapear essa estrutura em uma matriz Simbolo x Predador, conforme o exemplo abaixo. </p>

<br>

<p align="center">

| <b>Símbolos |   <b>Cobra   |   <b>Tigre   |   <b>Águia   |
|-------------|--------------|--------------|--------------|
|    S1    |p(S1, Cobra)|p(S1, Tigre)|p(S1, Águia)|
|    S2    |p(S2, Cobra)|p(S2, Tigre)|p(S2, Águia)|
| ...|...|...|...|
|Sn|p(Sn, Cobra)|p(Sn, Tigre)|p(Sn, Águia)|

</p>

<br>

<p>Dito isso, cada macaco contém uma estrutura similar em seu interior. E a partir disso surge o problema: como fazer com que o símbolo com a maior probabilidade para cada predador seja o mesmo entre os macacos? </p>

<h2>A nossa solução</h2>
<p>Nessa versão do sistema, optamos por construir um ambiente de simulação simples no qual não existem os conceitos de morte, reprodução e fuga. Assim, nossos agentes (os macacos) podem apenas avistar predadores, alertar o grupo sobre os predadores avistados e ouvir os alertas emitidos pelos outros macacos. Assim, seus sensores são dados pela visão e pela audição, enquanto seus atuadores são dados pela voz. </p>

<h3> A movimentação dos animais </h3>
<p>No que diz respeito à movimentação dos animais no ambiente, optamos por uma função que leva em conta a velocidade de cada animal. Isso quer dizer que, dada uma matriz ambiente de qualquer ordem quadrática, os animais se movem para qualquer uma das oito direções possíveis (norte, sul, leste, oeste, sudeste, sudoeste, noroeste e nordeste) conforme a velocidade determinada para eles no momento de seu instanciamento. Por exemplo, cobras e macacos se movem de 1 em 1, enquanto tigres se movem de 2 em 2. Assim, conseguimos cobrir uma maior área em ambientes maiores, como uma matriz ambiente 50x50. Assim, para cada iteração, a função movimento dos animais sorteia uma direção e verifica se a mesma é possível (se não ultrapassa os limites da matriz). Caso seja um movimento válido, o objeto é deslocado e a nova posição é guardada dentro de um vetor do animal (usado para o calculo da movimentação em pixels na interface gráfica). </p>


<h3> O aprendizado </h3>
<p>Na medida em que os macacos se movem em uma área que possui outros animais em movimento, eles usam seu raio de visão e audição para guardar informações sobre o ambiente. Isso quer dizer que, quando um objeto predador é capturado por seu raio de visão, além de gritar o símbolo com a maior probabilidade, ele também guarda o próprio predador em sua memória. Ao mesmo tempo, caso seu raio de audição capture alguma informação (um alerta de outro macaco), o símbolo capturado é salvo em sua memória. </p>
<p>É a partir da memória de cada macaco que o aprendizado ocorre. Nesse caso, optamos pelo uso de uma lógica de reforço e punição simples e que pode ser modificada futuramente. Assim, quando um macaco possui tanto um predador quanto um símbolo alertado por outro macaco em sua memória, há um reforço que incrementa a probabilidade do simbolo ouvido ser usado para o predador avistado. Ao mesmo tempo, caso o macaco verifique em sua matriz interna a situação em que um mesmo símbolo é o que possui a maior probabilidade de uso para mais de um predador, sua probabilidade é decrementada para um deles. Isso é feito para garantir que símbolos diferentes sejam usados para cada predador, impossibilitando sinônimos ou palavras ambuíguas. Por fim, tanto o incremento quanto o incremento seguem uma taxa de aprendizado que pode ser setada no início da simulação. </p>

<h3>A condição de parada </h3>
<p>Como qualquer simulação, um objetivo deve ser considerado para que ela seja encerrada. Nesse caso, o nosso objetivo é o de alcançar uma linguagem comum entre os macacos que, num contexto predatório em que a morte é uma possibilidade, permita a sobrevivência do grupo.</p>
<p>Em nosso sistema, essa linguagem é dada por um vetor de tamanho 3, em que cada item contempla o símbolo com a maior probabilidade uso para cada um dos predadores. Assim, um vetor [1,3,5] significa que o macaco em questão tem a maior probabilidade de usar 1 para a cobra, 3 para o tigre e 5 para a águia. Dessa forma, para cada iteração do problema, os vetores de linguagem são comparados e, quando forem exatamente iguais, a simulação é encerrada.</p>

<p>Isso quer dizer, então, que o nosso objetivo para com o programa é a obserção da convergência léxica entre os macacos. Ainda não trabalhamos com a formação de gramáticas e entendemos que o sistema poderia ser expandido tal que passasse a considerar outros comportamentos, como a possibilidade de sinônimos, comportamento de fuga, esconderijos, morte e reprodução.</p>

<br>
<p align = "center">
<img src= "vervetSimulation/imgs/sim_vervet_monkeys.gif">
<br>
Exemplo de convergência léxica no programa.
</p>

<h2>Recursos utilizados:</h2>

<img alt="Eclipse" src="https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white"/>
<img alt="Java" src="https://img.shields.io/badge/Java_JDK_19-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>
<a href = "https://processing.org"><img alt="Biblioteca PApplet" src="https://img.shields.io/badge/PApplet-5C2D91.svg?style=for-the-badge&logo=openjdk&logoColor=white"/></a>
<img alt="Windows 11" src="https://img.shields.io/badge/Windows%2011-0078D4.svg?style=for-the-badge&logo=Windows-11&logoColor=white"/>
<br>

<h2>Requisitos de funcionamento:</h2>

- Eclipse IDE (2023-6+)
- Java JDK-19+



<h2>Instalação do projeto</h2>

1. Fazer o clone ou baixar o projeto
    ``` 
    git clone -b main https://github.com/henrimachado/vervetSimulation
    ```
2. Abrir o diretório do projeto pela IDE

3. Navegue:

    ```
    vervetSimulation > src > Default > vervetSimulation.java
    ```

4. Execute o sistema



<h2>Modificando variáveis de configuração</h2>

1. Navegue:

    ```
    vervetSimulation > src > Entidades > Config.java
    ```

2. Abra o arquivo da classe Config.java
3. Altere as variáveis desejadas. Atente-se para as que podem ser alteradas sem comprometer o funcionamento do programa: 
    ```
    largura
    altura
    raioVisao
    raioAudicao
    taxaAprendizado
    ```
    Para as demais, a modificação não deve ser feita.


<h2> Recursos adicionais </h2>
Em caso de não funcionamento da biblioteca PApplet, faça uso dos recursos abaixo para sua instalação:

1. <a href = "https://processing.org/download"> Download da Biblioteca </a>
2. <a href = "https://www.youtube.com/watch?v=sOGUweK8cUI">Tutorial de instalação</a>

<h2>Desenvolvedores</h2>

[Henrique Machado](https://www.linkedin.com/in/henrimachado/) • [Iago Fernandes](https://www.linkedin.com/in/iago-fernandes-083309207/) • [Lucas Alexsander](https://www.linkedin.com/in/lucas-alexsander-barbosa-cruz-481bbb21a/)