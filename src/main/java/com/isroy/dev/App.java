package com.isroy.dev;

import com.isroy.dev.exception.EvaluationException;
import com.isroy.dev.feature.gate.FeatureGatingValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class App 
{

    public static Scanner in;
    public static HashMap<String, Object> defaultUser;


    public static void main( String[] args )
    {
        in = new Scanner(System.in);

        defaultUser = new HashMap<>();
        defaultUser.put("name","Ishan");
        defaultUser.put("age",26);
        defaultUser.put("gender", "Male");
        defaultUser.put("past_order_amount", 15000.00);
        defaultUser.put("address.city","Bangalore");
        defaultUser.put("address.country", "India");

        int userOption;
        String expression = "";
        HashMap<String,Object> userMap = new HashMap<>();
        do{
            try{
                userOption = readUserOption();
                switch (userOption){
                    case 1:
                        userMap = defaultUser;
                        System.out.println("Using default user.\n");
                        expression = readConditionalExpression();
                        break;
                    case 2:
                        userMap = takeCustomUserInput(userMap);
                        printUser(userMap);
                        expression = readConditionalExpression();
                        break;
                    case 3:
                        System.out.println("Exiting Program...Thanks.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Option. Please try again");
                        continue;
                }
                System.out.println("\n===========================================\n");
                boolean result = FeatureGatingValidator.getInstance().isValid(expression,userMap);
                if(result)
                    System.out.println("The User is allowed\n");
                else
                    System.out.println("The User is not allowed\n");
            } catch (Exception e ) {
                e.printStackTrace();
//                System.out.println(e.getStackTrace());
                System.out.println("Error occured while processing the request, try again with correct parameters.");
                continue;
            }
        }while(true);

    }

    private static HashMap<String, Object> takeCustomUserInput(HashMap<String, Object> customUser) throws Exception{

        System.out.println("Enter custom user parameters:\n");
        System.out.println("Enter User Name: ");
        customUser.put("name", in.nextLine());
        System.out.println("Enter User Age: ");
        customUser.put("age", Integer.parseInt(in.nextLine()));
        System.out.println("Enter User Gender: ");
        customUser.put("gender", in.nextLine());
        System.out.println("Enter User Past Order Amount: ");
        customUser.put("past_order_amount", Double.parseDouble(in.nextLine()));
        System.out.println("Enter User City: ");
        customUser.put("address.city", in.nextLine());
        System.out.println("Enter User Country: ");
        customUser.put("address.country", in.nextLine());
        return customUser;
    }

    public static int readUserOption() throws Exception{
        System.out.println("===============Options===================");
        printUser(defaultUser);
        System.out.println("Select an option:\n" +
                "1.Use current user\n" +
                "2.Enter custom user\n" +
                "3.Exit\n");
        int option = Integer.parseInt(in.nextLine());
        return option;
    }


    public static void printUser(HashMap<String,Object> userMap){
        System.out.println("Current user:\n" +
                "Name: " + userMap.get("name") + "\n" +
                "Age: " + userMap.get("age") + "\n" +
                "Gender: " + userMap.get("gender") + "\n" +
                "Address.City :" + userMap.get("address.city") + "\n" +
                "Address.Country :" + userMap.get("address.country") + "\n" +
                "Past Order Amount :" + userMap.get("past_order_amount") + "\n");
    }

    public static String readConditionalExpression() throws Exception{
        System.out.println("Enter custom expression:\n");
        String expression = in.nextLine();
        return expression;
    }
}
