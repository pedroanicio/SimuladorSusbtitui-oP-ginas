import java.util.ArrayList;
import java.util.List;

public class LRU {

    private List<Integer> paginas;  // Lista para armazenar as páginas presentes na memória
    private int capacidade;         // Capacidade máxima da memória (número de molduras)

    public LRU(int tamanho) {
        this.capacidade = tamanho;
        this.paginas = new ArrayList<>(capacidade);  // Inicializa a lista de páginas com a capacidade especificada
    }

    public void inserir(int pagina) {
        if (paginas.size() == capacidade) {
            // Se a memória estiver cheia, remove a página menos recentemente usada
            int paginaRemovida = remover();
            //System.out.println("Removida página " + paginaRemovida);
        }

        // Adiciona a nova página à memória
        paginas.add(pagina);
    }

    public int remover() {
        // Remove a página menos recentemente usada (LRU - Least Recently Used)
        int paginaRemovida = paginas.remove(0);
        return paginaRemovida;
    }

    public int simular(List<Integer> sequenciaReferencias) {
        int faltasDePagina = 0;  // Contador de faltas de página

        for (int pagina : sequenciaReferencias) {
            if (!paginas.contains(pagina)) {
                // Se a página não está na memória, ocorre uma falta de página
                faltasDePagina++;
                inserir(pagina);
                System.out.println("Acessando página " + pagina + " Falta: " + faltasDePagina);
            } else {
                // Se a página já está na memória, move para o final (torna-se a mais recente)
                paginas.remove(Integer.valueOf(pagina));
                paginas.add(pagina);
                System.out.println("Acessando página " + pagina + " Já está na memória.");
            }
            System.out.println("Estado da memória: " + paginas);
        }
        return faltasDePagina;
    }

    public static void main(String[] args) {
        int qtdeMoldurasRam = 3;  // Número de molduras na memória RAM
        LRU lru = new LRU(qtdeMoldurasRam);  // Cria um objeto LRU com a capacidade especificada

        // Sequência de referências às páginas
        List<Integer> sequenciaReferencias = new ArrayList<>();
        sequenciaReferencias.add(7);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(1);
        sequenciaReferencias.add(2);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(3);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(4);
        sequenciaReferencias.add(2);
        sequenciaReferencias.add(3);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(3);
        sequenciaReferencias.add(2);
        sequenciaReferencias.add(1);
        sequenciaReferencias.add(2);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(1);
        sequenciaReferencias.add(7);
        sequenciaReferencias.add(0);
        sequenciaReferencias.add(1);

        int faltasDePagina = lru.simular(sequenciaReferencias);
        System.out.println("Número total de faltas de página: " + faltasDePagina);
    }
}
