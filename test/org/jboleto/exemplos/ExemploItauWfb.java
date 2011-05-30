/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jboleto.exemplos;

import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 *30/05/2011 10:10:04
 * @author talentos
 */
public class ExemploItauWfb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExemploItauWfb e = new ExemploItauWfb();
//        e.criaBoleto(dataDocumento, dataProcessamento, dataVencimento
//                , cedente, carteira, sacadoNome, sacadoEndereco
//                , sacadoBairro, sacadoCidade, sacadoUf, sacadoCep, sacadoCpfCnpj
//                , agencia, conta, dvConta, carteira, nossoNumero, numeroDocumento
//                , valor);
        JBoleto jBoleto = new JBoleto();
        JBoletoBean boleto = new JBoletoBean();

         boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "Patricia", "Av. Dom Pedro"
                , "Centro", "Salto", "SP", "13320-240", "049.747.666-51"
                , "0278", "90749", "8", "00000001", "1"
                , "5.00");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);


        boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/06/2011"
                , "J. Computação LTDA - ME", "109", "Patricia", "Av. Dom Pedro"
                , "Centro", "Salto", "SP", "13320-240", "049.747.666-51"
                , "0278", "84469", "1", "00000001", "1"
                , "5.00");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

           boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/06/2011"
                , "GERAL CAR P C I E LTDA", "109", "Patricia", "Av. Dom Pedro"
                , "Centro", "Salto", "SP", "13320-240", "049.747.666-51"
                , "0278", "91002", "1", "00000001", "1"
                , "5.00");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        jBoleto.writeToFile("itau.pdf");

    }

    private JBoletoBean criaBoleto(String dataDocumento, String dataProcessamento, String dataVencimento
                , String cedente, String carteira, String sacadoNome, String sacadoEndereco
                , String sacadoBairro, String sacadoCidade, String sacadoUf, String sacadoCep, String sacadoCpfCnpj
                , String agencia, String conta, String dvConta, String nossoNumero, String numeroDocumento
                , String valor) {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento(dataDocumento);
        jBoletoBean.setDataProcessamento(dataProcessamento);

        jBoletoBean.setCedente(cedente);
        jBoletoBean.setCarteira(carteira);

        jBoletoBean.setNomeSacado(sacadoNome);
        jBoletoBean.setEnderecoSacado(sacadoEndereco);
        jBoletoBean.setBairroSacado(sacadoBairro);
        jBoletoBean.setCidadeSacado(sacadoCidade);
        jBoletoBean.setUfSacado(sacadoUf);
        jBoletoBean.setCepSacado(sacadoCep);
        jBoletoBean.setCpfSacado(sacadoCpfCnpj);

        jBoletoBean.setLocalPagamento("Pagável em qualquer banco até o vencimento");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologação");

        jBoletoBean.setDescricoes(descricoes);

//        jBoletoBean.setImagemMarketing("/home/fabio/template_logo.png");

        jBoletoBean.setDataVencimento(dataVencimento);
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia(agencia);
        jBoletoBean.setContaCorrente(conta);
        jBoletoBean.setDvContaCorrente(dvConta);
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DM");

        jBoletoBean.setNossoNumero(nossoNumero);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento(numeroDocumento);
        jBoletoBean.setValorBoleto(valor);
        return jBoletoBean;
    }

}
