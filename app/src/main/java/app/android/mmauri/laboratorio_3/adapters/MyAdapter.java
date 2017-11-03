package app.android.mmauri.laboratorio_3.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.android.mmauri.laboratorio_3.R;
import app.android.mmauri.laboratorio_3.models.Fruit;

/**
 * Created by marc on 10/17/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<Fruit> fruits, int layout, Activity activity, MyAdapter.OnItemClickListener itemClickListener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.activity).inflate(this.layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.bind(this.fruits.get(position), this.itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.fruits.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        // Atributos del UI
        private ImageView imageViewBackground;
        private TextView name;
        private TextView description;
        private TextView quant;


        public ViewHolder(View itemView) {
            super(itemView);

            // Se vinculan los atributos del UI
            this.imageViewBackground = (ImageView) itemView.findViewById(R.id.imageViewBackground);
            this.name = (TextView) itemView.findViewById(R.id.textViewName);
            this.description = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.quant = (TextView) itemView.findViewById(R.id.textViewQuant);

            // Añadimos al view el listener para el context menu, en vez de hacerlo en
            // el activity mediante el método registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruit, final MyAdapter.OnItemClickListener listener) {

            /* Se setean los atributos del UI */
            this.name.setText(fruit.getName());
            this.description.setText(fruit.getDescription());
            this.quant.setText(Integer.toString(fruit.getQuant()));
            Picasso.with(activity).load(fruit.getBackgroundImg()).fit().into(this.imageViewBackground);

            /* Le damos color a la cantidad */
            if (fruit.getQuant() == Fruit.LIMIT_QUANTITY) {
                this.quant.setTypeface(null, Typeface.BOLD);
                this.quant.setTextColor(Color.RED);
            } else {
                this.quant.setTypeface(null, Typeface.NORMAL);
                //this.quant.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
                this.quant.setTextColor(Color.GRAY);
            }

            this.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            /* Sacamos informacion del item */
            Fruit currentFruit = fruits.get(getAdapterPosition());

            menu.setHeaderTitle(currentFruit.getName());
            menu.setHeaderIcon(currentFruit.getIconImg());

            /* Finalmente inflamos el menu con las modificaciones */
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.item_context_menu, menu);
            /*activity.getMenuInflater().inflate(R.menu.item_context_menu, menu);*/

            /*Por último, añadimos uno por uno, el listener onMenuItemClick para
            controlar las acciones en el contextMenu, anteriormente lo manejábamos
            con el método onContextItemSelected en el activity*/
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            /*No obtenemos nuestro objeto info
            porque la posición la podemos rescatar desde getAdapterPosition*/

            /*Que opcion se ha seleccionado?*/
            switch (item.getItemId()) {
                case R.id.delete_item:
                    fruits.remove(getAdapterPosition());
                    MyAdapter.this.notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_quant:
                    fruits.get(getAdapterPosition()).restoreQuant();
                    MyAdapter.this.notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}
