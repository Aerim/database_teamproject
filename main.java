package database;

import java.util.Scanner;

public class main {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      DBConnection connection=new DBConnection();
      //System.out.println("�����ڿ��� :"+connection.isAdmin("admin", "admin"));
      connection.sqlTest();
      
   }

}