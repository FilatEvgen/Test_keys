package ru.kosproger.testkeys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kosproger.data.CourseApiClient
import ru.kosproger.data.CourseRepositoryImpl
import ru.kosproger.data.RetrofitClient
import ru.kosproger.data.AppDatabase
import ru.kosproger.data.CourseDatabase
import ru.kosproger.domain.models.CourseUseCase

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var courseUseCase: CourseUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCourses)
        recyclerView.layoutManager = LinearLayoutManager(context)
        courseAdapter = CourseAdapter(emptyList())
        recyclerView.adapter = courseAdapter


        val remoteDataStore: CourseApiClient = RetrofitClient.courseApiClient
        val localDataStore: CourseDatabase = AppDatabase.getDatabase(requireContext()).courseDao()
        val courseRepository = CourseRepositoryImpl(localDataStore, remoteDataStore)
        courseUseCase = CourseUseCase(courseRepository)


        loadCourses()


        view.findViewById<Button>(R.id.buttonHome).setOnClickListener {

        }

        view.findViewById<Button>(R.id.buttonFavorites).setOnClickListener {

        }

        view.findViewById<Button>(R.id.buttonAccount).setOnClickListener {

        }

        return view
    }

    private fun loadCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            val courses = courseUseCase.execute()
            withContext(Dispatchers.Main) {
                courseAdapter.updateCourses(courses)
            }
        }
    }
}