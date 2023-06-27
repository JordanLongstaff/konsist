package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.util.TagHelper
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoFunctionDeclarationImpl private constructor(private val ktFunction: KtFunction, parentDeclaration: KoBaseDeclaration?) :
    KoParametrizedDeclarationImpl(ktFunction, parentDeclaration),
    KoFunctionDeclaration {

    private val localDeclarations: Sequence<KoDeclaration> by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.asSequence()
            ?: emptySequence()

        psiChildren.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClassDeclarationImpl.getInstance(it, this)
            } else if (it is KtFunction) {
                getInstance(it, this)
            } else if (it is KtProperty) {
                KoPropertyDeclarationImpl.getInstance(it, this)
            } else {
                null
            }
        }
    }

    override val returnType: KoTypeDeclaration? by lazy {
        val typeReference = ktFunction
            .children
            .filterIsInstance<KtTypeReference>()

        val type = if (isExtension() && typeReference.size > 1) {
            // We choose last because when we have extension function the first one is receiver and the second one is return type.
            typeReference.last()
        } else if (!isExtension()) {
            typeReference.firstOrNull()
        } else {
            null
        }

        type?.let { KoTypeDeclarationImpl.getInstance(type, this) }
    }

    override val receiverType: KoTypeDeclaration? by lazy {
        val type = if (isExtension()) {
            ktFunction
                .children
                .filterIsInstance<KtTypeReference>()
                .first()
        } else {
            null
        }

        type?.let { KoTypeDeclarationImpl.getInstance(type, this) }
    }

    override fun hasOperatorModifier(): Boolean = hasModifiers(KoModifier.OPERATOR)

    override fun hasInlineModifier(): Boolean = hasModifiers(KoModifier.INLINE)

    override fun hasTailrecModifier(): Boolean = hasModifiers(KoModifier.TAILREC)

    override fun hasInfixModifier(): Boolean = hasModifiers(KoModifier.INFIX)

    override fun hasExternalModifier(): Boolean = hasModifiers(KoModifier.EXTERNAL)

    override fun hasSuspendModifier(): Boolean = hasModifiers(KoModifier.SUSPEND)

    override fun hasOpenModifier(): Boolean = hasModifiers(KoModifier.OPEN)

    override fun hasOverrideModifier(): Boolean = hasModifiers(KoModifier.OVERRIDE)

    override fun hasFinalModifier(): Boolean = hasModifiers(KoModifier.FINAL)

    override fun hasAbstractModifier(): Boolean = hasModifiers(KoModifier.ABSTRACT)

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier(): Boolean = hasModifiers(KoModifier.EXPECT)

    override fun isExtension(): Boolean = ktFunction.isExtensionDeclaration()

    override fun hasReceiverType(name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }

    override fun hasReturnType(): Boolean = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): Sequence<KoDeclaration> = localDeclarations

    override fun hasValidReturnTag(enabled: Boolean): Boolean = TagHelper.hasValidReturnTag(enabled, returnType?.name, kDoc)

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagHelper.hasValidParamTag(enabled, parameters, kDoc)

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktFunction: KtFunction, parentDeclaration: KoBaseDeclaration?): KoFunctionDeclaration =
            cache.getOrCreateInstance(ktFunction, parentDeclaration) {
                KoFunctionDeclarationImpl(ktFunction, parentDeclaration)
            }
    }
}
