package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.present.resource.DrawableResources
import eu.ciambella.dailytest.present.resource.StringResources
import eu.ciambella.dailytest.present.common.mapper.ErrorPropertyMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ErrorPropertyMapperTest {

    private val drawableResources = mockk<DrawableResources> {
        every { icError } returns 0x456
    }
    private val stringResources = mockk<StringResources> {
        every { errorUnavailableTitle } returns "Error Occurred"
        every { errorUnavailableMessage } returns "Something went wrong. Please try again later."
        every { errorActionRetry } returns "Retry"
    }
    private val cut = ErrorPropertyMapper(drawableResources, stringResources)

    @Test
    fun `genericError should create ErrorProperty with retry action when onClick is provided`() {
        // Given
        val onClickMock: () -> Unit = {}

        // When
        val result = cut.genericError(onClickMock)

        // Then
        assertEquals("Error Occurred", result.title)
        assertEquals("Something went wrong. Please try again later.", result.message)
        assertEquals("Retry", result.action)
        assertEquals(onClickMock, result.onActionClick)
        assertEquals(0x456, (result.icon as AdaptiveImageProperty.Resource).imageRes)
    }

    @Test
    fun `genericError should create ErrorProperty without retry action when onClick is null`() {
        // When
        val result = cut.genericError(null)

        // Then
        assertEquals("Error Occurred", result.title)
        assertEquals("Something went wrong. Please try again later.", result.message)
        assertNull(result.action)
        assertNull(result.onActionClick)
        assertEquals(0x456, (result.icon as AdaptiveImageProperty.Resource).imageRes)
    }
}
