package com.example.mutlubievcase.view.service

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mutlubievcase.R
import com.example.mutlubievcase.adapters.BasicCardRecyclerViewAdapter
import com.example.mutlubievcase.interfaces.OnBasicCardClick
import com.example.mutlubievcase.models.BasicCardData
import com.example.mutlubievcase.utils.getDayFromDate
import com.example.mutlubievcase.utils.toFormattedString
import kotlinx.android.synthetic.main.dialog_key_note.*
import kotlinx.android.synthetic.main.fragment_order_selection.*
import java.util.*
import kotlin.collections.ArrayList


class OrderSelectionFragment : Fragment() {

    private lateinit var datePickerDialog: DatePickerDialog
    private var prices: ArrayList<BasicCardData> = arrayListOf()
    private lateinit var noteDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order_selection, container, false)
    }

    override fun onDestroyView() {
        (activity as ServiceOrderActivity).setSupportActionBar(null)
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTotalPrice()

        setUI()
        setRecyclerView()
        setDatePickerDialog()
        setToolbar()
        setSpinners(view.context)
        setListeners()
        initDialog(view)
    }

    private fun setUI(){
        day1DateText.text = Date().toFormattedString()
        day1DayText.text = Date().getDayFromDate()

        val calendar=Calendar.getInstance()
        calendar.add(Calendar.DATE,1)
        val tomorrowDate=calendar.time

        day2DateText.text = tomorrowDate.toFormattedString()
        day2DayText.text = tomorrowDate.getDayFromDate()
    }

    private fun setRecyclerView(){
        houseSizeRV.apply {
            val houseSizeList = resources.getStringArray(R.array.houseSize).toList().map {
                BasicCardData(it,when(it){
                    "1+0"-> 135
                    "1+1"-> 150
                    "2+1"-> 170
                    "3+1"-> 200
                    else -> 230
                })
            }
            val houseAdapter=BasicCardRecyclerViewAdapter(houseSizeList,object: OnBasicCardClick{
                override fun onBasicCardSelected(position: Int, data: BasicCardData,previousSelectedPosition: Int?,previousSelected: BasicCardData?) {
                    previousSelected?.let {
                        prices.remove(previousSelected)
                    }
                    prices.add(data)
                    getTotalPrice()
                }
            })
            layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
            adapter=houseAdapter
        }

        frequencyRV.apply {
            val frequencyList = resources.getStringArray(R.array.cleaningFrequency).toList().map { BasicCardData(it,0) }
            val frequencyAdapter=BasicCardRecyclerViewAdapter(frequencyList,object: OnBasicCardClick{
                override fun onBasicCardSelected(position: Int, data: BasicCardData,previousSelectedPosition: Int?,previousSelected: BasicCardData?) {}
            })
            layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
            adapter=frequencyAdapter
        }
    }

    private fun setDatePickerDialog(){
        val calendar=Calendar.getInstance()
        datePickerDialog=DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(year,month,dayOfMonth)
                val date = calendar.time

                day3DateText.text = date.toFormattedString()
                day3DayText.text = date.getDayFromDate()
                day3Spinner.performClick()
            },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel"
        ) { _, which ->
            if (which == DialogInterface.BUTTON_NEGATIVE){
                cardClickHandler(null)
            }
        }
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 2000
    }

    private fun setToolbar(){
        (activity as ServiceOrderActivity).apply {
            this.setSupportActionBar(orderToolbar)
            this.supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setListeners(){
        provinceSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val array=when(position){
                    0->R.array.districtAnkara
                    1->R.array.districtIstanbul
                    else->R.array.districtIzmir
                }
                val districtAdapter=ArrayAdapter.createFromResource(districtSpinner.context, array, android.R.layout.simple_spinner_item)
                districtSpinner.adapter = districtAdapter
            }
        }

        day1Card.setOnClickListener {
            day1Spinner.performClick()
            cardClickHandler(day1Card)
        }

        day2Card.setOnClickListener {
            day2Spinner.performClick()
            cardClickHandler(day2Card)
        }

        day3Card.setOnClickListener {
            cardClickHandler(day3Card)
            datePickerDialog.show()
        }

        extraYesButton.setOnClickListener {
            windowCleanCardView.visibility = View.GONE
            prices.add(BasicCardData("extra",225))
            getTotalPrice()
            extraButtonHandler(extraYesButton)
        }

        extraNoButton.setOnClickListener {
            windowCleanCardView.visibility = View.VISIBLE
            prices.remove(BasicCardData("extra",225))
            getTotalPrice()
            extraButtonHandler(extraNoButton)
        }

        cleaningStaffRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId==cleaningStaffYesRadio.id)
                prices.add(BasicCardData("cleaningStaff",20))
            else
                prices.remove(BasicCardData("cleaningStaff",20))
            getTotalPrice()
        }

        noteRadioButtonGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId==noteSecondRadio.id)
                noteDialog.show()
            else{
                noteBoxText.visibility = View.GONE
                noteBoxText.text = ""
            }
        }

        day1Spinner.setSelection(0,false)
        day2Spinner.setSelection(0,false)
        day3Spinner.setSelection(0,false)
        day1Spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day1TimeText.text = day1Spinner.selectedItem.toString()
                day1TimeText.visibility = View.VISIBLE
            }
        }

        day2Spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day2TimeText.text = day2Spinner.selectedItem.toString()
                day2TimeText.visibility = View.VISIBLE
            }
        }

        day3Spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day3TimeText.text = day3Spinner.selectedItem.toString()
                day3TimeText.visibility = View.VISIBLE
            }
        }
    }

    private fun setSpinners(context: Context){
        val adapter = ArrayAdapter.createFromResource(context, R.array.provinces, android.R.layout.simple_spinner_item)
        provinceSpinner.adapter = adapter

        val timeAdapter = ArrayAdapter.createFromResource(context, R.array.time, android.R.layout.simple_spinner_item)
        day1Spinner.adapter = timeAdapter
        day2Spinner.adapter = timeAdapter
        day3Spinner.adapter = timeAdapter
    }

    private fun initDialog(view: View){
        if (!::noteDialog.isInitialized){
            noteDialog = Dialog(view.context)
            noteDialog.apply {
                this.requestWindowFeature(Window.FEATURE_NO_TITLE)
                this.setContentView(R.layout.dialog_key_note)
                this.window?.setBackgroundDrawableResource(android.R.color.transparent)

                this.dialogOkButton.setOnClickListener {
                    this@OrderSelectionFragment.noteBoxText.text = this.dialogNoteText.text
                    this@OrderSelectionFragment.noteBoxText.visibility = View.VISIBLE
                    this.dialogNoteText.setText("")
                    this.dismiss()
                }

                this.dialogCancelButton.setOnClickListener {
                    this@OrderSelectionFragment.noteRadioButtonGroup.check(this@OrderSelectionFragment.noteFirstRadio.id)
                    this@OrderSelectionFragment.noteBoxText.visibility = View.GONE
                    this.dialogNoteText.setText("")
                    this.dismiss()
                }
            }
        }
    }

    private fun cardClickHandler(selectedCardView: CardView?){ // Handles the cards click state, e.g. if card clicked and if there is card that is previously selected should return to normal state.
        val list= listOf(day1Card,day2Card,day3Card)
        list.forEach {
            val textViewList=when(it){
                day1Card-> listOf(day1DayText,day1DateText,day1TimeText)
                day2Card-> listOf(day2DayText,day2DateText,day2TimeText)
                else->{
                    day3SelectLayout.visibility = if (it==selectedCardView) View.GONE else View.VISIBLE
                    day3Layout.visibility = if (it==selectedCardView) View.VISIBLE else View.GONE
                    listOf(day3DayText,day3DateText,day3TimeText)
                }
            }
            cardSelectedHandler(textViewList,it,it==selectedCardView)
        }
    }

    private fun cardSelectedHandler(textViewList: List<TextView>, cardView: CardView, isSelected: Boolean){
        if (!isSelected)
            textViewList[textViewList.size-1].visibility = View.GONE

        textViewList[textViewList.size-1].text = ""
        cardView.setCardBackgroundColor(resources.getColor(if (isSelected) android.R.color.holo_red_light else android.R.color.white,context?.theme))

        textViewList.forEach {
            it.setTextColor(resources.getColor(if (isSelected) android.R.color.white else android.R.color.black,context?.theme))
            if (cardView==day3Card) it.text = ""
        }
    }

    private fun extraButtonHandler(selectedButton: Button){
        val backgroundColor=android.R.color.holo_red_light
        val unSelectedBackgroundColor=android.R.color.white
        val unSelectedTextColor=android.R.color.black
        if (selectedButton==extraYesButton){
            extraYesButton.backgroundTintList = requireContext().resources.getColorStateList(backgroundColor,requireContext().theme)
            extraYesButton.setTextColor(requireContext().resources.getColor(unSelectedBackgroundColor,requireContext().theme))

            extraNoButton.backgroundTintList = requireContext().resources.getColorStateList(unSelectedBackgroundColor,requireContext().theme)
            extraNoButton.setTextColor(requireContext().resources.getColor(unSelectedTextColor,requireContext().theme))

            textView33.setTextColor(requireContext().resources.getColor(unSelectedBackgroundColor,requireContext().theme))
            textView33.setBackgroundColor(requireContext().resources.getColor(backgroundColor,requireContext().theme))
        }else{
            extraNoButton.backgroundTintList = requireContext().resources.getColorStateList(backgroundColor,extraNoButton.context.theme)
            extraNoButton.setTextColor(requireContext().resources.getColor(unSelectedBackgroundColor,extraNoButton.context.theme))

            extraYesButton.backgroundTintList = requireContext().resources.getColorStateList(unSelectedBackgroundColor,requireContext().theme)
            extraYesButton.setTextColor(requireContext().resources.getColor(unSelectedTextColor,requireContext().theme))

            textView33.setTextColor(requireContext().resources.getColor(backgroundColor,requireContext().theme))
            textView33.setBackgroundColor(requireContext().resources.getColor(R.color.light_gray,requireContext().theme))
        }
    }

    private fun getTotalPrice(){
        var sum=0
        prices.forEach {
            sum+=it.price
        }

        val priceString="${sum},00 TL"
        totalPriceText.text = priceString
    }
}