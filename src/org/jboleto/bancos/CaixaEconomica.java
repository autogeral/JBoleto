/*
 * Esta biblioteca e um software livre, que pode ser redistribuido e/ou
 * modificado sob os termos da LicenÃƒÂ§a Publica Geral Reduzida GNU,
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
 * Classe modelo para a criacao de outros
 * Copie este arquivo para o nome do banco q vc pretende criar, seguindo a mesma nomeclatura de nomes das outras classes
 * Caso o banco tenha algum metodo diferente de calcular os seus campos, vc pode criar os seu metodos particulares dentro
 * desta classe, pois os metodos que tem nesta classe sao padroes
 * @author Fabio Souza
 */
public class CaixaEconomica implements Banco {

    JBoletoBean boleto;
    private int REGISTRO = Integer.parseInt(System.getProperty("boleto.registro","1"));
    private int responsavelEmissaoBoleto;

    /**
     * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
     */
    public String getNumero() {
        return "104";
    }

    /**
     * Retorna  Identificador da Emissão do Boleto
     * Segundo a documentação da Caixa Economica (4-Beneficiário) 
     */
    public int getResponsavelEmissaoBoleto() {
        return (boleto.getCarteira().equals("1") ? 4 : responsavelEmissaoBoleto);
    }
    
    public void setResponsavelEmissaoBoleto(int responsavelEmissaoBoleto){
        this.responsavelEmissaoBoleto = responsavelEmissaoBoleto;
    }
    
    /**
     * Retorna o Campo livre
     */
//    private String getCampoLivre() {
//        return boleto.getCarteira() + boleto.getNossoNumero() + boleto.getAgencia() + boleto.getCodigoOperacao() + boleto.getCodigoFornecidoAgencia();
//    }
    
        /**
     * Retorna o Campo livre
     */ 
    private String getCampoLivre() {
       String aux = boleto.getCarteira() + getResponsavelEmissaoBoleto() + boleto.getNossoNumero();
       String codigoBeneficiario = boleto.getCedenteCodigoCliente() ;
       String nossoNumeroSequencia1 = aux.substring(2, 5);
       String constante1 = String.valueOf(REGISTRO); 
       String nossoNumeroSequencia2 = aux.substring(5, 8);
       String constante2 = Integer.toString(getResponsavelEmissaoBoleto());
       String nossoNumeroSequencia3 = aux.substring(8, 17);
       String aux1 = codigoBeneficiario + nossoNumeroSequencia1 + constante1 + nossoNumeroSequencia2 + constante2 + nossoNumeroSequencia3;
       String dVCampoLivre = DVCampoLivre(aux1);
       
       String retorno = codigoBeneficiario + nossoNumeroSequencia1 + constante1 + 
               nossoNumeroSequencia2 + constante2 + nossoNumeroSequencia3 + dVCampoLivre;
       
       return retorno;
      //  return boleto.getCedenteCodigoCliente()+ boleto.getNossoNumero().substring(2,4) + boleto.getAgencia() + boleto.getCodigoOperacao() + boleto.getCodigoFornecidoAgencia();
    }

    /**
     * Classe construtura, recebe como parametro a classe jboletobean
     * @param boleto
     */
    public CaixaEconomica(JBoletoBean boleto) {
        this.boleto = boleto;
    }

    /**
     * Metodo que monta o primeiro campo do codigo de barras
     * Este campo como os demais e feito a partir do da documentacao do banco
     * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
     * ÃƒÂ© sÃƒÂ³ concatenar os campos como no exemplo abaixo.
     */
    private String getCampo1() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + getCampoLivre().substring(0, 5);

        return boleto.getDigitoCampo(campo, 2);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo2() {
        String campo = getCampoLivre().substring(5, 15);

        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo3() {
        String campo = getCampoLivre().substring(15);

        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo4() {
        
        String banco = getNumero();
        String moeda = String.valueOf(boleto.getMoeda());
        String fatorVencimento = String.valueOf(boleto.getFatorVencimento());
        String valor =  boleto.getValorTitulo();
        String campoLivre = getCampoLivre();
        
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre();

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
    public String getCodigoBarras() {
        return getNumero() + String.valueOf(boleto.getMoeda()) + String.valueOf(getCampo4()) + String.valueOf(getCampo5()) + getCampoLivre();
    }

    /**
     * Metodo que concatena os campo para formar a linha digitavel
     * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
     */
    public String getLinhaDigitavel() {
        return getCampo1().substring(0, 5) + "." + getCampo1().substring(5) + "  " + getCampo2().substring(0, 5) + "." + getCampo2().substring(5) + "  " + getCampo3().substring(0, 5) + "." + getCampo3().substring(5) + "  " + getCampo4() + "  " + getCampo5();
    }

    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }

    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Gladyston Batista/Eac Software
     */
//    public String getAgenciaCodCedenteFormatted() {
//
//        Integer f1 = Integer.parseInt(boleto.getAgencia().substring(0, 4));
//        Integer f2 = Integer.parseInt(boleto.getCodigoOperacao().substring(0, 3));
//        Integer f3 = Integer.parseInt(boleto.getCodigoFornecidoAgencia());
//        Integer f4 = Integer.parseInt(boleto.getDvCodigoFornecidoAgencia());
//
//        return String.format("%04d.%03d.%08d-%01d", f1, f2, f3, f4);
//    }
    
    
    public String getAgenciaCodCedenteFormatted() {

        String ultimo = boleto.getCedenteCodigoCliente();
        ultimo = ultimo.substring(ultimo.length() - 1);

        String aux = boleto.getCedenteCodigoCliente().substring(0, boleto.getCedenteCodigoCliente().length() - 1);
        aux += "-" + ultimo;
        return boleto.getAgencia() + "/" + aux;
    }

    public String getNossoNumeroFormatted() {               
        String campo =   boleto.getCarteira() + getResponsavelEmissaoBoleto() + boleto.getNossoNumero();
        String digito = boleto.calculaDigitoVerificadorNossoNumero(campo);
        return boleto.getCarteira()+ getResponsavelEmissaoBoleto() + "/"+ boleto.getNossoNumero() + "-" + digito;
    }

    private String DVCampoLivre(String aux) {
      
        String campoLivre = "";

        int i = 0;
        int x = 0;
        int multiplicador = 9;

        /*
         1º PASSO 
         Aplicar o módulo 11, o primeiro dígito da direita para a esquerda será
         multiplicado por 2, o segundo, por 3 e 
         assim sucessivamente até o 9;
         */
        //2°Passo, Somar o resultado da multiplicação: 
        System.out.println();
        int soma = 0;
        while (i < aux.length()) {
            x = Integer.parseInt(aux.substring(i, i + 1));
            x = x * multiplicador;

            multiplicador -= 1;
            if (multiplicador == 1) {
                multiplicador = 9;
            }
            campoLivre += x;
            soma += x;
            i++;
        }

        /*
         3º PASSO 
         Dividir o Total da Soma por 11 
         Obs: Quando o Total da Soma for MENOR que o quociente (no caso 11), 
         pular o 3º PASSO, ou seja, o Total 
         da Soma deverá ser diminuído diretamente do quociente, 
         obtendo-se o DV como resultado. 
         */
        int resto = 0;
        if (soma > 11) {
            resto = soma % 11;
        }

        /*
         4º PASSO 
         Subtrair o resto da divisão de 11.
         Se o RESULTADO for maior que 9 (nove) o DV será 0 (zero), 
         caso contrário o RESULTADO será o DV
         */
        int dv = 11 - resto;
        if (dv > 9) {
            dv = 0;
        }

        return String.valueOf(dv);
    }
}
