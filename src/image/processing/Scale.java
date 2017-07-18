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

        for (int j = 0; j < imageOut.getHeight(); j++) {
            for (int i = 0; i < imageOut.getWidth(); i++) {
                try{
                    Color newPixelVal = img.getPixelValAt( (i * factor), (j * factor));
                    imageOut.setRGB(i, j, newPixelVal.getRGB()); 
                }catch(Exception e){
                    // not cosider wrong pixel values
                }
            }
        }
        return imageOut;
    }
    public BufferedImage upScale(Image img, int factor) {
       BufferedImage imageOut = new BufferedImage(img.getWidth() * factor, img.getHeight() * factor, BufferedImage.TYPE_INT_RGB);
       BufferedImage imageIn= img.getImage();
        //imagemodel UpScaleImage = new imagemodel((int) (img.getWidth() * factor), (int) (img.getHeight() * factor), image.getType());
        for (int i = 0; i < imageOut.getWidth(); i++) {
            for (int j = 0; j < imageOut.getHeight(); j++) {
                int x = (int) (i / factor);
                int y = (int) (j / factor);
                //imageOut.setRGB(i, j, newPixelVal.getRGB()); 

                imageOut.setRGB(i, j, imageIn.getRGB(x,y));
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
    
    public BufferedImage bilnearDownScale(){
        return null;
    }
}
