# :construction: README customizado em construção ! :construction:
<!-- uma aplicação simplificada de gerenciamento de horários de transporte interestadual
simula uma aplicação de gerenciamento de horários de transporte interestadual, contendo entidades como Station, BusLine e Schedule.
A entidade Station representa uma rodoviária com atributos id e name, representando o identificador único e o nome da rodoviária, respectivamente.

A entidade BusLine representa um itinerário e possui atributos id, code e name, representando o identificador único, o código e o nome da linha de transporte, respectivamente.

Por último, a entidade Schedule representa um horário de partida para uma linha de transporte e contém atributos id, departureTime, departureDate e available. Aqui, id é o identificador único do horário, departureTime e departureDate representam a hora e a data da partida, respectivamente, e available representa se o agendamento está em aberto (disponível) ou não.

Existe um docker-compose.yml que inicializa um container com o MySQL 8.0.31 que, de forma automática, cria o banco de dados trybetrackdb;
Existe um arquivo insomnia-endpoints.json que pode ser carregado no Cliente Rest Insomnia contendo exemplo de requisições para todos os endpoints da aplicação TrybeTrack;
Ao inicializar a aplicação TrybeTrack as tabelas do banco de dados são criadas e populadas automaticamente com dados estáticos e outros aleatórios. Logo, pode ocorrer de dados serem apresentados no conteúdo e não serem iguais aos armazenados no seu banco de dados;
Ao finalizar a aplicação TrybeTrack as tabelas do banco de dados são excluídas junto com os dados armazenados.
-->
