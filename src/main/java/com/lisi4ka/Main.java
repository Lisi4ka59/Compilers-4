package com.lisi4ka;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = """
            a = 5;
            b = 10;
            if (a < b) {
                c = a + b;
            } else {
                c = a - b;
            }
            i = 0;
            while (i < 3) {
                c = c + 1;
                i = i + 1;
            }
            print(i);
            print(c);
        """;

        CharStream charStream = CharStreams.fromString(input);
        MiniLangLexer lexer = new MiniLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniLangParser parser = new MiniLangParser(tokens);
        ParseTree tree = parser.program();

        MiniLangInterpreter interpreter = new MiniLangInterpreter();
        interpreter.visit(tree);
    }
}