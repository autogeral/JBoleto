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

package org.jboleto;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.jboleto.bancos.BancoBrasil;
import org.jboleto.bancos.BancoReal;
import org.jboleto.bancos.Bradesco;
import org.jboleto.bancos.CaixaEconomica;
import org.jboleto.bancos.Hsbc;
import org.jboleto.bancos.Itau;
import org.jboleto.bancos.NossaCaixa;
import org.jboleto.bancos.Santander;
import org.jboleto.bancos.SantanderBanespa;
import org.jboleto.bancos.Unibanco;
import org.jboleto.control.PDFGenerator;


/**
 * Classe resposavel por setar todas as configuracoes necessarias para a geracao do titulo / boleto
 * @author Flavio Brasil *
 */
public class JBoleto {
    
    public static final int BANCO_DO_BRASIL = 0;
    public static final int BRADESCO = 1;
    public static final int ITAU = 2;
    public static final int BANCO_REAL = 3;
    public static final int CAIXA_ECONOMICA = 4;
    public static final int UNIBANCO = 5;
    public static final int HSBC = 6;
    public static final int NOSSACAIXA = 7;
    public static final int SANTANDER = 8;
    public static final int SANTANDER_BANESPA = 9;
    
    private PDFGenerator generator;
    
    /**
     * Metodo responsavel por adicionar um boleto na fila para a geracao e identificando o seu respectivo banco
     */
    public void addBoleto(JBoletoBean boleto, int banco){
        
        Banco bancoBean = null;
        
        if (banco == JBoleto.BANCO_DO_BRASIL){
            
            bancoBean = new BancoBrasil(boleto);
        } 
        else if (banco==JBoleto.BRADESCO){
            
            bancoBean = new Bradesco(boleto);
        } 
        else if (banco==JBoleto.ITAU){
            
            bancoBean = new Itau(boleto);
        } 
        else if (banco==JBoleto.BANCO_REAL){
            
            bancoBean = new BancoReal(boleto);
        } 
        else if (banco==JBoleto.CAIXA_ECONOMICA){
            
            bancoBean = new CaixaEconomica(boleto);
        } 
        else if (banco==JBoleto.UNIBANCO){
            
            bancoBean = new Unibanco(boleto);
        } 
        else if (banco==JBoleto.HSBC){
            
            bancoBean = new Hsbc(boleto);
        } 
        else if (banco==JBoleto.NOSSACAIXA){
            
            bancoBean = new NossaCaixa(boleto);
        }
        else if (banco==JBoleto.SANTANDER){
            
            bancoBean = new Santander(boleto);
        }else if (banco==JBoleto.SANTANDER_BANESPA){
            bancoBean = new SantanderBanespa(boleto);

        }
                
        /**
         * Alterado por Gladyston/EAC Software
         * seta as inf no objeto boleto p/ serem acessadas atraves do main
         */
        boleto.setCodigoBarras(bancoBean.getCodigoBarras());
        boleto.setLinhaDigitavel(bancoBean.getLinhaDigitavel());
        
        
        /**
         * Alterado pela Fly solution
         * Caso queira colocar uma imagem de fundo personalizada para um banco em especifico
         * é só criar a imagem dentro da pasta img e passar como parametro conforme
         * mostrado abaixo.
         * A imagem padrão para todos os boletos é a template.png
         * Ainda não temos nenhuma personalizacao
         */
        if (generator == null) {
            String template ="template";
            if(banco==4){
                template ="templateCaixa";
            }
            generator = new PDFGenerator(template, boleto, bancoBean);
//            if (banco==JBoleto.NOSSACAIXA) {
//                generator = new PDFGenerator("template", boleto, bancoBean);
//            } else {
//                generator = new PDFGenerator("template", boleto, bancoBean);
//            }
        }
        
        generator.addBoleto(boleto, bancoBean);
    }
    
    /**
     * Metodo que cria o arquivo loca no disco
     */
    public void writeToFile(String path){
        
        ByteArrayOutputStream baos = generator.getBaos();
        
        try{
            
            FileOutputStream fos = new FileOutputStream(path);
            
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo que armazena os dados de cada boleto em buffer
     */
    public ByteArrayOutputStream writeToByteArray() {
        
        ByteArrayOutputStream baos = generator.getBaos();
        generator = null;
        
        return baos;
    }
}
