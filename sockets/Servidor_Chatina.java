import java.net.*;
import java.io.IOException;
import java.util.Scanner;

class Servidor_Chatina
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket servidor	= new ServerSocket(12345);
		Socket cliente		= servidor.accept();
        	Scanner msg		= new Scanner(cliente.getInputStream());

		System.out.println("Chatina App de Conversacoes");
		System.out.println("______________________________________________");
		System.out.println("Conectado com usuario " + cliente.getInetAddress().getHostAddress());

		while(msg.hasNextLine())
			System.out.println(msg.nextLine());

		msg.close();
		cliente.close();
		servidor.close();
	}
}
