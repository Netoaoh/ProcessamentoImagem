package filtros.equalizacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import filtros.GrayScale;

public class Equalizacao {
	public static BufferedImage Apply(BufferedImage image){
		BufferedImage grayImage = GrayScale.Apply(image);
		return histogramEqualization(grayImage);
	}
	
	private static BufferedImage histogramEqualization(BufferedImage original) {
		//Obtem a largura e a altura da imagem original
		int width = original.getWidth();
		int height = original.getHeight();
		
        int red;
        int green;
        int blue;
        int alpha;
        int newPixel = 0;
 
        //Recupera os dados do histograma
        ArrayList<int[]> histData = histogramEqualizationData(original);
 
        //Output da imagem equalizada
        BufferedImage histogramEQ = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        //Loop que percorre todos os pixels da imagem
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
            	//Obtem o valor RGB do pixel
            	int pixel = original.getRGB (i, j);
                alpha = new Color(pixel).getAlpha();	//Recupera o valor de Alpha
                red = new Color(pixel).getRed();	//Recupera o valor de Red
                green = new Color(pixel).getGreen();	//Recupera o valor de Green
                blue = new Color(pixel).getBlue();	//Recupera o valor de Blue
 
                //Define o valor do pixel usando o histograma
                red = histData.get(0)[red];
                green = histData.get(1)[green];
                blue = histData.get(2)[blue];
 
                //Converte o valor do pixel para 8bit
                newPixel = colorToRGB(alpha, red, green, blue);
 
                //Aplica a nova cor no pixel
                histogramEQ.setRGB(i, j, newPixel);
 
            }
        }
 
        return histogramEQ;
 
    }
 
    /*
     * Gera um histograma equalizado separando os valores RGB
     */
    private static ArrayList<int[]> histogramEqualizationData(BufferedImage input) {
 
        //Array que armazena os valores do histograma
        ArrayList<int[]> imageHist = imageHistogram(input);
        ArrayList<int[]> imageData = new ArrayList<int[]>();
 
        //inicializa os arrays
        int[] rhistogram = new int[256];
        int[] ghistogram = new int[256];
        int[] bhistogram = new int[256];
 
        for(int i = 0; i < 256; i++) {
        	rhistogram[i] = 0;
        	ghistogram[i] = 0;
        	bhistogram[i] = 0;
        }
 
        long sumr = 0;
        long sumg = 0;
        long sumb = 0;
 
        //Calcula o fator de escala
        float scaleFactor = (float) (255.0 / (input.getWidth() * input.getHeight()));
 
        //Loop que calcula os valores da imagem
        for(int i = 0; i < rhistogram.length; i++) {
            sumr += imageHist.get(0)[i];
            int valr = (int) (sumr * scaleFactor);
            if(valr > 255) {
                rhistogram[i] = 255;
            }
            else rhistogram[i] = valr;
 
            sumg += imageHist.get(1)[i];
            int valg = (int) (sumg * scaleFactor);
            if(valg > 255) {
                ghistogram[i] = 255;
            }
            else ghistogram[i] = valg;
 
            sumb += imageHist.get(2)[i];
            int valb = (int) (sumb * scaleFactor);
            if(valb > 255) {
                bhistogram[i] = 255;
            }
            else bhistogram[i] = valb;
        }
 
        //Adiciona ao array
        imageData.add(rhistogram);
        imageData.add(ghistogram);
        imageData.add(bhistogram);
 
        return imageData;
 
    }
 
    /*
     * Gera o histograma da imagem
     */
    public static ArrayList<int[]> imageHistogram(BufferedImage input) {
    	//Obtem a largura e a altura da imagem original
    	int width = input.getWidth();
		int height = input.getHeight();
    	
		//Inicializa os arrays
        int[] rhistogram = new int[256];
        int[] ghistogram = new int[256];
        int[] bhistogram = new int[256];
 
        for(int i = 0; i < 256; i++) {
        	rhistogram[i] = 0;
        	ghistogram[i] = 0;
        	bhistogram[i] = 0;
        }
 
        //Loop que percorre todos os pixels da imagem
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
            	//Obtem o valor RGB do pixel
            	int pixel = input.getRGB (i, j);
            	int red = (int) ((pixel & 0x00FF0000) >>> 16);	//Recupera o valor de Red
				int green = (int) ((pixel & 0x0000FF00) >>> 8);	//Recupera o valor de Green
				int blue = (int) (pixel & 0x000000FF);	//Recupera o valor de Blue
 
				//Incrementa o valor do array na posição referente ao tom RGB
                rhistogram[red] += 1; 
                ghistogram[green] += 1; 
                bhistogram[blue] += 1;
 
            }
        }
 
        //Cria e adiciona ao ArrayList
        ArrayList<int[]> hist = new ArrayList<int[]>();
        hist.add(rhistogram);
        hist.add(ghistogram);
        hist.add(bhistogram);
 
        return hist;
 
    }
 
    /*
     * Converte R, G, B, Alpha para o padrão 8bit
     */
    private static int colorToRGB(int alpha, int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += alpha; newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
}
