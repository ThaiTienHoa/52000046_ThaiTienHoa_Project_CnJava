package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";
    private static final List<String> VALID_EXTENSIONS = Arrays.asList("txt", "doc", "docx", "img", "pdf", "rar", "zip");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Get the file name from the request
        String fileName = request.getParameter("fileName");

        // Get the uploaded file as a Part object
        Part filePart = request.getPart("file");

        // Get the file extension
        String fileExtension = getFileExtension(filePart);

        // Check if the file extension is valid
        if (!isValidExtension(fileExtension)) {
            // If the file extension is not valid, display an error message
            response.getWriter().println("Unsupported file extension");
            return;
        }

        // Create the uploads directory if it does not exist
        File uploadDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Check if the file already exists in the uploads directory
        File existingFile = new File(uploadDir.getAbsolutePath() + File.separator + fileName);
        boolean fileExists = existingFile.exists();
        boolean overrideIfExists = request.getParameter("overrideIfExists") != null;

        if (fileExists && !overrideIfExists) {
            // If the file already exists and the "override if exists" check box is not selected, display an error message
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, existingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // return HTML response with link to uploaded file
            String fileURL = request.getContextPath() + "/" + UPLOAD_DIR + "/" + existingFile.getName();
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("File uploaded. Click <a href=\"" + fileURL + "\">here</a> to visit file.");
            response.getWriter().println("</body></html>");
            return;
        }

        // Save the uploaded file to the uploads directory
        String filePath = existingFile.getAbsolutePath();
        filePart.write(filePath);

        // Display a success message
        if (fileExists && overrideIfExists) {
            response.getWriter().println("File has been overridden");
        } else {
            response.getWriter().println("File already exists");
        }
    }

    private String getFileExtension(Part part) {
        String[] parts = part.getSubmittedFileName().split("\\.");
        return parts[parts.length - 1];
    }

    private boolean isValidExtension(String extension) {
        return VALID_EXTENSIONS.contains(extension);
    }

}
