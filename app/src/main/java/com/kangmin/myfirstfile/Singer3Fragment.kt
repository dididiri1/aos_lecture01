package com.kangmin.myfirstfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Singer3Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_singer3, container, false)

        val singerAdapter = SingerAdapter(CourseData.courses().toMutableList())
        val singerRv = view.findViewById<RecyclerView>(R.id.singerRv)
        singerRv.layoutManager = LinearLayoutManager(context)
        singerRv.adapter = singerAdapter

        view.findViewById<ImageView>(R.id.imageBtn1).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3Fragment_to_singer1Fragment)
        }

        view.findViewById<ImageView>(R.id.imageBtn2).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3Fragment_to_singer2Fragment2)
        }

        return view
    }
}