Feature: A mensagem será recebida, validada e será enviada ao sistema B.

  Scenario: A mensagem será validada com sucesso
    Dado que o sistema A enviou uma mensagem "Hello ET"
    E que será validada com sucesso
    Quando aplicação receber essa mensagem
    Então produzirá uma mensagem ao Sistema B "Hello ET"