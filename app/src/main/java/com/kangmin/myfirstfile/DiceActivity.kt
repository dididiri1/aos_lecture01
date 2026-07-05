package com.kangmin.myfirstfile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kangmin.myfirstfile.databinding.ActivityDiceBinding
import kotlin.random.Random

class DiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiceBinding

    private val diceDrawables = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dice)

        binding.diceStartBtn.setOnClickListener {
            binding.dice1.setImageResource(diceDrawables[Random.nextInt(1,6)])
            binding.dice2.setImageResource(diceDrawables[Random.nextInt(1,6)])
        }
    }
}
