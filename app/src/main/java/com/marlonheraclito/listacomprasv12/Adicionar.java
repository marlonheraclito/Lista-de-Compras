package com.marlonheraclito.listacomprasv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marlonheraclito.listacomprasv12.controle.Dao;
import com.marlonheraclito.listacomprasv12.modelo.Compra;

public class Adicionar extends AppCompatActivity {

    Button btnGuardar;
    EditText edtNovaCompra, edtQuant, edtPreco;

    Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        setTitle("Adicionar Compra");

        dao = new Dao(this);

        btnGuardar = findViewById(R.id.btnGuardar);
        edtNovaCompra = findViewById(R.id.edtNomeCompra);
        edtQuant = findViewById(R.id.edtQuant);
        edtPreco = findViewById(R.id.edtPreco);

      adicionarCompra();
    }

    public void adicionarCompra(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNovaCompra.getText().toString().equals("") && !edtQuant.getText().toString().equals("") && !edtPreco.getText().toString()
                        .equals("")) {
                    Compra c = new Compra(edtNovaCompra.getText().toString(), Integer.parseInt(edtQuant.getText().toString()),Float.parseFloat(edtPreco.getText().toString()));
                    long res = dao.adicionarCompra(c);
                    if(res > 0) {
                        Toast.makeText(Adicionar.this, "Salvo", Toast.LENGTH_SHORT).show();
                        edtNovaCompra.setText("");
                        edtQuant.setText("");
                        edtPreco.setText("");
                    } else {
                        Toast.makeText(Adicionar.this, "Erro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Adicionar.this, "Insira um item", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}