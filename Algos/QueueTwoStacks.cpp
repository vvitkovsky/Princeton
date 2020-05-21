// https://www.geeksforgeeks.org/queue-using-stacks/

#include <bits/stdc++.h> 
using namespace std;

// Method 1 (By making enQueue operation costly)
struct Queue {
    stack<int> s1, s2;

    void enQueue(int x)
    {
        // Move all elements from s1 to s2 
        while (!s1.empty()) {
            s2.push(s1.top());
            s1.pop();
        }

        // Push item into s1 
        s1.push(x);

        // Push everything back to s1 
        while (!s2.empty()) {
            s1.push(s2.top());
            s2.pop();
        }
    }

    // Dequeue an item from the queue 
    int deQueue()
    {
        // if first stack is empty 
        if (s1.empty()) {
            cout << "Q is Empty";
            exit(0);
        }

        // Return top of s1 
        int x = s1.top();
        s1.pop();
        return x;
    }
};

// Method 2 (By making deQueue operation costly)
struct Queue {
    stack<int> s1, s2;

    // Enqueue an item to the queue 
    void enQueue(int x)
    {
        // Push item into the first stack 
        s1.push(x);
    }

    // Dequeue an item from the queue 
    int deQueue()
    {
        // if both stacks are empty 
        if (s1.empty() && s2.empty()) {
            cout << "Q is empty";
            exit(0);
        }

        // if s2 is empty, move 
        // elements from s1 
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.top());
                s1.pop();
            }
        }

        // return the top item from s2 
        int x = s2.top();
        s2.pop();
        return x;
    }
};