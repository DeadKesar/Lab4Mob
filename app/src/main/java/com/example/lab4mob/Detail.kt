package com.example.lab4mob

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lab4mob.databinding.DetailBinding



class Detail : BaseActivity() {
    private lateinit var binding: DetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar("Meal Details", showBackButton = true)

        val mealName = intent.getStringExtra("meal_name") ?: "Unknown Meal"
        val mealArea = intent.getStringExtra("meal_area") ?: "Unknown"
        val mealThumb = intent.getStringExtra("meal_thumb")
        val mealInstructions = intent.getStringExtra("meal_instructions") ?: "No instructions"
        val mealCategory = intent.getStringExtra("meal_category") ?: "Unknown"

        binding.detailName.text = mealName
        binding.detailStatus.text = getString(R.string.area2, mealArea)
        binding.detailInstructions.text = mealInstructions.replace("\r\n", "\n")
        mealThumb?.let { url ->
            Glide.with(this)
                .load(url.replace("\\/", "/"))
                .into(binding.detailImage)
        }
    }
}