
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hanaa
 */
public class DataBase {
    public static ArrayList <User>users=new ArrayList<>();
    public static ArrayList <User>onlineUsers=new ArrayList<>();
    public static boolean isUser(User user){
    boolean flag=false;
        for (User iterativeUser : users) {
            if(iterativeUser.getPassword().equals(user.getPassword())&&iterativeUser.getEmail().equals(iterativeUser.getEmail())){
                flag=true;
            }
        }
        return flag;
    }
     public static boolean checkMail(User user){
    boolean flag=false;
        for (User iterativeUser : users) {
            if(iterativeUser.getEmail().equals(iterativeUser.getEmail())){
                flag=true;
            }
        }
        return flag;
    }
     public static User getUser(User user){
         User reUser=null;
        for (User iterativeUser : users) {
            if(iterativeUser.getPassword().equals(user.getPassword())&&iterativeUser.getEmail().equals(user.getEmail())){
               reUser=iterativeUser;
            }
        }
        return reUser;
    }
     
     public static ArrayList<User> getOnlineUser(){
         return onlineUsers;
    }
      public static void logOut(User user){
       Iterator<User> it = onlineUsers.iterator();
        while (it.hasNext()) {
            User itUser=(User)it.next();
            if(user.getPassword().equals(itUser.getPassword())&&user.getEmail().equals(itUser.getEmail())){
            
                it.remove();
            }
            
        }
    }
}
