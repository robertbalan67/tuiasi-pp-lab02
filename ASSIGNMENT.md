# Lab 02 — Programare Polyglot cu GraalVM (JavaScript + Kotlin)

Temă de programare polyglot: apelarea codului JavaScript din Kotlin prin API-ul GraalVM Polyglot.
Aplicația procesează o listă de cuvinte calculând suma de control (checksum) în JavaScript,
apoi identifică cuvintele cu aceeași sumă de control.

---

## Structura proiectului

```
lab02/
├── src/
│   ├── main/kotlin/ro/tuiasi/pp/lab02/
│   │   └── PolyglotApp.kt       ← funcții de implementat
│   └── test/kotlin/ro/tuiasi/pp/lab02/
│       └── PolyglotAppTest.kt   ← teste (nu se modifică)
├── .github/workflows/classroom.yml
├── build.gradle.kts
├── settings.gradle.kts
├── ASSIGNMENT.md
└── README.md
```

---

## Context: GraalVM Polyglot API

GraalVM permite executarea codului din mai multe limbaje într-o singură aplicație.
API-ul principal este `org.graalvm.polyglot.Context`:

```kotlin
Context.create("js").use { ctx ->
    ctx.eval("js", "1 + 2")          // evaluează expresie
    ctx.eval("js", "function f(x) { return x * 2; }")
    val result = ctx.eval("js", "f(5)")
    val intVal = result.asInt()       // extrage valoarea ca Int
}
```

---

## Cerințe

### 1. `computeChecksumJS(word: String): Int`

Calculează suma de control a unui cuvânt apelând funcția JavaScript `checksum(word)`
deja definită în constanta `CHECKSUM_JS`.

- Creează un `Context` GraalVM pentru JavaScript
- Evaluează `CHECKSUM_JS` pentru a defini funcția `checksum`
- Apelează `checksum(word)` și returnează rezultatul ca `Int`

**Exemplu:**
```kotlin
computeChecksumJS("abc")   // -> 294  (97 + 98 + 99)
computeChecksumJS("bca")   // -> 294  (aceleași caractere, ordine diferită)
computeChecksumJS("")      // -> 0
```

---

### 2. `upperCaseWordsJS(words: List<String>): List<String>`

Convertește fiecare cuvânt din listă la majuscule folosind JavaScript (`.toUpperCase()`).

- Creează un `Context` GraalVM
- Pentru fiecare cuvânt, evaluează o expresie JS care îl convertește la majuscule
- Returnează lista transformată

**Exemplu:**
```kotlin
upperCaseWordsJS(listOf("ana", "mere"))  // -> ["ANA", "MERE"]
upperCaseWordsJS(emptyList())            // -> []
```

---

### 3. `groupByChecksum(words: List<String>): Map<Int, List<String>>`

Grupează cuvintele după suma de control calculată cu `computeChecksumJS`.
Returnează **doar grupurile cu cel puțin 2 cuvinte** (cuvintele cu sumă unică sunt excluse).

- Post-condiție: Map<checksum, lista_cuvinte> cu grupuri de minim 2 elemente
- Ordinea cuvintelor în fiecare grup = ordinea din `words`

**Exemplu:**
```kotlin
groupByChecksum(listOf("abc", "bca", "hello", "aa"))
// -> {294: ["abc", "bca"]}
// "hello" (checksum 532) și "aa" (checksum 194) sunt singure → excluse
```

---

### 4. `printDuplicateChecksums(words: List<String>)`

Afișează la consolă grupurile cu duplicate, câte un grup pe linie.

**Format:** `Checksum <N>: [cuvant1, cuvant2, ...]`

**Exemplu de output:**
```
Checksum 294: [abc, bca]
```

---

## Cum se rulează testele

```bash
gradle test
```

---

## Tabel de evaluare

| Cerință                | Punctaj |
|------------------------|---------|
| `computeChecksumJS`    | 3p      |
| `upperCaseWordsJS`     | 2p      |
| `groupByChecksum`      | 3p      |
| `printDuplicateChecksums` | 2p   |
| **Total**              | **10p** |
