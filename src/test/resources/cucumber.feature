Feature: A mensagem será recebida, validada e será enviada ao sistema B
  Scenario: A mensagem será validada com sucesso
    Given que o sistema A enviou uma mensagem "Hello"
    And que será validada com sucesso
    When aplicação receber essa mensagem
    Then produzirá uma mensagem ao Sistema B "Hello"