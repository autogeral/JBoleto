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


package org.jboleto.bancos;

import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

/**
 * Classe responsavel em criar os campos do Banco do Brasil
 * @author Fabio Souza
 */
public class BancoBrasil implements Banco {
    
    JBoletoBean boleto;
    
    @Override
    public String getNumero() {
        return "001";
    }
    
    public BancoBrasil(JBoletoBean boleto) {
        this.boleto = boleto;
    }
    
    private String getCampoLivre() {
        String campo = "000000" + boleto.getNumConvenio() + boleto.getNossoNumero() + boleto.getCarteira();
        return campo;
    }
    
    private String getCampo1() {
        String campo = getNumero() + boleto.getMoeda() + getCampoLivre().substring(0,5);
        
        return boleto.getDigitoCampo(campo,2);
    }
    
    private String getCampo2() {
        String campo = getCampoLivre().substring(5,15);// + boleto.getAgencia();
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo3() {
        String campo = getCampoLivre().substring(15);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo4() {
        String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre();
        
        return boleto.getDigitoCodigoBarras(campo);
    }
    
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    public String getCodigoBarras() {
        String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) + getCampo4() +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre();
        
        return campo;
    }
    
    public String getLinhaDigitavel() {
        return 	getCampo1().substring(0,5) + "." + getCampo1().substring(5) + "  " +
                getCampo2().substring(0,5) + "." + getCampo2().substring(5) + "  " +
                getCampo3().substring(0,5) + "." + getCampo3().substring(5) + "  " +
                getCampo4() + "  " + getCampo5();
    }
    
    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }
    
    /**
     * Recupera a agencia / codigo cedente no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getAgenciaCodCedenteFormatted() {
       String zeros = "000";
       String adicionar = "";
       int qtdDigitos = 4;
       int resto = qtdDigitos - boleto.getAgencia().length();
       adicionar = zeros.substring(0, resto);
        if (boleto.getDvAgencia() == null || boleto.getDvAgencia().isEmpty()) {
            if (boleto.getAgencia().equals("354")) {
                boleto.setDvAgencia("9");
            }
        }
       
       /*O dígito da agência está sendo colocado a mão por que no cadastro de agências não se pede o dígito
       e para não ter que atualizar todas as agências do banco do Brasil sendo que somente a agencia 354,
       está sendo utilizada, foi feito o código acima. O ideal é possibilitar o cadastro completo da agência
       cadastrando o número e o dígito (Ex: 354-9)*/

        return adicionar + boleto.getAgencia() + "-" +  boleto.getDvAgencia() + " / " + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
    }
    
    /**
     * Recupera o nossoNumero no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getNossoNumeroFormatted() {
        return String.valueOf(Long.parseLong(boleto.getNumConvenio() + boleto.getNossoNumero()));
    }
    
}