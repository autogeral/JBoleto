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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;

/**
 * @author Fabio Souza
 * @version $Id: JBoletoBean.java,v 1.4 2008/01/07 13:22:05 fabiofns Exp $
 * Classe alterada por Flavio Brasil
 *
 */
public class JBoletoBean {

    private String agencia;
    private String dvAgencia;
    private String contaCorrente;
    private String dvContaCorrente;
    private String moeda = "9";
    private String carteira;
    private String numConvenio;
    private String nossoNumero;
    private String dvNossoNumero = "0";
    private String dataVencimento;
    private String dataDocumento;
    private String dataProcessamento;
    private String valorBoleto;
    private String caminho;
    private String tipoSaida;
    private String localPagamento;
    private String localPagamento2;
    private String cedente;
    private String cedenteCodigoCliente;
    private String qtdMoeda;
    private String valorMoeda;
    private String acrescimo;
    private String mensagemInstrucoes;
    private String instrucao1;
    private String instrucao2;
    private String instrucao3;
    private String instrucao4;
    private String instrucao5;
    private String nomeSacado;
    private String cpfSacado;
    private String enderecoSacado;
    private String cepSacado;
    private String bairroSacado;
    private String cidadeSacado;
    private String ufSacado;
    private String nomeCooperativa;
    private String cnpjCooperativa;
    private String enderecoCooperativa;
    private String cepCooperativa;
    private String bairroCooperativa;
    private String cidadeCooperativa;
    private String ufCooperativa;
    private String cnpjCedente;
    private String enderecoCedente;
    private String cepCedente;
    private String bairroCedente;
    private String cidadeCedente;
    private String ufCedente;
    private String especieDocumento;
    private String aceite;
    private String linhaDigitavel;
    private String codigoBarras;
    private String codCliente;
    private String ios;
    private String noDocumento = "";
    private String codigoOperacao = "";
    private String codigoFornecidoAgencia = "";
    private String dvCodigoFornecidoAgencia = "";
    private String imagemMarketing;
    private List<String> descricoes = null;
    private String descontoAbatimento;
    private String moraMulta;

    public JBoletoBean() {
    }

    public long getFatorVencimento() {

        String[] data = getDataVencimento().split("/");
        String dia = data[0];
        String mes = data[1];
        String ano = data[2];

//        Calendar dataBase = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
        Calendar dataBase = new GregorianCalendar(2000, Calendar.JULY, 3);
        Calendar vencimento = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes) - 1, Integer.parseInt(dia));
        long diferenca = vencimento.getTimeInMillis() - dataBase.getTimeInMillis();

        long diferencaDias = diferenca / (24 * 60 * 60 * 1000);
        
        // ajustando o ciclo do fator de vencimento (1000 a 9999) de acordo com a regra 
        long fator = (diferencaDias % 9000) + 1000;

        return fator;
    }
    
    public static String soPrimeiraMaiuscula (String astring) {
        if(astring==null){
            return null;
        }
        String palavras [] = astring.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean primeira = true;
        for(String palavra : palavras) {
            if(primeira) {
                primeira = false;
            } else {
                sb.append(" ");
            }
            String tratada = soPrimeiraMaiusculaPalavra(palavra);
            sb.append(tratada);
        }
        return sb.toString();
    }
    
    public static String soPrimeiraMaiusculaPalavra (String astring) {
        if(astring==null) {
            return null;
        }
        String string = astring.toLowerCase();
        if(string.length()==1) {
            return string;
        }
        if(string.equals("da") || string.equals("de") || string.equals("do")) {
            return string;
        }
        if(string.equals("me")){
            return string.toUpperCase();
        }
        return (string.length()>2?string.substring(0, 1).toUpperCase()+string.substring(1):string);
    }

    /**
     * Retorna o numero da agencia.
     * Completar com zeros a esquerda quando necessario
     * @return Retorna o numero da agencia.
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Seta o numero da agencia (ex. 2971).
     * @param agencia
     * Seta o numero da agencia (ex. 2971).
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * Retorna o numero da carteira.
     * Itau = 3 digitos
     * Bradesco = 2 digitos
     * @return Retorna o numero da carteira.
     */
    public String getCarteira() {
        return carteira;
    }

    /**
     * Seta a carteira para o titulo (ex. 175. Para outros tipos veja com seu banco).
     * @param carteira
     * Seta a carteira para o titulo (ex. 175. Para outros tipos veja com seu banco).
     */
    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    /**
     * Retorna o numero da conta corrente.
     * @return Retorna o numero da conta corrente.
     */
    public String getContaCorrente() {
        return contaCorrente;
    }

    /**
     * Seta o numero da conta corrente - Coloque zeros a esquerda quando necessario
     * @param contaCorrente
     * Seta o numero da conta corrente - Coloque zeros a esquerda quando necessario
     */
    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    /**
     * Retorna o nosso numero.
     * @return Retorna o nosso numero.
     */
    public String getNossoNumero() {
        return nossoNumero;
    }

    /**
     * Seta o nosso numero.
     * @param nossoNumero
     * @param qtdDigitos - Quantidade de digitos que contem o campo nosso numero referente ao seu banco
     * Seta o nosso numero.
     */
    public void setNossoNumero(String nossoNumero, int qtdDigitos) {
        String zeros = "0000000000000000000000000000000000000000";
        int rest = qtdDigitos - nossoNumero.length();
        if (rest <= 0) {
            this.setNossoNumero(nossoNumero);
        } else {
            this.setNossoNumero(zeros.substring(0, rest) + nossoNumero);
        }
    }

    /**
     * Retorna a data do vencimento do titulo.
     * @return Retorna a data do vencimento do titulo.
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Seta a data de vencimento do titulo (ex. 21/06/2005).
     * @param dataVencimento
     * Seta a data de vencimento do titulo (ex. 21/06/2005).
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * Retorna o valor do titulo.
     * @return Retorna o valor do titulo.
     */
    public String getValorBoleto() {
        return valorBoleto;
    }

    /**
     * Seta o valor do titulo (ex. 23.45 ou 1234.45).
     * @param valorBoleto
     * Seta o valor do titulo (ex. 23.45 ou 1234.45).
     */
    public void setValorBoleto(String valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    /**
     * Retorna o caminho onde o PDF foi salvo.
     * @return Retorna o caminho onde o PDF foi salvo.
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Seta o caminho onde o arquivo devera ser salvo. (ex.: /home/fabio/boleto-05-10-2005.pdf
     * @param caminho
     * Seta o caminho onde o arquivo devera ser salvo. (ex.: /home/fabio/boleto-05-10-2005.pdf
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    /**
     * Retorna o tipo da moeda.
     * @return Retorna o tipo da moeda.
     */
    public String getMoeda() {
        return moeda;
    }

    /**
     * Retorna o acrescimo fornecido ao boleto.
     * @return Retorna o acrescimo fornecido ao boleto.
     */
    public String getAcrescimo() {
        return acrescimo;
    }

    /**
     * Seta o acrescimo ao boleto.
     * @param acrescimo
     * Seta o acrescimo ao boleto.
     */
    public void setAcrescimo(String acrescimo) {
        this.acrescimo = acrescimo;
    }

    /**
     * Retorna o bairro do sacado.
     * @return Retorna o bairro do sacado.
     */
    public String getBairroSacado() {
        return bairroSacado;
    }

    /**
     * Seta o bairro do sacado.
     * @param bairroSacado
     * Seta o bairro do sacado.
     */
    public void setBairroSacado(String bairroSacado) {
        this.bairroSacado = bairroSacado;
    }

    /**
     * Retorna o nome do cedente.
     * @return Retorna o nome do cedente.
     */
    public String getCedente() {
        return cedente;
    }

    /**
     * Seta o nome do cedente.
     * @param cedente
     * Seta o nome do cedente.
     */
    public void setCedente(String cedente) {
        this.cedente = cedente;
    }
    
    public String getCedenteCodigoCliente() {
        return cedenteCodigoCliente;
    }
    
    /**
     * Seta o codigo do cedente Cliente.
     * @param cedenteCliente
     * Seta o codigo do cedente Cliente.
     */
    public void setCedenteCodigoCliente(String cedenteCodigoCliente) {
        this.cedenteCodigoCliente = cedenteCodigoCliente;
    }
    
    /**
     * Retorna o cep do sacado.
     * @return Retorna o cep do sacado.
     */
    public String getCepSacado() {
        return cepSacado;
    }

    /**
     * Seta o cep do sacado.
     * @param cepSacado
     * Seta o cep do sacado.
     */
    public void setCepSacado(String cepSacado) {
        this.cepSacado = cepSacado;
    }

    /**
     * Retorna a cidade do sacado.
     * @return Retorna a cidade do sacado.
     */
    public String getCidadeSacado() {
        return cidadeSacado;
    }

    /**
     * Seta a cidade do sacado.
     * @param cidadeSacado
     * Seta a cidade do sacado.
     */
    public void setCidadeSacado(String cidadeSacado) {
        this.cidadeSacado = cidadeSacado;
    }

    /**
     * Retorna o cpf ou cnpj do sacado.
     * @return Retorna o cpf ou cnpj do sacado.
     */
    public String getCpfSacado() {
        return cpfSacado;
    }

    /**
     * Seta o cpf ou cnpj do sacado.
     * @param cpfSacado
     * Seta o cpf ou cnpj do sacado.
     */
    public void setCpfSacado(String cpfSacado) {
        this.cpfSacado = cpfSacado;
    }

    /**
     * Retorna o endereco do sacado.
     * @return Retorna o endereco do sacado.
     */
    public String getEnderecoSacado() {
        return enderecoSacado;
    }

    /**
     * Seta o endereco do sacado.
     * @param enderecoSacado
     * Seta o endereco do sacado.
     */
    public void setEnderecoSacado(String enderecoSacado) {
        this.enderecoSacado = enderecoSacado;
    }

    /**
     * Modifica/atribui uma mensagem ao lado do titulo "Intrucoes"
     * @return
     */
    public String getMensagemInstrucoes() {
        return mensagemInstrucoes;
    }

    public void setMensagemInstrucoes(String mensagemInstrucoes) {
        this.mensagemInstrucoes = mensagemInstrucoes;
    }

    /**
     * Retorna o campo para a 1 linha da instrucao.
     * @return Retorna o campo para a 1 linha da instrucao.
     */
    public String getInstrucao1() {
        return instrucao1;
    }

    /**
     * Seta o campo para a 1 linha da instrucao.
     * @param instrucao1
     * Seta o campo para a 1 linha da instrucao.
     */
    public void setInstrucao1(String instrucao1) {
        this.instrucao1 = instrucao1;
    }

    /**
     * Retorna o campo para a 2 linha da instrucao.
     * @return Retorna o campo para a 2 linha da instrucao.
     */
    public String getInstrucao2() {
        return instrucao2;
    }

    /**
     * Seta o campo para a 2 linha da instrucao.
     * @param instrucao2
     * Seta o campo para a 2 linha da instrucao.
     */
    public void setInstrucao2(String instrucao2) {
        this.instrucao2 = instrucao2;
    }

    /**
     * Retorna o campo para a 3 linha da instrucao.
     * @return Retorna o campo para a 3 linha da instrucao.
     */
    public String getInstrucao3() {
        return instrucao3;
    }

    /**
     * Seta o campo para a 3 linha da instrucao.
     * @param instrucao3
     * Seta o campo para a 3 linha da instrucao.
     */
    public void setInstrucao3(String instrucao3) {
        this.instrucao3 = instrucao3;
    }

    /**
     * Retorna o campo para a 4 linha da instrucao.
     * @return Retorna o campo para a 4 linha da instrucao.
     */
    public String getInstrucao4() {
        return instrucao4;
    }

    /**
     * seta o campo para a 4 linha da instrucao.
     * @param instrucao4
     * seta o campo para a 4 linha da instrucao.
     */
    public void setInstrucao4(String instrucao4) {
        this.instrucao4 = instrucao4;
    }

    /**
     * Retorna o campo para a 5 linha da instrucao.
     * @return Retorna o campo para a 5 linha da instrucao.
     */
    public String getInstrucao5() {
        return instrucao5;
    }

    /**
     * Seta o campo para a 5 linha da instrucao.
     * @param instrucao5
     * Seta o campo para a 5 linha da instrucao.
     */
    public void setInstrucao5(String instrucao5) {
        this.instrucao5 = instrucao5;
    }

    /**
     * Retorna o local de pagamento.
     * @return Retorna o local de pagamento.
     */
    public String getLocalPagamento() {
        return localPagamento;
    }

    /**
     * Seta o local de pagamento.
     * @param localPagamento
     * Seta o local de pagamento.
     */
    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    /**
     * Retorna o nome do sacado.
     * @return Retorna o nome do sacado.
     */
    public String getNomeSacado() {
        return nomeSacado;
    }

    /**
     * Seta o nome do sacado.
     * @param nomeSacado
     * Seta o nome do sacado.
     */
    public void setNomeSacado(String nomeSacado) {
        this.nomeSacado = nomeSacado;
    }

    /**
     * Retorna a quantidade da moeda.
     * @return Retorna a quantidade da moeda.
     */
    public String getQtdMoeda() {
        return qtdMoeda;
    }

    /**
     * Seta a quantidade da moeda.
     * @param qtdMoeda
     * Seta a quantidade da moeda.
     */
    public void setQtdMoeda(String qtdMoeda) {
        this.qtdMoeda = qtdMoeda;
    }

    /**
     * Retorna o uf do sacado.
     * @return Retorna o uf do sacado.
     */
    public String getUfSacado() {
        return ufSacado;
    }

    /**
     * Seta o Uf do sacado.
     * @param ufSacado
     * Seta o Uf do sacado.
     */
    public void setUfSacado(String ufSacado) {
        this.ufSacado = ufSacado;
    }
    /** 
     * Retorna o nome da cooperativa
     *
     * @return Retorna o nome da cooperativa
     */ 
    public String getNomeCooperativa() {
        return nomeCooperativa;
    }
    /**     
     * @param nomeCooperativa
     * Seta o nome da cooperativa.
     */
    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
    }
    /** 
     * Retorna o cnpj da cooperativa
     *
     * @return Retorna o cnpj da cooperativa
     */ 
    public String getCnpjCooperativa() {
        return cnpjCooperativa;
    }
    /**     
     * @param cnpjCooperativa
     * Seta o cnpj da cooperativa.
     */
    public void setCnpjCooperativa(String cnpjCooperativa) {
        this.cnpjCooperativa = cnpjCooperativa;
    }
    /** 
     * Retorna o endereco da cooperativa
     *
     * @return Retorna o endereco da cooperativa
     */ 
    public String getEnderecoCooperativa() {
        return enderecoCooperativa;
    }
    /**     
     * @param enderecoCooperativa
     * Seta o endereco da cooperativa.
     */
    public void setEnderecoCooperativa(String enderecoCooperativa) {
        this.enderecoCooperativa = enderecoCooperativa;
    }
    /** 
     * Retorna o cep da cooperativa
     *
     * @return Retorna o cep da cooperativa
     */ 
    public String getCepCooperativa() {
        return cepCooperativa;
    }
    /**     
     * @param cepCooperativa
     * Seta o cep da cooperativa.
     */
    public void setCepCooperativa(String cepCooperativa) {
        this.cepCooperativa = cepCooperativa;
    }
    /**
     * Retorna o bairro da cooperativa
     *
     * @return Retorna o bairro da cooperativa
     */     
    public String getBairroCooperativa() {
        return bairroCooperativa;
    }
    /**     
     * @param bairroCooperativa
     * Seta o bairro da cooperativa.
     */
    public void setBairroCooperativa(String bairroCooperativa) {
        this.bairroCooperativa = bairroCooperativa;
    }
     /**
     * Retorna a cidade da cooperativa
     *
     * @return Retorna a cidade da cooperativa
     */     
    public String getCidadeCooperativa() {
        return cidadeCooperativa;
    }
    /**     
     * @param cidadeCooperativa
     * Seta a cidade da cooperativa.
     */
    public void setCidadeCooperativa(String cidadeCooperativa) {
        this.cidadeCooperativa = cidadeCooperativa;
    }
    /**
     * Retorna a uf da cooperativa
     *
     * @return Retorna a uf da cooperativa
     */     
    public String getUfCooperativa() {
        return ufCooperativa;
    }
    /**     
     * @param ufCooperativa
     * Seta o Uf da cooperativa.
     */
    public void setUfCooperativa(String ufCooperativa) {
        this.ufCooperativa = ufCooperativa;
    }
    /** 
     * Retorna o cnpj do cedente
     *
     * @return Retorna o cnpj do cedente
     */ 
    public String getCnpjCedente() {
        return cnpjCedente;
    }
    /**     
     * @param cnpjCedente
     * Seta o cnpj do cedente.
     */
    public void setCnpjCedente(String cnpjCedente) {
        this.cnpjCedente = cnpjCedente;
    }
    /** 
     * Retorna o endereco do Cedente
     *
     * @return Retorna o endereco do Cedente
     */ 
    public String getEnderecoCedente() {
        return enderecoCedente;
    }
    /**     
     * @param enderecoCedente
     * Seta o endereco do cedente.
     */
    public void setEnderecoCedente(String enderecoCedente) {
        this.enderecoCedente = enderecoCedente;
    }
    /** 
     * Retorna o cep do cedente
     *
     * @return Retorna o cep do cedente
     */ 
    public String getCepCedente() {
        return cepCedente;
    }
    /**     
     * @param cepCedente
     * Seta o cep do cedente.
     */
    public void setCepCedente(String cepCedente) {
        this.cepCedente = cepCedente;
    }
    /**
     * Retorna o bairro da cooperativa
     *
     * @return Retorna o bairro da cooperativa
     */     
    public String getBairroCedente() {
        return bairroCedente;
    }
    /**     
     * @param bairroCedente
     * Seta o bairro do cedente.
     */
    public void setBairroCedente(String bairroCedente) {
        this.bairroCedente = bairroCedente;
    }
     /**
     * Retorna a cidade do cedente
     *
     * @return Retorna a cidade do cedente
     */     
    public String getCidadeCedente() {
        return cidadeCedente;
    }
    /**     
     * @param cidadeCedente
     * Seta a cidade do cedente.
     */
    public void setCidadeCedente(String cidadeCedente) {
        this.cidadeCedente = cidadeCedente;
    }
    /**
     * Retorna a uf do cedente
     *
     * @return Retorna a uf do cedente
     */     
    public String getUfCedente() {
        return ufCedente;
    }
    /**     
     * @param ufCedente
     * Seta o Uf do cedente.
     */
    public void setUfCedente(String ufCedente) {
        this.ufCedente = ufCedente;
    }
    /**
     * Retorna o valor da moeda
     * @return Retorna o valor da moeda
     */
    public String getValorMoeda() {
        return valorMoeda;
    }

    /**
     * Seta o valor da moeda.
     * @param valorMoeda
     * Seta o valor da moeda.
     */
    public void setValorMoeda(String valorMoeda) {
        this.valorMoeda = valorMoeda;
    }

    /**
     * Retorna a segunda linha do local de pagamento
     * @return Retorna a segunda linha do local de pagamento
     */
    public String getLocalPagamento2() {
        return localPagamento2;
    }

    /**
     * Seta a segunda linha do local de pagamento.
     * @param localPagamento2
     * Seta a segunda linha do local de pagamento.
     */
    public void setLocalPagamento2(String localPagamento2) {
        this.localPagamento2 = localPagamento2;
    }

    /**
     * Retorna o campo aceite que por padrao vem com N.
     * @return Retorna o campo aceite que por padrao vem com N.
     */
    public String getAceite() {
        return aceite;
    }

    /**
     * Seta o campo aceite que por padrao vem com N.
     * @param aceite
     * Seta o campo aceite que por padrao vem com N.
     */
    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    /**
     * Retorna o campo especie do documento que por padrao vem com DV
     * @return Retorna o campo especie do documento que por padrao vem com DV
     */
    public String getEspecieDocumento() {
        return especieDocumento;
    }

    /**
     * Seta o campo especie do documento que por padrao vem com DV.
     * @param especieDocumento
     * Seta o campo especie do documento que por padrao vem com DV.
     */
    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    /**
     * Retorna a data do documento.
     * @return Retorna a data do documento.
     */
    public String getDataDocumento() {
        return dataDocumento;
    }

    /**
     * Seta a data do documento.
     * @param dataDocumento
     * Seta a data do documento.
     */
    public void setDataDocumento(String dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    /**
     * Retorna a data do processamento
     * @return Retorna a data do processamento.
     */
    public String getDataProcessamento() {
        return dataProcessamento;
    }

    /**
     * Seta a data do processamento.
     * @param dataProcessamento
     * Seta a data do processamento.
     */
    public void setDataProcessamento(String dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    /**
     * Seta o tipo da moeda
     * @param moeda
     * Seta o tipo da moeda.
     */
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    /**
     * Retorna tipo de saida do arquivo
     * @return Retorna tipo de saida do arquivo
     */
    public String getTipoSaida() {
        return tipoSaida;
    }

    /**
     * Seta o tipo de saida do arquivo (html ou pdf)
     * @param tipoSaida
     * Seta o tipo de saida do arquivo (html ou pdf)
     */
    public void setTipoSaida(String tipoSaida) {
        this.tipoSaida = tipoSaida;
    }

    /**
     * Retorna o número digitável do código de barras
     * @return Retorna o número digitável do código de barras
     */
    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    /**
     * Seta o número digitável do código de barras
     * @param codigoBarrasDividido
     * Seta o número digitável do código de barras
     */
    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    /**
     * Retorna o número do código de barras
     * @return Retorna o número do código de barras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * Seta o número do código de barras
     * @param codigoBarrasDividido
     * Seta o número do código de barras
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * Retorna o quinto campo da linha digitavel do codigo
     * @return Retorna o quinto campo da linha digitavel do codigo
     */
    public String getValorTitulo() {
        String zeros = "0000000000";
        DefaultFormatter formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));

        String valor = "";

        try {
            valor = formatter.valueToString(new Double(getValorCobrado()));
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

        valor = valor.replace(",", "").replace(".", "");
        String valorTitulo = zeros.substring(0, zeros.length() - valor.length()) + valor;

        return valorTitulo;
    }

    public String getValorCobrado() {
        double valor = new Double(getValorBoleto().replace(",", "."));
        valor += (moraMulta != null && !"".equals(moraMulta) ? new Double(moraMulta.replace(",", ".")) : 0);
        valor -= (descontoAbatimento != null && !"".equals(descontoAbatimento) ? new Double(descontoAbatimento.replace(",", ".")) : 0);
        return Double.toString(valor);
    }

    public String getMoraMulta() {
        return moraMulta;
    }

    public void setMoraMulta(String moraMulta) {
        this.moraMulta = moraMulta;
    }
    
    public String getDescontoAbatimento() {
        return descontoAbatimento;
    }

    public void setDescontoAbatimento(String descontoAbatimento) {
        this.descontoAbatimento = descontoAbatimento;
    }

    /**
     * Modulo 10 (212121)\n
     * Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     * @return Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCampo(String campo, int mult) {
        //Esta rotina faz o calcula 212121

        int multiplicador = mult;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i, 1 + i)) * multiplicador;

            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0, 1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1));
            }

            soma_campo = soma_campo + multiplicacao;

            // ALTERADO POR VITOR MOTTA PARA SUBSTITUIR O COMENTARIO ABAIXO
            // valores assumidos: 212121...
            multiplicador = (multiplicador % 2) + 1;
            /*
            if (multiplicador == 2)
            multiplicador = 1;
            else
            multiplicador = 2;
             */

        }
        int dac = 10 - (soma_campo % 10);

        if (dac == 10) {
            dac = 0;
        }

        campo = campo + String.valueOf(dac);

        return campo;
    }

    /**
     * ALTERADO POR VITOR MOTTA - METODO ALTERNATIVO AO METODO getDigitoCampo(String, int)
     * Modulo 10 (212121)\n
     * Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.\n
     * Nao tem a necessidade do mult e possui o mesmo efeito que o m�todo getDigitoCampo(String, int)
     * @return Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCampo(String campo) {
        //Esta rotina faz o calcula 212121

        int multiplicador = 2;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = campo.length(); i > 0; i--) {
            multiplicacao = Integer.parseInt(campo.substring(i - 1, i)) * multiplicador;

            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0, 1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1));
            }
            soma_campo += multiplicacao;

            // valores assumidos: 212121...
            multiplicador = (multiplicador % 2) + 1;
        }
        int dac = 10 - (soma_campo % 10);

        if (dac == 10) {
            dac = 0;
        }

        campo = campo + String.valueOf(dac);

        return campo;
    }

    /**
     * Modulo 11
     * Retorna o digito verificador do codigo de barras (4o campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     * @return Modulo 11 - Retorna o digito verificador do codigo de barras (4o campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCodigoBarras(String campo) {
        //Esta rotina faz o calcula no modulo 11 - 23456789

        int multiplicador = 4;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i, 1 + i)) * multiplicador;

            soma_campo = soma_campo + multiplicacao;

            // ALTERADO POR VITOR MOTTA PARA SUBSTITUIR O COMENTARIO ABAIXO
            // valores assumidos: 43298765432987...
            multiplicador = ((multiplicador + 5) % 8) + 2;
        }

        int dac = 11 - (soma_campo % 11);

        if (dac == 0 || dac == 1 || dac > 9) {
            dac = 1;
        }

        campo = String.valueOf(dac);

        return campo;
    }

    /**
     * Retorna o array de descricoes para serem colocadas na parte de cima do boleto.
     * @return Retorna o array de descricoes para serem colocadas na parte de cima do boleto.
     */
    public List<String> getDescricoes() {
        return descricoes;
    }

    /**
     * Seta um array de descricoes para serem colocadas na parte de cima do boleto
     * @param descricoes
     * Seta um array de descricoes para serem colocadas na parte de cima do boleto
     */
    public void setDescricoes(List<String> descricoes) {
        this.descricoes = descricoes;
    }

    /**
     * Retorna o codigo do cliente. Caseo seja codigo do cliente da caixa este campo deve conter 6 posicoes.
     * @return Retorna o codigo do cliente.
     */
    public String getCodCliente() {
        return codCliente;
    }

    /**
     * Seta um codigo de cliente. Alguns bancos pedem este campo.
     * @param codCliente
     * Seta um codigo de cliente. Alguns bancos pedem este campo
     */
    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    /**
     * Retorna o IOS do Banco.
     * @return Retorna o IOS do Banco.
     */
    public String getIOS() {
        return getIos();
    }

    /**
     * Seta um codigo de IOS do Banco.
     * @param codCliente
     * Seta um codigo de IOS do Banco.
     */
    public void setIOS(String ios) {
        this.setIos(ios);
    }

    /**
     * Retorna o numero do convenio
     * @return Retorna o numero do convenio
     */
    public String getNumConvenio() {
        return numConvenio;
    }

    /**
     * Seta o numero do convenio
     * @param numConvenio
     * Seta o numero do convenio
     */
    public void setNumConvenio(String numConvenio) {
        this.numConvenio = numConvenio;
    }

    public String getDvAgencia() {
        return dvAgencia;
    }

    public void setDvAgencia(String dvAgencia) {
        this.dvAgencia = dvAgencia;
    }

    public String getDvContaCorrente() {
        return dvContaCorrente;
    }

    public void setDvContaCorrente(String dvContaCorrente) {
        this.dvContaCorrente = dvContaCorrente;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
    }

    /**
     * recupera o codigo de operacao
     * @return Retorna o codigo de operacao
     */
    public String getCodigoOperacao() {
        return codigoOperacao;
    }

    /**
     * Seta o codigo de operacao - necessario somente para caixa
     * @param codigoOperacao
     * Seta o codigo da operacao
     */
    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    /**
     * Recupera o codigo fornecido pela agencia
     * @return Recupera o codigo fornecido pela agencia
     */
    public String getCodigoFornecidoAgencia() {
        return codigoFornecidoAgencia;
    }

    /**
     * Seta o codigo fornecido pela agencia - necessario somente para caixa.
     * @param codigoFornecidoAgencia
     * Seta o codigo fornecido pela agencia
     */
    public void setCodigoFornecidoAgencia(String codigoFornecidoAgencia) {
        this.codigoFornecidoAgencia = codigoFornecidoAgencia;
    }

    /**
     * Recupara o numero do documento
     * @return Recupera o numero do documento
     */
    public String getNoDocumento() {
        return noDocumento;
    }

    /**
     * Seta o numero do documento
     * @param noDocumento
     * Seta o numero do documento
     */
    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    /**
     * Completa uma String com zeros a esquerda
     * @param str String a ser completada
     * @param qtdZeros Quantidade de zeros que deseja a esquerda
     * Completa uma String com zeros a esquerda
     */
    public String completaZerosEsquerda(String str, int qtdZeros) {
        String zeros = "000000000000000000000000000000000000000";
        int dif = qtdZeros - str.length();

        return zeros.substring(0, dif) + str;
    }

    /**
     * Pega o caminho da imagem de marketing para ser colocada na parte superior do boleto
     */
    public String getImagemMarketing() {
        return imagemMarketing;
    }

    /**
     * Seta a imagem de marketing que ira na parte superior do boleto
     * @param imagemMarketing Caminho da imagem
     */
    public void setImagemMarketing(String imagemMarketing) {
        this.imagemMarketing = imagemMarketing;
    }

    /**
     * Recupar o digito verificador do nosso numero
     * @return Recupera o digito verificador do nosso numero
     * @author Gladyston Batista
     */
    public String getDvNossoNumero() {
        return (dvNossoNumero != null) ? dvNossoNumero : "";
    }

    /**
     * Seta o digito verificar do nosso numero
     * @author Gladyston Batista
     */
    public void setDvNossoNumero(String dvNossoNumero) {
        this.dvNossoNumero = dvNossoNumero;
    }

    /**
     * Recupar o digito verificador fornecido pela agencia
     * @return Recupar o digito verificador fornecido pela agencia
     * @author Gladyston Batista
     */
    public String getDvCodigoFornecidoAgencia() {
        return dvCodigoFornecidoAgencia;
    }

    /**
     * Seta o digito verificar do codigo fornecido pela Agencia
     * @author Gladyston Batista
     */
    public void setDvCodigoFornecidoAgencia(String dvCodigoFornecidoAgencia) {
        this.dvCodigoFornecidoAgencia = dvCodigoFornecidoAgencia;
    }

    /**
     * 	getModulo10
     *  @param String value
     * 	@return String dac
     *  @author Mario Grigioni
     */
    public String getModulo10(String campo) {

        int multiplicador = 2;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = campo.length(); i > 0; i--) {
            multiplicacao = Integer.parseInt(campo.substring(i - 1, i)) * multiplicador;

            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0, 1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1, 2));
            }
            soma_campo = soma_campo + multiplicacao;

            if (multiplicador == 1) {
                multiplicador = 2;
            } else {
                multiplicador = 1;
            }
        }
        int dac = 10 - (soma_campo % 10);

        if (dac > 9) {
            dac = 0;
        }

        return ((Integer) dac).toString();
    }

    /**
     * 	getModulo11
     *  Modulo 11 - 234567   (type = 7)
     *  Modulo 11 - 23456789 (type = 9)
     *  @param String value
     *  @param int type
     * 	@return int dac
     *  @author Mario Grigioni
     */
    public String getModulo11(String campo, int type) {
        int multiplicador = 2;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = campo.length(); i > 0; i--) {
            multiplicacao = Integer.parseInt(campo.substring(i - 1, i)) * multiplicador;

            soma_campo = soma_campo + multiplicacao;

            multiplicador++;
            if (multiplicador > 7 && type == 7) {
                multiplicador = 2;
            } else if (multiplicador > 9 && type == 9) {
                multiplicador = 2;
            }
        }

        int dac = 11 - (soma_campo % 11);

        if (dac > 9 && type == 7) {
            dac = 0;
        } else if ((dac == 0 || dac == 1 || dac > 9) && type == 9) {
            dac = 1;
        }

        return ((Integer) dac).toString();
    }
    
    
    public String calculaDigitoVerificadorNossoNumero(String campo){
        int soma = 0;
        int resto = 0;
        int multiplicador = 2;
        int aux =  0;
        int digito = 0;
       
        for(int i =0; i < campo.length() ; i++){
            aux = Integer.parseInt(campo.substring(i, i+1));
            soma += aux * multiplicador;
            
            if(multiplicador == 2){
                multiplicador = 9;
            }else {
                multiplicador--;
            }
        }
        
        resto = soma % 11;
        digito = 11 - resto;
                
        if(digito > 9){
            digito = 0;
        }
        
        return Integer.toString(digito);
    }
    
}
