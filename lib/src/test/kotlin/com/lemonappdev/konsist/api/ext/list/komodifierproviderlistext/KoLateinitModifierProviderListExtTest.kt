package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext


import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoLateinitModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLateinitModifierProviderListExtTest {
    @Test
    fun `withLateinitModifier() returns declaration with lateinit modifier`() {
        // given
        val declaration1: KoLateinitModifierProvider = mockk {
            every { hasLateinitModifier } returns true
        }
        val declaration2: KoLateinitModifierProvider = mockk {
            every { hasLateinitModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLateinitModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLateinitModifier() returns declaration without lateinit modifier`() {
        // given
        val declaration1: KoLateinitModifierProvider = mockk {
            every { hasLateinitModifier } returns true
        }
        val declaration2: KoLateinitModifierProvider = mockk {
            every { hasLateinitModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLateinitModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
