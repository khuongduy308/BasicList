package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbPerfectSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        etNumber = findViewById(R.id.etNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbPerfectSquare = findViewById(R.id.rbPerfectSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            // Xử lý khi nhấn nút Hiển thị
            val input = etNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập một số nguyên dương."
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = try {
                input.toInt().also {
                    if (it < 0) {
                        tvError.text = "Số phải là số nguyên dương."
                        tvError.visibility = View.VISIBLE
                        return@setOnClickListener
                    }
                }
            } catch (e: NumberFormatException) {
                tvError.text = "Dữ liệu không hợp lệ."
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            tvError.visibility = View.GONE // Ẩn thông báo lỗi nếu dữ liệu hợp lệ
            val result = ArrayList<Int>()

            // Kiểm tra lựa chọn và thêm số vào danh sách kết quả
            when {
                rbEven.isChecked -> {
                    for (i in 0..n step 2) {
                        result.add(i)
                    }
                }
                rbOdd.isChecked -> {
                    for (i in 1..n step 2) {
                        result.add(i)
                    }
                }
                rbPerfectSquare.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        result.add(i * i)
                        i++
                    }
                }
            }

            // Hiển thị kết quả trên ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }
}
