package ro.tuiasi.pp.lab02

import org.graalvm.polyglot.Context

/**
 * Codul JavaScript care definește funcția checksum.
 * Suma de control = suma codurilor ASCII ale caracterelor din cuvânt.
 * Exemplu: "abc" -> 97 + 98 + 99 = 294
 *
 * NOTĂ: dacă stub-ul din repo deja definește această constantă, șterge definiția de mai jos.
 */
const val CHECKSUM_JS = """
function checksum(word) {
    var sum = 0;
    for (var i = 0; i < word.length; i++) {
        sum += word.charCodeAt(i);
    }
    return sum;
}
"""

/**
 * Calculează suma de control a unui cuvânt apelând funcția JS `checksum(word)`.
 *
 * Exemplu:
 *   computeChecksumJS("abc") -> 294
 *   computeChecksumJS("bca") -> 294
 *   computeChecksumJS("")    -> 0
 */
fun computeChecksumJS(word: String): Int =
    Context.create("js").use { ctx ->
        ctx.eval("js", CHECKSUM_JS)
        // Apelăm funcția direct ca Value, evitând probleme cu quote-uri în eval string
        ctx.getBindings("js").getMember("checksum").execute(word).asInt()
    }

/**
 * Convertește fiecare cuvânt din listă la majuscule folosind JavaScript.
 *
 * Exemplu:
 *   upperCaseWordsJS(listOf("ana", "mere")) -> ["ANA", "MERE"]
 *   upperCaseWordsJS(emptyList())           -> []
 */
fun upperCaseWordsJS(words: List<String>): List<String> =
    Context.create("js").use { ctx ->
        words.map { word ->
            // Injectăm cuvântul ca binding JS ca să evităm orice problemă de escaping
            ctx.getBindings("js").putMember("_w", word)
            ctx.eval("js", "_w.toUpperCase()").asString()
        }
    }

/**
 * Grupează cuvintele după suma de control.
 * Returnează DOAR grupurile cu cel puțin 2 cuvinte (cele unice sunt excluse).
 *
 * Exemplu:
 *   groupByChecksum(listOf("abc", "bca", "hello", "aa"))
 *   -> {294: ["abc", "bca"]}
 */
fun groupByChecksum(words: List<String>): Map<Int, List<String>> {
    if (words.isEmpty()) return emptyMap()
    // Folosim un singur Context pentru toate cuvintele - mult mai eficient
    return Context.create("js").use { ctx ->
        ctx.eval("js", CHECKSUM_JS)
        val checksumFn = ctx.getBindings("js").getMember("checksum")
        words
            .groupBy { word -> checksumFn.execute(word).asInt() }
            .filter { (_, group) -> group.size >= 2 }
    }
}

/**
 * Afișează la consolă grupurile cu duplicate.
 * Format: "Checksum <N>: [cuvant1, cuvant2, ...]"
 *
 * Exemplu output:
 *   Checksum 294: [abc, bca]
 */
fun printDuplicateChecksums(words: List<String>) {
    val groups = groupByChecksum(words)
    groups.forEach { (checksum, group) ->
        println("Checksum $checksum: $group")
    }
}
