package pooa20171.iff.br.livraria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import pooa20171.iff.br.livraria.R;
import pooa20171.iff.br.livraria.model.Livro;

public class LivroDetalheActivity extends AppCompatActivity {
    EditText nome, autor, descricao;
    Button btsalvar,btalterar, btdeletar;

    int id;
    Livro livro;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_livro);
        autor = (EditText) findViewById(R.id.ed_autor_livro);
        descricao = (EditText) findViewById(R.id.ed_descricao_livro);

        btsalvar = (Button) findViewById(R.id.bt_salvar_livro);
        btalterar = (Button) findViewById(R.id.bt_alterar_livro);
        btdeletar = (Button) findViewById(R.id.bt_deletar_livro);

        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            livro = realm.where(Livro.class).equalTo("id",id).findFirst();

            nome.setText(livro.getNome());
            autor.setText(livro.getAutor());
            descricao.setText(livro.getDescricao());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }



        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar(){
        realm.beginTransaction();
        livro.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Livro deletado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Livro.class).max("id") !=null)
            proximoID = realm.where(Livro.class).max("id").intValue()+1;

        realm.beginTransaction();
        Livro livro = new Livro();
        livro.setId(proximoID);
        livro.setNome(nome.getText().toString());
        livro.setAutor(autor.getText().toString());
        livro.setDescricao(descricao.getText().toString());

        realm.copyToRealm(livro);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Livro Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void alterar() {

        realm.beginTransaction();

        livro.setNome(nome.getText().toString());
        livro.setAutor(autor.getText().toString());
        livro.setDescricao(descricao.getText().toString());

        realm.copyToRealm(livro);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Livro Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }
}
