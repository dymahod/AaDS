using System;
using System.Linq;

namespace Lab3
{
    class Program
    {
        private static void DoStockExchangeCheating()
        {
            Console.WriteLine("Prices:");

            var input = Console.ReadLine();

            uint[] data;
            try
            {
                data = input.Split(' ').Select(uint.Parse).ToArray();
            }
            catch (Exception)
            {
                Console.WriteLine("Wrong input");
                return;
            }
            

            var cheater = new StockExchangeCheater(data);

            var (buyDayIndex, sellDayIndex, profit) = cheater.DoTimeWarpCheating();


            if (profit > 0)
                Console.WriteLine(
                    "Buy on day {0}, at a price of {1}, sell on day {2}, at a price of {3}, and get PROFIT of {4}!!!",
                    buyDayIndex + 1, data[buyDayIndex], sellDayIndex + 1, data[sellDayIndex], profit);
            else
                Console.WriteLine("No profit here, dude...");

        }

        private static void DoMaxFind()
        {
            Console.WriteLine("Size:");
            int size = int.Parse(Console.ReadLine());

            var nums = new int[size];
            var ops = new char[size - 1];


            Console.WriteLine("Numbs:");
            for (var i = 0; i < size; ++i)
            {
                nums[i] = int.Parse(Console.ReadLine());
            }

            Console.WriteLine("Ops:");
            for (var i = 0; i < size - 1; ++i)
            {
                ops[i] = char.Parse(Console.ReadLine());
            }



            var finder = new ExpressionMaxFinder
            (
                nums,
                ops
            );

            finder.Find();
            
            Console.WriteLine("{0} = {1}", finder.Expression(), finder.Result());
        }
        

        static void Main(string[] args)
        {
            while (true)
            {
                var choice = ReadVariantFromConsole(new[]
                {
                    "Exit",
                    "Stock exchange cheat",
                    "Expression max"
                });

                switch (choice)
                {
                case 0:
                    return;

                case 1:
                    DoStockExchangeCheating();
                    break;

                case 2:
                    DoMaxFind();
                    break;
                }
            }
        }

        private static int ReadVariantFromConsole(string[] variants)
        {
            while (true)
            {
                for (var i = 0; i < variants.Length; ++i)
                    Console.WriteLine("{0}. {1}", i + 1, variants[i]);

                var userInput = Console.ReadLine();
                int choice;
                try
                {
                    choice = int.Parse(userInput);
                }
                catch (Exception)
                {
                    Console.WriteLine("Wrong input, try again");
                    continue;
                }

                if (0 < choice && choice <= variants.Length)
                    return choice - 1;

                Console.WriteLine("Wrong input, try again");
            }
        }
    }
}