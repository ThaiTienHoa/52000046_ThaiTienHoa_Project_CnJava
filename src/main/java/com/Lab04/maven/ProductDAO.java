package com.Lab04.maven;

import java.sql.*;
import java.util.ArrayList;


public class ProductDAO {
    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    public int insert(Product p) {
        // TODO Auto-generated method stub
        try {
            //Bước 1: Tạo kết nối
            Connection connection=MySQLConnUtils.getConnection();
            //Bước 2: Tạo đối tượng statement
//            Statement st=connection.createStatement();
            //Bước 3: Thực thi câu lênh SQL
//            String sql="INSERT INTO lab02(id, name, price, color)"
//                    + "VALUES ('" + p.getId() + "' , '" + p.getName() + "' , " + p.getPrice() + " , " + p.getColor() +")";

            String sql = "insert into lab02(id, name, price, color) values (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            st.setString(2, p.getName());
            st.setInt(3, (int) p.getPrice());
            st.setString(4, p.getColor());

            int ketqua =st.executeUpdate();

            //Bước 4: xử lý kết quả
//            System.out.println("Bạn đã thực thi : "+sql);
//            System.out.println("Số dòng vừa thêm: "+ketqua );

            //Bước 5 ngắt kết nối
            MySQLConnUtils.closeConnection(connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> selectAll(){
        ArrayList<Product> productList = new ArrayList<Product>();
        try{
            Connection connection = MySQLConnUtils.getConnection();
            Statement st  = connection.createStatement();
            String sql = "select * from lab02";
            ResultSet resultSet = st.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name  = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String color = resultSet.getString("color");
                Product product = new Product(id, name, price, color);
                productList.add(product);
            }
            MySQLConnUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product readById (int productId){
        Product product = null;
        try{
            Connection connection = MySQLConnUtils.getConnection();
            String sql = "Select * from lab02 Where id  = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name  = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String color = resultSet.getString("color");
                product = new Product(id, name, price, color);
            }
            MySQLConnUtils.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
    return product;
    }

    public int update (Product p){
        try{
            Connection connection = MySQLConnUtils.getConnection();
            String sql = "Update lab02 set name = ?, price = ?, color = ?" + "Where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, p.getName());
            st.setInt(2, (int) p.getPrice());
            st.setString(3, p.getColor());
            st.setInt(4, p.getId());
            int result = st.executeUpdate();
            MySQLConnUtils.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int productId){
        try{
            Connection connection = MySQLConnUtils.getConnection();
            String sql = "Delete from lab02 Where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            int result = st.executeUpdate();
            MySQLConnUtils.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
