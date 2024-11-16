package com.huayra.joseph.laboratoriocalificado3.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.huayra.joseph.laboratoriocalificado3.adapter.TeacherAdapter
import com.huayra.joseph.laboratoriocalificado3.data.ApiClient
import com.huayra.joseph.laboratoriocalificado3.databinding.ActivityEjercicio01Binding
import com.huayra.joseph.laboratoriocalificado3.model.Teacher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Ejercicio01 : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    private val teachers = mutableListOf<Teacher>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchTeachers()
    }

    private fun setupRecyclerView() {
        val adapter = TeacherAdapter(teachers, this::callTeacher, this::emailTeacher)
        binding.recyclerViewTeachers.adapter = adapter
        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getTeachers()
                withContext(Dispatchers.Main) {
                    teachers.clear()
                    teachers.addAll(response.teachers)
                    binding.recyclerViewTeachers.adapter?.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Log.e("Ejercicio01", "Error fetching teachers: ${e.message}")
            }
        }
    }

    private fun callTeacher(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${teacher.phone_number}")
        }
        startActivity(intent)
    }

    private fun emailTeacher(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${teacher.email}")
        }
        startActivity(intent)
    }
}
