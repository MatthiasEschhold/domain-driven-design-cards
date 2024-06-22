# Das Context Mapping Game

## Ziel des Spiels

Das Context Mapping Game hat das Ziel, Abhängigkeiten zwischen Bounded Contexts unter Berücksichtigung des
* Call Flow,
* Model Flow sowie
* Influence Flow

zu gestalten.

### Die Mission

Die Definition der Abhängigkeiten zwischen zwei Bounded Contexts anhand der Context Mapping Patterns von Domain-Driven Design stellt eine Mission des Teams dar. 
Das Spiel endet, sobald alle Missionen abgeschlossen sind. Dies bedeutet, dass alle Abhängigkeiten zwischen Bounded Contexts definiert sind.

## Spielvorbereitung

Die Vorbedingung für das Spielen des Context Mapping Game ist die Bekanntheit der Bounded Contexts. Jeder Bounded Context ist mittels Bounded Context Canvas beschrieben. 
Die befüllten Bounded Context Canvases werden physisch oder digital als Spielfeld verwendet. Sind Subdomänen bekannt, empfiehlt es sich, Bounded Contexts der gleichen 
Subdomäne gruppiert auf dem Spielfeld darzustellen.

## Spielverlauf

> 1. Dependency Storming (10 Minuten)

Im ersten Schritt des Context Mapping Game entscheidet sich das Team für die erste Mission, d.h. für einen zu betrachtenden Bounded Context. 
Die Mission beginnt mit 10 Minuten Brainstorming von Abhängigkeiten für den Bounded Context (Dependency Storming).

> 2. Gruppierung der Abhängigkeiten (10 Minuten)

Nun werden gleiche Nennungen gruppiert und die Abhängigkeiten nach eingehenden und ausgehenden Abhängigkeiten unterteilt. 
Abhängigkeiten werden auf dem Spielfeld als Sticky Notes platziert.

Optional kann die Abhängigkeit durch eine Linie zwischen den Bounded Contexts auf dem Spielfeld visualisiert werden.

> 3. Diskussion und Karten legen (15 Minuten)

Jede Abhängigkeit wird im Team mit einer Timebox von 15 Minuten intensiv diskutiert. Hierbei hat jede Spieler/in die Möglichkeit, 
ihre Ansicht zu teilen, andere Ansichten aufzunehmen und in den Austausch über die dargestellten Sachverhalte zu gehen.

Anschließend legt jede Spieler/in eine oder mehrere Spielkarten für den Upstream- und Downstream-Kontext auf das Spielfeld. 
Wie viele Spielkarten je Spieler/in gelegt werden, hängt von dem ausgewählten Context Mapping Pattern ab.

> 4. Entscheidungsfindung (15 Minuten)

Wenn sich eine gemeinsame Basis mit Ausreißern aufzeigt, kann die Entscheidungsfindung durch die Erklärung der Ausreißer gestartet werden. 
Wenn das Ergebnis sehr unterschiedlich oder einheitlich ist, beginnt eine Spieler/in mit der Schilderung ihrer Sicht.

Durch den Austausch im Team eliminieren sich nicht mehr passende Muster und die Spielkarten werden vom Spielfeld entfernt. 
Die Abhängigkeit gilt als definiert, wenn die gelegten Karten:
* eine valide Abbildung der Context Mapping Patterns darstellen _und_
* für Down- bzw. Upstream Bounded Context nur ein Context Mapping (Ausnahme Open Host Service und Published Language) gelegt ist _und_
* alle Spieler:innen über diese Definition der Beziehung übereinstimmen.

> Visueller Spielablauf

![Spielablauf Context Mapping Game](../img/cmg-gameplay.png)
