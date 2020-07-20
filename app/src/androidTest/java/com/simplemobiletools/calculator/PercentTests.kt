package com.simplemobiletools.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.simplemobiletools.calculator.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)

@TestAnnotation(["com.simplemobiletools.calculator.operation.FactorialOperation",
    "com.simplemobiletools.calculator.operation.DivideOperation"])
class PercentTests {
    @Rule
    @JvmField
    var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun percentTest_asTheFirstOperation() {
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
    fun percentTestInsideOtherOperation1() {
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



    private fun press(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }

    private fun longPress(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.longClick())
    }

    private fun checkResult(desired: String) {
        Espresso.onView(withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText(desired)))
    }

    private fun checkFormula(desired: String) {
        Espresso.onView(withId(R.id.formula)).check(ViewAssertions.matches(ViewMatchers.withText(desired)))
    }
}
