package cn.kerninventor.tools.romance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *     Human's memory is a strange, imprecise thing.
 *                                                  --- Alan Clements
 *
 *     que el pasado era mentira, que la memoria no tenía caminos de regreso,
 *     que toda la primavera antigua era irrecuperable,
 *     y que el amor más desatinado y tenaz era de todos modos una verdad efímera.
 *
 *                                                  --- Gabriel García Márquez
 * </p>
 *
 * @author Kern
 */
public abstract class Memory {

    private Map<Double, Memory> snippets = new HashMap<>();

    public final static boolean precise = false;

    public Memory recall(Short someting) {
        return Objects.requireNonNull(snippets.get(someting),"I ...");
    }

    public void remember(Double snippet, Memory memory) {
        snippets.put(snippet, memory);
    }


}
