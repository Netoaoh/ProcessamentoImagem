package filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * Aplica o filtro de inversão de cores (negativo)
 */
public class Inversion {

	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image) {
		//Obtem a largura e a altura da imagem original
		int width = image.getWidth();
		int height = image.getHeight();
		
		//Output da imagem com o filtro aplicado
		BufferedImage dst = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Loop que percorre todos os pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//Obtem o valor RGB do pixel
				int rgb = image.getRGB(i, j);
				int r = 255 - (int) ((rgb & 0x00FF0000) >>> 16);	//Subtrai o valor de Red do branco
				int g = 255 - (int) ((rgb & 0x0000FF00) >>> 8);	//Subtrai o valor de Green do branco
				int b = 255 - (int) (rgb & 0x000000FF);	//Subtrai o valor de Blue do branco
				
				//Define a nova cor do pixel
				Color color = new Color(r, g, b);
				
				//Aplica a nova cor no pixel
				dst.setRGB(i, j, color.getRGB());
			}
		}
		return dst;
	}
}
