# Bewusste Architekturentscheidungen als Erfolgsfaktor

>Every voice shapes the system! 
>
>_- Kenny Baas-Schwegler_

Die Summe an Architekturentscheidungen prägen die Architektur eines Systems. Implizit und nicht kommunizierte 
Architekturentscheidungen haben oft eine negative Auswirkung auf die Qualität des Systems. 
Bewusst getroffene Entscheidungen im Entwicklungsteam sind jedoch ein mächtiges Mittel um Lösungsstrategien im Team
konsistenz und nachhaltig anzuwenden.

Der Aspekt des Enabling bezieht sich an dieser Stelle darauf, dass dem Team Wissen, Rahmen und Raum für gemeinsame
Architekturentscheidungen bereitgestellt wird. Das Ziel im Hinblick auf das Tactical Architecture Game eine breite 
Transparenz und Commitment im Team über die taktische Softwarearchitektur eines Bounded Context oder Softwaresystem.

Der folgende Abschnitt beschreibt drei fundamentale Fragen, die ein Team in Form von Architekturentscheidungen beantworten muss.

| Grundlegene Fragestellung                                      | Elemente der Entscheidungsfindung                                                                                                                                                                                                                                                                                                                                                          | Methodische Unterstützung                          |
|----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|
| **Welches** Architekturmuster soll angewendet werden?          | Domänenzentrische Architekturmuster:<br/>1) Clean Architecture<br/>2) Hexagonal Architecture<br/>3) Onion Architecture<br/><br/>Geschichtete Architekturmuster:<br/>1) Package by Layer<br/>2) Package by Component<br/>3) Package by Feature<br/><br/>**Komplexität:**<br/>1) Essentielle Komplexität<br/>2) Technische Komplexität<br/>3) Qualitativ-bedingte komplexität<br/> 4) Evolutions-bedingte Komplexität | **Tactical Architecture Game**: Architekturmuster  |
| **Wie** wird das Architekturmuster konkreten implementiert? | Ein Set granularer Architekturentscheidungen, mit dem Charakter eines Konstruktionsplans für das Team, hinsichtlich der Umsetzung des Architekturmuster.<br/>Die zu treffenden Architekturentscheidungen hängen in Teilen vom Architekturmuster ab. |  **Tactical Architecture Game**: Konstruktionsplan |                      
| **Wie** gestaltet sich die Mustersprache?                      | Das taktische Domain-Driven Design bringt eine Reihe von Entwurfsmuster für die Programmierung mit. In Kombination mit den Eigenschaften des Architekturmuster ergibt sich eine Mustersprache. Diese muss auf einen gemeinsamen Verständnis im Team beruhen um gleichartig angewendet zu werden. | **Tactical Architecture Game**: Konstruktionsplan  |

Durch Grundsatzentscheidungen, wie das Architekturmuster oder die Anwendung des
taktischen DDD, manifestieren sich weiche Vorgaben (Soft Standard) für die Architektur.

Diese Vorgaben sind für die Implementierungsebene zu Abstrakt, 
und ohne weitere gemeinsame Entscheidungsfindung ist die Erreichung einer konsistenten Implementierung und die 
damit verbundenen Qualitätsziele Wartbarkeit, Erweiterbarkeit und Flexibilität, nicht möglich.

Die unterschiedlichen Ausprägungen des Tactical Architecture Game fördern die
kollaborative Entscheidungsfindung im Team für die grundlegenden Fragestellungen 1-3.



