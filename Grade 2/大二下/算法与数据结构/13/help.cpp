#include <bits/stdc++.h>
using namespace std;

struct m_block {
 m_block* previous, * next;
 bool free;
 int left, right, pointer;

 m_block(m_block* previous, m_block* next, bool free, int left, int right, int pointer) {
  this->previous = previous;
  this->next = next;
  this->free = free;
  this->left = left;
  this->right = right;
  this->pointer = pointer;
  if (previous)
   previous->next = this;
  if (next)
   next->previous = this;
 }

 void remove() {
  if (previous)
   previous->next = next;
  if (next)
   next->previous = previous;
 }
};

int n, m, k, length, request_list[100000]; //0 is declined, 1 is set, 2 is remove.
m_block* h[100000], * r[100000]; //h is the heap for blocks. r is the whole memory which the task describes.

void swap(int a, int b) {
 m_block* t = h[a];
 h[a] = h[b];
 h[b] = t;
 h[a]->pointer = a;
 h[b]->pointer = b;
}

int size_block(int x) {
 return h[x]->right - h[x]->left;
}

bool better(int a, int b) {
 return size_block(a) > size_block(b);
}

void heapify_index(int x) {
 while (true) {
  int tmp = x;
  if ((x * 2 + 1 < length) && better(x * 2 + 1, tmp))
   tmp = x * 2 + 1;
  if ((x * 2 + 2 < length) && better(x * 2 + 2, tmp))
   tmp = x * 2 + 2;
  if (x == tmp)
   break;
  swap(x, tmp);
  x = tmp;
 }
}
void heap_lift(int x) {
 while (x && better(x, (x - 1) / 2)) {
  swap(x, (x - 1) / 2);
  x = (x - 1) / 2;
 }
}

void heap_remove() {
 --length;
 if (length > 0) {
  swap(0, length);
  heapify_index(0);
 }
}

void heap_remove(int a) {
 swap(a, length - 1);
 length -= 1;
 if (a >= length)
  return;
 heap_lift(a);
 heapify_index(a);
}

void heap_add(m_block* a) {
 a->pointer = length;
 h[length] = a;
 heap_lift(length++);
}

void handle_set(int a) {
 m_block* x = h[0];
 if (!length || x->right - x->left < a) {
  request_list[k++] = 0; // reject request
  //printf("-1\n");
     cout << -1 << '\n';
  return;
 }
 request_list[k++] = 1; //It can be set.
 r[k - 1] = new m_block(x->previous, x, false, x->left, x->left + a, -1);
 cout << 1 + x->left << '\n';
 x->lef