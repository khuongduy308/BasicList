package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyễn Văn A", "20123456"),
            Student("Trần Thị B", "20123457"),
            Student("Lê Văn C", "20123458"),
            // Thêm các sinh viên khác
        )

        // Thiết lập RecyclerView và Adapter
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Lắng nghe thay đổi của ô tìm kiếm
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(query: String) {
        // Lọc danh sách sinh viên theo từ khóa
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            }
        } else {
            studentList
        }

        // Cập nhật danh sách trong Adapter
        studentAdapter.updateList(filteredList)
    }
}
