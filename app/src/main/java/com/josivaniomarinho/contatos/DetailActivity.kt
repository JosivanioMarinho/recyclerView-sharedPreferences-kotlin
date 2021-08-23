package com.josivaniomarinho.contatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getExtra()
        bindViews()
    }

    private fun getExtra(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    private fun bindViews(){
        findViewById<TextView>(R.id.tv_nome).text = contact?.name
        findViewById<TextView>(R.id.tv_telefone).text = contact?.phone
    }

    companion object{
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }

    // Método para finalizar a activity atual com o upnavigation
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}