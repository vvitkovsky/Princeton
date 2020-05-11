#include <iostream>

using namespace std;

const int N = 10;

void descending_binary_search(int (&array) [N], int left, int right, int value)
{
  // cout << "descending_binary_search: " << left << " " << right << endl;

  // empty interval
  if (left == right) {
    return;
  }

  // look at the middle of the interval
  int mid = (right+left)/2;
  if (array[mid] == value) {
    cout << "value found" << endl;
    return;
  }

  // interval is not splittable
  if (left+1 == right) {
    return;
  }

  if (value < array[mid]) {
    descending_binary_search(array, mid+1, right, value);
  }
  else {
    descending_binary_search(array, left, mid, value);
  }
}

void ascending_binary_search(int (&array) [N], int left, int right, int value)
{
  // cout << "ascending_binary_search: " << left << " " << right << endl;

  // empty interval
  if (left == right) {
    return;
  }

  // look at the middle of the interval
  int mid = (right+left)/2;
  if (array[mid] == value) {
    cout << "value found" << endl;
    return;
  }

  // interval is not splittable
  if (left+1 == right) {
    return;
  }

  if (value > array[mid]) {
    ascending_binary_search(array, mid+1, right, value);
  }
  else {
    ascending_binary_search(array, left, mid, value);
  }
}

void bitonic_search(int (&array) [N], int left, int right, int value)
{
  // cout << "bitonic_search: " << left << " " << right << endl;

  // empty interval
  if (left == right) {
    return;
  }

  int mid = (right+left)/2;
  if (array[mid] == value) {
    cout << "value found" << endl;
    return;
  }

  // not splittable interval
  if (left+1 == right) {
    return;
  }

  if(array[mid] > array[mid-1]) {
    if (value > array[mid]) {
      return bitonic_search(array, mid+1, right, value);
    }
    else {
      ascending_binary_search(array, left, mid, value);
      descending_binary_search(array, mid+1, right, value);
    }
  }

  else {
    if (value > array[mid]) {
      bitonic_search(array, left, mid, value);
    }
    else {
      ascending_binary_search(array, left, mid, value);
      descending_binary_search(array, mid+1, right, value);
    }
  }
}

int main()
{
  int array[N] = {2, 3, 5, 7, 9, 11, 13, 4, 1, 0};
  int value = 4;

  int left = 0;
  int right = N;

  // print "value found" is the desired value is in the bitonic array
  bitonic_search(array, left, right, value);

  return 0;
}