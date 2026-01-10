# Mobiiliohjelmointi viikkotehtävä 1 - Kotlin-perusteet

Ohjelma esittää simppeliä ToDo-sovellusta. Sovelluksessa voi lisätä tehtäviä ja merkata niitä tehdyiksi. Tehtäviä on mahdollista järjestää eräpäivän mukaan, tehtäviä voi myös suodattaa tehtyjen tehtävien perusteella.

Esittelyvideon näkee täältä [https://www.youtube.com/shorts/iUBMVSivUqQ](https://www.youtube.com/shorts/iUBMVSivUqQ)

## Mock Data

Projektiin on luotu MockData.kt-tiedosto, jonka avulla sovellus näyttää ennalta määritettyjä tehtäviä, tehtävän rakenne ja muuttujat on määritetty Task.kt-tiedostossa. Tehtävän muuttujiin kuuluu tehtävän indeksi (id),
otsikko (title), kuvaus (description), tärkeys (priority), eräpäivä (dueDate) ja mikäli tehtävä on merkattu tehdyksi (done).

## Funktiot

**addTask**

addTask-funktiolla lisätään tehtävä tehtävälistaan.

**toggleDone**

toggleDone-funktio mahdollistaa tehtävät merkkaamisen tehdyksi/keskeneräiseksi.

**filterByDone**

filterByDone-funktio suodattaa tehtävälistan tehtyjen tehtävien perusteella. Tämä funktio ei toimi täydellisesti.

**sortByDueDate**

sortByDueDate-funktio järjestää tehtävälistan eräpäivän mukaan, kauimmainen eräpäivä ensin.
