package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

/*    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        if(id == 0) {
            pw.print("<html" +
                    "<h3>Available users:</h3><br/>" +
                    "User ID: " +
                    "<ul>");
            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
                pw.print("<li>" + entry.getKey() + "</li>" +
                        "<ul>  " +
                        "<li>Name " + entry.getValue().getName() + "</li>" +
                        "<li>Surename" + entry.getValue().getSurename() + "</li>" +
                        "<li>Salary" + entry.getValue().getSalary() + "</li>" +
                        "</ul>");
            }
            pw.print("</ul>" +
                    "<a href=\"index.jsp\">Home</a>" +
                    "</html>");
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.print("<html" +
                        "<h3>Incorrect user</h3><br/>" +
                        "<a href=\"index.jsp\">Home</a>" +
                        "</html>");
            } else {
                pw.print("<html" +
                        "<h3>Required user</h3><br/>" +
                        "Name:" + model.getFromList().get(id).getName() + "<br/>" +
                        "Surename:" + model.getFromList().get(id).getSurename() + "<br/>" +
                        "Salary:" + model.getFromList().get(id).getSalary() + "<br/>" +
                        "<a href=\"index.jsp\">Home</a>" +
                        "</html>");
            }
        } else {
            pw.print("<html" +
                    "<h3>ID must be > 0</h3><br/>" +
                    "<a href=\"index.jsp\">Home</a>" +
                    "</html>");
        }

    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }catch (Exception e) {
            System.out.println("error");
        }
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);


        request.setCharacterEncoding("application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        int id = jobj.get("id").getAsInt();

        PrintWriter pw = response.getWriter();

        if(id == 0) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.print("Incorrect_User");
            } else {
                pw.print(gson.toJson(model.get(id)));
            }
        } else {
            pw.print("ID_must_be_Above_Zero");
        }
    }
}
