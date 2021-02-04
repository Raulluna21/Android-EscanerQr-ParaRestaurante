package com.luna.lector;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    public ArrayList<String> data;
    public ConstraintLayout DeleteItem;

    Adapter (Context context, ArrayList<String> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        String title = data.get(i);
        viewHolder.PedidoTexto.setText(title);
        viewHolder.carta.setTag(new Integer(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private  TextView Encabezado, PedidoTexto;
        public ConstraintLayout DeleteItem;
        public CardView carta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.carta = itemView.findViewById(R.id.carta);
            carta.setOnClickListener(this);
            this.DeleteItem = itemView.findViewById(R.id.DeleteItem);
            this.Encabezado = itemView.findViewById(R.id.Encabezado2);
            this.PedidoTexto = itemView.findViewById(R.id.PedidoTexto);
        }


        @Override
        public void onClick(View view) {
            Integer posi = Integer.parseInt(view.getTag().toString());
            //Toast.makeText(view.getContext(), Integer.toString(posi), Toast.LENGTH_LONG).show();
            Intent acte = new Intent(view.getContext(),DetalleItem.class);
            acte.putExtra("DetalleItem", data.get(getAdapterPosition()));
            acte.putExtra("posi", posi);
            view.getContext().startActivity(acte);
            //overridePendingTransition(R.anim.left_in,R.anim.left_out);
        }
    }

    public void removeItem(int pos){
        data.remove(pos);
        notifyItemRemoved(pos);
    }
/*
     public void restoreItem(int pos){
         data.add(pos);
         notifyItemRemoved(pos);
     }
*/
}