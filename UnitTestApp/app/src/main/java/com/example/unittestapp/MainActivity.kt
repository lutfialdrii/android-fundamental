package com.example.unittestapp

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unittestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel = MainViewModel(CuboidModel())

        activityMainBinding.btnSave.setOnClickListener(this)
        activityMainBinding.btnCalculateSurfaceArea.setOnClickListener(this)
        activityMainBinding.btnCalculateCircumference.setOnClickListener(this)
        activityMainBinding.btnCalculateVolume.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val length = activityMainBinding.edtLength.text.toString().trim()
        val height = activityMainBinding.edtHeight.text.toString().trim()
        val width = activityMainBinding.edtWidth.text.toString().trim()

        when {
            TextUtils.isEmpty(length) -> {
                activityMainBinding.edtLength.error = "Field tidak boleh kosong!"
            }

            TextUtils.isEmpty(height) -> {
                activityMainBinding.edtHeight.error = "Field tidak boleh kosong!"
            }

            TextUtils.isEmpty(width) -> {
                activityMainBinding.edtWidth.error = "Field tidak boleh kosong!"
            }

            else -> {
                val valueLength = length.toDouble()
                val valueHeight = height.toDouble()
                val valueWidht = width.toDouble()

                when (v?.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(valueWidht, valueLength, valueHeight)
                        visible()
                    }

                    R.id.btn_calculate_circumference -> {
                        activityMainBinding.tvResult.text =
                            mainViewModel.getCircumreference().toString()
                        gone()
                    }

                    R.id.btn_calculate_surface_area -> {
                        activityMainBinding.tvResult.text =
                            mainViewModel.getSurfaceArea().toString()
                        gone()
                    }

                    R.id.btn_calculate_volume -> {
                        activityMainBinding.tvResult.text =
                            mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }

    }

    private fun gone() {
        activityMainBinding.btnCalculateVolume.visibility = View.GONE
        activityMainBinding.btnCalculateCircumference.visibility = View.GONE
        activityMainBinding.btnCalculateSurfaceArea.visibility = View.GONE
        activityMainBinding.btnSave.visibility = View.VISIBLE
    }

    private fun visible() {
        activityMainBinding.btnCalculateVolume.visibility = View.VISIBLE
        activityMainBinding.btnCalculateCircumference.visibility = View.VISIBLE
        activityMainBinding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        activityMainBinding.btnSave.visibility = View.GONE
    }
}