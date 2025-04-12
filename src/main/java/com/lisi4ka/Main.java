package com.lisi4ka;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        StringBuilder programBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                programBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return;
        }

        String program = programBuilder.toString().trim();

        if (program.isEmpty()) {
            program = """
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
            """;
            System.out.println("Using default input:\n" + program);
        }

        CharStream charStream = CharStreams.fromString(program);
        MicroJathonLexer lexer = new MicroJathonLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MicroJathonParser parser = new MicroJathonParser(tokens);
        ParseTree tree = parser.program();

        MicroJathonInterpreter interpreter = new MicroJathonInterpreter();
        interpreter.visit(tree);
    }
}