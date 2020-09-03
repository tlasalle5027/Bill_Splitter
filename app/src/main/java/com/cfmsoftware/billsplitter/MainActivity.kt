package com.cfmsoftware.billsplitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import com.facebook.ads.AudienceNetworkAds

import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    //Initialize necessary variables
    private var check:Double = 0.00
    var tip:Double = 0.00
    var people:Double = 1.00
    private var total:Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AudienceNetworkAds.initialize(this)

        val tipPcnt = resources.getStringArray(R.array.Tip_Pct)
        val numPpl = resources.getStringArray(R.array.Num_People)

        //Initialize the spinners with the data from the string arrays
        val tipSpinner = findViewById<Spinner>(R.id.tipSpinner)
        if(tipSpinner != null) {
            val tipAdapter =
                ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tipPcnt)
            tipSpinner.adapter = tipAdapter
            tipSpinner.setSelection(0)

            tipSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    //Depending on the position of the spinner
                    //Set the tip percentage
                    when (position) {
                        0 -> tip = .10
                        1 -> tip = .12
                        2 -> tip = .15
                        3 -> tip = .20
                        4 -> tip = .25
                        5 -> tip = .30
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


        val pplSpinner = findViewById<Spinner>(R.id.peopleSpinner)
        if (pplSpinner != null){
            val pplAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, numPpl)
            pplSpinner.adapter = pplAdapter
            pplSpinner.setSelection(0)

            pplSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> people = 2.0
                        1 -> people = 3.0
                        2 -> people = 4.0
                        3 -> people = 5.0
                        4 -> people = 6.0
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    /**
     * Calculate the bill for each person splitting the meal,
     * to determine how much each person owes
     */
    fun calculateBill() {
        val bill = findViewById<EditText>(R.id.billEditText)
        val tot = findViewById<TextView>(R.id.amountView)

        check = if(bill.text.isNotBlank()){
            bill.text.toString().toDouble()
        } else{
            0.0
        }

        total = (check + (check * tip))/people
        val split = BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN)
        tot.text = "$$split"
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}