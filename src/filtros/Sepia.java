package filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * Aplica o filtro Sepia
 */
public class Sepia {

	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image){
		//Obtem a largura e a altura da imagem original
		int width = image.getWidth();
		int height = image.getHeight();
		
		//Output da imagem com o filtro aplicado
		BufferedImage dst = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		//Aplica o filtro de escala de cinza na imagem
		BufferedImage temp = GrayScale.Apply(image);
		
		//Loop que percorre todos os pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//Obtem o valor RGB do pixel
				int pixel = temp.getRGB(i, j);
				int r = (int) ((pixel & 0x00FF0000) >>> 16);	//Recupera o valor de Red
				int g = (int) ((pixel & 0x0000FF00) >>> 8);	//Recupera o valor de Green
				int b = (int) (pixel & 0x000000FF);	//Recupera o valor de Blue
				
				//Altera os valores originais do pixel
				r += 40;
				g += 20;
				b -= 20;
				
				//Verifica se os valores ultrapassam 255 e redefine o valor para o maximo
				if(r > 255)
					r = 255;
				
				if(g > 255)
					g = 255;
				
				if(b > 255)
					b = 255;
				
				//Define a nova cor do pixel
				Color color = new Color(r, g, b);
				
				//Aplica a nova cor no pixel
				dst.setRGB(i, j, color.getRGB());
			}
		}
		
		return dst;
	}
}
