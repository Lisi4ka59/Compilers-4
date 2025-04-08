package com.lisi4ka;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        String input = """
            a = 5;
            b = 10;
            if (a < b) {
                c = a + b;
            }
            i = 0;
            while (i < 3) {
                c = c + 1;
                i = i + 1;
            }
            m = b / 3;
            l = "str";
            print(l * 4);
            print(m);
            print(c);
            print(l);
        """;

        CharStream charStream = CharStreams.fromString(input);
        MiniLangLexer lexer = new MiniLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniLangParser parser = new MiniLangParser(tokens);
        ParseTree tree = parser.program();
        System.out.print("hello");

        MiniLangInterpreter interpreter = new MiniLangInterpreter();
        interpreter.visit(tree);
    }
}
