import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

class Teste_Trabalhador
{
	public static void main(String[] args) throws InterruptedException
	{
		int tempo, max_tmp;
		int produzir, max_q;
		Random gera_num;
		String[] Produtos;
		Set<Trabalhador> func	= new HashSet<Trabalhador>();
        	Scanner entrada         = new Scanner(System.in);
		
		System.out.println("FABRICA Ltda.");
		System.out.println("______________________________________________");
		System.out.println("Lista de produtos:");
		Produtos	= entrada.nextLine().split(" ");
		System.out.println("Maxima quantidade de cada produto (< 2 147 483 647):");
		max_q		= entrada.nextInt();
		System.out.println("Maximo de tempo para produzir a unidade (em ms; < 2 147 483 647) :");
		max_tmp	= entrada.nextInt();
		gera_num	= new Random((long)max_q);
		System.out.println("______________________________________________");

		for(var p : Produtos)
		{
			tempo		= (int)(gera_num.nextFloat() * max_tmp) + 1;
			produzir	= (int)(gera_num.nextFloat() * max_q) + 1;
			func.add(new Trabalhador(p, tempo, produzir));
		}

		for(var f : func)
			f.start();

		for(var f : func)
			f.join();

		System.out.println("______________________________________________");
		System.out.println("Fim da jornada de trabalho!");
		System.out.println("Resultados do dia util:");
		
		for(var f : func)
			System.out.println(f.quantidade + "\t" + f.produto);

		entrada.close();
	}
}

class Trabalhador extends Thread
{
	public String produto;
	public int tempo;
	public int quantidade;

	public Trabalhador(String produto, int tempo, int quantidade)
	{
		this.produto	= produto;
		this.tempo	= tempo;
		this.quantidade	= quantidade;
	}

	@Override
	public void run()
	{
		int p;
		Random aleatorio	= new Random();

		System.out.println("Comecando a produzir " + this.produto);
		for(p = 1; p <= this.quantidade; p++)
		{
			System.out.println(p + " " + this.produto);

			try
			{
				Thread.sleep(aleatorio.nextInt(this.tempo));
			}
			catch(InterruptedException e) {}
		}

		System.out.println("Acabei de produzir " + this.produto);
	}
}
