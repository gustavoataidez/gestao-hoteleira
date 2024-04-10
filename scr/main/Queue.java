
public class Queue {

    String queueItens[] = new String[7];
    String[] temp = new String[7];
    int front = 0, fim = 0;

    public Queue() {
        for (int x = 0; x < queueItens.length; x++) {
            queueItens[x] = "e";
        }
    }

    void addToQueue(String nome) {
        try {
            queueItens[fim] = nome;
            fim++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sobrecarga!");
            pegarDaQueue();
            queueItens[6] = nome;
        }

    }

    void pegarDaQueue() {
        try {
            if (fim > front && !queueItens[front].equals("e")) {
                System.out.println("Sr. " + queueItens[front] + " Removido da queue.");
                System.arraycopy(queueItens, 1, temp, 0, queueItens.length - 1);
                System.arraycopy(temp, 0, queueItens, 0, temp.length);
            } else {
                System.out.println("Queue está vazia");
                fim = 0;
            }
        } catch(NullPointerException e){
            System.out.println("Queue vazia");
        }
    }

    void mostrarQueue() {
        pegarDaQueue();
        pegarDaQueue();
        pegarDaQueue();
        System.out.println("");
    }
}