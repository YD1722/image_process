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
 * @author yasodhah
 */
public class GeneralOperations {
    
    public BufferedImage applyHorizontialFlip(BufferedImage img){
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //BufferedImage imageIn = img.getImage();

         for(int i=0;i<height;i++){
            for(int j=0;j<width;j++)
            {
            Color pldPixeval = new Color(img.getRGB(j, i));
            int r=pldPixeval.getRed();
            int g=pldPixeval.getGreen();
            int b=pldPixeval.getBlue();
            
            imageOut.setRGB(j, height-1-i, new Color(r,g,b).getRGB());
            
            }
         } 
         return imageOut;
    }
    
       public BufferedImage applyVerticalFlip(BufferedImage img){
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         for(int i=0;i<height;i++){
            for(int j=0;j<width;j++)
            {
            Color newPixelVal = new Color(img.getRGB(j, i));
            int r=newPixelVal.getRed();
            int g=newPixelVal.getGreen();
            int b=newPixelVal.getBlue();
            
            imageOut.setRGB(width-1-j, i, new Color(r,g,b).getRGB());
            
            }
         }
         
         return imageOut;
    }
    
}
