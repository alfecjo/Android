# ☕ Android..
![android](android.jpg)

## Este material foi desenvolvido em resposta a disciplina 'Android para Dispositivos Móveis', a qual faz parte do curso de Pós Graduação em Tecnologia Java, ministrado pela Universidade Tecnológica Federal do Paraná.
🎉 Os projetos (contidos nos diretórios), são entregas, dependêntes entre si, ou seja, o projeto final é composto pela soma de todas as entregas, cada uma em sua devida fase de projeto. Sendo Assim, a entrega de maior valor, exemplo: entrega3, é composta por todas as entregas inferiores + ela. Todas foram solicitadas ao longo do curso e juntas perfazem a nota que compõem a média final.

🥋 Se você está entrando no Java agora, vou deixar um comentário apenas para orientá-lo, caso considere o código destas entregas estranho. Existem todos os níveis de dificuldade, entretanto, as duas últimas foram consideradas pelo professor e os demais colegas, como mais HARD. Aqui, não estamos falando de Java tradicional e sim, Java no eco-sistema Android, até a IDE muda (são tratados assuntos como: banco de dados, gerenciamento de tela, CRUD completo, particularidades do DevAndroid e muito mais...) e sem exageros, caso não entenda de primeira, continue tentando, pois, o sucesso não só reside nos melhores, mas também, nos persistentes, que considero meu caso (_😎tirei nota 9.9 rsrsrs♻️). Desenvolver software, nada mais é que descartar a possibilidade de desistência, independente de qualquer motivo!

## Deve-se utilizar:

   - O Android Studio Dolphin 2021.3.1 ou superior (apenas versões estáveis);
   - Versão do Gradle específica da versão do Android Studio utilizada;
   - Minimum API Level 16 (minSdkVersion 16 no build.gradle (Module: app), que irá alterar a geração do AndroidManifest.xml);
   - targetSDK Version para a API Level 31 (Android 12.0) ou superior.

## 1️⃣ Entrega 01:

### 👨‍💻 Crie o projeto de um aplicativo com Nome relacionado ao Tema Escolhido e Aprovado pelo professor.

- Neste projeto crie uma Activity que implemente um formulário de cadastro de uma das entidades previstas com as seguintes características:

   - Uso de elementos Textview; 
   - Uso de elementos EditText (pelo menos 1);
   - Uso de elementos RadioButton (pelo menos 2) com pelo menos um RadioGroup; 
   - Uso de elementos CheckBox (pelo menos 1);
   - Uso de elementos Spinner (pelo menos 1);
   - Uso de 2 elementos Button;
   - Um dos Buttons deve ter o rótulo "Limpar" e ao ser clicado limpará os valores dos elementos EditText e desmarcará os RadioButtons e CheckBox, e depois será mostrado um Toast indicando a ação realizada;
   - Um dos Buttons deve ter o rótulo "Salvar" e ao ser clicado irá pegar os valores dos elementos EditText, CheckBox, Spinner e o RadioButton selecionado, e validar estes valores. Caso algum EditText esteja vazio ou nenhum RadioButton selecionado, deverá ser mostrado uma mensagem de erro em um Toast e o foco de edição voltará para o campo vazio (caso seja possível).
   - Caso o formulário de cadastro fique maior do que a tela do dispositivo será necessário colocar barra de rolagem, para tal, utilize a classe ScrollView ou HorizontalScrollView.

## 2️⃣ Entrega 02:

### 👨‍💻 Faça uma nova versão do projeto submetido na Entrega 1 criando uma nova Activity que listará itens, estes serão objetos de um tipo de Entidade relacionada ao Tema do Projeto. 

- Neste projeto além do entregue na versão anterior, deve-se:

   - Uso de elementos Textview;
   - Criar uma classe de Entidade relacionada ao Tema do Projeto (Pelo menos 4 atributos);
   - Carregar de Arrays do Resource um conjunto de dados (pelos menos 4 tipos de informação) que possibilite a instanciação de objetos da Entidade (Pelo menos 10);
   - Armazenar as Entidades instanciadas em um ArrayList;
   - Criar uma Activity que exiba um componente de listagem de itens ocupando toda tela, pode ser uma ListView ou RecyclerView (para a disciplina recomendo ListView);
   - Criar um Adapter customizado para exibir os dados de cada Entidade na listagem de Itens;
   - Ao clicar em um item deve-se mostrar uma mensagem em um Toast indicando que o mesmo foi clicado. A mensagem deve conter informações que identifiquem o elemento;
   - A Activity criada de listagem deve ser a principal do Aplicativo (Launcher), para tal no AndroidManifest.xml ela terá mapeada a ação de MAIN e a categoria LAUNCHER. Dica: ao criar uma nova Activity em um projeto já existente marque o item Launcher na tela Assistente de criação (Wizard).
   - Neste Projeto a Activity criada na Entrega 1 permanecerá no projeto, porém não será utilizada pelo usuário.
 
## 3️⃣ Entrega 03:

### 👨‍💻 Faça uma nova versão do projeto submetido na Entrega 2 criando uma nova Activity que exibirá os dados de autoria do App, e também efetivará a transição entre as Activities feitas anteriormente. 

- Neste projeto além do entregue na versão anterior, deve-se:

   - Crie uma Activity que exibirá os dados de Autoria do App, são eles: Nome do aluno, curso e e-mail, breve descrição do que faz o aplicativo, logo e nome da UTFPR;
   - Altere a Activity de Listagem de dados (Feito na Entrega 2), que agora não irá carregar dados de Arrays do Resource, e sim exibir dados cadastrados pela Activity de Cadastro. Para tal coloque no layout:
   - Button com rótulo Adicionar, que ao ser clicado abrirá a Activity de cadastro esperando um resultado (startActivityForResult);
   - Button com rótulo Sobre, que ao ser clicado abrirá a Activity de Autoria do App (startActivity). 
Na Activity de Cadastro (Feito na Entrega 1):
   - Ao clicar no Button "Salvar" deverá ser recuperado os dados da interface, validados e devolvidos a Activity de Listagem com o método setResult e resultado RESULT_OK;
   - Ao executar a ação de Voltar do sistema Android devolva RESULT_CANCELED, para tal inclua o setResult dentro do método que sobrescreve o onBackPressed().
   - Na Activity de Listagem trate o retorno da Activity de Cadastro dentro do método que sobrescreve o onActivityResult. Neste método receba os valores retornados, crie um objeto da entidade (Feita na Entrega 2), adicione ao ArrayList relacionado ao Adapter customizado, e por fim chame o método notifyDataSetChanged() do Adapter que forçará o redesenho dos itens dentro da ListView ou RecyclerView.

🕵️ Critérios de aceite e avaliação do exercício:



🎯 Importante:



# Tecnologia utilizada:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Android](https://img.shields.io/badge/java%20android-%3DDC84.svg?style=for-the-badge&logo=android&logoColor=white)

## Tabela de Conteúdos

- [Instalação](#Instalação)
- [Uso](#Uso)
- [Contribuição](#Contribuição)

## Instalação

1. Clone o repositório ou baixe o arquivo .zip:

```bash
git clone https://github.com/alfecjo/Java-II.git
```
## Uso

1. Execute em sua IDE de preferência. Contudo, o desenvolvimento foi feito no IntelliJ! Você pode começar com: "mvn install", no diretório raiz, que é onde se encontra o
   arquivo pom.xml. Desta forma, serão baixadas as dependências, caso seja necessário.

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra um problema ou envie uma solicitação pull ao repositório.

Ao contribuir para este projeto, siga o estilo de código existente, [convenções de commit](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em um branch separado.

Muito obrigado!!
