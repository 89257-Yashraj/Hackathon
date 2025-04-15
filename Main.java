package MenuDriven;

import java.sql.*;
import java.util.Scanner;

public class Main {
    // you may keep whole user object here
    public static String CUR_USER = null;
    public static void userMenu(Scanner sc) {
    	System.out.println("Welcome "+ CUR_USER);
        int choice;
        do {
            System.out.print("\n0. Log out\n1. Edit Profile\n2. Change Password\nEnter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                {
					int edit;
					do {
                		System.out.println("\n0. exit\n1. Edit first name\n2. Edit last name\n3. edit mail id\n");
                		edit = sc.nextInt();
                		switch(edit) {
                		
                			case 1:
                			{
                				System.out.println("Enter first name");
                				String fName = sc.next();
                				String sql = "update users set FirstName = ? where email=?";
                				EditProfile(sql, fName);
                				break;
                			}
                			case 2:
                			{
                				System.out.println("Enter last name");
                				String lName = sc.next();
                				String sql = "update users set LatName = ? where email=?";
                				EditProfile(sql, lName);
                				break;
                			}
                			case 3:
                			{
                				System.out.println("Enter mail id");
                				String email = sc.next();
                				String sql = "update users set email = ? where email=?";
                				EditProfile(sql, email);
                				break;
                			}
                		}
                	} while(edit!=0);
                	break;	
                }
                case 2:
                {
                	System.out.println("Enter new password");
    				String password = sc.next();
    				String sql = "update users set password = ? where email=?";
    				EditProfile(sql, password);
                    break;
                }
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                default:
                    if(choice!=0) {
                    	System.out.println("Invalid choice");
                    }
                    break;
            }
        }while(choice != 0);
        System.out.println("logged out successfully....");
        CUR_USER = null;
    }
    public static void mainMenu(Scanner sc) {
        int choice;
        do {
            System.out.print("\n0. Exit\n1. Sign In\n2. Sign Up\nEnter choice: ");
            choice = sc.nextInt();
            switch (choice) {
            
                case 1: // Sign In
                    CUR_USER = authenticate(sc);
                    if(CUR_USER != null)
                        userMenu(sc);
                    else
                        System.out.println("Invalid email or password.");
                    break;
                case 2: // Sign Up
                    registerUser(sc);
                    break;
            }
        }while(choice != 0);
        System.out.println("Thank you visit again....");
    }

    private static void registerUser(Scanner sc) {
        
    	System.out.println("Enter First Name");
    	String firstName = sc.next();
    	System.out.println("Enter last Name");
    	String lastName = sc.next();
    	System.out.println("Enter emailId");
    	String email = sc.next();
    	System.out.println("Enter password");
    	String password = sc.next();
    	
    	try(Connection con = DBUtil.getConnection()) {
    		
    		String sql = "insert into users(FirstName, LastName, email, password) values (?, ?, ?, ?);";
    		try(PreparedStatement stmt = con.prepareStatement(sql)) {
	    		stmt.setString(1, firstName);
	    		stmt.setString(2, lastName);
	    		stmt.setString(3, email);
	    		stmt.setString(4, password);
	    		stmt.executeUpdate();
	    		System.out.println("User successfully registerd....");
    		}
    	}
    	catch(Exception e) {
    		
    		System.out.println(e.getMessage());
    	}
    }

    private static String authenticate(Scanner sc) {
        String email, password;
        System.out.print("Enter email: ");
        email = sc.next();
        System.out.print("Enter password: ");
        password = sc.next();
        
        String str = null;
        try(Connection con = DBUtil.getConnection()) {
	        String sql = "select email from users where email=? and password=?";
	        try(PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setString(1, email);
		        stmt.setString(2, password);
		        try (ResultSet rs = stmt.executeQuery()) {
		        	if(rs.next()) {
		    		   
		        		str = rs.getString("email");
		        	}
		        }
	        }
        }
        catch(Exception e) {
        	
        	System.out.println(e.getMessage());
        }
        return str;
    }
    
    private static void EditProfile(String sql, String str) {
   
    	try(Connection con = DBUtil.getConnection()) {
	    	try(PreparedStatement stmt = con.prepareStatement(sql)) {
	    		
	    		stmt.setString(1, str);
	    		stmt.setString(2, CUR_USER);
	    		int count = stmt.executeUpdate();
	    		if(count==0) {
	    			
	    			System.out.println("Failed to edit");
	    		} else {
	    			
	    			System.out.println("updated successfully");
	    		} 
	    	}
    	}
    	catch(Exception e) {
    		
    		e.printStackTrace();
    	}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenu(sc);
        sc.close();
    }
}
