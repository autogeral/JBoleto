/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jboleto.exemplos;

import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 *30/05/2011 10:10:04
 * @author talentos
 */
public class ExemploItauWfb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExemploItauWfb e = new ExemploItauWfb();
//        e.criaBoleto(dataDocumento, dataProcessamento, dataVencimento
//                , cedente, carteira, sacadoNome, sacadoEndereco
//                , sacadoBairro, sacadoCidade, sacadoUf, sacadoCep, sacadoCpfCnpj
//                , agencia, conta, dvConta, carteira, nossoNumero, numeroDocumento
//                , valor);
        JBoleto jBoleto = new JBoleto();
        JBoletoBean boleto = new JBoletoBean();

         boleto = e.criaBoleto("13/05/2011", "13/05/2011", "07/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "MERCANTE TUBOS E ACOS LTDA", "Rua Emilia Golin, 700"
                , "J. Aracilia", "Guarulhos", "SP", "07076-120", "43.432.624/0001-90"
                , "0278", "90749", "8", "35835133", "8"
                , "2020.00");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("18/05/2011", "18/05/2011", "05/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "BELCAR CAMINHOES E MAQUINAS LTDA", "RDV BR, 153 KM 1282"
                , "Alto da Gloria", "Goiania", "GO", "74815-070", "02.212.918/0001-20"
                , "0278", "90749", "8", "36188947", "9"
                , "3314.40");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("19/05/2011", "19/05/2011", "16/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "36357046", "7"
                , "511.07");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/05/2011", "19/05/2011", "23/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "36357271", "5"
                , "511.07");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/05/2011", "19/05/2011", "30/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "36357571", "8"
                , "511.07");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/04/2011", "19/04/2011", "24/05/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "33402378", "1"
                , "614.72");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/04/2011", "19/04/2011", "01/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "33402486", "3"
                , "614.72");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("20/04/2011", "20/04/2011", "20/05/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "LUIZ MARQUES E LIMA LTDA", "Av. Herique de Holanda, 2380-2400"
                , "Centro", "Vitoria de Sto", "PE", "55608-001", "10.916.286/0001-03"
                , "0278", "90749", "8", "33539109", "4"
                , "1599.52");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("19/04/2011", "19/04/2011", "17/05/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "TROIANI ROLAMENTOS LTDA", "Rua Marechal Deodoro, 930"
                , "Centro", "Pocos de Caldas", "MG", "37701-014", "17.357.005/0001-79"
                , "0278", "90749", "8", "33402236", "1"
                , "614.72");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

          boleto = e.criaBoleto("20/04/2011", "20/04/2011", "04/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "LUIZ MARQUES E LIMA LTDA", "Av. Herique de Holanda, 2380-2400"
                , "Centro", "Vitoria de Sto", "PE", "55608-001", "10.916.286/0001-03"
                , "0278", "90749", "8", "33539272", "5"
                , "1599.52");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("20/04/2011", "20/04/2011", "19/06/2011", "WALZLAGER FABRIK DO BR LTDA", "109", "LUIZ MARQUES E LIMA LTDA", "Av. Herique de Holanda, 2380-2400"
                , "Centro", "Vitoria de Sto", "PE", "55608-001", "10.916.286/0001-03"
                , "0278", "90749", "8", "33539384", "6"
                , "1599.51");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "02/05/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "32662907", "2"
                , "1538.06");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "16/05/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "32662928", "7"
                , "1538.06");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("11/04/2011", "11/04/2011", "30/05/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "32662938", "3"
                , "1538.06");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "10/05/2011", "GERAL CAR P C I E LTDA", "109", "VENTURINE ALVES COMERCIO DE AU", "Av. Libero de Almeida Silvares"
                , "Coester", "Fernandopolis", "SP", "15600-000", "09.313.593/0001-10"
                , "0278", "91002", "1", "32663051", "3"
                , "623.39");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "09/06/2011", "GERAL CAR P C I E LTDA", "109", "VENTURINE ALVES COMERCIO DE AU", "Av. Libero de Almeida Silvares"
                , "Coester", "Fernandopolis", "SP", "15600-000", "09.313.593/0001-10"
                , "0278", "91002", "1", "32663079", "4"
                , "623.38");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("11/04/2011", "11/04/2011", "14/06/2011", "GERAL CAR P C I E LTDA", "109", "L. F. CLAUDIO & CIA LTDA - EPP", "Rua Coronel Daniel Peluso, 1310"
                , "Matadouro", "Braganca Paulista", "SP", "12926-162", "67.317.347/0001-33"
                , "0278", "91002", "1", "35463110", "7"
                , "656.98");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "25/06/2011", "GERAL CAR P C I E LTDA", "109", "NOVO CAMPO COMERCIAL LTDA", "Rua Madre Luciana Maria, 890"
                , "Sorocabano", "Jaboticabal", "SP", "14870-460", "67.521.187/0001-40"
                , "0278", "91002", "1", "32663196", "5"
                , "937.56");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "11/05/2011", "GERAL CAR P C I E LTDA", "109", "ADRIANO TADEU DE OLIVEIRA ME", "Av. Juscelino Kubitschek"
                , "Matadouro", "Braganca Paulista", "SP", "12926-010", "05.596.042/0001-50"
                , "0278", "91002", "1", "32663323", "6"
                , "697.50");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

        boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/06/2011", "GERAL CAR P C I E LTDA", "109", "AUTO PECAS FREDERICO LTDA", "Rua Marques de Pombal, 886"
                , "Campos Eliseosl", "Ribeirao Preto", "SP", "14080-100", "55.968.424/0001-36"
                , "0278", "91002", "1", "35464848", "8"
                , "1050.70");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/07/2011", "GERAL CAR P C I E LTDA", "109", "AUTO PECAS FREDERICO LTDA", "Rua Marques de Pombal, 886"
                , "Campos Eliseosl", "Ribeirao Preto", "SP", "14080-100", "55.968.424/0001-36"
                , "0278", "91002", "1", "35464920", "9"
                , "1050.70");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/05/2011", "19/05/2011", "16/06/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "36351992", "9"
                , "473.56");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("11/04/2011", "11/04/2011", "25/05/2011", "GERAL CAR P C I E LTDA", "109", "NOVO CAMPO COMERCIAL LTDA", "Rua Madre Luciana Maria, 890"
                , "Sorocabano", "Jaboticabal", "SP", "14870-460", "67.521.187/0001-40"
                , "0278", "91002", "1", "32663196", "5"
                , "937.56");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("18/05/2011", "18/05/2011", "24/05/2011", "GERAL CAR P C I E LTDA", "109", "DISPLATO EMBREAGENS LTDA", "Av. Roma, 755"
                , "Santa Terezinha", "Piracicaba", "SP", "13408-035", "53.030.680/0001-52"
                , "0278", "91002", "1", "36190180", "8"
                , "420.20");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/05/2011", "19/05/2011", "30/06/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "36352156", "3"
                , "473.56");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

         boleto = e.criaBoleto("19/05/2011", "19/05/2011", "14/07/2011", "GERAL CAR P C I E LTDA", "109", "PIQUERI COMERCIO E DISTRIBUICA", "Av. Otaviano Alves de Lima, 5260"
                , "Piqueri", "Sao Paulo", "SP", "02901-000", "11.416.091/0001-67"
                , "0278", "91002", "1", "36352393", "3"
                , "473.56");
        jBoleto.addBoleto(boleto, JBoleto.ITAU);

//        boleto = e.criaBoleto("10/05/2011", "10/05/2011", "10/06/2011"
//                , "J. Computação LTDA - ME", "109", "Patricia", "Av. Dom Pedro"
//                , "Centro", "Salto", "SP", "13320-240", "049.747.666-51"
//                , "0278", "84469", "1", "00000001", "1"
//                , "5.00");
//        jBoleto.addBoleto(boleto, JBoleto.ITAU);


        jBoleto.writeToFile("itau.pdf");

    }

    private JBoletoBean criaBoleto(String dataDocumento, String dataProcessamento, String dataVencimento
                , String cedente, String carteira, String sacadoNome, String sacadoEndereco
                , String sacadoBairro, String sacadoCidade, String sacadoUf, String sacadoCep, String sacadoCpfCnpj
                , String agencia, String conta, String dvConta, String nossoNumero, String numeroDocumento
                , String valor) {
        JBoletoBean jBoletoBean = new JBoletoBean();

        jBoletoBean.setDataDocumento(dataDocumento);
        jBoletoBean.setDataProcessamento(dataProcessamento);

        jBoletoBean.setCedente(cedente);
        jBoletoBean.setCarteira(carteira);

        jBoletoBean.setNomeSacado(sacadoNome);
        jBoletoBean.setEnderecoSacado(sacadoEndereco);
        jBoletoBean.setBairroSacado(sacadoBairro);
        jBoletoBean.setCidadeSacado(sacadoCidade);
        jBoletoBean.setUfSacado(sacadoUf);
        jBoletoBean.setCepSacado(sacadoCep);
        jBoletoBean.setCpfSacado(sacadoCpfCnpj);

        jBoletoBean.setLocalPagamento("Pagável em qualquer banco até o vencimento");

        Vector descricoes = new Vector();
        descricoes.add("Boleto de teste para homologação");

        jBoletoBean.setDescricoes(descricoes);

//        jBoletoBean.setImagemMarketing("/home/fabio/template_logo.png");

        jBoletoBean.setDataVencimento(dataVencimento);
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia(agencia);
        jBoletoBean.setContaCorrente(conta);
        jBoletoBean.setDvContaCorrente(dvConta);
        //Documentacao especifica 5 numeros
        jBoletoBean.setCodCliente("00000");
        jBoletoBean.setAceite("N");
        jBoletoBean.setEspecieDocumento("DM");

        jBoletoBean.setNossoNumero(nossoNumero);
        //especificacao para carteira 198 sao 7 numeros
        jBoletoBean.setNoDocumento(numeroDocumento);
        jBoletoBean.setValorBoleto(valor);
        return jBoletoBean;
    }

}
