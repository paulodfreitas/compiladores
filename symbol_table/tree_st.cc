#include "tree_st.h"
#include <stack>
#include <iostream>

st::TreeST::TreeST (TreeST * parent) {
    this->parent = parent;
}

void st::TreeST::put(const std::string& name, const Symbol& sym){
    if (table.count(name) == 0) {
        table[name] = sym;
    } else {
        throw std::exception();
    }
}

st::Symbol * st::TreeST::get (const std::string& name) {
    for (TreeST * cur = this; cur != NULL; cur = cur->parent) {
        if (cur->table.count(name) > 0){
            return &(cur->table[name]);
        }
    }
    return NULL;
}

void st::TreeST::print_table() {
    std::stack<TreeST*> nodes;
    TreeST * current = this;
    while (current != NULL) {
        nodes.push(current);
        current = current->parent;
    }
    
    // print nodes
    std::string prefix = "|";
    while (nodes.size() > 0) {
        current = nodes.top();
        Table::iterator it = current->table.begin();
        while (it != current->table.end()){
            std::cout << prefix << it->first << std::endl;
            ++it;
        }
        prefix += "|";
        nodes.pop();
    }
}
