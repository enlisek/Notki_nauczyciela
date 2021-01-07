package com.example.notki_nauczyciela.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.viewmodel.GroupViewModel
import kotlinx.android.synthetic.main.fragment_group_add.*
import kotlinx.android.synthetic.main.fragment_group_add.buttonBackFromAddGroupToWelcome
import kotlinx.android.synthetic.main.fragment_group_add.editTextGroupName

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: GroupViewModel

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

        viewModel= ViewModelProvider(requireActivity()).get(GroupViewModel::class.java)


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAddGroup.setOnClickListener {

            if (editTextGroupName.text.toString() != "")
            {
                viewModel.addGroup(editTextGroupName.text.toString())
                editTextGroupName.setText("")
            }

        }
        buttonBackFromAddGroupToWelcome.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_addGroupFragment_to_welcomeFragment)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}