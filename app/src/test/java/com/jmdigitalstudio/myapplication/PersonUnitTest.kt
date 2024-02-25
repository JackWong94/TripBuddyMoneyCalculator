package com.jmdigitalstudio.myapplication

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PersonUnitTest {

    //Person Class Unit Test
    @Test
    fun `addPerson should add a new person`() {
        TestUtils.logTestCaseTitle()
        // Arrange
        val name = "TestPerson1"

        // Act
        PersonManager.addPerson(name)

        // Assert
        assertEquals(1, PersonManager.people.size)
        assertEquals(name, PersonManager.people[0].name)
    }

    @Test
    fun `getPersonByName should return the correct person`() {
        TestUtils.logTestCaseTitle()
        // Arrange
        val name = "TestPerson2"
        val person = Person(name, 0.0, 0.0)
        PersonManager.people.add(person)

        // Act
        val result = PersonManager.getPersonByName(name)

        // Assert
        assertEquals(person, result)
    }

    @Before
    fun setUp() {
        PersonManager.people.clear()
    }

}