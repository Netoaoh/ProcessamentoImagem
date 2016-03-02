/**
*	Copyright 2015 Paulo Maria Neto
*	
*	This file is part of ProcessamentoImagem.
*	ProcessamentoImagem is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
*	as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*	ProcessamentoImagem is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
*	without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
*	See the GNU General Public License for more details.
*	You should have received a copy of the GNU General Public License along with ProcessamentoImagem. 
*	If not, see http://www.gnu.org/licenses/.
*/

package filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * Aplica o filtro de escala de cinza
 */
public class GrayScale {

	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image){
		//Obtem a largura e a altura da imagem original
		int width = image.getWidth();
		int height = image.getHeight();
		int media = 0;
		
		//Output da imagem com o filtro aplicado
		BufferedImage dst = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Loop que percorre todos os pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//Obtem o valor RGB do pixel
				int pixel = image.getRGB(i, j);
				int r = (int) ((pixel & 0x00FF0000) >>> 16);	//Recupera o valor de Red
				int g = (int) ((pixel & 0x0000FF00) >>> 8);	//Recupera o valor de Green
				int b = (int) (pixel & 0x000000FF);	//Recupera o valor de Blue
				
				//Calcula a media dos valores RGB
				media = (r + g + b) / 3;
				
				//Define a nova cor do pixel
				Color color = new Color(media, media, media);
				
				//Aplica a nova cor no pixel
				dst.setRGB(i, j, color.getRGB());
			}
		}
		
		return dst;
	}
}
