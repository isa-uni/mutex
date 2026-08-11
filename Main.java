public class Main {

    static int contador = 0;

    static Mutex mutex = new Mutex();

    public static void incrementar(String nome) {

        try {

            mutex.acquire(nome);

            //Sessão crítica

            for (int i = 0; i < 5; i++) {

                System.out.println(nome +" dentro da sessão crítica, incrementando " +contador);

                contador++;
            }

            mutex.release(nome);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> incrementar("t1"));
        Thread t2 = new Thread(() -> incrementar("t2"));
        Thread t3 = new Thread(() -> incrementar("t3"));
        Thread t4 = new Thread(() -> incrementar("t4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {

            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
            "Contador final: " + contador
        );
    }
}