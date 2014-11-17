all: lexer parser
	g++ parser.cc lexer.cc -ly -ll -o parser
parser: parser_rules types.h
	bison --defines=y.tab.h -o parser.cc parser_rules
lexer: lexer_rules
	flex -o lexer.cc lexer_rules
