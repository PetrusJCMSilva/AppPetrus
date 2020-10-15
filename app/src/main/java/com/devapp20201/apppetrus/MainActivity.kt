package com.devapp20201.apppetrus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.devapp20201.apppetrus.adapter.ArksClassAdapter
import com.devapp20201.apppetrus.model.ArksClass
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val MAIN_ACTIVITY_ARKSCLASS_EXTRA_EDIT = "ARKSCLASS_EXTRA_EDIT"
        const val EDIT_ACTIVITY = 0
        const val ADD_ACTIVITY = 1
        const val SAVED_INSTANCE_LIST = "saved_instance_class_list"
    }


    private val listArksClass: MutableList<ArksClass> = mutableListOf(
        ArksClass(1,"Hunter","Sword"),
        ArksClass(2,"Ranger","Assault Rifle"),
        ArksClass(3,"Force","Rod")
    )

    private val mArksClassAdapter = ArksClassAdapter(this,listArksClass,this::onClassListener)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SAVED_INSTANCE_LIST, java.util.ArrayList<ArksClass>(listArksClass))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val lista = savedInstanceState.getParcelableArrayList<ArksClass>(SAVED_INSTANCE_LIST)!!
        listArksClass.addAll(lista)
       mArksClassAdapter.notifyDataSetChanged()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        onPressAddButton()

    }

    private fun onClassListener(arksClass: ArksClass) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra(MAIN_ACTIVITY_ARKSCLASS_EXTRA_EDIT,arksClass)
        startActivityForResult(intent, EDIT_ACTIVITY)
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = mArksClassAdapter
        val layoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = layoutManager

    }

    private fun onPressAddButton(){
        addButton.setOnClickListener{
            val intent = Intent(this, AddEditActivity::class.java)
            startActivityForResult(intent, ADD_ACTIVITY)
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val arksclass = data?.getParcelableExtra<ArksClass>("ARKSCLASS")

        if (requestCode == ADD_ACTIVITY && resultCode == RESULT_OK) {
            if (arksclass != null) {
                listArksClass.add(arksclass)
                mArksClassAdapter.notifyDataSetChanged()
            }
        } else if (requestCode == EDIT_ACTIVITY && resultCode == RESULT_OK)
            if (arksclass != null) {
            val index: Int = listArksClass.indexOf(arksclass)
            listArksClass[index] = arksclass
            mArksClassAdapter.notifyDataSetChanged()
        }
    }


}


