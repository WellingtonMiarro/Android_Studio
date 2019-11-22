package View;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lembrete.R;

import java.util.List;

import DAO.PoemaDAO;
import Model.Poema;


public class ListarLembreteActivity extends AppCompatActivity {


    // objeto que será utilizado para vincular com o componete da Activity
    ListView listar_poemas;

    // objeto para acessar o BD
    PoemaDAO poemaDAO;

    // objeto para listar os registros do BD
    List<Poema> poemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_lembrete);

        // vincular o objeto com o componente
        listar_poemas = (ListView) findViewById(R.id.listar_poema_List);

        // instanciar objeto que cria conexão com o BD
        poemaDAO = new PoemaDAO(this);

        // consultar os registros do BD
        poemas = poemaDAO.obterPoema();

        // adaptador para atribuir os valores no ListView
        ArrayAdapter adptador =  new ArrayAdapter<Poema>(
                this,
                android.R.layout.simple_list_item_1,
                poemas
        );

        // atribuir os valores à ListView
        listar_poemas.setAdapter(adptador);

        // registro pra dizer que o menu deve ser criado
        registerForContextMenu(listar_poemas);

    }
    public void novoPoema(View view) {

        // redireciona pra tela que cadastra nova tarefa
        startActivity(new Intent(
                ListarLembreteActivity.this,
                CadastrarLembreteActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // consultar os registros do BD
        poemas = poemaDAO.obterPoema();

        // adaptador para atribuir os valores no ListView
        ArrayAdapter adptador =  new ArrayAdapter<Poema>(
                this,
                android.R.layout.simple_list_item_1,
                poemas
        );

        // atribuir os valores à ListView
        listar_poemas.setAdapter(adptador);
    }

    // método usado para criar o menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu, menu);
    }

    // método para editar um poema
    public void editarPoema(MenuItem item) {

        // obtém o item da lista (ListView) que foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Poema poemaEditar = poemas.get(menuInfo.position);

        // preparar os dados que serão transmitidos para Activity de Cadastro
        Intent intent = new Intent(this, CadastrarLembreteActivity.class);
        intent.putExtra("poema", poemaEditar); // construir um poema para ser editado redirecionando para tela de cadastro

        // abre a Activity de Cadastro
        startActivity(intent);

    }

    // método para excluir um poema
    public void excluirPoema(MenuItem item) {
        // obtém o item da lista (ListView) que foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Poema poemaExclir = poemas.get(menuInfo.position);

        // caixa de diálogo para confirmar a exclusão
        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setTitle("Atenção!")
                .setMessage("Deseja excluir o poema?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // remover da lista de poemas
                        poemas.remove(poemaExclir);
                        // remover do BD
                        poemaDAO.excluirPoema(poemaExclir);
                        // atualizar a ListView, removendo o item
                        listar_poemas.invalidateViews();
                    }
                }).create();

        // mostrar a caixa de diálogo
        dialog.show();


    }

}