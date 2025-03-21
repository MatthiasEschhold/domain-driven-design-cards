# Konstruktionsplan für ein Architekturmuster

Der Konstruktionsplan der Software ist abgeleitet vom Architekturmuster. Der Konstruktionsplan ergibt
sich durch ein Set von taktischen Architekturentscheidungen. Die Entscheidungen beschreiben,
wie das Architekturmuster konkret angewendet wird. Die Entscheidungen stellen richtungsweisende
Leitplanken der taktischen Architektur dar, auf die ein Team committet sein sollte.

## Starting Tactical Architecture Decisions für domänenzentrierte Architekturmuster

Die _Starting Tactical Architecture Decisions_ verteilen sich über vier Entscheidungskategorien:

1. **Domänenkern**
2. **Anwendungsfälle**
3. **Mappings**
4. **Modularisierung**

### Entscheidungskategorie Domänenkern

Der Domänenkern beschreibt den rein fachlichen Teil des Systems, bestehend aus 
Domänenmodell, Geschäftslogik* sowie -regeln*.

> Geschäftslogik wird im Kontext der Entscheidungskategorie Domänenkern und objekt-orientierter Programmierung auchVerhalten genannt

> Geschäftsregeln sind im Kontext der Entscheidungskategorie Domänenkern Invarianten für fachliche Werte und Domänenobjekte. 

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffener Verantwortungsbereich           |
| ---------------------- |:--------------------------------------------|
| Clean Architecture     | `Entities Ring`                             |
| Hexagonal Architecture | `Domain Hexagon`                            |
| Onion Architecture     | `Domain Service Onion` </br> `Domain Onion` |

#### Architekturentscheidung #1: Nach welchem Prinzip wird das Domänenmodell implementiert?

###### Vorbedingung

Keine

###### Soft Standard

Mit der Entscheidung für Domain-Driven Design wird das Ziel verfolgt, ein Domänenmodell Mittels
der Muster des taktischen Designs zu implementieren. Für die Implementierung eines 
Domänenmodells muss die Grundsatzentscheidung _Rich Domain Model_ versus _Anemic Domain Model_ getroffen 
werden. 

Diese Entscheidung legt fest, wie das Domänenmodell implementiert wird und an welchen 
Stellen Geschäftslogik und -regeln verortet werden.

Das _Rich Domain Model_ besagt, dass die Fachlogik im Domänenobjekt (_Aggregate_, _Entity_, _Value Object_) 
implementiert wird. Dadurch wird eine ausdrucksstarke Benennung der Methoden ermöglicht, die auf der 
Fachlichkeit basiert (im vgl. zu set-Methoden).

Eine spezielle Form des Verhaltens findet sich in der fachlichen Validierung von Invarianten. Invarianten finden 
sich auf Ebene des fachlichen Werts oder zur Sicherung der Konsistenzgrenze entlang einer _(Root) Entity_ wieder.

Ein _Anemic Domain Model_ enthält nur die Eigenschaften sowie dazugehörige getter und setter. 
Fachlogik und Validierung werden ausschließlich in _Domain Services_ implementiert. 
Das _Anemic Domain Model_ gilt auch als Anti-Pattern, da dies oft zu einem Verlust der Fachsprache im Modell
sowie zu Konsistenzverletzungen führt.

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen        | Kurzbeschreibung                                                            | 
|----------------------------------|:----------------------------------------------------------------------------|
| 1.1 Rich Domain Model            | Das Domänenobjekt enthält:<br/>1) Daten<br/>2) Invarianten<br/>3) Verhalten |
| 1.2 Moderately Rich Domain Model | Das Domänenobjekt enthält:<br/>1) Daten<br/>2) Invarianten                  |
| 1.3 Anemic Domain Model          | Das Domänenobjekt enthält ausschließlich Daten                              |

### Architekturentscheidung #2: Wie soll das Value Object Muster in der Implementierung angewendet werden?

###### Vorbedingung

| Architecture Decision | Entscheidung                                                     |
|-----------------------|:-----------------------------------------------------------------|
| 1                     | `1.1 Rich Domain Model` <br/> `1.2 Moderately Rich Domain Model` |

> **Annahme**<br/>
> Wird eine _Anemic Domain Model_ eingesetzt, wird die Implementierung von _Value Objects_ 
> als nicht wertstiftend angesehen. Die Entscheidung #2 muss in diesem Fall nicht 
> getroffen werden.  

###### Soft Standard

Wird dem _Rich_ oder dem _Moderately Rich Domain Model_ gefolgt, entsteht in Teams i.d.R. die Frage, 
ob jeder Fachwert als _Value Object_ implementiert werden soll. 
In einem komplexen _Aggregate_ können potenziell viele _Value Objects_ entstehen.

Die Empfehlung ist einen fachlichen Wert immer als _Value Object_ zu implementieren. Ziel ist
die Erhöhung der Ausdrucksstärke der Fachlichkeit sowie die Seiteneffektfreiheit aufgrund
der Imutabilität.

Ausnahmen können gemacht werden, wenn fachliche Werte 
- keine Validierung und kein Verhalten enthalten
- oder ein Fachwert ein Element eines anderen Fachwert ist und dieser die Validierung und / oder 
Verhalten implementiert.

Auf _Value Objects_ zu verzichten und ausschließlich primitive Datentypen zu verwenden,
ist ebenfalls eine Möglichkeit, wird jedoch als Anti-Pattern bewertet. Insbesondere bei komplexen 
_Aggregates_ mit viel und/oder komplexer Validierung und Verhalten, entsteht über die Zeit 
schwer wartbarer Code.

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen                   | Kurzbeschreibung                                                                                                                            |
|---------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------|
| 2.1 Use always value object pattern         | Jeder fachliche Wert wird als _Value Object_ implementiert.                                                                                 |     
| 2.2 Conditional use of value object pattern | Nur fachliche Werte, die Verhalten oder Invarianten enthalten, werden als _Value Object_ implementiert.                                     |  
| 2.3 No usage of value object pattern        | Jeder fachliche Wert wird mittels primitiven Datentyp implementiert. Verhalten und Invarianten werden in der _(Root) Entity_ implementiert. |       

#### Architekturentscheidung #3: Wie wird die fachliche Konsistenz bei Verwendung des Anemic Domain Model gesichert?

###### Vorbedingung

| Architecture Decision | Entscheidung              |
|-----------------------|:--------------------------|
| 1                     | `1.3 Anemic Domain Model` |

###### Soft Standard

Wird das _Anemic Domain Model_ angewendet, muss für die Invarianten und Verhalten ein anderes Element 
als die Domänenobjekte selbst gefunden werden. Eine Möglichkeit hierfür ist der _Domain Service_, jedoch 
würde dies dazu führen, dass die Verantwortung für Fachlogik und fachliche Validierung in einem Muster 
verortet wird. Bei erhöhter fachlicher Komplexität, empfiehlt sich daher die Einführung eines 
neuen Elements in der Mustersprache, wie z.B. ein _Validator_.

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung                                                                                   |
|---------------------------|:---------------------------------------------------------------------------------------------------|
| 3.1                       | Validierung der Invarianten und Verhalten wird in _Domain Services_ implementiert.                 |     
| 3.2                       | Validierung der Invarianten wird in _Validators_ und Verhalten in _Domain Services_ implementiert. |


#### Architekturentscheidung #4: Wie wird Validierung der Invarianten implementiert?

###### Vorbedingung

Keine

###### Soft Standard

Unabhängig davon, an welcher Stelle die Validierung der Invarianten in der Mustersprache verortet ist, 
benötigt das Entwicklungsteam eine gemeinsame Vorstellung davon, wie die Validierung der Invarianten
implementiert werden soll. 

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung                                                                                       |
|---------------------------|:-------------------------------------------------------------------------------------------------------|
| 4.1 Native                | Native Implementierung mit den mitteln der genutzten Programmiersprache.                               |     
| 4.2 Native Abstraction    | Native Implementierung mit einer proprietären Abstraktion zur Wiederverwendung von Validierungsregeln. |
| 4.3 Third-Party Library   | Einsatz einer Third-Party Library die Out-of-the-Box Validierungsregeln enthält.                       |


#### Architekturentscheidung #5: Welche Strategie der Objekterzeugung, wird für komplexe Fachobjekte verwendet?

###### Vorbedingung


| Architecture Decision | Entscheidung                                                     |
|-----------------------|:-----------------------------------------------------------------|
| 1                     | `1.1 Rich Domain Model` <br/> `1.2 Moderately Rich Domain Model` |

> **Annahme**<br/>
> Wird eine _Anemic Domain Model_ eingesetzt, wird die Sicherung der Konsistenz außerhalb der Domänenobjekte
> sichergestellt, siehe auch Architecture Decision #3. Aus diesem Grund spielt die Objekterzeugung keine Rolle bei
> der Erreichung dieses Ziels und muss nicht entschieden werden.

###### Soft Standard

Eine _Root Entity_, eine _Entity_ oder ein _Value Object_ muss stets einen gültigen fachlichen Zustand sicherstellen. 
Die Erzeugung der Fachobjekte spielt dabei eine entscheidende Rolle. Die Mustersprache des taktischen Designs beschreibt 
die Factory, die die Verantwortung für die Objekterzeugung übernimmt, wenn dies nicht mehr durch einen einfachen 
Konstruktor am Objekt selbst möglich ist.

Als Alternative zur Factory kann das Builder-Pattern verwendet werden und Static Factory Methoden sind eine gute Alternative
oder Ergänzung zu Konstruktoren.

In der Regel gibt es nicht den einen Erzeugungsprozess für Domänenobjekte. Folglich ist die Kombination der 
Lösungsstrategien für jeweilige passende Einsatzszenarien ein guter Weg. Eine willkürliche und im
schlimmsten Fall widersprüchliche Verwendung der Lösungsstrategien, erhöht die strukturelle Komplexität der Architektur. 
Daher wird ein aktiver Umgang mit dieser Fragestellung empfohlen.

**Szenarien**

Im Folgenden sind vier Szenarien aufgeführt, die Objekterzeugung in domänenzentrischen Architekturmuster bedingen.

1. Ein User führt Anwendungsfälle aus, bei denen das Domänenobjekt erzeugt werden muss
2. Der Zustand eines Domänenobjektes muss aus dem Speicher gelesen werden
3. Der Zustand eines Domänenobjektes ergibt sich aus unterschiedlichen Quellen (z.B. Speicher und externer Service)
4. Die Geschäftslogik bedingt die (Neu-)Erzeugung von Domänenobjekten als Bestandteil der Kontrollfluss der Domänenlogik

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
| 5.1 Factory               |                  |     
| 5.2 Static Factory Method |                  |
| 5.3 Constructor           |                  |
| 5.4 Builder               |                  |
| 5.5 Joker                 |                  |

### Entscheidungskategorie Anwendungsfälle

@ToDo

> Geschäftsregeln stellen im Kontext der Entscheidungskategorie Anwendungsfälle anwendungsfall-spezifische Regeln dar.

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffener Verantwortungsbereich |
|------------------------|:----------------------------------|
| Clean Architecture     | `Use Case Ring`                   |
| Hexagonal Architecture | `Application Hexagon`             |
| Onion Architecture     | `Application Onion`               |

#### Architekturentscheidung #6: Wie werden Use Cases geschnitten?

###### Vorbedingung

Keine

###### Soft Standard

Use Cases sind Schnittstellen der fachlichen Applikation und werden aus der Perspektive der Domäne definiert. 
Dabei können verschiedene Heuristiken zum Einsatz kommen, und keine Heuristik ist per se die beste.

Ziel ist es im Team zu definieren, welche Methodik überwiegend oder in Abhängigkeit eines bestimmten Szenarios
verwendet werden soll.

**Szenarien**

@ToDo

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
| 6.1 By Function           |                  |     
| 6.2 By Command & Query    |                  |
| 6.3 By (Root) Entity      |                  |
| 6.4 By Source             |                  |
| 6.5 By Consumer           |                  |
| 6.6 Joker                 |                  |

### Entscheidungskategorie Mappings

Die Entscheidungen über den Einsatz von Mapping-Strategien beeinflusst den Kopplungsgrad des Systems im Gesamtbild.
Mappings können jedoch auch Overhead darstellen, weshalb eine gute Balance abgestimmt auf die Anforderungen notwendig ist.

###### Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffene Verantwortungsbereich Two-Way Mapping | Betroffene Verantwortungsbereich Full Mapping |
| ---------------------- |:-------------------------------------------------|:----------------------------------------------|
| Clean Architecture     | `Interface Adapters Ring`                         | `Interface Adapters Ring`<br/>`Use Case Ring` |
| Hexagonal Architecture | `Adapters Hexagon`                                 | `Adapters Hexagon`<br/>`Application Hexagon`  |
| Onion Architecture     | `Adapters Onion`                                   | `Adapters Onion`<br/>`Application Onion`      |

#### Architekturentscheidung #7: Welche Mapping-Strategie wird als Default-Strategie verwendet und für welche Szenarien eignen sich alternative Strategien?

###### Vorbedingung

Keine

###### Soft Standard

@ToDo

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen    | Kurzbeschreibung |
|------------------------------|:-----------------|
| 7.1 One-Way Mapping Strategy |                  |     
| 7.2 Two-Way Mapping Strategy |                  |
| 7.3 Full Mapping Strategy    |                  |
| 7.4 No Mapping               |                  |
| 7.5 Joker                    |                  |

#### Architekturentscheidung #8: Wie werden Mappings implementiert?

| Enabling Architecture Decision | Entscheidung                                                     |
|--------------------------------|:-----------------------------------------------------------------|
| 7                              | `7.2 Two-Way Mapping Strategy` <br/> `7.3 Full Mapping Strategy` |

###### Soft Standard

Für die Two-Way und die Full Mapping Strategie stellt sich die Frage, wie Mappings implementiert werden sollen. 
Eine einheitliche Lösungsstrategie wird empfohlen, um die Komplexität in der Implementierung nicht unnötig zu erhöhen. 
Jede Variante hat Vor- und Nachteile, und letztendlich muss sie auch den Vorlieben des Teams entsprechen. 

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen   | Kurzbeschreibung |
|-----------------------------|:-----------------|
| 8.1 Native as adapter logic |                  |     
| 8.2 Native with a _Mapper_  |                  |
| 8.3 Third-Party Library     |                  |
| 8.4 Joker                   |                  |

### Entscheidungskategorie Modularisierung

Starting Architecture Decisions der Entscheidungskategorie Modularisierung haben übergreifenden Charakter 
und gelten in allen domänenzentrischen Architekturmuster auf gleiche Weise.

#### Architekturentscheidung #9: Vertikale vs. horizontale Schichtung

###### Vorbedingung

Keine

###### Soft Standard

@ToDo

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen                | Kurzbeschreibung |
|------------------------------------------|:-----------------|
| 9.1 Vertical Layers by Aggregate-Module  |                  |     
| 9.2 Horizontal Layers by Rings / Hexagon |                  |

#### Architekturentscheidung #10: Wie wird Modularisierung umgesetzt?

###### Vorbedingung

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
|                           |                  |     
|                           |                  |

#### Architekturentscheidung #11: Wie werden fachliche Module strukturiert?

###### Vorbedingung

| Architecture Decision |                Entscheidung                |
|-----------------------|:------------------------------------------:|
| 9                     | `9.1 Vertical Layers by Aggregate-Modules` |

###### Soft Standard

Das fachliche Modul wird abgeleitet auf Basis eines _Aggregate_ (_Root Entity_).
Innerhalb dieses Moduls werden die Verantwortungsbereiche (Ring, Hexagon bzw. Onion) des domänenzentrischen 
Architekturmuster abgebildet. Hierfür gibt es verschiedene Lösungsstrategien, die die architektonischen Elemente mehr
oder weniger stark zum Ausdruck bringen.

Zur Förderung der Verständlichkeit wird bei Modulen ab mittlerer Größe und Komplexität eine ausdrucksstarke Variante,
d.h. 11.1 und 11.2, empfohlen.

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen                 | Kurzbeschreibung |
|-------------------------------------------|:-----------------|
| 11.1 Architectural Expressive             |                  |     
| 11.2 Architectural Expressive Application |                  |
| 11.3 Rings/Hexagon as Layers              |                  |
| 11.4 Joker                                |                  |

#### Architekturentscheidung #12: Wie wird die Abhängigkeit zwischen zwei Aggregates aufgelöst?

###### Vorbedingung

Keine

###### Soft Standard

Im taktischen Domain-Driven Design wird eine Entkopplung auf Basis der Grenzen von Aggregates verfolgt. 
Aggregates haben einen unabhängigen Lebenszyklus.

Falls ein fachlicher Zusammenhang hergestellt werden muss, darf ein Aggregate ein anderes Aggregate nur über dessen 
Identität referenzieren. Die Referenz muss zur Laufzeit aufgelöst werden.

Abhängigkeiten dieser Art sind musterartig und gleichartig zu behandeln, um die Komplexität gering zu halten.

Welches Muster als Standard dienen kann und in welchen Szenarien auf alternative Lösungsstrategien zurückgegriffen wird, 
muss als kollaborative Architekturentscheidung im Team getroffen werden.

**Szenarien**

@ToDo

###### Risiko

@ToDo

###### Entscheidungsalternativen

| Entscheidungsalternativen            | Kurzbeschreibung |
|--------------------------------------|:-----------------|
| 12.1 Application Service Pattern     |                  |     
| 12.2 Adapter-Out Use-Case-In Pattern |                  |
| 12.3 Events                          |                  |
| 12.4 Joker                           |                  |

## Starting Tactical Architecture Decisions für geschichtete Architekturmuster

@ToDo

## Beispiel Flottenmanagement

## Beispiel Ersatzteileverwaltung