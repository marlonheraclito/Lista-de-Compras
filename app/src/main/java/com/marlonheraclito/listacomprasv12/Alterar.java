package com.marlonheraclito.listacomprasv12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marlonheraclito.listacomprasv12.controle.Dao;
import com.marlonheraclito.listacomprasv12.modelo.Compra;
import com.marlonheraclito.listacomprasv12.utils.Common;

public class Alterar extends AppCompatActivity {

    Button btnAlterar;
    EditText edtNomeAlterar, edtQuantAlterar, edtPrecoAlterar;

    Dao dao;

    int posicao;

    Compra c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        setTitle("Alterar Compra");

        dao = new Dao(this);

        btnAlterar = findViewById(R.id.btnAlterar );
        edtNomeAlterar = findViewById(R.id.edtNomeAlterar);
        edtQuantAlterar  = findViewById(R.id.edtQuantAlterar);
        edtPrecoAlterar = findViewById(R.id.edtPrecoAlterar);

        Intent in = getIntent();
        posicao = in.getIntExtra("id", 0);

        c = Common.listaCompras.get(posicao);
        edtNomeAlterar.setText(c.getNome());
        edtQuantAlterar.setText(String.valueOf(c.getQuant()));
        edtPrecoAlterar.setText(String.valueOf(c.getPreco()));


        alterar();

    }


    public void alterar(){
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setId(c.getId());
                c.setNome(edtNomeAlterar.getText().toString());
                c.setQuant(Integer.parseInt(edtQuantAlterar.getText().toString()));
                c.setPreco(Float.parseFloat(edtQuantAlterar.getText().toString()));

                Boolean res = dao.alterarCompra(c);
                if(res){
                    Toast.makeText(Alterar.this, "Alterado", Toast.LENGTH_SHORT).show();
                    /* Intent in = new Intent(Alterar.this, MainActivity.class);
                    startActivity(in);
                    finish(); */
                    edtPrecoAlterar.setText("");
                    edtNomeAlterar.setText("");
                    edtQuantAlterar.setText("");
                    btnAlterar.setEnabled(false);
                } else {
                    Toast.makeText(Alterar.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}