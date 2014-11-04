#include "tree_st.h"

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
