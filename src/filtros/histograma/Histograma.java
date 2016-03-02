package filtros.histograma;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import filtros.GrayScale;

/*
 * Gera o histograma da quantidade de tons de cinza da imagem
 */
public class Histograma {
	
	//Array que armazena os dados do histograma
	public static int[] data;

	/*
	 * Metodo que extrai os dados da imagem
	 */
	public static void GetData(BufferedImage image){
		//Obtem a largura e a altura da imagem original
		int width = image.getWidth();
		int height = image.getHeight();
		
		//Inicializa o array
		data = new int[256];
		
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
				
				//Incrementa o valor do array na posição referente ao tom RGB
				data[r] += 1;
				data[g] += 1;
				data[b] += 1;
			}
		}
	}
	
	/*
	 * Metodo que aplica o filtro
	 */
	public static void Apply(BufferedImage image, String savePath){
		//Chamada do metodo que gera os dados do histograma
		GetData(image);
		
		//Dataset que gera o grafico
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		
		//Loop que percorre o array de dados do histograma
		for (int k = 0; k < data.length; k++) {
			//Adiciona o valor ao dataset
			ds.setValue(data[k], "Imagem", Integer.toString(data[k]));
		}
		
		//Gera o grafico
		JFreeChart grafico = ChartFactory.createLineChart("Histograma", "Tons de cinza", 
				"Valor", ds, PlotOrientation.VERTICAL, true, true, false);
		
		//Salva o grafico em um arquivo de png
		try {
			OutputStream arquivo = new FileOutputStream(savePath);
			ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
