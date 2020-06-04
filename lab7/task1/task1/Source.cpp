#include <iostream>
#include <Windows.h>
#include <ctime>
#include <string>
#include <cstdlib>

int getHash(int value)
{
    int hash = 0;

    while (value != 0)
    {
        hash += (value % 10) * (value % 10);
        value /= 10;
    }

    return hash;
}

void add(int* map, int size, int value)
{
    int hash = getHash(value);
    int c = 1, d = 1, i = 0; // коэффициенты

    while (map[hash] != 0)
    {
        hash += c * i + (d * i) * (d * i);
        hash %= size;
        i++;
    }

    map[hash] = value;
}

void search(int* map, int size, int value)
{
    int counter = 0;
    int hash = getHash(value);

    while (map[hash] != value)
    {
        if (counter > size)
        {
            std::cout << "value not found!" << std::endl;
            return;
        }

        hash++;
        counter++;
        hash %= size;
    }

    std::cout << "Value found! Hash = " << hash << std::endl;
}

int main()
{
    int size = 3000000;
    int* map = new int[size] {};

    clock_t start = clock();

    int n;
    std::cout << "Enter the count: ";
    std::cin >> n;

    int num = 1, counter = 0;

    // первые 100 элементов
    clock_t start1 = clock();
    while (counter < 100)
    {
        add(map, size, num);
        num++;
        counter++;

        //cout << num << endl;
    }
    clock_t end1 = clock();
    ////////////////////////

    while (counter < n - 100)
    {
        add(map, size, num);
        num++;
        counter++;

        //cout << num << endl;
    }

    // последние 100 элементов
    clock_t start2 = clock();
    while (counter < n)
    {
        add(map, size, num);
        num++;
        counter++;

        //cout << num << endl;
    }
    clock_t end2 = clock();
    ///////////////////////////

    clock_t end = clock();


    int value;
    std::cout << "Enter the value: ";
    std::cin >> value;

    clock_t s = clock();
    std::cout << std::endl;
    search(map, size, value);
    clock_t e = clock();

    int beginTime = end1 - start1;
    int endTime = end2 - start2;
    int allTime = end - start;
    int searchTime = e - s;


    std::cout << std::endl
        << "first 100 = " << beginTime << "ms" << std::endl
        << "last 100 = " << endTime << "ms" << std::endl
        << "all time = " << allTime << "ms" << std::endl
        << "search time = " << searchTime << "ms" << std::endl
        << std::endl;

    //print(map, n);

    system("pause");
    return 0;
}