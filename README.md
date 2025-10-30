# Análise de Desempenho de Algoritmos de Ordenação

Este projeto realiza uma análise de desempenho comparativa entre seis algoritmos de ordenação, medindo o número de **comparações** e **trocas** de elementos em três cenários de teste distintos: um vetor aleatório, um vetor já ordenado e um vetor em ordem inversa.

## Estrutura do Projeto

O código-fonte está dividido em duas classes Java para melhor organização e separação de responsabilidades:

* `Sort.java`: A classe principal (`main`) que contém os vetores de teste e o laço de execução. Ela é responsável por "pilotar" os testes e chamar a classe de algoritmos.
* `AlgoritmosDeSort.java`: Uma classe de biblioteca que contém toda a lógica de ordenação. Nela estão as implementações de todos os seis algoritmos, as classes auxiliares (`Resultado`, `Node`), e as funções de contagem.


## Algoritmos Analisados

1.  **Comb Sort**
2.  **Gnome Sort**
3.  **Bucket Sort**
4.  **Bubble Sort**
5.  **Selection Sort**
6.  **Cocktail Sort**

## Vetores de Teste (N=20)

* **Vetor 1 (Aleatório)**: Um cenário de caso médio, com elementos desordenados.
    `{12, 18, 9, 25, 17, 31, 22, 27, 16, 13, 19, 23, 20, 30, 14, 11, 15, 24, 26, 28}`
* **Vetor 2 (Ordenado)**: O cenário de "melhor caso" para algoritmos otimizados.
    `{5, 7, 9, 10, 12, 14, 15, 17, 19, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32}`
* **Vetor 3 (Invertido)**: O cenário de "pior caso" para muitos algoritmos O(n²).
    `{99, 85, 73, 60, 50, 40, 35, 30, 25, 20, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6}`

---

## Resultados de Desempenho

A tabela abaixo mostra o número de **Comparações (C)** e **Trocas (T)** para cada algoritmo em cada vetor.

| Algoritmo | Vetor 1 (Aleatório) | Vetor 2 (Ordenado) | Vetor 3 (Invertido) |
| :--- | :--- | :--- | :--- |
| **Bubble Sort** | 180 C / 78 T | 19 C / 0 T | 190 C / 190 T |
| **Selection Sort** | 190 C / 18 T | 190 C / 0 T | 190 C / 10 T |
| **Cocktail Sort** | 154 C / 78 T | 19 C / 0 T | 190 C / 190 T |
| **Gnome Sort** | 174 C / 78 T | 19 C / 0 T | 380 C / 190 T |
| **Comb Sort** | **129 C / 22 T** | 110 C / 0 T | **129 C / 18 T** |
| **Bucket Sort**\* | **12 C / 58 T** | **10 C / 58 T** | **47 C / 54 T** |


---

## Ranking de Desempenho

Menos Trocas entre Elementos

| Posição | Vetor 1 (Aleatório) | Vetor 2 (Ordenado) | Vetor 3 (Invertido) |
| :--- | :--- | :--- | :--- |
| **1º** | **Selection Sort (18)** | **Bubble / Selection / Cocktail / Gnome / Comb (0)** | **Selection Sort (10)** |
| 2º | Comb Sort (22) | Bucket Sort (58)\* | Comb Sort (18) |
| 3º | Bucket Sort (58)\* | (Vazio) | Bucket Sort (54)\* |
| 4º | Bubble Sort (78) | (Vazio) | Bubble Sort (190) |
| 5º | Cocktail Sort (78) | (Vazio) | Cocktail Sort (190) |
| 6º | Gnome Sort (78) | (Vazio) | Gnome Sort (190) |

Menos Interações (Comparações)

| Posição | Vetor 1 (Aleatório) | Vetor 2 (Ordenado) | Vetor 3 (Invertido) |
| :--- | :--- | :--- | :--- |
| **1º** | **Bucket Sort (12)** | **Bucket Sort (10)** | **Bucket Sort (47)** |
| 2º | Comb Sort (129) | Bubble / Cocktail / Gnome (19) | Comb Sort (129) |
| 3º | Cocktail Sort (154) | Comb Sort (110) | Bubble Sort (190) |
| 4º | Gnome Sort (174) | Selection Sort (190) | Selection Sort (190) |
| 5º | Bubble Sort (180) | (Vazio) | Cocktail Sort (190) |
| 6º | Selection Sort (190) | (Vazio) | Gnome Sort (380) |

---
