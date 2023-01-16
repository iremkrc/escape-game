package Domain.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginController {
    GameController gameController;
    
    private static String loginName;
    
    private static List<String> userNameList;
    
    public LoginController(GameController gameController) {
        this.gameController = gameController;
    }
    

    public List<String> getUsernameList() {
        userNameList = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("usernames.txt"));
			while (scanner.hasNext()) {
				userNameList.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return userNameList;
    }
    public boolean isRegistered(String userName) {
        if(getUsernameList().contains(userName)){
            return true;
        }
        return false;
    }
    public void addUsername(String username) {
        userNameList.add(username);
    }


    public int signUpUser(String signUpName) {
        try {
					
            FileWriter writer = new FileWriter("usernames.txt", true);
            
            if(signUpName.equals("")) {
                writer.close();
                return 0;
            }else if (getUsernameList().contains(signUpName)) {
                writer.close();
                return 1;
            }else{
                writer.write(signUpName + "\n");
                getUsernameList().add(signUpName);
                writer.close();
                return 2;
            }
            
          } catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
          }
        return 3;
    }
    
    public static String getLoginName() {
        return loginName;
    }

    
    public static void setLoginName(String loginName) {
        LoginController.loginName = loginName;
    }
}
