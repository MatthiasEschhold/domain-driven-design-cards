# Clean Architecture Vibe Coding

## Vibe Coding - Create Your Architecture and Design Governance

Wie mit menschlichen Teamkollegen definieren wir mit unseren Agenten zu Beginn des Projektes 
einen gemeinsamen "Vibe" zur Sicherstellung der technischen Qualität des generierten Codes.

Als Architekt betrachte ich das System von Außen nach Innen oder ausgedrückt vom Groben ins Detail.
Der "Vibe" repräsentiert die übergeordneten Prinzipien und Leitlinien, um konsistente und qualitativ 
hochwertige Architektur- und Designlösungen zu erzeugen. 

Ein "Vibe" wird technisch als Konfigurationsdatei implementiert, dass von den Agenten referenziert wird.
In der Regel nennt man diese Konfigurationsdatei "agent-instructions" oder "custom-instructions".

Zur Besserung Übersicht enthält die "agent-instructions.md" im Ordner "agent-instructions" eine Auflistung aller
Instruktionsdateien für die verschiedenen Architektur- und Designregeln.

Instruktionsdateien werden bei Verwendung von GitHub Copilot in natürlicher Sprache geschrieben.

### Anlage der Agenten-Instruktionsdatei 

Erzeuge eine leere md-Datei mit dem Namen "copilot-instructions.md" und lege diese im Ordner ".github" ab. 
Existiert der Ordner ".github" nicht, so muss er erstellt werden.

Wird GitHub Copilot nicht anderweitig konfiguriert, so wird die Instruktionsdatei automatisch von GitHub Copilot
referenziert.

Die Datei enthält Referenzierungen zu weiteren Instruktionsdateien, die sich Detail und verschiedene Aspekte, wie z.B. 
Architektur oder Testing definieren.

### Grundlegende Arbeitsanweisungen für den Agenten

Grundlegende Arbeitsanweisungen für einen Agenten sind vielfältig. Im Folgenden sind einige Beispiele aufgeführt:
* **Architekturprinzipien**: Definiere die Architekturprinzipien, die der Agent befolgen soll, z.B. "Clean Architecture", "Hexagonal Architecture" oder "Onion Architecture".
* **Designmuster**: Lege fest, welche Designmuster der Agent verwenden soll
* **Programmiersprachen und Frameworks**: Bestimme die Programmiersprachen und Frameworks, die der Agent nutzen soll.
* **Code-Qualitätsstandards**: Definiere die Code-Qualitätsstandards, die der Agent einhalten soll, z.B. "Kotlin Coding Conventions", "Clean Code Principles".
* **Testing-Strategien**: Lege die Testing-Strategien fest, die der Agent anwenden soll, z.B. "Test-Driven Development", "Behavior-Driven Development".
* **Dokumentationsanforderungen**: Bestimme die Anforderungen an die Dokumentation, die der Agent erstellen soll, z.B. "Javadoc", "Markdown-Dokumentation".
* **Versionskontrolle**: Definiere die Richtlinien für die Versionskontrolle, die der Agent befolgen soll, z.B. "Git-Flow", "Semantic Versioning".
* **Sicherheitsrichtlinien**: Lege die Sicherheitsrichtlinien fest, die der Agent einhalten soll, z.B. "OWASP Top 10", "Secure Coding Practices".
* **Leistungsanforderungen**: Bestimme die Leistungsanforderungen, die der Agent berücksichtigen soll, z.B. "Minimale Latenz", "Hohe Skalierbarkeit".
* **Zusammenarbeitsrichtlinien**: Definiere die Richtlinien für die Zusammenarbeit mit menschlichen Entwicklern, z.B. "Code Reviews", "Pair Programming".
* **Ethik und Verantwortlichkeit**: Lege ethische Richtlinien fest, die der Agent befolgen soll, z.B. "Datenschutz", "Vermeidung von Bias".
* **Feedback-Mechanismen**: Bestimme, wie der Agent Feedback von menschlichen Entwicklern erhalten und darauf reagieren soll, z.B. "Feedback-Schleifen", "Kontinuierliche Verbesserung".
* **Lern- und Anpassungsfähigkeit**: Definiere, wie der Agent aus Erfahrungen lernen und sich an neue Anforderungen anpassen soll, z.B. "Maschinelles Lernen", "Kontinuierliches Lernen".
* **Projektmanagement-Methoden**: Lege fest, welche Projektmanagement-Methoden der Agent unterstützen soll, z.B. "Agile", "Scrum", "Kanban".
* **Kommunikationsprotokolle**: Bestimme die Kommunikationsprotokolle, die der Agent verwenden soll, z.B. "REST", "GraphQL", "gRPC".
* **Deployment-Strategien**: Definiere die Deployment-Strategien, die der Agent anwenden soll, z.B. "Continuous Integration/Continuous Deployment (CI/CD)", "Blue-Green Deployment".
* **Monitoring und Logging**: Lege die Anforderungen für Monitoring und Logging fest, die der Agent berücksichtigen soll, z.B. "Prometheus", "ELK Stack".
* **Regelmäßige Updates**: Bestimme, wie oft der Agent seine Instruktionsdateien aktualisieren soll, um auf dem neuesten Stand zu bleiben, z.B. "Wöchentliche Updates", "Monatliche Überprüfung".
* **Spezifische Anwendungsfälle**: Definiere spezifische Anwendungsfälle oder Szenarien, die der Agent berücksichtigen soll, z.B. "E-Commerce-Anwendungen", "Finanzanwendungen".
* **Rollen und Verantwortlichkeiten**: Lege die Rollen und Verantwortlichkeiten des Agenten im Entwicklungsteam fest, z.B. "Code-Generierung", "Code-Review", "Testing-Unterstützung".
* **Konfliktlösung**: Bestimme, wie der Agent mit Konflikten oder Unstimmigkeiten umgehen soll, z.B. "Priorisierung von Anforderungen", "Konsensfindung im Team".
* **Innovationsförderung**: Definiere, wie der Agent Innovationen fördern und neue Technologien oder Ansätze vorschlagen soll, z.B. "Technologie-Scouting", "Prototyping neuer Ideen".
* **Ressourcenmanagement**: Lege fest, wie der Agent Ressourcen effizient verwalten soll, z.B. "Speicheroptimierung", "CPU-Auslastungsmanagement".
* **Kundenzentrierung**: Bestimme, wie der Agent kundenzentriert arbeiten soll, z.B. "Berücksichtigung von Kundenfeedback", "Fokus auf Benutzerfreundlichkeit".
* **Nachhaltigkeit**: Definiere, wie der Agent nachhaltige Praktiken fördern soll, z.B. "Energieeffiziente Codierung", "Reduzierung des ökologischen Fußabdrucks".
* **Compliance und Regulierung**: Lege fest, wie der Agent Compliance-Anforderungen und regulatorische Vorgaben einhalten soll, z.B. "GDPR", "HIPAA".
* **Kulturelle Sensibilität**: Bestimme, wie der Agent kulturelle Sensibilitäten berücksichtigen soll, z.B. "Lokalisierung", "Internationale Standards".
* **Zukunftsorientierung**: Definiere, wie der Agent zukunftsorientiert arbeiten soll, z.B. "Vorbereitung auf zukünftige Technologien", "Skalierbarkeit für zukünftige Anforderungen".


