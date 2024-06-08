# Das Context Mapping Game

## Ziel des Spiels

Das Context Mapping Game hat das Ziel, Abhängigkeiten zwischen Bounded Contexts zu gestalten unter Berücksichtigung des
* Call Flow,
* Model Flow sowie
* Influence Flow.

### Die Mission

Die Definition der Abhängigkeit zwischen zwei Bounded Contexts anhand der Context Mapping Patterns von Domain-Driven
Design, stellt eine Mission des Teams dar. Ein Spiel besteht in der Regel aus mehreren Missionen, in der Annahme, dass es
mindestens drei Bounded Contexts im Scope des Spiels vorhanden sind. Das Spiel endet, sobald alle Missionen abgeschlossen sind.

## Spielvorbereitung

Die Vorbedingung für das Spielen des Context Mapping Game ist die Bekanntheit der Bounded Contexts. 
Jeder Bounded Context ist mittels Bounded Context Canvas beschrieben (und im besten Fall mittels Bounded Context Game
im Team definiert worden). Die befüllten Bounded Context Canvases werden physisch oder digital als Spielfeld verwendet. 
Sind Subdomäne bekannt, empfiehlt es sich, Bounded Contexts der gleichen Subdomäne gruppiert auf dem Spielfeld darzustellen. 

## Spielverlauf

> 1 Dependency Storming (10 Minuten)

Im ersten Schritt des Context Mapping Game entscheidet sich das Team für die erste Mission, d.h. für den zu 
betrachtenden Bounded Context. Jede Mission wird auf der Spieloberfläche ohne gelegte Spielkarten begonnen. 
Die Mission beginnt mit 10 Minuten Brainstorming von Abhängigkeiten (Dependency Storming). 

> 2 Gruppierung der Abhängigkeiten (10 Minuten)

Nun werden gleiche Nennungen gruppiert und die Abhängigkeiten nach eingehenden und ausgehenden Abhängigkeiten unterteilt. 
Abhängigkeiten werden auf dem Spielfeld als Sticky Note platziert. 

Optional kann die zum Ausdruck gebrachte Abhängigkeit durch eine Linie zwischen den Bounded Contexts
auf dem Spielfeld visualisiert werden.

> 3 Diskussion und Karten legen (15 Minuten)

Jede Abhängigkeit wird im Team mit einer Timebox von 15 Minuten intensiv diskutiert. Hierbei hat jede Spieler:in die Möglichkeit
seine Ansicht kunde zu tun, andere Ansichten zu konsumieren und in den Austausch über die dargestellten Sachverhalte zu gehen.

> 4 Diskussion und Karten legen (15 Minuten)

Anschließend legt jede Spieler:in eine oder mehrere Spielkarten für den Upstream- und Downstream-Kontext der Abhängigkeit
auf das Spielfeld. Wie viele Spielkarten je Spieler:in gelegt werden, hängt den ausgewählten Context Mapping Pattern ab.

Wenn sich eine gemeinsame Basis mit Ausreißern ergibt, kann die Entscheidungsfindung 
durch die Erklärung der Ausreißer gestartet werden. Wenn das Ergebnis sehr unterschiedlich
oder einheitlich ist, beginnt eine Spieler:in mit der Schilderung ihrer Sicht.

Die Spielkarten, die nicht mehr korrekt hinsichtlich der Abhängigkeiten sind, werden vom Spielfeld während 
der Diskussion entfernt. Die Abhängigkeit ist definiert, wenn die gelegten Karten
* eine valide Abbildung der Context Mapping Pattern darstellen und 
* für Down- bzw. Upstream Bounded Context nur ein Context Mapping (Ausnahme Open Host Service und Published Language) gelegt ist und
* alle Spieler:innen über diese Definition der Beziehung übereinstimmen.

> Visueller Spielablauf

![Spielablauf Context Mapping Game](../img/cmg-gameplay.png)

