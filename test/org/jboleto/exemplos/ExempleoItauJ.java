/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jboleto.exemplos;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 *30/05/2011 10:10:35
 * @author talentos
 */
public class ExempleoItauJ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("01/01/2010");
        jBoletoBean.setDataProcessamento("01/01/2010");

        jBoletoBean.setCedente("J. Computação LTDA - ME");
        jBoletoBean.setCarteira("109");

        jBoletoBean.setNomeSacado("Patricia Alvarenga Gontijo de Souza");
        jBoletoBean.setEnderecoSacado("Av. Dom Pedro II, 348");
        jBoletoBean.setBairroSacado("Centro");
        jBoletoBean.setCidadeSacado("Salto");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13320-240");
        jBoletoBean.setCpfSacado("049.747.666-51");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologa��o");

        jBoletoBean.setDescricoes(descricoes);

//        jBoletoBean.setImagemMarketing("/home/fabio/template_logo.png");

        jBoletoBean.setDataVencimento("31/08/2010");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("0278");
        jBoletoBean.setContaCorrente("84469");
        jBoletoBean.setDvContaCorrente("1");
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");

        jBoletoBean.setNossoNumero("1", 8);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento("0000001");
        jBoletoBean.setValorBoleto("2.00");

        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.ITAU);
        try {
            jBoleto.writeToFile("itauJ.pdf");
        } catch (IOException ex) {
            Logger.getLogger(ExempleoItauJ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
