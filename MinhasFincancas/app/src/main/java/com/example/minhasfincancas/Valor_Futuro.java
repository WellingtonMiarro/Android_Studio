package com.example.minhasfincancas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Valor_Futuro extends AppCompatActivity {
    private EditText campoNumeroDeMeses;
    private EditText campoTaxaJurosMensal;
    private EditText campoCapitalAtual;
    private EditText campoValorObtidoAoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor__futuro);
    }
    public void calcularPrestacoes(View view) {

        // chama método para instanciar as views
        instanciarCampos();

        // captura o número de meses
        int numeroDeMeses = Integer.parseInt(campoNumeroDeMeses.getText().toString());
        // captura a taxa de juros
        double taxaJurosMensal = Double.parseDouble(campoTaxaJurosMensal.getText().toString());
        // captura o capital atual
        double capitalAtual = Double.parseDouble(campoCapitalAtual.getText().toString());
        // calcula o valor das prestações
        double valorObtidoAoFinal = Math.pow(1 + (taxaJurosMensal / 100), numeroDeMeses) * capitalAtual;
        // arredondar para duas casas decimais
        valorObtidoAoFinal = Math.floor(valorObtidoAoFinal * 100) / 100;
        // mostra o valor
        campoValorObtidoAoFinal.setText(String.valueOf(valorObtidoAoFinal));

    }

    public void limpar(View view) {
        instanciarCampos();

        // atribui valores em branco (string vazia) nas views
        campoNumeroDeMeses.setText("");
        campoTaxaJurosMensal.setText("");
        campoCapitalAtual.setText("");
        campoValorObtidoAoFinal.setText("");
        // define o focus (item selecionado) ao campo
        campoNumeroDeMeses.requestFocus();

    }

    private void instanciarCampos() {
        // instancia os objetos
        campoNumeroDeMeses = (EditText) findViewById(R.id.edt_valor_futuro_n_de_meses);
        campoTaxaJurosMensal = (EditText) findViewById(R.id.edt_valor_futuro_taxa_juros_mensal);
        campoCapitalAtual = (EditText) findViewById(R.id.edt_valor_futuro_capital_atual);
        campoValorObtidoAoFinal = (EditText) findViewById(R.id.edt_valor_futuro_valor_obtido_ao_final);
    }
}
