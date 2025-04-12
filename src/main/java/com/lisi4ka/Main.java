package com.lisi4ka;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter your MicroJathon program (press ⌘ + D to end input):");
        StringBuilder programBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                programBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading example: " + e.getMessage());
            return;
        }

        String program = programBuilder.toString().trim();

        if (program.isEmpty()) {
//            program = """
//print("Целочисленные операции");
//a = 10 + 5;
//b = 20 - 4;
//c = 3 * 7;
//d = 8 / 2;
//
//print(a);
//print(b);
//print(c);
//print(d);
//
//print("Вещественные числа");
//e = 5.5 + 2.5;
//f = 10.0 - 3;
//g = 2 * 3.5;
//h = 7.5 / 2;
//
//print(e);
//print(f);
//print(g);
//print(h);
//
//print("Строки: конкатенация");
//s1 = "hello" + " world";
//print(s1);
//
//print("Строки: повторение");
//s2 = "ab" * 3;
//print(s2);
//
//print("Строки: удаление подстроки");
//s3 = "banana" - "na";
//print(s3);
//            """;
//            program = """
//
//                    a = 5;
//                    b = 10;
//                    c = 5;
//                    s1 = "hello";
//                    s2 = "world";
//                    s3 = "hello";
//
//                    print("Целочисленные сравнения");
//                    print(a == c);
//                    print(a != b);
//                    print(a < b);
//                    print(b > a);
//                    print(a <= c);
//                    print(b >= c);
//                    print(a == b);
//
//                    print("Вещественные сравнения");
//                    f1 = 3.5;
//                    f2 = 3.5;
//                    f3 = 4.0;
//                    print(f1 == f2);
//                    print(f1 != f3);
//                    print(f1 < f3);
//
//                    print("Сравнение строк");
//                    print(s1 == s3);
//                    print(s1 != s2);
//                    """;

//            program = """
//                    print("Пример работы функции round");
//                    x = 3.7;
//                    y = round(x);
//                    print(y);
//
//                    print("Условие без else");
//                    a = 5;
//                    b = 10;
//                    if (a < b) {
//                        print(1);
//                    }
//
//                    print("Условие с else");
//                    c = 15;
//                    if (c < 10) {
//                        print(0);
//                    } else {
//                        print(2);
//                    }
//
//                    print("Цикл while");
//                    i = 0;
//                    while (i < 3) {
//                        print(i);
//                        i = i + 1;
//                    }
//
//                    """;

//            program = """
//                    a = 0;
//                    b = 1;
//                    i = 2;
//
//                    while (i <= 28) {
//                        temp = b;
//                        b = a + b;
//                        a = temp;
//                        i = i + 1;
//                    }
//                    print("28-й элемент ряда Фибоначчи:");
//                    print(b);
//                    """;

            program = """
                    a = 1071;
                    b = 462;
                    
                    while (a != b) {
                        if (a > b) {
                            a = a - b;
                        } else {
                            b = b - a;
                        }
                    }
                    print("НОД для чисел 1071 и 462:");
                    print(a);
                    """;
            System.out.println("Using default input:\n" + program);
        }
        System.out.println("Interpreting and running your MicroJathon program...");

        CharStream charStream = CharStreams.fromString(program);
        MicroJathonLexer lexer = new MicroJathonLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MicroJathonParser parser = new MicroJathonParser(tokens);
        ParseTree tree = parser.program();

        MicroJathonInterpreter interpreter = new MicroJathonInterpreter();
        interpreter.visit(tree);
    }
}