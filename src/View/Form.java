/**
*   Copyright 2015 Paulo Maria Neto
*   
*   This file is part of ProcessamentoImagem.
*   ProcessamentoImagem is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
*   as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*   ProcessamentoImagem is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
*   without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
*   See the GNU General Public License for more details.
*   You should have received a copy of the GNU General Public License along with ProcessamentoImagem. 
*   If not, see http://www.gnu.org/licenses/.
*/

package View;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Form {
 
	/*
	 * Cria a janela de visualização
	 */
    public static void exibirImagem(BufferedImage image, BufferedImage image2) {
    	//Cria os paineis de visualização das imagens
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        ImageIcon icon2 = new ImageIcon(image2);
        JLabel imageLabel2 = new JLabel(icon2);
        //Cria o JFrame
        JFrame frame = new JFrame();
        
        
		Container contentPane = frame.getContentPane();
        
        //Define o layout
        contentPane.setLayout(new GridLayout());
        
        //Adiciona os paineis
        contentPane.add(new JScrollPane(imageLabel));
        contentPane.add(new JScrollPane(imageLabel2));
        //Define a função de fechamento da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Define o tamanho da janela e a torna visivel
        frame.setSize(1100, 680);
        frame.setVisible(true);
    }
}
