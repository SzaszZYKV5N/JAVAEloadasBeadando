
package com.mnbapi;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetDateIntervalResponseBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="GetDateIntervalResponseBody">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GetDateIntervalResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDateIntervalResponseBody", propOrder = {
    "getDateIntervalResult"
})
public class GetDateIntervalResponseBody {

    @XmlElementRef(name = "GetDateIntervalResult", namespace = "http://www.mnb.hu/webservices/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> getDateIntervalResult;

    /**
     * Gets the value of the getDateIntervalResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGetDateIntervalResult() {
        return getDateIntervalResult;
    }

    /**
     * Sets the value of the getDateIntervalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGetDateIntervalResult(JAXBElement<String> value) {
        this.getDateIntervalResult = value;
    }

}
