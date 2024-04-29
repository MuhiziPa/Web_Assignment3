import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/Registration";
            String dbUsername = "root";
            String dbPassword = "password";
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Check if the username and password match
            String query = "SELECT * FROM student WHERE email=? AND password=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    // Login successful
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("profile.jsp");
                } else {
                    // Login failed
                    out.println("<html><body><h2>Login failed. Invalid username or password.</h2></body></html>");
                }
            }

            // Close the database connection
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            out.println("<html><body><h2>Error: " + e.getMessage() + "</h2></body></html>");
        }
    }
}
