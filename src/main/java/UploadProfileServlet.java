import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;

@WebServlet("/UploadProfileServlet")
@MultipartConfig
public class UploadProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Handle profile picture upload
        Part profilePicturePart = request.getPart("profilePicture");
        String profilePictureFileName = extractFileName(profilePicturePart);
        String profilePictureUploadPath = "path_to_your_profile_picture_directory" + profilePictureFileName;

        try (InputStream inputStream = profilePicturePart.getInputStream()) {
            Files.copy(inputStream, Path.of(profilePictureUploadPath), StandardCopyOption.REPLACE_EXISTING);
        }

        // Handle diploma upload
        Part diplomaPart = request.getPart("diploma");
        String diplomaFileName = extractFileName(diplomaPart);
        String diplomaUploadPath = "path_to_your_diploma_directory" + diplomaFileName;

        try (InputStream inputStream = diplomaPart.getInputStream()) {
            Files.copy(inputStream, Path.of(diplomaUploadPath), StandardCopyOption.REPLACE_EXISTING);
        }

        // You can redirect the user to a success page or display a success message
        response.sendRedirect("success.jsp");
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
