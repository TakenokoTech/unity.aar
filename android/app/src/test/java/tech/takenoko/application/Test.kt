package tech.takenoko.application

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox


@RunWith(PowerMockRunner::class)
@PrepareForTest(TestB::class, TestC::class)
class Test {

    private lateinit var testA: TestA
    private val mockedTestB = PowerMockito.mock(TestB::class.java)
    private val mockedTestC = PowerMockito.mock(TestC::class.java)

    @Before
    fun setUp() {
        testA = TestA()
    }

    @Test
    fun success_getStringB() {
        Mockito.`when`(mockedTestB.getString()).thenReturn("testBBB")
        Whitebox.setInternalState(testA, "testB", mockedTestB)
        Assert.assertEquals(testA.getStringB(), "testBBB")
    }

    @Test
    fun success_getStringC() = runBlocking {
        Mockito.`when`(mockedTestC.getString()).thenReturn("testCCC")
        Whitebox.setInternalState(testA, "testC", mockedTestC)
        Assert.assertEquals(testA.getStringC(), "testCCC")
    }
}

class TestA {
    private val testB = TestB()
    private val testC = TestC()

    fun getStringB(): String {
        return testB.getString()
    }

    suspend fun getStringC(): String {
        return testC.getString()
    }
}

class TestB {
    fun getString(): String {
        return "TestB"
    }
}

class TestC {
    suspend fun getString(): String {
        delay(100)
        return "TestC"
    }
}