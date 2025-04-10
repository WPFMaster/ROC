using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace OK_22_2
{
    internal class Program
    {
		static void rek(ref int i, List<string> oup, string input)
		{
			List<char> lovOper = new List<char>();
			List<char> midOper = new List<char>();
			List<char> higOper = new List<char>();

			int val = 0;
			bool end = true;
			for (; i < input.Length; i++)
			{
				char c = input[i];
				if (Char.IsDigit(c))
				{
					val = val * 10 + int.Parse(c.ToString());
					end = true;
				}
				else if (c == '+' || c == '-')
				{
					if (end)
						oup.Add(val.ToString());
					val = 0;

					push(oup, higOper);
					push(oup, midOper);
					push(oup, lovOper);

					lovOper.Add(c);
				}
				else if (c == '*' || c == '/')
				{
					if (end)
						oup.Add(val.ToString());
					val = 0;

					push(oup, higOper);
					push(oup, midOper);

					midOper.Add(c);
				}
				else if (c == '^')
				{
					if (end)
						oup.Add(val.ToString());
					val = 0;

					push(oup, higOper);

					higOper.Add(c);
				}
				else if (c == '(')
				{
					i++;
					rek(ref i, oup, input);
					end = false;
				}
				else if (c == ')')
				{
					break;
				}
			}
			if (end)
				oup.Add(val.ToString());
			val = 0;

			push(oup, higOper);
			push(oup, midOper);
			push(oup, lovOper);
		}
		static void test01(string input)
		{
			List<string> oup = new List<string>();

			int o = 0;
			rek(ref o, oup, input);

			for (int i = 0; i < oup.Count; i++) 
			{
				Console.Write(oup[i] + " ");
			}
			Console.WriteLine();
			Console.WriteLine(compRun(oup));
		}
		static void push(List<string> des, List<char> src)
		{
			src.Reverse();
			des.AddRange(src.Select((x)=>x.ToString()));
			src.Clear();
		}
		static int compRun(List<string> str)
		{
			Stack<int> oup = new Stack<int>();
			foreach (string s in str)
			{
				if (int.TryParse(s, out int n))
				{
					oup.Push(n);
				}
				else
				{
					oup.Push(sif(oup.Pop(), oup.Pop(), s));
				}
			}
			return oup.Count == 1 ? oup.Pop() : -123456789;
		}
		static int sif(int b, int a, string op)
		{
			switch (op)
			{
				case "+": return a + b;
				case "-": return a - b;
				case "*": return a * b;
				case "/": return a / b;
				case "^": return (int)Math.Pow(a, b);
				default: return 0;
			}
		}
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
			//test01(input);

			List<int> nX = new List<int>();
			List<int> yX = new List<int>();

			
			bool isNeg = false;
			bool isRight = false;
			bool hasX = false;
			int val = 0;
			foreach (char ch in input)
			{
				deter(ch, ref val, ref isNeg, ref isRight, ref hasX, yX, nX);
			}
			deter('+', ref val, ref isNeg, ref isRight, ref hasX, yX, nX);


			int vX = yX.Sum();
			int vN = nX.Sum();
			int oup = -(vN / vX);
			Console.WriteLine(oup);
			return;
			string[] sides = input.Split('=');
			for (int i = 0; i < 2; i++)
			{
				//item.Split('+', '-');
				foreach (var item1 in Regex.Split(sides[i], @"(?=\+)|(?=-)"))
				{
					if (item1 == "") continue;
					string seg;
					int minus = 0;
					if (item1[0] == '-')
					{
						minus = 1;
						seg = item1.Substring(1);
					}
					else if (item1[0] == '+')
					{
						seg = item1.Substring(1);
					}
					else seg = item1;

                    if (seg.Contains('x'))
					{
						if (seg == "x")
						{
							yX.Add((i ^ minus) == 0 ? 1 : -1);
							continue;
						}
						yX.Add(((i ^ minus) == 0 ? 1 : -1) * int.Parse(seg.Replace('x', '\0')));
					}
                    else
					{
						nX.Add(((i ^ minus) == 0 ? 1 : -1) * int.Parse(seg));
					}
				}
			}
			//int vX = yX.Sum();
			//int vN = nX.Sum();
			//int oup = -(vN / vX);
			Console.WriteLine(oup);
		}
		static void deter(char ch, ref int val, ref bool isNeg, ref bool isRight, ref bool hasX, List<int> yX, List<int> nX)
		{
			if (ch == '=')
			{
				if (hasX) yX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				else nX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				val = 0;
				isNeg = false;
				hasX = false;

				isRight = true;
			}
			if (ch == '-')
			{
				if (hasX) yX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				else nX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				val = 0;
				hasX = false;

				isNeg = true;
			}
			if (ch == '+')
			{
				if (hasX) yX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				else nX.Add(((isNeg ^ isRight) ? -1 : 1) * val);
				val = 0;
				hasX = false;

				isNeg = false;
			}
			if (ch == 'x')
			{
				hasX = true;
				if (val == 0) val = 1;
			}
			if (Char.IsDigit(ch))
			{
				val *= 10;
				val += int.Parse(ch.ToString());
			}
		}
    }
}
