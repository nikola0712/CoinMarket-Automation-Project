$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/Backend.feature");
formatter.feature({
  "name": "Backend tasks",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Backend"
    }
  ]
});
formatter.scenario({
  "name": "Backend tasks with 3 tests",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Backend"
    }
  ]
});
formatter.step({
  "name": "Ids of crypto currencies are retrieved",
  "keyword": "When "
});
formatter.match({
  "location": "BackEndTask.getCoinIds()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Crypto Currencies are converted to Bolivian Boliviano currency",
  "keyword": "Then "
});
formatter.match({
  "location": "BackEndTask.convertToOtherCurrency()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Crypto Currency info is performed",
  "keyword": "And "
});
formatter.match({
  "location": "BackEndTask.cryptoCurrencyInfo()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Retrieve Currencies from Crypto Currency Info call",
  "rows": [
    {
      "cells": [
        "id",
        "Currency"
      ]
    },
    {
      "cells": [
        "1",
        "Bitcoin"
      ]
    },
    {
      "cells": [
        "2",
        "Litecoin"
      ]
    },
    {
      "cells": [
        "3",
        "Namecoin"
      ]
    },
    {
      "cells": [
        "4",
        "Terracoin"
      ]
    },
    {
      "cells": [
        "5",
        "Peercoin"
      ]
    },
    {
      "cells": [
        "6",
        "Novacoin"
      ]
    },
    {
      "cells": [
        "7",
        "Devcoin"
      ]
    },
    {
      "cells": [
        "8",
        "Feathercoin"
      ]
    },
    {
      "cells": [
        "9",
        "Mincoin"
      ]
    },
    {
      "cells": [
        "10",
        "Freicoin"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BackEndTask.cryptoCurrencyInfoCurrenciesDataTable(DataTable)"
});
formatter.result({
  "status": "passed"
});
});