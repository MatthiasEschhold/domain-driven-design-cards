# Vertikal denken, fachlich schneiden und ausdrucksstark implementieren

Clean Architecture ist erwachsen geworden. Zehn Jahre nach der ursprünglichen Definition von 
Robert C. Martin, etabliert sich die letzten Jahre im allgemeinen, eventuell auch im 
zusammenhang stehend mit der Verbreitung von Domain-Driven Design, 
eine spannende Weiterentwicklung: 

Weg von horizontalen Schichten – hin zu **vertikalen fachlichen Schnitten**,
anhand fachlicher Aspekte, wie z.B. Features, Anwendungsfälle und 
Komponenten die sich gemeinsam ändern

Das taktische Domain-Driven Design (DDD) liefert bewährte Entwurfsprinzipien
für die Implementierung fachlicher Module, die sogenannten **Aggregates**. 
Die Root Entity (aka Aggregate Root) ist der natürliche Ausgangspunkt für die fachliche 
Modularisierung. In ihr bündeln sich Features und Anwendungsfälle, die sich gemeinsam ändern und 
weiterentwickeln.

Die horizontale Schichtenarchitektur sowie die Clean Architecture, wie sie in Teil 1 beschrieben 
wurden, sind Schichtungen anhand technischen und architektonischer Eigenschaften. 
Vertikale Schichten richten sich nach der Fachlichkeit aus. Konkret bedeutet dies, 
anstatt Controller-, Service- und Repository-Pakete zu definieren, bestimmen Root Entities,
wie z.B. die `GarageOrder` die Modularisierung, in unserem Beispiel ausgedrückt durch die Paketierung
der Java-Anwendungen.

---

## 🧱 Von horizontal zu vertikal: Der Paradigmenwechsel

Die Aufteilung vieler Java-Anwendungen folgt bis heute einer horizontalen Schichtung, 
wie beispielsweise in Listing 1 dargestellt. 

Das Anwendungssystem unterteil sich in drei Schichten - `api`, `logic` und `infrastructure`.
Auf der `api`-Schicht sind Codeelemente für technische Zugangspunkte zur Anwendung verortet, z.B.
REST-Schnittstellen für ein Frontend und einen externen Web-Service, der die API konsumiert.

Die `api`-Schicht darf auf die `logic`-Schicht zugreifen. Dort sind die Geschäftslogik, 
für den Ablauf von Anwendungsfällen, sowie die Geschäftsregeln verortet.

Die `logic`-Schicht kapselt den Zugriff auf technische Infrastruktur, wie eine Datenbank oder 
externe Web-Services.

> **Die goldene Regel:**
> 
> Zugriffe sind in der horizontalen Schichtenarchitektur ausschließlich von oben nach unten erlaubt! 

Die Integration der technischen Infrastruktur befindet sich auf der `infrastructure`-Schicht. 
Dort ist ebenfalls das fachliche Modell, als Modell der Persistenz, angesiedelt. 
Die goldene Zugriffsregel erlaubt, das alle Schichten auf dem zentralen Modell operieren. 
Dies führt zu der Kerneigenschaft der Datenzentrierung in horizontale geschichteten Architekturen.

```
|-- de.arkem.javapro.layers
    |-- api
    |   |-- ui
    |   |-- externalservice
    |-- logic
    |   |-- service
    |   |-- rules
    |-- infrastructure
    |   |-- db
    |   |   |-- repository
    |   |   |-- entity
    |   |-- externalservice
    |   |-- ...    
```
[Listing 1: Beispielhafte horizontale Schichtung einer komplexeren Geschäftsanwendung]()

Die horizontale Schichtung wirkt auf den ersten Blick klar strukturiert. 
In der Praxis entstehen jedoch unerwünschte Abhängigkeiten zwischen fachlich vermeidlich 
unabhängigen Anwendungsfällen und Geschäftsobjekten. Gepaart mit Architektur Smells hinsichtlich 
der Schichtendefinitionen, führt zu einer hohen Kopplung im System. Gerate Softwaresysteme in diese 
Abwärtsspirale, nimmt die Erosion bis zur unwartbarkeit des Systems schleichend zu.

Software-Erosion beschreibt den schleichenden Prozess des Qualitätsverlusts, durch den stetigen Aufbau von
technischen Schulden sowie Einsparungen an qualitätssichernden Maßnahmen, wie z.B. für die Sicherung einer
stetig guten Codequalität.

Ein vergleichbares Phänomen entsteht in der horizontalen Schichtenarchitektur. 
Der Effekt der **logischen Erosion** entsteht, wenn die Struktur des Codes nicht der Struktur 
der Fachlichkeit folgt (Abb. 1). Die logische, nicht sichtbare Struktur, verwässert mit der Zeit und ungewünschte Kopplung
zwischen logischen fachlichen Modulen entsteht. Die Komplexität der Architektur steigt zusätzlich,
da die Fachlogik über mehrere horizontale Schichten verteilt wird.

Die sich stetig aufbauend unerwünschten Abhängigkeiten führen in weiteren Lebenszyklus zu einem
steigenden Risiko an Seiteneffekten bei Änderungen und Erweiterungen des Systems.

[Abb. 1: Logische Schichten in horizontaler Schichtenarchitektur]()

Die Alternative ist die vertikale Schichtung entlang fachlicher Aspekte, wie z.B. um Geschäftsobjekte, 
Prozesse, Features oder Anwendungsfälle.

Statt die Anwendung anhand technischer und architektonischer Elemente auf der obersten Ebene zu strukturieren,
erfolgt die Strukturierung aufgrund fachlicher Schnitte. 

In unserer Beispielanwendung für die Verwaltung der Geschäftsprozesse und Vorgänge in einem Autohaus, 
sieht einer vertikalen Schichtenarchitektur, wie dargestellt in Listing 2 aus.

```
|-- de.arkem.javapro.verticals
    |-- auftrag
    |-- fahrzeug
    |-- kunde
```
[Listing 2: Vertikale Schichtung der Autohausverwaltung]()


Bei vertikaler Schichtung ergeben in sich abgeschlossene und einfacher wartbare Module.
Dabei enthält jedes fachliche Modul den vollständigen Satz an Klassen, zur Abbildung des Architekturmusters. 
Listing 3 zeigt die interne Strukturierung der vertikalen Schichten anhand der technischen und architektonischen Eigenschaften.

```
|-- de.arkem.javapro.verticals
    |-- auftrag
    |   |-- api
    |   |-- logic
    |   |-- infrastructure
    |-- fahrzeug
    |   |-- api
    |   |-- logic
    |   |-- infrastructure
    |-- kunde
    |   |-- ...
```
[Listing 3: Interner Strukturierung der vertikalen Schichten]()

Vertikale Schichtung ermöglicht:

- Hohe Kohäsion innerhalb eines Moduls – alles gehört zum gleichen fachlichen Zusammenhang.
- Geringe Kopplung zwischen Modulen – Änderungen bleiben lokal.
- Gute Wartbarkeit – der Zusammenhang von Code und Domäne bleibt auch beim Systemwachstum nachvollziehbar.
- Bessere Systemverstehen - die Orientierung anhand Modulschnitte mit fachlicher Semantik, fördert das Systemverstehen und die Produktivität in der Implementierung.
- Unabhängige Evolution - isolierte Änderungen und Erweiterungen im fachlichen Modul, ohne Seiteneffekte.

In der Software Architecture Community, insbesondere in der Domain-Driven Design Community, herrscht Konsens,
dass vertikalen Schichtung der horizontalen Schichtung, auf der obersten Ebene der Anwendungsarchitektur zu
bevorzugen ist.

---

## 🧩 Fachliche Modularisierung durch Aggregates

Die Schnittpunkte für die fachliche Aufteilung ist bei Verwendung des taktischen Domain-Driven Design (DDD) klar:
Root Entities (aka Aggregate Roots) sind der natürliche Ausgangspunkt für modulare Schnitte und 
wird im DDD Aggregate genannt.

Ein Aggregate:

- bildet eine Konsistenzgrenze mit genau einem Root-Objekt
- kapselt den Zugriff auf alle dem Root-Objekt untergeordneten Elemente
- schützt mittels Invarianten die fachliche Modellkonsistenz bei Zustandsübergängen

> **Die goldene Regel:**
> 
> Ein Aggregate entspricht einer vertikalen Schicht und enthält nur eine Root Entity (aka Aggregate Root) je Vertikale.

Die Identifikation von Aggregates muss auf dem fachlichen Verständnis der Domäne basieren und wird
im Idealfall gemeinsam mit Domänenexperten methodisch erarbeitet, z.B. mittels _Design Level Event Storming_, 
_Domain Storytelling_ oder _Class Responsibility Collaboration Cards_.

Die Erkenntnisse durch Anwendung dieser Methoden, führen zu einem gemeinsamen Verständnis, dass ein guter
Aggregate-Schnitt abgeleitet werden kann.

Aggregates grenzen sich voneinander ab, da sie einen eigenständigen Lebenszyklus besitzen. Dies hat zur Folge,
dass jedes Aggregate die Mehrheit seiner Anwendungsfälle isoliert durchführen kann. 
Ein kleiner Teil Anwendungsfälle haben Auswirkungen auf mehrere Aggregates, was Transaktionen und Operationen 
über Aggregate-Grenzen bedingt. Dies sollten Ausnahmen darstellen.

In der Autohausverwaltung stellen bereits, die in Listing 2 eingeführten vertikalen Schichten `auftrag`, 
`kunde` und `fahrzeug` Aggregates dar. 

## 🧩 Implementierung des Entities-Ring mit taktischen DDD

Wie bereits in Teil 1 beschrieben, werden Eigenschaften, Verhalten und Regeln in der Entität gekapselt.
Dies basiert auf der Idee des [Rich Domain Model]() nach Martin Fowler. In der DDD Community gilt ein Rich
Domain Model als bessere Praktik im vgl. zu einem anemischen Modell, dass nur die Eigenschaften
der Entität kapselt. Verhalten und Regeln werden außerhalb implementiert. 

Die Argumente für ein Rich Domain Model, liegen in der ausdrucksstarken Abbildung der Fachlichkeit 
über ein Modell, sowie ein kohäsiven Modellaufbau, der die Sicherung der fachlichen Modellkonsistenz 
einfach ermöglicht. Dies sind Ziele im taktischen DDD, die mit weiteren Entwurfsmuster und -prinzipien 
ausgebaut werden. Diese Eigenschaften einer Root Entity sollen durch Listing 4 anhand der Root Entity
`Auftrag` mit Verhalten `freigeben` und dazugehörigen Regeln 
`zusammenhang Status Abgelehnt und einem notwendigen Ablehnungsgrund`.

```java
public class Auftrag {

    private final Auftragsnummer auftragsnummer;
    private final List<Auftragsposition> positionen;
    private Auftragsfreigabe freigabe;

    public Auftrag(Auftragsnummer nummer, List<Auftragsposition> positionen) {
        if (positionen.isEmpty()) throw new IllegalStateException("Ein Auftrag muss Positionen enthalten.");
        this.auftragsnummer = nummer;
        this.positionen = List.copyOf(positionen);
        this.freigabe = new Auftragsfreigabe(FreigabeStatus.OFFEN);
    }

    public void freigeben(FreigabeStatus status, Optional<Ablehnungsgrund> grund) {
        if (status == FreigabeStatus.ABGELEHNT && grund.isEmpty()) {
            throw new IllegalStateException("Ablehnungsgrund erforderlich!");
        }
        this.freigabe = new Auftragsfreigabe(status, grund);
    }
}
```
[Listing 4: Root Entity Auftrag implementiert nach dem Rich Domain Model Prinzip]()

## Die Mustersprache von DDD

Die Entwurfsprinzipien des taktischen DDD 

| DDD-Baustein                           | Beschreibung                                                                                                                                                                                                                                                                          |
|----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Entity**                             | Geschäftsobjekt, der realen oder digitalen Welt, mit eindeutiger Identität und Lebenszyklus innerhalb einer Root Entity.                                                                                                                                                              |
| **Root Entity** <br/> (Aggregate Root) | Führendes Geschäftsobjekt mit eindeutiger Identität, dass eine fachliche Konsistenzgrenze definiert und den Top-Level Lebenszyklus eines Domänenobjekt-Graphen definiert.                                                                                                             |
| **Value Object**                       | Fachlicher Wert, zur Abbildung der Eigenschaften eines Geschäftsobjekts, welches immutable zu implementieren ist, und für die Prüfung auf einen validen fachlichen Wert verantwortlich ist.                                                                                           |
| **Domain Service**                     | Die Abbildung von Verhalten an Entities ist limitiert, auf Verhalten, dass auf Basis der inhärenten Eigenschäften der Entität ausgeführt werden kann. Wird Geschäftslogik benötigt, die diese Entwurfsprinzip nicht erfüllt, wird in einem zustandlosen Domain Service implementiert. |
| **Factory**                            | +                                                                                                                                                                                                                                                                                     |

Die folgende Abbildung zeigt die detaillierte Modellierung des Root Entity `Auftrag` konform zu den 
DDD Prinzipien.

[Abb. 2: Root Entity Auftrag]()

## Abgrenzung und Abhängigkeiten zwischen Root Entities

[Abb. 3: Abhängigkeiten zwischen Root Entity bzw. Aggregates am Beispiel Auftrag und Kunde]()

## 🧭 Fazit: Paket- und Klassenstruktur in der Vertical Clean Architecture

Jedes vertikale Modul ist **autonom** – fachlich und technisch. Es kapselt seinen eigenen Satz an:

- **Domain Objects** und **Domain Logic** (Entities, Value Objects, Domain Services)
- **Use Cases** und **Interactors**
- **Interface Adapters** und **Mappers**

Listing 5 zeigt das Beispiel für das Modul `Auftrag`, welche in der Grundform bereits in Teil 1
eingeführt wurde. Die dort bereits dargestellte Klassen sind in Listing 5 aus Gründe der Übersichtlichkeit 
vernachlässigt. Weiter erfolgt in diesem Schritte eine **Umbenennung** des Entities Ring 
orientiert an der DDD-Terminologie in **Domain Ring**. Auf dem Domain Ring wird das Domänenmodell, bestehend aus
Root Entity, Entity und Value Object, sowie ergänzend benötigte Domain Services verortet.

```
|-- de.arkem.javapro.vertical.cleanarc
    |-- auftrag
    |   |-- adapter
    |   |-- usecase
    |   |-- domain
        |   |-- model
            |   |-- Auftrag.java
            |   |-- Auftragsnummer.java
            |   |-- Auftragsstatus.java
                |-- KundennummerRef.java
        |   |-- service
    |-- fahrzeug
    |   |-- ...
    |-- kunde
    |   |-- ...
```
[Listing 5: Strukturierung der vertikalen Schichten und des Domänenmodells mittels Clean Architectur]()