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
 * Classe responsavel em criar os campos do Banco ItaÃº.
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
        String campo = boleto.getNossoNumero().substring(2) + 
                String.valueOf(getDacNossoNumero()) + 
                String.valueOf(boleto.getAgencia()).substring(0,3);
        
        return boleto.getDigitoCampo(campo,1);
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

    private String getCampo4() {
        String campo = 	getCodigoBarrasSemDac();
        return boleto.getDigitoCodigoBarras(campo);
    }
    
    private String getCodigoBarrasSemDac() {
        return getNumero() + String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + String.valueOf(boleto.getCarteira()) +
                String.valueOf(boleto.getNossoNumero()) + getDacNossoNumero() + completaAgencia() +
                String.valueOf(boleto.getAgencia()) + boleto.getContaCorrente() + boleto.getDvContaCorrente() + "000";
    }
    
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    /**
     * POSIÇÃO   TAMANHO  PICTURE   CONTEÚDO
     * 01 a 03    03      9(03)     Código do Banco na Câmara de Compensação = '341' 
     * 04 a 04    01      9(01)     Código da Moeda = '9'
     * 05 a 05    01      9(01)     DAC código de Barras (Anexo 2) 
     * 06 a 09    04      9(04)     Fator de Vencimento (Anexo 6)
     * 10 a 19    10      9(08)V(2) Valor 
     * 20 a 22    03      9(03)     Carteira
     * 23 a 30    08      9(08)     Nosso Número 
     * 31 a 31    01      9(01)     DAC [Agência /Conta/Carteira/Nosso Número] (Anexo 4)
     * 32 a 35    04      9(04)     N.º da Agência cedente 
     * 36 a 40    05      9(05)     N.º da Conta Corrente
     * 41 a 41    01      9(01)     DAC [Agência/Conta Corrente] (Anexo 3)
     * 42 a 44 03 9(03) Zeros
     * @return 
     */
    @Override
    public String getCodigoBarras() {
        String codigo = getCodigoBarrasSemDac();
        String dac = boleto.getDigitoCodigoBarras(codigo);
        System.out.println(codigo+" DAC "+dac);
        codigo = codigo.substring(0, 4) + dac + codigo.substring(4);
        System.out.println("Resultado : "+codigo);
        return codigo;
    }
    
    @Override
    public String getLinhaDigitavel() {
        String campo = getCampo1();
        StringBuilder sb = new StringBuilder();
        sb.append(campo.substring(0, 5));
        sb.append(".");
        sb.append(campo.substring(5));
        sb.append(" ");

        campo = getCampo2();
        sb.append(campo.substring(0, 5));
        sb.append(".");
        sb.append(campo.substring(5));
        sb.append(" ");

        campo = getCampo3();
        sb.append(campo.substring(0, 5));
        sb.append(".");
        sb.append(campo.substring(5));
        sb.append(" ");

        sb.append(getCampo4());
        sb.append(" ");
        sb.append(getCampo5());
        campo = sb.toString();
        System.out.println("Linha Digitavel "+campo);
        return campo;
        
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
     * Completa o nÂ° da Agencia se estiver imcompleto (Ex: vem do banco 258 e cod. correto 0258)
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