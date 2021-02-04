package com.luna.lector;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int [] slide_images = {
            R.mipmap.uno_inicio,
            R.mipmap.dos_conexion,
            R.mipmap.tres_permisocamara,
            R.mipmap.cuatro_lista_principal,
            R.mipmap.cinco_eliminar_pedido,
            R.mipmap.seis_detalles_pedido,
            R.mipmap.siete_detalles_pedido_final,
            R.mipmap.ocho_editar_pedido,
            R.mipmap.nueve_cargando_pedido,
            R.mipmap.diez_codigopedido
    };

    public String [] slide_headings = {
            "1.Nombre del cliente",
            "2.Comprobar conexión",
            "3.Permiso para usar la camara",
            "4.Lista de perdidos escaneados",
            "5.Eliminar pedido",
            "6.Detalles del pedido",
            "7.Vista previa de la orden final",
            "8.Editar pedido",
            "9.Enviar pedido",
            "10.Código del pedido"
    };

    public String [] slide_decs = {
            "Para iniciar es necesario escribir tu nombre para que el chef sepa de quien es la orden que esta recibiendo, puedes comprobar el estado de tu conexión con el botón 'COMPROBAR RED', una vez que agregues tu nombre ya puedes iniciar a escanear utilizando el botón 'ESCANEAR DEL MENU'.",
            "Para hacer uso de esta aplicación es necesario que estes conectado a la red de nuestro establecimiento.",
            "Es necesario darle el permiso a la aplicación para hacer uso de la camara y poder escanear los códigos QR.",
            "Tu primer comida/bebida se agregará a la lista de pedidos principal, aquí tienes 2 botones 'ESCANEAR' para agregar más comidas o 'VISTA PREVIA' para ver la orden completa que recibirá el chef.",
            "Si deseas eliminar algún pedido escaneado basta con dezlinar hacía la izquierda, de esta forma desaparecerá de la lista.",
            "Al seleccionar algún pedido en la lista de pedidos principal podrás ver los detalles de esa comida/bebida.",
            "En la vista previa general de tu pedido.",
            "Con el botón 'EDITAR' si lo deseas, puedes editar los ingredientes que quieras cambiar, agregar o quitar, una vez que hayas realizado cambios es necesario que presiones el botón 'REGRESAR' para guardar tus cambios, para terminar presiona el botón 'ENVIAR', para que el chef reciba tu orden.",
            "El pedido se estará enviado al chef para ser procesado.",
            "Te recomendamos guardar tu código de pedido antes de presionar el botón 'FINALIZAR'"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideheading = view.findViewById(R.id.slide_heading);
        TextView slideDescription = view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideheading.setText(slide_headings[position]);
        slideDescription.setText(slide_decs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
