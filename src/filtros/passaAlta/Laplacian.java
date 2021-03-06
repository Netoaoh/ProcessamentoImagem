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
 * Aplica o filtro Laplacian
 */
public class Laplacian {

	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image) {
		
		//Matriz laplacian
	    float matrix[] = {	0, -1, 0,
	                  		-1, 4, -1,
	                  		0, -1, 0};
		
	    //Output da imagem com o filtro aplicado
		BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Define a matriz de convolução
		ConvolveOp op = new ConvolveOp( new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null );
		//Aplica o filtro
		image = op.filter(image, dest);
		
		return dest;
	}
}
