package ro.tuiasi.pp.lab02

import org.graalvm.polyglot.Context

/**
 * Cod JavaScript care calculează suma de control (checksum) a unui cuvânt.
 * Suma de control = suma valorilor ASCII ale caracterelor.
 *
 * Această constantă este folosită de [computeChecksumJS].
 * NU modifica această funcție JavaScript.
 */
val CHECKSUM_JS = """
    function checksum(word) {
        var sum = 0;
        for (var i = 0; i < word.length; i++) {
            sum += word.charCodeAt(i);
        }
        return sum;
    }
""".trimIndent()

/**
 * Calculează suma de control a unui cuvânt folosind motorul JavaScript din GraalVM.
 *
 * Pre-condiții: [word] nu este null; motorul GraalVM este disponibil.
 * Post-condiții: returnează un Int egal cu suma valorilor ASCII ale caracterelor din [word].
 *
 * Exemplu:
 *   computeChecksumJS("abc") == 97 + 98 + 99 == 294
 */
fun computeChecksumJS(word: String): Int {
    TODO("De implementat: folosește Context.create(\"js\") și evaluează CHECKSUM_JS + apelează checksum(word)")
}

/**
 * Convertește lista de cuvinte la MAJUSCULE folosind motorul JavaScript din GraalVM.
 *
 * Pre-condiții: [words] poate fi goală.
 * Post-condiții: returnează o nouă listă cu fiecare cuvânt transformat prin .toUpperCase() JS.
 *
 * Exemplu:
 *   upperCaseWordsJS(listOf("ana", "mere")) == listOf("ANA", "MERE")
 */
fun upperCaseWordsJS(words: List<String>): List<String> {
    TODO("De implementat: evaluează expresie JS care apelează word.toUpperCase() pentru fiecare cuvânt")
}

/**
 * Grupează cuvintele după suma lor de control și returnează grupurile cu cel puțin 2 cuvinte.
 *
 * Pre-condiții: [words] poate fi goală.
 * Post-condiții:
 *   - returnează un Map<Int, List<String>> unde cheia este suma de control
 *   - în map apar doar grupurile cu >= 2 cuvinte (cuvinte cu aceeași sumă de control)
 *   - cuvintele din fiecare grup sunt în ordinea în care apar în [words]
 *
 * Exemplu:
 *   "abc" și "bca" au ambele checksum 294 → apar în același grup
 *   "aa" are checksum 194 și e singurul → nu apare în rezultat
 */
fun groupByChecksum(words: List<String>): Map<Int, List<String>> {
    TODO("De implementat: folosește computeChecksumJS pentru fiecare cuvânt, apoi grupează și filtrează")
}

/**
 * Afișează la consolă grupurile de cuvinte cu aceeași sumă de control.
 * Format: "Checksum <N>: [cuvant1, cuvant2, ...]"
 *
 * Exemplu pentru groupByChecksum(listOf("abc","bca","hello","aa")):
 *   Checksum 294: [abc, bca]
 */
fun printDuplicateChecksums(words: List<String>) {
    TODO("De implementat: apelează groupByChecksum, iterează și printează fiecare grup")
}
