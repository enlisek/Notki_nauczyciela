package com.example.notki_nauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.viewmodel.*
import kotlinx.android.synthetic.main.fragment_student.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//private lateinit var mySpinner: Spinner
//private lateinit var adapterSpinner: ListAdapterStudent

/**
 * A simple [Fragment] subclass.
 * Use the [StudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapterStudent: ListAdapterStudent
    private lateinit var adapterOutStudents: ListAdapterOutStudents
    private lateinit var viewManager1: RecyclerView.LayoutManager
    private lateinit var viewManager2: RecyclerView.LayoutManager
    private lateinit var listViewModel: StudentViewModel
    private lateinit var markViewModel: MarkViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        markViewModel= ViewModelProvider(requireActivity()).get(MarkViewModel::class.java)
        listViewModel= ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        viewManager1= LinearLayoutManager(requireContext())
        viewManager2= LinearLayoutManager(requireContext())


        listViewModel.updateStudents()

        adapterStudent= ListAdapterStudent(listViewModel.listOfStudentInGroup,markViewModel,listViewModel)
        adapterOutStudents=ListAdapterOutStudents(listViewModel.listOfStudentOutGroup,listViewModel)

        listViewModel.listOfStudentInGroup.observe(viewLifecycleOwner, Observer {
            listViewModel.updateStudents()
            adapterStudent.notifyDataSetChanged()})

        listViewModel.listOfStudentOutGroup.observe(viewLifecycleOwner, Observer {
            listViewModel.updateStudents()
            adapterOutStudents.notifyDataSetChanged() })

        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewStudent.apply {
            adapter=adapterStudent
            layoutManager=viewManager1
        }
        recyclerViewAddStudent.apply {
            adapter=adapterOutStudents
            layoutManager=viewManager2
        }

        textView4.setText(listViewModel.currentGroup.value!!.name)

        buttonStudentToGroup.setOnClickListener { view->view.findNavController().navigate(R.id.action_studentFragment_to_groupFragment) }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}