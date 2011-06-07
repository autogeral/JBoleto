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
 * Classe responsavel em criar os campos do Banco Itaú.
 * @author Fabio Souza
 */
public class Itau implements Banco {
    
    JBoletoBean boleto;
    
    @Override
    public String getNumero() {
        return "341";
    }
    
    public Itau(JBoletoBean boleto) {
        this.boleto = boleto;
    }
    
    public int getDacNossoNumero() {
        int dac = 0;

        
        String campo =  completaAgencia() + String.valueOf(boleto.getAgencia()) + boleto.getContaCorrente() + String.valueOf(boleto.getCarteira()) + boleto.getNossoNumero();
        
        int multiplicador = 1;
        int multiplicacao = 0;
        int soma_campo = 0;
        
        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i,1+i)) * multiplicador;
            
            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0,1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1));
            }
            soma_campo = soma_campo + multiplicacao;
            
            if (multiplicador == 2)
                multiplicador = 1;
            else
                multiplicador = 2;
            
        }
        
        dac = 10 - (soma_campo%10);
        
        if (dac == 10)
            dac = 0;
        
        return dac;
    }    
    
    private String getCampo1() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + String.valueOf(boleto.getCarteira()) + boleto.getNossoNumero().substring(0,2);
        return boleto.getDigitoCampo(campo,2);
    }
    
    private String getCampo2() {
        String campo = boleto.getNossoNumero().substring(2) + String.valueOf(getDacNossoNumero()) + String.valueOf(boleto.getAgencia()).substring(0,3);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo2_198() {
        String campo = boleto.getNossoNumero().substring(2) +
                boleto.getNoDocumento().substring(0,4);
        return boleto.getDigitoCampo(campo,1);
    }

    private String getCampo2_157() {
        String campo = "";
        if (boleto.getAgencia().length() < 4) {
            campo = boleto.getNossoNumero().substring(2) + String.valueOf(getDacNossoNumero()) + completaAgencia() +String.valueOf(boleto.getAgencia()).substring(0, 2);
        } else {
            campo = boleto.getNossoNumero().substring(2) + String.valueOf(getDacNossoNumero()) + String.valueOf(boleto.getAgencia()).substring(0, 3);
        }

        return boleto.getDigitoCampo(campo, 1);
    }

    private String getCampo3() {
        String campo = String.valueOf(boleto.getAgencia()).substring(3) + boleto.getContaCorrente() + boleto.getDvContaCorrente() + "000";
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo3_198() {
        String campo = boleto.getNoDocumento().substring(4) +
                boleto.getCodCliente();
        String cdac = getCarteiraFormatted()+boleto.getNossoNumero()+
                boleto.getNoDocumento()+boleto.getCodCliente();
        campo += boleto.getModulo10(cdac);
        campo += "0";
        return boleto.getDigitoCampo(campo,1);
    }

    private String getCampo3_157() {
        String campo = "";
        if (boleto.getAgencia().length() < 4) {
            campo = String.valueOf(boleto.getAgencia()).substring(2) + boleto.getContaCorrente() + boleto.getDvContaCorrente() + "000";
        } else {
             campo = String.valueOf(boleto.getAgencia()).substring(3) + boleto.getContaCorrente() + boleto.getDvContaCorrente() + "000";
        }

        return boleto.getDigitoCampo(campo, 1);
    }

    private String getCampo4() {
        String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + String.valueOf(boleto.getCarteira()) +
                String.valueOf(boleto.getNossoNumero()) + getDacNossoNumero() +
                String.valueOf(boleto.getAgencia()) + boleto.getContaCorrente() + boleto.getDvContaCorrente() + "000";
        
        return boleto.getDigitoCodigoBarras(campo);
    }

    private String getCampo4_198() {
        String codigo = getNumero() + String.valueOf(boleto.getMoeda());
        String vencimentoValor = boleto.getFatorVencimento() + boleto.getValorTitulo();
        String numeros = boleto.getCarteira() + boleto.getNossoNumero()
                + boleto.getNoDocumento() + boleto.getCodCliente();
        String codigoBarras = codigo + vencimentoValor
                + boleto.getDigitoCampo(numeros) + "0";
        return boleto.getDigitoCodigoBarras(codigoBarras);
    }
    
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    @Override
    public String getCodigoBarras() {
        String carteira = getCarteiraFormatted();
        if ("198".equals(carteira)
                || "107".equals(carteira)
                || "122".equals(carteira)
                || "142".equals(carteira)
                || "143".equals(carteira)
                || "196".equals(carteira)) {
            String codigo = getNumero() + String.valueOf(boleto.getMoeda());
            assert (codigo.length() == 4);
            String vencimentoValor = boleto.getFatorVencimento() + boleto.getValorTitulo();
            assert (vencimentoValor.length() == 14);
            String numeros = boleto.getCarteira() + boleto.getNossoNumero()
                    + boleto.getNoDocumento() + boleto.getCodCliente();
            assert (numeros.length() == 23);
            String codigoBarras = codigo + getCampo4_198() + vencimentoValor
                    + boleto.getDigitoCampo(numeros) + "0";
            return codigoBarras;
        } else {
            return getNumero() + String.valueOf(boleto.getMoeda())
                    + String.valueOf(getCampo4())
                    + String.valueOf(getCampo5())
                    + String.valueOf(boleto.getCarteira())
                    + String.valueOf(boleto.getNossoNumero())
                    + String.valueOf(getDacNossoNumero())
                    + String.valueOf(completaAgencia() + boleto.getAgencia())
                    + boleto.getContaCorrente()
                    + boleto.getDvContaCorrente() + "000";

        }

    }
    
    @Override
    public String getLinhaDigitavel() {
        String carteira = getCarteiraFormatted();
        if("198".equals(carteira)
                || "107".equals(carteira)
                || "122".equals(carteira)
                || "142".equals(carteira)
                || "143".equals(carteira)
                || "196".equals(carteira)){
            String campo = getCampo1();
            StringBuilder sb = new StringBuilder();
            sb.append(campo.substring(0, 5));
            sb.append(".");
            sb.append(campo.substring(5));
            sb.append(" ");

            campo = getCampo2_198();
            sb.append(campo.substring(0, 5));
            sb.append(".");
            sb.append(campo.substring(5));
            sb.append(" ");

            campo = getCampo3_198();
            sb.append(campo.substring(0, 5));
            sb.append(".");
            sb.append(campo.substring(5));
            sb.append(" ");

            sb.append(getCampo4_198());
            sb.append(" ");
            sb.append(getCampo5());
            return sb.toString();
        } else {
            if ("157".equals(carteira) || "109".equals(carteira)) {
                String campo = getCampo1();
                StringBuilder sb = new StringBuilder();
                sb.append(campo.substring(0, 5));
                sb.append(".");
                sb.append(campo.substring(5));
                sb.append(" ");

                campo = getCampo2_157();
                sb.append(campo.substring(0, 5));
                sb.append(".");
                sb.append(campo.substring(5));
                sb.append(" ");

                campo = getCampo3_157();
                sb.append(campo.substring(0, 5));
                sb.append(".");
                sb.append(campo.substring(5));
                sb.append(" ");

                sb.append(getCampo4());
                sb.append(" ");
                sb.append(getCampo5());
                return sb.toString();
            }
            return getCampo1().substring(0, 5) + "." + getCampo1().substring(5) + "  "
                    + getCampo2().substring(0, 5) + "." + getCampo2().substring(5) + "  "
                    + getCampo3().substring(0, 5) + "." + getCampo3().substring(5) + "  "
                    + getCampo4() + "  " + getCampo5();
        }
        
    }
    
    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }
    
    /**
     * Recupera a agencia / codigo cedente no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getAgenciaCodCedenteFormatted() {
        return completaAgencia() + boleto.getAgencia() + "/" + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
    }

    /**
     * Completa o n° da Agencia se estiver imcompleto (Ex: vem do banco 258 e cod. correto 0258)
     */
     public String completaAgencia(){
        String zeros = "000";
        String adicionar = "";
        int qtdDigitos = 4;
        int resto = qtdDigitos - boleto.getAgencia().length();
        adicionar = zeros.substring(0, resto);
        return adicionar;
    }
    
    /**
     * Recupera o nossoNumero no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getNossoNumeroFormatted() {
        return (boleto.getCarteira() + "/" + boleto.getNossoNumero().substring(0, 8) + "-" + getDacNossoNumero());
    }
    
}