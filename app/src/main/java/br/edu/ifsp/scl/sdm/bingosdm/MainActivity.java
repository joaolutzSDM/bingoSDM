package br.edu.ifsp.scl.sdm.bingosdm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private ArrayList<Integer> numerosSorteados;

    TextView txvNumeroSorteado;
    TextView txvNumerosSorteados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txvNumeroSorteado = findViewById(R.id.txvNumeroSorteadoLabel);
        txvNumerosSorteados = findViewById(R.id.txvNumerosSorteadosTextView);

        numerosSorteados = new ArrayList<>();

        //recuperando os números sorteados, se necessário
        if(savedInstanceState != null) {
            numerosSorteados = savedInstanceState.getIntegerArrayList("numeros");
            printNumerosSorteados();
        }

        if(!numerosSorteados.isEmpty()) {
            printNumber(numerosSorteados.get(numerosSorteados.size() - 1));
        }

        setSupportActionBar(toolbar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Salvar os dados do estado dinâmico
        outState.putIntegerArrayList("numeros", numerosSorteados);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        numerosSorteados = savedInstanceState.getIntegerArrayList("numeros");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void sortearNumero(View view) {
        if(numerosSorteados.size() == 75) {
            Toast.makeText(this, R.string.numero_maximo_atingido, Toast.LENGTH_SHORT).show();
            return;
        }
        int numero = random.nextInt(75);
        if(!numerosSorteados.contains(numero)) {
            printNumber(numero);
            numerosSorteados.add(numero);
            if(numerosSorteados.size() == 1) {
                txvNumerosSorteados.setText(String.valueOf(numero));
            } else {
                txvNumerosSorteados.setText(txvNumerosSorteados.getText() + "\n" + String.valueOf(numero));
            }
        } else {
            sortearNumero(view);
        }
    }

    private void printNumerosSorteados() {
        for(int i = 0; i < numerosSorteados.size();i++) {
            if(i == 0) {
                txvNumerosSorteados.setText(String.valueOf(numerosSorteados.get(i)));
            } else {
                txvNumerosSorteados.setText(txvNumerosSorteados.getText() + "\n" + String.valueOf(numerosSorteados.get(i)));
            }
        }
    }

    private void printNumber(int numero) {
        if(numero <= 15) {
            txvNumeroSorteado.setText(String.format(getString(R.string.numeros_sorteados), "B", numero));
        } else if(numero <= 30) {
            txvNumeroSorteado.setText(String.format(getString(R.string.numeros_sorteados), "I", numero));
        } else if(numero <= 45) {
            txvNumeroSorteado.setText(String.format(getString(R.string.numeros_sorteados), "N", numero));
        } else if(numero <= 60) {
            txvNumeroSorteado.setText(String.format(getString(R.string.numeros_sorteados), "G", numero));
        } else { //maior que 60
            txvNumeroSorteado.setText(String.format(getString(R.string.numeros_sorteados), "O", numero));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
