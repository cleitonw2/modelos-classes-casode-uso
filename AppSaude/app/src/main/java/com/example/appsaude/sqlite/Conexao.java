package com.example.appsaude.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static SQLiteDatabase instance;

    public static SQLiteDatabase getInstance() {
        return instance;
    }

    public Conexao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        instance = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tabelaUsuario = "";
        tabelaUsuario += "create table if not exists usuario (";
        tabelaUsuario += "cpf integer primary key, ";
        tabelaUsuario += "nome varchar(255), ";
        tabelaUsuario += "idade integer, ";
        tabelaUsuario += "rua varchar(255), ";
        tabelaUsuario += "cidade varchar(255) ";
        tabelaUsuario += ")";

        db.execSQL(tabelaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
