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

import java.io.File;
import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Fabio Souza
 */
class ExemploBancoBrasilEX7 {

    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("06/05/2011");
        jBoletoBean.setDataProcessamento("06/05/2011");

        jBoletoBean.setCedente("EX7 CARTUCHOS LTDA");
        jBoletoBean.setCarteira("18");
        jBoletoBean.setMoeda("9");

        jBoletoBean.setNomeSacado("MURILO SOARES LIMA");
        jBoletoBean.setEnderecoSacado("RUA PORTUGAL, 205");
        jBoletoBean.setBairroSacado("VILA ROMA");
        jBoletoBean.setCidadeSacado("ITU");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13310-440");
        jBoletoBean.setCpfSacado("359.821.558-45");

        jBoletoBean.setLocalPagamento("Pagável em qualquer banco até o vencimento");
//        jBoletoBean.setLocalPagamento2("Pagável em qualquer banco até o vencimento");

        Vector descricoes = new Vector();
        descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);

        File f = new File("template_bb.png");
        if (f.exists()) {
            jBoletoBean.setImagemMarketing(f.getAbsolutePath());
        }

        jBoletoBean.setDataVencimento("06/06/2011");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");
        jBoletoBean.setEspecieDocumento("DM");
        jBoletoBean.setAceite("S");

        jBoletoBean.setAgencia("0354");
        jBoletoBean.setDvAgencia("9");
        jBoletoBean.setContaCorrente("42905"); //completar com zeros quando necessario
        jBoletoBean.setDvContaCorrente("8");
        jBoletoBean.setNoDocumento("77778");

        jBoletoBean.setNumConvenio("1394369");
        jBoletoBean.setNossoNumero("2", 10);
        jBoletoBean.setValorBoleto("5.00");

        JBoleto jBoleto = new JBoleto();

        jBoleto.addBoleto(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
        jBoleto.writeToFile("banco_brasil.pdf");

    }
}
