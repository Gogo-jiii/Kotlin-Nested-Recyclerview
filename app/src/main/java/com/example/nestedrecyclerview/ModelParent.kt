package com.example.nestedrecyclerview

import java.util.ArrayList

class ModelParent(
    var parent: String,
    var arrayListChild: ArrayList<ModelChild>,
    var isChildVisible: Boolean
) {

    override fun toString(): String {
        return "ModelParent{" +
                "parent='" + parent + '\'' +
                ", children=" + arrayListChild +
                ", isChildVisible=" + isChildVisible +
                '}'
    }
}