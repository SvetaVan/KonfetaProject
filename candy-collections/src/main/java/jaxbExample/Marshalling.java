package jaxbExample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Stack;

public class Marshalling {
    public static void main(String[] args) throws JAXBException {
        Customer customer = new Customer(1,"Sveta", 29);
        File file = new File("/home/svetka/Рабочий стол/file1.xml");
        toXMLFile(customer, file);


    }

    private static void toXMLFile(Customer customer, File file) throws JAXBException {
        //create jaxb context
        JAXBContext jaxbContext=  JAXBContext.newInstance(Customer.class);
        //create marshaller object
        Marshaller marshaller = jaxbContext.createMarshaller();
        //setting the property to show xml format output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer,file);
        marshaller.marshal(customer, System.out);
    }


}
