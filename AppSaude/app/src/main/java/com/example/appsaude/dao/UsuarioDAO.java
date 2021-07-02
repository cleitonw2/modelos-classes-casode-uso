package com.example.appsaude.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


import com.example.appsaude.entity.Usuario;
import com.example.appsaude.sqlite.Conexao;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("cpf", usuario.getCpf());
        values.put("nome", usuario.getNome());
        values.put("idade", usuario.getIdade());
        values.put("rua", usuario.getRua());
        values.put("cidade", usuario.getCidade());

        SQLiteDatabase conexao = Conexao.getInstance();
        conexao.insert("usuario", null, values);
    }

    public void alterar(Usuario usuario) {
        try {
            ContentValues values = new ContentValues();
            values.put("cpf", usuario.getCpf());
            values.put("nome", usuario.getNome());
            values.put("idade", usuario.getIdade());
            values.put("rua", usuario.getRua());
            values.put("cidade", usuario.getCidade());

            SQLiteDatabase conexao = Conexao.getInstance();
            conexao.update("usuario", values, "cpf = " + usuario.getCpf() , null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Integer cpf) {
        SQLiteDatabase conexao = Conexao.getInstance();
        conexao.delete("usuario" ,"cpf = " + cpf, null);
    }

}
