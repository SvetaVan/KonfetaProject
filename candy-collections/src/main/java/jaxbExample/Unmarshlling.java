package jaxbExample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.File;

public class Unmarshlling {
    public static void main(String[] args) throws JAXBException {

    File file = new File("/home/svetka/Рабочий стол/file1.xml");
    JAXBContext jaxbContext=  JAXBContext.newInstance(Customer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Customer customer1 = (Customer) unmarshaller.unmarshal(file);
        System.out.println(customer1.getId()+" "+customer1.getName()+" "+customer1.getAge());
    }
}
