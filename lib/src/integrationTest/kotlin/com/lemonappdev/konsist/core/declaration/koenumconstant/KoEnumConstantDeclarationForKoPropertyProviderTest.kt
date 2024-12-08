package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoPropertyProviderTest {
    @Test
    fun `enum-constant-contains-no-property`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-property")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            properties shouldBeEqualTo emptyList()
            numProperties shouldBeEqualTo 0
            countProperties { it.name == "sampleProperty" } shouldBeEqualTo 0
            hasProperties() shouldBeEqualTo false
            hasPropertyWithName(emptyList()) shouldBeEqualTo false
            hasPropertyWithName(emptySet()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo false
            hasPropertyWithName("sampleProperty") shouldBeEqualTo false
            hasPropertyWithName(listOf("sampleProperty")) shouldBeEqualTo false
            hasPropertyWithName(setOf("sampleProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty" } shouldBeEqualTo false
            hasAllProperties { it.name == "sampleProperty" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-contains-property`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-property")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numProperties shouldBeEqualTo 2
            countProperties { it.name == "sampleProperty1" } shouldBeEqualTo 1
            hasProperties() shouldBeEqualTo true
            hasPropertyWithName(emptyList()) shouldBeEqualTo true
            hasPropertyWithName(emptySet()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo true
            hasPropertyWithName("sampleProperty1") shouldBeEqualTo true
            hasPropertyWithName("otherProperty") shouldBeEqualTo false
            hasPropertyWithName("sampleProperty1", "otherProperty") shouldBeEqualTo true
            hasPropertyWithName(listOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertyWithName(listOf("otherProperty")) shouldBeEqualTo false
            hasPropertyWithName(listOf("sampleProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertyWithName(setOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertyWithName(setOf("otherProperty")) shouldBeEqualTo false
            hasPropertyWithName(setOf("sampleProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "otherProperty") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("sampleProperty1", "otherProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("sampleProperty1", "otherProperty")) shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty1" } shouldBeEqualTo true
            hasProperty { it.name == "otherProperty" } shouldBeEqualTo false
            hasAllProperties { it.name.endsWith("2") || it.name == "sampleProperty1" } shouldBeEqualTo true
            hasAllProperties { it.name.endsWith("2") } shouldBeEqualTo false
            properties
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleProperty1", "sampleProperty2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koenumconstant/snippet/forkopropertyprovider/", fileName)
}
