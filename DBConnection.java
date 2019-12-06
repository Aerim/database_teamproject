package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class DBConnection {

   private Connection con;
   private Statement st;
   private ResultSet rs;

   public DBConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzion?serverTimezone=UTC&characterEncoding=UTF-8", "root", "root");
         st = con.createStatement();

      } catch (Exception e) {
         System.out.println("���� ����" + e.getMessage());
      }
   }
   public static String lpad(String str, int len, String string) {
        String result = str;
        int templen   = len - result.length();

        for (int i = 0; i < templen; i++){
              result = result+string;
        }
        
        return result;
    }
   
   // �̺�Ʈ�� Ÿ�Կ� �°� ��ġ���ִ� �Լ�
      private void eventSearch(String userID) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String data;
         String type1 = "��Ŀ��";
         String type2 = "���۷���";
         String type3 = "������";
         String SQL;
         System.out.println("------------------");
         System.out.println("��� Ÿ���� �����Ͻÿ�.");
         System.out.println("1. " + type1);
         System.out.println("2. " + type2);
         System.out.println("3. " + type3);
         System.out.print(">>");
         int typenum = input.nextInt();
         switch (typenum) {
         case 1: {

            String type = type1;
            SQL = "create view type as select * from Event where type='" + type + "'";
            try {
               boolean isOk = st.execute(SQL);
            } catch (Exception e) {

            }
            eventSearchFilter(userID, type);
            break;
         }
         case 2: {
            String type = type2;
            SQL = "create view type as select * from Event where type='" + type + "'";
            try {
               boolean isOk = st.execute(SQL);
            } catch (Exception e) {

            }
            eventSearchFilter(userID, type);
            break;
         }
         case 3: {
            String type = type3;
            SQL = "create view type as select * from Event where type='" + type + "'";
            try {
               boolean isOk = st.execute(SQL);
            } catch (Exception e) {

            }
            eventSearchFilter(userID, type);
            break;
         }
         default:
            break;
         }
      }

      // Ÿ���� �Է¹����� � ���͸����� �˻��Ұ������� ���� �Լ�
      private void eventSearchFilter(String userID, String type) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String data;
         String SQL;
         System.out.println("� �˻��� �Ͻðڽ��ϱ�?");
         System.out.println("1. ��¥��");
         System.out.println("2. ������");
         System.out.println("3. �оߺ�");
         System.out.print(">>");
         int searchNum = input.nextInt();
         switch (searchNum) {
         case 1: {
            System.out.print("��¥�� �Է��ϼ��� ����)2019-11-11 : ");
            data = input.next();
            int i = -1;
            SQL = "SELECT * FROM type WHERE date = '" + data + "';";
            try {
               rs = st.executeQuery(SQL);

               System.out.println();
               HashMap<Integer, String> map = new HashMap<Integer, String>();
               while (rs.next()) {
                  System.out.println(++i + " | " + rs.getString("name") + " | " + rs.getString("type") + " | "
                        + rs.getString("field") + " | " + rs.getString("location") + " | " + rs.getString("date")
                        + " | " + rs.getString("period") + " | " + rs.getString("people") + " | "
                        + rs.getString("fee") + " | " + rs.getString("URL"));

                  map.put(i, rs.getString("name"));
               }
               SQL = "drop view type";
               try {
                  boolean isOk = st.execute(SQL);

               } catch (Exception e) {
                  System.out.println("error" + e.getMessage());
               }
               if (i == -1) {
                  System.out.println("�����ϴ� �˻������ �����ϴ�.");
               } else
                  addFavorite(userID, map);

            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }

            break;
         }
         case 2: {
            System.out.print("������ �Է��ϼ��� : ");
            data = input.next();
            int i = -1;
            SQL = "SELECT * FROM type WHERE location = '" + data + "';";
            try {
               rs = st.executeQuery(SQL);
               System.out.println();
               HashMap<Integer, String> map = new HashMap<Integer, String>();
               while (rs.next()) {
                  System.out.println(++i + " | " + rs.getString("name") + " | " + rs.getString("type") + " | "
                        + rs.getString("field") + " | " + rs.getString("location") + " | " + rs.getString("date")
                        + " | " + rs.getString("period") + " | " + rs.getString("people") + " | "
                        + rs.getString("fee") + " | " + rs.getString("URL"));

                  map.put(i, rs.getString("name"));
               }
               SQL = "drop view type";
               try {
                  boolean isOk = st.execute(SQL);

               } catch (Exception e) {
                  System.out.println("error" + e.getMessage());
               }
               if (i == -1) {
                  System.out.println("�����ϴ� �˻������ �����ϴ�.");
               } else
                  addFavorite(userID, map);
            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }

            break;
         }
         case 3: {
            System.out.print("�о߸� �Է��ϼ��� : ");
            data = input.next();
            int i = -1;
            SQL = "SELECT * FROM type WHERE field = '" + data + "';";
            try {
               rs = st.executeQuery(SQL);
               System.out.println();
               HashMap<Integer, String> map = new HashMap<Integer, String>();
               while (rs.next()) {
                  System.out.println(++i + " | " + rs.getString("name") + " | " + rs.getString("type") + " | "
                        + rs.getString("field") + " | " + rs.getString("location") + " | " + rs.getString("date")
                        + " | " + rs.getString("period") + " | " + rs.getString("people") + " | "
                        + rs.getString("fee") + " | " + rs.getString("URL"));

                  map.put(i, rs.getString("name"));
               }
               SQL = "drop view type";
               try {
                  boolean isOk = st.execute(SQL);

               } catch (Exception e) {
                  System.out.println("error" + e.getMessage());
               }
               if (i == -1) {
                  System.out.println("�����ϴ� �˻������ �����ϴ�.");
               } else
                  addFavorite(userID, map);
            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }

            break;
         }
         default:
            break;
         }
      }

      // ���ã�� �߰��ϴ� �Լ�
      private void addFavorite(String userID, HashMap<Integer, String> map) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String SQL;

         while (true) {
            System.out.println("���ã�⿡ �����Ͻðڽ��ϱ�? (y/n)");
            System.out.print(">>");
            String choice = input.next();
            try {
               if (choice.equals("Y") || choice.equals("y")) {
                  System.out.print("��Ͽ� ǥ�õ� ���ã�⿡ �߰��� ��� ���ڸ� �Է��� �ּ��� : ");
                  int num = input.nextInt();

                  SQL = "INSERT INTO favorites(name, user_id) values ('" + map.get(num) + "', '" + userID + "')";
                  int isSuccess = st.executeUpdate(SQL);
                  if (isSuccess == 0) {
                     System.out.println("�ùٸ��� ���� ��� ���� �Դϴ�.");
                  } else {
                     System.out.println("���ã�⿡ �߰� �Ǿ����ϴ�.");
                     continue;
                  }
               } else if (choice.equals("n") || choice.equals("N"))
                  break;
               else {
                  System.out.println("�Է� ����. y or n�� �Է����ּ���");
               }

            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }
         }
      }

      // ������ ����س��� ���ã�⸦ ��ȸ�ϴ� �Լ�
      private void showFavorites(String userID) {
         System.out.println("------------------");
         System.out.println("���� ���ã�� �������Դϴ�.");
         String SQL;

         SQL = "SELECT * FROM event natural join favorites WHERE favorites.user_id = '" + userID + "';";

         try {
            rs = st.executeQuery(SQL);
            while (rs.next()) {
               System.out.println(rs.getString("name") + " | " + rs.getString("type") + " | " + rs.getString("field")
                     + " | " + rs.getString("location") + " | " + rs.getString("date") + " | "
                     + rs.getString("period") + " | " + rs.getString("people") + " | " + rs.getString("fee") + " | "
                     + rs.getString("URL") + " | ");
            }
         } catch (Exception e) {
            System.out.println("error" + e.getMessage());
         }
      }

      // ��Ʈ������
      public void portFolio(String userID) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         System.out.println("------------------");
         System.out.println("���� ��Ʈ������ �������Դϴ�");
         System.out.println("1. �Է��ϱ�");
         System.out.println("2. ��ȸ�ϱ�");
         System.out.print(">>");
         int portNum = input.nextInt();
         switch (portNum) {
         case 1: {
            String SQL = "SELECT *from Event;";
            HashMap<Integer, String> map2 = new HashMap<Integer, String>();
            try {
               int i = 0;
               rs = st.executeQuery(SQL);

               while (rs.next()) {
                  System.out.println(i + " | " + rs.getString("name"));

                  map2.put(i, rs.getString("name"));
                  i++;
               }
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            // ��Ʈ������ �߰�
            while (true) {
               System.out.print("��� ��ȣ�� �Է��ϼ���: ");
               int num = input.nextInt();

               System.out.print("���� ����: ");
               String awards = input.next();

               SQL = "INSERT INTO PORTFOLIO(user_id, name, awards) " + "VALUES" + "('" + userID + "'," + "'"
                     + map2.get(num) + "'," + "'" + awards + "');";
               try {

                  int rs = st.executeUpdate(SQL);

               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }

               System.out.print("��� �Է��Ͻðڽ��ϱ�? (y/n) : ");
               String answer = input.next();

               if (answer.equals("N") || answer.equals("n"))
                  break;
            }
            break;
         }

         case 2: {
            // ��Ʈ������ ��ȸ
            String SQL = "SELECT Portfolio.name, date, awards FROM Event, Portfolio WHERE Event.name = Portfolio.name;";
            try {
               rs = st.executeQuery(SQL);
               while (rs.next()) {
                  System.out.println(
                        rs.getString("name") + " | " + rs.getString("date") + " | " + rs.getString("awards"));
               }
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         }

         default:
            break;
         }
      }

      public void recommend(String userID) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String data;
         String SQL;
         int i = 0;
         int j = 0;
         System.out.println("------------------");
         System.out.println("���� ��õ �������Դϴ�");

         try {
            System.out.println("<���ɺоߺ�>");
            SQL = "select * from event where field in (select field from user where user.user_id='" + userID + "');";
            rs = st.executeQuery(SQL);
            while (rs.next()) {
               System.out.println(rs.getString("name") + " | " + rs.getString("type") + " | " + rs.getString("field")
                     + " | " + rs.getString("location") + " | " + rs.getString("date") + " | "
                     + rs.getString("period") + " | " + rs.getString("people") + " | " + rs.getString("fee") + " | "
                     + rs.getString("URL"));
               ++i;
            }
            if (i == 0) {
               System.out.println("�ش� �о��� �����Ͱ� �����ϴ�.");
            }
         } catch (Exception e) {
            System.out.println("error" + e.getMessage());
         }

         try {
            System.out.println();
            System.out.println("<������>");
            SQL = "select * from event where location in (select location from user where user.user_id='" + userID
                  + "');";
            rs = st.executeQuery(SQL);
            while (rs.next()) {
               System.out.println(rs.getString("name") + " | " + rs.getString("type") + " | " + rs.getString("field")
                     + " | " + rs.getString("location") + " | " + rs.getString("date") + " | "
                     + rs.getString("period") + " | " + rs.getString("people") + " | " + rs.getString("fee") + " | "
                     + rs.getString("URL"));

               ++j;
            }
            if (j == 0) {
               System.out.println("�ش� �о��� �����Ͱ� �����ϴ�.");
            }
         } catch (Exception e) {
            System.out.println(e.getMessage());

         }
      }

      private void adminlogin() {
         loop: while(true) {
            Scanner input = new Scanner(System.in, "EUC-KR");
               String beforeEvent;
               String AfterEvent;
               String SQL;
               System.out.println();
               System.out.println("1. ��� �����ϱ�");
               System.out.println("2. ��� �����ϱ�");
               System.out.println("3. ��� �Է��ϱ�");
               System.out.print(">>");
               int num = input.nextInt();
               switch (num) {
               case 1: {
                  SQL = "create trigger updateEvent after update on Event for each row update favorites set name=New.name where name=Old.name;";
                  try {
                     boolean isOk = st.execute(SQL);
                     SQL = "SET FOREIGN_KEY_CHECKS=0";
                     boolean isTrue = st.execute(SQL);
                  } catch (Exception e) {
                     System.out.println("error" + e.getMessage());
                  }
                  SQL = "SELECT * FROM event;";
                  try {
                     int i = 0;
                     rs = st.executeQuery(SQL);
                     HashMap<Integer, String> map = new HashMap<Integer, String>();
                     while (rs.next()) {
                        System.out.println(i + " | " + rs.getString("name"));
                        map.put(i, rs.getString("name"));
                        i++;
                     }
                     System.out.print("��Ͽ� ǥ�õ� ������ ��� ���ڸ� �Է��� �ּ��� : ");
                     num = input.nextInt();
                     System.out.print("������ ��� �̸��� �Է��ϼ��� : ");
                     String data = input.nextLine();
                     AfterEvent = input.nextLine();
                     SQL = "UPDATE Event SET name = '" + AfterEvent + "' where name = '" + map.get(num) + "';";
                     try {
                        int isOk = st.executeUpdate(SQL);
                        if (isOk == 1)
                           System.out.println("��� ������ �Ϸ� �Ǿ����ϴ�.");
                        SQL = "SET FOREIGN_KEY_CHECKS=1";
                        boolean isTrue = st.execute(SQL);
                        SQL = "drop trigger updateEvent";
                        try {
                           boolean isPossible = st.execute(SQL);
                        } catch (Exception e) {
                           System.out.println("error" + e.getMessage());
                        }

                     } catch (Exception e) {
                        System.out.println("error" + e.getMessage());
                     }
                  } catch (Exception e) {
                     System.out.println("error" + e.getMessage());
                  }
                  break;
               }
               case 2: {
                  int i = 0;
                  SQL = "SELECT * FROM event;";
                  try {
                     rs = st.executeQuery(SQL);
                     HashMap<Integer, String> map = new HashMap<Integer, String>();
                     while (rs.next()) {
                        System.out.println(i + " | " + rs.getString("name"));
                        map.put(i, rs.getString("name"));
                        i++;
                     }
                     System.out.print("��Ͽ� ǥ�õ� ������ ��� ���ڸ� �Է��� �ּ��� : ");
                     num = input.nextInt();
                     SQL = "DELETE FROM Event WHERE name = '" + map.get(num) + "';";
                     try {
                        int isSuccess = st.executeUpdate(SQL);
                        if (isSuccess == 1)
                           System.out.println("��簡 ���� �Ǿ����ϴ�.");
                     } catch (Exception e) {
                        System.out.println("error" + e.getMessage());
                     }
                  } catch (Exception e) {
                     System.out.println("error" + e.getMessage());
                  }

                  break;
               }
               case 3: {
                  while (true) {
                      System.out.println();
                          System.out.print("����̸� >>");
                          String data = input.nextLine();
                          String name = input.nextLine();
                          System.out.print("���Ÿ��>>");
                          String type = input.next();
                          System.out.print("���о�>>");
                          String field = input.next();
                          System.out.print("������>>");
                          String location = input.next();
                          System.out.print("��糯¥ >>");
                          String date = input.next();
                          System.out.print("���������Ⱓ>>");
                          String period = input.next();
                          System.out.print("�ο� (��or����)>>");
                          String people = input.next();
                          System.out.print("������ (����) >>");
                          int fee = input.nextInt();
                          System.out.print("URL >>");
                          String URL = input.next();

                     SQL = "INSERT INTO event values ('" + name + "','" + type + "','" + field + "','" + location + "','"
                           + date + "','" + period + "','" + people + "','" + fee + "','" + URL + "');";
                     try {
                        int isSuccess = st.executeUpdate(SQL);
                        if (isSuccess == 1) {
                           System.out.println();
                           System.out.println("���������� ��ϵǾ����ϴ�.");
                           System.out.print("�� �Է��Ͻðڽ��ϱ�? (y/n) : ");
                           String choice = input.next();
                           if (choice.equals("y") || choice.equals("Y"))
                              continue;
                           else if (choice.equals("n") || choice.equals("N"))
                              break;
                           else
                              System.out.println("�ٽ� �Է����ּ��� y/n");
                        } else
                           break;
                     } catch (Exception e) {
                        System.out.println("error" + e.getMessage());
                     }
                     break;
                  }
                  break;

               }
               default: {
                  System.out.println("�ý����� ���� �Ǿ����ϴ�."); 
                     break loop;
               }
               }
         }
      }

      public void sqlTest() {
         StringBuilder sb = new StringBuilder();
         Scanner input = new Scanner(System.in, "EUC-KR");
         String admin_id = "admin";
         String admin_pwd = "admin";

         int checkName = 1;
         System.out.println("�ҽ��� ���̵忡 ���Ű� ȯ���մϴ�!");
         System.out.print("�����ڸ� '1', �Ϲ� ������ '2'�� �����ϼ��� : ");
         int input_num = input.nextInt();
         if (input_num == 1) {
            while (true) {
               System.out.print("id: ");
               String input_id = input.next();
               System.out.print("pwd: ");
               String input_pwd = input.next();
               if (input_id.equals(admin_id) && input_pwd.equals(admin_pwd)) {
                  System.out.println("������ �������� �α��� �Ǿ����ϴ�. ");
                  adminlogin();
               } else {
                  System.out.println("�α��� ���� �ٽ� �Է����ּ���");
                  continue;
               }
               break;
            }
         } else if (input_num == 2) {
            System.out.print("���̵� �Է��ϼ��� ������ �ڵ� �����˴ϴ�: ");
            String userID = input.next();
            String SQL = "SELECT * FROM user";
            try {
               rs = st.executeQuery(SQL);
               while (rs.next()) {
                  if (rs.getString("user_id").equals(userID)) {
                     checkName = 0;
                     break;
                  }
               }
            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }
            if (checkName == 1) {
               System.out.print("�̸��� �Է��ϼ��� : ");
               String userName = input.next();
               System.out.print("������ �Է��ϼ��� : ");
               String userArea = input.next();

               System.out.print("���ɺо߸� �Է��ϼ��� : ");
               String userInterest = input.next();
               SQL = "INSERT INTO USER VALUE(" + "'" + userID + "'," + "'" + userName + "'," + "'" + userArea + "',"
                     + "'" + userInterest + "');".toString();
               try {
                  int id = st.executeUpdate(SQL);
               } catch (Exception e) {
                  System.out.println("error" + e.getMessage());
               }
            }

            int num;
            String data;
            loop: while (true) {
               System.out.println();
               System.out.println("1. ��� �˻�");
               System.out.println("2. ���� ��Ʈ������");
               System.out.println("3. ��� ã��");
               System.out.println("4. ��õ");
               System.out.println("5. ������");
               System.out.print(">>");
               num = input.nextInt();
               switch (num) {
               case 1: {
                  eventSearch(userID);
                  continue;
               }
               case 2: {
                  portFolio(userID);
                  break;
               }
               case 3: {
                  showFavorites(userID);
                  break;
               }
               case 4: {
                  recommend(userID);
                  break;
               }
               case 5: {
                  break loop;
               }
               default:
                  break;
               }
            }
         } else
            System.out.println("error");
      }

   }