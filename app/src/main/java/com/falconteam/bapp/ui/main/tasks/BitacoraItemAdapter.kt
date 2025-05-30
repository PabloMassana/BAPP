package com.falconteam.bapp.ui.main.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.databinding.ItemBitacoraBinding

class BitacoraItemAdapter(private val bitacoras: List<Bitacora>) : RecyclerView.Adapter<BitacoraItemAdapter.BitacoraViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitacoraViewHolder {
        val binding = ItemBitacoraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitacoraViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitacoraViewHolder, position: Int) {
        holder.bind(bitacoras[position])
    }

    override fun getItemCount(): Int = bitacoras.size

    class BitacoraViewHolder(private val binding: ItemBitacoraBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bitacora: Bitacora) {
            binding.textViewFecha.text = bitacora.fecha
            binding.textViewResumen.text = bitacora.aprendizaje
        }
    }
}