#ifndef TREE_ST_H__
#define TREE_ST_H__

#include <map>
#include <string>
#include "symbol.h"

namespace st {
    typedef std::map<std::string, Symbol> Table;
    class TreeST{
        private:
            std::map<std::string, Symbol> table;
            TreeST * parent;

        public:
            TreeST(TreeST * parent);
            void put(const std::string& name, const Symbol& sym);
            Symbol * get(const std::string& name);
            void print_table();
    };
}

#endif
