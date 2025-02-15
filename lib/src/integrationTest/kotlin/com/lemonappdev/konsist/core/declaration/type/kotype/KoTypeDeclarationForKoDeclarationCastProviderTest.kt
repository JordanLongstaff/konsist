package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleCollection1
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass", "detekt.LongMethod")
class KoTypeDeclarationForKoDeclarationCastProviderTest {
    @Test
    fun `nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("nullable-class-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo true
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleType"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-class-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo true
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleType"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("nullable-object-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo true
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo true
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.asObjectDeclaration()?.name shouldBeEqualTo "SampleObject"
            it?.hasObjectDeclaration() shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-object-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo true
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo true
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.asObjectDeclaration()?.name shouldBeEqualTo "SampleObject"
            it?.hasObjectDeclaration() shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("nullable-interface-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo true
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo true
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.asInterfaceDeclaration()?.name shouldBeEqualTo "SampleInterface"
            it?.hasInterfaceDeclaration() shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-interface-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo true
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo true
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.asInterfaceDeclaration()?.name shouldBeEqualTo "SampleInterface"
            it?.hasInterfaceDeclaration() shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceOrObjectDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleClass" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-import-alias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo true
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.asImportAliasDeclaration()?.name shouldBeEqualTo "ImportAlias"
            it?.hasImportAliasDeclaration() shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-typealias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo true
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.asTypeAliasDeclaration()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasTypeAliasDeclaration() shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-import-alias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo true
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.asImportAliasDeclaration()?.name shouldBeEqualTo "ImportAlias"
            it?.hasImportAliasDeclaration() shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo true
            it?.isExternal shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeInstanceOf KoTypeParameterDeclaration::class
            it?.asTypeParameterDeclaration()?.name shouldBeEqualTo "TestType"
            it?.hasTypeParameterDeclaration() shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("not-nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo true
            it?.isExternal shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeInstanceOf KoTypeParameterDeclaration::class
            it?.asTypeParameterDeclaration()?.name shouldBeEqualTo "TestType"
            it?.hasTypeParameterDeclaration() shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-typealias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo true
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.asTypeAliasDeclaration()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasTypeAliasDeclaration() shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo true
            it?.isKotlinBasicType shouldBeEqualTo true
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinBasicTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(String::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(String::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(String::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.hasExternalDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo true
            it?.isKotlinBasicType shouldBeEqualTo true
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinBasicTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinBasicTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(String::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(String::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(String::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.hasExternalDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-generic-type`() {
        // given
        val sut =
            getSnippetFile("nullable-generic-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo true
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleCollection1"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-generic-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-generic-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo true
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo true
            it?.isClassOrInterface shouldBeEqualTo true
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleCollection1"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "SampleObject" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("nullable-external-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo true
            it?.asExternalDeclaration() shouldBeInstanceOf KoExternalDeclaration::class
            it?.asExternalDeclaration()?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasExternalDeclaration() shouldBeEqualTo true
            it?.hasExternalDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasExternalDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasExternalDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasExternalDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-external-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo true
            it?.asExternalDeclaration() shouldBeInstanceOf KoExternalDeclaration::class
            it?.asExternalDeclaration()?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasExternalDeclaration() shouldBeEqualTo true
            it?.hasExternalDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasExternalDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasExternalDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasExternalDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it shouldBeInstanceOf KoStarProjectionDeclaration::class
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkodeclarationcastprovider/",
            fileName,
        )
}
