package br.com.rldcarvalho.topfilmes;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static String convertJsonInString(BufferedReader bufferedReader) throws IOException {
        String answer;
        String jsonInString = "";
        while ((answer = bufferedReader.readLine()) != null){
            jsonInString += answer;
        }
        return jsonInString;
    }
}
