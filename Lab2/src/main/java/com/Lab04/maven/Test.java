package com.Lab04.maven;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Test {
    public static void main(String[] args) throws SQLException {
        int choice = 1;
        do {
            System.out.println("Product Management Menu");
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Chon: ");
            choice = sc.nextInt();
            int id, price;
            String name, color;
            Product p;
            switch (choice) {
                case 1:
                    getAllList();
                    break;
                case 2:
                    System.out.print("Nhap ID san pham can tim: ");
                    id = sc.nextInt();
                    getProductById(id);
                    break;
                case 3:
                    System.out.print("Nhap Id san pham: ");
                    id = sc.nextInt();
                    System.out.print("Nhap ten san pham: ");
                    name = sc.next();
                    System.out.print("Nhap gia san pham: ");
                    price = sc.nextInt();
                    System.out.print("Nhap mau san pham: ");
                    color = sc.next();
                    Product product = new Product(id, name, price, color);
                    insert(product);
                    break;
                case 4:
                    System.out.print("Nhap Id san pham can thay doi: ");
                    id = sc.nextInt();
                    System.out.print("Nhap ten san pham: ");
                    name = sc.next();
                    System.out.print("Nhap gia san pham: ");
                    price = sc.nextInt();
                    System.out.print("Nhap mau san pham: ");
                    color= sc.next();
                    product = new Product(id, name, price, color);
                    updateProduct(product);
                    break;
                case 5:
                    System.out.print("Nhap Id san pham can xoa: ");
                    id = sc.nextInt();
                    deleteProduct(id);
                    break;
                default:
                    System.exit(0);
            }
        } while (choice != 6);
    }

    public static void insert(Product product) throws SQLException {
        ProductDAO.getInstance().insert(product);
    }

    public static void getAllList(){
        ArrayList<Product> productsList = ProductDAO.getInstance().selectAll();
        for (Product product : productsList){
            System.out.println(product);
        }
    }

    public static void getProductById (int productId){
        Product product = ProductDAO.getInstance().readById(productId);
        System.out.print(product);
    }
    public static void updateProduct(Product p){
        ProductDAO.getInstance().update(p);
    }
    public static void deleteProduct(int productId){
       ProductDAO.getInstance().delete(productId);
    }

}

