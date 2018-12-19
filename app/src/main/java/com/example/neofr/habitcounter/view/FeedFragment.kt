package com.example.neofr.habitcounter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.neofr.habitcounter.model.HabitCounter
import com.example.neofr.habitcounter.model.HabitCounterFactory
import com.example.neofr.habitcounter.R
import java.util.Arrays.asList


class FeedFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HabitAdapter
    private fun updateUi() {
        val counters = asList(HabitCounterFactory().createHabit(1),
            HabitCounterFactory().createHabit(2) )
        adapter = HabitAdapter(counters)
        recyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        recyclerView = view.findViewById(R.id.feed_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        updateUi()
        return view
    }

    inner class HabitHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.feed_item, parent, false)) {
        var  habitName: TextView = itemView.findViewById(R.id.feed_habit_name)
        var habitCounter: TextView = itemView.findViewById(R.id.habitCount)
        lateinit var habit: HabitCounter
        fun bind(habitCounter: HabitCounter){
            this.habit = habitCounter
            habitName.text = this.habit.habit.getName
            this.habitCounter.text = "1"
        }
    }

    inner class HabitAdapter(var habits: List<HabitCounter>) : RecyclerView.Adapter<HabitHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HabitHolder {
            val inflater: LayoutInflater = LayoutInflater.from(activity)
            return HabitHolder(inflater, parent)
        }

        override fun getItemCount(): Int = habits.size

        override fun onBindViewHolder(habitHolder: HabitHolder, idx: Int) {
            val habitCounter = habits.get(idx)
            habitHolder.bind(habitCounter)
        }
    }
}