package br.com.fedablio.ica;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private TextView tvResultado;
    private EditText etPeso;
    private EditText etAltura;
    private LinearLayout llTelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResultado = (TextView) findViewById(R.id.textView1);
        etPeso = (EditText) findViewById(R.id.editText1);
        etAltura = (EditText) findViewById(R.id.editText2);
        llTelas = (LinearLayout) findViewById(R.id.llTelasActivity);
        View viewButton = getLayoutInflater().inflate(R.layout.tela0, null);
        llTelas.removeAllViews();
        llTelas.addView(viewButton);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
    }

    public void executa_calculo(View view) {
        if (etPeso.getText().length() != 0 && etAltura.getText().length() != 0) {
            double imc = 0.0;
            double peso = Double.parseDouble(etPeso.getText().toString());
            double altura = Double.parseDouble(etAltura.getText().toString());
            imc = peso / Math.pow(altura, 2);
            DecimalFormat arredonda = new DecimalFormat("0.00");
            String imc_pass = String.valueOf(arredonda.format(imc).replace(",", "."));
            if (imc >= 0 && imc < 18.5) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela1, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Abaixo do peso - IMC: " + imc_pass);
            } else if (imc >= 18.5 && imc <= 24.99) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela2, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Peso adequado - IMC: " + imc_pass);
            } else if (imc >= 25.00 && imc <= 29.99) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela3, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Sobrepeso - IMC: " + imc_pass);
            } else if (imc >= 30.00 && imc <= 34.99) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela4, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Obesidade grau I - IMC: " + imc_pass);
            } else if (imc >= 35.00 && imc <= 39.99) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela5, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Obesidade grau II - IMC: " + imc_pass);
            } else if (imc >= 40.00) {
                View viewButton = getLayoutInflater().inflate(R.layout.tela6, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("Obesidade grau III - IMC: " + imc_pass);
            } else {
                View viewButton = getLayoutInflater().inflate(R.layout.tela0, null);
                llTelas.removeAllViews();
                llTelas.addView(viewButton);
                tvResultado.setText("-");
            }
        } else {
            Toast.makeText(this, "Há campos em branco", Toast.LENGTH_SHORT).show();
        }
    }

    public void executa_limpeza(View view) {
        View viewButton = getLayoutInflater().inflate(R.layout.tela0, null);
        llTelas.removeAllViews();
        llTelas.addView(viewButton);
        tvResultado.setText("Classificação - IMC");
        etAltura.setText(null);
        etPeso.setText(null);
        etPeso.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnInformacoes) {
            Intent intent = new Intent(MainActivity.this, InformacaoActivity.class);
            startActivity(intent);
            finish();
        }
        if(id == R.id.mnSobre){
            Intent intent = new Intent(MainActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}