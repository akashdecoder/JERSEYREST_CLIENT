package org.example;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.example.service.CrudClient;

import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int choice;
        String name;
        int age;
        int id;

        while(true) {
            System.out.println("1. Retrieve Employees\n2. Add Employee\n3. Delete Employee\n4. Update Employee\n5. Quit Client");
            choice = in.nextInt();

            switch(choice) {
                case 1:
                    CrudClient.getEmployee();
                    break;
                case 2:
                    Random random = new Random();
                    in.nextLine();
                    name = in.nextLine();
                    age = in.nextInt();
                    CrudClient.addEmployee(name, age);
                    break;
                case 3:
                    id = in.nextInt();
                    CrudClient.deleteEmployee(id);
                    continue;
                case 4:
                    id = in.nextInt();
                    in.nextLine();
                    name = in.nextLine();
                    age = in.nextInt();
                    CrudClient.updateEmployee(id, name, age);
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Wrong Choice");

            }
        }

    }

}
