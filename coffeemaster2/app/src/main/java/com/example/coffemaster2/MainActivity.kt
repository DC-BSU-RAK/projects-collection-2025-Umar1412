package com.example.coffemaster2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Vars for coffee count
    private var espressoCount = 0
    private var cappuccinoCount = 0
    private var latteCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Espresso purchase buttons
        val espressoRemoveButton = findViewById<Button>(R.id.espressoRemoveButton)
        val espressoAddButton = findViewById<Button>(R.id.espressoAddButton)
        val espressoCountText = findViewById<TextView>(R.id.espressoCountText)
        val espressoSugarSelector = findViewById<NumberPicker>(R.id.espressoSugarSelector)

        // Sugar selector, max 5 and min and start value 0
        espressoSugarSelector.minValue = 0
        espressoSugarSelector.maxValue = 5
        espressoSugarSelector.value = 0

        // Button to decrease espresso count, but not below 0
        espressoRemoveButton.setOnClickListener {
            if (espressoCount > 0) espressoCount--
            espressoCountText.text = espressoCount.toString()
            setNumberSelectorEnabled(espressoSugarSelector, espressoCount > 0)
        }

        // Button click to increase espresso count
        espressoAddButton.setOnClickListener {
            espressoCount++
            espressoCountText.text = espressoCount.toString()
            setNumberSelectorEnabled(espressoSugarSelector, true)
        }

        // Initially disable sugar
        setNumberSelectorEnabled(espressoSugarSelector, false)

        // Cappuccino buttons
        val cappuccinoRemoveButton = findViewById<Button>(R.id.cappuccinoRemoveButton)
        val cappuccinoAddButton = findViewById<Button>(R.id.cappuccinoAddButton)
        val cappuccinoCountText = findViewById<TextView>(R.id.cappuccinoCountText)
        val cappuccinoSugarSelector = findViewById<NumberPicker>(R.id.cappuccinoSugarSelector)
        val cappuccinoMilkSelector = findViewById<NumberPicker>(R.id.cappuccinoMilkSelector)

        // Same logic as Espresso
        cappuccinoSugarSelector.minValue = 0
        cappuccinoSugarSelector.maxValue = 5
        cappuccinoSugarSelector.value = 0

        cappuccinoMilkSelector.minValue = 0
        cappuccinoMilkSelector.maxValue = 5
        cappuccinoMilkSelector.value = 0

        cappuccinoRemoveButton.setOnClickListener {
            if (cappuccinoCount > 0) cappuccinoCount--
            cappuccinoCountText.text = cappuccinoCount.toString()

            val enabled = cappuccinoCount > 0
            setNumberSelectorEnabled(cappuccinoSugarSelector, enabled)
            setNumberSelectorEnabled(cappuccinoMilkSelector, enabled)
        }

        cappuccinoAddButton.setOnClickListener {
            cappuccinoCount++
            cappuccinoCountText.text = cappuccinoCount.toString()

            setNumberSelectorEnabled(cappuccinoSugarSelector, true)
            setNumberSelectorEnabled(cappuccinoMilkSelector, true)
        }

        setNumberSelectorEnabled(cappuccinoSugarSelector, false)
        setNumberSelectorEnabled(cappuccinoMilkSelector, false)

        // Latte buttons
        val latteRemoveButton = findViewById<Button>(R.id.latteRemoveButton)
        val latteAddButton = findViewById<Button>(R.id.latteAddButton)
        val latteCountText = findViewById<TextView>(R.id.latteCountText)
        val latteSugarSelector = findViewById<NumberPicker>(R.id.latteSugarSelector)
        val latteMilkSelector = findViewById<NumberPicker>(R.id.latteMilkSelector)

        // Same logic as others above
        latteSugarSelector.minValue = 0
        latteSugarSelector.maxValue = 5
        latteSugarSelector.value = 0

        latteMilkSelector.minValue = 0
        latteMilkSelector.maxValue = 5
        latteMilkSelector.value = 0

        latteRemoveButton.setOnClickListener {
            if (latteCount > 0) latteCount--
            latteCountText.text = latteCount.toString()

            val enabled = latteCount > 0
            setNumberSelectorEnabled(latteSugarSelector, enabled)
            setNumberSelectorEnabled(latteMilkSelector, enabled)
        }

        latteAddButton.setOnClickListener {
            latteCount++
            latteCountText.text = latteCount.toString()

            setNumberSelectorEnabled(latteSugarSelector, true)
            setNumberSelectorEnabled(latteMilkSelector, true)
        }

        setNumberSelectorEnabled(latteSugarSelector, false)
        setNumberSelectorEnabled(latteMilkSelector, false)

        // Checkout button click listener
        val checkoutButton = findViewById<Button>(R.id.checkoutButton)
        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckOutActivity::class.java)

            // Pass coffee counts and sugar, milk values to checkout page
            intent.putExtra("espressoCount", espressoCount)
            intent.putExtra("espressoSugar", espressoSugarSelector.value)

            intent.putExtra("cappuccinoCount", cappuccinoCount)
            intent.putExtra("cappuccinoSugar", cappuccinoSugarSelector.value)
            intent.putExtra("cappuccinoMilk", cappuccinoMilkSelector.value)

            intent.putExtra("latteCount", latteCount)
            intent.putExtra("latteSugar", latteSugarSelector.value)
            intent.putExtra("latteMilk", latteMilkSelector.value)

            startActivity(intent)
        }
    }

    // Enable or disable milk, sugar interaction to make it user friendly
    fun setNumberSelectorEnabled(selector: NumberPicker, enabled: Boolean) {
        selector.isEnabled = true // Keep is there so it's shown to user
        selector.setOnTouchListener { _, _ -> !enabled }

        // Snack bar to show a pop up to give a help message to the user
        val mainLayout = findViewById<android.view.View>(android.R.id.content)
        com.google.android.material.snackbar.Snackbar.make(mainLayout, "Select which coffee you like and you can to use other options to choose sugar and milk to your liking!", com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE)
            .setDuration(5000) // 5 second pop up
            .show()

    }
}
