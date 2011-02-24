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
 * Classe responsavel em criar os campos do Sicredi
 * @author Cleiton Rezende
 */
public class Sicredi implements Banco {

    JBoletoBean boleto;

    @Override
    public String getNumero() {
        return "748";
    }

    public Sicredi(JBoletoBean boleto) {
        this.boleto = boleto;

        System.out.println(getCampoLivre());
    }

    private String getCampo1() {
        String campo = getNumero() +
                boleto.getMoeda() +
                getCampoLivre().substring(0, 5);

        return boleto.getDigitoCampo(campo, 2);
    }

    private String getCampo2() {
        String campo = getCampoLivre().substring(5, 15);

        return boleto.getDigitoCampo(campo, 1);
    }

    private String getCampo3() {
        String campo = getCampoLivre().substring(15, 25);

        return boleto.getDigitoCampo(campo, 1);
    }

    private String getCampo4() {
        String campo = getNumero() +
                String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() +
                boleto.getValorTitulo() +
                getCampoLivre();

        return boleto.getDigitoCodigoBarras(campo);
    }

    private String getCampo5() {
        String campo = boleto.getFatorVencimento() +
                boleto.getValorTitulo();
        return campo;
    }

    public String getCodigoBarras() {
        String campo1 = getNumero();
        String campo2 = String.valueOf(boleto.getMoeda());
        String campo3 = getCampo4();
        String campo4 = String.valueOf(boleto.getFatorVencimento());
        String campo5 = boleto.getValorTitulo();
        String campo6 = getCampoLivre();

        String campo = campo1 + campo2 + campo3 + campo4 + campo5 + campo6;
        return campo;
    }

    public String getLinhaDigitavel() {
        String campo = "";
        String campo1 = getCampo1();
        String campo2 = getCampo2();
        String campo3 = getCampo3();
        String campo4 = getCampo4();
        String campo5 = getCampo5();
        campo = campo1.substring(0, 5) + "." +
                campo1.substring(5) + "  " +
                campo2.substring(0, 5) + "." +
                campo2.substring(5) + "  " +
                campo3.substring(0, 5) + "." +
                campo3.substring(5) + "  " +
                campo4 + "  " + campo5;
        return campo;
    }

    public String getCampoLivre() {
        String retorno = "31" + boleto.getNossoNumero() + boleto.completaZerosEsquerda(boleto.getAgencia(), 4) + "05" + boleto.getCedente() + "10";
        retorno = retorno + getDigitoCampoLivre(retorno, 9);
        return retorno;
    }

    // Esta função tive que mudar por este motivo vriei outra com outro nome
    // mas acho teria que ir para a classe JBoletoBean
    public String getDigitoCampoLivre(String campo, int peso) {
        //Esta rotina fa o calcula no modulo 11 - 23456789

        int multiplicador = peso;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i, 1 + i)) * multiplicador;

            soma_campo = soma_campo + multiplicacao;

            if (multiplicador == 4) {
                multiplicador = 3;
            } else if (multiplicador == 3) {
                multiplicador = 2;
            } else if (multiplicador == 2) {
                multiplicador = 9;
            } else if (multiplicador == 9) {
                multiplicador = 8;
            } else if (multiplicador == 8) {
                multiplicador = 7;
            } else if (multiplicador == 7) {
                multiplicador = 6;
            } else if (multiplicador == 6) {
                multiplicador = 5;
            } else if (multiplicador == 5) {
                multiplicador = 4;
            }
        }

        int dac = (soma_campo % 11);
        if (dac <= 1) {
            dac = 0;
        } else {
            dac = 11 - dac;
            if (dac == 0 || dac == 1 || dac == 10 || dac == 11) {
                dac = 1;
            }

        }

        campo = String.valueOf(dac);

        return campo;
    }

    @Override
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }

    @Override
    public String getAgenciaCodCedenteFormatted() {
        return boleto.getAgencia();
    }

    @Override
    public String getNossoNumeroFormatted() {
        return boleto.getNossoNumero();
    }
}