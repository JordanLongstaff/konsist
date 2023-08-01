package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider

/**
 * List containing all declarations that have `abstract` modifier.
 *
 * @return A list containing declarations with the `abstract` modifier.
 */
fun <T : KoAbstractModifierProvider> List<T>.withAbstractModifier(): List<T> = filter { it.hasAbstractModifier }

/**
 * List containing all declarations that don't have `abstract` modifier.
 *
 * @return A list containing declarations without the `abstract` modifier.
 */
fun <T : KoAbstractModifierProvider> List<T>.withoutAbstractModifier(): List<T> = filterNot { it.hasAbstractModifier }
