package com.kangmin.myfirstfile

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

class DDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dday)

        var startDate: Calendar? = null
        var endDate: Calendar? = null
        val startBtn = findViewById<Button>(R.id.startBtn)
        val endBtn = findViewById<Button>(R.id.endBtn)

        startBtn.setOnClickListener {

            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)


            val dig = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(
                    view: DatePicker?,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {
                    startDate = GregorianCalendar(year, month, dayOfMonth)
                }

            },year, month, day)
            dig.show()
        }

        endBtn.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)


            val dig = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(
                    view: DatePicker?,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {
                    val start = startDate ?: return
                    endDate = GregorianCalendar(year, month, dayOfMonth)

                    val diffDays = TimeUnit.MILLISECONDS.toDays(
                        endDate!!.timeInMillis - start.timeInMillis
                    ) + 1

                    val countText = findViewById<TextView>(R.id.countText)
                    countText.text = diffDays.toString()
                }

            },year, month, day)
            dig.show()
        }

    }
}
