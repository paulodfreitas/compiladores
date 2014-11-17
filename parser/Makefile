all: lexer parser
	g++ parser.cc lexer.cc -ly -ll -o parser
parser: parser_rules.y types.h
	bison --defines=y.tab.h -o parser.cc parser_rules.y
lexer: lexer_rules
	flex -o lexer.cc lexer_rules
