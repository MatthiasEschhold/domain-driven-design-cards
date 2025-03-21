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
