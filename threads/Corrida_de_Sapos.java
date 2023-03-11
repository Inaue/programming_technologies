import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

class Corrida
{
    private static float distanciaCorrida;
    private static float maxPulo;
    private static int NumSapos;
    
    public static void main(String[] args) throws InterruptedException
    {
        Scanner entrada         = new Scanner(System.in);
        Random gerador_num      = new Random();
        Set<Sapo> corredores    = new HashSet<Sapo>();
        int i;
        
        System.out.println("CORRIDA DE SAPOS");
        System.out.println("Defina a distancia a ser percorrida (cm):");
        Corrida.distanciaCorrida    = entrada.nextFloat();
        System.out.println("Defina a quantidade de sapos participantes:");
        Corrida.NumSapos            = entrada.nextInt();
        System.out.println("Defina o maximo que um sapo consegue pular (cm):");
        Corrida.maxPulo             = entrada.nextFloat();

        System.out.println("_____________________________________________________");

        for (i = 0; i < NumSapos; i++)
            corredores.add(new Sapo(Corrida.distanciaCorrida, Corrida.maxPulo * (gerador_num.nextFloat()/2 + 0.5f)));

        for (var sapo : corredores)
                sapo.start();
        
        while(Sapo.obter_sapos_terminaram() != Sapo.obter_total_sapos())
        {
            System.out.println(Sapo.obter_sapos_terminaram() + "/" + Sapo.obter_total_sapos() + " sapos concluiram a corrida...");
            Thread.sleep(1000);
        }
        
        System.out.println("_____________________________________________________");

        for (var sapo : corredores)
            System.out.println("Posicao do sapo " + sapo.obterId() + " (pulo de " + sapo.obterDistanciaPulo() + " cm): " + sapo.obterPosicao());

        entrada.close();
    }
}

class Sapo extends Thread
{
    private static int totalSapos       = 0;
    private static int terminaram       = 0;
    private int id;
    private int posicao;
    private float distanciaPercorrida   = 0;
    private float distanciaPercorrer;
    private float distanciaPulo;

    public Sapo(float percorrer, float pulo)
    {
        this.distanciaPercorrer = percorrer;
        this.distanciaPulo      = pulo;
        this.id                 = Sapo.totalSapos++;
    }

    public void run() 
    {
        while(this.distanciaPercorrida < this.distanciaPercorrer)
        {
            this.distanciaPercorrida += this.distanciaPulo;

            try {
                Thread.sleep((long)1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        this.posicao = ++Sapo.terminaram;
    }

    public static int obter_sapos_terminaram()
    {
        return Sapo.terminaram;
    }

    public static int obter_total_sapos()
    {
        return Sapo.totalSapos;
    }

    public int obterId()
    {
        return this.id;
    }

    public int obterPosicao()
    {
        return this.posicao;
    }

    public float obterDistanciaPulo()
    {
        return this.distanciaPulo;
    }
}