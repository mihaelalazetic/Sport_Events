//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.06.20 at 02:54:09 PM CEST 
//


package com.example.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sportType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sportType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BASKETBALL"/>
 *     &lt;enumeration value="MULTI_SPORT"/>
 *     &lt;enumeration value="RUGBY"/>
 *     &lt;enumeration value="SOCCER"/>
 *     &lt;enumeration value="TENNIS"/>
 *     &lt;enumeration value="VOLLEYBALL"/>
 *     &lt;enumeration value="HANDBALL"/>
 *     &lt;enumeration value="SKIING"/>
 *     &lt;enumeration value="BASEBALL"/>
 *     &lt;enumeration value="BOXING"/>
 *     &lt;enumeration value="HOCKEY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sportType")
@XmlEnum
public enum SportType {

    BASKETBALL,
    MULTI_SPORT,
    RUGBY,
    SOCCER,
    TENNIS,
    VOLLEYBALL,
    HANDBALL,
    SKIING,
    BASEBALL,
    BOXING,
    HOCKEY;

    public String value() {
        return name();
    }

    public static SportType fromValue(String v) {
        return valueOf(v);
    }

}
