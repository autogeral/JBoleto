/*
 * Esta biblioteca e um software livre, que pode ser redistribuido e/ou 
 * modificado sob os termos da LicenÃ§a Publica Geral Reduzida GNU, 
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
 * @author Fabio Souza
 */
class ExemploItau {

    public static void main(String args[]) {
        testaJ();
        testaPatricia();
    }

    private static void testaJ() {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("01/01/2010");
        jBoletoBean.setDataProcessamento("01/01/2010");

        jBoletoBean.setCedente("J. Computação LTDA - ME");
        jBoletoBean.setCarteira("198");

        jBoletoBean.setNomeSacado("Patricia Alvarenga Gontijo de Souza");
        jBoletoBean.setEnderecoSacado("Av. Dom Pedro II, 348");
        jBoletoBean.setBairroSacado("Centro");
        jBoletoBean.setCidadeSacado("Salto");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13320-240");
        jBoletoBean.setCpfSacado("049.747.666-51");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologação");

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
        jBoleto.writeToFile("itauJ.pdf");
    }

    private static void testaPatricia() {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("01/01/2010");
        jBoletoBean.setDataProcessamento("01/01/2010");

        jBoletoBean.setCedente("Patricia Alvarenga Gontijo de Souza");
        jBoletoBean.setCarteira("198");

        jBoletoBean.setNomeSacado("Murilo de Moraes Tuvani");
        jBoletoBean.setEnderecoSacado("Rua Marechal Deodoro, 505 ap 132");
        jBoletoBean.setBairroSacado("Chafariz");
        jBoletoBean.setCidadeSacado("Itu");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13300-110");
        jBoletoBean.setCpfSacado("279.339.928-03");

        Vector<String> descricoes = new Vector<String>();
        descricoes.add("Boleto de teste para homologação");

        jBoletoBean.setDescricoes(descricoes);

//        jBoletoBean.setImagemMarketing("/home/fabio/template_logo.png");

        jBoletoBean.setDataVencimento("31/08/2010");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("8162");
        jBoletoBean.setContaCorrente("02872");
        jBoletoBean.setDvContaCorrente("7");
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");

        jBoletoBean.setNossoNumero("1", 8);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento("0000001");
        jBoletoBean.setValorBoleto("2.00");

        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.ITAU);
        jBoleto.writeToFile("itauPatricia.pdf");
    }
}
