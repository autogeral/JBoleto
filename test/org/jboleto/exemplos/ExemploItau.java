
package org.jboleto.exemplos;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Fabio Souza
 *
 */
class ExemploItau {

    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento("09/09/2021");
        jBoletoBean.setDataProcessamento("09/09/2021");

        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DM");
        jBoletoBean.setCedente("AUTO GERAL AUTOPECAS DE ITU LTDA");
        jBoletoBean.setCarteira("109");

        jBoletoBean.setNomeSacado("Auto Geral AutoPecas LTDA");
        jBoletoBean.setEnderecoSacado("AV. Dom Pedro II, 1090");
        jBoletoBean.setBairroSacado("Centro");
        jBoletoBean.setCidadeSacado("Salto");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13320-903");
        jBoletoBean.setCpfSacado("05.437.537/0002-18");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO ITAÚ");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO ITAÚ");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologação");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("30/09/2021");
        jBoletoBean.setInstrucao1("NAO RECEBER APOS O QUINTO DIA UTIL");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,04 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        
        jBoletoBean.setAgencia("0278");
        jBoletoBean.setContaCorrente("92471");
        jBoletoBean.setDvContaCorrente("7");
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");

        jBoletoBean.setNossoNumero("00000001", 8);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento("1");
        jBoletoBean.setValorBoleto("20");

        
        String valor = jBoletoBean.getModulo11("3419166700000123451101234567880057123457000", 9);
        if(valor.equals("6")){
            System.out.println("Dv do codigo de barras esta correto");
        }else{
            System.out.println("Dv do codigo de barras NAO esta correto");
        }
        
        JBoleto jBoleto = new JBoleto();
        jBoleto.addBoleto(jBoletoBean, JBoleto.ITAU);      

        File file = new File("boletoItauTeste.pdf");
        
        try {
            jBoleto.writeToFile(file);
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(file.toURI());
        } catch (IOException ex) {
            Logger.getLogger(ExemploItau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}