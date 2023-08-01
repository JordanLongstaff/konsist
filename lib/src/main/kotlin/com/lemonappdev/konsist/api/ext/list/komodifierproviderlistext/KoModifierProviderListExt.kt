@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider

/**
 * List containing declarations with any specified modifier.
 *
 * @return A list containing declarations with any modifier.
 */
fun <T : KoModifierProvider> List<T>.withModifiers(): List<T> = filter { it.hasModifiers() }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing declarations with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * List containing declarations with no modifier.
 *
 * @return A list containing declarations with no modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutModifiers(): List<T> = filterNot { it.hasModifiers() }

/**
 * List containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing declarations without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}
