package org.example.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.json.JSONObject;

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
            WebResource resource = c.resource("https://jerseydemo.herokuapp.com/jersey/create");


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
        WebResource resource = c.resource("https://jerseydemo.herokuapp.com/jersey/employess");

        String response = resource.get(String.class);

        JSONArray jsonArray = new JSONArray(response);
        for(int i=0; i< jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("Name: " + jsonObject.getString("name") +"\n"+"Age: " + jsonObject.getInt("age"));
            System.out.println();
        }

        System.out.println(response);
    }

    public static void deleteEmployee(int id){
        Client c  = Client.create();
        WebResource resource = c.resource("https://jerseydemo.herokuapp.com/jersey/delete/" + id);
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

        WebResource resource = c.resource("https://jerseydemo.herokuapp.com/jersey/update/" + id);
        ClientResponse response = resource
                .type("application/json").put(ClientResponse.class, input);
        System.out.println(response);
    }
}
