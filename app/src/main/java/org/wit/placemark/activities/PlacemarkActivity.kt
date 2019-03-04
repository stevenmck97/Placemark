package org.wit.placemark.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_list.*
import kotlinx.android.synthetic.main.card_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    var placemark = PlacemarkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        app = application as MainApp

        if (intent.hasExtra("placemark_edit"))
        {
            placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
            placemarkTitle.setText(placemark.title)
            placemarkDescription.setText(placemark.description)
        }

        btnAdd.setOnClickListener() {
            placemark.title = placemarkTitle.text.toString()
            placemark.description = placemarkDescription.text.toString()
            if (placemark.title.isNotEmpty()) {
                app.placemarks.create(placemark.copy())
                info("Add Button Pressed. name: ${placemark.title}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please enter a title")
            }
        }

        //Add action bar and set title
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel-> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
