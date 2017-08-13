package br.com.ricardosander.meupetshop.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Representa uma classe responsável por processar uma requisição http.
 */
public interface HttpHandler {

    /**
     * Processa a requisição http.
     * @param req Requisição http.
     * @param resp Resposta http.
     * @return Destino após processamento.
     */
    String handler(HttpServletRequest req, HttpServletResponse resp);

}