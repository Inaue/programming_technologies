import java.net.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

class Cliente_Chatina
{
	public static void main(String[] args)
			throws UnknownHostException , IOException
	{
		Socket cliente		= new Socket("127.0.0.1", 12345);
        	Scanner teclado		= new Scanner(System.in);
		PrintStream saida	= new PrintStream(cliente.getOutputStream());

		System.out.println("Chatina App de Conversacoes");
		System.out.println("______________________________________________");
		System.out.println("Conectado com usuario " + cliente.getInetAddress().getHostAddress());

		while(teclado.hasNextLine())
			saida.println(teclado.nextLine());

		teclado.close();
		saida.close();
		cliente.close();
	}
}
