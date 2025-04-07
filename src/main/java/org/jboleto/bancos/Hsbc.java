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

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

/**
 * Classe para gerar o boleto do unibanco
 * @author Fabio Souza
 */
public class Hsbc implements Banco {

    JBoletoBean boleto;

    /**
     * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
     */
    @Override
    public String getNumero() {
        return "399";
    }

    public String getCnr() {
        return "2";
    }

    public String getApp() {
        return "2";
    }

    public String getDataJuliano() {
        int juliano = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date vencimento = sdf.parse(boleto.getDataVencimento());
            Calendar cal = Calendar.getInstance();
            cal.setTime(vencimento);
            juliano = cal.get(Calendar.DAY_OF_YEAR)*10;
            juliano += cal.get(Calendar.YEAR)%10;
        } catch (ParseException ex) {
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(4);
        nf.setMaximumIntegerDigits(4);
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(false);
        String dataJuliana = nf.format(juliano);
        return dataJuliana;
    }

    /**
     * MÃ©todo particular do hsbc
     */
    public String getCampoLivre() {
        return getNumero() + boleto.getMoeda() + getCampo4() +
                boleto.getFatorVencimento() + boleto.getValorTitulo() +
                boleto.getCodCliente() + getNossoNumeroFormatted() +
                getDataJuliano() + getApp();
    }

    /**
     * Classe construtura, recebe como parametro a classe jboletobean
     */
    public Hsbc(JBoletoBean boleto) {
        this.boleto = boleto;
    }

    /**
     * Metodo que monta o primeiro campo do codigo de barras
     * Este campo como os demais e feito a partir do da documentacao do banco
     * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
     * Ã© sÃ³ concatenar os campos como no exemplo abaixo.
     */
    private String getCampo1() {
        String campo = getNumero() + boleto.getMoeda() + boleto.getCodCliente().substring(0, 5);

        return boleto.getDigitoCampo(campo, 2);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo2() {
        String campo = boleto.getCodCliente().substring(5) + getNossoNumeroFormatted().substring(0, 8);

        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo3() {
        String campo = getNossoNumeroFormatted().substring(8) +
                getDataJuliano() + getApp();
        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo4() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() + boleto.getValorTitulo() +
                boleto.getCodCliente() + getNossoNumeroFormatted() +
                getDataJuliano() + getApp();
        return boleto.getDigitoCodigoBarras(campo);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }

    /**
     * Metodo para monta o desenho do codigo de barras
     * A ordem destes campos tambem variam de banco para banco, entao e so olhar na documentacao do seu banco
     * qual a ordem correta
     */
    @Override
    public String getCodigoBarras() {
        return getNumero() + String.valueOf(boleto.getMoeda()) +
               getCampo4() + boleto.getFatorVencimento() +
               boleto.getValorTitulo() + boleto.getCodCliente() +
               getNossoNumeroFormatted() + getDataJuliano() + getApp();        
    }

    /**
     * Metodo que concatena os campo para formar a linha digitavel
     * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
     */
    @Override
    public String getLinhaDigitavel() {
        return   getCampo1().substring(0, 5) + "." + getCampo1().substring(5) +
          "  " + getCampo2().substring(0, 5) + "." + getCampo2().substring(5) +
          "  " + getCampo3().substring(0, 5) + "." + getCampo3().substring(5) +
          "  " + getCampo4() + "  " + getCampo5();
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
        //return boleto.getAgencia() + " / " + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
        return boleto.getCodCliente();
    }

    /**
     * Recupera o nossoNumero no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    @Override
    public String getNossoNumeroFormatted() {
        String noDocumento = boleto.getNoDocumento();
        String dv1 = boleto.getModulo11(noDocumento, 9);
        String tpdv = "4";
        String valor = boleto.getNoDocumento()+dv1+tpdv;
        long nvalor = Long.parseLong(valor);
        long ncliente = Long.parseLong(boleto.getCodCliente());
        String data = boleto.getDataVencimento().substring(0,2) +
                      boleto.getDataVencimento().substring(3,5)+
                      boleto.getDataVencimento().substring(8);
        long ldata = Long.parseLong(data);
        long soma = nvalor+ncliente+ldata;
        String dv2 = boleto.getModulo11(Long.toString(soma), 9);
        String nossoNumeroFormatted = boleto.getNoDocumento()+dv1+tpdv+dv2;
        while(nossoNumeroFormatted.length()<13) {
            nossoNumeroFormatted = "0"+nossoNumeroFormatted;
        }
        return nossoNumeroFormatted;
    }
}