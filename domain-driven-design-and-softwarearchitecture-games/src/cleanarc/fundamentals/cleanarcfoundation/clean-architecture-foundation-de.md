# Die Grundlagen, die zählen

In der heutigen, schnelllebigen Softwareentwicklung sind Wartbarkeit und Anpassungsfähigkeit 
keine „Nice-to-haves“ mehr – sie sind überlebenswichtig. Mit wachsender Projektgröße und sich ständig ändernden 
Anforderungen muss auch die Architektur mithalten. Für Entwickler:innen, die skalierbare, testbare und 
langlebige Lösungen anstreben, bietet die **Clean Architecture** ein starkes Fundament.

Aber worum geht’s dabei genau? Und wie setzt man es sinnvoll ein, ohne zu übertreiben? Werfen wir einen Blick darauf.

---

## 🧱 Was ist Clean Architecture?

Clean Architecture, bekannt geworden durch Robert C. Martin, 
ist ein Architekturmuster, das auf der **strikten Trennung von Fachlichkeit und Technik** 
basiert. Ziel ist es, die Abhängigkeit zu Frameworks, Datenbanken, Dateisystem, 
Message Broker und andere Infrastrukturkomponenten aus dem Anwendungscode herauszulösen, um 
eine langfristig flexible Architektur zu schaffen.

Die Clean Architecture hat ein **konzentrisch angeordnetes Ringmodell** (Abb. 1), wobei jeder Ring
eine eindeutige Verantwortung hat:

| Ring                                                                                  | Description                                                                                                                                                                                                                   |
|---------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Entities                                                                              | Geschäftsobjekte, -logik und -regeln.                                                                                                                                                                                |
| Use Cases                                                                             | Ermöglicht die Ausführung von Anwendungsfällen und kapselt den Zugriff auf den Entities Ring.                                                                                                                                 |
| Interface Adapters | Integration von technischen Komponenten sowie Adaption auf die fachliche Applikation, bestehend aus Use Cases und Entities Ring. Typische Interface Adapter sind Controller, Mapper, Repositories, Service Clients, Gateways, usw. |
| Frameworks \& Drivers | Framework- und Infrastrukturkomponenten, die durch die Interface Adapters integriert werden. Typische Infrastrukturkomponenten sind HTTP, Datenbank, Message Broker, Dateisystem,, Cache, Treiber, usw.                       |


> **Die goldene Regel:** 
> 
> **Abhängigkeiten** zeigen **nach innen**. 
> 
> Der innerste Ring in der Clean Architecture ist der Entities Ring.
> 
> In anderen Worten: Die fachliche Applikation, bestehend aus Use Cases und Entities Ringen, 
> kennt weder Framework noch Datenbank noch andere technische Komponenten.

![Abb. 1: Ring Modell der Clean Architecture](../../../img/clean-arc-ring-model.png)

---

## 💡 Clean Architecture vs horizontale Schichtenarchitektur

Das Architekturmuster der **horizontalen Schichtenarchitektur** (Abb. 2) hat lange die Architekturen 
der Applikationslandschaften von Unternehmen dominiert. Bis heute findet diese 
Muster eine breite Anwendung - auch in der Greenfield-Entwicklung.

![Abb. 2: Horizontale Schichtenarchitektur](../../../img/horizontal-layered-arc.png)

Die horizontale Schichtenarchitektur funktioniert in Softwaresystemen von geringer Komplexität gut.
Die Einfachheit des Musters ermöglicht ein schnelles Voranschreiten, unter der Annahme Anforderungen
sind gut bekannt und stabil. 

In komplexeren Systemen entsteht oft eine enge Kopplung zwischen fachlichen und technischen Code. 
Dies erhöht die Komplexität des Quellcodes. Die Wartbarkeit und insbesondere auch die Erweiterbarkeit wird
dadurch langfristig erschwert und teuer.

Kopplung zu Framework- oder Infrastrukturkomponenten werden auf Codeebene 
sichtbar durch z.B.:
- JPA, REST oder andere Annotationen für die Integration von technischen Komponenten in Klassen mit Geschäftsobjekte und -logik
- Logik in Repositories
- Validierungen und Geschäftsregeln in Controllern
- Datenbankzugriffe im Domänenmodell
- Umgang mit Kommunikationsprotokolle vermischt mit der Geschäftslogik

Clean Architecture entkoppelt bewusst deutlich stärker. Hierfür wird eine smarte 
Kombination von Design-Prinzipien gebündelt und zu einem stimmigen und ganzheitlichen Architekturmodell
kombiniert. Dazu gehören:
- **Single Responsibility Principle**: Jede Klasse hat nur eine Aufgabe bzw. nur einen Grund sich zu ändern
- **Separation of Concerns**: Jedes Modul hat nur eine fachliche oder technische Verantwortung
- **Dependency Inversion Principle**: Abhängigkeiten zeigen immer auf Abstraktionen, nicht auf konkrete Implementierungen
- **Interface Segregation Principle**: Viele spezifische Schnittstellen sind besser als eine allgemeine
- **Ports and Adapters**: Die fachliche Applikation ist von der Außenwelt isoliert mittels Ports (Schnittstellen). 
 Adapter der Außenwelt implementieren oder nutzen diese Schnittstellen.

---

## Ein Anwendungsfall in der Clean Architecture abbilden

Die Beispielanwendung, hat die Aufgabe Prozesse in einem Autohaus abzubilden, dessen Herzstück die Verwaltung 
der Werkstattaufträge darstellt.

Der **Werkstattauftrag** (`GarageOrder`) ist hierbei ein führendes 
Geschäftsobjekt (Entity), auf das mehrere Anwendungsfälle ausgeführt werden. 
Beispiele hierfür sind:
- Einen neuen Auftrag anlegen
- Auftragsdetails erfassen und aktualisieren
- Auftragspositionen hinzufügen oder entfernen
- Den Status eines Auftrags aktualisieren
- Den Auftrag einem Mitarbeiter zuweisen
- Den Auftrag einem Kunden sowie Fahrzeug zuordnen
- Einen Auftrag abschließen oder stornieren

Die dargestellten Anwendungsfälle stellen das Verhalten des Geschäftsobjektes dar. 
Des Weiteren enthält ein Geschäftsobjekt 
- Eigenschaften, wie z.B. eine Auftragsnummer (`OrderNumber`) oder einen 
Auftragsstatus (`OrderState`), sowie 
- Geschäftsregeln, wie z.B. die Prüfung, das nur ein erlaubter Statusübergang unter 
Berücksichtigung von Reihenfolge und erlaubte Folgestatus stattfindet. 

Die Entity `GarageOrder` kapselt das Verhalten, die Eigenschaften und die Regeln 
und wird auf dem Entities Ring verortet (Abb. 3).

![Abb. 3: Geschäftsobjekte des Entities Ring](../../../img/.png)

Der Anwendungsfall "Einen neuen Auftrag anlegen" wird im Use Cases Ring durch die Schnittstelle 
`CreateGarageOrder` repräsentiert. Die konkrete Implementierung, `CreateGarageOrderInteractor`,
befindet sich ebenfalls im Use Cases Ring. Der Interactor koordiniert die Erstellung eines neuen Auftrags.
Er nutzt die Entity `GarageOrder` aus dem Entities Ring, um ein neues 
Auftragsobjekt zu erstellen, sowie Verhalten und die Geschäftsregeln auszuführen.

Anschließend verwendet der Interactor ein Repository-Interface (`SaveGarageOrder`), 
um den neuen Auftrag zu persistieren. Das Repository-Interface ist eine Abstraktion, die im Use Cases Ring, als sogenannter
Use Case Output Port, definiert ist.

Mit der Unterscheidung in Use Case Input Ports und Use Case Output Ports 
wird die fachliche Applikation in eine 
- eingehende Seite, Konsumenten nutzen die Applikation über einen technischen Kanal, und 
- eine ausgehende Seite, die Applikation nutzt technische Komponenten über einen technischen Kanal,

unterteilt. 

Der Use Case Interactor bringt diese beiden Seiten zusammen, in dem er einen 
Use Case Input Port implementiert und im Ablauf
des Anwendungsfall (Flow of Control) Use Case Output Ports nutzt. Die Abbildung des
Flow of Control ist die Kernaufgabe eines Use Case Interactors.

![Abb. 4: Das Klassenmodell des Use Cases und Entities Ring](../../../img/.png)

Die konkrete Implementierung des Repositories (`GarageOrderRepository`) 
befindet sich im Interface Adapters Ring. Das `GarageOrderRepository` implementiert den
Use Case Output Port `SaveAuftrag` und nutzt die Framework-Komponente JPA, 
um die `GarageOrderDbEntity` in der Datenbank zu speichern. 

Der Adapter übernimmt an dieser Stelle die Aufgabe, die Transformation zwischen 
den Entitäten der fachlichen Applikation (Entity Model) und der technischen Infrastruktur
(Adapter Model) zu überführen. Es empfiehlt sich die Verantwortung der Transformation
ein einer sogenannten Mapper-Klasse zu kapseln.

Das `GarageOrderRepository` ist ein Interface Adapter, der ausgehende Seite der 
Applikation. Als Beispiele für Interface Adapter der eingehenden Seite dient ein 
REST-Controller, der eine HTTP-Anfrage für die Anlage eines neuen Auftrags entgegennimmt 
und verarbeitet. Für die Verarbeitung nutzt der `GarageOrderController` den eingehenden 
Use Case Input Port `CreateGarageOrder`, um die Auftragsanlage durchzuführen. Für die
Transformation zwischen Adapter-Modell und Entity-Modell wird ebenfalls eine Mapper-Klasse eingesetzt.

Für die Umsetzung der goldenen Regeln der Abhängigkeitsrichtung - Abhängigkeiten müssen immer in das 
Innere des Ringmodells zeigen - und somit die Implementierung einer 
technologie-agnostischen fachlichen Applikation, werden drei Konzepte angewendet:

1. **Adapter Model**: Adapter Modelle repräsentieren die Datenstrukturen, die von den Adaptern verwendet werden (z. B. REST-Ressourcen, Datenbank-Entitäten). Sie sind spezifisch für die jeweilige Technologie und sollten nicht direkt in der Domäne verwendet werden.
2. **Mapper**: Mapper sind verantwortlich für die Umwandlung zwischen Adapter- und Domänenmodellen. Sie isolieren die Domäne von den technischen Details der Adapter und ermöglichen eine klare Trennung der Verantwortlichkeiten.
3. **Ports & Adapter**: Adapter sind die Komponenten, die die
   ein- und ausgehende Use Cases (= Ports) nutzen bzw. implementieren. Hierbei wird das
Dependency Inversion Principle angewendet und es entsteht eine Abhängigkeitsumkehr, 
mit einer strikten Abhängigkeitsrichtung zum Inneren des Ringmodells hin.


![Abb. 4: Das Klassenmodell im Gesamtbild mit Interface Adapters](../../../img/.png)

Anhand von diesem beispielhaften Anwendungsfall wird deutlich, 
wie die Clean Architecture eine klare Trennung zwischen Fachlichkeit und Technik anstrebt.

Abb.5 stellt die Mustersprache der Clean Architecture verallgemeinert dar.

![Abb. 5: Das Klassenmodell im Gesamtbild mit Interface Adapters](../../../img/.png)

## ⚙️ Strukturierung mittels Packages

Die Paketstruktur in Listing 1, bildet die Ringe der Clean Architecture als Packages ab. 
Dabei wird die Unterscheidung der Interface Adapters und Use Cases als ein- und ausgehend
anhand der kurzen Begriffe "in" und "out" als Sub-Packages abgebildet. 
Diese Abbildungsvariante wird als **architektonisch Ausdrucksstark** (Architectural Expressive)
bezeichnet.

```
|-- de.arkem.javapro.cleanarc
    |-- adapters
    |    |-- in
    |    |  |-- GarageOrderController.java
    |    |  |-- GarageOrderResource.java
    |    |  |-- GarageOrderToResourceMapper.java
    |    |-- out
    |    |  |-- GarageOrderRepository.java
    |    |  |-- GarageOrderDbEntity.java
    |    |  |-- GarageOrderToDbEntityMapper.java
    |-- usecases
    |    |-- in
    |    |  |-- CreateGarageOrder.java
    |    |-- interactor
    |    |  |-- CreateGarageOrderInteractor.java
    |    |-- out
    |    |  |-- SaveGarageOrder.java
    |-- entities
    |    |-- GarageOrder.java
```
[Listing 1: Paketstruktur mit Klassen in der Clean Architecture am Beispiel Auftrag anlegen]()

## 🧭 Fazit: Wann ist Clean Architecture sinnvoll?

Clean Architecture lohnt sich besonders, wenn:

- Deine Anwendung langfristig erweiterbar bleiben soll.
- Du testbare, robuste Fachlogik willst.
- Du Abhängigkeiten zu Frameworks- und Infrastrukturkomponenten minimieren willst.
- Du Flexibilität und schnelle Anpassbarkeit bei Technologieentscheidungen brauchst.

Aber Achtung: Für einfache CRUD-Anwendungen kann Clean Architecture zu viel sein.

---

## 🧪 Weiterführende Links

- [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

## 🧪 What's next?

Nach dem Aufbau der Grundlagen der Clean Architecture, widmen wir uns im nächsten Blog 
vertikaler Schichtung, taktischen Domain-Driven Design und dem Zusammenhang dieser 
Konzepte mit der Clean Architecture. Dies erweitert unser Wissen um die Clean Architecture
in einer zeitgemäßen Implementierung, die eine Evolution des Systems über den gesamten Lebenszyklus
anstreben.
 
---
