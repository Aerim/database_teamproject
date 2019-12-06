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
         System.out.println("연결 오류" + e.getMessage());
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
   
   // 이벤트의 타입에 맞게 서치해주는 함수
      private void eventSearch(String userID) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String data;
         String type1 = "해커톤";
         String type2 = "컨퍼런스";
         String type3 = "공모전";
         String SQL;
         System.out.println("------------------");
         System.out.println("행사 타입을 선택하시오.");
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

      // 타입을 입력받은후 어떤 필터링으로 검색할것인지에 대한 함수
      private void eventSearchFilter(String userID, String type) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String data;
         String SQL;
         System.out.println("어떤 검색을 하시겠습니까?");
         System.out.println("1. 날짜별");
         System.out.println("2. 지역별");
         System.out.println("3. 분야별");
         System.out.print(">>");
         int searchNum = input.nextInt();
         switch (searchNum) {
         case 1: {
            System.out.print("날짜를 입력하세요 예시)2019-11-11 : ");
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
                  System.out.println("존재하는 검색결과가 없습니다.");
               } else
                  addFavorite(userID, map);

            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }

            break;
         }
         case 2: {
            System.out.print("지역을 입력하세요 : ");
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
                  System.out.println("존재하는 검색결과가 없습니다.");
               } else
                  addFavorite(userID, map);
            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }

            break;
         }
         case 3: {
            System.out.print("분야를 입력하세요 : ");
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
                  System.out.println("존재하는 검색결과가 없습니다.");
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

      // 즐겨찾기 추가하는 함수
      private void addFavorite(String userID, HashMap<Integer, String> map) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         String SQL;

         while (true) {
            System.out.println("즐겨찾기에 저장하시겠습니까? (y/n)");
            System.out.print(">>");
            String choice = input.next();
            try {
               if (choice.equals("Y") || choice.equals("y")) {
                  System.out.print("목록에 표시된 즐겨찾기에 추가할 행사 숫자를 입력해 주세요 : ");
                  int num = input.nextInt();

                  SQL = "INSERT INTO favorites(name, user_id) values ('" + map.get(num) + "', '" + userID + "')";
                  int isSuccess = st.executeUpdate(SQL);
                  if (isSuccess == 0) {
                     System.out.println("올바르지 않은 행사 숫자 입니다.");
                  } else {
                     System.out.println("즐겨찾기에 추가 되었습니다.");
                     continue;
                  }
               } else if (choice.equals("n") || choice.equals("N"))
                  break;
               else {
                  System.out.println("입력 오류. y or n로 입력해주세요");
               }

            } catch (Exception e) {
               System.out.println("error" + e.getMessage());
            }
         }
      }

      // 유저가 등록해놓은 즐겨찾기를 조회하는 함수
      private void showFavorites(String userID) {
         System.out.println("------------------");
         System.out.println("나의 즐겨찾기 페이지입니다.");
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

      // 포트폴리오
      public void portFolio(String userID) {
         Scanner input = new Scanner(System.in, "EUC-KR");
         System.out.println("------------------");
         System.out.println("나의 포트폴리오 페이지입니다");
         System.out.println("1. 입력하기");
         System.out.println("2. 조회하기");
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
            // 포트폴리오 추가
            while (true) {
               System.out.print("행사 번호를 입력하세요: ");
               int num = input.nextInt();

               System.out.print("수상 내역: ");
               String awards = input.next();

               SQL = "INSERT INTO PORTFOLIO(user_id, name, awards) " + "VALUES" + "('" + userID + "'," + "'"
                     + map2.get(num) + "'," + "'" + awards + "');";
               try {

                  int rs = st.executeUpdate(SQL);

               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }

               System.out.print("계속 입력하시겠습니까? (y/n) : ");
               String answer = input.next();

               if (answer.equals("N") || answer.equals("n"))
                  break;
            }
            break;
         }

         case 2: {
            // 포트폴리오 조회
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
         System.out.println("나의 추천 페이지입니다");

         try {
            System.out.println("<관심분야별>");
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
               System.out.println("해당 분야의 데이터가 없습니다.");
            }
         } catch (Exception e) {
            System.out.println("error" + e.getMessage());
         }

         try {
            System.out.println();
            System.out.println("<지역별>");
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
               System.out.println("해당 분야의 데이터가 없습니다.");
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
               System.out.println("1. 행사 수정하기");
               System.out.println("2. 행사 삭제하기");
               System.out.println("3. 행사 입력하기");
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
                     System.out.print("목록에 표시된 수정할 행사 숫자를 입력해 주세요 : ");
                     num = input.nextInt();
                     System.out.print("수정할 행사 이름을 입력하세요 : ");
                     String data = input.nextLine();
                     AfterEvent = input.nextLine();
                     SQL = "UPDATE Event SET name = '" + AfterEvent + "' where name = '" + map.get(num) + "';";
                     try {
                        int isOk = st.executeUpdate(SQL);
                        if (isOk == 1)
                           System.out.println("행사 수정이 완료 되었습니다.");
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
                     System.out.print("목록에 표시된 삭제할 행사 숫자를 입력해 주세요 : ");
                     num = input.nextInt();
                     SQL = "DELETE FROM Event WHERE name = '" + map.get(num) + "';";
                     try {
                        int isSuccess = st.executeUpdate(SQL);
                        if (isSuccess == 1)
                           System.out.println("행사가 삭제 되었습니다.");
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
                          System.out.print("행사이름 >>");
                          String data = input.nextLine();
                          String name = input.nextLine();
                          System.out.print("행사타입>>");
                          String type = input.next();
                          System.out.print("행사분야>>");
                          String field = input.next();
                          System.out.print("행사장소>>");
                          String location = input.next();
                          System.out.print("행사날짜 >>");
                          String date = input.next();
                          System.out.print("접수마감기간>>");
                          String period = input.next();
                          System.out.print("인원 (팀or개인)>>");
                          String people = input.next();
                          System.out.print("입장비용 (숫자) >>");
                          int fee = input.nextInt();
                          System.out.print("URL >>");
                          String URL = input.next();

                     SQL = "INSERT INTO event values ('" + name + "','" + type + "','" + field + "','" + location + "','"
                           + date + "','" + period + "','" + people + "','" + fee + "','" + URL + "');";
                     try {
                        int isSuccess = st.executeUpdate(SQL);
                        if (isSuccess == 1) {
                           System.out.println();
                           System.out.println("정상적으로 등록되었습니다.");
                           System.out.print("더 입력하시겠습니까? (y/n) : ");
                           String choice = input.next();
                           if (choice.equals("y") || choice.equals("Y"))
                              continue;
                           else if (choice.equals("n") || choice.equals("N"))
                              break;
                           else
                              System.out.println("다시 입력해주세요 y/n");
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
                  System.out.println("시스템이 종료 되었습니다."); 
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
         System.out.println("소슐랭 가이드에 오신걸 환영합니다!");
         System.out.print("관리자면 '1', 일반 유저면 '2'를 선택하세요 : ");
         int input_num = input.nextInt();
         if (input_num == 1) {
            while (true) {
               System.out.print("id: ");
               String input_id = input.next();
               System.out.print("pwd: ");
               String input_pwd = input.next();
               if (input_id.equals(admin_id) && input_pwd.equals(admin_pwd)) {
                  System.out.println("관리자 권한으로 로그인 되었습니다. ");
                  adminlogin();
               } else {
                  System.out.println("로그인 실패 다시 입력해주세요");
                  continue;
               }
               break;
            }
         } else if (input_num == 2) {
            System.out.print("아이디를 입력하세요 없으면 자동 생성됩니다: ");
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
               System.out.print("이름을 입력하세요 : ");
               String userName = input.next();
               System.out.print("지역을 입력하세요 : ");
               String userArea = input.next();

               System.out.print("관심분야를 입력하세요 : ");
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
               System.out.println("1. 행사 검색");
               System.out.println("2. 나의 포트폴리오");
               System.out.println("3. 즐겨 찾기");
               System.out.println("4. 추천");
               System.out.println("5. 나가기");
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