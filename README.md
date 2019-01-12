projchatSO - Sistemas para Internet
===================================

Projeto Chat - Sistemas Operacionais.

O projchatSO é um chat que realiza tudo o que um chat comum já faz, com a única diferença de ser completamente transparente em suas ações. Aqui você poderá visualizar os pacotes enviados, os pacotes recebidos, o tamanho destes pacotes, as informações(bytes) contidos nestes, assim como outros detalhes muito interessantes. Com a dinâmica entre Servidor e Cliente, pretendemos demonstrar na prática assuntos abordados em sala de aula pelo professor.

**Prévia tela de registros de conectados.**

![enter image description here](https://lh3.googleusercontent.com/-d2p1P0SlFqg/WgsdRQ41W1I/AAAAAAAAAMo/MrOpGmnIebcc93Vkcujr6-bSzGcK_iiWgCLcBGAs/s500/servidor1.png "Servidor")


![enter image description here](https://lh3.googleusercontent.com/-7imZop1sia4/Wgsdn-K7oBI/AAAAAAAAAMw/t3j3w2YhYWAlIC452sDoB0KkPP5NKdzogCLcBGAs/s500/servidor2.png "Servidor")

Funcionamento básico do Servidor
--------------------------------

 - Ao ser ligado, o servidor criará uma Thread para escutar, em loop infinito, uma determinada porta. Dessa forma, assim que algum cliente se conectar a esta porta, o servidor criará mais uma Thread para escutar tudo o que o cliente enviar para o Servidor. Ao final, teremos diversas Thread funcionando; cada uma delas dando atenção a apenas um cliente específico.
