package com.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Currencies {

    public static String getCurrencies(CurrenciesModel currenciesModel) throws IOException {
//        URL url = null;
//        try {
//            url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Scanner scanner = new Scanner((InputStream) url.getContent());
//
//        String res="";
//
//        while (scanner.hasNext()){
//            res+=scanner.nextLine();
//        }

        StringBuilder sb = null;
        URL url;
        URLConnection urlCon;

        try {
            url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
            urlCon = url.openConnection();
            BufferedReader in = null;
            if (urlCon.getHeaderField("Content-Encoding") != null
                    && urlCon.getHeaderField("Content-Encoding").equals("gzip")) {
                LOGGER.info("reading data from URL as GZIP Stream");
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(urlCon.getInputStream())));
            } else {
                LOGGER.info("reading data from URL as InputStream");
                in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            }
            String inputLine;
            sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("organizations");
        JSONObject jobj = jsonArray.getJSONObject(2);
        currenciesModel.setName(jobj.getString("title"));
        JSONObject currenciesJSON = jobj.getJSONObject("currencies");

        JSONObject eurJSON = currenciesJSON.getJSONObject("EUR");
        currenciesModel.setEurAsk(eurJSON.getString("ask"));
        currenciesModel.setEurBid(eurJSON.getString("bid"));

        JSONObject rubJSON=currenciesJSON.getJSONObject("RUB");
        currenciesModel.setRubAsk(rubJSON.getString("ask"));
        currenciesModel.setRubBid(rubJSON.getString("bid"));

        JSONObject usdJSON=currenciesJSON.getJSONObject("USD");
        currenciesModel.setUsdAsk(usdJSON.getString("ask"));
        currenciesModel.setUsdBid(usdJSON.getString("bid"));


return "Bank -> "+currenciesModel.getName()+"\n"+
        "EUR:\n"+
        "ASK-> "+currenciesModel.getEurAsk()+"\t"+"BIL-> "+currenciesModel.getEurBid()+"\n"+
        "RUB:\n"+
        "ASK-> "+currenciesModel.getRubAsk()+"\t"+"BIL-> "+currenciesModel.getRubBid()+"\n"+
        "USD:\n"+
        "ASK-> "+currenciesModel.getUsdAsk()+"\t"+"BIL-> "+currenciesModel.getUsdBid();
    }
}
