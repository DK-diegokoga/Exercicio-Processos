package view;
import controller.RedesController;
import javax.swing.JOptionPane;

public class Main{
	public static void main(String[]args) {
		String so = System.getProperty("os.name");
		RedesController redes = new RedesController();
		
		int menu;
		
		do {
			menu = Integer.parseInt(JOptionPane.showInputDialog("Menu : \n"
															+ "1 - Ipv4 \n"
															+ "2 - PING \n"
															+ "3 - Finalizar"));
			
			switch (menu) {
			case 1: 
				JOptionPane.showMessageDialog(null,redes.ip(so));
				break;
			case 2:
				JOptionPane.showMessageDialog(null,redes.ping(so));
				break;
			case 3: 
				JOptionPane.showMessageDialog(null,"Programa finalizado");
				break;
			default:
				JOptionPane.showMessageDialog(null,"Valor inserido é invalido");
			}
		}while(menu!=3);

	}

}
