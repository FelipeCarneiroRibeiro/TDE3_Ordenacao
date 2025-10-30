public class AlgoritmosDeSort {

    public static class Resultado {
        long comparacoes = 0;
        long trocas = 0;
    }
    static class Node {
        int valor;
        Node proximo;
        Node(int v) { this.valor = v; this.proximo = null; }
    }
    
    public static void testarEImprimirAlgoritmo(String nome, int tipoAlgoritmo,
                                                 int[] v1, int n1,
                                                 int[] v2, int n2,
                                                 int[] v3, int n3) {
        
        Resultado r1 = roteadorSort(tipoAlgoritmo, copiarVetor(v1, n1), n1);
        Resultado r2 = roteadorSort(tipoAlgoritmo, copiarVetor(v2, n2), n2);
        Resultado r3 = roteadorSort(tipoAlgoritmo, copiarVetor(v3, n3), n3);

        System.out.println("---------------------------------");
        System.out.println("Algoritmo: " + nome);
        System.out.println("---------------------------------");
        
        String s1 = r1.comparacoes + " Comparações / " + r1.trocas + " Trocas";
        System.out.printf("  Vetor 1 (Aleatório): %s\n", s1);
        
        String s2 = r2.comparacoes + " Comparações / " + r2.trocas + " Trocas";
        System.out.printf("  Vetor 2 (Ordenado):  %s\n", s2);

        String s3 = r3.comparacoes + " Comparações / " + r3.trocas + " Trocas";
        System.out.printf("  Vetor 3 (Invertido): %s\n", s3);
        System.out.println(); 
    }

    private static Resultado roteadorSort(int tipo, int[] ar, int n) {
        switch (tipo) {
            case 1: return bubbleSort(ar, n);
            case 2: return selectionSort(ar, n);
            case 3: return cocktailSort(ar, n);
            case 4: return gnomeSort(ar, n);
            case 5: return combSort(ar, n);
            case 6: return bucketSort(ar, n);
            default: return new Resultado();
        }
    }
    
    private static void swap(int[] ar, int i, int j, Resultado res) {
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
        res.trocas++;
    }
    
    // 1. Bubble Sort
    private static Resultado bubbleSort(int[] ar, int n) {
        Resultado res = new Resultado();
        boolean trocou;
        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                res.comparacoes++;
                if (ar[j] > ar[j + 1]) {
                    swap(ar, j, j + 1, res);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
        return res;
    }

    // 2. Selection Sort
    private static Resultado selectionSort(int[] ar, int n) {
        Resultado res = new Resultado();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                res.comparacoes++;
                if (ar[j] < ar[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(ar, minIdx, i, res);
            }
        }
        return res;
    }

    // 3. Cocktail Sort
    private static Resultado cocktailSort(int[] ar, int n) {
        Resultado res = new Resultado();
        boolean trocou = true;
        int inicio = 0, fim = n - 1;
        while (trocou) {
            trocou = false;
            for (int i = inicio; i < fim; i++) {
                res.comparacoes++;
                if (ar[i] > ar[i + 1]) {
                    swap(ar, i, i + 1, res);
                    trocou = true;
                }
            }
            if (!trocou) break;
            fim--;
            trocou = false;
            for (int i = fim - 1; i >= inicio; i--) {
                res.comparacoes++;
                if (ar[i] > ar[i + 1]) {
                    swap(ar, i, i + 1, res);
                    trocou = true;
                }
            }
            inicio++;
        }
        return res;
    }

    // 4. Gnome Sort
    private static Resultado gnomeSort(int[] ar, int n) {
        Resultado res = new Resultado();
        int i = 0;
        while (i < n) {
            if (i == 0) {
                i++;
            }
            res.comparacoes++;
            if (ar[i - 1] <= ar[i]) {
                i++;
            } else {
                swap(ar, i, i - 1, res);
                i--;
            }
        }
        return res;
    }

    // 5. Comb Sort
    private static Resultado combSort(int[] ar, int n) {
        Resultado res = new Resultado();
        int gap = n;
        float shrink = 1.3f; 
        boolean trocou = true;
        while (gap > 1 || trocou) {
            int novoGap = (int) (gap / shrink);
            gap = (novoGap < 1) ? 1 : novoGap;
            
            trocou = false;
            for (int i = 0; i < n - gap; i++) {
                res.comparacoes++;
                if (ar[i] > ar[i + gap]) {
                    swap(ar, i, i + gap, res);
                    trocou = true;
                }
            }
        }
        return res;
    }

    // 6. Bucket Sort
    private static Resultado bucketSort(int[] ar, int n) {
        Resultado res = new Resultado();
        if (n <= 0) return res;
        int min = ar[0], max = ar[0];
        for (int i = 1; i < n; i++) {
            if (ar[i] < min) min = ar[i];
            if (ar[i] > max) max = ar[i];
        }
        int numBaldes = 10;
        Node[] baldes = new Node[numBaldes];
        float intervalo = (float)(max - min) / (numBaldes - 1);
        if (intervalo == 0) intervalo = 1; 
        for (int i = 0; i < n; i++) {
            int idxBalde = (int) ((ar[i] - min) / intervalo);
            Node novoNode = new Node(ar[i]);
            novoNode.proximo = baldes[idxBalde];
            baldes[idxBalde] = novoNode;
            res.trocas++; 
        }
        int idxVetor = 0;
        for (int i = 0; i < numBaldes; i++) {
            if (baldes[i] != null) {
                baldes[i] = insertionSortLista(baldes[i], res);
                Node node = baldes[i];
                while (node != null) {
                    ar[idxVetor++] = node.valor;
                    node = node.proximo;
                    res.trocas++; 
                }
            }
        }
        return res;
    }

    private static Node insertionSortLista(Node cabeca, Resultado res) {
        if (cabeca == null || cabeca.proximo == null) return cabeca;
        Node ordenada = null;
        Node atual = cabeca;
        while (atual != null) {
            Node proximo = atual.proximo; 
            if (ordenada == null) {
                atual.proximo = null;
                ordenada = atual;
            } else {
                res.comparacoes++;
                if (atual.valor <= ordenada.valor) {
                    atual.proximo = ordenada;
                    ordenada = atual;
                } else {
                    Node temp = ordenada;
                    while (temp.proximo != null) {
                        res.comparacoes++;
                        if (atual.valor <= temp.proximo.valor) break;
                        temp = temp.proximo;
                    }
                    atual.proximo = temp.proximo;
                    temp.proximo = atual;
                }
            }
            res.trocas++;
            atual = proximo;
        }
        return ordenada;
    }
    
    private static int[] copiarVetor(int[] original, int n) {
        int[] copia = new int[n];
        for (int i = 0; i < n; i++) {
            copia[i] = original[i];
        }
        return copia;
    }
}