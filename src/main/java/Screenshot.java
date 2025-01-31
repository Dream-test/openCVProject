import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screenshot {
    private final Rect searchArea;
    private final String pathName; //Part of the capture file name

    public Screenshot(Rect searchArea, String pathName) {
        this.searchArea = searchArea;
        this.pathName = pathName;
    }
     public String capture() throws AWTException, IOException {
         Rectangle screenRect = new Rectangle(searchArea.x, searchArea.y, searchArea.w, searchArea.h);

         BufferedImage capture = new Robot().createScreenCapture(screenRect);
         String imagePath = "./test/resources/" + pathName + "_capture.png";

         File imageFile = new File(imagePath);
         ImageIO.write(capture, "png", imageFile);

         return imagePath;
     }
}
