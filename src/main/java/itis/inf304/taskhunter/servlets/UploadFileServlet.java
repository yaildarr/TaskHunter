package itis.inf304.taskhunter.servlets;

import com.cloudinary.Cloudinary;
import itis.inf304.taskhunter.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
        fileSizeThreshold = 5 * 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UploadFileServlet extends HttpServlet {


    private static final Logger LOG = Logger.getLogger(UploadFileServlet.class.getName());

    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    private static final String FILE_PREFIX = "/tmp";
    private static final int DIRECTORIES_COUNT = 10;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = getFile(filename, part);

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
            String fileUrl = (String) uploadResult.get("url");
            req.getSession().setAttribute("newPhotoUrl", fileUrl);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"fileUrl\": \"" + fileUrl + "\"}");
        } catch (Exception e) {
            LOG.severe("Error uploading file: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error uploading file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

    private static File getFile(String filename, Part part) throws IOException {
        File file = new File(FILE_PREFIX + File.separator + filename.hashCode() % DIRECTORIES_COUNT
                + File.separator + filename);

        file.getParentFile().mkdirs();
        try (InputStream content = part.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return file;
    }
}
