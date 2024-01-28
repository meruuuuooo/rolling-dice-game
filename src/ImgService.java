import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class ImgService {
    public static JLabel loadImage(String filePath) {
        BufferedImage image;
        JLabel imageContainer;
        try (InputStream inputStream = ImgService.class.getResourceAsStream(filePath)) {
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                imageContainer = new JLabel(new ImageIcon(image));
                return imageContainer;
            } else {
                System.out.println("Error: File not found - " + filePath);
                return null;
            }
        } catch (Exception e) {
            // Handle the exception according to your application's needs
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static void updateImage(JLabel imageContainer, String filePath) {
        BufferedImage image;
        try (InputStream inputStream = ImgService.class.getResourceAsStream(filePath)) {
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
                imageContainer.setIcon(new ImageIcon(image));
            } else {
                System.out.println("Error: File not found - " + filePath);
            }
        } catch (Exception e) {
            // Handle the exception according to your application's needs
            System.out.println("Error: " + e);
        }
    }
}
