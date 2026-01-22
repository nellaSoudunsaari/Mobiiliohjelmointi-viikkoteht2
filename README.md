# Mobiiliohjelmointi viikkotehtävä 2 - ViewModel

Ohjelma esittää simppeliä ToDo-sovellusta. Sovelluksessa voi lisätä tehtäviä ja merkata niitä tehdyiksi. Tehtäviä on mahdollista järjestää eräpäivän mukaan, tehtäviä voi myös suodattaa tehtyjen tehtävien perusteella.

Esittelyvideon näkee täältä [https://www.youtube.com/shorts/iUBMVSivUqQ](https://www.youtube.com/shorts/iUBMVSivUqQ)

## Funktiot

**addTask**

addTask-funktiolla lisätään tehtävä tehtävälistaan.

**toggleDone**

toggleDone-funktio mahdollistaa tehtävät merkkaamisen tehdyksi/keskeneräiseksi.

**filterByDone**

filterByDone-funktio suodattaa tehtävälistan tehtyjen tehtävien perusteella.

**sortByDueDate**

sortByDueDate-funktio järjestää tehtävälistan eräpäivän mukaan, kauimmainen eräpäivä ensin.

**deleteTask**

Poistaa tehtävän tehtävälistasta id:n perusteella.

## Miten Compose-tilanhallinta toimii?

Tila(state) on muuttuva arvo joka Compose:sta puhuttaessa vaikuttaa siihen miltä käyttöliittymä näyttää. Esimerkkejä muuttuvista tiloista ovat mm. listan sisältö, valittu/ei valittu checkbox ja tekstikentän sisältö.
Kun tilan muutos havaitaan, Compose päivittää ne käyttöliittymän elementit, joihin tila on kytkettynä. Tätä kutsutaan myös nimellä rekompositio(eng. recomposition).

## Miksi ViewModel on pelkkää remember:iä parempi?

ViewModel on Android Architecture Components -komponentti, joka säilyttää tilan konfiguraation muutosten yli. Eli vaikka kääntäisit näyttöä, tila pysyy konfiguroituna. ViewModel myös erottaa toimintalogiikan käyttöliittymästä, helpottaa testaamista ja integroituu hyvin muiden Architecture Components -komponenttien kanssa. 
