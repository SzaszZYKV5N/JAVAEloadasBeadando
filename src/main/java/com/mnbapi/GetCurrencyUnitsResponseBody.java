
package com.mnbapi;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetCurrencyUnitsResponseBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="GetCurrencyUnitsResponseBody">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GetCurrencyUnitsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCurrencyUnitsResponseBody", propOrder = {
    "getCurrencyUnitsResult"
})
public class GetCurrencyUnitsResponseBody {

    @XmlElementRef(name = "GetCurrencyUnitsResult", namespace = "http://www.mnb.hu/webservices/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> getCurrencyUnitsResult;

    /**
     * Gets the value of the getCurrencyUnitsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGetCurrencyUnitsResult() {
        return getCurrencyUnitsResult;
    }

    /**
     * Sets the value of the getCurrencyUnitsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGetCurrencyUnitsResult(JAXBElement<String> value) {
        this.getCurrencyUnitsResult = value;
    }

}
