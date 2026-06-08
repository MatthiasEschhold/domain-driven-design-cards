Gute Frage — ich schlage kurz vor, wie ich vorgehe: 1) Beschreibe Kriterien für einen guten Namen, 2) nenne sinnvolle Alternativen mit Vor-/Nachteilen, 3) zeige ein kurzes Code‑Beispiel mit meiner Empfehlung und Varianten, 4) nenne noch ein paar Hinweise zu Exceptions/Tests. Soll ich das direkt in OrderNumber.java einbauen?
Checklist
<input></input>Kriterien für Benennung erläutern
<input></input>Konkrete Namensvorschläge + Vor-/Nachteile
<input></input>Empfohlenes Beispielcode‑Snippet (record) zeigen
<input></input>Hinweise zu Exceptions, Tests und Mehrfachinvarianten geben
Kriterien für einen guten Namen
Kurz, aussagekräftig und deklarativ/imperativ (bei Validierungs-/Vorbedingungen ist imperativ üblich: require/ensure/check/validate).
Domain‑kontext: wenn Methode nur in OrderNumber lebt, muss der Klassename nicht wiederholt werden.
Signalisiert Verhalten: wirft die Methode Exceptions (require/ensure) oder gibt sie nur Bool zurück (isValid/check)?
Bei mehreren Invarianten: plural oder generischer Name (checkInvariants/validateState).
Konkrete Namensvorschläge (mit Kommentar)
validate(int value) — neutral, verbreitet; passt gut, wenn Methode “prüft” und ggf. throwt.
requireValid(int value) — signalisiert klar: Vorbedingung, wirft bei Verstoß; gut für fail‑fast.
ensurePositive(int value) / requirePositive(int value) — sehr präzise; ideal wenn die Invariante sehr spezifisch ist (hier: > 0).
checkInvariants() / validateInvariants() — wenn mehrere Bedingungen geprüft werden (meist non‑static).
isValid(int value) — nur prüfen, gibt boolean zurück; nützlich für tryParse-Pattern.
Warum nicht z. B. fulfillInvariant?
„fulfill“ klingt nach Ausführung/Erfüllung, nicht nach Prüfung; nicht treffend für eine Validierungsmethode.
Meine Empfehlung
Wenn die Methode die Invariante validiert und bei Fehlern eine Exception wirft: nutze ein imperatives, aussagekräftiges Verb wie requireValid oder requirePositive.
Wenn du präzise sein willst: requirePositive (oder requirePositiveValue) — sehr klar.
Wenn du generisch bleiben willst: requireValid oder validate.
Beispiel (empfohlen: requireValid als private static, gibt den (ggf. normalisierten) Wert zurück — praktisch in Records)
Vorschlag 1 — requireValid (throws)
// ...existing code...
public record OrderNumber(int value) {

    public OrderNumber {
        value = requireValid(value);
    }

    private static int requireValid(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("order number must be positive (>0)");
        }
        return value;
    }

    // ...existing code...
}
Vorteile: sehr deutlich, fail‑fast, ermöglicht einfache Transformation/Normalisierung (z. B. trim/parse vorher).
Vorschlag 2 — sehr spezifisch requirePositive:
private static int requirePositive(int value) {
if (value <= 0) {
throw new IllegalArgumentException("must be > 0");
}
return value;
}
Vorteil: Name beschreibt genau die geprüfte Bedingung.
Vorschlag 3 — isValid (wenn du lieber kein Exception‑Werfen innerhalb der Methode möchtest):
private static boolean isValid(int value) {
return value > 0;
}
// Verwendung: if (!isValid(value)) throw ...
Vorteil: klar trennbar Prüfung vs. Verhalten (werft die Ausnahme).
Auswahlhilfe / Konventionen
Library/Team: Viele Teams nutzen requireXxx für Vorbedingungen (ähnlich zu Guava Preconditions.checkArgument).
Lokalität: In einer ValueObject‑Klasse kannst du den Klassennamen weglassen; in Utility‑Klassen wäre requireValidOrderNumber passender.
Mehrere Invarianten: benutze checkInvariants() oder validateState() und halte einzelne private Helfer für jede Bedingung (z. B. requirePositive, requireMaxLength).
Exception‑Typ
IllegalArgumentException ist üblich und ausreichend.
Wenn du Domain‑spezifische Fehlerbehandlung willst, erstelle z. B. InvalidOrderNumberException extends IllegalArgumentException — hilfreich, falls du später Fehler semantisch unterscheiden willst.
Tests
Schreibe Unit‑Tests für:
gültige Werte (z. B. 1, Integer.MAX_VALUE)
0 und negative Werte → assertThrows
parse/tryParse Verhalten (falls vorhanden)
Testnamen z. B. constructor_withZero_throwsIllegalArgumentException.
Nächste Schritte
Soll ich jetzt die OrderNumber‑Datei so refactoren und die private Methode requireValid (oder eine andere bevorzugte Variante) einfügen?
Oder möchtest du zuerst einen Namen wählen (z. B. requireValid vs requirePositive), damit ich die Änderung direkt vornehme und gleich Unit‑Tests anlege?