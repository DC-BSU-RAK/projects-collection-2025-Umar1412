package com.example.coffemaster2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheckOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this checkout
        setContentView(R.layout.activity_checkout)

        // Get espresso order details passed from last activity through intent
        val espressoCount = intent.getIntExtra("espressoCount", 0)
        val espressoSugar = intent.getIntExtra("espressoSugar", 0)

        // Get cappuccino order details
        val cappuccinoCount = intent.getIntExtra("cappuccinoCount", 0)
        val cappuccinoSugar = intent.getIntExtra("cappuccinoSugar", 0)
        val cappuccinoMilk = intent.getIntExtra("cappuccinoMilk", 0)

        // Get latte order details
        val latteCount = intent.getIntExtra("latteCount", 0)
        val latteSugar = intent.getIntExtra("latteSugar", 0)
        val latteMilk = intent.getIntExtra("latteMilk", 0)

        // Find the textView in layout where the order details will be displayed
        val checkoutDetails = findViewById<TextView>(R.id.checkoutDetails)

        // Display Order Details with all information
        checkoutDetails.text = """
            Espresso: $espressoCount (Sugar: $espressoSugar)
            Cappuccino: $cappuccinoCount (Sugar: $cappuccinoSugar, Milk: $cappuccinoMilk)
            Latte: $latteCount (Sugar: $latteSugar, Milk: $latteMilk)
        """.trimIndent()
    }
}
