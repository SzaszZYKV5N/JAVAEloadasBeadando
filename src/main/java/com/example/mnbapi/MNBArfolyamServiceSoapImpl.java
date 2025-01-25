package com.example.mnbapi;



import jakarta.xml.ws.*;

import javax.xml.*;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "MNBArfolyamServiceSoapImpl", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://www.mnb.hu/arfolyamok.asmx?wsdl")
public class MNBArfolyamServiceSoapImpl extends Service {

    private static final URL MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION;
    private static final WebServiceException MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION;
    private static final QName MNBARFOLYAMSERVICESOAPIMPL_QNAME = new QName("http://tempuri.org/", "MNBArfolyamServiceSoapImpl");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://www.mnb.hu/arfolyamok.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION = url;
        MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION = e;
    }

    public MNBArfolyamServiceSoapImpl() {
        super(__getWsdlLocation(), MNBARFOLYAMSERVICESOAPIMPL_QNAME);
    }

    public MNBArfolyamServiceSoapImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), MNBARFOLYAMSERVICESOAPIMPL_QNAME, features);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation) {
        super(wsdlLocation, MNBARFOLYAMSERVICESOAPIMPL_QNAME);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MNBARFOLYAMSERVICESOAPIMPL_QNAME, features);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "CustomBinding_MNBArfolyamServiceSoap")
    public MNBArfolyamServiceSoap getCustomBindingMNBArfolyamServiceSoap() {
        return super.getPort(new QName("http://tempuri.org/", "CustomBinding_MNBArfolyamServiceSoap"), MNBArfolyamServiceSoap.class);
    }

    @WebEndpoint(name = "CustomBinding_MNBArfolyamServiceSoap")
    public MNBArfolyamServiceSoap getCustomBindingMNBArfolyamServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "CustomBinding_MNBArfolyamServiceSoap"), MNBArfolyamServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION != null) {
            throw MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION;
        }
        return MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION;
    }
}