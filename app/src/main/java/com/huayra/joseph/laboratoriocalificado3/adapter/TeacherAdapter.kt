package com.huayra.joseph.laboratoriocalificado3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huayra.joseph.laboratoriocalificado3.databinding.ItemTeacherBinding
import com.huayra.joseph.laboratoriocalificado3.model.Teacher

class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val callTeacher: (Teacher) -> Unit,
    private val emailTeacher: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher, callTeacher, emailTeacher)
    }

    override fun getItemCount(): Int = teachers.size

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher, callTeacher: (Teacher) -> Unit, emailTeacher: (Teacher) -> Unit) {
            binding.tvName.text = "${teacher.name} ${teacher.last_name}"
            binding.tvPhone.text = teacher.phone_number
            Glide.with(binding.ivImage.context)
                .load(teacher.image_url)
                .into(binding.ivImage)

            binding.root.setOnClickListener {
                callTeacher(teacher)
            }

            binding.root.setOnLongClickListener {
                emailTeacher(teacher)
                true
            }
        }
    }
}
