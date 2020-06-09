package com.marlonheraclito.listacomprasv12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.marlonheraclito.listacomprasv12.adapter.AdapterWorld;
import com.marlonheraclito.listacomprasv12.controle.Dao;
import com.marlonheraclito.listacomprasv12.modelo.Compra;
import com.marlonheraclito.listacomprasv12.utils.Common;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Dao daoCompras;
    AdapterWorld adapterWorld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Lista de Compras");

        daoCompras = new Dao(this);
        List<Compra> listaCompras = daoCompras.select();
        Common.listaCompras = listaCompras;

        adapterWorld = new AdapterWorld(this, Common.listaCompras);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterWorld);

        registerForContextMenu(recyclerView);
        adapterWorld.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_novo:
                abrirNovo();
                return true;

            case R.id.menuTotal:
                Toast.makeText(this, calcularTotal() + " $00", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                deletar(item.getGroupId());
                break;
            case 2:
                Intent in = new Intent(this, Alterar.class);
                in.putExtra("id", item.getGroupId());
                startActivity(in);
                onResume();
                break;
        }

        return super.onContextItemSelected(item);
    }



    public void abrirNovo(){
        Intent in = new Intent(this, Adicionar.class);
        startActivity(in);
        onResume();
    }

    public void deletar(int position) {
        Compra c = Common.listaCompras.get(position);
        String id = String.valueOf(c.getId());
        Integer res = daoCompras.apagarCompra(id);
        if(res > 0){
            Toast.makeText(this, "Apagado", Toast.LENGTH_SHORT).show();
            adapterWorld.remover(position);
        }
    }

    public String calcularTotal(){
        float total = 0;
        for(int i = 0; i < Common.listaCompras.size(); i ++){
           total = total + Common.listaCompras.get(i).getTotal();
        }

        return String.valueOf(total);
    }
}