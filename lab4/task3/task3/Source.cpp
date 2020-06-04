#include <iostream>
#include <ctime>

using namespace std;

int swaps = 0, compares = 0;

void sort(int* array, int low, int high) {

	if (low >= high) {
		compares++;
		return;
	}
	int supElement = array[low + (high - low) / 2];

	int l = low, h = high;
	while (l <= h) {
		while (array[l] < supElement) {
			l++;
			compares++;
		}
		while (array[h] > supElement) {
			h--;
			compares++;
		}
		if (l <= h) {
			int temp = array[l];
			array[l] = array[h];
			array[h] = temp;
			l++;
			h--;
			compares++;
			swaps++;
		}
	}

	compares++;
	if (low < h) {
		sort(array, low, h);
	}

	compares++;
	if (high > l) {
		sort(array, l, high);
	}

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
	clock_t time, start, finish;
	start = clock();
	sort(array, 0, size - 1);
	finish = clock();
	time = finish - start;
	cout << "По возрастанию" << endl;
	cout << "Time:" << time << endl;
	cout << "Swaps:" << swaps << endl;
	cout << "Compares:" << compares << endl;


	swaps = 0, compares = 0;

	int* array1 = new int[size];
	int k = size;
	for (int i = 0; i < size; i++) {
		array1[i] = k;
		k--;
	}
	start = clock();
	sort(array1, 0, size - 1);
	finish = clock();
	time = finish - start;
	cout << "По убыванию" << endl;
	cout << "Time:" << time << endl;
	cout << "Swaps:" << swaps << endl;
	cout << "Compares:" << compares << endl;


	swaps = 0, compares = 0;

	int* array2 = new int[size];
	for (int i = 0; i < size; i++) {
		array2[i] = rand();
	}

	cout << "Исходный" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}

	start = clock();
	sort(array2, 0, size - 1);
	finish = clock();
	time = finish - start;
	cout << "Случайный порядок" << endl;
	cout << "Time:" << time << endl;
	cout << "Swaps:" << swaps << endl;
	cout << "Compares:" << compares << endl;

	cout << "Отсортированный" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}

	system("pause");
	return 0;
}