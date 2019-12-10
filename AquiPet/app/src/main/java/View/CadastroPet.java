package View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aquipet.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Model.Pet;

public class CadastroPet extends AppCompatActivity {

    //Campos correspondentes a ACTIVITY
    private EditText campoEspecie;
    private EditText campoNomePet;
    private EditText campoRaca;
    private EditText campoPropetario;
    private ListView ltvDados;

    //serve para conectar com o banco de dados do FIREBASE
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //definido como objeto de dados em Model
    private Pet petSelecionado;

    // responsavel por manter a lista de registros do BD
    private List<Pet> listPet = new ArrayList<>();
    private ArrayAdapter<Pet> arrayAdapterPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // relacionar os objetos com os componentes
        campoEspecie = (EditText) findViewById(R.id.edt_especie);
        campoNomePet = (EditText) findViewById(R.id.edt_nomePet);
        campoRaca = (EditText) findViewById(R.id.edt_raca);
        campoPropetario = (EditText) findViewById(R.id.edt_dono);
        ltvDados = (ListView) findViewById(R.id.ltv_dados);

        inicializarFirebase();
        eventoDataBase();

        ltvDados.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // transforma o item selecionado num objeto do tipo Pessoa
                petSelecionado = (Pet) adapterView.getItemAtPosition(i);
                // atribui os valores às propriedades do objeto
                campoEspecie.setText(petSelecionado.getEspecie());
                campoNomePet.setText(petSelecionado.getNomePet());
                campoRaca.setText(petSelecionado.getRaca());
                campoPropetario.setText(petSelecionado.getPropetario());

            }
        });



    }
    private void eventoDataBase() {
        databaseReference.child("Pet").addValueEventListener(new ValueEventListener() {
            // sobrescreve método que verifica se houve mudança no banco
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPet.clear(); // limpa o ListView antes de popular novamente
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Pet p = objSnapshot.getValue(Pet.class);
                    listPet.add(p);
                }
                arrayAdapterPet = new ArrayAdapter<Pet>(CadastroPet.this, android.R.layout.simple_list_item_1, listPet);
                ltvDados.setAdapter(arrayAdapterPet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    // método responsável para criar uma instância com o Firebase
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_novo) { // opção para cadastrar um novo registro
            Pet p = new Pet();
            // gera um ID para o novo registro
            p.setUid(UUID.randomUUID().toString());
            p.setEspecie(campoEspecie.getText().toString());
            p.setNomePet(campoNomePet.getText().toString());
            p.setRaca(campoRaca.getText().toString());
            p.setPropetario(campoPropetario.getText().toString());

            // salva os dados do objeto no BD usando como nome de tabela "Pet"
            databaseReference.child("Pet").child(p.getUid()).setValue(p);

            //método para limpar os campos
            limparCampos();

        } else if (id == R.id.menu_atualiza) { // opção para atualizar um registro existente
            Pet p = new Pet();
            // obtém o ID do objeto selecionado no ListView
            p.setUid(UUID.randomUUID().toString());
            p.setEspecie(campoEspecie.getText().toString());
            p.setNomePet(campoNomePet.getText().toString());
            p.setRaca(campoRaca.getText().toString());
            p.setPropetario(campoPropetario.getText().toString());


            // salva os dados do objeto no BD usando como nome de tabela "Pet"
            databaseReference.child("Pet").child(p.getUid()).setValue(p);

            // método para limpar os campos
            limparCampos();

        } else if (id == R.id.menu_deleta) { // opção para excluir um registro existente
            Pet p = new Pet();
            // obtém o ID do objeto selecionado no ListView
            p.setUid(petSelecionado.getUid());
            // excluir os dados do registro a partir do ID
            databaseReference.child("Pet").child(p.getUid()).removeValue();

            //e por fim chamar o método para limpar os campos
            limparCampos();
        }

        return true;
    }

    private void limparCampos() { // método para limpar os campos
        campoEspecie.setText("");
        campoNomePet.setText("");
        campoRaca.setText("");
        campoPropetario.setText("");

        campoEspecie.requestFocus(); // mantém o focus neste campo após limpar
    }


}
