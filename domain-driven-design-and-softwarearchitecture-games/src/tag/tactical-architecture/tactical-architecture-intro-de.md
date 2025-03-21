# Bewusste Architekturentscheidungen als Erfolgsfaktor

Die Summe an Architekturentscheidungen prägen die Architektur eines Systems. Implizit und nicht kommunizierte 
Architekturentscheidungen haben oft eine negative Auswirkung auf die Qualität des Systems. 
Bewusst getroffene Entscheidungen im Entwicklungsteam sind jedoch ein mächtiges Mittel um Lösungsstrategien im Team
gemeinsam festzulegen. Dies führt zu einer konsistenzen Anwendung und fördert die
Verständlichkeit der Architektur.

[Architecture Enabling](), wie einführend beschrieben, manifestiert sich hier auch bewusst gelebte 
kollaborative Entscheidungsfindung. Das Ergebnis: Ein Konstruktionsplan für die taktische Softwarearchitektur
eines Bounded Context bzw. System. Methodisch unterstützt wird dies durch Gamification.


Der folgende Abschnitt beschreibt die fundamentalen Fragen, die ein Team beantworten muss. Weiterführend werden die
_Starting Tactical Architecture Decisions_ für domänenzentrierte und geschichtete Architekturmuster beschrieben. 

| Grundlegene Fragestellung                                                  | Elemente der Entscheidungsfindung                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Methodische Unterstützung                         |
|----------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------|
| **Welches** Architekturmuster soll verwendet werden?                       | Domänenzentrische Architekturmuster:<br/>1) Clean Architecture<br/>2) Hexagonal Architecture<br/>3) Onion Architecture<br/><br/>Geschichtete Architekturmuster:<br/>1) Package by Layer<br/>2) Package by Component<br/>3) Package by Feature<br/><br/>**Komplexität:**<br/>1) Essentielle Komplexität<br/>2) Technische Komplexität<br/>3) Qualitativ-bedingte komplexität<br/> 4) Evolutions-bedingte Komplexität <br/><br/>Mehr dazu:<br/>[Architekturmuster]()<br/>[Bewertung von Architekturmuster]() | **Tactical Architecture Game**: Architekturmuster |
| **Wie** wird das Architekturmuster im konkreten Projektkontext angewendet? | Kombination von Architekturentscheidungen, die ein Team zu Beginn gemeinsam finden sollte und dadurch einen gemeinsamen Konstruktionsplan für die Anwendung des Musters festlegt. Die zu treffenden Architekturentscheidungen hängen von eingesetzten Muster ab.<br/><br/>Mehr dazu:<br/>[Konstruktionsplan für Architekturmuster](tactical-architecture-decisions-de)                                                                                                                                    | **Tactical Architecture Game**: Konstruktionsplan |                      
| **Wie** gestaltet sich die Mustersprache?                                  | Das taktische Domain-Driven Design bringt eine Reihe von Entwurfsmuster für die Programmierung mit. In Kombination mit den Eigenschaften des Architekturmuster ergibt sich eine Mustersprache. Diese muss auf einen gemeinsamen Verständnis im Team beruhen um gleichartig angewendet zu werden.                                                                                                                                                                                                                            | **Tactical Architecture Game**: Konstruktionsplan |

Durch Grundsatzentscheidungen, wie z.B. das einzusetzende Architekturmuster oder die Anwendung des
taktischen DDD, manifestieren sich weiche Vorgaben (Soft Standards) für die Architektur.
Diese Vorgaben sind zu Abstrakt um ohne weitere Analyse und Austausch im Team,
diese zielführend und erfolgreich umzusetzen. Folglich müssen diese detailliert werden.

Die unterschiedlichen Ausprägungen des Tactical Architecture Game fördern die
kollaborative Entscheidungsfindung im Team für die grundlegenden Fragestellungen 1-3. 
Die damit verbunden Architekturentscheidungen werden als _Starting Tactical Architecture Decisions_ bezeichnet. 
Ihr Ziel ist es das Team zu befähigen die taktische Architektur konsistenz anzuwenden und die damit
Qualitätsziele Wartbarkeit, Erweiterbarkeit und Flexibilität zu erreichen.



