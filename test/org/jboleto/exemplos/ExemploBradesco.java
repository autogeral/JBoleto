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
 * @author Fabio Souza
 */
class ExemploBradesco {

    public static void main(String args[]) {

        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("20/06/2011");
        jBoletoBean.setDataProcessamento("20/06/2011");

        jBoletoBean.setCedente("Bruna Cartuchos e Suprimentos para Impressoras Ltda");
        jBoletoBean.setCarteira("09");

        jBoletoBean.setNomeSacado("MURILO SOARES LIMA");
        jBoletoBean.setEnderecoSacado("RUA PORTUGAL, 205");
        jBoletoBean.setBairroSacado("VILA ROMA");
        jBoletoBean.setCidadeSacado("ITU");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13310-440");
        jBoletoBean.setCpfSacado("359.821.558-45");

        jBoletoBean.setLocalPagamento("Pagável Preferencialmente na rede Bradesco ou Banco Postal");

        Vector descricoes = new Vector();
        descricoes.add("Teste");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("20/07/2011");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("1724");
        jBoletoBean.setDvAgencia("8");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DM");

        jBoletoBean.setContaCorrente("5455");
        jBoletoBean.setDvContaCorrente("0");

        jBoletoBean.setNossoNumero("1", 11);
        jBoletoBean.setNoDocumento("1");
        jBoletoBean.setValorBoleto("1.00");

        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.BRADESCO);
        jBoleto.writeToFile("bradesco.pdf");

    }
}
