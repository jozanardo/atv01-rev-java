/**
 * App class.
 */
public final class MainHelper {

    private MainHelper() {}

    /**
     * Retorna a quantidade de dígitos presente no inteiro.
     *
     * @param n o inteiro
     * @return a quantidade de dígitos
     */
    public static int contaDigitos(int n) {
        // TODO: completar
        int cont = 1;
        while (n >= 10) {
            n = n / 10;
            cont++;
        }
        return cont;
    }

    /**
     * Retorna um array contendo os maximos das colunas da matriz.
     *
     * @param v a matriz
     * @return o array de maximos
     */
    public static int[] maximosColunas(int[][] v) {
        // TODO: completar
        int[] a;
        a = new int[v[0].length];
        for (int i = 0; i < v[0].length; i++) {
            for (int[] ints : v) {
                if (ints[i] > a[i]) {
                    a[i] = ints[i];
                }
            }
        }
        return a;
    }

}
