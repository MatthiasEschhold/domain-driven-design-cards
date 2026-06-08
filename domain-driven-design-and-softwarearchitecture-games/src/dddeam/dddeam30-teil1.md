# Wenn Domain-Driven Design nicht mehr ausreicht

## Architekturarbeit im Großmaßstab neu denken

Domain-Driven Design (DDD) hat sich als eines der wirksamsten Mittel etabliert, um komplexe Softwaresysteme sinnvoll 
zu strukturieren. Durch eine enge Ausrichtung an der Business-Domäne, Bounded Contexts und eine gemeinsame Sprache hilft 
DDD Teams dabei, Systeme zu bauen, die nicht nur technisch funktionieren, sondern fachlich stimmig sind.

Solange sich die Business-Domäne von einer überschaubaren Anzahl von Teams verantworten lässt, bietet DDD und 
Agilität genügend Orientierung. Teams können eigenständig gute Architekturentscheidungen treffen und 
sich schnell weiterentwickeln, ohne die Kontrolle zu verlieren. Doch ab einer bestimmten Größe entstehen neue Herausforderungen.

---

## Wenn die Domäne größer wird als das Team

In großen Organisationen ist die Business-Domäne selten klein oder klar abgegrenzt. Sie spannt sich über mehrere Produktbereiche, Organisationseinheiten und lange Zeiträume. Dutzende Teams arbeiten parallel an Greenfield-Systemen, Legacy-Modernisierung und allem dazwischen.

An diesem Punkt stößt DDD an seine natürlichen Grenzen. Nicht, weil die Konzepte falsch wären, 
sondern weil DDD primär auf **Architektur- und Modellierungsentscheidungen auf Team- und Systemebene** ausgerichtet ist. 
Fragen wie diese bleiben offen:

- Wie steuern wir eine Business Domäne, die über viele Teams verteilt ist?
- Wie schaffen wir architektonische Orientierung und Fördern Eigenverantwortung über mehrere Jahre?
- Wie kommunizieren wir Architektur gegenüber dem Management und Fachbereichen, ohne in technische Details abzutauchen?

Diese Fragen lassen sich nicht mehr mit Design auf strategischer und taktischer Ebene lösen. 
Es sind **Koordinations- und Führungsprobleme**, die Werkzeuge aus dem Bereich Leadership, Management und skaliere Agilität bedingen.

---

## Architektur wird zur Führungsaufgabe

Mit wachsender Größe verändert sich die Rolle von Architektur grundlegend. Architektur ist nicht mehr nur das Ergebnis guter Designentscheidungen, sondern beeinflusst Investitionen, Prioritäten und organisatorische Strukturen.

Damit entsteht ein Spannungsfeld:

- Teams brauchen Autonomie, um effektiv zu arbeiten.
- Die Organisation braucht Alignment, um Fragmentierung zu vermeiden.

Zentrale Kontrolle bremst Teams aus.  
Vollständige Dezentralisierung führt zu Inkonsistenz.

Beides skaliert nicht.

---

## Warum DDD ergänzt – nicht ersetzt – werden muss

Eine naheliegende Reaktion ist, DDD zugunsten klassischer Enterprise-Architekturansätze aufzugeben. Das wäre ein Fehler.

DDD bleibt essenziell. Es ist weiterhin der beste Ansatz, um Software nah an der Domäne zu gestalten und Teams zu befähigen, gute lokale Entscheidungen zu treffen. Was fehlt, ist eine **zusätzliche Ebene**, die:

- domänenweite Sichtbarkeit schafft
- strategische Zielbilder ermöglicht
- Architektur über Zeit steuerbar macht
- Entscheidungsräume klar definiert

Hier kommen Praktiken des **Enterprise Architecture Managements (EAM)** ins Spiel – nicht als schwergewichtiges Governance-Instrument, sondern als Ergänzung zu DDD.

---

## Die erste Brücke: Von Bounded Contexts zu Business Capabilities

Eines der größten Probleme im Großmaßstab ist fehlende gemeinsame Sprache. Während Entwicklungsteams in Bounded Contexts denken, argumentieren Management und Fachbereiche in Fähigkeiten, Produkten und Verantwortlichkeiten.

Eine **Business Capability Map** hilft, diese Welten zu verbinden.

Capabilities beschreiben, *was* ein Unternehmen können muss – unabhängig davon, *wie* es technisch umgesetzt wird. Sie sind stabil, gut kommunizierbar und anschlussfähig für Stakeholder.

Gleichzeitig lassen sich Capabilities mit Bounded Contexts verknüpfen. So entsteht eine Brücke zwischen strategischer Sicht und konkreter Architektur.

---

## Architektur über Zeit denken

Ein weiterer blinder Fleck von „purem“ DDD ist die Zeitdimension. DDD beschreibt vor allem Zielarchitekturen. In der Realität existieren jedoch Legacy-Systeme, technische Schulden und bewusste Übergangszustände.

EAM adressiert das mit **Architektur-Roadmaps** und **Zwischenarchitekturen**. Diese machen explizit, dass nicht jeder Zustand ideal ist – aber notwendig. Architektur wird damit von einem statischen Ideal zu einem **bewusst gesteuerten Entwicklungsprozess**.

---

## Architektur ist auch eine Entscheidungsfrage

Sobald Architektur viele Teams und lange Zeiträume umfasst, rückt eine zentrale Frage in den Vordergrund: *Wer entscheidet was?*

Architekturentscheidungen sind nie rein technisch. Sie betreffen Verantwortung, Budgets und Zusammenarbeit. Implizite oder informelle Entscheidungen funktionieren im Großmaßstab nicht.

Hier liefert **Management 3.0** einen wichtigen Impuls. Das **Delegation Board** macht Entscheidungsräume explizit und transparent. Übertragen auf Architektur entsteht ein **Architecture Delegation Board**, das klar festlegt, welche Entscheidungen bei Teams liegen, welche abgestimmt werden müssen und welche strategisch sind.

Architektur wird so von Kontrolle zu **ermöglichender Führung**.

---

## Ausblick auf Teil 2

In diesem ersten Teil haben wir gesehen, warum Domain-Driven Design im Großmaßstab an Grenzen stößt – und warum die Antwort nicht im Ersatz, sondern in der Erweiterung liegt.

Im **zweiten Teil** geht es darum, wie sich große Business-Domänen konkret steuern lassen:  
mit Business Capability Maps, Architektur-Roadmaps, Zwischenarchitekturen und einer bewussten Modernisierungsstrategie – ohne die Autonomie der Teams zu verlieren.

DDD bleibt der Motor.  
Enterprise Architecture liefert die Landkarte.

## Klare Übergänge sowie Emergentes Verhalten ziwschen DDD und EAM

Capa Map -> CoMo -> Roadmap -> Zwischenarchitektur -> Strategische Architektur -> Taktische Architektur -> Applicationsarchitektur

Bei den Betrachtung der Wechselwirkungen zwischen den Disziplinen stellen wie wiederkehren synergien, symbiose und Kreisläufe fest.

Wenn Agilität als operatives Prozessmodell hinzukommt:
Roadmap -> Betirebsmodell -> BI Planning