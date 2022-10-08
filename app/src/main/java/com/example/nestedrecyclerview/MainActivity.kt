package com.example.nestedrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var arrayListParent = ArrayList<ModelParent>()
    private var arrayListChild = ArrayList<ArrayList<ModelChild>>()
    private var arrayListChildA = ArrayList<ModelChild>()
    private var arrayListChildB = ArrayList<ModelChild>()
    private var arrayListChildC = ArrayList<ModelChild>()
    private var arrayParent = arrayOf("a", "b", "c")
    private var arrayChildA = arrayOf("a1", "a2", "a3")
    private var arrayChildB = arrayOf("b1", "b2", "b3")
    private var arrayChildC = arrayOf("c1", "c2", "c3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapterParent = AdapterParent(this, parentData)
        recyclerViewParent.layoutManager = LinearLayoutManager(this)
        recyclerViewParent.itemAnimator = DefaultItemAnimator()
        recyclerViewParent.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerViewParent.adapter = adapterParent
    }

    private val parentData: ArrayList<ModelParent>
        get() {
            arrayListChild = childData
            for (i in arrayParent.indices) {
                val parent = arrayParent[i]
                val child = arrayListChild[i]
                arrayListParent.add(ModelParent(parent, child, false))
            }
            return arrayListParent
        }

    private val childData: ArrayList<ArrayList<ModelChild>>
        get() {
            for (i in arrayChildA.indices) {
                arrayListChildA.add(ModelChild(arrayChildA[i]))
            }
            for (i in arrayChildB.indices) {
                arrayListChildB.add(ModelChild(arrayChildB[i]))
            }
            for (i in arrayChildC.indices) {
                arrayListChildC.add(ModelChild(arrayChildC[i]))
            }
            arrayListChild.add(arrayListChildA)
            arrayListChild.add(arrayListChildB)
            arrayListChild.add(arrayListChildC)
            return arrayListChild
        }
}