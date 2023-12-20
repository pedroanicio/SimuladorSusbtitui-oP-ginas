import java.util.*;

public class OTIMO {
    private int moldurasRam;
    private List<Integer> pageReferenceList;
    private Queue<Integer> filaPaginas;
    private int faltas;
    public OTIMO(int moldurasRam, List<Integer> pageReferenceList) {
        this.moldurasRam = moldurasRam;
        this.pageReferenceList = pageReferenceList;
        this.filaPaginas = new LinkedList<>();
        this.faltas = 0;
    }
    public void accessPages() {
        // Aqui ele itera sobre as páginas na sequência de referência
        for (Integer pageNumber : pageReferenceList) {
            // Aqui é feita a verificação se a página já está na fila de páginas na RAM
            if (!filaPaginas.contains(pageNumber)) {
                // Aqui é feita a verificação se a fila está cheia
                if (filaPaginas.size() == moldurasRam) {
                    // Encontrar a página que não será referenciada por mais tempo no futuro
                    int pageToReplace = findPageToReplace(pageReferenceList.subList(pageReferenceList.indexOf(pageNumber) + 1, pageReferenceList.size()));
                    // Remove a página identificada para substituição
                    filaPaginas.remove(pageToReplace);
                    System.out.println("Faltas de páginas - Página " + pageToReplace + " removida do quadro.");
                }

                filaPaginas.add(pageNumber);

                faltas++;
            } else {

                System.out.println("Página " + pageNumber + " já está no quadro.");
            }

            System.out.println("Fila de páginas: " + filaPaginas);
        }
    }

    // Encontra a página que não será referenciada por mais tempo no futuro
    private int findPageToReplace(List<Integer> futurePageReferences) {
        // Cria um conjunto para as páginas atuais na fila
        Set<Integer> frameSet = new HashSet<>(filaPaginas);
        int paginaMaisDistanteIndex = -1;
        int paginaMaisDistante = -1;

        // Itera sobre as páginas na fila atual
        for (int i = 0; i < filaPaginas.size(); i++) {
            int pagina = filaPaginas.poll(); // Remove a página da fila temporariamente
            // Verifica se a página não será referenciada no futuro
            if (!futurePageReferences.contains(pagina)) {

                return pagina;
            }
            // Aqui é encontrado o próximo índice de ocorrência da página na lista de referências futuras
            int nextOccurrenceIndex = futurePageReferences.indexOf(pagina);
            // Aqui é atualizada a página mais distante se o próximo índice for maior
            if (nextOccurrenceIndex > paginaMaisDistanteIndex) {
                paginaMaisDistanteIndex = nextOccurrenceIndex;
                paginaMaisDistante = pagina;
            }
            // Aqui a página é adicionada de volta à fila
            filaPaginas.add(pagina);
        }


        return paginaMaisDistante;
    }
    // Aqui é declarado o método para retornar o número total de faltas de páginas.
    public int getFaltas() {
        return faltas;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o número de quadros na RAM: ");
        int moldurasRam = scanner.nextInt();

        System.out.print("Insira a sequência de referência da página (separada por espaços): ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String inputSequence = scanner.nextLine();
        String[] paginasArray = inputSequence.split(" ");
        List<Integer> pageReferenceList = new ArrayList<>();
        for (String pagina : paginasArray) {
            pageReferenceList.add(Integer.parseInt(pagina.trim()));
        }

        OTIMO otimoSimulator = new OTIMO(moldurasRam, pageReferenceList);
        otimoSimulator.accessPages();

        System.out.println("Total de falta de páginas: " + otimoSimulator.getFaltas());

        scanner.close();
    }
}