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

import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Mario Grigioni
 */
class ExemploSantander {

    public static void main(String args[]) {

        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("01/08/2001");
        jBoletoBean.setDataProcessamento("01/08/2001");


        jBoletoBean.setCedente("AUTO GERAL DE ITU LTDA");
        jBoletoBean.setCarteira("06");

        jBoletoBean.setNomeSacado("GtTurbo");
        jBoletoBean.setEnderecoSacado("Rua Araticum 951");
        jBoletoBean.setBairroSacado("Anil");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22753-501");
        jBoletoBean.setCpfSacado("87524988753");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO SANTANDER");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO SANTANDER");

        Vector descricoes = new Vector();
        descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("01/08/2001");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("148");
        jBoletoBean.setDvAgencia("13");

        jBoletoBean.setContaCorrente("02647");
        jBoletoBean.setDvContaCorrente("8");

        jBoletoBean.setCodCliente("14813026478");

        jBoletoBean.setNossoNumero("0004952", 7);
        jBoletoBean.setValorBoleto("103.58");

        jBoletoBean.setCarteira("COB");

        JBoleto jBoleto = new JBoleto();

        try {
            jBoleto.addBoleto(jBoletoBean, JBoleto.SANTANDER);
            jBoleto.writeToFile("santander.pdf");
        } catch (IOException | DocumentException | ParseException ex) {
            Logger.getLogger(ExemploSantander.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
