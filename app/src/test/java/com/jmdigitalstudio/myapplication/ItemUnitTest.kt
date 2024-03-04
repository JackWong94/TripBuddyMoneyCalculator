package com.jmdigitalstudio.myapplication

import com.jmdigitalstudio.myapplication.data.ItemManager
import com.jmdigitalstudio.myapplication.data.PersonManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ItemUnitTest {

    //Item Class Unit Test
    @Test
    fun `addOwingItem should add a new item`() {
        TestUtils.logTestCaseTitle()
        // Arrange
        val name = "testItem"
        val price = 20.0
        PersonManager.addPerson("testPerson1")
        PersonManager.addPerson("testPerson2")
        PersonManager.addPerson("testPerson3")
        val paidBy = PersonManager.getPersonByName("testPerson1")
        val oweBy = PersonManager.people

        // Act
        ItemManager.addOwingItem(name,price,paidBy,oweBy)

        // Assert
        assertEquals(1, ItemManager.items.size)
        assertEquals(name, ItemManager.items[0].name)
    }
    @Test
    fun `addOwingItem should add Person totalPaid totalOwed and change owedAndPaidDifference`() {
        TestUtils.logTestCaseTitle()
        // Arrange
        val name = "testItem"
        val price = 20.0
        PersonManager.addPerson("testPerson1")
        PersonManager.addPerson("testPerson2")
        PersonManager.addPerson("testPerson3")
        val paidBy = PersonManager.getPersonByName("testPerson1")
        val oweBy = PersonManager.people

        // Act
        ItemManager.addOwingItem(name,price,paidBy,oweBy)

        // Assert
        //Make sure that each person share the equal amount of the item price as owedTotal
        PersonManager.people.forEach {
            assertEquals(price/oweBy.size,it.owedTotal,0.00)
        }
        //Make sure that person that paid for this item has paidTotal increased by the price
        assertEquals(price,paidBy.paidTotal,0.00)
        //Make sure that person that paid for this item has owedAndPaidDifference calculated
        assertEquals(price - paidBy.owedTotal,paidBy.owedAndPaidDifference,0.00)
    }
    @Before
    fun setUp() {
        PersonManager.people.clear()
        ItemManager.items.clear()
    }

}