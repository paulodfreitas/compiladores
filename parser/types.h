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
		IDENTIFIER
	} NodeType;

	class Node {
	public:
	  NodeType type;
		std::string literal_value;
		std::list<Node*> children;
		Node(NodeType t) {
			type = t;
		}
	};
};
