package cn.kerninventor.tools.romance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Kern
 * @Date 2020/2/27 14:25
 */
public abstract class Memory {

    private Map<Short, Memory> snippets = new HashMap<>();

    public final static boolean precise = false;

    public Memory recall(Double someting) {
        return Objects.requireNonNull(snippets.get(someting.shortValue()),"I ...");
    }

    public void remember(Short snippet, Memory memory) {
        snippets.put(snippet, memory);
    }






















    /**
     *  Human memory is a strange, imprecise thing.
     *
     *                                              --- Alan Clements
     *
     *
     *  que el pasado era mentira, que la memoria no tenía caminos de regreso,
     *  que toda la primavera antigua era irrecuperable,
     *  y que el amor más desatinado y tenaz era de todos modos una verdad efímera.
     *
     *                                              --- Gabriel García Márquez
     */

}