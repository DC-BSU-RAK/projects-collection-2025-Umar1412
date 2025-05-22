package com.example.superme

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // First thing user uses on screen

    private lateinit var nameInput: EditText
    private lateinit var traitSpinner: Spinner
    private lateinit var motivationSpinner: Spinner
    private lateinit var animalSpinner: Spinner
    private lateinit var generateButton: Button


    // Spinner choices

    private val traits = listOf("Bravery", "Intelligence", "Kindness", "Mischief", "Loyalty")
    private val motivations = listOf("Protect others", "Solve mysteries", "Spread joy", "Cause chaos", "Fame")
    private val animals = listOf("Eagle", "Cat", "Dog", "Owl", "Fox")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SuperMeDebug", "Launching MainActivity")
        setContentView(R.layout.activity_main)


        // elements connect with code
        try {
            nameInput = findViewById(R.id.nameInput)
            traitSpinner = findViewById(R.id.traitSpinner)
            motivationSpinner = findViewById(R.id.motivationSpinner)
            animalSpinner = findViewById(R.id.animalSpinner)
            generateButton = findViewById(R.id.generateButton)

            setupSpinner(traitSpinner, traits)
            setupSpinner(motivationSpinner, motivations)
            setupSpinner(animalSpinner, animals)

            generateButton.setOnClickListener {
                val name = nameInput.text.toString().trim()
             //get what user picks
                val trait = traitSpinner.selectedItem?.toString() ?: ""
                val motivation = motivationSpinner.selectedItem?.toString() ?: ""
                val animal = animalSpinner.selectedItem?.toString() ?: ""

                // Warning if name is left empty by user

                if (name.isEmpty()) {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
              // super power message
                val result = generateSuperpower(name, trait, motivation, animal)
                showResultDialog(result)
            }
        } catch (e: Exception) {
            Log.e("SuperMeDebug", "Error: ${e.message}")
            Toast.makeText(this, "ohh no! Something went wrong: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter
    }
// create message using power chosen by user
    private fun generateSuperpower(name: String, trait: String, motivation: String, animal: String): String {
        val traitPowers = mapOf(
            "Bravery" to "🦁 lead everyone through danger",
            "Intelligence" to "🧠 solve puzzles, BIG BRAIN",
            "Kindness" to "💖 heal emotional wounds",
            "Mischief" to "😈 unleash chaos perfectly",
            "Loyalty" to "🛡️ shield all your friends from harm"
        )

        val motivationPowers = mapOf(
            "Protect others" to "🛡️ generate force fields",
            "Solve mysteries" to "🔍 see hidden truths",
            "Spread joy" to "🎉 make everyone laugh uncontrollably",
            "Cause chaos" to "🌀 teleport things randomly",
            "Fame" to "🌟 shine like a spotlight in every room"
        )

        val animalPowers = mapOf(
            "Eagle" to "🦅 sharp vision that sees through walls",
            "Cat" to "🐱 nine lives and midnight acrobatics",
            "Dog" to "🐶 super sniffing and loyalty boosts",
            "Owl" to "🦉 night vision and wisdom aura",
            "Fox" to "🦊 clever illusions and sneaky vanish"
        )
     // all powers
        val finalPower =
            "You ${traitPowers[trait] ?: "have an unknown power"}, with the power to ${motivationPowers[motivation] ?: "do something mysterious"}, and a touch of ${animalPowers[animal] ?: "mystical essence"}."
     // add user name
        return "${name.replaceFirstChar { it.uppercaseChar() }}, your superpower is:\n$finalPower"
    }
     // result displayed and user being able to copy it
    private fun showResultDialog(powerText: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("🦸 Your Superpower!")
        dialogBuilder.setMessage(powerText)
         // copy button
        dialogBuilder.setPositiveButton("📋 Copy") { dialog, _ ->
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            if (clipboard != null) {
                clipboard.setPrimaryClip(ClipData.newPlainText("Superpower", powerText))
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Clipboard not available", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Done") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.show()
    }
}
