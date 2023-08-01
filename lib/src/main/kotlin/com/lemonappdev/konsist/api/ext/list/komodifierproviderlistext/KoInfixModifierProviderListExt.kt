package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInfixModifierProvider

/**
 * List containing declarations with `infix` modifier.
 *
 * @return A list containing declarations with the `infix` modifier.
 */
fun <T : KoInfixModifierProvider> List<T>.withInfixModifier(): List<T> = filter { it.hasInfixModifier }

/**
 * List containing declarations without `infix` modifier.
 *
 * @return A list containing declarations without the `infix` modifier.
 */
fun <T : KoInfixModifierProvider> List<T>.withoutInfixModifier(): List<T> = filterNot { it.hasInfixModifier }
