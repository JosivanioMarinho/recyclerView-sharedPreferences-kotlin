package com.josivaniomarinho.contatos

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.josivaniomarinho.contatos.DetailActivity.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemListener {

    private val rvListt: RecyclerView by lazy {
        findViewById(R.id.rv_list)
    }
    private val adapter: ContactAdapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportActionBar?.setTitle("Contatos")

        fatchListContacts()
        bind()
    }

    private fun fatchListContacts(){
        val list = arrayListOf(
            Contact(
                "Josivânio Marinho",
                "(81) 99292-8181",
                "png"
            ),
            Contact(
                "Jessiele Gouveia",
                "(81) 99292-8181",
                "png"
            ),
        )
        getInstanceSharedPreference().edit {
            putString("contacts", Gson().toJson(list))
        }
    }

    private fun getInstanceSharedPreference(): SharedPreferences{
        return getSharedPreferences("com.josivaniomarinho.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun bind(){
        rvListt.adapter = adapter
        rvListt.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact> {
        val list = getInstanceSharedPreference().getString("contacts", "[]")
        val turnsType = object: TypeToken<List<Contact>>(){}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun updateList(){
        adapter.updateList(getListContacts())
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu1 -> {
                showToast("Exibindo menu 1")
                return true
            }
            R.id.menu2  -> {
                showToast("Exibindo menu 2")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun clickitem(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}