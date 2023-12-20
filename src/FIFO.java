import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;

public class FIFO {
    private int moldurasRam; // Aqui é declarado o número de molduras na RAM. Número de molduras na RAM
    private Queue<Integer> filaPaginas; // Aqui é declarada a fila para armazenar as páginas na ordem que foram carregadas
    private int faltas; // Aqui é declarado o contador de faltas de página.

    public FIFO(int moldurasRam) {
        this.moldurasRam = moldurasRam;
        this.filaPaginas = new LinkedList<>();
        this.faltas = 0;
    }

    public void accessPage(int numeroPagina) {
        if (!filaPaginas.contains(numeroPagina)) {
            if (filaPaginas.size() == moldurasRam) {
                int paginaRemovida = filaPaginas.poll(); // Aqui a página mais antiga é removida.
                System.out.println("Faltas de páginas - Página " + paginaRemovida + " removida do quadro.");
            }
            filaPaginas.add(numeroPagina); // Aqui é adicionada a nova página à fila.
            faltas++;
        } else {
            System.out.println("Página " + numeroPagina + " já está no quadro.");
        }
        System.out.println("Fila de página: " + filaPaginas);
    }

    // Aqui é declarado o método para retornar o número total de faltas de páginas.
    public int getFaltas() {
        return faltas;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o número de quadros na RAM: ");
        int moldurasRam = scanner.nextInt();

        FIFO fifoSimulator = new FIFO(moldurasRam);
        scanner.nextLine(); // Aqui é consumida a quebra de linha que está pendente.

        System.out.print("Insira a sequência de referência da página (separada por espaços): ");
        String inputSequence = scanner.nextLine();
        String[] paginas = inputSequence.split(" ");

        // Aqui é adicionada a instrução para imprimir a sequência fornecida.
        System.out.println("Sequência informada: " + Arrays.toString(paginas));

        for (String pagina : paginas) {
            pagina = pagina.trim(); // Aqui os espaços em branco extras são removidos.
            if (!pagina.isEmpty()) { // Aqui ignora as strings vazias.
                int numeroPagina = Integer.parseInt(pagina);
                fifoSimulator.accessPage(numeroPagina);
            }
        }
        // Aqui é onde o número total de faltas de páginas é impresso no console.
        System.out.println("O total de faltas de páginas é: " + fifoSimulator.getFaltas());

        scanner.close();
    }
}