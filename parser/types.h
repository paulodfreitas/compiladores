#include <list>
#include <iostream>

typedef struct _constant_t {
    char val[100];
    char type[20];
} __constant_t;

typedef struct _op_t {
    char type[100];
} __op_t;

namespace nd {
	typedef enum {
		PROGRAM,
		PROC_BODY,
        BLOCK_STMT,
        DECL_LIST,
        DECL,
        VARIABLE_DECL,
        IDENT_LIST,
        TYPE,
        SIMPLE_TYPE,
        ARRAY_TYPE,
        TAMANHO,
        PROC_DECL,
        PROC_HEADER,
        PARAMETER_TYPE,
        PROC_SIGNATURE,
        TYPE_LIST,
        STMT_LIST,
        STMT,
        LABEL,
        UNLABELLED_STMT,
        ASSIGN_STMT,
        VARIABLE,
        ARRAY_ELEMENT,
        IF_STMT,
        CONDITION,
        LOOP_STMT,
        READ_STMT,
        WRITE_STMT,
        GOTO_STMT,
        PROC_STMT,
        RETURN_STMT,
        EXPR_LIST,
        EXPRESSION,
        SIMPLE_EXPR,
        TERM,
        FACTOR_A,
        FACTOR_A_OP,
        FACTOR,
        RELOP,
        ADDOP,
        MULOP,
        CONSTANT,
		IDENTIFIER
	} NodeType;

	class Node {
	public:
	  NodeType type;
		std::string literal_value;
		std::string literal_type;
		std::list<Node*> children;
		Node(NodeType t) {
			type = t;
		}
	};
};
