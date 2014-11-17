%{
#include "types.h"
#include <ctype.h>
#include <cstdio>
#include <cstring>
#include <iostream>

int yydebug=1;
extern int yylineno,yychar;
char digit_parse[100];
unsigned digit_index=0;
extern int yylex(void);
extern int yyparse();

void yyerror(const char *str)
{
    printf("%s\nline: %d\n",str,yylineno);
}
 
int yywrap()
{
    return 1;
} 
  
main()
{
    yyparse();
} 


%}

%token PROGRAM END DECLARE ARRAY OF PROCEDURE IF THEN ELSE WHILE DO UNTIL READ WRITE GOTO RETURN NOT OR MOD AND TRUE FALSE GE LE NE ASSIGN


%union{
    int number;
    char string[100];
    struct _constant_t constant_t;
    struct _op_t op_t;
		nd::Node* node;
}

%token <number> CHARACTER
%token <string> ID REAL_CONSTANT INTEGER BOOLEAN CHAR LABEL REAL
%type <number> caractere 
%type <number> digit
%type <number> char_constant
%type <string> integer_constant
%type <string> real_constant
%type <number> boolean_constant
%type <node> program proc_body block_stmt decl_list decl variable_decl ident_list type
%type <node> simple_type array_type tamanho proc_decl proc_header formal_list
%type <node> parameter_decl parameter_type proc_signature type_list stmt_list
%type <node> stmt unlabelled_stmt assign_stmt variable array_element if_stmt
%type <node> condition loop_stmt read_stmt write_stmt goto_stmt proc_stmt return_stmt
%type <node> expr_list expression simple_expr term factor_a factor relop addop mulop
%type <node> constant label identifier

%right  '='
%left  '+'  '-' AND
%left  '*'  '/' OR MOD

%%
program           : PROGRAM identifier proc_body
                  {$$ = new nd::Node(nd::PROGRAM);
                   $$->children.push_back($2);
                   $$->children.push_back($3); printf("program\n");};

proc_body         : block_stmt
                  {$$ = new nd::Node(nd::PROC_BODY); $$->children.push_back($1); printf("proc-body\n");};

block_stmt        : DO stmt_list END {$$ = new nd::Node(nd::BLOCK_STMT); $$->children.push_back($2); }
                  | DECLARE decl_list DO stmt_list END
                  {$$ = new nd::Node(nd::BLOCK_STMT); $$->children.push_back($2); $$->children.push_back($4); printf("block_stmt\n");};

decl_list         : decl {$$ = new nd::Node(nd::DECL_LIST); $$->children.push_back($1); }
                  | decl_list ';' decl
                  {$$ = new nd::Node(nd::DECL_LIST); $$->children.push_back($1); 
                   $$->children.push_back($3); printf("decl_list\n");};

decl              : variable_decl {$$ = new nd::Node(nd::DECL); $$->children.push_back($1);}
                  | proc_decl
                  {$$ = new nd::Node(nd::DECL); $$->children.push_back($1); printf("decl\n");};

variable_decl     : type ident_list
                  {$$ = new nd::Node(nd::VARIABLE_DECL); $$->children.push_back($1);
                   $$->children.push_back($2); printf("variable_decl\n");};

ident_list        : identifier {$$ = new nd::Node(nd::IDENT_LIST);
                                $$->children.push_back($1);}
                  | ident_list ',' identifier
                  {$$ = new nd::Node(nd::IDENT_LIST);
                   $$->children.push_back($1);
                   $$->children.push_back($3);
                   printf("ident_list\n");};

type              : simple_type {$$ = new nd::Node(nd::TYPE); $$->children.push_back($1);}
                  | array_type
                  {$$ = new nd::Node(nd::TYPE); $$->children.push_back($1); printf("type\n");};

simple_type       : INTEGER {$$ = new nd::Node(nd::SIMPLE_TYPE); $$->literal_value = $1;}
                  | REAL {$$ = new nd::Node(nd::SIMPLE_TYPE); $$->literal_value = $1;}
                  | BOOLEAN  {$$ = new nd::Node(nd::SIMPLE_TYPE); $$->literal_value = $1;}
                  | CHAR {$$ = new nd::Node(nd::SIMPLE_TYPE); $$->literal_value = $1;}
                  | LABEL {$$ = new nd::Node(nd::SIMPLE_TYPE); $$->literal_value = $1; printf("simple_type\n");};

array_type        : ARRAY tamanho OF simple_type
                  {$$ = new nd::Node(nd::ARRAY_TYPE); $$->children.push_back($2); $$->children.push_back($4);
                   printf("array_type\n");};

tamanho           : integer_constant
                  {$$ = new nd::Node(nd::TAMANHO); $$->literal_value=$1; printf("tamanho\n");};

proc_decl         : proc_header block_stmt
                  {$$ = new nd::Node(nd::PROC_DECL);
                   $$->children.push_back($1);
                   $$->children.push_back($2); printf("proc_decl\n");};

proc_header       : PROCEDURE identifier {$$ = new nd::Node(nd::PROC_HEADER);
                                          $$->children.push_back($2);}
                  | PROCEDURE identifier '(' formal_list ')'
                  {$$ = new nd::Node(nd::PROC_HEADER);
                   $$->children.push_back($2);
                   $$->children.push_back($4);
                   printf("proc_header\n");};

formal_list       : parameter_decl {$$->children.push_back($1);}
                  | formal_list ';' parameter_decl
                  {$$->children.push_back($1);
                   $$->children.push_back($3); printf("formal_list\n");};

parameter_decl    : parameter_type identifier
                  {$$->children.push_back($1);
                   $$->children.push_back($2);
                   printf("parameter_decl\n");};

parameter_type    : type {$$ = new nd::Node(nd::PARAMETER_TYPE);
                          $$->children.push_back($1);}
                  | proc_signature
                  {$$ = new nd::Node(nd::PARAMETER_TYPE); $$->children.push_back($1); printf("parameter_type\n");};

proc_signature    : PROCEDURE identifier {$$ = new nd::Node(nd::PROC_SIGNATURE);
                                          $$->children.push_back($2);}
                  | PROCEDURE identifier '(' type_list ')' 
                  {$$ = new nd::Node(nd::PROC_SIGNATURE);
                   $$->children.push_back($2);
                   $$->children.push_back($4); printf("proc_signature\n");};

type_list         : parameter_type {$$ = new nd::Node(nd::TYPE_LIST); $$->children.push_back($1);}
                  | type_list ',' parameter_type
                  {$$ = new nd::Node(nd::TYPE_LIST); $$->children.push_back($1); $$->children.push_back($3); printf("type_list\n");};

stmt_list         : stmt {$$ = new nd::Node(nd::STMT_LIST); $$->children.push_back($1);}
                  | stmt_list ';' stmt
                  {$$ = new nd::Node(nd::STMT_LIST); $$->children.push_back($1); $$->children.push_back($3); printf("stmt_list\n");};

stmt              : label ':' unlabelled_stmt {$$ = new nd::Node(nd::STMT); $$->children.push_back($1); $$->children.push_back($3);}
                  | unlabelled_stmt
                  {$$ = new nd::Node(nd::STMT); $$->children.push_back($1); printf("stmt\n");};

label             : identifier {$$ = new nd::Node(nd::LABEL);
                                $$->children.push_back($1); printf("label\n");};

unlabelled_stmt   : assign_stmt {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | if_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}   
                  | loop_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | read_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | write_stmt {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | goto_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}   
                  | proc_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | return_stmt  {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1);}
                  | block_stmt {$$ = new nd::Node(nd::UNLABELLED_STMT); $$->children.push_back($1); printf("unlabelled_stmt\n");};

assign_stmt       : variable ASSIGN expression
                  {$$ = new nd::Node(nd::ASSIGN_STMT); $$->children.push_back($1);
                   $$->children.push_back($3); printf("assign_stmt\n");};

variable          : identifier {$$ = new nd::Node(nd::VARIABLE);
                                $$->children.push_back($1);}
                  | array_element
                  {$$ = new nd::Node(nd::VARIABLE); $$->children.push_back($1); printf("variable\n");};

array_element     : identifier '[' expression ']'
                  {$$ = new nd::Node(nd::ARRAY_ELEMENT); $$->children.push_back($1);
                   $$->children.push_back($3); printf("array_element\n");};

if_stmt           : IF condition THEN stmt_list END {$$ = new nd::Node(nd::IF_STMT);
                                                     $$->children.push_back($2);
                                                     $$->children.push_back($4);}
                  | IF condition THEN stmt_list ELSE stmt_list END
                  {$$ = new nd::Node(nd::IF_STMT);
                   $$->children.push_back($2);
                   $$->children.push_back($4); 
                   $$->children.push_back($6); printf("if_stmt\n");};

condition         : expression
                  {$$ = new nd::Node(nd::CONDITION); $$->children.push_back($1); printf("condition\n");};

loop_stmt         : WHILE condition block_stmt {$$ = new nd::Node(nd::LOOP_STMT); $$->children.push_back($2);
                                                $$->children.push_back($3);}
                  | DO stmt_list UNTIL
                  {$$ = new nd::Node(nd::LOOP_STMT); $$->children.push_back($2); printf("loop_stmt\n");};

read_stmt         : READ '(' ident_list ')'
                  {$$ = new nd::Node(nd::READ_STMT); $$->children.push_back($3); printf("read_stmt\n");};

write_stmt        : WRITE '(' expr_list ')'
                  {$$ = new nd::Node(nd::WRITE_STMT); $$->children.push_back($3); printf("write_stmt\n");};

goto_stmt         : GOTO label
                  {$$ = new nd::Node(nd::GOTO_STMT); $$->children.push_back($2); printf("goto_stmt\n");};

proc_stmt         : identifier {$$ = new nd::Node(nd::PROC_STMT); $$->children.push_back($1);} 
                  | identifier '(' expr_list ')' 
                  {$$ = new nd::Node(nd::PROC_STMT); $$->children.push_back($1); $$->children.push_back($3); printf("proc_stmt\n");};

return_stmt       : RETURN 
                  {$$ = new nd::Node(nd::RETURN_STMT); printf("return_stmt\n");};

expr_list         : expression {$$ = new nd::Node(nd::EXPR_LIST); $$->children.push_back($1);}
                  | expr_list ',' expression
                  {$$ = new nd::Node(nd::EXPR_LIST); $$->children.push_back($1); $$->children.push_back($3); printf("expr_list\n");};

expression        : simple_expr {$$ = new nd::Node(nd::EXPRESSION); $$->children.push_back($1);}
                  | simple_expr relop simple_expr
                  {$$ = new nd::Node(nd::EXPRESSION); $$->children.push_back($1); 
                   $$->children.push_back($2);
                   $$->children.push_back($3); printf("expression\n");};

simple_expr       : term {$$ = new nd::Node(nd::SIMPLE_EXPR); $$->children.push_back($1);}
                  | simple_expr addop term
                  {$$ = new nd::Node(nd::SIMPLE_EXPR); 
                   $$->children.push_back($1);
                   $$->children.push_back($2);
                   $$->children.push_back($3);
                   printf("simple_expr\n");};

term              : factor_a {$$ = new nd::Node(nd::TERM); $$->children.push_back($1);}
                  | term mulop factor_a
                  {$$ = new nd::Node(nd::TERM);
                   $$->children.push_back($1);
                   $$->children.push_back($2);
                   $$->children.push_back($3);
                   printf("term\n");};

factor_a          : factor {$$ = new nd::Node(nd::FACTOR_A); $$->children.push_back($1);}
                  | NOT factor {$$ = new nd::Node(nd::FACTOR_A);
                                nd::Node * node_op = new nd::Node(nd::FACTOR_A_OP);
                                node_op->literal_value = "not";
                                $$->children.push_back(node_op);
                                $$->children.push_back($2);}
                  | '-' factor
                  {$$ = new nd::Node(nd::FACTOR_A);
                   nd::Node * node_op = new nd::Node(nd::FACTOR_A_OP);
                   node_op->literal_value = "-";
                   $$->children.push_back(node_op);
                   $$->children.push_back($2); printf("factor_a\n");};

factor            : variable {$$ = new nd::Node(nd::FACTOR); $$->children.push_back($1);}
                  | constant {$$ = new nd::Node(nd::FACTOR); $$->children.push_back($1);}
                  | '(' expression ')' 
                  {$$ = new nd::Node(nd::FACTOR); $$->children.push_back($2); printf("factor\n");};

relop             : '=' {$$ = new nd::Node(nd::RELOP); $$->literal_value = "assign"; printf("relop: =\n");}
                  | '<' {$$ = new nd::Node(nd::RELOP); $$->literal_value = "lt"; printf("relop: <\n");}
                  | LE {$$ = new nd::Node(nd::RELOP); $$->literal_value = "leq"; printf("relop: <=\n");}
                  | '>' {$$ = new nd::Node(nd::RELOP); $$->literal_value = "gt"; printf("relop: >\n");}
                  | GE {$$ = new nd::Node(nd::RELOP); $$->literal_value = "geq"; printf("relop: >=\n");}
                  | NE {$$ = new nd::Node(nd::RELOP); $$->literal_value = "neq"; printf("relop: !=\n");}
                  ;

addop             : '+' {$$ = new nd::Node(nd::ADDOP); $$->literal_value = "add"; printf("addop: +\n");}
                  | '-' {$$ = new nd::Node(nd::ADDOP); $$->literal_value = "sub"; printf("addop: -\n");}
                  | OR {$$ = new nd::Node(nd::ADDOP); $$->literal_value = "or"; printf("addop: OR\n");}
                  ;

mulop             : '*' {$$ = new nd::Node(nd::MULOP); $$->literal_value = "mul"; printf("mulop: *\n");}
                  | '/' {$$ = new nd::Node(nd::MULOP); $$->literal_value = "div"; printf("mulop: /\n");}
                  | AND {$$ = new nd::Node(nd::MULOP); $$->literal_value = "and"; printf("mulop: AND\n");}
                  ;

constant          : integer_constant {$$ = new nd::Node(nd::CONSTANT); $$->literal_type="integer"; $$->literal_value=$1; printf("constant type=%s value=%s\n", $$->literal_type.c_str(), $$->literal_value.c_str());}
                  | real_constant {$$ = new nd::Node(nd::CONSTANT); $$->literal_type="real"; $$->literal_value=$1; printf("constant type=%s value=%s\n", $$->literal_type.c_str(), $$->literal_value.c_str());}
                  | char_constant {$$ = new nd::Node(nd::CONSTANT); $$->literal_type="char"; $$->literal_value=$1; printf("constant type=%s value=%s\n", $$->literal_type.c_str(), $$->literal_value.c_str());}
                  | boolean_constant {$$ = new nd::Node(nd::CONSTANT); $$->literal_type="boolean"; $$->literal_value=$1; printf("constant type=%s value=%s\n", $$->literal_type.c_str(), $$->literal_value.c_str());};

boolean_constant  : FALSE {$$='0';}
                  | TRUE
                  {$$='1'; printf("boolean_constant\n");};

integer_constant  : unsigned_integer
                  {digit_parse[digit_index] = '\0'; strcpy($$,digit_parse);
                   digit_index=0;
                   printf("integer_constant: %s\n", $$);};
                  ;

unsigned_integer  : digit {digit_parse[digit_index++] = $1; printf("single digit: %d\n", $1);};
                  | unsigned_integer digit {digit_parse[digit_index++]=$2; printf("composed digit: %d\n", $2);};
                  ;

real_constant     : REAL_CONSTANT
                  {strcpy($$,$1); printf("real_constant: %s\n", $1);};

char_constant     : caractere
                  {$$=$1; printf("char_constant\n");};
                  ;

identifier        : ID
                  {$$ = new nd::Node(nd::IDENTIFIER); $$->literal_value=$1; printf("identifier: %s\n",$1);};

digit             : '0' {$$='0'; printf("digit: 0\n");}  
                  | '1' {$$='1'; printf("digit: 1\n");} 
                  | '2' {$$='2'; printf("digit: 2\n");} 
                  | '3' {$$='3'; printf("digit: 3\n");} 
                  | '4' {$$='4'; printf("digit: 4\n");} 
                  | '5' {$$='5'; printf("digit: 5\n");} 
                  | '6' {$$='6'; printf("digit: 6\n");} 
                  | '7' {$$='7'; printf("digit: 7\n");} 
                  | '8' {$$='8'; printf("digit: 8\n");} 
                  | '9' {$$='9'; printf("digit: 9\n");}
                  ;

caractere         : CHARACTER 
                  {$$=$1; printf("caractere: %c\n",$1);}
                  ;

%%


