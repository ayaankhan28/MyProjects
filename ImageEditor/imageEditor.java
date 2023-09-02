import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class imageEditor{

    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    public static BufferedImage changeBrightness(BufferedImage inputImage, int increase) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();

                // Adjust brightness based on the "increase" parameter
                red = red + (increase * red / 100);
                blue = blue + (increase * blue / 100);
                green = green + (increase * green / 100);

                // Ensure color components are within the valid range [0, 255]
                if (red > 255)
                    red = 255;
                if (blue > 255)
                    blue = 255;
                if (green > 255)
                    green = 255;
                if (red < 0)
                    red = 0;
                if (blue < 0)
                    blue = 0;
                if (green < 0)
                    green = 0;

                // Create a new color with adjusted brightness
                Color newPixel = new Color(red, green, blue);

                // Set the pixel in the output image
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }

        return outputImage;
    }
    public static void printPixelValues(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                System.out.print("(" + pixel.getRed() + " " + pixel.getBlue() + " " + pixel.getGreen() + ")");
            }
            System.out.println();
        }
    }
    public static BufferedImage Vertical_Flip(BufferedImage inputImage)
    {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);

        for(int i=0; i<height; i++)
            for(int j=0; j<width; j++)
                outputImage.setRGB(j, i, inputImage.getRGB(j, height-1-i));

        return outputImage;
    }




    public static BufferedImage Horizontal_Flip(BufferedImage inputImage)
    {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);

        for(int i=0; i<height; i++)
            for(int j=0; j<width; j++)
                outputImage.setRGB(j, i, inputImage.getRGB(width-1-j, i));

        return outputImage;
    }
    public static BufferedImage Rotate_Clockwise(BufferedImage inputImage)
    {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        for(int i=0; i<height; i++)
            for(int j=0; j<width; j++)
            {
                outputImage.setRGB(height-1-i, j, inputImage.getRGB(j,i));
            }
        return outputImage;
    }

    // 1 2 3 0
    // 4 5 6 0
    // 7 8 9 0

    public static BufferedImage Rotate_AntiClockwise(BufferedImage inputImage)
    {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        for(int i=0; i<height; i++)
            for(int j=0; j<width; j++)
            {
                outputImage.setRGB(i, width-1-j, inputImage.getRGB(j,i));
            }
        return outputImage;
    }





    public static void main(String args[]) {
        //
        /*BufferedImage outim;
        File inputFile = new File("C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png");

        try {

            BufferedImage inputImage = ImageIO.read(inputFile);
            outim = convertToGrayScale(inputImage);
            File outputImageFile = new File("C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image2.png");
            ImageIO.write(outim, "PNG", outputImageFile);3
        }
        catch (IOException e){
            System.out.println("error");
        }*/
        //Execute(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image3.png",0);


        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("SELECT ANY OF THE FOLLOWING OPERATIONS\n" +
                    "1. Convert to GrayScale\n" +
                    "2. Change Brightness\n" +
                    "3. Vertical Flip\n" +
                    "4. Horizontal Flip\n" +
                    "5. Rotate Clockwise\n" +
                    "6. Rotate Anti-Clockwise");

            int edit_type = sc.nextInt();
            sc.nextLine();

            if(edit_type==1){ //grayscale
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();
                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = convertToGrayScale(inputImage);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }
            else if(edit_type==2){//brightening
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();
                System.out.println("provide your brightness percentage");
                int briper = sc.nextInt();
                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = changeBrightness(inputImage,briper);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }
            else if(edit_type==3){//horizontal flip
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();

                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = Horizontal_Flip(inputImage);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }
            else if(edit_type==4){//vertical flip
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();

                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = Vertical_Flip(inputImage);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }
            else if(edit_type==5){//vertical flip
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();

                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = Rotate_Clockwise(inputImage);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }
            else if(edit_type==6){//vertical flip
                //(1,"C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image1.png","C:\\Users\\Dell\\IdeaProjects\\Scaler Java\\src\\image7.png",0);
                System.out.println("provide your input image path");
                String ipimg = sc.nextLine();
                //sc.nextLine();
                System.out.println("provide your output image path");
                String opimg = sc.nextLine();

                File inputFile = new File(ipimg);
                BufferedImage outim;
                File outputImageFile = new File(opimg);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    outim = Rotate_AntiClockwise(inputImage);
                    ImageIO.write(outim, "PNG", outputImageFile);
                    //ImageIO.getImageWritersByFormatName("PNG").next().dispose();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
                break;
            }


        }






    }

}