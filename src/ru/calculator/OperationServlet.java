package ru.calculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.calculator.logic.Parameters;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/counter")
public class OperationServlet extends HttpServlet {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        private static final Parameters obj = new Parameters();

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            StringBuffer jb = new StringBuffer();
            String line;

            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (Exception e) {
                System.out.println("error");
            }
            JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);


            request.setCharacterEncoding("application/json;charset=UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            double a = jobj.get("a").getAsDouble();
            double b = jobj.get("b").getAsDouble();
            String operation = jobj.get("operation").getAsString();

            PrintWriter pw = response.getWriter();

            switch (operation) {
                case "+":
                    obj.setResult(a + b);
                    pw.print(gson.toJson(obj));
                    break;
                case "-":
                    obj.setResult(a - b);
                    pw.print(gson.toJson(obj));
                    break;
                case "*":
                    obj.setResult(a * b);
                    pw.print(gson.toJson(obj));
                    break;
            }

        }
}

