<%@ page import="java.sql.*" %>
<html>
<head>
    <title>List of Files</title>
</head>
<body>
    <h1>List of Files</h1>
    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Download</th>
        </tr>
        <%
            String dbURL = "jdbc:mysql://localhost:3306/myseries";
            String dbUser = "root";
            String dbPass = "root";
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
                String sql = "SELECT first_name, last_name FROM contacts";
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                // Check if any rows are returned
                if (!result.isBeforeFirst()) {
                    out.println("<tr><td colspan='3'>No files found in the database</td></tr>");
                }

                while (result.next()) {
                    String firstName = result.getString("first_name");
                    String lastName = result.getString("last_name");

                    if (firstName != null && lastName != null) {
        %>
                    <tr>
                        <td><%= firstName %></td>
                        <td><%= lastName %></td>
                        <td><a href="FileDownloadServlet?first_name=<%= firstName %>&last_name=<%= lastName %>">Download</a></td>
                    </tr>
        <%
                    } else {
                        out.println("<tr><td colspan='3'>Invalid entry in the database</td></tr>");
                    }
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<tr><td colspan='3'>Error: " + e.getMessage() + "</td></tr>");
            }
        %>
    </table>
</body>
</html>
