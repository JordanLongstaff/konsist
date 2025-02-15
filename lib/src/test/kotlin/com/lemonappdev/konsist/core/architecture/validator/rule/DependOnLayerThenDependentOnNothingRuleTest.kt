package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class DependOnLayerThenDependentOnNothingRuleTest {
    private val sut = DependOnLayerThenDependentOnNothingRule()

    @Test
    fun `should throw exception when single layer violates depend on layer then dependent on nothing rule`() {
        // given
        val layer: Layer = mockk()
        val dependentLayer: Layer = mockk()

        every { layer.name } returns "layer name"
        every { dependentLayer.name } returns "dependent layer name"

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // when
        val func = {
            sut.validate(
                layerDependencies = layerDependencies,
            )
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Conflicting dependency configurations:\n" +
            "Layer layer name was previously set to depend on other layers, so it cannot be set as dependent on nothing."
    }

    @Test
    fun `should throw exception when multiple layers violate depend on layer then dependent on nothing rule`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val dependentLayer: Layer = mockk()

        every { layer1.name } returns "layer name 1"
        every { layer2.name } returns "layer name 2"
        every { dependentLayer.name } returns "dependent layer name"

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer1,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // when
        val func = {
            sut.validate(
                layerDependencies = layerDependencies,
            )
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Conflicting dependency configurations:\n" +
            "Layer layer name 1 was previously set to depend on other layers, so it cannot be set as dependent on nothing.\n" +
            "Layer layer name 2 was previously set to depend on other layers, so it cannot be set as dependent on nothing."
    }

    @Test
    fun `should not throw exception when no layer violates depend on layer then dependent on nothing rule`() {
        // given
        val layer: Layer = mockk()
        val dependentLayer: Layer = mockk()

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        sut.validate(
            layerDependencies = layerDependencies,
        )

        // then
        // No exception thrown
    }
}
