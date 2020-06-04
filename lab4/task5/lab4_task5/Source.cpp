#include <iostream>
#include <ctime>

#define SIZE 2000000

using namespace std;

int d[SIZE];

void randFill(int* arr, int n) // рандом
{
	for (int i = 0; i < n; i++)
	{
		arr[i] = rand() % SIZE;
	}
}

void incFill(int* arr, int n) // возрастание
{
	for (int i = 0; i < n; i++)
	{
		arr[i] = i;
	}
}

void decFill(int* arr, int n) // убывание
{
	for (int i = 0; i < n; i++)
	{
		arr[i] = n - i;
	}
}

void zeroFill(int* d)
{
	for (int i = 0; i < SIZE; i++)
	{
		d[i] = 0;
	}
}

int sort(int* arr, int n)
{
	clock_t start = clock();

	for (int i = 0; i < n; i++)
	{
		d[arr[i]] += 1;
	}

	clock_t end = clock();

	return end - start;
}

int main()
{
	int n;
	cin >> n;

	int* a = new int[n] {};

	zeroFill(d);
	randFill(a, n); // тут метод меняешь
	cout << "rand time = " << sort(a, n) << "ms" << endl;

	zeroFill(d);
	incFill(a, n);
	cout << "inc time = " << sort(a, n) << "ms" << endl;

	zeroFill(d);
	decFill(a, n);
	cout << "dec time = " << sort(a, n) << "ms" << endl;

	/*
	for (int i = 0; i < SIZE; i++)
	{
		for (int j = 0; j < d[i]; j++)
		{
			cout << i << " ";
		}
	}

	*/

	system("pause");
	return 0;
}