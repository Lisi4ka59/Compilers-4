package com.lisi4ka;


import java.util.HashMap;
import java.util.Map;


public class MiniLangInterpreter extends MiniLangBaseVisitor<Integer> {
    private final Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitProgram(MiniLangParser.ProgramContext ctx) {
        for (var stmt : ctx.statement()) {
            visit(stmt);
        }
        return 0;
    }

    @Override
    public Integer visitBlock(MiniLangParser.BlockContext ctx) {
        for (var stmt : ctx.statement()) {
            visit(stmt);
        }
        return 0;
    }

    @Override
    public Integer visitVariable(MiniLangParser.VariableContext ctx) {
        return memory.getOrDefault(ctx.ID().getText(), 0);
    }

    @Override
    public Integer visitIntExpr(MiniLangParser.IntExprContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitVarExpr(MiniLangParser.VarExprContext ctx) {
        return visit(ctx.variable());
    }

    @Override
    public Integer visitParenExpr(MiniLangParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitAddSubExpr(MiniLangParser.AddSubExprContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Integer visitMulDivExpr(MiniLangParser.MulDivExprContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Integer visitCompareExpr(MiniLangParser.CompareExprContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        return switch (ctx.op.getText()) {
            case "==" -> (left == right) ? 1 : 0;
            case "!=" -> (left != right) ? 1 : 0;
            case "<" -> (left < right) ? 1 : 0;
            case ">" -> (left > right) ? 1 : 0;
            case "<=" -> (left <= right) ? 1 : 0;
            case ">=" -> (left >= right) ? 1 : 0;
            default -> 0;
        };
    }

    @Override
    public Integer visitStatement(MiniLangParser.StatementContext ctx) {
        if (ctx.variable() != null && ctx.expr() != null) {
            // Присваивание
            int value = visit(ctx.expr());
            memory.put(ctx.variable().getText(), value);
        } else if (ctx.getChild(0).getText().equals("print")) {
            // Обработка print(expr);
            int value = visit(ctx.expr());
            System.out.println(value);
        } else if (ctx.getChild(0).getText().equals("if")) {
            int cond = visit(ctx.expr());
            if (cond != 0) {
                visit(ctx.block(0));
            } else if (ctx.block().size() > 1) {
                visit(ctx.block(1));
            }
        } else if (ctx.getChild(0).getText().equals("while")) {
            while (visit(ctx.expr()) != 0) {
                visit(ctx.block(0));
            }
        } else {
            // Просто блок
            visit(ctx.block(0));
        }
        return 0;
    }
}
