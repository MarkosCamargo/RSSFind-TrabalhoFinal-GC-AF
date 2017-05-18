package br.com.udesc.ceavi.findnews.leitorrss;

import br.com.udesc.ceavi.findnews.modelo.Noticia;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marcos
 */
public interface IRSS {

    /**
     * Metodo que busca nos sites cadastrados as noticias que contenham as
     * palavras chaves cadastradas
     *
     *
     * @return NOTICIAS - Um array com todas as noticias encontradas
     */
    public List<Noticia> verificaNoticiasManual();

}
