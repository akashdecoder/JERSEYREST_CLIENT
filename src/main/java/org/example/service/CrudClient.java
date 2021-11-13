package org.example.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.Random;

public class CrudClient {
    public static void addEmployee(String name, int age){
        Random random = new Random();
        long id = random.nextLong();

        String input = "{\"age\": "
                + Integer.toString(age) +
                ",\"name\":"
                + "\"" + name + "\"" +
                "}";
//        String input = "{\"age\":21,\"name\":\"John Doe\"}";
        Client c  = Client.create();

        try {
            WebResource resource = c.resource("http://localhost:8091/RESTAPI/create");


            ClientResponse response = resource
                    .type("application/json").post(ClientResponse.class, input);

            // check response status code
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void getEmployee(){
        Client c  = Client.create();
        WebResource resource = c.resource("http://localhost:8091/RESTAPI/");

        String response = resource.get(String.class);
        System.out.println(response);
    }

    public static void deleteEmployee(int id){
        Client c  = Client.create();
        WebResource resource = c.resource("http://localhost:8091/RESTAPI/delete/" + id);
        ClientResponse response = resource
                .type("application/json").delete(ClientResponse.class);
        System.out.println(response);
    }

    public static void updateEmployee(int id, String name, int age){
        Client c  = Client.create();

        String input = "{\"age\": "
                + Integer.toString(age) +
                ",\"name\":"
                + "\"" + name + "\"" +
                "}";

        WebResource resource = c.resource("http://localhost:8091/RESTAPI/update/" + id);
        ClientResponse response = resource
                .type("application/json").put(ClientResponse.class, input);
        System.out.println(response);
    }
}
