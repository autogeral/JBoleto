package org.jboleto.bancos;

import org.jboleto.Banco;
import org.jboleto.JBoletoBean;
/**
 *
 * @author larissa.camargo
 *     06.08.2021
 * 
 */
public class Inter implements Banco {
     JBoletoBean boleto;

    public Inter(JBoletoBean boleto){
        this.boleto = boleto;
    }

    @Override
    public String getNumero() {
        return "077";
    }

    @Override
    public String getCodigoBarras() {
        String aux = getCodigoBarrasSemDac();
        return aux.substring(0,4)+getDvCodigoBarras()+aux.substring(4);
    }

    private String getCodigoBarrasSemDac(){
        return getNumero()+boleto.getMoeda()+boleto.getFatorVencimento()
                +boleto.getValorTitulo()
                +"9"+boleto.getCodCliente()+boleto.getNossoNumero()
                +boleto.getIOS()+boleto.getCarteira();
    }

    private String getDvCodigoBarras(){
        String aux = getCodigoBarrasSemDac();
        return boleto.getModulo11(aux, 9);
    }

    @Override
    public String getLinhaDigitavel() {
        String aux = getNumero()+boleto.getMoeda()+"9"
                +boleto.getCodCliente().substring(0, 4);
        String campo1 = aux+boleto.getModulo10(aux);
        aux = boleto.getCodCliente().substring(4)+
                boleto.getNossoNumero().substring(0, 7);
        String campo2 = aux +boleto.getModulo10(aux);
        aux = boleto.getNossoNumero().substring(7)+
                boleto.getIOS()+boleto.getCarteira();
        String campo3 = aux+boleto.getModulo10(aux);
        String campo4 = getDvCodigoBarras();
        String campo5 = boleto.getFatorVencimento()+ boleto.getValorTitulo();
        return 	campo1.substring(0,5) + "." + campo1.substring(5) + "  "
             +  campo2.substring(0,5) + "." + campo2.substring(5) + "  "
             + campo3.substring(0,5) + "." + campo3.substring(5) + "  "
             + campo4 + "  " + campo5;
    }

    @Override
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }

    @Override
    public String getAgenciaCodCedenteFormatted() {
        return boleto.getAgencia()+"/"+boleto.getCodCliente();
    }

    @Override
    public String getNossoNumeroFormatted() {
        return boleto.getNossoNumero().substring(0, 12)+"-"+boleto.getNossoNumero().substring(12);
    }
}
