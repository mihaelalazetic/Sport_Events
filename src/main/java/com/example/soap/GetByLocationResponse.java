//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.06.20 at 02:54:09 PM CEST 
//


package com.example.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sportEvents" type="{http://www.webs_32_2017.com/soap}sportEvents"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sportEvents"
})
@XmlRootElement(name = "getByLocationResponse")
public class GetByLocationResponse {

    @XmlElement(required = true)
    protected SportEvents sportEvents;

    /**
     * Gets the value of the sportEvents property.
     * 
     * @return
     *     possible object is
     *     {@link SportEvents }
     *     
     */
    public SportEvents getSportEvents() {
        return sportEvents;
    }

    /**
     * Sets the value of the sportEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link SportEvents }
     *     
     */
    public void setSportEvents(SportEvents value) {
        this.sportEvents = value;
    }

}
