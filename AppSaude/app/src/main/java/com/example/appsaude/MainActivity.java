package com.example.appsaude;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsaude.dao.UsuarioDAO;
import com.example.appsaude.entity.Usuario;
import com.example.appsaude.sqlite.Conexao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlistar = findViewById(R.id.btnListar);
        Intent intent = new Intent(this, ListActivity.class);

        btnlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        new Conexao(getApplicationContext(),
                "appsaude",
                null,
                1);
    }

    public void salvar(View view) {
        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtCpf = findViewById(R.id.txtCpf);
        EditText txtIdade = findViewById(R.id.txtIdade);
        EditText txtRua = findViewById(R.id.txtRua);
        EditText txtCidade = findViewById(R.id.txtCidade);

        int cpf = Integer.parseInt(txtCpf.getText().toString());
        int idade = Integer.parseInt(txtIdade.getText().toString());

        Usuario usuario = new Usuario();
        usuario.setNome(txtNome.getText().toString());
        usuario.setCpf(cpf);
        usuario.setIdade(idade);
        usuario.setRua(txtRua.getText().toString());
        usuario.setCidade(txtCidade.getText().toString());

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.salvar(usuario);

        Toast.makeText(getApplicationContext(),
                "Usuário Cadastrado com Sucesso", Toast.LENGTH_LONG).show();

    }
}