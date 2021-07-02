package com.example.appsaude;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appsaude.dao.UsuarioDAO;
import com.example.appsaude.entity.Usuario;
import com.example.appsaude.sqlite.Conexao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnPesquisar = findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCpf = findViewById(R.id.txtList);
                int cpf = Integer.parseInt(txtCpf.getText().toString());
                carregarDados(cpf);
            }
        });

        Button btnAtualizar = findViewById(R.id.btnAtualizar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCpf = findViewById(R.id.txtList);
                EditText txtIdade = findViewById(R.id.editTextIdade);
                EditText txtNome = findViewById(R.id.editTextNome);
                EditText txtCidade = findViewById(R.id.editTextCidade);
                EditText txtRua = findViewById(R.id.editTextRua);
                int cpf = Integer.parseInt(txtCpf.getText().toString());
                int idade = Integer.parseInt(txtIdade.getText().toString());

                Usuario usuario = new Usuario();
                usuario.setCpf(cpf);
                usuario.setIdade(idade);
                usuario.setNome(txtNome.getText().toString());
                usuario.setRua(txtRua.getText().toString());
                usuario.setCidade(txtCidade.getText().toString());

                UsuarioDAO usuarioDao = new UsuarioDAO();
                usuarioDao.alterar(usuario);

                Toast.makeText(getApplicationContext(),
                        "Usuário Alterado com Sucesso", Toast.LENGTH_LONG).show();

            }
        });

        Button btnDeletar = findViewById(R.id.btnDeletar);

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCpf = findViewById(R.id.txtList);
                int cpf = Integer.parseInt(txtCpf.getText().toString());

                UsuarioDAO userDao = new UsuarioDAO();
                userDao.excluir(cpf);

                Toast.makeText(getApplicationContext(),
                        "Usuário Excluido com Sucesso", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void carregarDados(int cpf){
        try {
            EditText editTextNome = (EditText) findViewById(R.id.editTextNome);
            EditText editTextIdade = (EditText) findViewById(R.id.editTextIdade);
            EditText editTextRua = (EditText) findViewById(R.id.editTextRua);
            EditText editTextCidade = (EditText) findViewById(R.id.editTextCidade);
            SQLiteDatabase conexao = Conexao.getInstance();
            Cursor cursor = conexao.rawQuery
                    ("SELECT cpf, nome, idade, rua, cidade FROM usuario WHERE cpf = " + cpf, null);
            cursor.moveToFirst();
            editTextNome.setText(cursor.getString(1));
            editTextIdade.setText(cursor.getString(2));
            editTextRua.setText(cursor.getString(3));
            editTextCidade.setText(cursor.getString(4));


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}