package com.epam.chernev.tag;

import java.util.List;

public class ContainsFunction {

    private ContainsFunction() {

    }

    public static boolean contains(List<String> list, String o) {
        return list.contains(o);
    }

}
