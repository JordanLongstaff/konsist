package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider

/**
 * List containing all declarations that have `inner` modifier.
 *
 * @return A list containing declarations with the `inner` modifier.
 */
fun <T : KoInnerModifierProvider> List<T>.withInnerModifier(): List<T> = filter { it.hasInnerModifier }

/**
 * List containing all declarations that don't have `inner` modifier.
 *
 * @return A list containing declarations without the `inner` modifier.
 */
fun <T : KoInnerModifierProvider> List<T>.withoutInnerModifier(): List<T> = filterNot { it.hasInnerModifier }
