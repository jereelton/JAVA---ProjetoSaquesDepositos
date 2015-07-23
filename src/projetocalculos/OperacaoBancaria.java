/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocalculos;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author jereelton
 */
public class OperacaoBancaria {

    private String valorDeposito;
    private String valorResgate;
    private String valorSaldo;
    private String resultadoDeposito;
    private String resultadoResgate;
    private boolean saldoInsuficiente;
    
    public void depositarDinheiro(String valorDeposito) {
    
        if(valorDeposito.equals("")) {
            JOptionPane.showMessageDialog(null, "Valor para depósito não informado");
        } else {
            this.valorDeposito = valorDeposito.replaceAll("[^0-9]", "").replaceAll("^[0]", "");
        }
        
    }
    
    public void resgatarDinheiro(String valorResgate) {
        
        if(valorResgate.equals("")) {
            JOptionPane.showMessageDialog(null, "Valor para resgate não informado");
        } else {
            this.valorResgate = valorResgate.replaceAll("[^0-9]", "").replaceAll("^[0]", "");
        }
        
    }
    
    public void obterSaldoConta(String valorSaldo) {
        
        this.valorSaldo = valorSaldo.replaceAll("[^0-9]", "");
    
    }
    
    public String obterResultadoDeposito() {
    
        return this.resultadoDeposito;
        
    }
    
    public String obterResultadoResgate() {
    
        return this.resultadoResgate;
        
    }
    
    public String mostrarExtratoDeposito() {
    
        FormataMoeda formatarMoeda = new FormataMoeda(this.valorDeposito);
        return "Depósito efetuado : R$ " + formatarMoeda.formatar() + "\n";
        
    }
    
    public String mostrarExtratoResgate() {
    
        FormataMoeda formatarMoeda = new FormataMoeda(this.valorResgate);
            
        if(this.saldoInsuficiente == false) {
            return "Resgate efetuado  : R$ " + formatarMoeda.formatar() + "\n";
        } else {
            return "Resgate bloqueado : R$ " + formatarMoeda.formatar() + "\n";
        }
        
    }
    
    public void calcularDeposito() {
    
        BigDecimal valorDepositar  = new BigDecimal(this.valorDeposito);
        BigDecimal valorSaldoAtual = new BigDecimal(this.valorSaldo);
        FormataMoeda formatarMoeda = new FormataMoeda(valorDepositar.add(valorSaldoAtual).toString());
        
        this.resultadoDeposito = formatarMoeda.formatar();
        
        //Debug
        System.out.println("Valor Deposito     : " + valorDepositar);
        System.out.println("Valor Saldo Atual  : " + valorSaldoAtual);
        System.out.println("Resultado Deposito : " + this.resultadoDeposito);
                
    }
    
    public void calcularResgate() {
    
        BigDecimal valorSaldoAtual = new BigDecimal(this.valorSaldo);
        BigDecimal valorResgatar   = new BigDecimal(this.valorResgate);

        if(valorResgatar.compareTo(valorSaldoAtual) == 1) {
        
            JOptionPane.showMessageDialog(null, "Seu saldo é insuficiente\n para realizar o Resgate");
            this.saldoInsuficiente = true;
            return;
        
        }
        
        FormataMoeda formatarMoeda = new FormataMoeda(valorSaldoAtual.subtract(valorResgatar).toString());
        
        this.saldoInsuficiente = false;
        this.resultadoResgate  = formatarMoeda.formatar();
        
        //Debug
        System.out.println("Valor Resgate     : " + valorResgatar);
        System.out.println("Valor Saldo Atual : " + valorSaldoAtual);
        System.out.println("Resultado Resgate : " + this.resultadoResgate);
                
    }
    
}
