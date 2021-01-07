package com.example.notki_nauczyciela.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.repositories.GroupRepository
import java.nio.file.Files.delete

class ListAdapterGroup(var groups: LiveData<List<Group>>,val studentViewModel: StudentViewModel, val groupViewModel: GroupViewModel): RecyclerView.Adapter<ListAdapterGroup.GroupHolder>() {

    inner class GroupHolder(view: View): RecyclerView.ViewHolder(view){
        val textView1=view.findViewById<TextView>(R.id.textView1Group)
        val buttonSelectGroup=view.findViewById<Button>(R.id.buttonSelectGroup)
        val buttonDeleteGroup=view.findViewById<Button>(R.id.buttonDeleteGroup)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.one_row_group,parent,false) as View
        return GroupHolder(view)
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {

        holder.textView1.text=groups.value?.get(position)?.name

        holder.buttonSelectGroup.setOnClickListener {
            studentViewModel.setCurrentGroup(groups.value?.get(position)!!)
            studentViewModel.updateStudents()
            holder.itemView.findNavController().navigate(R.id.action_groupFragment_to_studentFragment)
        }

        holder.buttonDeleteGroup.setOnClickListener {
            groupViewModel.deleteGroup(groups.value?.get(position)!!)
            groupViewModel.updateGroups()
        }


    }

    override fun getItemCount(): Int {
        return groups.value?.size?:0
    }
}