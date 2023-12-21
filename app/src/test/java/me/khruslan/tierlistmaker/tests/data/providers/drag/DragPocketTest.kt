package me.khruslan.tierlistmaker.tests.data.providers.drag

import io.mockk.*
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.data.providers.drag.DragPocket
import me.khruslan.tierlistmaker.data.providers.drag.DragPocketImpl
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class DragPocketTest {

    private lateinit var dragPocket: DragPocket

    private val dummyData = listOf(
        ImageDragData(
            image = StorageImage(
                id = "36177a66-bbd1-4593-9cfa-652dc7bb9a95",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/6990388288310.jpeg"
            ),
            itemPosition = 0,
            tierPosition = BACKLOG_TIER_POSITION
        ),
        ImageDragData(
            image = StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1549127750330.jpeg"
            ),
            itemPosition = 0,
            tierPosition = 4
        )
    )

    private fun verifyErrorWasLogged() = verify { Timber.e(any<Throwable>(), any()) }
    private fun verifyErrorWasNotLogged() = verify { Timber.Forest wasNot called }

    @Before
    fun init() {
        dragPocket = DragPocketImpl()
        mockkObject(Timber.Forest)
    }

    @After
    fun release() {
        unmockkObject(Timber.Forest)
    }

    @Test
    fun `Sets and pops shadow`() {
        val shadow = dummyData[0]
        dragPocket.shadow = shadow

        assertEquals(shadow, dragPocket.shadow)
        verifyErrorWasNotLogged()
        assertNull(dragPocket.shadow)
    }

    @Test
    fun `Logs error if shadow is unavailable`() {
        assertNull(dragPocket.shadow)
        verifyErrorWasLogged()
    }

    @Test
    fun `Sets and pops target`() {
        val target = dummyData[0]
        dragPocket.target = target

        assertEquals(target, dragPocket.target)
        assertNull(dragPocket.target)
        verifyErrorWasNotLogged()
    }

    @Test
    fun `Sets and pops cached target if target is already set`() {
        val target = dummyData[0]
        dragPocket.target = target
        dragPocket.target = null

        assertNull(dragPocket.target)
        assertEquals(target, dragPocket.cachedTarget)
        verifyErrorWasNotLogged()
        assertNull(dragPocket.cachedTarget)
    }

    @Test
    fun `Logs error if cached target is unavailable`() {
        assertNull(dragPocket.cachedTarget)
        verifyErrorWasLogged()
    }
}