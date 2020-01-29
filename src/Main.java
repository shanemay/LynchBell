import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 *     Finds all Lynch-Bell numbers. This method of finding the
 *     numbers is not optimized.
 * </p>
 * <p>
 *     Note: I think it would be worth creating a class for each Bell-Lynch number.
 *     This might be something I write in the future. If so, then perhaps it can be written
 *     to check the number in Radix 10 as well as alternate numeral systems (e.g. Hex, Sexagesimal, etc.)
 * </p>
 *
 * @author Shane Carroll May
 */
public class Main {

    /**
     * The first Lynch-Bell number.
     */
    public static final int FIRST_CANDIDATE = 1;

    /**
     * The last possible Lynch-Bell number.
     */
    public static final int LAST_CANDIDATE = 98764321;

    /**
     * The base of the denary system.
     */
    public static final int DECIMAL_RADIX = 10;

    /**
     * Given a number, is the number a possible Lynch-Bell number.
     * Meaning, does this number contain unique digits with no zeros (0).
     *
     * @param value the possible Lynch-Bell number.
     *
     * @return <code>true</code> if the number is a possible Lynch-Bell number,
     * otherwise, <code>false</code>
     */
    public static boolean isViableCandidate(int value) {
        var working_copy = value;
        var digits = new HashSet<Integer>();
        while (working_copy != 0) {
            var digit = working_copy % DECIMAL_RADIX;
            if (digit == 0) {
                return false;
            } else {
                if (digits.isEmpty() || !digits.contains(digit)) {
                    digits.add(digit);
                } else {
                    return false;
                }
            }
            working_copy /= DECIMAL_RADIX;
        }
        return true;
    }

    /**
     * Gets a set of possible Lynch-Bell numbers.
     *
     * @return a {@link Set} of all possible Lynch-Bell numbers.
     */
    public static Set<Integer> getCandidates() {
        var start = FIRST_CANDIDATE;
        var candidates = new HashSet<Integer>();
        candidates.add(start);
        start++;
        for (var counter = start; counter <= LAST_CANDIDATE; counter++) {
            if (isViableCandidate(counter)) {
                candidates.add(counter);
            } // else, the value is not a viable candidate, doNothing();
        }

        return candidates;
    }

    /**
     * Determines if the given integer value is evenly divisible by all of its digits.
     *
     * @param value the number to test for divisibility.
     *
     * @return <code>true</code> if the number is divisible by all of its digits,
     * otherwise <code>false</code>.
     *
     * @throws ArithmeticException if the given integer contains a zero.
     */
    public static boolean isDivisibleByAllDigits(int value) {
        var working_copy = value;
        while (working_copy != 0) {
            var digit = working_copy % DECIMAL_RADIX;
            if (value % digit != 0) {
                return false;
            } // else, it was a divisor, keep looking, do_nothing();
            working_copy /= DECIMAL_RADIX;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Loading numbers...");
        var candidates = getCandidates();
        System.out.printf("Found %d candidates%n", candidates.size());

        System.out.println("Searching for Lynch-Bell numbers...");
        var lynchBellNumbers = new TreeSet<Integer>();
        for (var element : candidates) {
            if (isDivisibleByAllDigits(element)) {
                lynchBellNumbers.add(element);
            } // else, not a Lynch-Bell number, do_nothing();
        }

        System.out.printf("Found %d Lynch-Bell numbers%n", lynchBellNumbers.size());
        for (var element : lynchBellNumbers) {
            System.out.printf("Found: %d%n", element);
        }
    }
}