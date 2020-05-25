/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productlist;

/**
 *
 * @author cindylee
 */

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class ProductList {

    /**
     * @param args the command line arguments
     */
    static HashMap<Integer, Product> items;
    
    public static void main(String[] args) {
        Connection conn = null;
        items = new HashMap<Integer, Product>();

        try{
            String url = "jdbc:mariadb://localhost:3306/cs3913";
            String dbuser = "CS3913";
            String password = "GettingAnA+";
            conn = DriverManager.getConnection(url,dbuser,password);
            Statement s = conn.createStatement();
            ResultSet products = s.executeQuery("select * from Products;");
            
            while (products.next()){
                int id = products.getInt(1);
                String name = products.getString(2);
                items.put(id, new Product(id, name));
            }
            products.close();
            
            for (Integer pid: items.keySet()){
                ResultSet reviews = s.executeQuery("select Rating from Reviews where PID=" + pid);
                while (reviews.next()){
                    int reviewRating = reviews.getInt("Rating");
                    items.get(pid).addRating(reviewRating);
                }
                reviews.close();
            }
            s.close();
            conn.close();
            
            displayResult();
        }
        catch (Exception e){}
    }
    
    static void displayResult(){
        Product[] prods = new Product[items.size()];
        int index = 0;
        for (Integer key: items.keySet()){
            prods[index] = items.get(key);
            index++;
        }

        int compareSize = prods.length;
        while (compareSize != 0){
            int indexMax = 0;
            double average = 0.0;
            for (int pos = 0; pos < compareSize; pos++){
                double prodAvg = prods[pos].getAverage();
                if (prodAvg > average){
                    average = prodAvg;
                    indexMax = pos;
                }
            }
            System.out.println(prods[indexMax].name);
            Product end = prods[compareSize - 1];
            prods[compareSize - 1] = prods[indexMax];
            prods[indexMax] = end;
            compareSize--;
        }
    }
}


class Product{
    String name;
    int pid;
    ArrayList<Integer> ratings;
    
    Product(int id, String productName){
        pid = id;
        name = productName;
        ratings = new ArrayList<Integer>();
    }
    
    void addRating(int newRating){
        ratings.add(newRating);
    }
    
    double getAverage(){
        if (ratings.isEmpty()){
            return 0;
        }
        int sum = 0;
        for (int index = 0 ; index < ratings.size(); index++){
            sum += ratings.get(index);
        }
        return sum / (ratings.size() * 1.0);
    }
}