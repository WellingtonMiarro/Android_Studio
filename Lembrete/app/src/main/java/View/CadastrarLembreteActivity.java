package View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lembrete.R;

import DAO.PoemaDAO;
import Model.Poema;




public class CadastrarLembreteActivity extends AppCompatActivity {

    // objetos correspondentes aos componentes da Activity
    private EditText campoTitulo;
    private EditText campoCategoria;
    private EditText campoData;
    private EditText campoDescricao;
    private EditText campoAutor;

    // objeto para acessar ao BD
    private PoemaDAO poemaDAO;

    // objeto referente ao poema
    private Poema poema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lembrete);

        // relacionar os objetos com os componentes
        campoTitulo = (EditText) findViewById(R.id.salvar_titulo_Poema);
        campoCategoria = (EditText) findViewById(R.id.salvar_categoria_Poema);
        campoData = (EditText) findViewById(R.id.salvar_data_Poema);
        campoDescricao = (EditText) findViewById(R.id.salvar_descricao_Poema);
        campoAutor = (EditText) findViewById(R.id.salvar_autor_Poema);

        // instanciar o objeto que estabelece a conexão com o BD
        poemaDAO = new PoemaDAO(this);

        // define um intent para possivelmente resgatar algo
        Intent intent = getIntent();

        // verificar se veio alguma intent de outra Activity
        if (intent.hasExtra("poema")) {
            poema = (Poema) intent.getSerializableExtra("poema");

            // preencher os campos do formulário para edição
            campoTitulo.setText(poema.getTitulo());
            campoCategoria.setText(poema.getCategoria());
            campoData.setText(poema.getData());
            campoDescricao.setText(poema.getDescricao());
            campoAutor.setText(poema.getAutor());

        }

    }

    public void salvarPoema(View view) {
        // verificar se é edição ou novo registro
        if (poema == null) { // novo registro
            poema = new Poema();

            // obter os dados do formulários e salvar no objeto
            poema.setTitulo(campoTitulo.getText().toString().trim());
            poema.setCategoria(campoCategoria.getText().toString().trim());
            poema.setData(campoData.getText().toString().trim());
            poema.setDescricao(campoDescricao.getText().toString().trim());
            poema.setAutor(campoAutor.getText().toString().trim());

            // salvar no BD, recebendo o ID do poema
            long id = poemaDAO.inserirPoema(poema);

            // informar o usuário que foi salvo
            Toast.makeText(this,
                    "Poema criado com sucesso. Id: " + id,
                    Toast.LENGTH_LONG).show();

        } else { // atualizar registro existente

            // obter os dados do formulários e salvar no objeto
            poema.setTitulo(campoTitulo.getText().toString().trim());
            poema.setCategoria(campoCategoria.getText().toString().trim());
            poema.setData(campoData.getText().toString().trim());
            poema.setDescricao(campoDescricao.getText().toString().trim());
            poema.setAutor(campoAutor.getText().toString().trim());

            // atualizar os dados no BD
            poemaDAO.atualizarPoema(poema);

            // emitir mensagem de sucesso
            Toast.makeText(this,
                    "Poema atualizado com sucesso!",
                    Toast.LENGTH_LONG).show();

        }

    }
}

