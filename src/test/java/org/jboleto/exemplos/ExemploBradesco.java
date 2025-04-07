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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 * @author Fabio Souza
 */
class ExemploBradesco {

    public static void main(String args[]) {

        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento("09/01/2017");
        jBoletoBean.setDataProcessamento("09/01/2017");

        jBoletoBean.setCedente("PREVEMT MEDICINA DO TRABALHO");
        jBoletoBean.setCarteira("09");

        jBoletoBean.setNomeSacado("MURILO SOARES LIMA");
        jBoletoBean.setEnderecoSacado("RUA PORTUGAL, 205");
        jBoletoBean.setBairroSacado("VILA ROMA");
        jBoletoBean.setCidadeSacado("ITU");
        jBoletoBean.setUfSacado("SP");
        jBoletoBean.setCepSacado("13310-440");
        jBoletoBean.setCpfSacado("359.821.558-45");

        jBoletoBean.setLocalPagamento("Pag�vel preferencialmente na Rede Bradesco ou Bradesco Expresso");

        Vector descricoes = new Vector();
        descricoes.add("Teste");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("11/01/2017");
        Long nossoNumero = 15082006115L;
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);
        double valorBoleto = 662.23;
        double porcentagemMora = Double.parseDouble(System.getProperty("boleto.mora.juros.dia", "0.0012"));
        double valorMora = valorBoleto * porcentagemMora;
        double porcentagemMultaBoleto = Double.parseDouble(System.getProperty("boleto.porcentagem.multa", "0.0210"));
        if (porcentagemMultaBoleto < 1) {
            porcentagemMultaBoleto *= 100;
        }
        jBoletoBean.setInstrucao1("Ap�s o vencimento Mora dia R$ " + nf.format(valorMora));
        jBoletoBean.setInstrucao2("Ap�s o vencimento, multa de  " + nf.format(porcentagemMultaBoleto) + "%");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("Controle Participante: " + String.valueOf(nossoNumero));

        jBoletoBean.setAgencia("7509");
        jBoletoBean.setDvAgencia("4");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DS");

        jBoletoBean.setContaCorrente("4031445");
        jBoletoBean.setDvContaCorrente("8");

        jBoletoBean.setNossoNumero(String.valueOf(nossoNumero), 11);
        jBoletoBean.setNoDocumento("21084");
        jBoletoBean.setValorBoleto(String.valueOf(valorBoleto));

        JBoleto jBoleto = new JBoleto();

        try {
            jBoleto.addBoleto(jBoletoBean, JBoleto.BRADESCO);
            jBoleto.writeToFile("bradesco.pdf");
        } catch (IOException | DocumentException | ParseException ex) {
            Logger.getLogger(ExemploReal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
