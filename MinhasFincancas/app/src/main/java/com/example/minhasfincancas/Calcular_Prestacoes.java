package com.example.minhasfincancas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Calcular_Prestacoes extends AppCompatActivity {
    private EditText campoNumeroDeMeses;
    private EditText campoTaxaJurosMensal;
    private EditText campoValorFinanciado;
    private EditText campoValorDasPrestacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular__prestacoes);

        setTitle("Calcular Prestações");
    }

    public void calcularPrestacoes(View view) {

        // chama método para instanciar as views
        instanciarCampos();

        // captura o número de meses
        int numeroDeMeses = Integer.parseInt(campoNumeroDeMeses.getText().toString());
        // captura a taxa de juros
        double taxaJurosMensal = Double.parseDouble(campoTaxaJurosMensal.getText().toString());
        // captura o valor financiado
        double valorFinanciado = Double.parseDouble(campoValorFinanciado.getText().toString());
        // calcula o valor das prestações
        double valorDasPrestacoes = (valorFinanciado * (taxaJurosMensal/100)) / (1 - Math.pow(1+(taxaJurosMensal/100), -numeroDeMeses));
        // arredondar para duas casas decimais
        valorDasPrestacoes = Math.floor(valorDasPrestacoes * 100) / 100;
        // mostra o valor
        campoValorDasPrestacoes.setText(String.valueOf(valorDasPrestacoes));

    }

    public void limpar(View view) {

        // chama método para instanciar as views
        instanciarCampos();

        // atribui valores em branco (string vazia) nas views
        campoNumeroDeMeses.setText("");
        campoTaxaJurosMensal.setText("");
        campoValorFinanciado.setText("");
        campoValorDasPrestacoes.setText("");
        // define o focus (item selecionado) ao campo
        campoNumeroDeMeses.requestFocus();

    }

    private void instanciarCampos() {
        // instancia os objetos
        campoNumeroDeMeses = (EditText) findViewById(R.id.edt_calcular_prestacoes_n_de_meses);
        campoTaxaJurosMensal = (EditText) findViewById(R.id.edt_calcular_prestacoes_taxa_juros_mensal);
        campoValorFinanciado = (EditText) findViewById(R.id.edt_calcular_prestacoes_valor_financiado);
        campoValorDasPrestacoes = (EditText) findViewById(R.id.edt_calcular_prestacoes_valor_das_prestacoes);
    }
}
