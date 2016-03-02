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
