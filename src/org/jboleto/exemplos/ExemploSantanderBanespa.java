/*
 * Esta biblioteca e um software livre, que pode ser redistribuido e/ou 
 * modificado sob os termos da Licença Publica Geral Reduzida GNU, 
 * conforme publicada pela Free Software Foundation, versao 2.1 da licenca.
 *
 * Esta biblioteca e distribuida na experanca de ser util aos seus usuarios, 
 * porem NAO TEM NENHUMA GARANTIA, EXPLICITAS OU IMPLICITAS, COMERCIAIS OU 
 * DE ATENDIMENTO A UMA DETERMINADA FINALIDADE. 
 * Veja a Licenca Publica Geral Reduzida GNU para maiores detalhes. 
 * A licenca se encontra no arquivo lgpl-br.txt 
 */
package org.jboleto.exemplos;

import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Mario Grigioni
 */
class ExemploSantanderBanespa {

    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento("27/08/2009");
        jBoletoBean.setDataProcessamento("27/08/2009");

        jBoletoBean.setNoDocumento("NRO_DCTO");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("COB");
        jBoletoBean.setCedente("AUTO GERAL DE ITU LTDA");
        jBoletoBean.setCarteira("102");

        jBoletoBean.setNomeSacado("Murilo de Moraes Tuvani");
        jBoletoBean.setEnderecoSacado("Rua Marechal Deodoro, 505");
        jBoletoBean.setBairroSacado("Chafariz");
        jBoletoBean.setCidadeSacado("Itu");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13300-110");
        //jBoletoBean.setCpfSacado("279.339.928-03");
        jBoletoBean.setCpfSacado("999.999.999-99");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO SANTANDER BANESPA");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO SANTANDER SANTANDER BANESPA");

        Vector descricoes = new Vector();
        descricoes.add("1 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("2 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("3 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("4 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("5 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("6 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("7 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        descricoes.add("8 NF 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99, 9999999-99");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("18/09/2009");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("065");

        jBoletoBean.setContaCorrente("48000041");
        jBoletoBean.setDvContaCorrente("4");

        //jBoletoBean.setCodCliente("1284053");
        jBoletoBean.setCodCliente("1284282");

        jBoletoBean.setNossoNumero("19", 13);
        jBoletoBean.setValorBoleto("10");

        jBoletoBean.setIOS("0");
        jBoletoBean.setCarteira("102");

        //jBoletoBean.setImagemMarketing("C:/Users/Murilo/Documents/sarg.png");

        String valor = jBoletoBean.getModulo11("0339204600000273719028203356661245780020102", 9);
        if(valor.equals("6")){
            System.out.println("Dv do codigo de barras esta correto");
        }else{
            System.out.println("Dv do codigo de barras NAO esta correto");
        }
        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.SANTANDER_BANESPA);
//        SantanderBanespa bancoBean = new SantanderBanespa(jBoletoBean);
//        System.out.println(bancoBean.getCodigoBarras());
        jBoleto.writeToFile("santanderBanespaComLogo.pdf");

    }
}
