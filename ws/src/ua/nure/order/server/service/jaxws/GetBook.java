
package ua.nure.order.server.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.4
 * Thu Feb 11 15:07:28 EET 2016
 * Generated source version: 3.1.4
 */

@XmlRootElement(name = "getBook", namespace = "http://service.server.order.nure.ua/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBook", namespace = "http://service.server.order.nure.ua/")

public class GetBook {

    @XmlElement(name = "id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int newId)  {
        this.id = newId;
    }

}

