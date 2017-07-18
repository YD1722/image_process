package image.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Main {

    public static Image image;
    public static Filter filter;
    public static Scale scale;

//    public static void main(String[] args) {
//		//meanFilter=new MeanFilter(); 
//        //meanFilter.doMeanFilter(5);
//        image = new Image("grayscale.jpg");
//        filter = new Filter();
//        scale = new Scale();
//                //filter.doMeanFilter(3,image);
//        //BufferedImage img= filter.doAlphaTrimFiltering(image,0);
//        BufferedImage img = scale.upScale(image, 2);
//
//        File f = new File("MyFile.png");
//        try {
//            ImageIO.write(img, "png", f);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
