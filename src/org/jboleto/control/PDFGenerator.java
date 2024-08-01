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
package org.jboleto.control;

import com.lowagie.text.BadElementException;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.jboleto.Banco;
import org.jboleto.JBoletoBean;

/**
 *
 * Classe responsavel pela geracao dos boletos no formato PDF.
 *
 * @author Fabio Souza
 */
public class PDFGenerator {

    ByteArrayOutputStream baos;

    private DefaultFormatter formatter;

    private Document document;
    private PdfContentByte cb;

    private PdfTemplate tempImgBoleto;
    private PdfTemplate tempImgBanco;
    private PdfTemplate tempImgMarketing;

    private final float IMAGEM_BOLETO_WIDTH = 514.22f;
    private final float IMAGEM_BOLETO_HEIGHT = 385.109f;

    private final float IMAGEM_BANCO_WIDTH = 100.0f;
    private final float IMAGEM_BANCO_HEIGHT = 23.0f;

    private final float IMAGEM_MARKETING_WIDTH = 511.2f;
    private final float IMAGEM_MARKETING_HEIGHT = 3341.3f;
    private boolean boletoCaixa = false;
    private boolean boletoBradesco = false;

    Image imgTitulo = null;

    //gera template com a imagem do marketing
    Image imgMarketing = null;

    Image imgBanco = null;

    /**
     *
     * @param template Imagem de referencia para imprimir
     * @param boleto
     * @param banco
     * @throws com.lowagie.text.DocumentException
     * @throws com.lowagie.text.BadElementException
     * @throws java.io.IOException
     */
    public PDFGenerator(String template, JBoletoBean boleto, Banco banco) throws DocumentException, BadElementException, IOException {

        baos = new ByteArrayOutputStream();

        formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));

        document = new Document(PageSize.A4);

            PdfWriter writer = PdfWriter.getInstance(document, baos);

            document.open();

            cb = writer.getDirectContent();

            //gera template com o fundo do boleto
            imgTitulo = Image.getInstance(getClass().getResource("/img/" + template + ".gif"));
            imgTitulo.scaleToFit(IMAGEM_BOLETO_WIDTH, IMAGEM_BOLETO_HEIGHT);
            imgTitulo.setAbsolutePosition(0, 0);
            //Para usar no template da caixa
            if (template.equals("templateCaixa")) {
                boletoCaixa = true;
            } else if (template.equals("templateBancoBradesco")) {
                boletoBradesco = true;
            }

            //Pega a imagem do marketing
            if (boleto.getImagemMarketing() != null) {

                imgMarketing = Image.getInstance(boleto.getImagemMarketing());
            } else { //caso o metodo nao esteja setado coloca uma imagem de um pixel no lugar

                imgMarketing = Image.getInstance(getClass().getResource("/img/pixel.gif"));
            }

            imgMarketing.scaleToFit(IMAGEM_MARKETING_WIDTH, IMAGEM_MARKETING_HEIGHT);
            imgMarketing.setAbsolutePosition(0, 0);

            imgBanco = Image.getInstance(getClass().getResource("/img/" + banco.getNumero() + ".gif"));
            imgBanco.scaleToFit(IMAGEM_BANCO_WIDTH, IMAGEM_BANCO_HEIGHT);
            imgBanco.setAbsolutePosition(0, 0);
    }

    /**
     * Adiciona um boleto na fila.
     *
     * @author Fabio Souza
     * @param boleto
     * @param banco
     * @throws com.lowagie.text.DocumentException
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public void addBoleto(JBoletoBean boleto, Banco banco) throws DocumentException, IOException, ParseException {

        if (imgMarketing != null) {
            tempImgMarketing = cb.createTemplate(IMAGEM_MARKETING_WIDTH, IMAGEM_MARKETING_HEIGHT);
            tempImgMarketing.addImage(imgMarketing);
        }

        tempImgBoleto = cb.createTemplate(IMAGEM_BOLETO_WIDTH, IMAGEM_BOLETO_HEIGHT);
        tempImgBoleto.addImage(imgTitulo);

        //gera template com a imagem do logo do banco
        tempImgBanco = cb.createTemplate(IMAGEM_BANCO_WIDTH, IMAGEM_BANCO_HEIGHT);
        tempImgBanco.addImage(imgBanco);

        float altura = 412;

        document.newPage();

        cb.addTemplate(tempImgMarketing, document.left(), document.top() - 340);

        cb.addTemplate(tempImgBoleto, document.left(), document.top() - 750);
        cb.addTemplate(tempImgBanco, document.left(), document.top() - 486);

        BaseFont bfTextoSimples = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        BaseFont bfTextoCB = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);

        //inicio das descricoes do boleto
        cb.beginText();
        cb.setFontAndSize(bfTextoCB, 10);

        if (boleto.getDescricoes() != null) {

            List<String> descricoes = boleto.getDescricoes();

            for (int i = 0; i < descricoes.size(); i++) {

                //cb.setTextMatrix(document.left(),(document.top()-70)-(i*15));
                cb.setTextMatrix(document.left(), document.top() - (i * 15));
                cb.showText(String.valueOf(descricoes.get(i)));
            }

            cb.endText();
        }

        //fim descricoes
        cb.beginText();
        cb.setFontAndSize(bfTextoCB, 13);

        cb.setTextMatrix(document.left() + 125, altura - 87);

        if (banco.getNumero().equals("104")) {
            cb.showText(banco.getNumero() + "-" + "0");
            cb.endText();
        } else {
            cb.showText(banco.getNumero() + "-" + boleto.getDigitoCodigoBarras(banco.getNumero()));
            cb.endText();
        }

        cb.beginText();
        cb.setFontAndSize(bfTextoSimples, 8);

        if (!boletoBradesco) {
            cb.setTextMatrix(document.left() + 50, altura + 23);
            cb.showText(boleto.getCedente()); //imprime o cedente
        } else {
            cb.setFontAndSize(bfTextoSimples, 6);
            cb.setTextMatrix(document.left() + 40, altura + 30);
            cb.showText(boleto.getNomeCooperativa().toUpperCase() + " - " + boleto.getCnpjCooperativa());//imprime o cedente
            cb.setTextMatrix(document.left() + 40, altura + 23);
            cb.showText(boleto.getEnderecoCooperativa().toUpperCase() + "/" + boleto.getBairroCooperativa().toUpperCase() + " - "
                    + boleto.getCidadeCooperativa().toUpperCase() + "/" + boleto.getUfCooperativa().toUpperCase() + " - " + boleto.getCepCooperativa());
            cb.setFontAndSize(bfTextoSimples, 8);
        }

        cb.setTextMatrix(document.left() + 5, altura);
        if (!boletoBradesco) {
            cb.showText(JBoletoBean.soPrimeiraMaiuscula(boleto.getNomeSacado()));
        } else {
            cb.showText(boleto.getNoDocumento());
        }

        cb.setTextMatrix(document.left() + 230, altura);
        cb.showText(boleto.getDataVencimento());

        cb.setTextMatrix(document.left() + 400, altura);
        //cb.showText(formatter.valueToString(new Double(boleto.getValorBoleto())));
        if (!boletoBradesco) {
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getValorCobrado().replace(",", "."))));
        } else {
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getValorBoleto().replace(",", "."))));
            cb.setTextMatrix(document.left() + 400, altura - 19);
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getValorCobrado().replace(",", "."))));
        }

        // ALTERADO POR GLADYSTON
        cb.setTextMatrix(document.left() + 5, altura - 19);
        cb.showText(banco.getAgenciaCodCedenteFormatted());

        // ALTERADO POR GLADYSTON
        cb.setTextMatrix(document.left() + 146, altura - 19);
        cb.showText(banco.getNossoNumeroFormatted());

        if (!boletoBradesco) {
            cb.setTextMatrix(document.left() + 5, altura - 40);
            cb.showText(boleto.getNoDocumento());
        } else {
            cb.setTextMatrix(document.left() + 310, altura - 19);
            cb.showText("R$");
            cb.setFontAndSize(bfTextoSimples, 5);
            cb.setTextMatrix(document.left() + 30, altura - 30);
            cb.showText(boleto.getNomeSacado().toUpperCase() + "     " + boleto.getCpfSacado());
            cb.setTextMatrix(document.left() + 30, altura - 36);
            cb.showText(boleto.getEnderecoSacado().toUpperCase() + " " + boleto.getBairroSacado().toUpperCase() + " - " + boleto.getCidadeSacado().toUpperCase()
                    + "/" + boleto.getUfSacado().toUpperCase() + " - " + boleto.getCepSacado());
            cb.setTextMatrix(document.left() + 50, altura - 46);
            cb.showText(boleto.getCedente().toUpperCase() + " - " + boleto.getCnpjCedente() + " " + boleto.getEnderecoCedente().toUpperCase()
                    + " - " + boleto.getBairroCedente().toUpperCase() + " - "
                    + boleto.getCidadeCedente().toUpperCase() + "/" + boleto.getUfCedente().toUpperCase() + " - " + boleto.getCepCedente());
            cb.setFontAndSize(bfTextoCB, 8);
        }
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bfTextoCB, 10);

        cb.setTextMatrix(document.left() + 175, altura - 87);
        cb.showText(banco.getLinhaDigitavel());
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(bfTextoSimples, 8);

        cb.setTextMatrix(document.left() + 5, altura - 111);
        cb.showText(boleto.getLocalPagamento());

        cb.setTextMatrix(document.left() + 5, altura - 121);
        cb.showText(boleto.getLocalPagamento2());

        cb.setTextMatrix(document.left() + 425, altura - 121);
        cb.showText(boleto.getDataVencimento());

        if (!boletoBradesco) {
            cb.setTextMatrix(document.left() + 5, altura - 141);
            cb.showText(boleto.getCedente());
        } else {
            cb.setFontAndSize(bfTextoSimples, 6);
            cb.setTextMatrix(document.left() + 40, altura - 129);
            cb.showText(boleto.getNomeCooperativa() + " - " + boleto.getCnpjCooperativa());
            cb.setTextMatrix(document.left() + 5, altura - 138);
            cb.showText(boleto.getEnderecoCooperativa() + "/" + boleto.getBairroCooperativa() + " - "
                    + boleto.getCidadeCooperativa() + " / " + boleto.getUfCooperativa() + " - " + boleto.getCepCooperativa());
            cb.setFontAndSize(bfTextoSimples, 8);
        }

        // ALTERADO POR GLADYSTON
        cb.setTextMatrix(document.left() + 410, altura - 141);
        String agenciacodCendenteFormatada = banco.getAgenciaCodCedenteFormatted();
        //Boleto da Caixa  usa formatacao diferente 
        if (banco.getNumero().equals("104")) {
            agenciacodCendenteFormatada = agenciacodCendenteFormatada.replace(".000.00", "/");
        }
        cb.showText(agenciacodCendenteFormatada);

        cb.setTextMatrix(document.left() + 5, altura - 162);
        cb.showText(boleto.getDataDocumento());

        cb.setTextMatrix(document.left() + 70, altura - 162);
        cb.showText(boleto.getNoDocumento());

        cb.setTextMatrix(document.left() + 180, altura - 162);
        cb.showText(boleto.getEspecieDocumento());

        cb.setTextMatrix(document.left() + 250, altura - 162);
        cb.showText(boleto.getAceite());

        cb.setTextMatrix(document.left() + 300, altura - 162);
        cb.showText(boleto.getDataProcessamento());

        // ALTERADO POR GLADYSTON
        cb.setTextMatrix(document.left() + 410, altura - 162);
        String nossoNumeroformatado = banco.getNossoNumeroFormatted();
        //Boleto da Caixa  usa formatacao diferente 
        if (banco.getNumero().equals("104")) {
            nossoNumeroformatado = nossoNumeroformatado.replace(".", "/");
        }
        cb.showText(nossoNumeroformatado);

        // ALTERADO POR GLADYSTON
        cb.setTextMatrix(document.left() + 122, altura - 185);
        cb.showText(banco.getCarteiraFormatted());

        cb.setTextMatrix(document.left() + 200, altura - 185);
        cb.showText("R$");

        cb.setTextMatrix(document.left() + 430, altura - 185);
        cb.showText(formatter.valueToString(Double.valueOf(boleto.getValorBoleto().replace(",", "."))));

        if (boleto.getDescontoAbatimento() != null && !"".equals(boleto.getDescontoAbatimento())) {
            cb.setTextMatrix(document.left() + 430, altura - 206);
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getDescontoAbatimento().replace(",", "."))));
        }

//            if (boleto.getDescontoAbatimento() != null && !"".equals(boleto.getDescontoAbatimento())) {
//                cb.setTextMatrix(document.left() + 430, altura - 227);
//                cb.showText(formatter.valueToString(new Double(boleto.getDescontoAbatimento())));
//            }
        if (boleto.getMoraMulta() != null && !"".equals(boleto.getMoraMulta().replace(",", "."))) {
            cb.setTextMatrix(document.left() + 430, altura - 248);
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getMoraMulta().replace(",", "."))));
        }

        if (boleto.getAcrescimo() != null && !"".equals(boleto.getAcrescimo())) {
            cb.setTextMatrix(document.left() + 430, altura - 269);
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getAcrescimo().replace(",", "."))));
        }

        if (boleto.getValorCobrado() != null && !"".equals(boleto.getValorCobrado())) {
            cb.setTextMatrix(document.left() + 430, altura - 290);
            cb.showText(formatter.valueToString(Double.valueOf(boleto.getValorCobrado().replace(",", "."))));
        }

        cb.setFontAndSize(bfTextoSimples, 6);
        cb.setTextMatrix(document.left() + 37, altura - 197);
        cb.showText(boleto.getMensagemInstrucoes());
        cb.setFontAndSize(bfTextoSimples, 8);

        cb.setTextMatrix(document.left() + 5, altura - 207);
        cb.showText(boleto.getInstrucao1());

        cb.setTextMatrix(document.left() + 5, altura - 217);
        cb.showText(boleto.getInstrucao2());

        cb.setTextMatrix(document.left() + 5, altura - 227);
        cb.showText(boleto.getInstrucao3());

        cb.setTextMatrix(document.left() + 5, altura - 237);
        cb.showText(boleto.getInstrucao4());

        cb.setTextMatrix(document.left() + 5, altura - 247);
        cb.showText(boleto.getInstrucao5());

        if (!boletoCaixa && !boletoBradesco) {
            cb.setTextMatrix(document.left() + 5, altura - 277);
            cb.showText(boleto.getCedente());
        }

        if (!boletoCaixa && !boletoBradesco) {
            cb.setTextMatrix(document.left() + 30, altura - 302);
            cb.showText(JBoletoBean.soPrimeiraMaiuscula(boleto.getNomeSacado()) + "     " + boleto.getCpfSacado());
        } else if (boletoCaixa) {
            cb.setTextMatrix(document.left() + 30, altura - 302);
            cb.showText(JBoletoBean.soPrimeiraMaiuscula(boleto.getNomeSacado()));
            //Cnpj
            cb.setTextMatrix(document.left() + 423, altura - 303);
            cb.showText(boleto.getCpfSacado());
        } else if (boletoBradesco) {
            cb.setFontAndSize(bfTextoSimples, 5);
            cb.setTextMatrix(document.left() + 30, altura - 299);
            cb.showText(boleto.getNomeSacado().toUpperCase() + "     " + boleto.getCpfSacado());
            cb.setFontAndSize(bfTextoCB, 10);
        }

        if (!boletoBradesco) {
            cb.setTextMatrix(document.left() + 30, altura - 312);
            cb.showText(boleto.getEnderecoSacado());
        } else {
            cb.setTextMatrix(document.left() + 30, altura - 306);
            cb.setFontAndSize(bfTextoSimples, 5);
            cb.showText(boleto.getEnderecoSacado().toUpperCase() + " " + boleto.getBairroSacado().toUpperCase());
            cb.setTextMatrix(document.left() + 30, altura - 313);
            cb.showText(boleto.getCidadeSacado().toUpperCase() + "/" + boleto.getUfSacado().toUpperCase() + " - " + boleto.getCepSacado());
            cb.setTextMatrix(document.left() + 50, altura - 325);
            cb.showText(boleto.getCedente().toUpperCase() + "     " + boleto.getCnpjCedente());
            cb.setTextMatrix(document.left() + 50, altura - 332);
            cb.showText(boleto.getEnderecoCedente().toUpperCase() + " - " + boleto.getBairroCedente().toUpperCase() + " - "
                    + boleto.getCidadeCedente().toUpperCase() + "/" + boleto.getUfCedente().toUpperCase() + " - " + boleto.getCepCedente());
            cb.setFontAndSize(bfTextoCB, 10);
        }

        if (!boletoCaixa && !boletoBradesco) {
            cb.setTextMatrix(document.left() + 30, altura - 322);
            cb.showText(boleto.getCepSacado() + " " + boleto.getBairroSacado() + " - " + boleto.getCidadeSacado() + " " + boleto.getUfSacado());
        } else if (boletoCaixa) {
            //bairro - cidade
            cb.setTextMatrix(document.left() + 30, altura - 322);
            cb.showText(boleto.getBairroSacado() + " - " + boleto.getCidadeSacado());
            //estadp
            cb.setTextMatrix(document.left() + 401, altura - 316);
            cb.showText(boleto.getUfSacado());
            //cep
            cb.setTextMatrix(document.left() + 445, altura - 316);
            cb.showText(boleto.getCepSacado());
        }

        cb.endText();

        BarcodeInter25 code = new BarcodeInter25();
        code.setCode(banco.getCodigoBarras());
        code.setExtended(true);

        code.setTextAlignment(Element.ALIGN_LEFT);
        code.setBarHeight(37.00f);
        code.setFont(null);
        code.setX(0.73f);
        code.setN(3);

        PdfTemplate imgCB = code.createTemplateWithBarcode(cb, null, null);
        cb.addTemplate(imgCB, 40, 10);

    }

    public ByteArrayOutputStream getBaos() {

        document.close();
        return baos;
    }

}
