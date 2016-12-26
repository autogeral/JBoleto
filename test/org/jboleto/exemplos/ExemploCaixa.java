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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;


/**
 * @author Fabio Souza
 */
class ExemploCaixa {
    
    public static void main(String args[]) {
        JBoletoBean jBoletoBean = new JBoletoBean();
        
        jBoletoBean.setDataDocumento("31/05/2007");
        jBoletoBean.setDataProcessamento("31/05/2007");
        
        jBoletoBean.setCedente("KOBI SYSTEM LTDA ME");
        
        jBoletoBean.setNomeSacado("Teste");
        jBoletoBean.setEnderecoSacado("Rua teste");
        jBoletoBean.setBairroSacado("XXXX");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22753-501");
        jBoletoBean.setCpfSacado("87524988753");
        jBoletoBean.setCarteira("57");
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NA CAIXA ECONOMICA");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NA CAIXA ECONOMICA");
        
        Vector descricoes = new Vector();
        descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);
        
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("Inclusao de mais uma linha");
        jBoletoBean.setInstrucao4("");
        
        jBoletoBean.setAgencia("0155");
        jBoletoBean.setContaCorrente("13877");
        jBoletoBean.setDvContaCorrente("4");
        
        jBoletoBean.setCarteira("80"); //pode ser 80 ou 81 ou 82 (Confirmar com gerente)
        jBoletoBean.setCodigoOperacao("870");
        jBoletoBean.setCodigoFornecidoAgencia("00000324");
        
        jBoletoBean.setNossoNumero("19525086",8);
        jBoletoBean.setNoDocumento("987656123");
        
        jBoletoBean.setValorBoleto("2952.95");
        jBoletoBean.setDataVencimento("02/10/2007");
        
        JBoleto jBoleto = new JBoleto();
        jBoleto.addBoleto(jBoletoBean,JBoleto.CAIXA_ECONOMICA);
        
        try {
            jBoleto.writeToFile("caixa.pdf");
        } catch (IOException ex) {
            Logger.getLogger(ExemploCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
