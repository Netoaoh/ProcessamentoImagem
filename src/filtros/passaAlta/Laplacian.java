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
