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
