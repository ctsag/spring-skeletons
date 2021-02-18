package gr.nothingness.springskeletons.cucumberexternal.lib;

public class FibonacciSequencer {

    private static final Integer SIZE = 10;

    public String getSequence() {
        Integer n1 = 0;
        Integer n2 = 1;

        StringBuilder sequence = new StringBuilder(n1 + " " + n2);

        for (int i = 2; i < SIZE; i++) {
            Integer n3 = n1 + n2;

            sequence.append(" ").append(n3);
            n1 = n2;
            n2 = n3;

        }

        return sequence.toString();
    }

}