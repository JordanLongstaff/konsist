package com.lemonappdev.konsist.declaration.koparameter

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.constructors
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterForKoSourceSetProviderTest {
    @Test
    fun `source set name for parameter in constructor is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in constructor is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetProjectDirectory/sample/AppClassTest.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo INTEGRATION_TEST
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetProjectDirectory/sample/AppClassTest.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo INTEGRATION_TEST
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in constructor is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in constructor is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetProjectDirectory/sample/LibClassTest.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo TEST
            resideInSourceSet(TEST) shouldBeEqualTo true
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetProjectDirectory/sample/LibClassTest.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo TEST
            resideInSourceSet(TEST) shouldBeEqualTo true
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in constructor is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in constructor is 'main' in root module with double src package`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/src/RootSrcClass.kt".toOsSeparator())
            .classes()
            .constructors
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name for parameter in function invocation is 'main' in root module with double src package`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/src/RootSrcClass.kt".toOsSeparator())
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    companion object {
        private const val MAIN = "main"
        private const val TEST = "test"
        private const val INTEGRATION_TEST = "integrationTest"
    }
}
