package org.example;

import org.example.model.Employee;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int choice;
        String name;
        int age;

        while(true) {
            System.out.println("1. Retrieve Employees\n2. Add Employee");
            choice = in.nextInt();

            switch(choice) {
                case 1:
                    getEmployees();
                    break;
                case 2:
                    Random random = new Random();
                    int id = random.nextInt(10000);
                    name = in.next();
                    age = in.nextInt();
                    Response response = addEmployee(new Employee(id, name, age));
                    System.out.println(response);
                    break;
                default:
                    System.out.println("Wrong Choice");

            }
        }

    }

    public static void getEmployees() {
        String REST_URI = "http://localhost:8091/RESTAPI/";
        Client client = ClientBuilder.newClient();

        String entity = client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header("some-header", "true")
                .get(String.class);

        System.out.println(entity);
    }

    public static Response addEmployee(Employee employee) {
        String REST_URI = "http://localhost:8091/RESTAPI/create";
        Client client = ClientBuilder.newClient();

        return  client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(employee, MediaType.APPLICATION_XML));
    }
}
