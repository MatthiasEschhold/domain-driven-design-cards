# Architecture Enabling mittels kollaborativen Architekturentscheidungen

## Kurzbeschreibung Tactical Architecture Game

Das Tactical Architecture Game ist ein Gamification-Ansatz, um den taktischen Konstruktionsplan für die Softwarearchitektur kollaborativ im Team zu finden.

Das Spiel basiert auf einem Set von Architekturentscheidungen, die in Summe den Konstruktionsplan darstellen. Die Entscheidungen stellen richtungsweisende Leitplanken der taktischen Architektur dar, auf die ein Team committet sein sollte. Aufgrund diesen Bedeutung, werden die Entscheidungen als Enabler Architecture Decisions bezeichnet.

Das Tactical Architecture Game, wie hier beschrieben, ist für das taktische Domain-Driven Design in Kombination mit
domänen-zentrischen Architekturmustern Clean, Hexagonal sowie Onion Architecture konzipiert. Durch diese Grundsatzentscheidungen ergeben sich bereits weiche Vorgaben (Soft Standards) für die Architektur. Die Enabler Architecture Decisions beschreibt einekonkret zur Anwendung kommende Lösungsstrategie für die Soft Standards dar.

Die Enabler Architecture Decisions erstrecken sich über vier Entscheidungskategorien:

1. Modularisierung
2. Domänenkern
3. Anwendungsfälle
4. Mappings

Jede Kategorie enthält in der Regel mehrere Entscheidungen.

## Enabler Architecture Decisions

### Entscheidungskategorie Modularisierung

Enabler Architecture Decisions der Entscheidungskategorie Modularisierung haben übergreifenden Charakter und gelten in allen domänenzentrischen Architekturmuster auf gleiche Weise.

#### Architekturentscheidung #1: Vertikale vs. Horizontale Schichtung

###### Vorbedingung

Keine

###### Soft Standard

###### Lösungsstrategien

1. Vertikale und fachlich motivierte Schichtung auf Architekturebene 1
2. Technische Schichtung auf Architekturebene 1 mit fachlicher Schichtung auf Architekturebene 2 und Split Packages

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #2: Wie werden fachliche Module strukturiert?

###### Vorbedingung

| Enabling Architecture Decision |     Entscheidung     |
| ------------------------------ | :------------------: |
| 1                              | Vertikale Schichtung |

###### Soft Standard

Das fachliche Modul bildet sich um ein Aggregate. 
Innerhalb dieses Moduls werden die Verantwortungsbereiche (Ring, Hexagon bzw. Onion) 
des domänenzentrischen Architekturmuster abgebildet. 
Hierfür gibt es verschiedene Lösungsstrategien.

###### Lösungsstrategien

1. Architektonisch ausdrucksstark
2. Architektonisch ausdrucksstark in der fachlichen Applikation
3. Verantwortungsbereiche (Ring, Hexagon bzw. Onion) als Schichten

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #3: Wie wird die Abhängigkeit zwischen zwei Aggregates behandelt?

###### Vorbedingung

Keine

###### Soft Standard

Im taktischen Domain-Driven Design wird eine Entkopplung auf Basis der Grenzen von Aggregates verfolgt. Aggregates haben einen unabhängigen Lebenszyklus.

Falls ein fachlicher Zusammenhang hergestellt werden muss, darf ein Aggregate ein anderes Aggregate nur über dessen Identität referenzieren. Die Referenz muss zur Laufzeit aufgelöst werden.

Abhängigkeiten dieser Art sind musterartig und gleichartig zu behandeln, um die
Komplexität gering und die Verständlichkeit der Architektur hoch zu halten.

Welches Muster als Standard dienen kann und in welchen Szenarien auf alternative Lösungsstrategien zurückgegriffen wird, muss als kollaborative Architekturentscheidung im Team getroffen werden.

###### Lösungsstrategien

1. Application Service Pattern
2. Adapter Out Use Case In Pattern
3. Event-driven / Publish-Subscribe Pattern

#### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

### Entscheidungskategorie Domänenkern

@ToDo

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffene Verantwortungsbereich Two-Way Mapping |
| ---------------------- | :----------------------------------------------: |
| Clean Architecture     |                  Entities Ring                   |
| Hexagonal Architecture |                  Domain Hexagon                  |
| Onion Architecture     |        Domain Service Onion, Domain Onion        |

#### Architekturentscheidung #4: Nach welchem Prinzip wird das Domänenmodell implementiert?

###### Vorbedingung

Keine

###### Soft Standard

Mit der Entscheidung für Domain-Driven Design wird das Ziel verfolgt, ein Domänenmodell herauszuarbeiten und Mittels der Muster des taktischen Design zu implementieren.

Für die Implementierung eines Domänenmodell muss die Grundsatzentscheidung Rich Domain Model versus Anemic Domain Model getroffen werden. Diese Entscheidung legt fest, wie das Domänenmodell implementiert wird und an welchen Stellen Geschäftslogik und -regeln verortet werden.

Das Rich Domain Model besagt, dass die Fachlogik im Domänenobjekt (Aggregate, Entity, Value Object) implementiert wird. Dadurch wird eine ausdrucksstarke Benennung der Methoden ermöglicht, die auf der Fachlichkeit basiert, im Vergleich zu einfachen set- und get-Methoden. Zudem erlaubt es die fachliche Validierung zur Sicherung der Konsistenz einer (Root) Entity ebenfalls direkt am Objekt zu implementieren. Gleiches gilt für Value Objects, die die Validierung des fachlichen Werts implementieren.

Ein Anemic Domain Model enthält nur die Eigenschaften sowie dazugehörige getter und setter. Fachlogik und Validierung werden ausschließlich in Domain Services implementiert. Das Anemic Domain Model gilt als Anti-Pattern, da dies oft zu einem Verlust der Fachsprache sowie zu Konsistenzverletzungen führt. Dennoch muss ein Team diese Entscheidung bewusst treffen.

###### Lösungsstrategien

1. Rich Domain Model
2. Anemic Domain Model

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #5: Wie soll das Value Object Muster in der Implementierung angewendet werden?

###### Vorbedingung

| Enabling Architecture Decision |   Entscheidung    |
| ------------------------------ | :---------------: |
| 4                              | Rich Domain Model |

###### Soft Standard

Wird dem Rich Domain Model gefolgt, entsteht in Teams regelmäßig die Frage, ob jeder Fachwert als Value Object implementiert werden muss. In komplexen Aggregates können potenziell zahlreiche Value Objects entstehen.

Die Empfehlung ist fachliche Werte immer als Value Objects zu implementieren, um die Fachlichkeit zu stärken und die Robustheit zu erhöhen.

Ausnahmen können gemacht werden, wenn fachliche Werte nicht validiert werden müssen oder wenn fachliche Werte Elemente eines verschachtelten Value Objects darstellen.

Auf Value Objects zu verzichten und ausschließlich primitive Datentypen für fachliche Werte zu verwenden, ist ebenfalls eine Möglichkeit, wird jedoch als Anti-Pattern bewertet. Insbesondere bei komplexen Aggregates mit viel Validierungslogik, entsteht über die Zeit schwer lesbarer Code.

###### Lösungsstrategien

1. Jeder fachliche Wert wird als Value Object implementiert
2. Nur fachliche Werte, die Verhalten und/oder Validierung bedingen, werden als Value Object implementiert
3. Jeder fachliche Wert wird mittels primitiven Datentyp implementiert

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #6: Wie soll das Value Object Muster implementiert werden?

###### Vorbedingung

| Enabling Architecture Decision |                              Entscheidung                              |
| ------------------------------ | :--------------------------------------------------------------------: |
| 4                              |                           Rich Domain Model                            |
| 5                              | Implementierung von Fachwerte als eigenes Object (Value Object Muster) |

###### Soft Standard

@ToDo

###### Lösungsstrategien

1. Jeder fachliche Wert wird als Value Object implementiert
2. Nur fachliche Werte, die Verhalten und/oder Validierung bedingen, werden als Value Object implementiert
3. Jeder fachliche Wert wird mittels primitiven Datentyp implementiert

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #7: Wie wird die fachliche Konsistenz bei Verwendung des Anemic Domain Model gesichert?

###### Vorbedingung

| Enabling Architecture Decision |    Entscheidung     |
| ------------------------------ | :-----------------: |
| 4                              | Anemic Domain Model |

###### Soft Standard

Wird das Anemic Domain Model angewendet, muss für die Validierungslogik ein anderes Element als die Fachobjekte selbst gefunden werden. Eine Möglichkeit hierfür ist der Domain Service, jedoch würde dies dazu führen, dass die Verantwortung für Fachlogik und fachliche Validierung in einem Muster verortet wird. Bei erhöhter fachlicher Komplexität, empfiehlt sich daher die Einführung eines neuen Elements in der Mustersprache, wie z.B. ein Validator.

###### Lösungsstrategien

1. Validierungslogik im Domain Services
2. Eigenes Element in der Mustersprache, zugehörig zum Domänenkern

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #8: Wie wird Validierung implementiert?

###### Vorbedingung

Keine

###### Soft Standard

Unabhängig davon, an welcher Stelle die Validierung in der Mustersprache verortet ist, benötigt das Entwicklungsteam eine gemeinsame Vorstellung davon, wie die Validierung implementiert werden soll. Die Möglichkeiten reichen von der nativen Implementierung in der verwendeten Programmiersprache über selbst erstellte Abstraktionen bis hin zum Einsatz von Drittanbieter-Bibliotheken.

###### Lösungsstrategien

1. Native Implementierung ohne Abstraktion
2. Native Implementierung mit Abstraktion
3. Drittanbieter-Bibliotheken

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #9: Welche Strategie der Objekterzeugung, wird für komplexe Fachobjekte verwendet?

###### Vorbedingung

Keine

###### Soft Standard

Eine Root-Entity, eine Entity oder ein Value Object muss stets einen gültigen fachlichen Zustand sicherstellen. Die Erzeugung der Fachobjekte spielt dabei eine entscheidende Rolle. Die Mustersprache des taktischen Designs beschreibt die Factory, die die Verantwortung für die Objekterzeugung übernimmt, wenn dies nicht mehr durch einen einfachen Konstruktor am Objekt selbst möglich ist.

Als Alternative zur Factory kann das Builder-Pattern verwendet werden und Static Factory Methoden sind eine gute Alternative oder Ergänzung zu Konstruktoren.

In der Regel gibt es nicht den einen Erzeugungsprozess für eine (Root) Entity. Folglich ist die Kombination der Lösungsstrategien für jeweilige passende Einsatzszenarien ein erfolgsversprechender Weg. Eine willkürliche und im schlimmsten Fall widersprüchliche Verwendung der Lösungsstrategien, erhöht die strukturelle Komplexität der Architektur. Daher wird ein aktiver Umgang mit dieser Fragestellung empfohlen.

###### Gängige Szenarien der Objekterzeugung in domänenzentrischen Architekturmuster

1. Ein User führt Anwendungsfälle aus, bei denen das Domänenobjekt erzeugt werden muss
2. Der Zustand eines Domänenobjekt muss aus dem Speicher gelesen werden
3. Der Zustand eines Domänenobjekt ergibt sich aus unterschiedlichen Quellen (z.B. Speicher und externer Service)
4. Die Geschäftslogik bedingt die (Neu-)Erzeugung von Domänenobjekten als Bestandteil des Kontrollfluss der Domänenlogik

###### Lösungsstrategien

1. Factory
2. Static Method Factory
3. Konstruktor
4. Builder
5. Deine Strategy (Joker)

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

### Entscheidungskategorie Anwendungsfälle

@ToDo

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffene Verantwortungsbereich Two-Way Mapping |
| ---------------------- | :----------------------------------------------: |
| Clean Architecture     |                  Use Case Ring                   |
| Hexagonal Architecture |               Application Hexagon                |
| Onion Architecture     |                Application Onion                 |

#### Architekturentscheidung #10: Wie werden Use Cases geschnitten?

###### Vorbedingung

Keine

###### Soft Standard

Use Cases sind Schnittstellen der fachlichen Applikation und werden aus der Perspektive der Domäne definiert. Dabei können verschiedene Heuristiken zum Einsatz kommen, und keine Heuristik ist per se die beste. Entscheidend ist vielmehr ein gemeinsames Verständnis und die einheitliche Anwendung, die jeweils von konkreten Szenarien abhängt.

###### Gängige Szenarien und passende Lösungsstrategie

@ToDo

#### Lösungsstrategien

1. Use Case je Funktion
2. Use Case für alle Command-Operationen
3. Use Case für alle Query-Operationen
4. Use Case für alle Schnittstellen bezogen auf eine (Root) Entity
5. Use Case für alle Schnittstelle zu einer technischen Komponenten (z.B. Datenbank) innerhalb einer fachlichen Modulgrenze

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

### Entscheidungskategorie Mappings

Entscheidungen über Mapping-Strategien beeinflussen im Gesamtbild signifikant die stärke der Kopplung der Architekturbausteine auf lange Sicht. Kurzfristig sind Mappings jedoch auch Overhead, weshalb eine gute Balance, passend zu unterschiedliche Szenarien, im Team gefunden werden muss.

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffene Verantwortungsbereich Two-Way Mapping | Betroffene Verantwortungsbereich Full Mapping |
| ---------------------- | :----------------------------------------------: | :-------------------------------------------: |
| Clean Architecture     |             Interface Adapters Ring              |    Interface Adapters Ring, Use Case Ring     |
| Hexagonal Architecture |                 Adapters Hexagon                 |     Adapters Hexagon, Application Hexagon     |
| Onion Architecture     |                  Adapters Onion                  |       Adapters Onion, Application Onion       |

#### Architekturentscheidung #11: Welche Mapping-Strategie wird als Default-Strategie verwendet und für welche Szenarien eignen sich alternative Strategien?

###### Vorbedingung

Keine

###### Soft Standard

Diese Entscheidung wurde bereits bei der Beschreibung der Clean Architecture erläutert.

###### Gängige Szenarien und passende Lösungsstrategie

@ToDo

###### Lösungsstrategien

1. One-Mapping
2. Two-Way Mapping
3. Full Mapping
4. No Mapping

#### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo

#### Architekturentscheidung #12: Wie werden Mappings implementiert?

| Enabling Architecture Decision |              Entscheidung              |
| ------------------------------ | :------------------------------------: |
| 11                             | Two-Way und/oder Full Mapping Strategy |

###### Soft Standard

Für die Two-Way und die Full Mapping Strategie stellt sich die Frage, wie Mappings implementiert werden sollen. Eine einheitliche Lösungsstrategie wird empfohlen, um die Komplexität in der Implementierung nicht unnötig zu erhöhen. Jede Strategie hat Vor- und Nachteile, und letztendlich muss sie auch den Vorlieben des Teams entsprechen. Daher wird empfohlen diese Entscheidung bewusst im Team zu treffen.

###### Lösungsstrategien

1. Native Implementierung durch einen eigenes Element in der Mustersprache (z.B. Mapper)
2. Native Implementierung als Logik des Interface Adapters (z.B. private Methode)
3. Implementierung durch ein eigenes Element in der Mustersprache sowie mit Verwendung von Drittanbieter-Bibliotheken

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

@ToDo
