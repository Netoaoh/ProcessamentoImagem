package filtros.passaBaixa;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/*
 * Aplica o filtro GaussianBlur
 */
public class GaussianBlur {
	
	/*
	 * Metodo que aplica o filtro
	 */
	public static BufferedImage Apply(BufferedImage image) {
		
		//Matriz GaussianBlur
		float matrix[] = {
				1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f,
				2.0f/16.0f, 4.0f/16.0f, 2.0f/16.0f,
				1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f
		};
		
		//Output da imagem com o filtro aplicado
		BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		//Define a matriz de convolução
		ConvolveOp op = new ConvolveOp( new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null );
		//Aplica o filtro
		image = op.filter(image, dest);
		
		return dest;
	}
}
