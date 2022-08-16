package br.com.rldcarvalho.topfilmes;

import java.util.List;

public interface JsonParser {
    List<? extends Content> parse();
}
