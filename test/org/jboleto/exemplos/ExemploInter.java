package org.jboleto.exemplos;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 *
 * @author larissa.camargo
 *     06.08.2021
 */

public class ExemploInter {

    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento("06/08/2021");
        jBoletoBean.setDataProcessamento("06/08/2021");

        jBoletoBean.setNoDocumento("10367547753");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("99");
        jBoletoBean.setCedente("05.437.537/0004 - AUTO GERAL DE ITU LTDA");
        jBoletoBean.setCarteira("112");

        jBoletoBean.setNomeSacado("Larissa Antunes de Camargo");
        jBoletoBean.setEnderecoSacado("Rua San Marino, 505");
        jBoletoBean.setBairroSacado("Jd Saltense");
        jBoletoBean.setCidadeSacado("Salto");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13300-110");
        jBoletoBean.setCpfSacado("500.368.694.54");

        jBoletoBean.setLocalPagamento("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO");

        Vector descricoes = new Vector();
        descricoes.add("Boleto referente a teste");

        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("31/08/2021");
        jBoletoBean.setInstrucao1("Não receber após o vencimento.");
        jBoletoBean.setInstrucao2("Ouvidoria: 0800 940 7772/ SAC - Deficiente de Fala e Audição 0800 979 70 99");

        jBoletoBean.setAgencia("0001-9");

        jBoletoBean.setContaCorrente("48000041");
        jBoletoBean.setDvContaCorrente("4");

        jBoletoBean.setCodCliente("1284282");

        jBoletoBean.setNossoNumero("0", 13);
        jBoletoBean.setValorBoleto("30");

        jBoletoBean.setIOS("0");
        jBoletoBean.setCarteira("112");

        String valor = jBoletoBean.getModulo11("0339204600000273719028203356661245780020102", 9);
        if (valor.equals("6")) {
            System.out.println("Dv do codigo de barras esta correto");
        } else {
            System.out.println("Dv do codigo de barras NAO esta correto");
        }
        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.INTER);


        File file = new File("boletoBancoInter.pdf");

        try {
            jBoleto.writeToFile(file);
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(file.toURI());
        } catch (IOException ex) {
            Logger.getLogger(ExemploSantanderBanespa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
