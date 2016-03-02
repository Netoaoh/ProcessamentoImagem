package filtros.passaAlta;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/*
 * Aplica o filtro Sobel
 */
public class Sobel {
	
	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image) {
		//Obtem a largura e a altura da imagem original
		int width = image.getWidth();
		int height = image.getHeight();
		
		//Matriz horizontal
	    float matrixH[] = {	1.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f,
	                  		0.0f/4.0f, 0.0f/4.0f, 0.0f/4.0f,
	                  		-1.0f/4.0f, -2.0f/4.0f, -1.0f/4.0f };
		
	    //Matriz vertical
	    float matrixV[] = {	1.0f/4.0f, 0.0f/4.0f, -1.0f/4.0f,
			          		1.0f/4.0f, 0.0f/4.0f, -2.0f/4.0f,
			          		1.0f/4.0f, 0.0f/4.0f, -1.0f/4.0f };

	    
	    //Output da imagem com o filtro aplicado
		BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Imagem que recebe a matriz horizontal
		BufferedImage tempH = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		//Imagem que recebe a matriz vertical
		BufferedImage tempV = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Define a matriz de convolução horizontal
		ConvolveOp opH = new ConvolveOp( new Kernel(3, 3, matrixH), ConvolveOp.EDGE_NO_OP, null );
		//Aplica o filtro
		opH.filter(image, tempH);
		
		//Define a matriz de convolução vertical
		ConvolveOp opV = new ConvolveOp( new Kernel(3, 3, matrixV), ConvolveOp.EDGE_NO_OP, null );
		//Aplica o filtro
		opV.filter(image, tempV);
		
		//Loop que percorre todos os pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//Define o valor do novo pixel somando os pixels da imagem com matriz vertical e horizontal
				dest.setRGB(i, j, tempH.getRGB(i, j) + tempV.getRGB(i, j));
			}
		}
		
		return dest;
	}
}
