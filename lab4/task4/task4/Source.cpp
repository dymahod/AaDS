#include <iostream>
#include <ctime>

using namespace std;

void sort(int* array, int size) {
	int compares = 0, swaps = 0;

	clock_t time, start, finish;
	start = clock();

	bool isSorted = false;

	int temp;
	while (!isSorted) {
		isSorted = true;
		for (int i = 0; i < size - 1; i++) {
			if (array[i] > array[i + 1]) {
				isSorted = false;

				temp = array[i];
				array[i] = array[i + 1];
				array[i + 1] = temp;
				swaps++;
			}
			compares++;
		}
	}

	finish = clock();
	time = finish - start;
	cout << "Time:" << time << endl;
	cout << "Swaps:" << swaps << endl;
	cout << "Compares:" << compares << endl;

}
int main() {
	setlocale(0, "");
	int size;
	cout << "Введите размерность массива: ";
	cin >> size;

	int* array = new int[size];
	for (int i = 0; i < size; i++) {
		array[i] = i;
	}

	cout << "По возрастанию" << endl;
	sort(array, size);

	int* array1 = new int[size];
	int k = size;
	for (int i = 0; i < size; i++) {
		array1[i] = k;
		k--;
	}
	cout << "По убыванию" << endl;
	sort(array1, size);

	int* array2 = new int[size];
	for (int i = 0; i < size; i++) {
		array2[i] = rand();
	}

	cout << "Исходный" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}

	cout << "Случайный порядок" << endl;
	sort(array2, size);

	cout << "Отсортированный" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}

	system("pause");
	return 0;
}



