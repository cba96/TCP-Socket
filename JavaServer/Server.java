package aJava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class aNew {
	
	public static void main(String[] args) {
		String field1 = "";
    	String field2 = "";
    	String field3 = "";
    	String CliToSer = "";
    	
    	//& 문자열 분리
    	String str = CliToSer;
    	String[] array = str.split("&");
    	//출력
    	for(int i=0;i<array.length;i++) {
    	System.out.println(array[i]);
    	}
		
		
		  Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	      PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	        
	      //TCP소캣 연결
	        try {
	        	 ServerSocket ss = new ServerSocket(8433);
		            Socket s = ss.accept();
		            System.out.println("Client Accepted");
		            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		           // System.out.println(br.readLine());
		            CliToSer = (br.readLine()); //체크, 위에거랑 같음 (클라이언트에서 보낸 메세지 C변수에 저장)
		            System.out.print(CliToSer);
		            
		            //& 문자열 분리
		        	str = CliToSer;
		        	array = str.split("&");
		        	//출력
		        	for(int i=0;i<array.length;i++) {
		        		System.out.println();	
		        		
		        	System.out.println("문자열 분리 : " + array[i]); // 값이 잘 들어갔는지 확인
		        	System.out.println();
		        	}
		            
		        //SQL SELECT	
	            String quary = "SELECT EmployeeNumber2, Temp, Date_TB2 FROM user_dailyinfo WHERE EmployeeNumber2 = ? AND Date_TB2 = ?";
	            
	            conn = DBConnection.getConnection();
	            pstm = conn.prepareStatement(quary);
	            
	            // 쿼리에 값을 세팅한다.
	            // 여기서 1, 2, 3은 첫번째, 두번째, 세번째 위치홀더 라는 뜻
	            
	            //prepareStatement로 ? 부분에 전달해주기
	            pstm.setString(1, array[0]); //입력값 문제 (사번)
	            pstm.setString(2, array[1]); //입력값 문제 (출근 날짜)
	           
	            ResultSet rs = pstm.executeQuery();
	            
	            //
	            boolean found;
	            //found = rs.next();
	            //
	            
	            String abc = "";
	            
	            //원하는 데이터 가져와서 콘솔창에 뿌려주기
	            while (rs.next()) {
	            	field1 = rs.getString(1);
	            	field2 = rs.getString(3);
	            	field3 = rs.getString(2);
					System.out.printf("%s\t%10s\t%10s\n", rs.getString(1), rs.getString(3), rs.getString(2));		
						
	            }
	            //데이터 유뮤 확인 로직
	            if (field2.isEmpty())
	                System.out.println("데이터가 없음");
	            else 
	                System.out.println("데어터 있음");
	            
				rs.close();
				pstm.close();
				conn.close();
	            
	           
	          //C# 클라이언트로 보내주기  
	            PrintWriter wr = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
	            
	            if (field2.isEmpty()) {
	            	wr.print("Server Message : error_msg \n");
	            }
	            if (field2.isEmpty() == false) 
	            	 wr.print("Server Message : ");
	            		wr.print(field1 +"&");
	           // wr.print("&");
	            wr.print(field2 +"&");
	           // wr.print("&");
	            wr.println(field3); 
	            
	            
	            //& 문자열 분리
	        	str = CliToSer;
	        	array = str.split("&");
	        	//출력
	        	for(int i=0;i<array.length;i++) {
	        		System.out.println();	
	        		
	        	System.out.println("문자열 분리 : " + array[i]); // 값이 잘 들어갔는지 확인
	        	System.out.println();
	        	}
	        	
	        	System.out.println("0번째 배열 : "+array[0]); // 값이 잘 들어갔는지 확인
	        	System.out.println("1번째 배열"+array[1]); // 값이 잘 들어갔는지 확인
	        	
	        } 
	        catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	}

}
