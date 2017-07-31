package pooa20171.iff.br.livraria.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pooa20171.iff.br.livraria.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button livroBT = (Button) findViewById(R.id.bt_livros);

        livroBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LivroActivity.class);
                startActivity(intent);

            }
        });


    }
    private Context getContext(){
        return this;
    }

}
