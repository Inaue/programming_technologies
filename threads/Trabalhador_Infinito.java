import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

class Teste_Trabalhador
{
	public static void main(String[] args) throws InterruptedException
	{
		int tempo, max_tmp;
		int produzir;
		Random gera_num;
		String Produto;
		Trabalhador func;
        	Scanner entrada         = new Scanner(System.in);
		
		System.out.println("FABRICA 24h Ltda.");
		System.out.println("__________________________________________________________________");
		System.out.println("Digite o nome do produto a ser produzido:");
		Produto		= entrada.next();
		System.out.println("Maximo de tempo para produzir a unidade (em ms; < 2 147 483 647) :");
		max_tmp		= entrada.nextInt();
		gera_num	= new Random((long)max_tmp);
		System.out.println("__________________________________________________________________");
		tempo	= (int)(gera_num.nextFloat() * max_tmp) + 1;
		func	= new Trabalhador(Produto, tempo);
		System.out.println("Pressione ENTER para encerrar a jornada.");
		func.start();
		entrada.next();
		func.parar();
		func.join();
		System.out.println("__________________________________________________________________");
		System.out.println("Fim da jornada de trabalho!");
		System.out.println("Resultados do dia util:");
		
		System.out.println(func.quantidade + "\t" + func.produto);

		entrada.close();
	}
}

class Trabalhador extends Thread
{
	public String produto;
	public int tempo;
	public int quantidade;
	public boolean finalizar;

	public Trabalhador(String produto, int tempo)
	{
		this.produto	= produto;
		this.tempo	= tempo;
		this.quantidade	= 0;
		this.finalizar	= false;
	}

	@Override
	public void run()
	{
		Random aleatorio	= new Random();

		System.out.println("Comecando a produzir " + this.produto);
		while(!this.finalizar)
		{
			System.out.println(++this.quantidade + " " + this.produto);

			try
			{
				Thread.sleep(aleatorio.nextInt(this.tempo));
			}
			catch(InterruptedException e) {}
		}

		System.out.println("Acabei de produzir " + this.produto);
	}

	public void parar()
	{
		this.finalizar = true;
	}
}

