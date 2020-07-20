package com.simplemobiletools.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.simplemobiletools.calculator.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@TestAnnotation(["com.simplemobiletools.calculator.helpers.CalculatorImpl", "com.simplemobiletools.calculator.activities.MainActivity"])
class MainActivityTest {
    @Rule
    @JvmField
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addDigits() {
        press(R.id.btn_1)
        press(R.id.btn_2)
        press(R.id.btn_3)
        press(R.id.btn_4)
        press(R.id.btn_5)
        press(R.id.btn_6)
        press(R.id.btn_7)
        press(R.id.btn_8)
        press(R.id.btn_9)
        press(R.id.btn_0)
        checkResult("1,234,567,890")
    }

    @Test
    fun removeLeadingZero() {
        press(R.id.btn_0)
        press(R.id.btn_5)
        checkResult("5")
    }

    @Test
    fun clearComplexTest() {
        press(R.id.btn_1)
        press(R.id.btn_plus)
        press(R.id.btn_1)
        press(R.id.btn_decimal)
        press(R.id.btn_5)
        press(R.id.btn_5)
        press(R.id.btn_clear)
        press(R.id.btn_1)
        press(R.id.btn_equals)
        checkResult("2.51")
        checkFormula("1+1.51")
    }

    @Test
    fun additionTest() {
        press(R.id.btn_0)
        press(R.id.btn_minus)
        press(R.id.btn_2)
        press(R.id.btn_decimal)
        press(R.id.btn_5)
        press(R.id.btn_plus)
        press(R.id.btn_6)
        press(R.id.btn_equals)
        checkResult("3.5")
        checkFormula("-2.5+6")
        press(R.id.btn_equals)
        checkResult("9.5")
        checkFormula("3.5+6")
    }

    @Test
    fun subtractionTest() {
        press(R.id.btn_7)
        press(R.id.btn_decimal)
        press(R.id.btn_8)
        press(R.id.btn_minus)
        press(R.id.btn_3)
        press(R.id.btn_equals)
        checkResult("4.8")
        checkFormula("7.8-3")
    }

    @Test
    fun multiplyTest() {
        press(R.id.btn_2)
        press(R.id.btn_multiply)
        press(R.id.btn_4)
        press(R.id.btn_equals)
        checkResult("8")
        checkFormula("2*4")
    }

    @Test
    fun divisionTest() {
        press(R.id.btn_1)
        press(R.id.btn_0)
        press(R.id.btn_divide)
        press(R.id.btn_4)
        press(R.id.btn_equals)
        checkResult("2.5")
        checkFormula("10/4")
    }

    @Test
    fun divisionByZeroTest() {
        press(R.id.btn_8)
        press(R.id.btn_divide)
        press(R.id.btn_0)
        press(R.id.btn_equals)
        checkResult("0")
        checkFormula("8/0")
    }

    @Test
    fun percentTest() {
        press(R.id.btn_1)
        press(R.id.btn_0)
        press(R.id.btn_percent)
        press(R.id.btn_2)
        press(R.id.btn_0)
        press(R.id.btn_equals)
        checkResult("2")
        checkFormula("10%20")
    }

    @Test
    fun percentTestInsideOtherOperation() {
        press(R.id.btn_8)
        press(R.id.btn_0)
        press(R.id.btn_minus)
        press(R.id.btn_1)
        press(R.id.btn_0)
        press(R.id.btn_percent)
        press(R.id.btn_equals)
        checkResult("72")
        checkFormula("80-10%")
    }

    @Test
    fun powerTest() {
        press(R.id.btn_2)
        press(R.id.btn_power)
        press(R.id.btn_3)
        press(R.id.btn_equals)
        checkResult("8")
        checkFormula("2^3")
    }

    @Test
    fun rootTest() {
        press(R.id.btn_9)
        press(R.id.btn_root)
        checkResult("3")
        checkFormula("√9")
    }

    @Test
    fun clearTest() {
        press(R.id.btn_2)
        press(R.id.btn_5)
        press(R.id.btn_decimal)
        press(R.id.btn_7)
        press(R.id.btn_clear)
        checkResult("25")
        press(R.id.btn_clear)
        checkResult("2")
        press(R.id.btn_clear)
        checkResult("0")
        press(R.id.btn_clear)
        checkResult("0")
    }

    @Test
    fun clearLongTest() {
        press(R.id.btn_2)
        press(R.id.btn_plus)
        press(R.id.btn_5)
        press(R.id.btn_equals)
        longPress(R.id.btn_clear)
        press(R.id.btn_plus)
        press(R.id.btn_2)
        press(R.id.btn_equals)
        checkResult("2")
        checkFormula("0+2")
    }

    @Test
    fun complexTest() {
        press(R.id.btn_2)
        press(R.id.btn_plus)
        press(R.id.btn_5)
        press(R.id.btn_minus)
        checkResult("7")
        checkFormula("2+5")
        press(R.id.btn_3)
        press(R.id.btn_multiply)
        checkResult("4")
        checkFormula("7-3")
        press(R.id.btn_1)
        press(R.id.btn_0)
        press(R.id.btn_divide)
        checkResult("40")
        checkFormula("4*10")
        press(R.id.btn_5)
        press(R.id.btn_power)
        checkResult("8")
        checkFormula("40/5")
        press(R.id.btn_2)
        press(R.id.btn_equals)
        checkResult("64")
        checkFormula("8^2")
        press(R.id.btn_root)
        checkResult("8")
        checkFormula("√64")
        press(R.id.btn_clear)
        checkResult("0")
    }

    private fun press(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }

    private fun longPress(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.longClick())
    }

    private fun checkResult(desired: String) {
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText(desired)))
    }

    private fun checkFormula(desired: String) {
        Espresso.onView(ViewMatchers.withId(R.id.formula)).check(ViewAssertions.matches(ViewMatchers.withText(desired)))
    }
}
