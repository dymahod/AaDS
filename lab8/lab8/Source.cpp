#include <iostream>

using namespace std;

class Tree
{
private:

    struct Node
    {
        Node* left = nullptr;
        Node* right = nullptr;
        Node* parent = nullptr;
        int data;
        int level = 0;
    };

    struct NodePrintState
    {
        NodePrintState* child_state;
        bool printing_last_child;
    };

    Node* root;
    NodePrintState* root_state;

    void m_add(Node*& tree, int data, int level)
    {
        if (tree == nullptr)
        {
            tree = new Node;
            tree->data = data;
            return;
        }

        if (data < tree->data)
        {
            if (tree->left != nullptr)
            {
                m_add(tree->left, data, ++level);
            }
            else
            {
                tree->left = new Node;
                tree->left->data = data;
                tree->left->level = level;
                tree->left->parent = tree;
            }
        }
        else
        {
            if (tree->right != nullptr)
            {
                m_add(tree->right, data, ++level);
            }
            else
            {
                tree->right = new Node;
                tree->right->data = data;
                tree->right->level = level;
                tree->right->parent = tree;
            }
        }
    }

    void m_erase(Node*& tree)
    {
        if (tree->left == nullptr && tree->right == nullptr && tree->parent != nullptr)
        {
            if (tree->data < tree->parent->data)
                tree->parent->left = nullptr;
            else
                tree->parent->right = nullptr;

            delete tree;
        }
        else if (tree->left == nullptr && tree->right != nullptr && tree->parent != nullptr)
        {
            if (tree->data < tree->parent->data)
                tree->parent->left = tree->right;
            else
                tree->parent->right = tree->right;

            delete tree;
        }
        else if (tree->left != nullptr && tree->right == nullptr && tree->parent != nullptr)
        {
            if (tree->data < tree->parent->data)
                tree->parent->left = tree->left;
            else
                tree->parent->right = tree->left;

            delete tree;
        }
        else if (tree->left != nullptr && tree->right != nullptr && tree->parent != nullptr)
        {
            Node* tmp = m_findMaxLeft(tree->left);

            if (tree->data < tree->parent->data)
                tree->parent->left = tmp;
            else
                tree->parent->right = tmp;

            tmp->left = tree->left;
            tmp->right = tree->right;

            delete tree;
        }
        else if (tree->left == nullptr && tree->right == nullptr && tree->parent == nullptr)
        {
            tree->data = 0;
            root = nullptr;
        }
        else if (tree->parent == nullptr)
        {
            Node* tmp = tree;
            Node* new_root;

            if (tree->left != nullptr)
            {
                new_root = m_findMaxLeft(tree->left); // используем левое удаление

                if (new_root != tree->left)
                {
                    new_root->left = tree->left;
                    new_root->parent->right = nullptr;
                }
                else
                {
                    new_root->parent->left = nullptr;
                }

                if (tree->right != nullptr)
                {
                    new_root->right = tree->right;
                }
            }
            else
            {
                new_root = m_findMinRight(tree->right); // используем правое удаление 

                if (new_root != tree->right)
                {
                    new_root->right = tree->right;
                    new_root->parent->left = nullptr;
                }
                else
                {
                    new_root->parent->right = nullptr;
                }

                if (tree->left != nullptr)
                {
                    new_root->left = tree->left;
                }
            }

            new_root->parent = nullptr;

            root = new_root;
            delete tmp;
        }
    }

    Node* m_findMinRight(Node* tree)
    {
        if (tree->left == nullptr)
        {
            return tree;
        }
        else
        {
            m_findMaxLeft(tree->left);
        }
    }

    Node* m_findMaxLeft(Node* tree)
    {
        if (tree->right == nullptr)
        {
            return tree;
        }
        else
        {
            m_findMaxLeft(tree->right);
        }
    }

    Node* m_search(Node*& tree, int value)
    {
        if (tree == nullptr)
        {
            return nullptr;
        }

        if (value == tree->data)
        {
            return tree;
        }
        else if (value < tree->data)
        {
            m_search(tree->left, value);
        }
        else
        {
            m_search(tree->right, value);
        }
    }

    void m_print(Node*& tree)
    {
        NodePrintState* parent_state;

        if (root_state != nullptr)
        {
            cout << " ";

            NodePrintState* state = root_state;

            while (state->child_state != nullptr)
            {
                cout << (state->printing_last_child ? "  " : "| ");
                state = state->child_state;
            }

            parent_state = state;
            cout << (parent_state->printing_last_child ? (char)192 : (char)195);
            cout << (parent_state->printing_last_child ? "R" : "L");
        }
        else
        {
            parent_state = nullptr;
        }

        cout << ":" << tree->data << endl;

        if ((tree->left != nullptr) || (tree->right != nullptr))
        { // если есть дети 
            NodePrintState state;
            if (parent_state != nullptr)
            {
                parent_state->child_state = &state;
            }
            else
            {
                root_state = &state;
            }

            state.child_state = nullptr;

            //печатаем детей
            if (tree->left != nullptr)
            {
                state.printing_last_child = (tree->right == nullptr);
                m_print(tree->left);
            }

            if (tree->right != nullptr)
            {
                state.printing_last_child = true;
                m_print(tree->right);
            }

            if (parent_state != nullptr)
            {
                parent_state->child_state = nullptr;
            }
            else
            {
                root_state = nullptr;
            }
        }
    }

    void m_preOrder(Node* root)
    {
        if (root != nullptr)
        {
            cout << root->data << " ";
            m_preOrder(root->left);
            m_preOrder(root->right);
        }
    }

    void m_inOrder(Node* root)
    {
        if (root != nullptr)
        {
            m_preOrder(root->left);
            cout << root->data << " ";
            m_preOrder(root->right);
        }
    }

    void m_postOrder(Node* root)
    {
        if (root != nullptr)
        {
            m_preOrder(root->left);
            m_preOrder(root->right);
            cout << root->data << " ";
        }
    }

    int m_calcHeight(Node* node)
    {
        if (node == nullptr)
        {
            return 0;
        }

        int left, right;

        if (node->left != nullptr)
        {
            left = m_calcHeight(node->left);
        }
        else
        {
            left = -1;
        }

        if (node->right != nullptr)
        {
            right = m_calcHeight(node->right);
        }
        else
        {
            right = -1;
        }

        int max = left > right ? left : right;

        return max + 1;
    }

    int m_calcDepth(Node* node)
    {
        if (node->parent == nullptr)
        {
            return 0;
        }

        return m_calcDepth(node->parent) + 1;
    }

public:

    Tree()
    {
        root = nullptr;
        root_state = nullptr;
    }

    void add(int data)
    {
        m_add(root, data, 0);
    }

    void erase(int value)
    {
        Node* tmp = m_search(root, value);
        m_erase(tmp);
    }

    Node* search(int value)
    {
        return m_search(root, value);
    }

    void print()
    {
        m_print(root);
    }

    void printPreOrder()
    {
        m_preOrder(root);
    }

    void printInOrder()
    {
        m_inOrder(root);
    }

    void printPostOrder()
    {
        m_postOrder(root);
    }

    int calcHeight(int value)
    {
        return m_calcHeight(search(value));
    }

    int calcDepth(int value)
    {
        return m_calcDepth(search(value));
    }

    int calcLevel(int value)
    {
        return ((m_calcHeight(search(value))) - (m_calcDepth(search(value)))); 
    }
};

int main()
{
    bool is_running = true;
    Tree t;

    while (is_running)
    {
        int cmd, data;
        cout << endl;
        cout << "1. Add" << endl;
        cout << "2. Erase" << endl;
        cout << "3. Node Height" << endl;
        cout << "4. Node Depth" << endl;
        cout << "5. Node Level" << endl;
        cout << "6. Print Order" << endl;
        cout << "7. Print Tree" << endl;
        cout << "8. Exit" << endl;
        cout << " >>> ";
        cin >> cmd;

        switch (cmd)
        {
        case 1:
            cout << endl << " Enter value: ";
            cin >> data;
            t.add(data);
            break;
        case 2:
            cout << endl << " Enter value: ";
            cin >> data;
            t.erase(data);
            break;
        case 3:
            cout << endl << " Enter value: ";
            cin >> data;
            cout << t.calcHeight(data) << endl;
            break;
        case 4:
            cout << endl << " Enter value: ";
            cin >> data;
            cout << t.calcDepth(data) << endl;
            break;
        case 5:
            cout << endl << " Enter value: ";
            cin >> data;
            cout << t.calcLevel(data) << endl;
            break;
        case 6:
            int order;
            cout << "1. Pre" << endl;
            cout << "2. In" << endl;
            cout << "3. Post" << endl;
            cin >> order;
            if (order == 1)
            {
                t.printPreOrder();
            }
            else if (order == 2)
            {
                t.printInOrder();
            }
            else if (order == 3)
            {
                t.printPostOrder();
            }
            break;
        case 7:
            cout << endl;
            t.print();
            break;
        case 8:
            is_running = false;
            break;
        }
    }

    return 0;
}
