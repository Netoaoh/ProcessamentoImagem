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

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import View.Form;
import filtros.GrayScale;
import filtros.Inversion;
import filtros.Sepia;
import filtros.Threshold;
import filtros.equalizacao.Equalizacao;
import filtros.histograma.Histograma;
import filtros.passaAlta.Laplacian;
import filtros.passaAlta.Sobel;
import filtros.passaBaixa.GaussianBlur;
import filtros.passaBaixa.Media;

public class Main {
	
	public static boolean exit = false;
	public static boolean reiniciar = true;
	
	/*
	 * Metodo principal
	 */
	public static void main(final String[] args){
		//Inicia a aplicação em uma thread com SwingUtilities (evita erros do Swing com JFileChooser no MacOSX)
	    if (!SwingUtilities.isEventDispatchThread()) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	while(reiniciar){
	                		app();
	                	}
	                } catch (Exception e) {
	                    throw new RuntimeException(e);
	                }
	            }
	        });
	        return;
	    }
	}

	/*
	 * Ações da aplicação
	 */
	public static void app() {
		
		try {
			//Exibe a janela de escolha de arquivos
			JFileChooser openFileDialig = new JFileChooser();
			openFileDialig.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = openFileDialig.showDialog(null, "Selecione uma imagem");
			
			//Verifica se um arquivo foi selecionado
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				//Recupera o arquivo selecionado
	            File file = openFileDialig.getSelectedFile();
	            
	            //Carrega o arquivo em um BufferedImage
	            BufferedImage originalImage = ImageIO.read(file);
	            
	            //Define as opções de filtro
	            String[] choices = { 
	            		"Escala de Cinza", 
	            		"Negativo", 
	            		"Sepia", 
	            		"Threshold", 
	            		"Gaussian Blur",
	            		"Media",
	            		"Laplacian",
	            		"Sobel",
	            		"Histograma",
	            		"Equalização"
	            		};
	            
	            //Recupera o filtro selecionado
	            String input = (String) JOptionPane.showInputDialog(null, "Selecione o filtro que deseja aplicar.",
	                "Escolha de filtros", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	            
	            //Exibe a janela para salvar a imagem de saida
	            JFileChooser saveFileDialog = new JFileChooser();
	            
	            int ret = saveFileDialog.showSaveDialog(null);
	            
	            String savePath = "";
	            
	            //Verifica se o caminho é valido
	            if (ret == JFileChooser.APPROVE_OPTION) {
	            	savePath = saveFileDialog.getSelectedFile().getCanonicalPath();
	            } else {
	            	//Encerra a aplicação caso o caminho seja invalido
		        	JOptionPane.showMessageDialog(null, "Encerrando programa.");
		        	reiniciar = false;
					exit = true;
					
					System.exit(0);
		        }
	            
	            BufferedImage tempImage = null;
	            
	            //Verifica qual filtro foi selecionado
	            switch(input)
	            {
		            case "Escala de Cinza":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = GrayScale.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Negativo":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Inversion.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Sepia":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Sepia.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Threshold":
		            	//Exibe a janela para escolha do limiar
		            	String threshold = JOptionPane.showInputDialog("Digite o valor do threshold.");
		            	int val = 128;
		            	//Tenta converter o valor digitado para int
		            	try{
		            		val = Integer.parseInt(threshold);
		            	} catch(Exception e){
		            		//Exibe mensagem de erro
		            		JOptionPane.showMessageDialog(null, "Valor inválido. Atribuido valor padrão (128).");
		            	}
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Threshold.Apply(originalImage, val);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Gaussian Blur":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = GaussianBlur.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Media":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Media.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Laplacian":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Laplacian.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Sobel":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Sobel.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
		            case "Histograma":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	originalImage = GrayScale.Apply(originalImage);
		            	Histograma.Apply(originalImage, savePath);
		            	break;
		            case "Equalização":
		            	//Aplica o filtro e salva a imagem no caminho selecionado
		            	tempImage = Equalizacao.Apply(originalImage);
		            	originalImage = GrayScale.Apply(originalImage);
						ImageIO.write(tempImage, "png",new File(savePath));
		            	break;
	            	default:
	            		//Exibe mensagem de erro
	            		JOptionPane.showMessageDialog(null, "Filtro inválido.");
	            		break;
	            }
	            
	            //Carrega a imagem filtrada em um BufferedImage
	            BufferedImage filtredImage = ImageIO.read(new File(savePath));
	            //Exibe a imagem original e a imagem filtrada
				Form.exibirImagem(originalImage, filtredImage);
				
				//Exibe a mensagem de filtro aplicado
				JOptionPane.showMessageDialog(null, "Filtro aplicado com sucesso.");
				
				//Define as opções de recomeço
				Object[] options = {
						"Escolher outro filtro",
	                    "Encerrar"
	                    };
				
				//Recupera a opção selecionada
				int opt = JOptionPane.showOptionDialog(null,
				    "O que deseja fazer?",
				    "Filtro aplicado com sucesso.",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[0]);
				
				//Verifica a opção selecionada
				switch(opt)
				{
					case 0:
						reiniciar = true;
						exit = false;
						break;
					case 1:
						reiniciar = false;
						exit = true;
						break;
					default:
						break;
				}
				
	        } else {
	        	//Exibe mensagem de erro
	        	JOptionPane.showMessageDialog(null, "Encerrando programa.");
	        	reiniciar = false;
				exit = true;
	        }
		} catch(Exception e){
        	e.printStackTrace();
        }
        
		//Encerra a aplicação
		if(exit)
			System.exit(0);
	}

}
