package com.josivaniomarinho.contatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    var listener: ClickItemListener
) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()

    // Cria cada item vizual na tela
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    // Cada item que vai ser mostrado passa por esse método
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    // Diz para o recycler quatos itens tem na lista
    override fun getItemCount(): Int {
        return list.size
    }

    public fun updateList(list: List<Contact>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    // Trata cada item da lista
    class ContactAdapterViewHolder(
        itemView: View, var list: List<Contact>, var listener: ClickItemListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        private val  ivPhoto: ImageView = itemView.findViewById(R.id.iv_photograph)

        init {
            itemView.setOnClickListener {
                listener.clickitem(list[adapterPosition])
            }
        }

        fun bind(contact: Contact){
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}