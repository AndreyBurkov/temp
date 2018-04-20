import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class MyServlet extends HttpServlet {

    private final String URL = "jdbc:mysql://mymessenger.cvlbgdhvltbw.ap-southeast-2.rds.amazonaws.com:3306/mymessenger";
    private final String USERNAME = "root";
    private final String PASSWORD = "12345678";
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("\n\n\n\tBINGO!!!\n\n\n");
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                writer.println("Name: " + resultSet.getString("firstname"));
                writer.println("Surname: " + resultSet.getString("secondname"));
                writer.println("Password: " + resultSet.getString("password"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("\n\n\n\tНе удалось подключиться к серверу!!!\n\n\n");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
