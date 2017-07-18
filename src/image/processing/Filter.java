package image.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    int[][] mask = new int[][]{
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1},};
    int[][] edgeDetectMask = new int[][]{
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1},};

    public BufferedImage doMeanFiltering(Image img) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int j = 1; j < height - 1; j++) {  //j ==> height
            for (int i = 1; i < width - 1; i++) {   // i==> width
                int colorRed = 0;
                int colorGreen = 0;
                int colorBlue = 0;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        //System.out.println((i - 1 + column) + "  " + (j - 1 + row));
                        colorRed = colorRed + (img.getPixelValAt(i - 1 + c, j - 1 + r).getRed() * mask[r][c]);
                        colorGreen = colorGreen + ((img.getPixelValAt(i - 1 + c, j - 1 + r).getGreen() * mask[r][c]));
                        colorBlue = colorBlue + ((img.getPixelValAt(i - 1 + c, j - 1 + r).getBlue() * mask[r][c]));
                        //RGB += (img.getPixelValAt(i - 1 + column, j - 1 + row).getRGB() * mask[row][column]);
                        //RGB+=(double)(img.getPixelValAt((i-1+column, j-1+row).getRGB()*mask1[row][column]))/9;
                    }
                }
                //System.out.println(RGB);
                Color newPixelValue = new Color((int) (colorRed) / 9, (int) (colorGreen) / 9, (int) (colorBlue) / 9);

                imageOut.setRGB(i, j, newPixelValue.getRGB());
            }
        }
        return imageOut;

    }

    public BufferedImage doMedianFiltering(Image img) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int j = 1; j < height - 1; j++) {  //j ==> height
            for (int i = 1; i < width - 1; i++) {   // i==> width

                ArrayList<Integer> tempPixelList = new ArrayList();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        tempPixelList.add(img.getPixelValAt(i - 1 + c, j - 1 + r).getRGB());
                    }
                }
                imageOut.setRGB(i, j, tempPixelList.get(4));

            }
        }
        return imageOut;
    }

    public BufferedImage doAlphaTrimFiltering(Image img, int p) {
        int height = img.getHeight();
        int width = img.getWidth();
        //d= N-2p
        int d = 9 - (2 * p);
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int j = 1; j < height - 1; j++) {  //j ==> height
            for (int i = 1; i < width - 1; i++) {   // i==> width

                ArrayList<Color> tempPixelList = new ArrayList();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        tempPixelList.add(img.getPixelValAt(i - 1 + c, j - 1 + r));
                    }
                }
                //int rgbVal = 0;

                double red = 0;
                double green = 0;
                double blue = 0;
                for (int k = p; k < 9 - p; k++) {
                    //rgbVal += tempPixelList.get(k);
                    red = red + tempPixelList.get(k).getRed();
                    green = green + tempPixelList.get(k).getGreen();
                    blue = blue + tempPixelList.get(k).getBlue();
                }
                red = red / d;
                green = green / d;
                blue = blue / d;

                Color newPixelValue = new Color((int) red, (int) green, (int) blue);
                imageOut.setRGB(i, j, newPixelValue.getRGB());
            }
        }
        return imageOut;

    }

    public BufferedImage doEdgeDetection(Image img) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int j = 1; j < height; j++) {
            for (int i = 1; i < width; i++) {
                int colorRed = 0;
                int colorGreen = 0;
                int colorBlue = 0;

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        Color mycolor = img.getPixelValAt(i - 1 + col, j - 1 + row);
                        colorRed = colorRed + (mycolor.getRed() * edgeDetectMask[row][col]);
                        colorRed = colorRed + (mycolor.getRed() * edgeDetectMask[2 - col][row]);

                        colorGreen = colorGreen + (mycolor.getGreen() * edgeDetectMask[row][col]);
                        colorGreen = colorGreen + (mycolor.getGreen() * edgeDetectMask[2 - col][row]);

                        colorBlue = colorBlue + (mycolor.getBlue() * edgeDetectMask[2 - col][row]);
                        colorBlue = colorBlue + (mycolor.getBlue() * edgeDetectMask[row][col]);

                    }
                }
               
                try {
                    Color newPixelValue = new Color(colorRed, colorGreen, colorBlue);
                    imageOut.setRGB(i, j, newPixelValue.getRGB());
                } catch (Exception e) {

                }

            }
        }
        return imageOut;
    }
}
