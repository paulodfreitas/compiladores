#include <iostream>
#include <cassert>
#include "symbol.h"
#include "tree_st.h"

/*
example: { // B1 
            int x; int y;
            { // B2
                int a; int b;
            }
            {  // B3
                int c;
                { // B4
                    int d;
                }
            }
         }

variables accessible to scope for each block:
    B1: x, y
    B2: x, y, a, b
    B3: x, y, c
    B4: x, y, c, d


*/

int main(int argc, char* argv[]){
    st::TreeST * context;
    // As soon as entered B1, create node for B1
    st::TreeST * tB1 = new st::TreeST(NULL);
    // Make current context = B1
    context = tB1;
    context->put("x", st::Symbol());
    context->put("y", st::Symbol());
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    // Assert that we can find x and y in current context
    assert(context->get("x") != NULL);
    assert(context->get("y") != NULL);
    // As soon as entered B2, create node for B2
    st::TreeST * tB2 = new st::TreeST(tB1);
    // Make current context = B2
    context = tB2;
    context->put("a", st::Symbol());
    context->put("b", st::Symbol());
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    // Assert that we can find x, y, a and b in current context
    assert(context->get("x") != NULL);
    assert(context->get("y") != NULL);
    assert(context->get("a") != NULL);
    assert(context->get("b") != NULL);
    // Leave B2: context = B2.parent (= B1) and destroy B2
    context = tB1;
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    delete tB2;
    // Assert that we CAN'T find a and b in context anymore
    assert(context->get("a") == NULL);
    assert(context->get("b") == NULL);
    // Enter B3, create node for B3
    st::TreeST * tB3 = new st::TreeST(tB1);
    // Make current context = B3
    context = tB3;
    context->put("c", st::Symbol());
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    // Assert that we can find x, y and c in current context
    assert(context->get("x") != NULL);
    assert(context->get("y") != NULL);
    assert(context->get("c") != NULL);
    // Enter B4, create node for B4
    st::TreeST * tB4 = new st::TreeST(tB3);
    // Make current context = B4
    context = tB4;
    context->put("d", st::Symbol());
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    // Assert that we can find x, y, c and d in current context
    assert(context->get("x") != NULL);
    assert(context->get("y") != NULL);
    assert(context->get("c") != NULL);
    assert(context->get("d") != NULL);
    // Leave B4: context = B4.parent (= B3) and destroy B4
    context = tB3;
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    delete tB4;
    // Assert that we CAN'T find d in context anymore
    assert(context->get("d") == NULL);
    // Leave B3: context = B3.parent (= B1) and destroy B3
    context = tB1;
    context->print_table();
    std::cout << "--------------------------------------------------------------------------------" << std::endl;
    delete tB3;
    // Assert that we CAN'T find c in context anymore
    assert(context->get("c") == NULL);

    return 0;
}
