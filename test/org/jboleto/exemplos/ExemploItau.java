
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
        jBoletoBean.setDataDocumento("23/08/2021");
        jBoletoBean.setDataProcessamento("23/08/2021");

        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DM");
        jBoletoBean.setCedente("AUTO GERAL DE ITU LTDA");
        jBoletoBean.setCarteira("109");

        jBoletoBean.setNomeSacado("Larissa Antunes de Camargo");
        jBoletoBean.setEnderecoSacado("Rua San Marino, 323");
        jBoletoBean.setBairroSacado("Jd Saltense");
        jBoletoBean.setCidadeSacado("Salto");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13327-271");
        jBoletoBean.setCpfSacado("500.683.354-64");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO ITAÚ");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO ITAÚ");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologação");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("01/09/2021");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        
        jBoletoBean.setAgencia("0057");
        jBoletoBean.setContaCorrente("12345");
        jBoletoBean.setDvContaCorrente("7");
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");

        jBoletoBean.setNossoNumero("12345678", 8);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento("0000001");
        jBoletoBean.setValorBoleto("123.45");

        
        String valor = jBoletoBean.getModulo11("3419204600000273719028203356661245780020102", 2);
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