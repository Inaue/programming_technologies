import java.util.Stack;
import java.util.Random;
import java.util.Scanner;

class Teste_Produtor_Consumidor
{
	public static void main(String[] args) throws InterruptedException
	{
		int capacidade, producao;
		PilhaSincronizada<Float> p;
		Produtor pr;
		Consumidor co;
        	Scanner entrada;

		entrada	= new Scanner(System.in);
		System.out.println("PRODUCAO E CONSUMO");
		System.out.println("_____________________________________");
		System.out.println("Digite o total de numeros a produzir:");
		producao	= entrada.nextInt();
		System.out.println("Digite a capacidade do armazem:");
		capacidade	= entrada.nextInt();
		System.out.println("_____________________________________");
		p		= new PilhaSincronizada<Float>(capacidade);
		pr		= new Produtor(p, producao);
		co		= new Consumidor(p, producao);
		pr.start();
		co.start();

		entrada.close();
	}
}

class PilhaSincronizada<E> extends Stack<E>
{
	public PilhaSincronizada() {}

	public PilhaSincronizada(int minCapacity)
	{
		this.ensureCapacity(minCapacity);
	}

	@Override
	public synchronized E push(E element)
	{
		while(this.size() == this.capacity())
		{
			try
			{
				this.wait();
			}
			catch(InterruptedException e) {}
		}

		this.notify();

		return super.push(element);
	}

	@Override
	public synchronized E pop()
	{
		while(this.empty())
		{
			try
			{
				this.wait();
			}
			catch(InterruptedException e) {}
		}

		this.notify();

		return super.pop();
	}

}

class Produtor extends Thread
{
	private PilhaSincronizada pilha;
	private int e_produzir;

	public Produtor(PilhaSincronizada pilha, int produzir)
	{
		this.pilha	= pilha;
		this.e_produzir	= produzir;
	}

	@Override
	public void run()
	{
		int elemento;
		float novo_e;

		for(elemento = 0; elemento < this.e_produzir; elemento++)
		{
			novo_e = (float)Math.random();
			pilha.push(novo_e);
			System.out.println("Produzido: " + novo_e);

			try
			{
				Thread.sleep((int)(Math.random() * 10));
			}
			catch(InterruptedException e) {}
		}
	}
}

class Consumidor extends Thread
{
	private PilhaSincronizada pilha;
	private int e_consumir;

	public Consumidor(PilhaSincronizada pilha, int consumir)
	{
		this.pilha	= pilha;
		this.e_consumir	= consumir;
	}

	@Override
	public void run()
	{
		int elemento;

		for(elemento = 0; elemento < this.e_consumir; elemento++)
		{
			System.out.println("Consumido: " + pilha.pop());

			try
			{
				Thread.sleep((int)(Math.random() * 100));
			}
			catch(InterruptedException e) {}
		}
	}
}
