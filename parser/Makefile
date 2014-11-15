all: lexer parser
	gcc lex.yy.c y.tab.c -ly -ll -o parser
parser: parser_rules
	yacc parser_rules
lexer: lexer_rules
	lex lexer_rules