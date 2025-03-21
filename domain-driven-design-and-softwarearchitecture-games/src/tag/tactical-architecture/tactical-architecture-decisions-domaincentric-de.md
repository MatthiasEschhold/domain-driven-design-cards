# Entscheidungskategorie Domänenkern

Der Domänenkern beschreibt den fachlichen Kern des Systems im Inneren der domänenzentrierten
Architekturmodelle und besteht aus **Domänenmodell**, **-logik** sowie **-regeln**.

## Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffener Verantwortungsbereich           |
| ---------------------- |:--------------------------------------------|
| Clean Architecture     | `Entities Ring`                             |
| Hexagonal Architecture | `Domain Hexagon`                            |
| Onion Architecture     | `Domain Service Onion` </br> `Domain Onion` |

## Architekturentscheidung #1: Nach welchem Prinzip wird das Domänenmodell implementiert?

### Vorbedingung

Keine

### Soft Standard

Mit der Entscheidung für DDD wird das Ziel verfolgt, ein Domänenmodell zu implementieren. Dabei ist
das **Aggregate**, **Entity** und **Value Object** Muster zu verwenden. 

### Entscheidungskontext

Für die Implementierung eines Domänenmodells muss die Grundsatzentscheidung zwischen dem 
_Rich Domain Model_ (1.1) und dem _Anemic Domain Model_ (1.2) getroffen werden. 

Diese Entscheidung legt fest, wie das Domänenmodell implementiert wird und an welchen 
Stellen Geschäftslogik und -regeln verortet werden.

Das _Rich Domain Model_ besagt, dass die Fachlogik im Domänenobjekt (_Aggregate_, _Entity_, _Value Object_) 
implementiert wird. Dadurch wird eine ausdrucksstarke Benennung der Methoden ermöglicht, die auf der 
Fachlichkeit basiert (im vgl. zu set-Methoden). Eine spezielle Form des Verhaltens findet sich in der 
implementierung von Domänenobjekt-bezogenen Geschäftsregeln.

Ein _Anemic Domain Model_ enthält nur die Eigenschaften sowie dazugehörige getter und setter. 
Fachlogik und Validierung werden ausschließlich in _Domain Services_ implementiert. 
Das _Anemic Domain Model_ gilt auch als Anti-Pattern, da dies oft zu einem Verlust der Fachsprache im Modell
und zu unnötige Komplexität führt im vgl. dazu die Fachlichkeit im Modell abzubilden.

Der Mittelweg stellt das _Moderately Rich Domain Model_ (1.3) dar. Hier werden Daten und Geschäftsregeln im Objekt gekapselt, 
weitere Fachlogik wird in _Domain Services_ ausgelagert.

### Risiko wenn diese Entscheidung nicht aktiv getroffen wird

- Es entstehen uneinheitliche Implementierungen des Domänenmodells
- Die Fachlichkeit wird nicht ausreichend im Modell abgebildet und unnötige technische Komplexität entsteht

### Entscheidungsalternativen

| Entscheidungsalternativen        | Kurzbeschreibung                                                            | 
|----------------------------------|:----------------------------------------------------------------------------|
| 1.1 Rich Domain Model            | Das Domänenobjekt enthält:<br/>1) Daten<br/>2) Geschäftsregeln<br/>3) Verhalten |
| 1.2 Moderately Rich Domain Model | Das Domänenobjekt enthält:<br/>1) Daten<br/>2) Geschäftsregeln                  |
| 1.3 Anemic Domain Model          | Das Domänenobjekt enthält ausschließlich Daten                              |

## Architekturentscheidung #2: Wie soll das Value Object Muster in der Implementierung angewendet werden?

### Vorbedingung

| Architecture Decision | Entscheidung                                                     |
|-----------------------|:-----------------------------------------------------------------|
| 1                     | `1.1 Rich Domain Model` <br/> `1.2 Moderately Rich Domain Model` |

> **Annahme**<br/>
> Wird eine 1.3 _Anemic Domain Model_ eingesetzt, wird die Implementierung von _Value Objects_ 
> als nicht wertstiftend angesehen. Die Entscheidung #2 muss in diesem Fall nicht 
> getroffen werden.  

### Entscheidungskontext

Wird dem _Rich_ oder dem _Moderately Rich Domain Model_ gefolgt, entsteht in Teams i.d.R. die Frage, 
ob jeder Fachwert als _Value Object_ implementiert werden soll, denn in einem komplexen _Aggregate_ 
können potenziell viele _Value Objects_ entstehen.

In der Literatur gibt es auf deiner eine Seite die Empfehlung einen fachlichen Wert immer als _Value Object_ zu implementieren. 
Auf der anderen Seite existiert ein großen vielfalt von unterschiedlichen Implementierungsbeispiele von Value Objects.
Wird diese Aussage mit den Codebeispielen in Verbindung gebracht, wird deutlich, dass die Empfehlung nicht bedeutet, 
dass keine primitiven Datentypen mehr verwendet werden dürfen.

Dennoch ist das, das eine extrem der Entscheidung: _Jeder Fachwert wird mittels Value Object Muster implementiert._ (2.1) 
Ein Value Object wird angewendet mit den Zielen,
- Die Fachliche Ausdrucksstärke in der Paketstruktur und auf Klassenebene zu erhöhen
- Eine Fachliche Typisierung durch die Eigenschaft der Selbst-Validierung zu realisieren
- Fachliche Logik bezogen auf den Fachwert zu kapseln
- Die Seiteneffektfreiheit durch die Eigenschaft der Immutabilität, insbesondere bei Paralellität, zu erhöhen

Es gibt jedoch auch Fälle, in denen die Implementierung eines Fachwertes als _Value Object_ nicht notwendig ist.
Dies ist dann der Fall, wenn die fachliche Typisierung und Selbst-Validierung und die Kapselung von
Fachlogik nicht notwendig ist. 
Ein pragmatischer Ansatz, _Value Objects nur zu verwenden, wenn diese Eigenschaften notwendig sind_ (2.2), ist valide und 
in Abhängigkeit der konkreten fachlichen Anforderungen zu betrachten.

Auf _Value Objects zu verzichten und ausschließlich primitive Datentypen zu verwenden_ (2.3),
ist ebenfalls eine Möglichkeit, wird jedoch in der DDD-Community als Anti-Pattern bewertet. 
Dies ist insbesondere bei komplexen _Aggregates_ mit viel und/oder komplexen Geschäftsregeln und Logik der Fall, 
dann dies dann vollständig im _Aggregate_ oder in einer _Entity_ implementiert werden muss.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

- Es entstehen uneinheitliche Implementierungen der Fachwerte und in der Konsequenz auch die Prüfung von Geschäftsregeln
- Es entstehen widersprüchlich Implementierungsvarianten und die Verständlichkeit des Codes wird erschwert
- Entwickler/innen ist unklar, welche Implementierungsvariante wann für einen Fachwert zu wählen ist

### Entscheidungsalternativen

| Entscheidungsalternativen                   | Kurzbeschreibung                                                                                                                        |
|---------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------|
| 2.1 Use always value object pattern         | Jeder fachliche Wert wird als _Value Object_ implementiert.                                                                             |     
| 2.2 Conditional use of value object pattern | Nur fachliche Werte, die Logik oder Geschäftsregeln enthalten, werden als _Value Object_ implementiert.                                 |  
| 2.3 No usage of value object pattern        | Jeder fachliche Wert wird als primitiver Datentyp implementiert. Logik und Geschäftsregeln werden in der _(Root) Entity_ implementiert. |       

## Architekturentscheidung #3: Wie wird die fachliche Konsistenz bei Verwendung des Anemic Domain Model gesichert?

### Vorbedingung

| Architecture Decision | Entscheidung              |
|-----------------------|:--------------------------|
| 1                     | `1.3 Anemic Domain Model` |

### Entscheidungskontext

Wird das _Anemic Domain Model_ angewendet, muss für die Invarianten und Verhalten ein anderes Element 
als die Domänenobjekte selbst gefunden werden. Eine Möglichkeit hierfür ist der _Domain Service_ (3.1), jedoch 
würde dies dazu führen, dass die Verantwortung für Fachlogik und fachliche Validierung in einem Muster 
verortet wird. Bei erhöhter fachlicher Komplexität, empfiehlt sich daher die Einführung eines 
neuen Elements in der Mustersprache, wie z.B. ein _Validator_ (3.2).

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

- Es entstehen uneinheitliche Implementierungen der Fachwerte und in der Konsequenz auch die Prüfung von Geschäftsregeln
- Dies reduziert die Verständlichkeit und Entwickler/innen ist unklar, welche Implementierungsvariante wann für einen Fachwert zu wählen ist

### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung                                                                                                                 |
|---------------------------|:---------------------------------------------------------------------------------------------------------------------------------|
| 3.1                       | Domänenobjekt-bezogene Geschäftsregeln und Logik werden _Domain Services_ implementiert.                                         |     
| 3.2                       | Domänenobjekt-bezogene Geschäftsregeln wird in _Validators_ und Domänenobjekt-bezogene Logik in _Domain Services_ implementiert. |


## Architekturentscheidung #4: Wie werden Domänenobjekt-bezogene Geschäftsregeln implementiert?

### Vorbedingung

Keine

### Entscheidungskontext

Unabhängig davon, in welchem Element der Mustersprache die Implementierung von Geschäftsregeln verortet wird,
stellt sich die Frage wie die Implementierung erfolgen soll.

Eine Möglichkeit ist die Implementierung der Geschäftsregeln _nativ mit den Mitteln der genutzten Programmiersprache_ (4.1).

Eine weitere Möglichkeit ist die Implementierung mit einer _proprietären Abstraktion zur Wiederverwendung von Validierungsregeln_ (4.2).
Diese Abstraktion koppelt auf lange Sicht, fachliche Komponenten des Systems und sollte daher mit Bedacht eingesetzt werden.

Eine dritte Möglichkeit ist der Einsatz einer _Third-Party Library_ (4.3), die Out-of-the-Box Validierungsregeln enthält.
Dies kann die Implementierung von Geschäftsregeln beschleunigen. Jedoch sollte die Abhängigkeit zur Library hinsichtlich
Funktionsumfang, Stabilität, Erweiterbarkeit und Einfachheit bewertet werden.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

- Es entstehen uneinheitliche und evtl. widersprüchliche Implementierungen für die Prüfung von Geschäftsregeln
- Dies reduziert die Verständlichkeit und den Entwickler/innen ist unklar, welche Implementierungsvariante wann für die Implementierung von Geschäftsregeln zu verwenden ist
- Mehrere Libraries werden für denselben Zweck verwendet, was zu einer unnötigen Komplexität führt
- Ein Mix aus nativer Implementierung und Third-Party Libraries führt zu einer schwer nachvollziehbaren Verflechtung von Regeln, die keine fachliche Konsistenz gewährleisten

### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung                                                                                       |
|---------------------------|:-------------------------------------------------------------------------------------------------------|
| 4.1 Native                | Native Implementierung mit den mitteln der genutzten Programmiersprache.                               |     
| 4.2 Native Abstraction    | Native Implementierung mit einer proprietären Abstraktion zur Wiederverwendung von Validierungsregeln. |
| 4.3 Third-Party Library   | Einsatz einer Third-Party Library die Out-of-the-Box Validierungsregeln enthält.                       |


## Architekturentscheidung #5: Welche Strategie der Objekterzeugung, wird für komplexe Fachobjekte verwendet?

### Vorbedingung

Keine

### Entscheidungskontext

Die Mustersprache des taktischen Designs beschreibt die _Factory_ (5.1), die die Verantwortung für die Objekterzeugung übernimmt, 
wenn dies nicht mehr durch einen einfachen Konstruktor (5.4) am Objekt selbst möglich ist.

Als Ergänzung oder Alternative zur _Factory_ kann das _Builder-Pattern_ (5.2) eingesetzt werden. Als Ergänzung bzw. Alternative zum Konstruktor, 
ist _Static Factory Method Pattern_ (5.3) zielführend.

In der Regel gibt es nicht den einen Erzeugungsprozess für Domänenobjekte. Folglich ist die Kombination der 
Lösungsstrategien für jeweilige passende Einsatzszenarien ein guter Weg. Wann welche Muster eingesetzt wird, sollte basierend auf einem 
gemeinsamen Verständnis in der Architekturentscheidung festgehalten werden.

**Szenarien**

Im Folgenden sind vier Szenarien aufgeführt, die Objekterzeugung in domänenzentrischen Architekturmuster bedingen.

1. Ein User führt Anwendungsfälle aus, bei denen das Domänenobjekt erzeugt werden muss
2. Der Zustand eines Domänenobjektes muss aus dem Speicher gelesen werden
3. Der Zustand eines Domänenobjektes ergibt sich aus unterschiedlichen Quellen (z.B. Speicher und externer Service)
4. Die Geschäftslogik bedingt die (Neu-)Erzeugung von Domänenobjekten als Bestandteil der Kontrollfluss der Domänenlogik

### Risiko

- Eine uneinheitliche und potenziell widersprüchliche Verwendung der Erzeugungsmuster erhöht die strukturelle Komplexität
- Dies reduziert die Verständlichkeit und Entwickler/innen ist unklar, welches Erzeugungsmuster für einen Fachwert zu wählen ist, was zu weiteren Abweichungen und Varianten führt

### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
| 5.1 Factory               |                  |     
| 5.2 Static Factory Method |                  |
| 5.3 Constructor           |                  |
| 5.4 Builder               |                  |
| 5.5 Joker                 |                  |

# Entscheidungskategorie Anwendungsfälle

@ToDo

> Geschäftsregeln stellen im Kontext der Entscheidungskategorie Anwendungsfälle anwendungsfall-spezifische Regeln dar.

## Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffener Verantwortungsbereich |
|------------------------|:----------------------------------|
| Clean Architecture     | `Use Case Ring`                   |
| Hexagonal Architecture | `Application Hexagon`             |
| Onion Architecture     | `Application Onion`               |

## Architekturentscheidung #6: Wie werden Use Cases geschnitten?

### Vorbedingung

Keine

### Entscheidungskontext

Use Cases sind Schnittstellen der fachlichen Applikation und werden aus der Perspektive der Domäne definiert.
Dabei können verschiedene Heuristiken zum Einsatz kommen, und keine Heuristik ist per se die beste.

Ziel ist es im Team zu definieren, welche Heuristik überwiegend oder in Abhängigkeit eines bestimmten Szenarios
verwendet werden soll. Diese Szenarien der Anwendung sind auf Basis eines gemeinsamen Verständnisses
in der Architekturentscheidung zu dokumentieren.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

- Es entstehen uneinheitliche Varianten der Implementierung und Benennung von Use Cases
- Dies reduziert die Verständlichkeit und Entwickler/innen ist unklar, welche Implementierungsvariante wann für einen Use Case zu wählen ist

###### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
| 6.1 By Function           |                  |     
| 6.2 By Command & Query    |                  |
| 6.3 By (Root) Entity      |                  |
| 6.4 By Source             |                  |
| 6.5 By Consumer           |                  |
| 6.6 Joker                 |                  |

# Entscheidungskategorie Mappings

Die Entscheidungen über den Einsatz von Mapping-Strategien beeinflusst den Kopplungsgrad des Systems im Gesamtbild.
Mappings können jedoch auch Overhead darstellen, weshalb eine gute Balance abgestimmt auf die Anforderungen notwendig ist.

## Einordnung in die domänenzentrischen Architekturmuster

| Architekturmuster      | Betroffene Verantwortungsbereich Two-Way Mapping | Betroffene Verantwortungsbereich Full Mapping |
| ---------------------- |:-------------------------------------------------|:----------------------------------------------|
| Clean Architecture     | `Interface Adapters Ring`                         | `Interface Adapters Ring`<br/>`Use Case Ring` |
| Hexagonal Architecture | `Adapters Hexagon`                                 | `Adapters Hexagon`<br/>`Application Hexagon`  |
| Onion Architecture     | `Adapters Onion`                                   | `Adapters Onion`<br/>`Application Onion`      |

## Architekturentscheidung #7: Welche Mapping-Strategie wird als Default-Strategie verwendet und für welche Szenarien eignen sich alternative Strategien?

### Vorbedingung

Keine

### Entscheidungskontext

@ToDo

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen    | Kurzbeschreibung |
|------------------------------|:-----------------|
| 7.1 One-Way Mapping Strategy |                  |     
| 7.2 Two-Way Mapping Strategy |                  |
| 7.3 Full Mapping Strategy    |                  |
| 7.4 No Mapping               |                  |
| 7.5 Joker                    |                  |

## Architekturentscheidung #8: Wie werden Mappings implementiert?

| Enabling Architecture Decision | Entscheidung                                                     |
|--------------------------------|:-----------------------------------------------------------------|
| 7                              | `7.2 Two-Way Mapping Strategy` <br/> `7.3 Full Mapping Strategy` |

### Entscheidungskontext

Für die Two-Way und die Full Mapping Strategie stellt sich die Frage, wie Mappings implementiert werden sollen.
Eine einheitliche Lösungsstrategie wird empfohlen, um die Komplexität in der Implementierung nicht unnötig zu erhöhen.
Jede Variante hat Vor- und Nachteile, und letztendlich muss sie auch den Vorlieben des Teams entsprechen.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen   | Kurzbeschreibung |
|-----------------------------|:-----------------|
| 8.1 Native as adapter logic |                  |     
| 8.2 Native with a _Mapper_  |                  |
| 8.3 Third-Party Library     |                  |
| 8.4 Joker                   |                  |

# Entscheidungskategorie Modularisierung

Starting Architecture Decisions der Entscheidungskategorie Modularisierung haben übergreifenden Charakter
und gelten in allen domänenzentrischen Architekturmuster auf gleiche Weise.

## Architekturentscheidung #9: Vertikale vs horizontale Schichtung

### Vorbedingung

Keine

### Entscheidungskontext

@ToDo

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen                | Kurzbeschreibung |
|------------------------------------------|:-----------------|
| 9.1 Vertical Layers by Aggregate-Module  |                  |     
| 9.2 Horizontal Layers by Rings / Hexagon |                  |

## Architekturentscheidung #10: Wie wird Modularisierung umgesetzt?

### Vorbedingung


### Entscheidungskontext

@ToDo

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen | Kurzbeschreibung |
|---------------------------|:-----------------|
|                           |                  |     
|                           |                  |

## Architekturentscheidung #11: Wie werden fachliche Module strukturiert?

### Vorbedingung

| Architecture Decision |                Entscheidung                |
|-----------------------|:------------------------------------------:|
| 9                     | `9.1 Vertical Layers by Aggregate-Modules` |

### Entscheidungskontext

Das fachliche Modul wird abgeleitet auf Basis eines _Aggregate_ (_Root Entity_).
Innerhalb dieses Moduls werden die Verantwortungsbereiche (Ring, Hexagon bzw. Onion) des domänenzentrischen
Architekturmuster abgebildet. Hierfür gibt es verschiedene Lösungsstrategien, die die architektonischen Elemente mehr
oder weniger stark zum Ausdruck bringen.

Zur Förderung der Verständlichkeit wird bei Modulen ab mittlerer Größe und Komplexität eine ausdrucksstarke Variante,
d.h. 11.1 und 11.2, empfohlen.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen                 | Kurzbeschreibung |
|-------------------------------------------|:-----------------|
| 11.1 Architectural Expressive             |                  |     
| 11.2 Architectural Expressive Application |                  |
| 11.3 Rings/Hexagon as Layers              |                  |
| 11.4 Joker                                |                  |

## Architekturentscheidung #12: Wie wird die Abhängigkeit zwischen zwei Aggregates aufgelöst?

### Vorbedingung

Keine

### Entscheidungskontext

Im taktischen Domain-Driven Design wird eine Entkopplung auf Basis der Grenzen von Aggregates verfolgt.
Aggregates haben einen unabhängigen Lebenszyklus.

Falls ein fachlicher Zusammenhang hergestellt werden muss, darf ein Aggregate ein anderes Aggregate nur über dessen
Identität referenzieren. Die Referenz muss zur Laufzeit aufgelöst werden.

Abhängigkeiten dieser Art sind musterartig und gleichartig zu behandeln, um die Komplexität gering zu halten.

Welches Muster als Standard dienen kann und in welchen Szenarien auf alternative Lösungsstrategien zurückgegriffen wird,
muss als kollaborative Architekturentscheidung im Team getroffen werden.

### Risiko, wenn diese Entscheidung nicht aktiv getroffen wird

@ToDo

### Entscheidungsalternativen

| Entscheidungsalternativen            | Kurzbeschreibung |
|--------------------------------------|:-----------------|
| 12.1 Application Service Pattern     |                  |     
| 12.2 Adapter-Out Use-Case-In Pattern |                  |
| 12.3 Events                          |                  |
| 12.4 Joker                           |                  |
