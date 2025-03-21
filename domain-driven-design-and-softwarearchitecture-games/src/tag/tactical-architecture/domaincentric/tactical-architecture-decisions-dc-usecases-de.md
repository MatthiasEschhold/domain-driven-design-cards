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
