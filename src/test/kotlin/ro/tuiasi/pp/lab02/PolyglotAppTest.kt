package ro.tuiasi.pp.lab02

import kotlin.test.*

class PolyglotAppTest {

    // --- computeChecksumJS ---

    @Test
    fun `computeChecksumJS calculeaza suma ASCII corecta pentru abc`() {
        // a=97, b=98, c=99 => 294
        assertEquals(294, computeChecksumJS("abc"))
    }

    @Test
    fun `computeChecksumJS calculeaza suma ASCII corecta pentru bca`() {
        // b=98, c=99, a=97 => 294
        assertEquals(294, computeChecksumJS("bca"))
    }

    @Test
    fun `computeChecksumJS sir gol returneaza 0`() {
        assertEquals(0, computeChecksumJS(""))
    }

    @Test
    fun `computeChecksumJS rezultat consistent pentru acelasi cuvant`() {
        assertEquals(computeChecksumJS("hello"), computeChecksumJS("hello"))
    }

    @Test
    fun `computeChecksumJS anagrame au aceeasi suma`() {
        assertEquals(computeChecksumJS("abc"), computeChecksumJS("cab"))
    }

    // --- upperCaseWordsJS ---

    @Test
    fun `upperCaseWordsJS converteste cuvintele la majuscule`() {
        assertEquals(listOf("ANA", "MERE"), upperCaseWordsJS(listOf("ana", "mere")))
    }

    @Test
    fun `upperCaseWordsJS lista goala returneaza lista goala`() {
        assertEquals(emptyList(), upperCaseWordsJS(emptyList()))
    }

    @Test
    fun `upperCaseWordsJS text deja majuscul ramane neschimbat`() {
        assertEquals(listOf("ABC"), upperCaseWordsJS(listOf("ABC")))
    }

    // --- groupByChecksum ---

    @Test
    fun `groupByChecksum grupeaza anagramele impreuna`() {
        val result = groupByChecksum(listOf("abc", "bca", "hello"))
        // "abc" si "bca" au acelasi checksum, "hello" e singur
        val group = result.values.find { it.contains("abc") }
        assertNotNull(group, "Grupul cu abc trebuie sa existe")
        assertTrue(group.contains("bca"), "bca trebuie sa fie in acelasi grup cu abc")
        assertFalse(result.values.any { it.contains("hello") }, "hello nu trebuie sa apara (e singur)")
    }

    @Test
    fun `groupByChecksum lista goala returneaza map gol`() {
        assertEquals(emptyMap(), groupByChecksum(emptyList()))
    }

    @Test
    fun `groupByChecksum fara duplicate returneaza map gol`() {
        // fiecare cuvant cu checksum unic
        val result = groupByChecksum(listOf("a", "bb", "ccc"))
        assertTrue(result.isEmpty(), "Nu trebuie sa existe grupuri cu un singur cuvant")
    }

    @Test
    fun `groupByChecksum toate cuvintele cu aceeasi suma formeaza un grup`() {
        // "abc", "bca", "cab" sunt anagrame => acelasi checksum
        val result = groupByChecksum(listOf("abc", "bca", "cab"))
        assertEquals(1, result.size, "Trebuie sa existe exact un grup")
        assertEquals(3, result.values.first().size, "Grupul trebuie sa contina 3 cuvinte")
    }
}
