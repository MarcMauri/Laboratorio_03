package app.android.mmauri.laboratorio_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.android.mmauri.laboratorio_3.R;
import app.android.mmauri.laboratorio_3.adapters.MyAdapter;
import app.android.mmauri.laboratorio_3.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private List<Fruit> fruits;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar variables
        this.fruits = getAllFruits();

        this.myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        this.myRecyclerView.setHasFixedSize(true);
        this.myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.myLayoutManager = new LinearLayoutManager(this);
        this.myRecyclerView.setLayoutManager(this.myLayoutManager);

        /* Configurar adapter */
        this.myAdapter = new MyAdapter(this.fruits, R.layout.recyclerview_item, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.increaseQuant();
                myAdapter.notifyItemChanged(position);
            }
        });

        myRecyclerView.setAdapter(this.myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Que item se ha seleccionado? */
        switch (item.getItemId()) {
            case R.id.add_item:
                // Insertamos una fruta por defecto al final de la lista
                addFruit(this.fruits.size());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /* ------------ */
    /* CROW Methods */
    /* ------------ */

    private List<Fruit> getAllFruits() {
        return new ArrayList<Fruit>() {{
            add(new Fruit("Strawberry", "Strawberry description", R.mipmap.ic_strawberry, R.drawable.strawberry_bg, 0));
            add(new Fruit("Orange", "Orange description", R.mipmap.ic_orange, R.drawable.orange_bg, 0));
            add(new Fruit("Apple", "Apple description", R.mipmap.ic_apple, R.drawable.apple_bg, 0));
            add(new Fruit("Banana", "Banana description", R.mipmap.ic_banana, R.drawable.banana_bg, 0));
            add(new Fruit("Cherry", "Cherry description", R.mipmap.ic_cherry, R.drawable.cherry_bg, 0));
            add(new Fruit("Pear", "Pear description", R.mipmap.ic_pear, R.drawable.pear_bg, 0));
            add(new Fruit("Raspberry", "Raspberry description", R.mipmap.ic_raspberry, R.drawable.raspberry_bg, 0));
        }};
    }

    private void addFruit(int position) {
        this.fruits.add(position, new Fruit("Plum #" + (++this.counter), "Fruit added by the user", R.mipmap.ic_plum, R.drawable.plum_bg, 0));
        this.myAdapter.notifyItemInserted(position);
        this.myRecyclerView.scrollToPosition(position);
    }

}
