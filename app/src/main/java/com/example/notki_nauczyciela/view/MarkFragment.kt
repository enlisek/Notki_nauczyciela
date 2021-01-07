package com.example.notki_nauczyciela.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.viewmodel.*
import kotlinx.android.synthetic.main.fragment_mark.*
import kotlinx.android.synthetic.main.fragment_student.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapterMark: ListAdapterMark
    private lateinit var viewManager1: RecyclerView.LayoutManager
    private lateinit var listViewModel: MarkViewModel

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

        listViewModel= ViewModelProvider(requireActivity()).get(MarkViewModel::class.java)
        viewManager1= LinearLayoutManager(requireContext())

        listViewModel.updateMarks()


        adapterMark= ListAdapterMark(listViewModel.listOfMarks,listViewModel)
        listViewModel.listOfMarks.observe(viewLifecycleOwner, Observer {
            adapterMark.notifyDataSetChanged()
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mark, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewMark.apply {
            adapter=adapterMark
            layoutManager=viewManager1
        }
        textView7.setText("${listViewModel.currentStudent.value!!.name} ${listViewModel.currentStudent.value!!.lastName}")
        val spinner: Spinner = view.findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.marks,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        buttonAddMark.setOnClickListener {
            if (spinner.selectedItem.toString() != "" && editTextTextPersonName3.text.toString() != "") {
                listViewModel.addMark(spinner.getSelectedItem().toString(),editTextTextPersonName3.text.toString())
                listViewModel.updateMarks()
                spinner.setSelection(0)
                editTextTextPersonName3.setText("")
            }

        }


        buttonMarkToStudent.setOnClickListener { view->view.findNavController().navigate(R.id.action_markFragment_to_studentFragment) }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MarkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}