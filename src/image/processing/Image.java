package image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
//import javax.swing.JFrame;

public class Image {

    private int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getPixelValAt(int x, int y) {
        return new Color(this.image.getRGB(x, y));
    }

   
    private int height;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }
    private List<List<Color>> pixelArray;

    Image(File input) {
        try {
            //File input = new File(imgPath);
            this.image = ImageIO.read(input);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<List<Color>> getPixels(String fileName) {

        List<List<Color>> pixels = new ArrayList<List<Color>>();
        try {
            File input = new File(fileName);
            this.image = ImageIO.read(input);
            this.width = image.getWidth();
            this.height = image.getHeight();
            //System.out.println(width+"__"+height);
            int count = 0;
            //pixels = new ArrayList[width][height];
            for (int i = 0; i < height; i++) {
                ArrayList<Color> temp = new ArrayList();
                for (int j = 0; j < width; j++) {
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    temp.add(c);
	               //System.out.println();
                    //temp[j-1]= c;

	               //System.out.println(c);
                    //System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                }
                pixels.add(temp);
            }

        } catch (Exception e) {
        }

        return pixels;
        // ?
    }

    public List<List<Color>> applyKernel(int row, int col, int size, List<List<Color>> pixelArray) {
        List<List<Color>> kernel_pixels = new ArrayList<List<Color>>();
        for (int i = row; i < row + size; i++) {
            ArrayList<Color> temp = new ArrayList();
            for (int j = col; j < col + size; j++) {
                temp.add(pixelArray.get(i).get(j));
            }
            kernel_pixels.add(temp);
        }

        return kernel_pixels;
    }

}
