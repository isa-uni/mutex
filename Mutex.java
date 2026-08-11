import java.util.LinkedList;
import java.util.Queue;

public class Mutex {

    private boolean liberado = true;
    private Queue<String> filaEspera = new LinkedList<>();

    public synchronized void acquire(String nome)throws InterruptedException {

        while (!liberado) {

            if (!filaEspera.contains(nome)) {
                filaEspera.add(nome);
            }

            System.out.println(nome + " entrou na fila de espera.");
            System.out.println("O mutex está ocupado.");
            //Não pode entrar ainda.
            wait();
        }

        liberado = false;

        System.out.println("O mutex está livre.");
        System.out.println(nome + " entrou na sessão crítica.");
    }

    public synchronized void release(String nome) {

        System.out.println(nome + " liberando a sessão.");

        liberado = true;

        //Notificando que mutex foi liberado
        notify();

        System.out.println(nome + " saiu. Mutex liberado.");
    }
}