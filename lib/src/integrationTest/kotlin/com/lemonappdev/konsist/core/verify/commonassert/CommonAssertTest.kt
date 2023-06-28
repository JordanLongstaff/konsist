package com.lemonappdev.konsist.core.verify.commonassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class CommonAssertTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' has failed. Invalid declarations (1)") ?: throw e
        }
    }

    // ToDo("this test doesn't work!!! Fix it!)
    @Test
    fun `architecture-assert-test-method-name`() {
        // given
        val scope = Konsist.scopeFromProduction()
        val architecture = Konsist
            .architecture()
            .addDependencies { }

        // then
        try {
            architecture.assert(scope)
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'architecture-assert-test-method-name' has failed. Invalid declarations") ?: throw e
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/commonassert/snippet/", fileName)
}
