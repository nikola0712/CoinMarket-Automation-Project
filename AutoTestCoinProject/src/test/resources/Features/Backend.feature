@Backend

Feature: Backend tasks

  Scenario: Backend tasks with 3 tests

    #Backend-Task-1
    When Ids of crypto currencies are retrieved
    Then Crypto Currencies are converted to Bolivian Boliviano currency

    #Backend-Task-2
    And Crypto Currency info is performed

    #Backend-Task-3
    And Retrieve Currencies from Crypto Currency Info call
    | id | Currency    |
    | 1  | Bitcoin     |
    | 2  | Litecoin    |
    | 3  | Namecoin    |
    | 4  | Terracoin   |
    | 5  | Peercoin    |
    | 6  | Novacoin    |
    | 7  | Devcoin     |
    | 8  | Feathercoin |
    | 9  | Mincoin     |
    | 10 | Freicoin    |
