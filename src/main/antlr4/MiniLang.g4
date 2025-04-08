grammar MiniLang;

program: statement* EOF;

statement
    : variable '=' expr ';'
    | 'print' '(' expr ')' ';'
    | 'if' '(' expr ')' block ('else' block)?
    | 'while' '(' expr ')' block
    | block
    ;

block: '{' statement* '}';

expr
    : expr op=('*'|'/') expr     # MulDivExpr
    | expr op=('+'|'-') expr     # AddSubExpr
    | expr op=('=='|'!='|'<'|'>'|'<='|'>=') expr # CompareExpr
    | '(' expr ')'               # ParenExpr
    | INT                        # IntExpr
    | variable                   # VarExpr
    ;

variable: ID;

ID: [a-zA-Z_][a-zA-Z0-9_]*;
INT: [0-9]+;

WS: [ \t\r\n]+ -> skip;