package in.lali;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    private String dbURL = "jdbc:mysql://localhost:3306/myseries";
    private String dbUser = "root";
    private String dbPass = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        System.out.println("First Name: " + firstName); // Debugging statement
        System.out.println("Last Name: " + lastName);   // Debugging statement

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            response.getWriter().print("Error: Missing first name or last name parameter.");
            return;
        }

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "SELECT photo FROM contacts WHERE first_name = ? AND last_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                byte[] fileData = result.getBytes("photo");

                if (fileData != null) {
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=" + firstName + "_" + lastName + ".jpg");

                    OutputStream out = response.getOutputStream();
                    out.write(fileData);
                    out.close();
                } else {
                    response.getWriter().print("Error: File data is null.");
                }
            } else {
                response.getWriter().print("Error: No file found for the provided name.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
