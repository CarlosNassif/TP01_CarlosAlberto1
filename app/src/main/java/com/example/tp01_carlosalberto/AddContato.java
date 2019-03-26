package com.example.tp01_carlosalberto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContato extends AppCompatActivity {

    private static final int cod_SalvarContato = 1;
    private static final int cod_EnviarWhatsApp = 2;
    private static final int cod_EnviarEmail = 3;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contato);
    }

    public void Button_CriarContato (View view) {

        //Criar um objeto Pessoa com os dados digitados
        EditText editText = (EditText) findViewById(R.id.caixa_email);
        String email = editText.getText().toString();

        editText = (EditText) findViewById(R.id.caixa_nome);
        String nome = editText.getText().toString();

        editText = (EditText) findViewById(R.id.caixa_numero);
        String numero = editText.getText().toString();

        try {

            pessoa = new Pessoa(nome, email, numero);

            CriarContato();

        } catch (Exception e) {
            Toast.makeText(this, "Erro na inserção dos dados!", Toast.LENGTH_SHORT).show();
        }

    }
    private void CriarContato() {

        // Criar contato

        Intent intent = new Intent(Intent.ACTION_INSERT);

        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, pessoa.getNome());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, pessoa.getNumero());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, pessoa.getEmail());
        intent.putExtra("finishActivityOnSaveCompleted", true);

        startActivityForResult(intent, cod_SalvarContato);

    }

    private void EnviarEmail()  {

        // Enviar e-mail

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"+pessoa.getEmail())); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Notificação aplicativo: TP01_CarlosAlberto");
        intent.putExtra(Intent.EXTRA_TEXT, "Olá " + pessoa.getNome()+ "! Seu número "+pessoa.getNumero()+" e seu e-mail "+pessoa.getEmail()+ " foram adicionado aos meus contatos.");

        startActivityForResult(intent, cod_EnviarEmail);

    }

    private void EnviarWhatsApp() {

        // Enviar WhatsApp

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setPackage("com.whatsapp");
        intent.setData(Uri.parse( "https://api.whatsapp.com/send?phone=" + "+5531" + pessoa.getNumero() + "&text=" + "Olá " + pessoa.getNome() + "! Seu número " + pessoa.getNumero() + " e seu e-mail " + pessoa.getEmail() + " foram adicionado aos meus contatos."));


        try {
            startActivityForResult(intent, cod_EnviarWhatsApp);
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp não encontrado!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == cod_SalvarContato) {

            if(resultCode == RESULT_OK) {

                Toast.makeText(this, "Envie agora um WhatsApp para o seu novo contato.", Toast.LENGTH_SHORT).show();
                EnviarWhatsApp();

            } else {

                Toast.makeText(this, "Erro ao adicionar o contato!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }

        }

        if (requestCode == cod_EnviarWhatsApp) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Envie agora um e-mail para seu novo contato.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao mandar mensagem no WhatsApp!", Toast.LENGTH_SHORT).show();
            }
            EnviarEmail();

        }

        if(requestCode == cod_EnviarEmail){

                Intent intent = new Intent(this, MainActivity.class);
                Toast.makeText(this, "Feito!", Toast.LENGTH_SHORT).show();
                startActivity(intent);


        }

    }
}

