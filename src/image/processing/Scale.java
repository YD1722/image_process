/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author YD
 */
public class Scale {

    public BufferedImage downScale(Image img, int factor) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width / factor, height / factor, BufferedImage.TYPE_INT_RGB);
        //BufferedImage imageIn = img.getImage();


        for (int j = 0; j < imageOut.getHeight(); j++) {
            for (int i = 0; i < imageOut.getWidth(); i++) {
                try {
                    Color newPixelVal = img.getPixelValAt((i * factor), (j * factor));
                    imageOut.setRGB(i, j, newPixelVal.getRGB());
                } catch (Exception e) {
                    // not cosider wrong pixel values
                }
            }
        }
        return imageOut;
    }

    public BufferedImage upScale(Image img, int factor) {
        BufferedImage imageOut = new BufferedImage(img.getWidth() * factor, img.getHeight() * factor, BufferedImage.TYPE_INT_RGB);
        BufferedImage imageIn = img.getImage();
        //imagemodel UpScaleImage = new imagemodel((int) (img.getWidth() * factor), (int) (img.getHeight() * factor), image.getType());
        for (int i = 0; i < imageOut.getWidth(); i++) {
            for (int j = 0; j < imageOut.getHeight(); j++) {
                int x = (int) (i / factor);
                int y = (int) (j / factor);
                //imageOut.setRGB(i, j, newPixelVal.getRGB()); 

                imageOut.setRGB(i, j, imageIn.getRGB(x, y));
            }
        }
        return imageOut;
    }

//    public BufferedImage upScale(Image img, int factor) {
//        int height = img.getHeight();
//        int width = img.getWidth();
//        BufferedImage imageOut = new BufferedImage(width*factor, height*factor, BufferedImage.TYPE_INT_RGB);
//
//        for (int j = 0; j < height; j++) { //h
//            for (int i = 0; i < width; i++) {//w
//                try{
//                    Color newPixelVal = img.getPixelValAt( (i), (j));
//                    imageOut.setRGB(i, j, newPixelVal.getRGB()); 
//                    imageOut.setRGB(i+1,j,newPixelVal.getRGB());
//                    imageOut.setRGB(i, j+1, newPixelVal.getRGB()); 
//                    imageOut.setRGB(i+1,j+1,newPixelVal.getRGB());
//                }catch(Exception e){
//                    // not cosider wrong pixel values
//                }
//            }
//        }
//        
//        return imageOut;
//
//    }
    public BufferedImage bilnearDownScale(Image img, double factor) {
        System.out.println(img.getHeight());
        //System.out.println(img.getWidth());
        
        BufferedImage imageOut = new BufferedImage((int) ((double)img.getWidth() * factor), (int) ((double)img.getHeight() * factor), BufferedImage.TYPE_INT_RGB);
        System.out.println(imageOut.getHeight());
        BufferedImage imageIn = img.getImage(); // get buffered image

        for (int i = 0; i < imageOut.getWidth(); i++) {
            for (int j = 0; j < imageOut.getHeight(); j++) {
                double pxD = (i / factor);
                double pyD = (j / factor);
                double xDiff = pxD - (int) pxD;
                double yDiff = pyD - (int) pyD;
                
                int[] pA= {(int)(pxD),(int)(pyD)};
                int[] pB = {pA[0] + 1, pA[1]};
                int[] pC = {pA[0], pA[1] + 1};
                int[] pD = {pC[0] + 1, pC[1]};
                int[][] pixelValues = new int[][]{
                    pA,
                    pB,
                    pC,
                    pD
                };
                //System.out.println(pixelValues);
                for (int k = 0; k < 4; k++) {
                    if (pixelValues[k][1] >= img.getHeight()) {
                        pixelValues[k][1] = img.getHeight() - 1;
                    }
                    if (pixelValues[k][0] >= img.getWidth()) {
                        pixelValues[k][0] = img.getWidth() - 1;
                    }

                }

                Color cA = new Color(imageIn.getRGB(pA[0],pA[1]));
                Color cB = new Color(imageIn.getRGB(pB[0], pB[1]));
                Color cC = new Color(imageIn.getRGB(pC[0], pC[1]));
                Color cD = new Color(imageIn.getRGB(pD[0], pD[1]));

                int val = (int) (cA.getRed() * (1 - xDiff) * (1 - yDiff)
                        + cB.getRed() * xDiff * (1 - yDiff)
                        + cC.getRed() * yDiff * (1 - xDiff)
                        + cD.getRed() * xDiff * yDiff);

                if (val < 0) {
                    val = 0;
                }
                if (val > 255) {
                    val = 255;
                }
                imageOut.setRGB(i, j, new Color(val, val, val).getRGB());
            }
        }

        return imageOut;
    }
}
