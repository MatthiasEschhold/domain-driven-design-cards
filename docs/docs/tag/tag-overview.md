# Architecture Enabling mittels kollaborativen Architekturentscheidungen

Das Tactical Architecture Game ist ein Gamification-Ansatz, um den taktischen Architekturplan im Entwicklungsteam zu entwickeln.
Das Spiel basiert auf sogenannte Enabler Architecture Decisions. Die Summe aller Entscheidungen ergibt den Architekturplan.

Das Tactical Architecture Game, wie hier beschrieben, ist für das taktische Domain-Driven Design in Kombination mit
domänen-zentrischen Architekturmustern, wie z.B. Clean, Hexagonal oder Onion Architecture, konzipiert.

Die Enabler Architecture Decisions erstrecken sich über die folgenden Kategorien:

1. Modularisierung
2. Use Cases
3. Domänenkern
4. Mappings

Jede Kategorie enthält in der Regel mehrere Entscheidungen. Die Summe der Entscheidungen beschreiben die Umsetzungsvision der Architektur.

Im Weiteren die Begriffe, die die Grundidee des Tactical Architecture Game in verallgemeinerter Form beschreiben:

> Enabler Architecture Decision
> Architekturentscheidung für die Umsetzung eines umfangreichen Architekturkonzeptes, wobei die Entscheidung eine bestimmte Lösungsstrategie für einen Teilbereich des Architekturkonzepts darstellt.

> Architekturkonzept
> Muster und -prinzipien der taktischen Architekturebene, gebündelt und benannt als Architekturkonzept mit dem Charakter einer Basisstrukturierung durch Ringe, Schichten, Komponenten, Module und Klassen.

> Dabei wird das Architekturkonzept i.d.R. durch mehrere Entscheidungen ausgedrückt. Die Summe von
> Enabler Architecture Decisions stellt die Umsetzungsvision der Architektur des Teams dar.

## Enabling Architecture Decisions

### Entscheidungskategorie Modularisierung

Enabler Architecture Decisions der Entscheidungskategorie Modularisierung haben übergreifenden Charakter und gelten in allen domänenzentrischen Architekturmuster auf gleiche Weise.

#### EAD 1: Wie werden fachliche Module strukturiert?

Das fachliche Modul bildet sich um ein Aggregate. Innerhalb dieses Moduls werden die Ringe des Clean Architecture Pattern abgebildet. Hierfür gibt es verschiedene Lösungsstrategien.

###### Lösungsstrategien

1. Architektonisch ausdrucksstark
2. Architektonisch ausdrucksstark in der fachlichen Applikation
3. Ringe als Schichten

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 2: Wie wird die Abhängigkeit zwischen zwei Aggregates behandelt?

Im taktischen Domain-Driven Design wird eine Entkopplung auf Basis der Grenzen von Aggregates verfolgt. Aggregates haben einen unabhängigen Lebenszyklus. Falls jedoch ein fachlicher Zusammenhang besteht, darf ein Aggregate ein anderes Aggregate nur über die Identität referenzieren. Die Referenz muss zur Laufzeit aufgelöst werden [7]. Es empfiehlt sich, Abhängigkeiten dieser Art einheitlich zu behandeln, solange besondere Anforderungen keine Ausnahme bedingen. Welches Muster als Standard dienen kann und in welchen Szenarien auf alternative Lösungsstrategien zurückgegriffen wird, sollte durch eine aktive Entscheidung im Entwicklungsteam festgelegt werden.

###### Lösungsstrategien

1.	Application Service Pattern
2.	Adapter Out Use Case In Pattern
3.	Event-driven / Publish-Subscribe Pattern

#### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

### Entscheidungskategorie Domänenkern

Entscheidungen der Kategorie Domänenkern beziehen sich auf den Entities-Ring der Clean Architecture.

#### EAD 3: Nach welchem Prinzip wird das Domänenmodell implementiert?

Diese Entscheidung legt grundlegend fest, wie das Domänenmodell implementiert wird und an welchen Stellen Geschäftslogik und -regeln umgesetzt werden. Dabei wird zwischen der Variante des Rich Domain Models und des Anemic Domain Models unterschieden.

Das Rich Domain Model besagt, dass die Fachlogik im Domänenobjekt implementiert wird. Dadurch wird eine ausdrucksstarke Benennung der Methoden ermöglicht, die auf der Fachlichkeit basiert, im Vergleich zu einfachen set- und get-Methoden. Zudem erlaubt es die fachliche Validierung zur Sicherung der Konsistenz einer (Root) Entity ebenfalls direkt am Objekt vorzunehmen. Gleiches gilt für Value Objects, die die Validierung des fachlichen Werts implementieren.

Ein Anemic Domain Model enthält nur die Eigenschaften sowie dazugehörige getter und setter. Fachlogik und Validierung werden ausschließlich in Domain Services implementiert. Das Anemic Domain Model gilt als Anti-Pattern, da dies oft zu einem Verlust der Fachsprache sowie zu Konsistenzverletzungen führt [9]. Dennoch muss ein Team diese Entscheidung bewusst treffen.

###### Lösungsstrategien

1.	Rich Domain Model
2.	Anemic Domain Model

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 4: Wie soll das Value Object Muster in der Implementierung angewendet werden?

Wird dem Rich Domain Model gefolgt, entsteht in Teams regelmäßig die Frage, ob jeder Fachwert als Value Object implementiert werden muss. Denn in komplexen Aggregates können potenziell zahlreiche Value Objects entstehen. Die Empfehlung ist fachliche Werte immer als Value Objects zu implementieren, um die Fachlichkeit zu stärken und die Robustheit zu erhöhen. Ausnahmen können gemacht werden, wenn fachliche Werte nicht validiert werden müssen oder wenn fachliche Werte Elemente eines verschachtelten Value Objects darstellen. Auf Value Objects zu verzichten und primitive Datentypen für fachliche Werte zu verwenden, ist ebenfalls eine Möglichkeit, wird jedoch als Anti-Pattern bewertet ([7] und [9]).

###### Lösungsstrategien

1.	Jeder fachliche Wert wird als Value Object implementiert
2.	Nur fachliche Werte, die Verhalten und/oder Validierung bedingen, werden als Value Object implementiert
3.	Jeder fachliche Wert wird mittels primitiven Datentyp implementiert

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 5: Wie soll das Value Object Muster implementiert werden?

###### Vorbedingung

Entscheidung für das Rich Domain Model sowie für die Anwendung des Value Object Muster.

###### Lösungsstrategien

1.	Jeder fachliche Wert wird als Value Object implementiert
2.	Nur fachliche Werte, die Verhalten und/oder Validierung bedingen, werden als Value Object implementiert
3.	Jeder fachliche Wert wird mittels primitiven Datentyp implementiert

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 6: Wie wird die fachliche Konsistenz bei Verwendung des Anemic Domain Model gesichert?

Wird das Anemic Domain Model verwendet, muss für die Validierungslogik ein anderes Element als die Fachobjekte selbst gefunden werden. Eine Möglichkeit hierfür ist der Domain Service, jedoch würde dies dazu führen, dass die Verantwortung für Fachlogik und fachliche Validierung in einem Muster verortet wird. Bei erhöhter fachlicher Komplexität empfiehlt sich daher die Einführung eines neuen Elements in der Mustersprache, wie z.B. ein Validator.

###### Vorbedingung

Entscheidung für das Anemic Domain Model

###### Lösungsstrategien

1.	Validierungslogik im Domain Services
2.	Eigenes Element in der Mustersprache, zugehörig zum Domänenkern

#### EAD 7: Wie wird Validierung implementiert?

Unabhängig davon, an welcher Stelle die Validierung in der Mustersprache verortet ist, benötigt das Entwicklungsteam eine gemeinsame Vorstellung davon, wie die Validierung implementiert werden soll. Die Möglichkeiten reichen von der nativen Implementierung in der verwendeten Programmiersprache über selbst erstellte Abstraktionen bis hin zum Einsatz von Drittanbieter-Bibliotheken. Der Lösungsraum ist vielfältig.

###### Lösungsstrategien

1.	Native Implementierung ohne Abstraktion
2.	Native Implementierung mit Abstraktion
3.	Drittanbieter-Bibliotheken

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 8: Welche Strategie der Objekterzeugung, wird für komplexe Fachobjekte verwendet?

Eine Root-Entity, eine Entity oder ein Value Object muss stets einen gültigen fachlichen Zustand sicherstellen. Die Erzeugung der Fachobjekte spielt dabei eine entscheidende Rolle. Die Mustersprache des taktischen Designs beschreibt die Factory, die die Verantwortung für die Objekterzeugung übernimmt, wenn dies nicht mehr durch einen einfachen Konstruktor möglich ist. Als Alternative zur Factory kann das Builder-Pattern verwendet werden und Static Factory Methoden sind eine gute Alternative oder Ergänzung zu Konstruktoren.

In der Regel gibt es nicht den einen Erzeugungsprozess für eine (Root) Entity. Folglich ist die Kombination der Lösungsstrategien für jeweilige passende Einsatzszenarien ein erfolgsversprechender Weg. Eine willkürliche und im schlimmsten Fall widersprüchliche Verwendung der Lösungsstrategien, erhöht die strukturelle Komplexität der Architektur. Daher wird ein aktiver Umgang mit dieser Fragestellung empfohlen.

###### Lösungsstrategien

1.	Factory
2.	Static Method Factory
3.	Konstruktor
4.	Builder
      Enabler Architecture Decisions der Kategorie Anwendungsfälle der Domäne
      Die Entscheidungskategorie Anwendungsfälle der Domäne bezieht sich auf den Use Cases Ring der Clean Architecture.

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

### Entscheidungskategorie Use Cases

#### EAD 9: Wie werden Use Cases geschnitten?

Use Cases sind Schnittstellen der fachlichen Applikation und werden aus der Perspektive der Domäne definiert. Dabei können verschiedene Heuristiken zum Einsatz kommen, und keine Heuristik ist per se die beste [6]. Entscheidend ist vielmehr ein gemeinsames Verständnis und die einheitliche Anwendung, die jeweils von konkreten Anwendungsfällen abhängt.

#### Lösungsstrategien

1.	Use Case je Funktion
2.	Use Case für alle Command-Operationen
3.	Use Case für alle Query-Operationen
4.	Use Case für alle Schnittstellen bezogen auf eine (Root) Entity
5.	Use Case für alle Schnittstelle zu einer technischen Komponenten (z.B. Datenbank) innerhalb einer fachlichen Modulgrenze
      Enabler Architecture Decisions der Kategorie Mappings
      Entscheidungen über Mapping-Strategien sind in der Clean Architecture auf den Interface Adapters Ring bzw. bei der der Full Mapping Strategie ebenfalls auf den Use Cases Ring bezogen.

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 10: Welche Mapping-Strategie wird als Default-Strategie verwendet und für welche Szenarien eignen sich alternative Strategien?

Diese Entscheidung wurde bereits bei der Beschreibung der Clean Architecture erläutert.

###### Lösungsstrategien

1.	One-Mapping
2.	Two-Way Mapping
3.	Full Mapping
4.	No Mapping

#### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

### Entscheidungskategorie Mappings

#### EAD 11: Wie werden Mappings implementiert?

Für die Two-Way und die Full Mapping Strategie stellt sich die Frage, wie Mappings implementiert werden sollen. Eine einheitliche Lösungsstrategie wird empfohlen, um die Komplexität in der Implementierung nicht unnötig zu erhöhen. Jede Strategie hat Vor- und Nachteile, und letztendlich muss sie auch den Vorlieben des Teams entsprechen. Daher wird empfohlen diese Entscheidung bewusst im Team zu treffen.

###### Lösungsstrategien

1.	Native Implementierung durch einen eigenes Element in der Mustersprache (z.B. Mapper)
2.	Native Implementierung als Logik des Interface Adapters
3.	Implementierung durch ein eigenes Element in der Mustersprache sowie mit Verwendung von Drittanbieter-Bibliotheken
      Gamification als Enabler für taktische Softwarearchitektur
      Das Tactical Architecture Game setzt die Ideen des Bounded Context, des Context Mapping sowie des Strategic Classification Game fort. Aus Sicht des Softwareentwicklungsprozesses ist dies eine weitere Iteration, die intensive Architekturarbeit beinhaltet und den Übergang von der strategischen zur taktischen Architektur sowie zur Implementierung darstellt. Das AMET führt den roten Faden der iterativen Architekturentwicklung fort, moderiert das Spiel und unterstützt durch Expertenwissen und Erfahrung. Das Tactical Architecture Game leitet den Abschluss der initialen "Slow Down"- und der Enabling-Phase ein. Vorbedingungen für das Tactical Architecture Game ist, dass das Team die Reife zu treffen der gemeinschaftliche Architekturentscheidungen durch das entsprechende Hintergrundwissen hat. Die Vorbedingung für das Tactical Architecture Game ist, dass das Team über die entsprechende Reife durch eine fundierte Wissensbasis verfügt, um gemeinschaftliche Architekturentscheidungen zu treffen.
      Spielanleitung des Tactical Architecture Game

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird

#### EAD 12: Wie werden Mappings implementiert?

Für die Two-Way und die Full Mapping Strategie stellt sich die Frage, wie Mappings implementiert werden sollen. Eine einheitliche Lösungsstrategie wird empfohlen, um die Komplexität in der Implementierung nicht unnötig zu erhöhen. Jede Strategie hat Vor- und Nachteile, und letztendlich muss sie auch den Vorlieben des Teams entsprechen. Daher wird empfohlen diese Entscheidung im Team zu treffen.

###### Lösungsstrategien
1.	Native Implementierung durch einen eigenes Element in der Mustersprache (z.B. Mapper)
2.	Native Implementierung als Logik des Interface Adapters
3.	Implementierung durch ein eigenes Element in der Mustersprache sowie mit Verwendung von Drittanbieter-Bibliotheken

###### Risiko, wenn die Entscheidung nicht bewusst getroffen wird