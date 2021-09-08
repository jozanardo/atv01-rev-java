import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for App class.
 */
public class MainHelperTest {
    static final String maxColsInput = "src/test/resources/max-cols.txt";
    static final String contaDigitosInput = "src/test/resources/conta-digitos.txt";

    private static class MaxColsCase {
        int[][] input;
        int[] output;

        public MaxColsCase(int[][] input, int[] output) {
            this.input = input;
            this.output = output;
        }
    }

    private static class ContaDigitosCase {
        int input;
        int output;

        public ContaDigitosCase(int input, int output) {
            this.input = input;
            this.output = output;
        }
    }

    /**
     * Parse maxCols test input data.
     *
     * @return a list of test cases
     */
    private List<MaxColsCase> parseMaxColsCases() {
        var cases = new ArrayList<MaxColsCase>();
        try (var scanner = new Scanner(new File(maxColsInput))) {
            int qty = scanner.nextInt();

            for (int i = 0; i < qty; i++) {
                int lines = scanner.nextInt();
                int cols = scanner.nextInt();
                var input = new int[lines][cols];
                var output = new int[cols];

                for (int j = 0; j < lines; j++) {
                    for (int k = 0; k < cols; k++) {
                        input[j][k] = scanner.nextInt();
                    }
                }
                for (int j = 0; j < cols; j++) {
                    output[j] = scanner.nextInt();
                }
                cases.add(new MaxColsCase(input, output));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open maxCols input file");
            e.printStackTrace();
        }

        return cases;
    }

    private List<ContaDigitosCase> parseContaDigitosCases() {
        var cases = new ArrayList<ContaDigitosCase>();

        try (var scanner = new Scanner(new File(contaDigitosInput))) {
            while (scanner.hasNext()) {
                cases.add(new ContaDigitosCase(scanner.nextInt(), scanner.nextInt()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open contaDigitos input file");
            e.printStackTrace();
        }

        return cases;
    }

    @Test
    public void testContaDigitos() {
        var cases = parseContaDigitosCases();

        for (var singleCase : cases) {
            int output = MainHelper.contaDigitos(singleCase.input);
            var message = String.format("%d has %d digits and not %d.",
                singleCase.input, singleCase.output, output);
            Assertions.assertEquals(singleCase.output, output, message);
        }
    }

    @Test
    public void testMaximosColunas() {
        var cases = parseMaxColsCases();

        for (var singleCase : cases) {
            var result = MainHelper.maximosColunas(singleCase.input);

            Arrays.sort(result);
            Arrays.sort(singleCase.output);

            String sb = "Result: " + Arrays.toString(result) + "\n"
                +       "Expected: " + Arrays.toString(singleCase.output) + "\n";

            Assertions.assertArrayEquals(singleCase.output, result, sb);
        }
    }
}
