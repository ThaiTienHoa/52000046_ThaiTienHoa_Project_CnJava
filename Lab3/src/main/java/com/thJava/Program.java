package com.thJava;


//import java.math.BigDecimal;
import java.util.List;


import DAO.ManufactureDAO;
import DAO.PhoneDAO;
import org.hibernate.Session;

import database.HibernateUtils;
import POJO.Manufacture;
import POJO.Phone;

public class Program {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Session session = HibernateUtils.getFactory().openSession();
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();

//        Ham add phones
//        Phone phone1 = new Phone(3, "iPhone 13 Pro ", 30000000, "grey", "USA", 50000);
//        phoneDAO.add(phone1);

//        Ham getid
//        System.out.println("San pham theo id la: ");
//        Phone phone2 = phoneDAO.getPhoneById(1);
//        System.out.println(phone2);
//
//
//////      Ham getall phone
//        System.out.println("Tat ca san pham la: ");
//        List<Phone> phones = phoneDAO.getAllPhones();
//        for (Phone phone : phones) {
//            System.out.println(phone);
//        }
//
//
////        Ham delete by id
////        phoneDAO.removeById(3);
//
//
////        Ham delete by object
////        List<Phone> p = phoneDAO.getAllPhones();
////        if (!p.isEmpty()) {
////            Phone phone1 = p.get(0);
////            phoneDAO.remove(phone1);
////        }
//
////        Ham update
////        List<Phone> p = phoneDAO.getAllPhones();
////        if (!p.isEmpty()) {
////            Phone phone1 = p.get(0);
////            phone1.setCountry("Japan");
////            phoneDAO.update(phone1);
////            session.close();
////        }
//
////        Ham tra ve phone co gia cao nhat
//        Phone phoneWithHighestSellingPrice = phoneDAO.getPhoneWithHighestSellingPrice();
//        System.out.println("Phone with highest selling price: " + phoneWithHighestSellingPrice);
//
//        // Ham sort phones by country name, if two phones have the same country name , sort descending by price
//        List<Phone> sortNameCountry = phoneDAO.getPhonesSortedByCountryAndPrice();
//        System.out.println("Phones sorted by country and price:");
//        for (Phone phone : sortNameCountry) {
//            System.out.println(phone);
//        }
//
//        // Ham kiem tra co san pham nao tren 50tr khong
//        boolean hasPhonePricedAbove50Million = phoneDAO.isAnyPhonePricedAbove50Million();
//        if (hasPhonePricedAbove50Million) {
//            System.out.println("There is at least one phone priced above 50 million VND.");
//        } else {
//            System.out.println("There are no phones priced above 50 million VND.");
//        }
//
//        // Ham kiem tra color and price
//        Phone result = phoneDAO.getFirstPinkPhoneOver15Million();
//        if (result == null) {
//            System.out.println("No phone found with color 'Pink' and a selling price over 15 million.");
//        } else {
//            System.out.println("First phone with color 'Pink' and a selling price over 15 million:");
//            System.out.println(result.toString());
//        }
//            Ham add Manufacture
//        Manufacture manufacture = new Manufacture(2, "Apple", "USA", 60000);
//        ManufactureDAO.add(manufacture);

//        Ham getManuById
//        System.out.println("San pham theo id la: ");
//        Manufacture manufacture1 = manufactureDAO.getManufactureById(1);
//        System.out.println(manufacture1);
//
////        Ham getall Manufactures
//        System.out.println("Tat ca san pham la: ");
//        List<Manufacture> manufactures = manufactureDAO.getAllManufactures();
//        for (Manufacture m : manufactures) {
//            System.out.println(m);
//        }

//        Ham delete maunufacture by id
//        manufactureDAO.removeById(2);

//        Ham delete by object
//        List<Manufacture> manufactures1 = manufactureDAO.getAllManufactures();
//        if (!manufactures1.isEmpty()) {
//            Manufacture m = manufactures1.get(0);
//            manufactureDAO.remove(m);
//        }

//        Ham update manufacture
//        List<Manufacture> m = manufactureDAO.getAllManufactures();
//        if (!m.isEmpty()) {
//            Manufacture manufacture2 = m.get(0);
//            manufacture2.setLocation("Japan");
//            manufactureDAO.update(manufacture2);
//            session.close();
//        }

//        Ham check employee above 100e
        boolean a = ManufactureDAO.checkAbove100E();
        if (a) {
            System.out.println("There are manufacturers have more than 100 employees.");
        } else {
            System.out.println("There are no manufacturers have more than 100 employees.");
        }

//        Ham tinh tong employee
            int sumOfAllEmployees = (int) manufactureDAO.sumOfAllEmployees();
            System.out.println("Sum of all employees of the manufactures: " + sumOfAllEmployees);

//        Ham check san pham dua tren country
//        Manufacture lastUSManufacturer = ManufactureDAO.getLastUSManufacturer();
//        System.out.println("Last US-based manufacturer: " + lastUSManufacturer.getName());

    }


}



