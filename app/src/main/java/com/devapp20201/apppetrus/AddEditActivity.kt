package com.devapp20201.apppetrus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devapp20201.apppetrus.model.ArksClass
import kotlinx.android.synthetic.main.activity_add_edit.*
import kotlin.random.Random
import com.devapp20201.apppetrus.util.RandomNumber

class AddEditActivity : AppCompatActivity() {

    private lateinit var arksclass: ArksClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        loadScreen()
        pressAddButton()
        pressCancelButton()
    }

    private fun loadScreen() {
        val mainClass =
            intent.getParcelableExtra<ArksClass>(MainActivity.MAIN_ACTIVITY_ARKSCLASS_EXTRA_EDIT)
        if (mainClass != null) {
            arksclass = mainClass
            loadEdit(arksclass)
        }

    }


    private fun loadEdit(aclass: ArksClass) {

        editClass.setText(aclass.name)
        editWeapon.setText(aclass.weapon)

    }

    private fun pressAddButton() {
        btnSave.setOnClickListener {
            intent.putExtra("ARKSCLASS", saveArksClass())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun pressCancelButton() {
        btnCancel.setOnClickListener() {
            setResult(RESULT_CANCELED, intent)
            finish()

        }
    }

    private fun saveArksClass(): ArksClass {

        var nome = editClass.text.toString()
        var weapon = editWeapon.text.toString()

        return if (this::arksclass.isInitialized) ArksClass(arksclass.id, nome, weapon)
        else ArksClass(randomId(), nome, weapon)


    }

    private fun randomId(): Int {
        return Random.nextInt(4, 100)
    }
}
