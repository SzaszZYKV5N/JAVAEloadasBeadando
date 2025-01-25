package com.example.menu;

import com.example.mnbapi.MNBArfolyamServiceSoap;
import com.example.mnbapi.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import com.example.mnbapi.MNBArfolyamServiceSoapImpl;
import javafx.scene.chart.XYChart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MnbHelper {

    private final MNBArfolyamServiceSoap service;

    public MnbHelper() {
        try {
            // Biztosítjuk, hogy az implementáció helyesen megy
            MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
            this.service = impl.getCustomBindingMNBArfolyamServiceSoap();
        } catch (Exception e) {
            throw new RuntimeException("Nem sikerült inicializálni a webszolgáltatást.", e);
        }
    }

    public Map<String, String> getAllInfo() {
        Map<String, String> allInfo = new HashMap<>();
        try {
            allInfo.put("Info", service.getInfo());
            allInfo.put("Currencies", service.getCurrencies());
            allInfo.put("Exchange Rate", service.getCurrentExchangeRates());
            allInfo.put("Data Interval", service.getDateInterval());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allInfo;
    }

    public List<String> getCurrencies() {
        List<String> penznemek = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            String currXML = service.getCurrencies();
            InputStream inputStream = new ByteArrayInputStream(currXML.getBytes(StandardCharsets.UTF_8));
            Document doc = builder.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList currencies = doc.getElementsByTagName("Curr");
            for (int i = 0; i < currencies.getLength(); i++) {
                Element currency = (Element) currencies.item(i);
                penznemek.add(currency.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return penznemek;
    }

    public String getExchangeRate(String currency, String startDate, Boolean csudaszep) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage, ParserConfigurationException, IOException, SAXException {
        String exch = service.getExchangeRates(startDate, startDate, currency);
        if (!csudaszep) {
            return exch;
        } else {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(exch.getBytes(StandardCharsets.UTF_8));
            Document doc = builder.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList currencyRates = doc.getElementsByTagName("Rate");
            if (currencyRates.getLength() > 0) {
                Node rateNode = currencyRates.item(0);
                String rateValue = rateNode.getTextContent().trim();
                return rateValue;
            } else {
                throw new IllegalArgumentException("Nem található <Rate> elem az XML-ben.");
            }
        }
    }

    public List<XYChart.Data<String, Number>> getExchangeRates(String currency, String startDate, String endDate) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage, ParserConfigurationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<XYChart.Data<String, Number>> dataList = new ArrayList<>();
        LocalDate startDateTime = LocalDate.parse(startDate, formatter);
        LocalDate endDateTime = LocalDate.parse(endDate, formatter);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        while (!startDateTime.isAfter(endDateTime)) {
            try {
                String currentDate = startDateTime.format(formatter);
                String response = service.getExchangeRates(currentDate, currentDate, currency);
                Document document = builder.parse(new ByteArrayInputStream(response.getBytes()));
                document.getDocumentElement().normalize();
                NodeList rateNodes = document.getElementsByTagName("Rate");
                if (rateNodes.getLength() > 0) {
                    Node rateNode = rateNodes.item(0);
                    NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
                    Number parsedNumber = format.parse(rateNode.getTextContent());
                    double value = parsedNumber.doubleValue();
                    dataList.add(new XYChart.Data<>(currentDate, value));
                }
                startDateTime = startDateTime.plusDays(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(dataList);
        return dataList;
    }
}