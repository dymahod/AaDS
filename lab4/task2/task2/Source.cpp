#include <iostream>
#include <ctime>

using namespace std;

void sort(int* array, int size) {
	int compares = 0, swaps = 0;
	clock_t time, start, finish;
	start = clock();
	int j;
	for (int i = 0; i < size; i++)
	{
		int value = array[i];
		for (j = i - 1; j >= 0; j--) {
			compares++;
			if (value < array[j]) {
				swaps++;
				array[j + 1] = array[j];
			}
			else {
				break;
			}
		}
		array[j + 1] = value;
	}
	finish = clock();
	time = finish - start;
	cout << "Time:" << time << endl;
	cout << "Swaps:" << swaps << endl;
	cout << "Compares:" << compares << endl;
}
int main()
{
	setlocale(0, "");
	int size;
	cout << "������� ����������� �������: ";
	cin >> size;

	int* array = new int[size];
	for (int i = 0; i < size; i++) {
		array[i] = i;
	}

	cout << "�� �����������" << endl;
	sort(array, size);

	int* array1 = new int[size];
	int k = size;
	for (int i = 0; i < size; i++) {
		array1[i] = k;
		k--;
	}
	cout << "�� ��������" << endl;
	sort(array1, size);

	int* array2 = new int[size];
	for (int i = 0; i < size; i++) {
		array2[i] = rand();
	}

	cout << "��������" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}

	cout << "��������� �������" << endl;
	sort(array2, size);

	cout << "���������������" << endl;
	for (int i = 0; i < size; i++)
	{
		cout << array2[i] << " ";
	}
}



