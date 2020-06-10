package br.com.appbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CadastrarCliente extends AppCompatActivity {

    private EditText editTitulo, editAutor, editEditora;
    private Button btnSalvar, btnLimpar;
    private Map<String,String> dados = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        editTitulo = findViewById(R.id.editTitulo);
        editAutor = findViewById(R.id.editAutor);
        editEditora = findViewById(R.id.editEditora);

        // Botão de salvar dados.
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                dados.put("titulo", editTitulo.getText().toString());
                dados.put("autor", editAutor.getText().toString());
                dados.put("editora", editEditora.getText().toString());

                if(!checarCampos()) {
                    String resultado = crud.insereDado(dados);
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), Consulta.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Os campos de Titulo, Autor e Editora não podem estar em branco.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean checarCampos(){

        if(editTitulo.getText().toString() == "" || editAutor.getText().toString() == "" || editEditora.getText().toString() == ""){
            return true;
        }else{
            return false;
        }
    }
}
