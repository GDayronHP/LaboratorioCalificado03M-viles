package com.huayra.joseph.laboratoriocalificado3.data

import com.huayra.joseph.laboratoriocalificado3.model.TeacherResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher-b")
    suspend fun getTeachers(): TeacherResponse
}
