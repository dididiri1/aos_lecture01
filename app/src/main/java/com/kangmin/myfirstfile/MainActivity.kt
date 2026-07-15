package com.kangmin.myfirstfile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.util.Calendar
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val writeBtn = findViewById<ImageView>(R.id.writeBtn)
        var dateText = ""

        writeBtn.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.custem_dialog, null)
            val alertDialog = AlertDialog.Builder(this).setView(dialogView).setTitle("운동 메모 다이얼로그")

            val mAlertDialog = alertDialog.show()

            val dateSelectBtn = dialogView.findViewById<Button>(R.id.dateSelectButton)

            dateSelectBtn.setOnClickListener {
                val today = GregorianCalendar()
                val year: Int = today.get(Calendar.YEAR)
                val month: Int = today.get(Calendar.MONTH)
                val day: Int = today.get(Calendar.DATE)

                val dlg = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->

                    val date = "${selectedYear}. ${selectedMonth + 1}. ${selectedDay}"
                    dateText = date
                    dateSelectBtn.text = date

                }, year, month, day)

                dlg.show()
            }

            mAlertDialog.findViewById<Button>(R.id.saveBtn)?.setOnClickListener {
                val memoEditText = mAlertDialog.findViewById<EditText>(R.id.memoEditText)?.text.toString()

                val dateModel = DateModel(dateText, memoEditText)

                val database = Firebase.database
                val myRef = database.getReference("message")

                myRef.push().setValue(dateModel)
            }
            

        }

    }
}
