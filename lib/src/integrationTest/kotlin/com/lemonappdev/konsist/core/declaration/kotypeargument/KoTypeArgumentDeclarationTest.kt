package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationTest {
    @Test
    fun `not-generic-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "String"
    }

    @Test
    fun `generic-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "Set<String>"
    }

    @Test
    fun `generic-complex-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "Map<List<String>, Int>"
    }

    @Test
    fun `star-projection-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "*"
    }

    @Test
    fun `out-projection-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "out String"
    }

    @Test
    fun `in-projection-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.toString() shouldBeEqualTo "in String"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forgeneral/", fileName)
}
