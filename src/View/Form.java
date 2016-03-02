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
