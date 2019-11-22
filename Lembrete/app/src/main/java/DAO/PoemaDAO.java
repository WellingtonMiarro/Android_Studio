package DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Poema;

public class PoemaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public PoemaDAO (Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserirPoema(Poema poema) {

        // objeto contendo os valores para salvar no BD
        ContentValues values = new ContentValues();

        // colocar os valores
        values.put("titulo", poema.getTitulo());
        values.put("categoria", poema.getCategoria());
        values.put("data", poema.getData());
        values.put("descricao", poema.getDescricao());
        values.put("autor", poema.getAutor());

        // insere no BD e retorna com o novo ID
        return banco.insert("poema", null, values);
    }

    public List<Poema> obterPoema() {

        // objeto que recebe os registros do BD
        List<Poema> poemas = new ArrayList<>();

        // objeter os dados do BD
        Cursor cursor = banco.query(
                "poema",
                new String[]{"id", "titulo", "categoria", "data", "descricao", "autor"},
                null,
                null,
                null,
                null,
                null
        );

        // loop para percorrer os registros
        while (cursor.moveToNext()) {
            Poema p = new Poema();
            //P = Poema abreviaçao
            // transformando o registro num objeto
            p.setId(cursor.getInt(0));
            p.setTitulo(cursor.getString(1));
            p.setCategoria(cursor.getString(2));
            p.setData(cursor.getString(3));
            p.setDescricao(cursor.getString(4));
            p.setAutor(cursor.getString(5));

            // adicionar o poema a lista de poemas
            poemas.add(p);
        }

        // retornar os poemas listado
        return poemas;
    }

    // método para excluir um poema
    public void excluirPoema(Poema poema) {
        // exclui o peoma do banco
        banco.delete(
                "poema",
                "id = ?",
                new String[]{String.valueOf(poema.getId())});
    }

    // método para atualizar um poema
    public void atualizarPoema(Poema poema) {

        // objeto contendo os valores para salvar no BD
        ContentValues values = new ContentValues();

        // colocar os valores
        values.put("titulo", poema.getTitulo());
        values.put("categotia", poema.getCategoria());
        values.put("data", poema.getData());
        values.put("descricao", poema.getDescricao());
        values.put("autor", poema.getAutor());

        // envia os dados após a edição do registro
        banco.update(
                "poema",
                values,
                "id = ?",
                new String[]{String.valueOf(poema.getId())});

    }

}
