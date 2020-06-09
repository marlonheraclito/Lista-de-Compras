package com.marlonheraclito.listacomprasv12.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marlonheraclito.listacomprasv12.modelo.Compra;
import com.marlonheraclito.listacomprasv12.utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private SQLiteDatabase db;
    private DataBaseHelper helper;

    public Dao(Context context) {
       helper = new DataBaseHelper(context);
       db = helper.getWritableDatabase();
    }

    public long adicionarCompra(Compra c){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", c.getNome());
        contentValues.put("QUANT", c.getQuant());
        contentValues.put("PRECO", c.getPreco());
        contentValues.put("TOTAL", c.getTotal());


        long res = db.insert(helper.TABLE_NAME, null, contentValues);
        // db.close();
        return res;
    }

    public List<Compra> select(){
        List<Compra> listaCompras = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + helper.TABLE_NAME, null);

        if(res.getCount()!=0){
            while (res.moveToNext()) {
                Compra c = new Compra(res.getInt(0), res.getString(1), res.getInt(2), res.getFloat(3),
                        res.getFloat(4));
                listaCompras.add(c);
            }
            res.close();
        }
        return listaCompras;
    }

    public Integer apagarCompra(String id){
        return db.delete(helper.TABLE_NAME, "ID = ?", new String[] {id});
    }

    public Boolean alterarCompra(Compra c){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", c.getId());
        contentValues.put("NOME", c.getNome());
        contentValues.put("QUANT", c.getQuant());
        contentValues.put("PRECO", c.getQuant());

        db.update(helper.TABLE_NAME, contentValues, "ID = ?", new String[] {String.valueOf(c.getId())});
        db.close();
        return true;
    }


}
