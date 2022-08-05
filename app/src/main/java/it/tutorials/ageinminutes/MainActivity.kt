package it.tutorials.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? =  null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        /**al premere del bottone "btnDatePicker" richiamo la funzione "clickdatePicker()"*/
        btnDatePicker.setOnClickListener{
            clickDatePiker()
        }

    }

    private fun clickDatePiker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = /**RICHIAMO FUNZIONE PREDEFINITA "DatePickerDialog", ASSEGNADOGLI VALORI*/
            DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDayOfMonth ->

                    //COMANDO DI TEST VISIVO
                    Toast.makeText(this,
                        "Year was $selectedYear, month was ${selectedMonth+1}, and day was $selectedDayOfMonth",
                        Toast.LENGTH_LONG).show()

                    /** CREO VARIABILE STRINGA A CUI ASSEGNO I VALORI SCELTI DALL'UTENTE*/
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                    /** ASSEGNO A "tvSelectedDate" IL VALORE DI "selectedDate" TRAMITE LA FUNZIONE .TEXT
                     * QUESTO È IL TESTO VISIBILE SULL'APP */
                    tvSelectedDate?.text = selectedDate


                    /** CREO OGGETTO SIMPLEDATAFORMAT ASSEGNANDOGLI UN FORMATO */
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    //CREO VARIABILE TIPO DATA
                    val theDate = sdf.parse(selectedDate)
                    theDate?.let {
                        /**restituisce l'equivalente in minuti della data selezionata */
                        val selectedDateInMinutes = theDate.time/ 60000

                        /**restituisce il tempo passato dal 1/1/1970 in millisecondi */
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                        currentDate?.let {
                            /**trasformo il tempo passato dal 1/1/1970 da millisecondi a minuti */
                            val currentDateInMInutes = currentDate.time / 60000

                            /** CALCOLO LA DIFFERENZA */
                            val differenceInMinute = currentDateInMInutes-selectedDateInMinutes

                            tvAgeInMinutes?.text = differenceInMinute.toString() }
                        }


                },
                year,
                month,
                day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

        }


}