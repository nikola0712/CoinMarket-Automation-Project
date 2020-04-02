@Frontend

Feature: Frontend tasks

   Scenario: Frontend tasks with 3 tests
     #Frontend-Task-1
     Given Market coin landing page is open
     When View All button is clicked
     Then Result of "100" crypto currencies is returned

     #Frontend-Task-2
     And Select "10" random crypto currencies from list and compare them on WatchList tab

     #Frontend-Task-3
     When Market coin landing page is open
     Then Open dropdown of Crypto Currency tab and click on Full List
     And Compare recorded data on full list and filtered data