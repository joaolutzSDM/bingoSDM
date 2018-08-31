package br.edu.ifsp.scl.sdm.bingosdm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private ArrayList<Integer> numerosSorteados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();

        //recuperando os números sorteados, se necessário
        numerosSorteados = savedInstanceState == null ? new ArrayList<Integer>() : savedInstanceState.getIntegerArrayList("numeros");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Salvar os dados do estado dinâmico
        outState.putIntegerArrayList("numeros", numerosSorteados);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void sortearNumero(View view) {
        int numero = random.nextInt(75);
        if(!numerosSorteados.contains(numero)) {
            numerosSorteados.add(numero);
        } else {
            sortearNumero(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
