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

            //Доступные операции
/*
            "+ - Сложение"
            "- - Разность"
            "* - Умножение"
            "/ - Деление"
            "div - Целочисленное деление"
            "mod - Остаток от деления"
            "^ - Возведение в степень (a - число, b - необходимая степень)"
*/

            switch (operation) {
                case "+":
                    obj.setResult(a+b);
                    pw.print(gson.toJson(obj));
                    break;
                case "-":
                    obj.setResult(a-b);
                    pw.print(gson.toJson(obj));
                    break;
                case "*":
                    obj.setResult(a*b);
                    pw.print(gson.toJson(obj));
                    break;
                case "/":
                    if (b != 0) {
                        double c = a;
                        obj.setResult(c/a);
                        pw.print(gson.toJson(obj));
                        break;
                    } else {
                        pw.print("На ноль делить нельзя");
                        break;
                    }
                case "div":
                    if (b != 0) {
                        obj.setResult(a/b);
                        pw.print(gson.toJson(obj));
                        break;
                    } else {
                        pw.print("На ноль делить нельзя");
                        break;
                    }
                case "mod":
                    if (b != 0) {
                        obj.setResult(a % b);
                        pw.print(gson.toJson(obj));
                        break;
                    } else {
                        pw.print("На ноль делить нельзя");
                        break;
                    }
                case "^":
                    double res = 1;
                    for (int c = 1; c <= Math.abs(b); c++) {
                        res = res * a;
                    }
                    if (b<0 && b!=0) {
                        System.out.println(1/res);
                        obj.setResult(1/res);
                        pw.print(gson.toJson(obj));
                    }
                    else if (b>0 && b!=0) {
                        obj.setResult(res);
                        pw.print(gson.toJson(obj));
                    }
                    else {
                        obj.setResult(1);
                        pw.print(gson.toJson(obj));
                    }
            }
        }
}

