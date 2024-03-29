package com.automendes.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var contacts : List<Contact> = arrayListOf(

        Contact("Tiago", "924130247"),
        Contact("Diogo", "934691855"),
        Contact("Rafael", "918527873")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewContacts = findViewById<ListView>(R.id.listViewContacts)
        val contactsAdapter = ContactsAdapter()
        listViewContacts.adapter = contactsAdapter
    }

    inner class ContactsAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return contacts.size
        }

        override fun getItem(position: Int): Any {
            return contacts[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

            val rootView = layoutInflater.inflate(R.layout.row_content, viewGroup, false)
            val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
            val textViewContact = rootView.findViewById<TextView>(R.id.textViewContact)

            // preencher views
            textViewName.text = contacts[position].name
            textViewContact.text = contacts[position].phone

            rootView.setOnClickListener {
                val intent = Intent(this@MainActivity, ContactDetailActivity:: class.java)
                intent.putExtra(ContactDetailActivity.CONTACT_NAME, contacts[position].name)
                intent.putExtra(ContactDetailActivity.CONTACT_PHONE, contacts[position].phone)
                startActivity(intent)
            }

            return rootView

        }

    }
}