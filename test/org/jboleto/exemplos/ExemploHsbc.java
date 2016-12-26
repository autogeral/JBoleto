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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Fabio Souza
 */
class ExemploHsbc {

    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("04/07/2008");
        jBoletoBean.setDataProcessamento("04/07/2008");

        jBoletoBean.setCedente("J. Computacao");

        jBoletoBean.setNomeSacado("Murilo de Moraes Tuvani");
        jBoletoBean.setEnderecoSacado("Rua Marechal Deodoro, 505");
        jBoletoBean.setBairroSacado("Centro");
        jBoletoBean.setCidadeSacado("Itu");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13300-110");
        jBoletoBean.setCpfSacado("999.999.999-99");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO UNIBANCO");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO UNIBANCO");

        List<String> descricoes = new ArrayList<String>();
        descricoes.add("Sistema - R$ 1200,00");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setCarteira("CNR");
        jBoletoBean.setDataVencimento("04/07/2008");

        //não obrigatorio para calculos do boleto
        jBoletoBean.setAgencia("0000");
        jBoletoBean.setContaCorrente("000000");
        jBoletoBean.setDvContaCorrente("0");

        //jBoletoBean.setNossoNumero("239104761", 13);
        jBoletoBean.setNoDocumento("239104761");
        jBoletoBean.setValorBoleto("1200.00");

        //código do cliente fornecido pelo hsbc
        jBoletoBean.setCodCliente("8351202");
        //3999392300001200008351202000023910476118682
        //String simulado = "3999392300001200008351202000023910476118682";
        //System.out.println("DV: "+jBoletoBean.getDigitoCodigoBarras(simulado));

        JBoleto jBoleto = new JBoleto();


        jBoleto.addBoleto(jBoletoBean, JBoleto.HSBC);
        try {
            jBoleto.writeToFile("hsbc.pdf");
        } catch (IOException ex) {
            Logger.getLogger(ExemploHsbc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
