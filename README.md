# Lab 02 — GraalVM Polyglot (JavaScript + Kotlin)

Programare polyglot: apelarea codului JavaScript din Kotlin prin API-ul GraalVM.

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

## Funcții de implementat

| Funcție                   | Descriere                                           |
|---------------------------|-----------------------------------------------------|
| `computeChecksumJS`       | Calculează checksum prin funcție JS (GraalVM)       |
| `upperCaseWordsJS`        | Convertește cuvinte la majuscule prin JS            |
| `groupByChecksum`         | Grupează cuvintele cu aceeași sumă de control       |
| `printDuplicateChecksums` | Afișează grupurile cu duplicate                     |

## Cum rulezi

```bash
gradle test
```

## Cerințe sistem

- JDK 21 (Temurin recomandat)
- Gradle 8.11+ (sau IntelliJ cu suport Gradle)

## Citește mai mult

Vezi [ASSIGNMENT.md](ASSIGNMENT.md) pentru cerințele complete.
