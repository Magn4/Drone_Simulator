package Code.API;

import com.google.gson.Gson;

public class Selector {
    public static void main(String[] args){

        String json = """
                {'name':'Dani',
                'age': 45,
                'phone': 222333,
                'city':'NewYork'}
                """;

                Gson gson = new Gson();

                User user = gson.fromJson(json, User.class);

                System.out.println(user);

    }
    
}
