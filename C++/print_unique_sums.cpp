#include <bits/stdc++.h>
using namespace std;

set<int> ans;

void printUniqueSums(int *arr, int size, int sum, int curr)
{
	if (curr == size)
	{
		// processing
		ans.insert(sum);
		return;
	}

	printUniqueSums(arr, size, sum, curr + 1);			   //1
	printUniqueSums(arr, size, sum + arr[curr], curr + 1); //2
	return;
}

int main()
{
	int n;
	std::cin >> n;
	int arr[n];
	for (int i = 0; i < n; i++)
		std::cin >> arr[i];

	printUniqueSums(arr, n, 0, 0);

	cout << ans.size() << '\n';
	for (auto &x : ans)
		cout << x << ' ';

	return 0;
}

/*
//3
//1 2 3
//Sol:
//7
//0 1 2 3 4 5 6


iterative solution

	// sum of elements
	// int sum = 0;
	// 	cout << sum << " ";
	// 	int temp;
	// 	ans.insert(sum);
	// 	for (int i = 0; i < n; i++)
	// 	{
	// 		sum = arr[i];
	// 		ans.insert(sum);
	// 		for (int j = i + 1; j < n; j++)
	// 		{
	// 			sum += arr[j];
	// 			ans.insert(sum);
	// 		}
	// 	}
	// element to element
	// 	for (int i = 0; i < n; i++)
	// 	{
	// 		temp = arr[i];
	// 		ans.insert(temp);
	// 		sum = 0;
	// 		for (int j = i + 1; j < n; j++)
	// 		{
	// 			sum = temp + arr[j];
	// 			ans.insert(sum);
	// 			sum = 0;
	// 		}
	// 	}
	*/