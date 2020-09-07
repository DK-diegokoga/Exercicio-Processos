package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	//Construtor
	public RedesController(){
		super();
		
	}public String ip(String so){
		StringBuffer buffer = new StringBuffer("");
		String x = "";
		String r;
		if(so.contains("Windows")){	
		try {
			Process p = Runtime.getRuntime().exec("ipconfig");//comando windows
			InputStream fluxo = p.getInputStream();//pegando o fluxo de bits que vem do processo
			InputStreamReader leitor = new InputStreamReader(fluxo);//transforma esse fluxo em String
			BufferedReader buffer1 = new BufferedReader(leitor);//Armazena ele em um buffer
			String linha = buffer1.readLine();//le a primeira linha do buffer e depois descarta, assim sucessivamente  
				 while(linha!=null){
					if (linha.contains("Adaptador")) {
						String [] arg  = linha.split(":");
						x = arg[0];
					}
					if (linha.contains("IPv4")) {
						String [] arg  = linha.split(" ");
						buffer.append(x + " - Ipv4: " + arg[arg.length - 1]);
						buffer.append("\n");
						
					}linha = buffer1.readLine();
				 }	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}else {
		try {
			Process p = Runtime.getRuntime().exec("ifconfig");//comando linux
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer1 = new BufferedReader(leitor);
			String linha = buffer1.readLine();
			while(linha!=null){
				if(linha.contains("flags")) {
					String [] arg = linha.split(":");
					x = arg[0];
				}
				if(linha.contains("inet")) {
					String [] arg = linha.split(" ");//espaços
					buffer.append(x + " - Ipv4: " + arg[0]);
					buffer.append("\n");
				}linha = buffer1.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
			}
		}
		r = buffer.toString();
		return r;
	}	
		public String ping(String so) {
			String r = "";
			String x = "";
			if(so.contains("Windows")) {
				try {
					Process p = Runtime.getRuntime().exec("ping 8.8.8.8 -n 10");//endereço de ip do google
					InputStream fluxo = p.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer1 = new BufferedReader(leitor);
					String linha = buffer1.readLine();
					while(linha!=null) {
						x = linha;
						linha = buffer1.readLine();
					}
					String[]arg = x.split(" ");
					r = "A media de ping: " + arg[arg.length - 1];
				}catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					double aux = 0;
					Process p = Runtime.getRuntime().exec("ping -c 10 8.8.8.8");//endereço de ip do google
					InputStream fluxo = p.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer1 = new BufferedReader(leitor);
					String linha = buffer1.readLine();
					while(linha!=null) {
						if (linha.contains("ttl")) {//time to live 
							String []arg= linha.split(" ");
							String []arg1= arg[6].split("=");//posição 
							aux += Double.parseDouble(arg1[1]);
						}
						linha = buffer1.readLine();
					}
					aux = aux/10;//calculo da media do ping linux
					r = "A media de ping: " + aux + " ms";
				}catch (IOException e) {
					e.printStackTrace();
				}
			}return r;
		}
}
	