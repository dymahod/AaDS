using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Lab3
{
    internal class ExpressionMaxFinder
    {
        /* (i, j)-sub-expression -- expression made of numbers from i-th to j-th from input expression.
         *                          So, input is (0, size-1)-sub-expression.
         */

        private readonly int[] _numbers;
        private readonly char[] _operations;

        private readonly int _size;

        /* Matrix has in [i, j] element:
         * if i = j -- i-th number from input expression
         * if i < j -- max possible value of (i, j)-sub-expression
         * if i < j -- min possible value of (j, i)-sub-expression
         */
        private int[,] _minMaxMatrix;


        private (
            int OperationIndex, 
            (int Row, int Column) LeftOperandIndex, 
            (int Row, int Column) RightOperandIndex
            )[,] _operationIndices;


        public ExpressionMaxFinder(int[] numbers, char[] operations)
        {
            if (numbers.Length != operations.Length + 1)
                throw new ArgumentException("Signs don't fit numbers");

            _numbers = numbers;
            _operations = operations;

            _size = numbers.Length;

            _minMaxMatrix = null;
            _operationIndices = null;
        }


        public void Find()
        {
            _minMaxMatrix = new int[_size, _size];
            _operationIndices = new (int OperationIndex, (int, int) LeftOperandIndex, (int, int) RightOperandIndex)[_size, _size];


            //Main diagonal
            for (var i = 0; i < _size; ++i)
            {
                _minMaxMatrix[i, i] = _numbers[i];
                _operationIndices[i, i] = (-1, (i, i), (i, i));
            }

            //Diagonals parallel to main
            for (var diagIndex = 1; diagIndex < _size; ++diagIndex)
            {
                //Diagonal [0, diagIndex], [1, diagIndex + 1] .. [size-1 - diagIndex, size-1]
                for (var elementIndex = 0; elementIndex < _size - diagIndex; ++elementIndex)
                {
                    var row = elementIndex;
                    var column = elementIndex + diagIndex;

                    //Now get max & min values of (row, column)-sub-expr.
                    var maxValue = int.MinValue;
                    var minValue = int.MaxValue;

                    int maxOperationIndex = -1;
                    var maxLeftOperandIndex = (-1, -1);
                    var maxRightOperandIndex = (-1, -1);

                    int minOperationIndex = -1;
                    var minLeftOperandIndex = (-1, -1);
                    var minRightOperandIndex = (-1, -1);

                    /*Max value of (i, j)-sub-expr is maximum of values of
                     *{
                     * (i, i)(op)(i + 1, j),
                     * (i, i + 1)(op)(i + 2, j),
                     *          ...
                     * (i, j - 2)(op)(j - 1, j),
                     *}
                     * where (op) is corresponding operation (from _operations)
                     */
                    for (var k = row; k < column; ++k)
                    {
                        int possibleMinVal;

                        int possibleMaxVal;

                        if ('*' == _operations[k])
                        {
                            //Min           Min
                            //Max(x * y) == Max
                            //{
                            //  Max(x) * Max(y), Max(x) * Min(y),
                            //  Min(x) * Max(y), Min(x) * Min(y)
                            //}

                            var maxMax = _minMaxMatrix[row, k] * _minMaxMatrix[k + 1, column];
                            var maxMin = _minMaxMatrix[row, k] * _minMaxMatrix[column, k + 1];
                            var minMax = _minMaxMatrix[k, row] * _minMaxMatrix[k + 1, column];
                            var minMin = _minMaxMatrix[k, row] * _minMaxMatrix[column, k + 1];

                            (possibleMinVal, possibleMaxVal) = 
                                GetMinAndMax(maxMax, maxMin, minMax, minMin);

                            if (possibleMaxVal > maxValue)
                            {
                                maxValue = possibleMaxVal;

                                maxOperationIndex = k;
                                maxLeftOperandIndex = possibleMaxVal == maxMax || possibleMaxVal == maxMin ? (row, k) : (k, row);
                                maxRightOperandIndex = possibleMaxVal == maxMax || possibleMaxVal == minMax ? (k + 1, column) : (column, k + 1);
                            }

                            if (possibleMinVal < minValue)
                            {
                                minValue = possibleMinVal;

                                minOperationIndex = k;
                                minLeftOperandIndex = possibleMinVal == maxMax || possibleMinVal == maxMin ? (row, k) : (k, row);
                                minRightOperandIndex = possibleMinVal == maxMax || possibleMinVal == minMax ? (k + 1, column) : (column, k + 1);
                            }
                        }
                        else//if ('+' == _operations[k])
                        {
                            //Min           Min      Min
                            //Max(x + y) == Max(x) + Max(y)
                            possibleMaxVal = _minMaxMatrix[row, k] + _minMaxMatrix[k + 1, column];
                            if (possibleMaxVal > maxValue)
                            {
                                maxValue = possibleMaxVal;

                                maxOperationIndex = k;
                                maxLeftOperandIndex = (row, k);
                                maxRightOperandIndex = (k + 1, column);
                            }

                            possibleMinVal = _minMaxMatrix[k, row] + _minMaxMatrix[column, k + 1];
                            if (possibleMinVal < minValue)
                            {
                                minValue = possibleMinVal;

                                minOperationIndex = k;
                                minLeftOperandIndex = (k, row);
                                minRightOperandIndex = (column, k + 1);
                            }
                        }
                    }


                    _minMaxMatrix[row, column] = maxValue;
                    _minMaxMatrix[column, row] = minValue;

                    _operationIndices[row, column] = (maxOperationIndex, maxLeftOperandIndex, maxRightOperandIndex);
                    _operationIndices[column, row] = (minOperationIndex, minLeftOperandIndex, minRightOperandIndex);
                }
            }

        }

        private (int, int) GetMinAndMax(params int[] values)
        {
            return (values.Min(), values.Max());
        }


        private string ExpressionToString(int row, int column)
        {
            //If row==column?
            if (-1 == _operationIndices[row, column].OperationIndex)
                return _minMaxMatrix[row, column].ToString();

            var result = "";

            result += "(" + ExpressionToString(_operationIndices[row, column].LeftOperandIndex.Row, _operationIndices[row, column].LeftOperandIndex.Column) 
                          + ")" + _operations[_operationIndices[row, column].OperationIndex] + "(" +
                          ExpressionToString(_operationIndices[row, column].RightOperandIndex.Row, _operationIndices[row, column].RightOperandIndex.Column)
                          + ")";

            return result;
        }


        public string Expression()
        {
            if (null == _minMaxMatrix)
                throw new Exception("Call \"Find()\" before getting results!");

            return ExpressionToString(0, _size - 1);
        }

        public int Result()
        {
            if (null == _minMaxMatrix)
                throw new Exception("Call \"Find()\" before getting results!");

            return _minMaxMatrix[0, _size - 1];
        }
    }
}