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


package org.jboleto.bancos;

import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

/**
 * Classe responsavel em criar os campos do Banco Bradesco.
 * @author Fabio Souza
 */
public class Bradesco implements Banco {
    
    JBoletoBean boleto;
    
    @Override
    public String getNumero() {
        return "237";
    }
    
    public Bradesco(JBoletoBean boleto) {
        this.boleto = boleto;
    }

     public int getDacNossoNumero() {
        int dac;
        int resto;


        String campo =  String.valueOf(boleto.getCarteira()) + boleto.getNossoNumero();

        int multiplicador = 2;
        int multiplicacao;
        int soma_campo = 0;

         for (int i = campo.length(); i > 0; i--) {
             multiplicacao = Integer.parseInt(campo.substring(i-1, i)) * multiplicador;
            
             soma_campo = soma_campo + multiplicacao;

             multiplicador = ((multiplicador + 5) % 6) + 2;

         }

        resto = (soma_campo%11);
         if (resto != 0) {
             dac = 11 - resto;
         }else{
            dac = resto;
         }

       
        return dac;
    }
     
    private String getCampoLivre() {
        String campo = boleto.getAgencia() + boleto.getCarteira() + boleto.getNossoNumero() + getContaCorrenteFormatted()+ "0";        
        return campo;
    }
    
    private String getCampo1() {
        String campo = getNumero() + boleto.getMoeda() + getCampoLivre().substring(0,5);
        
        return boleto.getDigitoCampo(campo,2);
    }
    
    private String getCampo2() {
        String campo = getCampoLivre().substring(5,15);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo3() {
        String campo = getCampoLivre().substring(15,25);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo4() {
        String campo = getNumero() +
                boleto.getMoeda() +
                boleto.getFatorVencimento() +
                boleto.getValorTitulo() +
                boleto.getAgencia() +
                boleto.getCarteira() +
                boleto.getNossoNumero() +
                getContaCorrenteFormatted() + "0";
        
        return boleto.getDigitoCodigoBarras(campo);
    }
    
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    @Override
    public String getCodigoBarras() {
        String carteira = getCarteiraFormatted();
        String campo;
        if ("09".equals(carteira)) {
            campo = getNumero() + String.valueOf(boleto.getMoeda()) + getCampo4()
                    + boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre();
        } else {
            campo = getNumero() + String.valueOf(boleto.getMoeda()) + getCampo4()
                    + boleto.getFatorVencimento() + boleto.getValorTitulo() + boleto.getAgencia()
                    + boleto.getCarteira() + boleto.getNossoNumero() + boleto.getContaCorrente() + "0";
        }
        return campo;
    }
    
    @Override
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
    @Override
    public String getCarteiraFormatted() {         
        String carteira;
        String zeros = "00";
        
        int rest = 2 - boleto.getCarteira().length();
        if (rest <= 0) {
            carteira = boleto.getCarteira();
        } else {
            carteira = zeros.substring(0, rest) + boleto.getCarteira();
        }
        return carteira;
    }

    /**
     * Recupera a conta corrente no padrao especificado pelo banco
     * @author Cesário Lange
     */
    public String getContaCorrenteFormatted() {
        String zeros = "0000000";
        int rest = 7 - boleto.getContaCorrente().length();
        if (rest <= 0) {
           return  boleto.getContaCorrente();
        } else {
            return(zeros.substring(0, rest) + boleto.getContaCorrente());
        }        
    }

    
    /**
     * Recupera a agencia / codigo cedente no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getAgenciaCodCedenteFormatted() {
        if (boleto.getAgencia().equals("1724") && boleto.getContaCorrente().equals("5455")) {
            return boleto.getAgencia() + "-8" +" / " + "000" +boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
        } else {
            return boleto.getAgencia() + "-" + boleto.getDvAgencia() + "/" + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
        }
    }
    
    @Override
    public String getNossoNumeroFormatted() {
        if (getCarteiraFormatted().equals("09")) {
            if (getDacNossoNumero() == 10) {
                return (boleto.getCarteira() + "/" + boleto.getNossoNumero().substring(0, 11) + "-" + "P");
            } else {
                return (boleto.getCarteira() + "/" + boleto.getNossoNumero().substring(0, 11) + "-" + getDacNossoNumero());
            }
        } else {
            return String.valueOf(Integer.parseInt(boleto.getNossoNumero()));
        }
    }
}